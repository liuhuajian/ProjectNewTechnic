package messi.lhj.com.projectnewtechnic.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import messi.lhj.com.projectnewtechnic.common.Constants;

/**
 * Created by messi on 2017/11/18.
 */

public class Utils {

    /**
     * 获取已安装应用商店的包名列表
     * 获取有在AndroidManifest 里面注册<category android:name="android.intent.category.APP_MARKET" />的app
     *
     * @param context
     * @return
     */
    public static ArrayList<String> getInstallAppMarkets(Context context) {
        //默认的应用市场列表，有些应用市场没有设置APP_MARKET通过隐式搜索不到
        List<String> pkgList = Arrays.asList(Constants.packages);
        ArrayList<String> pkgs = new ArrayList<>();
        if (context == null)
            return pkgs;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        if (infos == null || infos.size() == 0)
            return pkgs;
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            String pkgName = "";
            try {
                ActivityInfo activityInfo = infos.get(i).activityInfo;
                pkgName = activityInfo.packageName;


            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(pkgName))
                pkgs.add(pkgName);

        }
        Logger.d(pkgs.toString());
        //取两个list并集,去除重复
        pkgList.removeAll(pkgs);
        pkgs.addAll(pkgList);
        return pkgs;
    }

    public static boolean getAllInstallApp(Context context, String channel) {

        int index = Arrays.asList(Constants.channels).indexOf(channel);
        String packageName = Constants.packages[index];
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            Logger.d(channel+"-->已安装该应用市场");
        } catch (PackageManager.NameNotFoundException e) {
            Logger.d(channel+"-->未安装该应用市场");
            return false;
        }
        return true;
    }
}
