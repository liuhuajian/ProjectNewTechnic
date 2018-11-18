package com.project.common.core.view.uiManage;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewStub;

/**
 *项目 国民健康
 * @Create by yexm
 * @创建日期 2018/7/27 10:21
 *
 * @版本 0.1
 * @类说明: 界面状态Builder
 */

public class StateLayoutManagerBuilder {


    Context context;
    View contentView;
    /**
     * 不同情况的页面内容资源ID和ViewStub
     */
    int loadingLayoutResId;
    ViewStub netWorkErrorVs;
    ViewStub emptyDataVs;
    ViewStub errorVs;
    ViewStub systemMaintainVs;
    ViewStub systemBusyVs;
    /**
     * 不同情况重试控件资源ID
     */
    int netWorkErrorRetryViewId;
    int emptyDataRetryViewId;
    int errorRetryViewId;
    int systemMaintainRetryViewId;
    int systemBusyRetryViewId;
    int retryViewId;

    StatesLayoutManager.OnRetryListener onRetryListener;

    public StateLayoutManagerBuilder(Context context) {
        this.context = context;
    }

    /**
     * 加载页面资源ID
     *
     * @param contentLayoutResId
     * @return
     */
    public StateLayoutManagerBuilder setLoadingLayoutResId(@LayoutRes int contentLayoutResId) {
        this.loadingLayoutResId = contentLayoutResId;
        return this;
    }

    /**
     * 正文内容资源ID
     *
     * @param contentView
     * @return
     */
    public StateLayoutManagerBuilder setContentView(View contentView) {
        this.contentView = contentView;
        return this;
    }

    /**
     * 网络错误
     *
     * @param newWorkErrorId
     * @return
     */
    public StateLayoutManagerBuilder setNetWorkErrorLayoutResId(@LayoutRes int newWorkErrorId) {
        netWorkErrorVs = new ViewStub(context);
        netWorkErrorVs.setLayoutResource(newWorkErrorId);
        return this;
    }

    /**
     * 页面内容为空
     * @param noDataViewId
     * @return
     */
    public StateLayoutManagerBuilder setContentEmptyLayoutResId(@LayoutRes int noDataViewId) {
        emptyDataVs = new ViewStub(context);
        emptyDataVs.setLayoutResource(noDataViewId);
        return this;
    }


    /**
     * 页面错误
     * @param errorViewId
     * @return
     */
    public StateLayoutManagerBuilder setContentErrorResId(@LayoutRes int errorViewId) {
        errorVs = new ViewStub(context);
        errorVs.setLayoutResource(errorViewId);
        return this;
    }

    /**
     * 系统升级中
     * @param systemMaintainViewId
     * @return
     */
    public StateLayoutManagerBuilder setSystemMaintainResId(@LayoutRes int systemMaintainViewId) {
        systemMaintainVs = new ViewStub(context);
        systemMaintainVs.setLayoutResource(systemMaintainViewId);
        return this;
    }

    /**
     * 系统繁忙
     * @param systemMaintainViewId
     * @return
     */
    public StateLayoutManagerBuilder setSystemBusyResId(@LayoutRes int systemMaintainViewId) {
        systemBusyVs = new ViewStub(context);
        systemBusyVs.setLayoutResource(systemMaintainViewId);
        return this;
    }

    /**
     * 重试按钮ID
     * @param retryViewId
     * @return
     */
    public StateLayoutManagerBuilder setRetryViewId(@IdRes int retryViewId) {
        this.retryViewId = retryViewId;
        return this;
    }

    /**
     * 网络异常重试按钮ID
     * @param netWorkErrorRetryViewId
     * @return
     */
    public StateLayoutManagerBuilder setNetWorkErrorRetryViewId(@IdRes int netWorkErrorRetryViewId) {
        this.netWorkErrorRetryViewId = netWorkErrorRetryViewId;
        return this;
    }

    /**
     * 数据为空重试按钮ID
     * @param emptyDataRetryViewId
     * @return
     */
    public StateLayoutManagerBuilder setEmptyDataRetryViewId(@IdRes int emptyDataRetryViewId) {
        this.emptyDataRetryViewId = emptyDataRetryViewId;
        return this;
    }

    /**
     * 系统升级重试按钮ID
     * @param systemMaintainRetryViewId
     * @return
     */
    public StateLayoutManagerBuilder systemMaintainDataRetryViewId(@IdRes int systemMaintainRetryViewId) {
        this.systemMaintainRetryViewId = systemMaintainRetryViewId;
        return this;
    }

    /**
     * 系统繁忙重试按钮ID
     * @param systemBusyRetryViewId
     * @return
     */
    public StateLayoutManagerBuilder systemBusyDataRetryViewId(@IdRes int systemBusyRetryViewId) {
        this.systemBusyRetryViewId = systemBusyRetryViewId;
        return this;
    }

    /**
     * 页面错误重试按钮ID
     * @param errorRetryViewId
     * @return
     */
    public StateLayoutManagerBuilder setErrorRetryViewId(@IdRes int errorRetryViewId) {
        this.errorRetryViewId = errorRetryViewId;
        return this;
    }

    public StateLayoutManagerBuilder setOnRetryListener(StatesLayoutManager.OnRetryListener onRetryListener) {
        this.onRetryListener = onRetryListener;
        return this;
    }

    public StatesLayoutManager create() {
        return new StatesLayoutManager(this);
    }
}
