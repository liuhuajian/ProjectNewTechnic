package com.project.common.core.base;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by julyzeng on 2017/2/15.
 * 防止持有外部Activity引用造成内存泄露。
 */
public abstract class WeakReferenceHandler extends Handler {

    private WeakReference<Activity> mReference;

    public WeakReferenceHandler(Activity reference) {
        mReference = new WeakReference<>(reference);
    }
    @Override
    public void handleMessage(Message msg) {
        if (mReference.get() == null) return;
        handleMessage(mReference.get(), msg);
    }

    protected abstract void handleMessage(Activity reference, Message msg);
}

