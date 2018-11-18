package com.project.common.core.view.dialog.logicsetter;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.view.dialog.CommonFragmentDialog;
import com.project.common.core.view.dialog.data.DialogBaseBean;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/7/26 11:38
 * @版本 0.1
 * @类说明: 基础的setter
 */

public class NetDialogLogicSetter implements CommonFragmentDialog.ILogicSetter {
    private String descript;
    private View dialogView;

    public NetDialogLogicSetter(String descript) {
        this.descript = descript;
    }

    public NetDialogLogicSetter(View view) {
        dialogView = view;
    }

    public NetDialogLogicSetter() {
        this("");
    }

    @Override
    public CommonFragmentDialog.ILogicSetter setLogic(CommonFragmentDialog fragmentDialog, View rootView) {
        if (dialogView != null) {
            FrameLayout fl_contain = rootView.findViewById(R.id.fl_contain);
            fl_contain.removeAllViews();
            fl_contain.addView(dialogView);
        } else {
            TextView textView = rootView.findViewById(R.id.tv_descript);
            textView.setText(descript);
        }
        return this;
    }
}
