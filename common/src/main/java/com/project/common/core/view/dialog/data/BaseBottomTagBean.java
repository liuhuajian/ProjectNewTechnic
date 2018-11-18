package com.project.common.core.view.dialog.data;

import com.project.common.core.http.bean.BaseTagBean;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/8/30 10:30
 * @版本 0.2
 * @类说明:
 */

public class BaseBottomTagBean extends BaseTagBean {
    public BaseBottomTagBean() {
    }

    public BaseBottomTagBean(String contentText) {
        this.contentText = contentText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    private String contentText;
}
