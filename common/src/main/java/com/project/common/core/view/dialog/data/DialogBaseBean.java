package com.project.common.core.view.dialog.data;

import java.io.Serializable;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/8/21 16:00
 * @版本 0.2
 * @类说明: dialog 上层数据
 */

public class DialogBaseBean implements Serializable {
    private String title = "";
    private String confirmData = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id = 0;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content = "";

    @Override
    public String toString() {
        return "DialogBaseBean{" +
                "title='" + title + '\'' +
                ", confirmData='" + confirmData + '\'' +
                ", cancleData='" + cancleData + '\'' +
                '}';
    }

    public String getConfirmData() {
        return confirmData;
    }

    public void setConfirmData(String confirmData) {
        this.confirmData = confirmData;
    }

    public String getCancleData() {
        return cancleData;
    }

    public void setCancleData(String cancleData) {
        this.cancleData = cancleData;
    }

    private String cancleData = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
