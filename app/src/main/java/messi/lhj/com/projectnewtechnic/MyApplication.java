package messi.lhj.com.projectnewtechnic;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;

import messi.lhj.com.projectnewtechnic.sonic.SonicRuntimeImpl;

/**
 * Created by messi on 2017/8/27.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(),"6c9ca605a4",false);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplicationContext()), new SonicConfig.Builder().build());
        }
    }
}
