package com.project.common.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 项目：
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：
 * 描    述：标题透明渐变scrollview
 * 修订历史：
 */

public class TitleAlphaScrollView extends ScrollView {

    private int postionY = 0;
    private OnScrollChangedListener onScrollChangedListener;

    public TitleAlphaScrollView(Context context) {
        this(context, null);
    }

    public TitleAlphaScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleAlphaScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldx, int oldy) {
        super.onScrollChanged(l, t, oldx, oldy);

        if (onScrollChangedListener != null) {
            if (oldy < t) {// 手指向上滑动，屏幕内容下滑
                onScrollChangedListener.onScrollChanged(oldy, t, oldx, oldy, false);
            } else if (oldy > t) {// 手指向下滑动，屏幕内容上滑
                onScrollChangedListener.onScrollChanged(oldy, t, oldx, oldy, true);
            }
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (onScrollChangedListener != null) {

            onScrollChangedListener.onScroll(scrollX,scrollY);
        }

    }

    /**
     * 拦截事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    public interface OnScrollChangedListener {

        void onScrollChanged(int l, int t, int oldl, int oldt, boolean isUp);

        void onScroll(int sx,int sy);
    }

    /**
     * 滑动监听，对外提供的方法
     *
     * @param onScrollChangedListener
     */
    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }
}
