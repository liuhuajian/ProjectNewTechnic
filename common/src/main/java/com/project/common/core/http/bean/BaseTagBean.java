package com.project.common.core.http.bean;

import java.util.List;

/**
 *项目 国民健康
 * @Create by yexm
 * @创建日期 2018/8/30 13:25
 *
 * @版本 0.2
 * @类说明: 系统标签返回基类
 */

public class BaseTagBean {

    /**
     * typeId : string
     * typeName : string
     */

    private String typeId;
    private String typeName;
    /**
     * tagId : TI2018082320001
     * tagName : 爸爸
     * tagLevel : 家庭成员关系
     * parentId : null
     * tagRemark : null
     * targetType : null
     * sysFlag : null
     * tagUnit : null
     * targetText : null
     * sortId : null
     */

    private String tagId;
    private String tagName;
    private String tagLevel;
    private String parentId;
    private String tagRemark;
    private String targetType;
    private String sysFlag;
    private String tagUnit;
    private String targetText;
    private String sortId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
    private List<SystemSonTagInfoBean> systemSonTagInfo;



    public List<SystemSonTagInfoBean> getSystemSonTagInfo() {
        return systemSonTagInfo;
    }

    public void setSystemSonTagInfo(List<SystemSonTagInfoBean> systemSonTagInfo) {
        this.systemSonTagInfo = systemSonTagInfo;
    }

    public static class SystemSonTagInfoBean {
        /**
         * tagId : TI2018082310012
         * tagName : 青霉素
         * typeId : ST2018082104
         * tagLevel : 2
         * parentId : TI2018082310011
         * tagRemark : null
         * targetType : null
         * sysFlag : null
         * tagUnit : null
         * targetText : null
         * sortId : null
         */

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

        public Object getTagRemark() {
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

        public Object getSysFlag() {
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
    }
}

