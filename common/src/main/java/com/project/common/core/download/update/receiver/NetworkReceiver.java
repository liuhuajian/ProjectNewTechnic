package com.project.common.core.download.update.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.project.common.core.download.update.AutoUpdate;
import com.project.common.core.download.update.service.DownloadService;


/**
 * Created by julyzeng on 2016-08-22.
 */
public class NetworkReceiver extends BroadcastReceiver{
    private static final String TAG = "NetworkReceiver";
    private static boolean isExctue = false;
    public static final String FINISH_PROGRESS = "FINISH_PROGRESS";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (DownloadService.ACTION_UPDATE == intent.getAction()){
            int progress = intent.getIntExtra(FINISH_PROGRESS,0);
            Log.e(TAG,"-----FINISH_PROGRESS------"+progress);
            AutoUpdate.updateProgress(progress);
        }
//        }else {
//            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isConnected()){//有网
////                AutoUpdate.startDownloadService();
//                isExctue = false;
//            }else {//无网
//                if (!isExctue){
//                    AutoUpdate.showToast("请检查网络");
//                    isExctue = true;
//                }
//            }
//        }
    }
}
