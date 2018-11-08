package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class AllCityBean {

    /**
     *
     * msg : 获取所有城市列表成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * disorder : 0
         * id : 110100
         * name : 北京市
         * pid : 110000
         */

        private int disorder;
        private int id;
        private String name;
        private int pid;

        public int getDisorder() {
            return disorder;
        }

        public void setDisorder(int disorder) {
            this.disorder = disorder;
        }

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }
    }
}
