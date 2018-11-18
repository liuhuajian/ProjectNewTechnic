package com.project.common.core.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;

import com.project.common.R;
import com.project.common.core.utils.MyLogger;

/**
 * Created by Administrator on 2017/8/21.
 */

public class CustomerTextView extends AppCompatTextView {

    private Drawable drawable;
    private int degree;
    private float transX;
    private float transY;
    private boolean banTouchAnim;
    private Animator anim1,anim2;
    private Handler mHandler = new Handler();

    public CustomerTextView(Context context) {
        super(context);
    }

    private void initView(Context context) {
        PropertyValuesHolder valueHolder1 = PropertyValuesHolder.ofFloat(
                "scaleX", 1f, 0.9f);
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat(
                "scaleY", 1f, 0.9f);
        anim1 = ObjectAnimator.ofPropertyValuesHolder(this, valueHolder1,
                valuesHolder2);
        anim1.setDuration(100);
        anim1.setInterpolator(new LinearInterpolator());

        PropertyValuesHolder valueHolder3 = PropertyValuesHolder.ofFloat(
                "scaleX", 0.9f, 1f);
        PropertyValuesHolder valuesHolder4 = PropertyValuesHolder.ofFloat(
                "scaleY", 0.9f, 1f);
        anim2 = ObjectAnimator.ofPropertyValuesHolder(this, valueHolder3,
                valuesHolder4);
        anim2.setDuration(100);
        anim2.setInterpolator(new LinearInterpolator());
    }

    public CustomerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        drawable = this.getBackground();
        init(context ,attrs);
        initView(context);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomerTextView);
        degree = array.getInt(R.styleable.CustomerTextView_degree, 0);
        transX = array.getFloat(R.styleable.CustomerTextView_transX, 0);
        transY = array.getFloat(R.styleable.CustomerTextView_transY, 0);
        banTouchAnim = array.getBoolean(R.styleable.CustomerTextView_banTouch,false);
    }

    public void setBanTouchAnim(boolean banTouchn){
        this.banTouchAnim = banTouchn;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                MyLogger.i("ACTION_DOWN--->"+ banTouchAnim);
                if (drawable!=null && !banTouchAnim) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    drawable.setAlpha(110);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            anim2.end();
                            anim1.start();
                        }
                    });
                }
                break;
            case MotionEvent.ACTION_UP:
                if (drawable!=null &&!banTouchAnim) {
                    drawable.setAlpha(255);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            anim1.end();
                            anim2.start();
                        }
                    });
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
