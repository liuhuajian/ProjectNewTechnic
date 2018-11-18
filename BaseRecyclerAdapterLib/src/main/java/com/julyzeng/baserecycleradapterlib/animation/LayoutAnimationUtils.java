package com.julyzeng.baserecycleradapterlib.animation;

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/6/8 14:12
 * 描    述：
 * 修订历史：
 */

public class LayoutAnimationUtils {

    public static LayoutAnimationController getLayoutAnimation(Context context,int id){
        Animation animation = AnimationUtils.loadAnimation(context, id);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setInterpolator(new AccelerateInterpolator());
        layoutAnimationController.setDelay(0.6f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_RANDOM);
        return layoutAnimationController;
    }
}
