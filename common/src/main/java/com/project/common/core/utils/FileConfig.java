package com.project.common.core.utils;

import android.os.Environment;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/3/13 9:23
 * 描    述：文件属性类，设置文件保存目录。
 * 修订历史：
 */

public class FileConfig {
    // *************************** 应用使用的文件路径 *****************************//
    // 公用文件路径
    /** 应用根目录 */
    public static final String PATH_BASE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yyh/";
    /** 应用Log日志 */
    public static final String PATH_LOG = PATH_BASE + "Log/";
    /** 临时文件夹 */
    public static final String PATH_HTML = PATH_BASE + "Html/";
    /** 临时文件 */
    public static final String PATH_HTML_TEMP = PATH_BASE + "Html/temp.html";
    /** 下载文件文件夹 */
    public static final String PATH_DOWNLOAD = PATH_BASE + "Download/";
    /** 拍照文件夹 */
    public static final String PATH_CAMERA = PATH_BASE + "Camera/";
    /** 应用基本缓存文件图片路径 */
    public static final String PATH_IMAGES = PATH_BASE + "Image/";
    /** 收藏的图片路径 */
    public static final String PATH_PHOTOS = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/yyh/";
    /** 拍照缓存文件 */
    public static final String PATH_IMAGE_TEMP = PATH_CAMERA + "temp.jpg";

    // 用户私有文件路径
    /** 用户私有缓存文件夹 */
    public static String PATH_USER_FILE = "";
    /** 用户私有图片缓存文件夹 */
    public static String PATH_USER_IMAGE = "";
    /** 用户私有图片缩略图 缓存文件夹 */
    public static String PATH_USER_THUMBNAIL = PATH_CAMERA+"thumb/";
    /** 用户私有录音缓存文件夹 */
    public static String PATH_USER_AUDIO = "";
    /** 用户私有视频缓存文件夹 */
    public static String PATH_USER_VIDEO = "";
    /** 用户私收藏文件夹 */
    public static String PATH_USER_FAVORITES = "";
    /** 上传文件名字 */
    public static String UPLOAD_FILE_NAME = "";



}
