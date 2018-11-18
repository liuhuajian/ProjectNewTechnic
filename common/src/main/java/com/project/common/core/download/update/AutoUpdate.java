package com.project.common.core.download.update;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.project.common.core.BaseApp;
import com.project.common.core.download.update.entity.AutoUpdateEntity;
import com.project.common.core.download.update.entity.FileInfo;
import com.project.common.core.download.update.service.DownloadService;
import com.project.common.core.utils.MyLogger;

import java.io.File;


/**
 * Created by julyzeng on 2016-08-19.
 */
@SuppressWarnings("ALL")
public class AutoUpdate implements AutoUpdateInterface {
    private static String TAG = "AutoUpdate";
    private static Context context;
    private static AutoUpdateEntity entity;
    private AutoUpdateDialog dialog;
    public static final int NOTIFICATION_ID = 0;
    private static NotificationManager notificationManager;
    private static Notification notify;
    private static Notification.Builder builder;

    private FileInfo fileInfo=null;

    public AutoUpdate(Context context, AutoUpdateEntity entity) {
        this.context = context;
//        entity = new AutoUpdateEntity(json);
        this.entity = entity;
        try {
            fileInfo = new FileInfo(0, entity.getApkUrl()==null?"":entity.getApkUrl());
        } catch (Exception e) {

        }
    }


    @Override
    public void autoUpdateComfirm(String apkUrl) {
        Log.e(TAG, "autoUpdateComfirm");

        final UpdateAppDialog dialog = new UpdateAppDialog(context);
        //点击安装按钮判断本地是否存在已经下载好的安装包
        File file = new File(StorageUtils.getCacheDirectory(context)+"/"+apkUrl.split("/")[apkUrl.split("/").length-1]);
        if(file.exists()
                && BaseApp.sp.getString("url","").equals(apkUrl) && BaseApp.sp.getInt("finish_progress",0) == 100){
            Utils.installAPk(context,file);
            MyLogger.e("已经存在安装包，跳转到安装");
        }else {
            dialog.showDialog();
            createNotification();
            startDownloadService();
        }
    }

    /**
     * 启动下载服务
     */
    public void startDownloadService() {

        fileInfo.setFileName(entity.getApkUrl()
                .substring(entity
                                .getApkUrl()
                                .lastIndexOf("/") + 1,
                        fileInfo.getFileUrl().length()));
        Log.e(TAG,"-------getFileName--------"+fileInfo.getFileName());
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(DownloadService.ACTION_START);
        intent.putExtra(DownloadService.FILE_INFO, fileInfo);
        context.startService(intent);
    }

    @SuppressLint("NewApi")
	private void createNotification() {
        int icon = Utils.getApplicationIcon(context);
        String applicationName = Utils.getApplicationName(context);
        builder = new Notification.Builder(context)
                .setSmallIcon(icon)
                .setContentTitle(applicationName)
                .setContentText("正在下载:0%");
        notify = builder.build();
        notify.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notify);
    }

    @SuppressLint("NewApi")
	public static void updateProgress(int progress) {
        builder.setContentText("正在下载:" + progress + "%").setProgress(100, progress, false);
        notify = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notify);
        if (progress == 100) {
            notificationManager.cancel(NOTIFICATION_ID);
        }
    }

    @Override
    public void autoUpdateCancle() {

    }

    public void setAutoDialogListener(AutoUpdateDialog.AutoUpdateDialogListener listener) {

        if (!Utils.getVersionName(context).equals(entity.getVersionName())) {
            dialog = new AutoUpdateDialog(context, entity, listener, this);
            dialog.showAutoUpdateDialog();
        }
    }

    /**
     * 弹出吐司
     *
     * @param s
     */
    public static void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
