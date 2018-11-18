package com.project.common.core.view.refresh;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.project.common.R;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.MyLogger;

/**
 * @author create by yexm
 * @time 2017/12/21 17:16
 */

@SuppressLint("AppCompatCustomView")
public class FMLRefreshView extends FrameLayout implements PullRefreshLayout.IRefreshView {
    LottieAnimationView lottieAnimationView;
    LottieAnimationView lottieAnimationViewEnd;

    private int time;
    private boolean start;

    public FMLRefreshView(Context context) {
        this(context, null);
    }

    public FMLRefreshView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FMLRefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.fml_refresh_view, this);
        lottieAnimationView = view.findViewById(R.id.animation_view);
        lottieAnimationViewEnd = view.findViewById(R.id.animation_view_end);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void stop() {
        MyLogger.e("stop");
        start = false;
        lottieAnimationViewEnd.setVisibility(VISIBLE);
        lottieAnimationView.setVisibility(GONE);
        lottieAnimationViewEnd.playAnimation();
        lottieAnimationView.cancelAnimation();
    }

    @Override
    public void start() {
    }

    @Override
    public int getStopTime() {
        return 1200;
    }


    @Override
    public void doRefresh() {
        MyLogger.e("doRefresh");
        lottieAnimationView.playAnimation();
        start=false;
    }

    @Override
    public void onPull(int offset, int total, int overPull) {
        if (!start&&!lottieAnimationView.isAnimating()) {
            start = true;
            lottieAnimationViewEnd.setVisibility(GONE);
            lottieAnimationView.setVisibility(VISIBLE);
            lottieAnimationView.cancelAnimation();
        }
        if (lottieAnimationView.isAnimating()) {
            return;
        }
        lottieAnimationView.setProgress(offset * 1.0f / total);
    }
}
