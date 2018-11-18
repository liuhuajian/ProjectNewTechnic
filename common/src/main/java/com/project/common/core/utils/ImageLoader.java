package com.project.common.core.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.project.common.R;

import javax.security.auth.callback.Callback;

/**
 * Created by julyzeng on 2017/2/28.
 */
public class ImageLoader {

    //默认加载图片方法
    public static void loadImageView(Context mContext, String path, ImageView mImageView,boolean isCache){

        Glide.with(mContext).load(path)
                .placeholder(R.drawable.bg_image_default)
                .skipMemoryCache(isCache)//设置跳过内存缓存true
                .fitCenter()
                .error(R.drawable.bg_image_default).into(mImageView);
    }


    //加载图片完成监听方法
    public static void loadImageViewProgress(Context mContext, String path, ImageView mImageView, final ProgressBar dialog,int width,int height){

        Glide.with(mContext).load(path).fitCenter()
                .placeholder(R.drawable.bg_image_default).error(R.drawable.bg_image_default).dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(width,height)
                .into(new GlideDrawableImageViewTarget(mImageView) {
            @Override
            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);
                //在这里添加一些图片加载完成的操作
                dialog.setVisibility(View.GONE);
            }
            });
    }
    //加载圆形图片方法
    public static void loadCircleImageView(Activity mContext, String path, ImageView mImageView){

        if (mContext !=null && mImageView!=null && !mContext.isFinishing()) {
            Glide.with(mContext).load(path).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .dontAnimate().bitmapTransform(new GlideCircleTransform(mContext)).into(mImageView);
        }
    }

    //加载圆角图片方法
    //加载圆角图片方法
    public static void loadRoundImageView(Activity mContext, String path, ImageView mImageView,int round){

        if (mContext !=null && mImageView!=null && !mContext.isFinishing()) {
            Glide.with(mContext).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.bg_image_default).bitmapTransform(new GlideRoundTransform(mContext,round)).into(mImageView);
        }
    }

    //加载指定大小
    public static void loadImageView(Context mContext, String path, int w, int h, ImageView mImageView) {
        Glide.with(mContext.getApplicationContext()).load(path).centerCrop().dontAnimate().skipMemoryCache(false).override(w, h).into(mImageView);
    }
    //加载指定16x9大小默认图长宽比。
    public static void loadImageViewWH(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().placeholder(R.drawable.bg_image_default).override(width, height).into(mImageView);
    }
    //加载指定250大小
    public static void loadImageView250(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().placeholder(R.drawable.bg_image_default).override(width, height).into(mImageView);
    }
    public static void loadImageView250(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().placeholder(R.drawable.bg_image_default).into(mImageView);
    }
    public static void loadImageView2(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().override(width, height).into(mImageView);
    }
    public static void loadImageWithCallBack(Context mContext, String path, GlideDrawableImageViewTarget target) {
        Glide.with(mContext).load(path).placeholder(R.drawable.bg_image_default)
                .skipMemoryCache(false)//设置跳过内存缓存true
                .fitCenter()
                .error(R.drawable.bg_image_default).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(target);
    }
    //设置下载优先级
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).priority(Priority.NORMAL).dontAnimate().placeholder(R.drawable.bg_image_default)
                .error(R.drawable.bg_image_default).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(mImageView);
    }
    //不设置居中裁剪
    public static void loadImageViewNoScaleType(Context mContext, String path,  ImageView mImageView) {
        Glide.with(mContext).load(path).priority(Priority.NORMAL).placeholder(R.drawable.bg_image_default).dontAnimate().into(mImageView);
    }
    //设置下载优先级
    public static void loadImageView(Context mContext, @DrawableRes int id, ImageView mImageView) {
        Glide.with(mContext).load(id).priority(Priority.NORMAL).dontAnimate().placeholder(R.drawable.bg_image_default)
                .error(R.drawable.bg_image_default).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(mImageView);
    }

    public static void loadImageView(Context mContext, String path, ImageView mImageView,int w,int h) {

        Glide.with(mContext.getApplicationContext()).load(path).fitCenter().dontAnimate().skipMemoryCache(false).override(w, h).into(mImageView);
    }

   /**
    * 设置缓存策略
    策略分为：
    all:缓存源资源和转换后的资源
    none:不作任何磁盘缓存
    source:缓存源资源
    result：缓存转换后的资源
    **/
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }

    /**设置加载动画

    api也提供了几个常用的动画：比如crossFade()
     */
    public static void loadImageViewAnim(Context mContext, String path, int anim, ImageView mImageView) {
        Glide.with(mContext).load(path).animate(anim).into(mImageView);
    }


    //设置加载缩略图
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.3f).into(mImageView);
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
    public static void loadImageViewContent(Context mContext, String path, SimpleTarget<GlideDrawable> simpleTarget) {
        Glide.with(mContext).load(path).centerCrop().into(simpleTarget);
    }

    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }


}
