package com.project.common.core.download.update;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by julyzeng on 2016-08-19.
 */
@SuppressWarnings("ALL")
public class Utils {
    private static String TAG = "Utils";
    Context context;
    PackageManager pm;
    public Utils(Context context) {
		this.context = context;
		pm = context.getPackageManager();
	}
    public static int getVersionCode(Context context) {
        if (context != null) {
            try {
            	return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG, e.toString());
            }
        }
        return 0;
    }
    
    public static String getVersionName(Context context){
    	if(context!=null){
    		try {
				return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
			} catch (Exception e) {
				Log.e(TAG, e.toString());
			}
    	}
		return null;
    }
    
    
    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    public static int getApplicationIcon(Context context){
        return context.getApplicationInfo().icon;
    }

    public static void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static void installAPk(Context context,File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //如果没有设置SDCard写权限，或者没有sdcard,apk文件保存在内存中，需要授予权限才能安装
        try {
            String[] command = {"chmod", "777", apkFile.toString()};
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
        } catch (IOException ignored) {
        }
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(FileProvider.getUriForFile(context, "com.project.common.provider",apkFile), "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
