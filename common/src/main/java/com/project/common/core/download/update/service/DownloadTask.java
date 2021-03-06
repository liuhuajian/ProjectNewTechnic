package com.project.common.core.download.update.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.project.common.core.download.update.StorageUtils;
import com.project.common.core.download.update.ThreadInfoSharedPreferences;
import com.project.common.core.download.update.Utils;
import com.project.common.core.download.update.entity.FileInfo;
import com.project.common.core.download.update.entity.ThreadInfo;
import com.project.common.core.download.update.receiver.NetworkReceiver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * 下载任务类
 * Created by julyzeng on 2016-08-22.
 */
public class DownloadTask {
    private static final String TAG = "DownloadTask";
    private Context context;
    private FileInfo fileInfo;
    private int finishProgress = 0;

    public DownloadTask(Context context, FileInfo fileInfo) {
        this.context = context;
        this.fileInfo = fileInfo;
    }

    public void download() {
        //读取数据库线程信息
        List<ThreadInfo> infos = ThreadInfoSharedPreferences.getThreads(fileInfo.getFileUrl());
        ThreadInfo info = null;
        if (infos.size() == 0) {
            info = new ThreadInfo(0,fileInfo.getFileUrl(),0,fileInfo.getFileLength(),0);
        }else {
            info = infos.get(0);
        }
        DownloadThread thread = new DownloadThread(info);
        thread.start();
    }

    class DownloadThread extends Thread {
        private ThreadInfo threadInfo = null;
        public DownloadThread(ThreadInfo threadInfo) {
            this.threadInfo = threadInfo;
        }

        @Override
        public void run() {
            HttpURLConnection connection = null;
            InputStream is = null;
            RandomAccessFile raf = null;
            Intent intent = new Intent(DownloadService.ACTION_UPDATE);
            //设置下载位置
            Log.e(TAG, "----------设置下载位置-------");
            try {
                //插入线程信息
                if (!ThreadInfoSharedPreferences.isExists(threadInfo.getUrl(),threadInfo.getId())){
                    ThreadInfoSharedPreferences.insertThead(threadInfo);
                }
                URL url = new URL(threadInfo.getUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10 * 1000);
                connection.setReadTimeout(10 * 1000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Charset", "UTF-8");
                //设置文件写入位置
                Log.e(TAG, "----------设置文件写入位置-------");
                int start = threadInfo.getStart() + threadInfo.getFinishProgress();
                connection.setRequestProperty("Range", "bytes=" + start + "-" + threadInfo.getEnd());
                Log.e("TAG", "----------getResponseCode-----------" + connection.getResponseCode());
                Log.e("TAG", "---start---" + start + "-----end-----" + threadInfo.getEnd());
                File file = new File(StorageUtils.getCacheDirectory(context), fileInfo.getFileName());
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(start);
                finishProgress += threadInfo.getFinishProgress();
                //开始下载
                Log.e(TAG, "----------开始下载-------");
                long time = System.currentTimeMillis();
                int progress = 0;
                Log.e("TAG", "----------getResponseCode-----------" + connection.getResponseCode());
                if (connection.getResponseCode() == 206) {
                    //读取数据
                    is = connection.getInputStream();
                    byte[] buffer = new byte[4 * 1024];
                    int length = -1;
                    while ((length = is.read(buffer)) != -1) {
                        //写入文件
                        raf.write(buffer, 0, length);
                        finishProgress += length;
                        //发送广播
                        if (System.currentTimeMillis() - time > 500) {
                            time = System.currentTimeMillis();
                            progress = (int) (finishProgress * 100L / fileInfo.getFileLength());
                            Log.e(TAG, "-----写文件-----" + finishProgress + "---" + fileInfo.getFileLength() + "-----" + progress + "%");
                            intent.putExtra(NetworkReceiver.FINISH_PROGRESS,
                                    progress);
                            context.sendBroadcast(intent);
                            //在下载暂停时保存下载位置
//                            ThreadInfoSharedPreferences.updateTread(threadInfo.getUrl(), threadInfo.getId(), finishProgress);
                        }
                    }
                    Log.e(TAG, "---------------完成---------------------");
                    progress = (int) (finishProgress * 100L / fileInfo.getFileLength());
                    Log.e(TAG, "-----写文件-----" + finishProgress + "---" + fileInfo.getFileLength() + "-----" + progress + "%");
                    //下载完成后记录一下
                    ThreadInfoSharedPreferences.updateTread(progress,threadInfo.getUrl());
                    intent.putExtra(NetworkReceiver.FINISH_PROGRESS, progress);
                    context.sendBroadcast(intent);
                    Utils.installAPk(context,file);
//                    threadDAO.deleteTread(threadInfo.getUrl(),threadInfo.getId());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (raf != null) {
                        raf.close();
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


//    /**
//     * 获取FileProvider的auth
//     *
//     * @return
//     */
//    static String getFileProviderAuthority() {
//        try {
//            for (ProviderInfo provider : CrashApplication.mContext.getPackageManager().getPackageInfo(CrashApplication.mContext.getPackageName(), PackageManager.GET_PROVIDERS).providers) {
//                if (FileProvider.class.getName().equals(provider.name) && provider.authority.endsWith(".update_apk.file_provider")) {
//                    return provider.authority;
//                }
//            }
//        } catch (PackageManager.NameNotFoundException ignore) {
//        }
//        return null;
//    }


}
