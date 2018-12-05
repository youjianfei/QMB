package com.jingnuo.quanmb.entityclass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 飞 on 2018/12/5.
 */

public class CancelOrderBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String task_des;
        private  boolean isselect;

        public boolean isIsselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
        }

        public String getTask_des() {
            return task_des;
        }

        public void setTask_des(String task_des) {
            this.task_des = task_des;
        }
    }
}
