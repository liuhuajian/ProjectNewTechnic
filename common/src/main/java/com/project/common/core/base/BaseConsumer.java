package com.project.common.core.base;

import com.project.common.core.http.HttpCallBack;

import io.reactivex.functions.Consumer;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/7/5 20:30
 * @版本1.0
 * @类说明：
 */

public class BaseConsumer<T> extends HttpCallBack<T> implements Consumer<T> {

    public BaseConsumer() {

    }

    @Override
    public void accept(T t) throws Exception {
        this.onNext(t);
    }

}
