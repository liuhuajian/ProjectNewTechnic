package com.project.common.core.view.uiManage;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 *项目 国民健康
 * @Create by yexm
 * @创建日期 2018/7/27 9:56
 *
 * @版本 0.1
 * @类说明: baseactivity 的content 根布局
 */
public class RootFrameLayout extends FrameLayout {

    /**
     *  loading 加载id
     */
    public static final int LAYOUT_LOADING_ID = 1;

    /**
     *  内容id
     */
    public static final int LAYOUT_CONTENT_ID = 2;

    /**
     *  异常id
     */
    public static final int LAYOUT_ERROR_ID = 3;

    /**
     *  网络异常id
     */
    public static final int LAYOUT_NETWORK_ERROR_ID = 4;

    /**
     *  空数据id
     */
    public static final int LAYOUT_EMPTYDATA_ID = 5;

    /**
     *  系统升级
     */
    public static final int LAYOUT_SYSTEM_MAINTAIN_ID = 6;

    /**
     *  系统繁忙
     */
    public static final int LAYOUT_SYSTEM_BUSY_ID = 7;

    /**
     *  存放布局集合
     */
    private SparseArray<View> layoutSparseArray = new SparseArray();

    /**
     *  布局管理器
     */
    private StatesLayoutManager mStatesLayoutManager;


    public RootFrameLayout(Context context) {
        super(context);
    }

    public RootFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public RootFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RootFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setStatusLayoutManager(StatesLayoutManager statesLayoutManager) {
        mStatesLayoutManager = statesLayoutManager;
        addAllLayoutToLayout();
    }

    public void addAllLayoutToLayout() {
        if(mStatesLayoutManager.contentView != null)
        {
            layoutSparseArray.put(RootFrameLayout.LAYOUT_CONTENT_ID, mStatesLayoutManager.contentView);
            addView(mStatesLayoutManager.contentView);
        }
        if(mStatesLayoutManager.loadingLayoutResId != 0){

            View resView = LayoutInflater.from(getContext()).inflate(mStatesLayoutManager.loadingLayoutResId, null);
            layoutSparseArray.put(RootFrameLayout.LAYOUT_LOADING_ID, resView);
            resView.setVisibility(GONE);
            addView(resView);
        }
        if(mStatesLayoutManager.emptyDataVs != null) {
            addView(mStatesLayoutManager.emptyDataVs);
        }
        if(mStatesLayoutManager.errorVs != null) {
            addView(mStatesLayoutManager.errorVs);
        }
        if(mStatesLayoutManager.netWorkErrorVs != null) {
            addView(mStatesLayoutManager.netWorkErrorVs);
        }
        if(mStatesLayoutManager.systemMaintainVs != null) {
            addView(mStatesLayoutManager.systemMaintainVs);
        }
        if(mStatesLayoutManager.systemBusyVs != null) {
            addView(mStatesLayoutManager.systemBusyVs);
        }
    }

    /**
     *  显示loading
     */
    public void showLoading() {
        if(layoutSparseArray.get(LAYOUT_LOADING_ID) != null) {
            showHideViewById(LAYOUT_LOADING_ID);
        }
    }

    /**
     *  显示内容
     */
    public void showContent() {
        if(layoutSparseArray.get(LAYOUT_CONTENT_ID) != null) {
            showHideViewById(LAYOUT_CONTENT_ID);
        }
    }

    /**
     *  显示空数据
     */
    public void showEmptyData() {
        if(inflateLayout(LAYOUT_EMPTYDATA_ID)) {
            showHideViewById(LAYOUT_EMPTYDATA_ID);
        }
    }

    /**
     *  显示网络异常
     */
    public void showNetWorkError() {
        if(inflateLayout(LAYOUT_NETWORK_ERROR_ID)) {
            showHideViewById(LAYOUT_NETWORK_ERROR_ID);
        }
    }

    /**
     *  显示系统升级中
     */
    public void showSystemMaintain() {
        if(inflateLayout(LAYOUT_SYSTEM_MAINTAIN_ID)) {
            showHideViewById(LAYOUT_SYSTEM_MAINTAIN_ID);
        }
    }

