package com.julyzeng.baserecycleradapterlib.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * 底部进入加旋转加透明动画。
 */
public class BottomInRorateAnimation implements BaseAnimation {

    @Override
    public Animator[] getAnimators(View view) {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", view.getRootView().getHeight()+200, 0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1f);
        ObjectAnimator ratation = ObjectAnimator.ofFloat(view, "rotationX", 75,30, 0f);
        return new Animator[]{
                translationY, alpha,ratation
        };
    }
}
