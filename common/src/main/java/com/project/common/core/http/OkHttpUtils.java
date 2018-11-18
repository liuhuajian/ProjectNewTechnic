package com.project.common.core.http;

import com.project.common.core.BaseApp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by yexm on 2018/7/6.
 */

public class OkHttpUtils {
    private OkHttpUtils() {

    }

    public static OkHttpUtils getInstace() {
        return new OkHttpUtils();
    }

    public OkHttpClient getOkhttp() {

        //设置 请求的缓存
        File cacheFile = new File(BaseApp.mContext.getExternalCacheDir(), "guoming");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .cache(cache);

        return builder.build();
    }
}
