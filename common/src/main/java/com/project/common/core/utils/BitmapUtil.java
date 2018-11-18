package com.project.common.core.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;

import com.project.common.core.BaseApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/4/7 10:19
 * 描    述：Bitmap工具类，主要是解决拍照旋转的适配
 * 修订历史：
 */

public class BitmapUtil {
    private BitmapUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取图片的旋转角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照指定的角度进行旋转
     *
     * @param bitmap 需要旋转的图片
     * @param degree 指定的旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bitmap, int degree) {
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return newBitmap;
    }

    /**
     * 获取我们需要的整理过旋转角度的Uri
     * @param activity  上下文环境
     * @param path      路径
     * @return          正常的Uri
     */
    public static Uri getRotatedUri(Activity activity, String path){
        int degree = BitmapUtil.getBitmapDegree(path);
        if (degree != 0){
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            Bitmap newBitmap = BitmapUtil.rotateBitmapByDegree(bitmap,degree);
            return Uri.parse(MediaStore.Images.Media.insertImage(activity.getContentResolver(),newBitmap,null,null));
        }else{
            return Uri.fromFile(new File(path));
        }
    }

    /**
     * 将图片按照指定的角度进行旋转
     *
     * @param path   需要旋转的图片的路径
     * @param degree 指定的旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(String path, int degree) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return rotateBitmapByDegree(bitmap,degree);
    }

    public static Bitmap getBitmap(Context context, Uri uri) {

        ContentResolver resolver = context.getContentResolver();

        Bitmap image = null;
        try {

            InputStream is = resolver.openInputStream(Uri.parse(uri.toString()));
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    /**
     * 获取截图
     * @param headerView 这里传入的是你要截的布局的根View
     * @return 将布局转化为bitmap
     */
    public static Bitmap getBitmapByView(View headerView) {
        int h = headerView.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(headerView.getWidth(), h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        headerView.draw(canvas);
        return bitmap;
    }

    /*
     * 把bitmap转化为file
     * */
    public static File bitMap2File(Bitmap bitmap) {

        String path = "";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory() + File.separator;//保存到sd根目录下
        }
        File f = new File(path, System.currentTimeMillis()+"share.jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            bitmap.recycle();

            try {
                MediaStore.Images.Media.insertImage(BaseApp.mContext.getContentResolver(), f.getAbsolutePath(), f.getName(), null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(f);
            BaseApp.mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return f;
        }
    }

    /**
     * drawable 转化成bitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }
}
