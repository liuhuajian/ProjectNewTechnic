package com.project.common.core.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2018/6/10  9:27
 * 描    述： 日志管理类
 * 修订历史：
 */

public class NotNull {

    public static boolean isNotNull(Integer i) {
        if (null != i && 0 != i) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Double d) {
        if (null != d && 0 != d) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object object) {
        if (null != object && !"".equals(object)) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(List<?> t) {
        if (null != t && t.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Map<?, ?> t) {
        if (null != t && t.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object[] objects) {
        if (null != objects && objects.length > 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(JSONArray jsonArray) {
        if (null != jsonArray && jsonArray.length() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(JSONObject jsonObject) {
        if (null != jsonObject && !"".equals(jsonObject)) {
            return true;
        }
        return false;
    }

    public static boolean isNotNullAndNaN(Object object) {
        if (isNotNull(object) && !object.toString().equals("NaN")) {
            return true;
        }
        return false;
    }

    /**
     *
     * 方法描述<判断多个字符串是否有空值>
     * @version 1.0
     * @createTime 2015年11月6日,上午10:06:00
     * @updateTime
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param strings
     * @return
     */
    public static boolean isNotNull(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (null == strings[i] || "".equals(strings[i])) {
                return false;
            }
        }
        return true;
    }
}
