package com.jingnuo.quanmb.entityclass;

public class QueRenHelp_Bean {


    /**
     * date : {"Status_name":"已接单","client_name":"崔","client_sex":"男","commission":20,"detailed_address":"0809","img_url":"d:aaa","is_counteroffer":"1","mobile_no":"110","name":"王丽","task_EndDate":"2018-04-20 14:44:38","task_StartDate":"2018-04-03 14:44:23","task_Status_code":"02","task_description":"接口连接超时","task_name":"接口维护2","user_grade":"一级"}
     * message : 确认帮助成功
     * status : 1
     */

    private DateBean date;
    private String message;
    private int status;

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
    }

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

    public static class DateBean {
        /**
         * Status_name : 已接单
         * client_name : 崔
         * client_sex : 男
         * commission : 20
         * detailed_address : 0809
         * img_url : d:aaa
         * is_counteroffer : 1
         * mobile_no : 110
         * name : 王丽
         * task_EndDate : 2018-04-20 14:44:38
         * task_StartDate : 2018-04-03 14:44:23
         * task_Status_code : 02
         * task_description : 接口连接超时
         * task_name : 接口维护2
         * user_grade : 一级
         */

        private String Status_name;
        private String client_name;
        private String client_sex;
        private int commission;
        private String detailed_address;
        private String img_url;
        private String is_counteroffer;
        private String mobile_no;
        private String name;
        private String task_EndDate;
        private String task_StartDate;
        private String task_Status_code;
        private String task_description;
        private String task_name;
        private String user_grade;

        public String getStatus_name() {
            return Status_name;
        }

        public void setStatus_name(String Status_name) {
            this.Status_name = Status_name;
        }

        public String getClient_name() {
            return client_name;
        }

        public void setClient_name(String client_name) {
            this.client_name = client_name;
        }

        public String getClient_sex() {
            return client_sex;
        }

        public void setClient_sex(String client_sex) {
            this.client_sex = client_sex;
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

        public String getIs_counteroffer() {
            return is_counteroffer;
        }

        public void setIs_counteroffer(String is_counteroffer) {
            this.is_counteroffer = is_counteroffer;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
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

        public String getTask_Status_code() {
            return task_Status_code;
        }

        public void setTask_Status_code(String task_Status_code) {
            this.task_Status_code = task_Status_code;
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
