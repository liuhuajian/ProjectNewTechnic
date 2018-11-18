package com.project.common.core.http;



/**
 * 项目：xxx_xxx
 * 类说明：http请求返回类模板
 *
 * @author:julyzeng Create 2018/6/10 9:45.
 */

public abstract class HttpCallBack<T> implements ICallBack<T> {

    @Override
    public void onCompleted() {}

    @Override
    public void onError(Throwable e) {}


    @Override
    public void onNext(T t) {}
}