    /**
     *  显示系统繁忙
     */
    public void showSystemBusy() {
        if(inflateLayout(LAYOUT_SYSTEM_BUSY_ID)) {
            showHideViewById(LAYOUT_SYSTEM_BUSY_ID);
        }
    }

    /**
     *  显示异常
     */
    public void showError() {
        if(inflateLayout(LAYOUT_ERROR_ID)) {
            showHideViewById(LAYOUT_ERROR_ID);
        }
    }

    /**
     *  根据ID显示隐藏布局
     * @param id
     */
    private void showHideViewById(int id) {
        for(int i = 0; i < layoutSparseArray.size(); i++) {
            int key = layoutSparseArray.keyAt(i);
            View valueView = layoutSparseArray.valueAt(i);
            //显示该view
            if(key == id) {
                valueView.setVisibility(View.VISIBLE);
//                if(mStatesLayoutManager.onShowHideViewListener != null) mStatesLayoutManager.onShowHideViewListener.onShowView(valueView, key);
            } else {
                if(valueView.getVisibility() != View.GONE) {
                    valueView.setVisibility(View.GONE);
//                    if(mStatesLayoutManager.onShowHideViewListener != null) mStatesLayoutManager.onShowHideViewListener.onHideView(valueView, key);
                }
            }
        }
    }

//    private void hideAllView(){
//        for(int i = 0; i < layoutSparseArray.size(); i++) {
//            int key = layoutSparseArray.keyAt(i);
//            View valueView = layoutSparseArray.valueAt(i);
//            valueView.setVisibility(GONE);
//        }
//    }

    private boolean inflateLayout(int id) {
        boolean isShow = true;
        if(layoutSparseArray.get(id) != null) return isShow;
        switch (id) {
            case LAYOUT_NETWORK_ERROR_ID:
                if(mStatesLayoutManager.netWorkErrorVs != null) {
                    View view = mStatesLayoutManager.netWorkErrorVs.inflate();
                    retryLoad(view, mStatesLayoutManager.netWorkErrorRetryViewId);
                    layoutSparseArray.put(id, view);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_ERROR_ID:
                if(mStatesLayoutManager.errorVs != null) {
                    View view = mStatesLayoutManager.errorVs.inflate();
                    retryLoad(view, mStatesLayoutManager.errorRetryViewId);
                    layoutSparseArray.put(id, view);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_EMPTYDATA_ID:
                if(mStatesLayoutManager.emptyDataVs != null) {
                    View view = mStatesLayoutManager.emptyDataVs.inflate();
                    retryLoad(view, mStatesLayoutManager.emptyDataRetryViewId);
                    layoutSparseArray.put(id, view);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_SYSTEM_MAINTAIN_ID:
                if(mStatesLayoutManager.systemMaintainVs != null) {
                    View view = mStatesLayoutManager.systemMaintainVs.inflate();
                    retryLoad(view, mStatesLayoutManager.systemMaintainRetryViewId);
                    layoutSparseArray.put(id, view);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_SYSTEM_BUSY_ID:
                if(mStatesLayoutManager.systemBusyVs != null) {
                    View view = mStatesLayoutManager.systemBusyVs.inflate();
                    retryLoad(view, mStatesLayoutManager.systemBusyRetryViewId);
                    layoutSparseArray.put(id, view);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            default:
                break;
        }
        return isShow;
    }

    public View getSystemMaintainView(){
        return layoutSparseArray.get(LAYOUT_SYSTEM_MAINTAIN_ID);
    }

    /**
     *  重试加载
     */
    public void retryLoad(View view, int id) {
        View retryView = view.findViewById(mStatesLayoutManager.retryViewId != 0 ? mStatesLayoutManager.retryViewId : id);
        if(retryView == null || mStatesLayoutManager.onRetryListener == null) {
            return;
        }
        retryView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mStatesLayoutManager.onRetryListener.onRetry();
            }
        });
    }
}

