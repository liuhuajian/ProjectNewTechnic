package messi.lhj.com.projectnewtechnic;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by messi on 2017/8/27.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(),"6c9ca605a4",true);
    }
}
