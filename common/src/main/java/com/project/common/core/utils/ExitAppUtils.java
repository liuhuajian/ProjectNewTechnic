package com.project.common.core.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/***
 *
 * @author julyzeng
 *	用来退出app的方法
 */
public class ExitAppUtils {
    private List<Activity> activities = new LinkedList<>();
    private static ExitAppUtils instance;

    private ExitAppUtils() {
    }
    // 单例模式中获取唯一的ExitAPPUtils实例
    public static ExitAppUtils getInstance() {
        if(null == instance) {
            instance =new ExitAppUtils();
        }
        return instance;
    }
    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activities.add(activity);
        MyLogger.i("activity:" + activity.getClass().getSimpleName() + " size:" + activities.size());
        for (Activity aty : activities) {
            MyLogger.i("aty: " + aty.getClass().getSimpleName());
        }
    }

    // 遍历所有Activity并finish
    public void exit() {
        for(Activity activity : activities) {
            activity.finish();
        }
        System.exit(0);
    }

    /**
     *  remove Activity from Stack
     */
    public void removeActivity(Activity activity) {
        MyLogger.i("finishActivity");
        if (activities == null) {
            activities = new Stack<Activity>();
        }
        MyLogger.i("activity:" + activity.getClass().getSimpleName() + " size:" + activities.size());
        activities.remove(activity);
        MyLogger.d("activity stack size:"+activities.size());
        for (Activity aty : activities) {
            MyLogger.i("aty: " + aty.getClass().getSimpleName());
        }
    }

    /**
     * finish activity that in stack
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        Activity currActivity = null;
//        Iterator<Activity> iterator = activities.iterator();
//        while (iterator.hasNext()){
//            Activity activity = iterator.next();
//            if (activity.getClass().equals(cls)){
//                finishActivity(activity);
//            }
//        }
        for (Activity activity : activities) {
            if (activity.getClass().equals(cls)) {
                currActivity = activity;
            }
        }
        if (currActivity!=null)
            finishActivity(currActivity);
    }

    private void finishActivity(Activity activity){
        MyLogger.i("finishActivity");
        if (activity != null) {
            MyLogger.i("activity:" + activity.getClass().getSimpleName() + " size:" + activities.size());
            activities.remove(activity);
            activity.finish();
            MyLogger.i("activity stack size:"+activities.size());
//            for (Activity aty : activities) {
//                MyLogger.i("aty: " + aty.getClass().getSimpleName());
//            }
            activity = null;
        }
    }

    public static void unbindReferences(View view) {
        try {
            if (view != null) {
                view.destroyDrawingCache();
                unbindViewReferences(view);
                if (view instanceof ViewGroup){
                    unbindViewGroupReferences((ViewGroup) view);
                }
            }
        } catch (Throwable e) {

        }
    }

    private static void unbindViewGroupReferences(ViewGroup viewGroup) {
        int nrOfChildren = viewGroup.getChildCount();
        for (int i = 0; i < nrOfChildren; i++) {
            View view = viewGroup.getChildAt(i);
            unbindViewReferences(view);
            if (view instanceof ViewGroup)
                unbindViewGroupReferences((ViewGroup) view);
        }
        try {
            viewGroup.removeAllViews();
        } catch (Throwable mayHappen) {
            // AdapterViews, ListViews and potentially other ViewGroups don't
            // support the removeAllViews operation
        }
    }

    @SuppressWarnings("deprecation")
    private static void unbindViewReferences(View view) {
        // set all listeners to null (not every view and not every API level
        // supports the methods)
        try {
            view.setOnClickListener(null);
            view.setOnCreateContextMenuListener(null);
            view.setOnFocusChangeListener(null);
            view.setOnKeyListener(null);
            view.setOnLongClickListener(null);
            view.setOnClickListener(null);
        } catch (Throwable mayHappen) {
            //todo
        }

        // set background to null
        Drawable d = view.getBackground();
        if (d != null){
            d.setCallback(null);
        }

        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            d = imageView.getDrawable();
            if (d != null){
                d.setCallback(null);
            }
            imageView.setImageDrawable(null);
            imageView.setBackgroundDrawable(null);
        }

        // destroy WebView
        if (view instanceof WebView) {
            WebView webview = (WebView) view;
            webview.stopLoading();
            webview.clearFormData();
            webview.clearDisappearingChildren();
            webview.setWebChromeClient(null);
            webview.setWebViewClient(null);
            webview.destroyDrawingCache();
            webview.destroy();
            webview = null;
        }

        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            try {
                listView.removeAllViewsInLayout();
            } catch (Throwable mayHappen) {
            }
            ((ListView) view).destroyDrawingCache();
        }
    }

}
