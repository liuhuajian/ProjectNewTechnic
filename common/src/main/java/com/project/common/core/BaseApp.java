package com.project.common.core;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.project.common.BuildConfig;
import com.project.common.core.http.constant.Constant;
import com.project.common.core.utils.Constants;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * 项目：健康商城
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/6/19 15:26
 * @版本1.0
 * @类说明：
 */

public class BaseApp extends MultiDexApplication {


    public static Context mContext;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
//        if (BuildConfig.isDebug) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
//        }
        ARouter.init(this);
        initUMConfig();
    }

    private void initUMConfig() {
        UMConfigure.init(this, "5b67fd4af43e486542000028", "umeng",UMConfigure.DEVICE_TYPE_PHONE, "");
        PlatformConfig.setWeixin(Constants.APP_ID, Constants.SECRET);
        UMShareAPI.get(getApplicationContext());
        Config.setMiniPreView();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
