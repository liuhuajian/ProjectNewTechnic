package com.project.common.core.view.dialog.logicsetter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.view.dialog.CommonFragmentDialog;
import com.project.common.core.view.dialog.CommonFragmentDialog.ILogicSetter;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/8/23 17:04
 * @版本 0.2
 * @类说明: 选择地址
 */

public class BottomSelectAddressSetter implements ILogicSetter {
    private ILogicSetterClickLisenter onConfirmClickListener;

    private String mTitle;
    private String mContent;

    public BottomSelectAddressSetter() {
        this("");
    }

    public BottomSelectAddressSetter(String title) {
        mTitle = title;
    }


    @Override
    public ILogicSetter setLogic(final CommonFragmentDialog fragmentDialog, View rootView) {

        return this;
    }

    public interface ILogicSetterClickLisenter {
        void click(String content);
    }

    public BottomSelectAddressSetter setILogicSetterClickLisenter(ILogicSetterClickLisenter lisenter) {
        onConfirmClickListener = lisenter;
        return this;
    }
}
