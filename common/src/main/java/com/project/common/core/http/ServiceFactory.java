package com.project.common.core.http;


import com.project.common.core.BaseApp;
import com.project.common.core.http.constant.NetConstantURL;
import com.project.common.core.http.interceptor.RequestCommonParameterInterceptor;
import com.project.common.core.http.interceptor.TokenInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 工厂模式
 * <p>
 * 利用第三方retrofit加载网络请求 返回实体类
 */
public class ServiceFactory {

    public static <T> T createServiceFrom(final Class<T> serviceClass) {
        //设置 请求的缓存
        File cacheFile = new File(BaseApp.mContext.getExternalCacheDir(), "guoming");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        HttpLoggingInterceptor interceptor = null;
//        if (BuildConfig.DEBUG) {
            interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new RequestCommonParameterInterceptor())//添加公共参数拦截器
                .addInterceptor(new RequestPamarmsInterceptor())
//                .authenticator(new TokenAuthenticator())
//                .addInterceptor(new TokenInterceptor())
//                .addInterceptor(new CacheInterceptor())
//                .addNetworkInterceptor(new CacheInterceptor())
//                .addInterceptor(interceptor)
                .build();

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(NetConstantURL.BASE_URL)
//                .baseUrl("http://192.168.1.18:10007/test/web/")
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .client(okHttpClient)//自定义客户端 处理参数加密或者修改请求
                .build();
        return adapter.create(serviceClass);
    }


}
