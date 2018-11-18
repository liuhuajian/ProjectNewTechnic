package com.project.common.core.listener;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/8/14 13:49
 * @版本0.2
 * @类说明：下拉刷新帮助类，定义公用的调用方法
 */

public interface PullToRefreshListener {

    void onRefresh();//下拉刷新

    void onLoadMoreRequested();//上拉加载更多
}
