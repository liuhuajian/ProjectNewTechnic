package com.project.common.core.utils;

import android.os.CountDownTimer;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/8/7 14:47
 * @版本 0.1
 * @类说明: 倒计时
 */
public class BaseCountDown extends CountDownTimer {

    public static final long DEFAULT_COUNTDOWN_INTERVAL = 1000;
    public static final long DEFAULT_MILLIS_INFUTURE = 60 * DEFAULT_COUNTDOWN_INTERVAL;

    public BaseCountDown(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public BaseCountDown() {
        this(DEFAULT_MILLIS_INFUTURE);
    }

    public BaseCountDown(long millisInFuture) {
        this(millisInFuture, DEFAULT_COUNTDOWN_INTERVAL);
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }


    @Override
    public void onFinish() {

    }

    public interface OnCountDownListener {
        void surplusTime(long surplusTime);

        void finish();
    }
}
