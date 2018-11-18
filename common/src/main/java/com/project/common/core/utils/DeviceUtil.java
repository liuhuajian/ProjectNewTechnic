package com.project.common.core.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/8/1 16:27
 * @版本0.1
 * @类说明：设备相关参数获取
 */

public class DeviceUtil {


    /**
     * 获取设备唯一标识ID,不需要权限获取
     */
    public static final String getDeviceId(Context context){

        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        return id;

    }
}
