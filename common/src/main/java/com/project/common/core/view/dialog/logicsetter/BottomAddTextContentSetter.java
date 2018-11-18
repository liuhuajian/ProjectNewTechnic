package com.project.common.core.view.dialog.logicsetter;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.common.R;
import com.project.common.core.http.bean.BaseTagBean;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.ToastUtil;
import com.project.common.core.view.dialog.CommonFragmentDialog;
import com.project.common.core.view.dialog.CommonFragmentDialog.ILogicSetter;

import java.util.List;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/8/23 17:04
 * @版本 0.2
 * @类说明: 选择标签
 */

public class BottomAddTextContentSetter implements ILogicSetter {
    private ILogicSetterClickLisenter onConfirmClickListener;

    private String mTitle;
    private String mContent;

    public BottomAddTextContentSetter() {
        this("");
    }

    public BottomAddTextContentSetter(String title) {
        mTitle = title;
    }

    public BottomAddTextContentSetter(String title, String content) {
        mTitle = title;
        mContent = content;
    }

    @Override
    public ILogicSetter setLogic(final CommonFragmentDialog fragmentDialog, View rootView) {
        TextView title = rootView.findViewById(R.id.tv_tittle);
        final TextView tv_length_rate = rootView.findViewById(R.id.tv_length_rate);
        ImageView iv_close = rootView.findViewById(R.id.iv_close);
        final EditText et_content = rootView.findViewById(R.id.et_content);
        TextView tv_comfirm = rootView.findViewById(R.id.tv_comfirm);
        title.setText(mTitle);
        et_content.setText(mContent);
        tv_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onConfirmClickListener != null) {
                    mContent = et_content.getText().toString();
                    onConfirmClickListener.click(mContent);
                }
                fragmentDialog.dismiss();
            }
        });
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    tv_length_rate.setText(s.length() + "/100");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    public BottomAddTextContentSetter setILogicSetterClickLisenter(ILogicSetterClickLisenter lisenter) {
        onConfirmClickListener = lisenter;
        return this;
    }
}
