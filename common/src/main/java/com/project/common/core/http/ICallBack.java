package com.project.common.core.http;


/**
 * 项目：xxx_xxx
 * 类说明：发送请求后的回调接口
 *
 * @author:julyzeng Create 2018/6/10 9:46.
 */

public interface ICallBack<T> {

    /**
     * 事件结束的监听
     */
    void onCompleted();

    /**
     * 事件错误的监听
     * @param e 错误原因
     */
    void onError( Throwable e);

    /**
     * 请求成功的请求实体
     * @param t
     */
    void onNext(T t);
}
