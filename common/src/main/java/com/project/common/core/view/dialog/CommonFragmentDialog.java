package com.project.common.core.view.dialog;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.project.common.R;
import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.view.dialog.data.DialogBaseBean;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/7/26 10:04
 * @版本 0.1
 * @类说明: 公共的dialog
 */
public class CommonFragmentDialog extends DialogFragment {
    private int layoutResource = 0; //自定义资源
    private boolean cancelEnable = false;  //是否可取消
    private ILogicSetter mLogicSetter = null;
    private int animStyleResource = 0;
    private int gravity = Gravity.CENTER;
    private boolean isMatchParent = false;//是否铺满屏幕

    protected View mRootView;

    //装在的内容
    private FrameLayout frameLayoutContainer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);


        if (layoutResource != 0) {
            mRootView = inflater.inflate(layoutResource, container);
        } else {
            mRootView = inflater.inflate(R.layout.view_common_dialog, container);
//            View contentView = View.inflate(getActivity(), layoutResource, null);
//            frameLayoutContainer = mRootView.findViewById(R.id.common_content_container);
//            frameLayoutContainer.removeAllViews();
//            frameLayoutContainer.addView(contentView);
//            frameLayoutContainer.setVisibility(View.VISIBLE);
        }

        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        if (isMatchParent) window.getDecorView().setPadding(0, 0, 0, 0); //设置铺满屏幕
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = gravity;
        if (isMatchParent) attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);
        return mRootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        setCancelable(cancelEnable);
        if (animStyleResource == 0) {
            getDialog().getWindow().setWindowAnimations(R.style.dialog_common_anim);
        } else {
            getDialog().getWindow().setWindowAnimations(animStyleResource);
        }

        if (mLogicSetter != null) {
            mLogicSetter.setLogic(this, view);
        }
    }


    @Override
    public void show(FragmentManager manager, String tag) {

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(this, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    static class DialogBuilder {
        private int layoutResource = 0; //自定义资源
        private boolean cancelEnable = false;  //是否可取消
        private ILogicSetter mLogicSetter = null;
        private int animStyleResource = 0;
        private int gravity = Gravity.CENTER;
        private boolean isMatchParent = false;//是否铺满屏幕


        public DialogBuilder setLayoutResouce(int layoutResource)

        {
            this.layoutResource = layoutResource;
            return this;
        }

        public DialogBuilder setCancelEnable(boolean cancelEnable)

        {
            this.cancelEnable = cancelEnable;
            return this;
        }

        public DialogBuilder setLogicSetter(ILogicSetter mLogicSetter)

        {
            this.mLogicSetter = mLogicSetter;
            return this;
        }

        public DialogBuilder setAnimStyleResource(int animStyleResource)

        {
            this.animStyleResource = animStyleResource;
            return this;
        }

        public DialogBuilder setGravity(int gravity)

        {
            this.gravity = gravity;
            return this;
        }

        public DialogBuilder setIsMatchParent(boolean isMatchParent)

        {
            this.isMatchParent = isMatchParent;
            return this;
        }

        private void applySetting(CommonFragmentDialog fragmentDialog) {
            fragmentDialog.cancelEnable = this.cancelEnable;
            fragmentDialog.mLogicSetter = this.mLogicSetter;
            fragmentDialog.animStyleResource = this.animStyleResource;
            fragmentDialog.gravity = this.gravity;
            fragmentDialog.isMatchParent = this.isMatchParent;
            fragmentDialog.layoutResource = this.layoutResource;
        }

        public CommonFragmentDialog build()

        {
            CommonFragmentDialog commonDialog = new CommonFragmentDialog();
            applySetting(commonDialog);
            return commonDialog;
        }
    }

    public interface ILogicSetter {
        ILogicSetter setLogic(CommonFragmentDialog fragmentDialog, View rootView);
    }
}
