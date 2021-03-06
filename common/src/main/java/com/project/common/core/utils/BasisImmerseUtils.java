package com.project.common.core.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BasisImmerseUtils {


    /**
     * 设置沉浸式属性
     *
     * @param view 为实现沉浸式的布局, 需添加属性: android:fitsSystemWindows="true"
     */
    public static void setImmerseLayout(Activity activity, View view) {
        //沉浸式状态栏为Android4.4后特性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏属性
            setPaddingTop(activity, view);
        }
    }

    /**
     * 将布局的PaddingTop增加系统栏的高度
     */
    public static void setPaddingTop(Activity activity, View view) {
        int statusBarHeight = getStatusBarHeight(activity);//获取系统状态栏高度
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + statusBarHeight, view.getPaddingRight(), view.getPaddingBottom());//设置view的paddingTop高度, 防止显示错乱
    }

    /**
     * 设置沉浸式属性
     */
    public static void setImmerseLayout(Activity activity) {
        //沉浸式状态栏为Android4.4后特性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏属性
        }
    }

    /**
     * 取消沉浸式属性
     *
     * @param view 为实现沉浸式的布局, 需添加属性: android:fitsSystemWindows="true"
     */
    public static void clearImmerseLayout(Activity activity, View view) {
        //沉浸式状态栏为Android4.4后特性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStatusBarHeight(activity);//获取系统状态栏高度
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() - statusBarHeight, view.getPaddingRight(), view.getPaddingBottom());//设置view的paddingTop高度, 防止显示错乱
        }
    }

    /**
     * 取消沉浸式属性
     */
    public static void clearImmerseLayout(Activity activity) {
        //沉浸式状态栏为Android4.4后特性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 获取系统状态栏高度
     *
     * @return pixel
     */
    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 设置顶部状态栏和底部导航栏透明, 并使主题布局占据顶部和底部位置
     * <p>
     * 效果: 顶部状态栏和底部导航栏悬空在界面上
     */
    public static void setTransparentWindowBar(Activity activity) {
        //支持Android5.0以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 隐藏顶部状态栏
     */
    public static void hideStatusBar(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * 隐藏底部导航栏
     */
    public static void hideNavigationBar(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /**
     * 隐藏底部导航栏和顶部状态栏(点击任意位置效果消失)
     */
    public static void hideStatusBarAndNavigationBar(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /**
     * 设置完全沉浸式效果(游戏和视频播放需求)
     * <p>
     * 需要在onWindowFocusChanged方法中调用
     */
    public static void setOnWindowFocusChanged(Activity activity, boolean hasFocus) {
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

}
