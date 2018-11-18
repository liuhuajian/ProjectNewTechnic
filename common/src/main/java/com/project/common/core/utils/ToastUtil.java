package com.project.common.core.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.project.common.R;

/**
 * Toast操作工具类
 *
 * @author com.tentinet.golf.qrcode.MipcaActivityCapture
 * @version 1.0.0
 * @date 2013-03-19
 * @updateTime 2015-9-9,下午2:56:40
 * @updateAuthor 曾招林
 * @updateInfo (解决Toast重复弹出的问题)
 */
public class ToastUtil {

    private static ToastUtil toastUtil;
    Context application;


    private ToastUtil(Context context) {
        application = CheckReferenceUtils.checkReferIsNull(context);
    }

    public static void init(Context context) {
        if (toastUtil == null) {
            synchronized (ToastUtil.class) {
                if (toastUtil == null) {
                    toastUtil = new ToastUtil(context);
                }
            }
        }
    }

    public static ToastUtil getInstance() {
        return CheckReferenceUtils.checkReferIsNull(toastUtil);
    }

    private static Toast toast;

    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            if (toast != null) {
                toast.cancel();
            }
        }
    };

    /**
     * @param mContext
     * @param resId
     * @description<通过id短显示内容>
     * @version 1.0
     * @createTime 2015年9月9日, 下午3:17:38
     * @updateTime 2015年9月9日, 下午3:17:38
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo ()
     */
    public static void showToast(Context mContext, int resId) {
        showShortToast(mContext, mContext.getResources().getString(resId));
    }

    public void showToast(int resId) {
        showShortToast(application, application.getResources().getString(resId));
    }

    public void showToast(String msg) {
        getInstance().showLong(application, msg);
    }

    /**
     * @param mContext
     * @param string
     * @description<通过长显示内容>
     * @version 1.0
     * @createTime 2015年9月9日, 下午3:18:13
     * @updateTime 2015年9月9日, 下午3:18:13
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo ()
     */
    public static void showLongToast(Context mContext, int string) {
        showLong(mContext, mContext.getResources().getString(string));
    }

    /**
     * 显示提示信息
     *
     * @param text 提示内容
     * @author com.tentinet.golf.qrcode.MipcaActivityCapture
     * @version 1.0
     * @date 2013-03-19
     * @updateTime 2015-9-9,下午2:56:40
     * @updateAuthor 曾招林
     * @updateInfo (解决Toast重复弹出的问题-短显示内容)
     */
    public static void showShortToast(Context context, String text) {

        mHandler.removeCallbacks(r);
        if (toast != null)
            toast.setText(text);
        else
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, 1000);

        toast.show();

    }

    /**
     * 显示提示信息(时间较长)
     *
     * @param text 提示内容
     * @author com.tentinet.golf.qrcode.MipcaActivityCapture
     * @version 1.0
     * @date 2013-04-07
     * @updateTime 2015-9-9,下午2:57:40
     * @updateAuthor 曾招林
     * @updateInfo (解决Toast重复弹出的问题-长显示内容)
     */
    public static void showLong(Context context, String text) {
        mHandler.removeCallbacks(r);
        if (toast != null)
            toast.setText(text);
        else
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mHandler.postDelayed(r, 3000);
        toast.show();
    }

    /**
     * 自定义位置的toast
     *
     * @param context   上下文
     * @param text      显示字符
     * @param gravity   显示位置
     * @param alignleft 向右移动量
     * @param alignTop  向下移动量
     * @version 1.0
     * @createTime 2015-6-1,下午5:56:40
     * @updateTime 2015-6-1,下午5:56:40
     * @createAuthor
     * @updateAuthor
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static void showPositionToast(Context context, String text, int gravity, int alignleft, int alignTop) {

        toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        // 放在左上角。如果你想往右边移动，将第二个参数设为>0；往下移动，增大第三个参数；后两个参数都只得像素
        toast.setGravity(gravity, alignleft, alignTop);
        toast.show();
    }


    /**
     * @param context
     * @param text
     * @param
     * @param
     * @param
     * @description<自定义toast显示中间位置并带图标>
     * @version 1.0
     * @createTime 2015年9月28日, 上午9:13:16
     * @updateTime 2015年9月28日, 上午9:13:16
     * @createAuthor julyzeng
     * @updateAuthor
     * @updateInfo ()
     */
    public static void showCenterToast(Context context, String text) {

        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout layout = (LinearLayout) toast.getView();
        ImageView image = new ImageView(context);
		image.setImageResource(R.mipmap.ic_toast_fail);
        layout.addView(image, 0);
        toast.show();
    }


    /**
     * 自定义toastView
     */
    public static void showView(Context context,View view, int position) {
       Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
    }

    public static void cancekToast() {

        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }
}
