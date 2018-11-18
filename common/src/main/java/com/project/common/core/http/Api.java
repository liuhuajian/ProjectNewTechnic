package com.project.common.core.http;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/4/4 11:57
 * 描    述：
 * 修订历史：
 */

public class Api {

    public Disposable disposable;

    /**
     * 对 Observable<T> 做统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     * @param responseObservable
     * @param <T>
     * @return
     */

//    protected <T>Observable<T> appLySchedulers(Observable<T> responseObservable){
//        return responseObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap((Function<? super T, ? extends ObservableSource<? extends T>>) new Function<T, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(T t) throws Exception {
//                        return flatResponse(t);
//                    }
//                });
//    }
    protected <T>Observable<T> appLySchedulers(Observable<T> responseObservable){
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    /**
     * 对网络接口返回的Response进行分割操作 对于json 解析错误以及返回的 响应实体为空的情况
     * @param response
     * @return
     */
//    private  <T> Observable<T> flatResponse(final T response){
//
//        Observable<T> tObservable = Observable.create(new ObservableOnSubscribe<T>() {
//            @Override
//            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
//
//                if (emitter.isDisposed()) {
//                    return;
//                }
//
//                if (!checkNet(BaseApp.mContext)){
////                        emitter.onError(new NetWorkThrowable(ApiErrorCode.ERROR_NO_INTERNET,"网络连接异常"));
//                    emitter.onError(new Exception("网络连接异常"));
//                    return;
//                }else if(response == null){
//                    emitter.onError(new NullPointerException());
//                }
//                else {
//                    emitter.onNext(response);
//                    MyLogger.e(JSON.toJSONString(response));
//                }
//            }
//        });
//
//        tObservable.subscribe(new Observer<T>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                disposable = d;
//            }
//
//            @Override
//            public void onNext(T t) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                disposable.dispose();
//            }
//
//            @Override
//            public void onComplete() {
//                disposable.dispose();
//            }
//        });
//        return tObservable;
//    }


    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    private boolean checkNet(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对像
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info == null || !info.isAvailable()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
