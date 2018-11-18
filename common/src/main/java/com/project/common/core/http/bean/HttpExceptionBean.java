package com.project.common.core.http.bean;

/**
 * 2018/6/10 9:46
 */

public class HttpExceptionBean {

    private int code;
    private String msg;
    private String body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
