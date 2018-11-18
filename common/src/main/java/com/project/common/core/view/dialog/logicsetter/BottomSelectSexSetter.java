package com.project.common.core.view.dialog.logicsetter;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.common.R;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.MyLogger;
import com.project.common.core.view.dialog.CommonFragmentDialog;
import com.project.common.core.view.dialog.CommonFragmentDialog.ILogicSetter;

import java.util.List;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/8/23 17:04
 * @版本 0.2
 * @类说明:选项性别
 */

public class BottomSelectSexSetter implements ILogicSetter {
    private ILogicSetterClickLisenter onConfirmClickListener;

    private String mTitle;
    private String mCurrent;

    public BottomSelectSexSetter(String title) {
        this.mTitle = title;
    }

    public BottomSelectSexSetter(String title, String current) {
        this.mTitle = title;
        if (TextUtils.isEmpty(current)) {
            return;
        }
        mCurrent = current;
    }

    @Override
    public ILogicSetter setLogic(final CommonFragmentDialog fragmentDialog, View rootView) {

        TextView title = rootView.findViewById(R.id.tv_title);
        final LinearLayout male = rootView.findViewById(R.id.ll_male);
        final LinearLayout famale = rootView.findViewById(R.id.ll_famale);
        title.setText(mTitle);
        if (!TextUtils.isEmpty(mCurrent)) {
            if (mCurrent.equals("1")) {
                male.setSelected(true);
                famale.setSelected(false);
            } else if (mCurrent.equals("2")) {
                male.setSelected(false);
                famale.setSelected(true);
            }
        }
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                famale.setSelected(false);
                if (onConfirmClickListener != null) {
                    onConfirmClickListener.click("1");

                }
                fragmentDialog.dismiss();
            }
        });
        famale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setSelected(false);
                if (onConfirmClickListener != null) {
                    onConfirmClickListener.click("2");
                }
                fragmentDialog.dismiss();
            }
        });
        return this;
    }

    public BottomSelectSexSetter setLogicSetterComfirmLisenter(ILogicSetterClickLisenter onClickListener) {
        if (onClickListener != null) {
            this.onConfirmClickListener = onClickListener;
        }
        return this;
    }


    public interface ILogicSetterClickLisenter {
        void click(String sex);
    }

}
