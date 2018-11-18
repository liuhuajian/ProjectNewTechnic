package com.project.common.core.view.uiManage;

import com.project.common.core.base.BaseActivity;
import com.project.common.core.http.exception.ApiErrorCode;
import com.project.common.core.http.exception.NetWorkThrowable;
import com.project.common.core.utils.ToastUtil;
import com.project.common.core.view.dialog.logicsetter.BaseLogicSetter;
import com.project.common.core.view.dialog.DialogHelper;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/7/27 14:32
 * @版本 0.1
 * @类说明:统一错误处理
 */

public class ErrorStateHandler {
    public static void ErrorHander(BaseActivity activity, StatesLayoutManager statesLayoutManager, NetWorkThrowable httpError) {
//        statesLayoutManager.showNetWorkError();
        switch (httpError.status) {
            case ApiErrorCode.ERROR_NO_INTERNET:
                DialogHelper.showCommonDialog(new BaseLogicSetter("网络连接失败，请检查网络后重试！","好").setCancelIsShow(true)).show(activity.getFragmentManager(), "error_net");
                break;

            default:
                ToastUtil.getInstance().showToast(httpError.getMessage());

        }
    }
}
