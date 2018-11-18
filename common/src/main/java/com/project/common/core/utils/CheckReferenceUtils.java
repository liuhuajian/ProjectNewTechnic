package com.project.common.core.utils;

/**
 *项目 国民健康
 * @Create by yexm
 * @创建日期 2018/7/30 14:25
 *
 * @版本 0.1
 * @类说明: 
 */

public class CheckReferenceUtils {
    public static <T> T checkReferIsNull(T obj) {
        if (obj == null) {
            throw new NullPointerException("this obj is null ");
        }
        return obj;
    }
}
