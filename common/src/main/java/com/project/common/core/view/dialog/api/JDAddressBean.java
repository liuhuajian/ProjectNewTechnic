package com.project.common.core.view.dialog.api;

import java.util.List;

/**
 * Created by yexm on 2018/11/8.
 */

public class JDAddressBean {

    /**
     * success : true
     * data : [{"id":2817,"name":"静安区","is3cod":"false","cod":true},{"id":2820,"name":"闸北区","is3cod":"false","cod":true},{"id":2822,"name":"虹口区","is3cod":"false","cod":true},{"id":2823,"name":"杨浦区","is3cod":"false","cod":true},{"id":2824,"name":"宝山区","is3cod":"false","cod":true},{"id":2825,"name":"闵行区","is3cod":"false","cod":true},{"id":2826,"name":"嘉定区","is3cod":"false","cod":true},{"id":2830,"name":"浦东新区","is3cod":"false","cod":true},{"id":2833,"name":"青浦区","is3cod":"false","cod":true},{"id":2834,"name":"松江区","is3cod":"false","cod":true},{"id":2835,"name":"金山区","is3cod":"false","cod":true},{"id":2837,"name":"奉贤区","is3cod":"false","cod":true},{"id":2841,"name":"普陀区","is3cod":"false","cod":true},{"id":78,"name":"黄浦区","is3cod":"false","cod":true},{"id":2919,"name":"崇明区","is3cod":"false","cod":true},{"id":2813,"name":"徐汇区","is3cod":"false","cod":true},{"id":2815,"name":"长宁区","is3cod":"false","cod":true}]
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean{
        /**
         * id : 2817
         * name : 静安区
         * is3cod : false
         * cod : true
         */

        private int id;
        private String name;
        private String is3cod;
        private boolean cod;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIs3cod() {
            return is3cod;
        }

        public void setIs3cod(String is3cod) {
            this.is3cod = is3cod;
        }

        public boolean isCod() {
            return cod;
        }

        public void setCod(boolean cod) {
            this.cod = cod;
        }
    }
}
