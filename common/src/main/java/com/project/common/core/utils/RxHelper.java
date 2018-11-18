package com.project.common.core.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yexm on 2018/8/7.
 */

public class RxHelper {
    public static Observable<Integer> rxCountDown(Long count) {
        final Long countDown;
        if (count < 0l) {
            count = 0l;
        }
        countDown = count;
        return Observable.interval(0, 1, TimeUnit.SECONDS).
                map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long increaseTime) {
                        return (int) (countDown - increaseTime);
                    }
                }).take(count + 1).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
