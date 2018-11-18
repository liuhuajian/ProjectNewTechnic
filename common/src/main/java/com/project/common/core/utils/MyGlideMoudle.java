package com.project.common.core.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.project.common.R;

import java.io.File;


/**
 * 项目：健康商城
 *
 * @Creator:曾招林julyzeng 2018/6/14 13:58
 * @版本1.0
 * @类说明：全局设置glide的tag
 */

public class MyGlideMoudle implements GlideModule {
    private static final int MEMORY_MAX_SPACE=(int) (Runtime.getRuntime().maxMemory()/8);
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
        //设置加载图片的样式,比默认图片质量好,但占用内存会大点
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        builder.setMemoryCache(new LruResourceCache(MEMORY_MAX_SPACE));
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, getDiskFileString(context,"glideCache"), 20*1024*1024));
    }
    @Override
    public void registerComponents(Context context, Glide glide) {
    }
    private String getDiskFileString(Context mContext,String str) {
        File dirFile=new File(mContext.getCacheDir().getAbsolutePath().toString()+str);
        File tempFile=new File(dirFile,"bitmaps");
        if (! tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdirs();
        }
        return tempFile.getAbsolutePath().toString();
    }
}
