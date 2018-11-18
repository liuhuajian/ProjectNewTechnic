package com.project.common.core.base;


import com.project.common.core.http.Api;
import com.project.common.core.http.BaseObserver;
import com.project.common.core.http.HttpCallBack;
import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.utils.MyLogger;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 项目：原产地
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/4/2 22:50
 * 描    述：
 * 修订历史：
 */

public abstract class BaseCommonPresenter<T extends BaseView, H extends Api> implements BasePresenter {

    /**
     * Api类的对象
     */
    protected H mApiWrapper;
    /**
     * 使用CompositeDisposable来持有所有的compositeDisposable
     */
    protected CompositeDisposable compositeDisposable;

    public T view;

    public BaseCommonPresenter(T view, Api api) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        compositeDisposable = new CompositeDisposable();
        // 构建 ApiWrapper 对象
        mApiWrapper = (H) api;
        this.view = view;
    }

    public H getRequestApi() {
        return mApiWrapper;
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <E>
     * @return
     */
    protected <E> Observer newObserver(final HttpCallBack<E> onNext) {
        return newObserver(onNext, true);
    }

    protected <E> Observer newObserver(final HttpCallBack<E> onNext, boolean isShowDialog) {
        return newObserver(onNext, isShowDialog, false);
    }

    protected <E> Observer newObserver(final HttpCallBack<E> onNext, boolean isShowDialog, boolean isCancel) {
        return new BaseObserver<E>(onNext, view, isShowDialog, isCancel) {
            @Override
            public void onSubscribe(Disposable d) {
                if (d != null) {
                    compositeDisposable.add(d);
                }
                super.onSubscribe(d);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                MyLogger.e("onError" + e);
            }

            @Override
            public void onNext(JsonResult<E> t) {
                if (!compositeDisposable.isDisposed()) {
                    super.onNext(t);
                }
            }
        };
    }

    /**
     * 解绑 CompositeSubscription
     */
    public void unsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

}
