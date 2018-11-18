package com.project.common.core.download.update;


import com.project.common.core.BaseApp;
import com.project.common.core.download.update.entity.ThreadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/7/11 9:05
 * @版本1.0
 * @类说明：
 */

public class ThreadInfoSharedPreferences {


    public static List<ThreadInfo> getThreads(String url){
        List<ThreadInfo> list = new ArrayList<>();
        ThreadInfo info = new ThreadInfo();
        if (BaseApp.sp.getString("url","").equals(url)){
            info.setId(BaseApp.sp.getInt("thread_id",0));
            info.setUrl(BaseApp.sp.getString("url",""));
            info.setStart(BaseApp.sp.getInt("start",0));
            info.setEnd(BaseApp.sp.getInt("end",0));
            info.setFinishProgress(BaseApp.sp.getInt("finish_progress",0));
            list.add(info);
        }
        return list;
    }

    public static synchronized void insertThead(ThreadInfo info){

        BaseApp.sp.edit()
                .putInt("thread_id",info.getId())
                .putInt("start",info.getStart())
                .putInt("end",info.getEnd())
                .putString("url",info.getUrl())
                .putInt("finish_progress",info.getId())
                .commit();
    }

    public static boolean isExists(String url, int threadId) {
        if (url.equals(BaseApp.sp.getString("url","")) && threadId == BaseApp.sp.getInt("thread_id",-1)){
            return true;
        }
        return false;
    }

    public static synchronized void updateTread( int finishProgress,String url ) {

        BaseApp.sp.edit()
                .putString("url",url)
                .putInt("finish_progress",finishProgress)
                .commit();
    }

}
