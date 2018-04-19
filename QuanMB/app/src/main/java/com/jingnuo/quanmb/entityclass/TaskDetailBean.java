package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class TaskDetailBean {

    /**
     * data : [{"Status_name":"待帮助","commission":1001,"detailed_address":"1709","img_url":"","name":"王丽","task_EndDate":"2018-04-23 10:34:23","task_StartDate":"2018-04-18 10:34:19","task_description":"1单元顶层无电梯","task_name":"修水管","user_grade":"一级"}]
     * message : 获取列表成功
     * status : 1
     */

    private String message;
    private int status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Status_name : 待帮助
         * commission : 1001
         * detailed_address : 1709
         * img_url :
         * name : 王丽
         * task_EndDate : 2018-04-23 10:34:23
         * task_StartDate : 2018-04-18 10:34:19
         * task_description : 1单元顶层无电梯
         * task_name : 修水管
         * user_grade : 一级
         */

        private String Status_name;
        private int commission;
        private String detailed_address;
        private String img_url;
        private String name;
        private String task_EndDate;
        private String task_StartDate;
        private String task_description;
        private String task_name;
        private String user_grade;

        public String getStatus_name() {
            return Status_name;
        }

        public void setStatus_name(String Status_name) {
            this.Status_name = Status_name;
        }

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
        }

        public String getDetailed_address() {
            return detailed_address;
        }

        public void setDetailed_address(String detailed_address) {
            this.detailed_address = detailed_address;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTask_EndDate() {
            return task_EndDate;
        }

        public void setTask_EndDate(String task_EndDate) {
            this.task_EndDate = task_EndDate;
        }

        public String getTask_StartDate() {
            return task_StartDate;
        }

        public void setTask_StartDate(String task_StartDate) {
            this.task_StartDate = task_StartDate;
        }

        public String getTask_description() {
            return task_description;
        }

        public void setTask_description(String task_description) {
            this.task_description = task_description;
        }

        public String getTask_name() {
            return task_name;
        }

        public void setTask_name(String task_name) {
            this.task_name = task_name;
        }

        public String getUser_grade() {
            return user_grade;
        }

        public void setUser_grade(String user_grade) {
            this.user_grade = user_grade;
        }
    }
}
