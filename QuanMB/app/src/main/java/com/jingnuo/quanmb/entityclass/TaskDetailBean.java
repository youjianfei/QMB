package com.jingnuo.quanmb.entityclass;

public class TaskDetailBean {


    /**
     * data : {"Status_name":"待帮助","client_no":"2147483681","commission":123,"createDate":"2018-05-03 19:38:39","detailed_address":"312","is_counteroffer":"1","nick_name":"3333","specialty_name":"房屋","task_EndDate":"2018-05-04 19:38:39","task_Status_code":"01","task_description":"123","task_name":"12312","url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/d2389f24-a789-4d0e-9760-442ab37386b4hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525506806&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=aE0V0QKTuRfrmt0NSUhgJF3P1v0%3D,","user_grade":"1"}
     * message : 获取列表成功
     * status : 1
     */

    private DataBean data;
    private String message;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * Status_name : 待帮助
         * client_no : 2147483681
         * commission : 123
         * createDate : 2018-05-03 19:38:39
         * detailed_address : 312
         * is_counteroffer : 1
         * nick_name : 3333
         * specialty_name : 房屋
         * task_EndDate : 2018-05-04 19:38:39
         * task_Status_code : 01
         * task_description : 123
         * task_name : 12312
         * url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/d2389f24-a789-4d0e-9760-442ab37386b4hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525506806&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=aE0V0QKTuRfrmt0NSUhgJF3P1v0%3D,
         * user_grade : 1
         */

        private String Status_name;
        private String client_no;
        private int commission;
        private String createDate;
        private String detailed_address;
        private String is_counteroffer;
        private String nick_name;
        private String specialty_name;
        private String task_EndDate;
        private String task_Status_code;
        private String task_description;
        private String task_name;
        private String url;
        private String user_grade;

        public String getStatus_name() {
            return Status_name;
        }

        public void setStatus_name(String Status_name) {
            this.Status_name = Status_name;
        }

        public String getClient_no() {
            return client_no;
        }

        public void setClient_no(String client_no) {
            this.client_no = client_no;
        }

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getDetailed_address() {
            return detailed_address;
        }

        public void setDetailed_address(String detailed_address) {
            this.detailed_address = detailed_address;
        }

        public String getIs_counteroffer() {
            return is_counteroffer;
        }

        public void setIs_counteroffer(String is_counteroffer) {
            this.is_counteroffer = is_counteroffer;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getSpecialty_name() {
            return specialty_name;
        }

        public void setSpecialty_name(String specialty_name) {
            this.specialty_name = specialty_name;
        }

        public String getTask_EndDate() {
            return task_EndDate;
        }

        public void setTask_EndDate(String task_EndDate) {
            this.task_EndDate = task_EndDate;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUser_grade() {
            return user_grade;
        }

        public void setUser_grade(String user_grade) {
            this.user_grade = user_grade;
        }
    }
}
