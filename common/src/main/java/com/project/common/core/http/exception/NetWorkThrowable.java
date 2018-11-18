package com.project.common.core.http.exception;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2018/6/10 11:55
 * 描    述：网络异常处理
 * 修订历史：
 */

public class NetWorkThrowable extends Exception {

    public String status;
    public String msg;

    public NetWorkThrowable(String code, String message) {
        this.status = code;
        this.msg = message;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
