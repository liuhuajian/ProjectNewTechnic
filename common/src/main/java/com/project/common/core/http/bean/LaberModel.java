package com.project.common.core.http.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目：国民健康平台
 *
 * @Creator曾招林julyzeng
 * @创建日期： 2018/8/30 1546
 * @版本0.2
 * @类说明：平台所有标签实体类（目前最高二级）
 */

public class LaberModel implements Serializable {

    private String tagId;
    private String tagName;
    private String typeId;
    private String tagLevel;
    private String parentId;
    private String tagRemark;
    private String targetType;
    private String sysFlag;
    private String tagUnit;
    private String targetText;
    private String sortId;

    private boolean isSelected = false;

    private List<LaberModel> systemSonTagInfo;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTagLevel() {
        return tagLevel;
    }

    public void setTagLevel(String tagLevel) {
        this.tagLevel = tagLevel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTagRemark() {
        return tagRemark;
    }

    public void setTagRemark(String tagRemark) {
        this.tagRemark = tagRemark;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

    public String getTagUnit() {
        return tagUnit;
    }

    public void setTagUnit(String tagUnit) {
        this.tagUnit = tagUnit;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public List<LaberModel> getSystemSonTagInfo() {
        return systemSonTagInfo;
    }

    public void setSystemSonTagInfo(List<LaberModel> systemSonTagInfo) {
        this.systemSonTagInfo = systemSonTagInfo;
    }
}
