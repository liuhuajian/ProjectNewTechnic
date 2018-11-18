package com.project.common.core.view.dialog.logicsetter;

import android.text.Editable;
import android.text.TextWatcher;
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
 * @类说明: 选择标签
 */

public class CenterTextContentSetter implements ILogicSetter {
    private ILogicSetterClickLisenter onConfirmClickListener;

    private String mTitle;
    private String mContent;

    public CenterTextContentSetter() {
        this("");
    }

    public CenterTextContentSetter(String title) {
        mTitle = title;
    }

    public CenterTextContentSetter(String title, String content) {
        mTitle = title;
        mContent = content;
    }

    @Override
    public ILogicSetter setLogic(final CommonFragmentDialog fragmentDialog, View rootView) {
        TextView title = rootView.findViewById(R.id.tv_tittle);
        final TextView tv_content = rootView.findViewById(R.id.tv_content);
        tv_content.setText(mContent);
        ImageView iv_close = rootView.findViewById(R.id.iv_close);
        title.setText(mTitle);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentDialog.dismiss();
            }
        });
        return this;
    }

    public interface ILogicSetterClickLisenter {
        void click(String content);
    }

    public CenterTextContentSetter setILogicSetterClickLisenter(ILogicSetterClickLisenter lisenter) {
        onConfirmClickListener = lisenter;
        return this;
    }
}
