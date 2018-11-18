package com.project.common.core.view.dialog;

import android.view.Gravity;

import com.project.common.core.http.bean.JsonResult;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/7/26 10:45
 * @版本 0.1
 * @类说明: dialog生产工厂
 */

public class DialogFactory<H extends CommonFragmentDialog.ILogicSetter,T> {
    private boolean isCancelEnable;
    private int gravity;
    private int layoutResource;
    private H mLogsicSetter;
    private int animStyleResource;
    private boolean isMatchParent;

    public DialogFactory() {
        this(null, 0);
    }
    public DialogFactory(H mLogsicSetter) {
        this(mLogsicSetter, 0);
    }
    public DialogFactory(T t) {
        this(null, 0, t);
    }

    public DialogFactory(H mLogsicSetter, int layoutResource) {
        this(mLogsicSetter, layoutResource, true);
    }

    public DialogFactory(H mLogsicSetter, int layoutResource, T t) {
        this(mLogsicSetter, layoutResource, true, t);
    }

    public DialogFactory(H mLogsicSetter, int layoutResource, boolean isCancelEnable) {
        this(mLogsicSetter, layoutResource, isCancelEnable, Gravity.CENTER);
    }

    public DialogFactory(H mLogsicSetter, int layoutResource, boolean isCancelEnable, T t) {
        this(mLogsicSetter, layoutResource, isCancelEnable, Gravity.CENTER, t);
    }

    public DialogFactory(H mLogsicSetter, int layoutResource, boolean isCancelEnable, int gravity) {
        this(mLogsicSetter, layoutResource, isCancelEnable, gravity, false);
    }

    public DialogFactory(H mLogsicSetter, int layoutResource, boolean isCancelEnable, int gravity, T t) {
        this(mLogsicSetter, layoutResource, isCancelEnable, gravity, false, t);
    }

    public DialogFactory(H mLogsicSetter, int layoutResource, boolean isCancelEnable, int gravity, boolean isMatchParent) {
        this(mLogsicSetter, layoutResource, isCancelEnable, gravity, isMatchParent, 0);

    }

    public DialogFactory(H mLogsicSetter, int layoutResource, boolean isCancelEnable, int gravity, boolean isMatchParent, T t) {
        this(mLogsicSetter, layoutResource, isCancelEnable,gravity, isMatchParent, 0, t);

    }

    public DialogFactory(H mLogsicSetter, int layoutResource, boolean isCancelEnable, int gravity, boolean isMatchParent, int animStyleResource) {
        this(mLogsicSetter, layoutResource, isCancelEnable, gravity, isMatchParent, animStyleResource, null);
    }

    public DialogFactory(H mLogsicSetter, int layoutResource, boolean isCancelEnable, int gravity, boolean isMatchParent, int animStyleResource, T t) {
        this.isCancelEnable = isCancelEnable;
        this.gravity = gravity;
        this.layoutResource = layoutResource;
        this.isMatchParent = isMatchParent;
        this.animStyleResource = animStyleResource;
        this.mLogsicSetter = mLogsicSetter;
    }
    public CommonFragmentDialog createDialog() {
        return new CommonFragmentDialog.DialogBuilder()
                .setGravity(gravity)
                .setCancelEnable(isCancelEnable)
                .setLogicSetter(mLogsicSetter)
                .setAnimStyleResource(animStyleResource)
                .setLayoutResouce(layoutResource)
                .setIsMatchParent(isMatchParent).build();
    }
}
