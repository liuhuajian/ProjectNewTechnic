package com.project.common.core.http;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/7/13 10:39
 * @版本1.0
 * @类说明：第三方请求（不进行统一加密处理）
 */

public class OtherRequestManager {



    //第三方接口调用时不是统一的baseurl
    public static <T> T createServiceFrom(final Class<T> serviceClass,String url) {

        return getRetrofit(url).create(serviceClass);
    }


    public  static  <T>Observable<T> getResponseObservable(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io())//在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread());//回到主线程去处理请求结果
//                .subscribe(observer);
    }

    /**
     *  获取配置好的retrofit对象
     * @param url 第三方的请求Url
     * @return
     */
    public static Retrofit getRetrofit(String url){

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .client(OkHttpUtils.getInstace().getOkhttp())//自定义客户端 处理参数加密或者修改请求
                .build();
        return adapter;
    }

}
