package com.project.common.core.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/3/13 12:42
 * 描    述：图片处理工具类
 * 修订历史：
 */

public class ImageUtils {

    /** 图片的最大尺寸，任意边长大于960，都将进行压缩 */
    private static int maxSize = 960;
    /** 缩略图的图片的最大尺寸，所有图片都会生成缩略图 */
    private static final int thumMaxSize = 300;

    /**
     *   文件路径转成bitmap 并压缩
     * @param filePath
     * @return
     */
    public static Bitmap loadBitmap(String filePath) {
        try {

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, 720, maxSize);
            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(filePath, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *   先将图片压缩后再转成二进制流
     * @param bm
     * @return
     */
    public static byte[] bitmapBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 保存图片到本地
     * @param bitmap
     */
    public static void saveImage(Context context,Bitmap bitmap,String fileName){

        try {
            //以图片名称为文件名保存，避免下载重复图片 ,创建目录可在Application
            if(!new File(FileConfig.PATH_DOWNLOAD).exists()){
                FileUtils.mkdirs(FileConfig.PATH_DOWNLOAD);
            }
            File file = new File(FileConfig.PATH_DOWNLOAD + fileName);
            //如果目标文件已经存在，则
            if(file.exists())
            {
                ToastUtil.showLong(context,"图片已存在！");
                return;
            }
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            ToastUtil.showLong(context,"图片已经保存至目录："+ FileConfig.PATH_DOWNLOAD);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 将Bitmap转换成字符串
     *
     * @param bitmap
     * @return
     */
    public static String bitmaptoString(Bitmap bitmap) {
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bStream);
        byte[] bytes = bStream.toByteArray();
        string = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT);
        //去空格去换行
        return string.replaceAll("\n","").replaceAll("\r","");
    }


    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
    /* 压缩一张图片。我们需要知道这张图片的原始大小，然后根据我们设定的压缩比例进行压缩。 */
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
    /*
     * 1、如果图片的原始高度或者宽带大约我们期望的宽带和高度，我们需要计算出缩放比例的数值。否则就不缩放
     * 2、如果使用大的值作位压缩倍数，则压缩出来的图片大小会小于我们设定的值
     * 3、如果使用小的值作位压缩倍数，则压缩出来的图片大小会大于我们设定的值
     */
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }



}
