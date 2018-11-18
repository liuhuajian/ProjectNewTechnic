package com.project.common.core.download.update.entity;

import java.io.Serializable;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/9/6 13:36
 * @版本0.2
 * @类说明：版本升级
 */

public class AppVersion implements Serializable {

    private String id;
    private int currentVersion;
    private int miniVersion;
    private String versionName;
    private String os;//os  1-ios 2-android
    private String downloadUrl;
    private String versionDesc;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public int getMiniVersion() {
        return miniVersion;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setMiniVersion(int miniVersion) {
        this.miniVersion = miniVersion;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
