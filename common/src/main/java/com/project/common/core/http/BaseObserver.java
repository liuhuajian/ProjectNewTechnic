package com.project.common.core.http;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.project.common.R;
import com.project.common.core.BaseApp;
import com.project.common.core.base.BaseActivity;
import com.project.common.core.base.BaseView;
import com.project.common.core.base.UserInfo;
import com.project.common.core.http.bean.JsonResult;
import com.project.common.core.http.constant.HttpErrorCode;
import com.project.common.core.http.exception.APIException;
import com.project.common.core.http.exception.ApiErrorCode;
import com.project.common.core.http.exception.NetWorkThrowable;
import com.project.common.core.utils.CheckReferenceUtils;
import com.project.common.core.utils.MyLogger;
import com.project.common.core.utils.ToastUtil;
import com.project.common.core.utils.UserInfoHelper;
import com.project.common.core.view.CustomAlertDialog;
import com.project.common.core.view.uiManage.ErrorStateHandler;

import org.apache.http.conn.ConnectTimeoutException;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2018/6/10 6:18
 * 描    述：观察者
 * 修订历史：叶贤明  解耦，同一处理请求错误情况。添加dialog// 暂时不添加2.0样式工厂
 */

public class BaseObserver<T> implements Observer<JsonResult<T>> {

    private SoftReference<BaseActivity> mContext;
    private ICallBack<T> mCallBack;
    //网络加载的dialog 点击是否可以取消
    private Boolean isCancle;
    //是否显示dialog
    private boolean isShowDialog;
    private AlertDialog dialog;
    //抽象view
    private SoftReference<BaseView> baseView;

    public BaseObserver(BaseView view, ICallBack<T> callBack) {
        this(callBack, view, true);
    }

    public BaseObserver(ICallBack<T> callBack, BaseView view, boolean isShowDialog) {
        this(callBack, view, isShowDialog, false);
    }

    public BaseObserver(ICallBack<T> callBack, BaseView view, boolean isShowDialog, boolean isCancle) {
        this.isShowDialog = isShowDialog;
        this.isCancle = isCancle;
        Context context = null;
        baseView = new SoftReference(view);
        if (view instanceof Activity) {
            context = (Context) view;
        } else if (view instanceof Fragment) {
            context = ((Fragment) view).getActivity();
        } else {
            throw new ClassCastException("this view mast is BaseActivity child");
        }
        mContext = new SoftReference(context);
        mCallBack = CheckReferenceUtils.checkReferIsNull(callBack);
        if (mContext.get() != null) {
            View dialogView = View.inflate(mContext.get(), R.layout.http_dialog, null);
            dialog = new AlertDialog.Builder(mContext.get(), R.style.progressDialog).setView(dialogView).setCancelable(this.isCancle).create();
        }
    }

    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    public boolean checkNet(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对像
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info == null || !info.isAvailable()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

        if (!d.isDisposed() && mContext.get() != null) {
            if (!checkNet(mContext.get())) {
                onError(new NetWorkThrowable(ApiErrorCode.ERROR_NO_INTERNET, "网络连接异常"));
                d.dispose();
                return;
            }
            if (!mContext.get().isFinishing())
            showDialog();
        }
    }

    void showDialog() {
        if (dialog != null && isShowDialog && !dialog.isShowing()) {
            dialog.show();
            WindowManager windowManager = mContext.get().getWindowManager();
            Display defaultDisplay = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
            p.width = (int) (defaultDisplay.getWidth() * 0.5);    //宽度设置为屏幕的0.5
            dialog.getWindow().setAttributes(p);
        }
    }

    void hiddenDialog() {
        if (isShowDialog && dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onNext(@NonNull JsonResult<T> t) {

        JsonResult<T> basicBean = (JsonResult) t;
        if (basicBean.getData() instanceof UserInfo) {
            UserInfoHelper.userInfo = (UserInfo) basicBean.getData();
        }
        if (HttpErrorCode.SUCCESS.equals(basicBean.getCode())) {
            if (baseView.get().isShowErrorLayout() && baseView != null) {
                baseView.get().getStatesLayoutManager().showContent();
            }
            mCallBack.onNext(basicBean.getData());
            hiddenDialog();
        } else {
            onError(new APIException(basicBean.getCode(), basicBean.getMessage()));
        }

    }


    @Override
    public void onError(@NonNull Throwable e) {
        MyLogger.e("excep==="+e.getMessage());
        if (e instanceof NetWorkThrowable) {
            ErrorStateHandler.ErrorHander(mContext.get(),baseView.get().getStatesLayoutManager(),(NetWorkThrowable)e);
        } else if (e instanceof ConnectTimeoutException) {
            ToastUtil.getInstance().showToast("连接超时");
        } else if (e instanceof ConnectException) {
            ToastUtil.getInstance().showToast("连接服务器请求异常");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.getInstance().showToast("连接服务器超时，服务器小哥哥正在努力加油~");//服务器重启或者崩溃时
        } else if (e instanceof HttpException) {
            ToastUtil.getInstance().showToast("服务器小哥哥正在努力加油~");//一般是服务出错或参数错误
        } else if (e instanceof APIException) {
            ToastUtil.getInstance().showToast(e.getMessage());
        } else {
            ToastUtil.getInstance().showToast(e.getMessage());
        }
        mCallBack.onError(e);
        hiddenDialog();
    }

    @Override
    public void onComplete() {
        mCallBack.onCompleted();
        hiddenDialog();
    }
}
