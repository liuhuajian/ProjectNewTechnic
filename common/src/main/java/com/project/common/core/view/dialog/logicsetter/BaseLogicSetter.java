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

public class BaseLogicSetter implements CommonFragmentDialog.ILogicSetter {

    private ILogicSetterClickLisenter onConfirmClickListener;
    private ILogicSetterClickLisenter onCancelClickListener;

    private DialogBaseBean mDialogBaseBean;
    //
    private boolean isNotShowCancel;
    private View mContent;

    public BaseLogicSetter() {
        mDialogBaseBean = new DialogBaseBean();
    }

    public BaseLogicSetter(String title) {
        this();
        mDialogBaseBean.setTitle(title);
    }

    public BaseLogicSetter(String title, String comfirText) {
        this(title);
        mDialogBaseBean.setConfirmData(comfirText);
    }

    public BaseLogicSetter(String title, String comfirText, String cancelText) {
        this(title, comfirText);
        mDialogBaseBean.setCancleData(cancelText);
    }

    public BaseLogicSetter(DialogBaseBean dialogBaseBean) {
        this(dialogBaseBean, null);
    }

    public BaseLogicSetter(DialogBaseBean dialogBaseBean, View view) {
        mDialogBaseBean = dialogBaseBean;
        mContent = view;
    }

    @Override
    public BaseLogicSetter setLogic(final CommonFragmentDialog fragmentDialog, View rootView) {
        TextView comfrim = rootView.findViewById(R.id.common_dialog_positive);
        TextView cancle = rootView.findViewById(R.id.common_dialog_negative);
        TextView common_dialog_title = rootView.findViewById(R.id.common_dialog_title);
        FrameLayout contain = rootView.findViewById(R.id.common_content_container);
        if (mDialogBaseBean.getId() != 0) {
            contain.removeAllViews();
            View view = View.inflate(rootView.getContext(), mDialogBaseBean.getId(), null);
            FrameLayout.LayoutParams params =new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            contain.addView(view,params);
            contain.setVisibility(View.VISIBLE);
        }
        if (mContent != null) {
            FrameLayout.LayoutParams params =new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            contain.addView(mContent,params);
            contain.setVisibility(View.VISIBLE);
        }
        common_dialog_title.setText(mDialogBaseBean.getTitle());
        if (!TextUtils.isEmpty(mDialogBaseBean.getConfirmData())) {
            comfrim.setText(mDialogBaseBean.getConfirmData());
        }
        if (!TextUtils.isEmpty(mDialogBaseBean.getCancleData())) {
            cancle.setText(mDialogBaseBean.getCancleData());
        }
        if (isNotShowCancel) {
            cancle.setVisibility(View.GONE);
        }
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onConfirmClickListener != null) {
                    onConfirmClickListener.click();
                }
                fragmentDialog.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelClickListener != null) {
                    onCancelClickListener.click();
                }
                fragmentDialog.dismiss();
            }
        });
        return this;
    }


    public BaseLogicSetter setLogicSetterComfirmLisenter(ILogicSetterClickLisenter onClickListener) {
        if (onClickListener != null) {
            this.onConfirmClickListener = onClickListener;
        }
        return this;
    }

    public BaseLogicSetter setLogicSetterCancelLisenter(ILogicSetterClickLisenter onClickListener) {
        if (onClickListener != null) {
            this.onCancelClickListener = onClickListener;
        }
        return this;
    }

    public BaseLogicSetter setCancelIsShow(boolean isShowCancel) {
        this.isNotShowCancel = isShowCancel;
        return this;
    }

    public interface ILogicSetterClickLisenter {
        void click();
    }
}
