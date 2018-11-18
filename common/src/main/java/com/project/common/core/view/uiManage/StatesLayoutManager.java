package com.project.common.core.view.uiManage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

/**
 *项目 国民健康
 * @Create by yexm
 * @创建日期 2018/7/27 10:09
 *
 * @版本 0.1
 * @类说明: 界面状态置换器
 */

public class StatesLayoutManager {
    // 不同状态显示的内容
    final View contentView;
    //不同情况的页面内容资源ID和ViewStub
    final int loadingLayoutResId;
    //网络加载错误
    final ViewStub netWorkErrorVs;
    final int netWorkErrorRetryViewId;
    //返回数据为空
    final ViewStub emptyDataVs;
    final int emptyDataRetryViewId;
    //
    final ViewStub errorVs;
    final int errorRetryViewId;
    //系统维护
    final ViewStub systemMaintainVs;
    final int systemMaintainRetryViewId;
    //系统繁忙
    final ViewStub systemBusyVs;
    final int systemBusyRetryViewId;
    final int retryViewId;
    //重新加载监听
    final OnRetryListener onRetryListener;

    //根布局
    final RootFrameLayout rootFrameLayout;

    public StatesLayoutManager(StateLayoutManagerBuilder builder) {

        this.loadingLayoutResId = builder.loadingLayoutResId;
        this.contentView = builder.contentView;
        this.netWorkErrorVs = builder.netWorkErrorVs;
        this.netWorkErrorRetryViewId = builder.netWorkErrorRetryViewId;
        this.emptyDataVs = builder.emptyDataVs;
        this.emptyDataRetryViewId = builder.emptyDataRetryViewId;
        this.errorVs = builder.errorVs;
        this.errorRetryViewId = builder.errorRetryViewId;
        this.systemMaintainVs = builder.systemMaintainVs;
        this.systemMaintainRetryViewId = builder.systemMaintainRetryViewId;
        this.systemBusyVs = builder.systemBusyVs;
        this.systemBusyRetryViewId = builder.systemBusyRetryViewId;
        this.retryViewId = builder.retryViewId;
        onRetryListener = builder.onRetryListener;


        rootFrameLayout = new RootFrameLayout(builder.context);
        rootFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootFrameLayout.setStatusLayoutManager(this);
    }

    /**
     *  显示loading
     */
    public void showLoading() {
        rootFrameLayout.showLoading();
    }

    /**
     *  显示内容
     */
    public void showContent() {
        rootFrameLayout.showContent();
    }

    /**
     *  显示空数据
     */
    public void showEmptyData() {
        rootFrameLayout.showEmptyData();
    }

    /**
     *  显示网络异常
     */
    public void showNetWorkError() {
        rootFrameLayout.showNetWorkError();
    }

    /**
     *  显示系统升级中
     */
    public void showSystemMaintain() {
        rootFrameLayout.showSystemMaintain();
    }

    /**
     * 显示系统繁忙
     */
    public void showSystemBusy(){
        rootFrameLayout.showSystemBusy();
    }

    /**
     *  显示异常
     */
    public void showError() {
        rootFrameLayout.showError();
    }

    public View getSystemMaintainView(){
        return rootFrameLayout.getSystemMaintainView();

    }

    /**
     *  得到root 布局
     */
    public View getRootLayout() {
        return rootFrameLayout;
    }
    //重试监听
    public interface OnRetryListener {

        void onRetry();
    }
}
