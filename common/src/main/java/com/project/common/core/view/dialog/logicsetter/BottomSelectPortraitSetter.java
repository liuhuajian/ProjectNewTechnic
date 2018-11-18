package com.project.common.core.view.dialog.logicsetter;

import android.view.View;
import android.widget.RadioButton;
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
 * @类说明: 选择头像
 */

public class BottomSelectPortraitSetter implements ILogicSetter {
    private ILogicSetterClickLisenter onTakePhotoClickListener;
    private ILogicSetterClickLisenter onSelectPhotoClickListener;

    public BottomSelectPortraitSetter() {

    }

    @Override
    public ILogicSetter setLogic(final CommonFragmentDialog fragmentDialog, View rootView) {


        TextView tv_select_photo = rootView.findViewById(R.id.tv_select_photo);
        TextView tv_take_photo = rootView.findViewById(R.id.tv_take_photo);
        TextView btn_cancel = rootView.findViewById(R.id.btn_cancel);
        tv_select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTakePhotoClickListener!=null){
                    onTakePhotoClickListener.click();
                }
                fragmentDialog.dismiss();
            }
        });
        tv_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSelectPhotoClickListener!=null){
                    onSelectPhotoClickListener.click();
                }
                fragmentDialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentDialog.dismiss();
            }
        });
        return this;
    }

    public BottomSelectPortraitSetter setTakePhotoLisenter(ILogicSetterClickLisenter onClickListener) {
        if (onClickListener != null) {
            this.onTakePhotoClickListener = onClickListener;
        }
        return this;
    }
    public BottomSelectPortraitSetter setSelectPhotoLisenter(ILogicSetterClickLisenter onClickListener) {
        if (onClickListener != null) {
            this.onSelectPhotoClickListener = onClickListener;
        }
        return this;
    }

    public interface ILogicSetterClickLisenter {
        void click();
    }

}
