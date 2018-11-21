package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by 飞 on 2018/11/15.
 */

public class DescriptionTypeBean {

    /**
     * code : 1
     * data : [{"task_des":"更换屏幕"},{"task_des":"更换电池"}]
     * messgae : 操作成功
     */

    private int code;
    private String messgae;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * task_des : 更换屏幕
         */

        private String task_des;
        private String des_id;

        public String getDes_id() {
            return des_id;
        }

        public void setDes_id(String des_id) {
            this.des_id = des_id;
        }

        public String getTask_des() {
            return task_des;
        }

        public void setTask_des(String task_des) {
            this.task_des = task_des;
        }
    }
}
