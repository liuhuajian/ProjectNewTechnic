package com.project.common.core.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * 项目：国民健康平台
 *
 * @Creator:liuhuajian
 * @创建日期： 2018/11/8 20:20
 * @版本0.2
 * @类说明：
 */
public class ResourceUtil {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    public static Uri resourceIdToUri(Context context, int resourceId) {
        String data = ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId;
        return Uri.parse(data);
    }
}
