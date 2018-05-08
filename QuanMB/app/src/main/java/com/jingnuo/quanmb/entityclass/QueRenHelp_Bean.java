package com.jingnuo.quanmb.entityclass;

public class QueRenHelp_Bean {


    /**
     * date : {"Status_name":"已接单","client_no":"2147483680","counteroffer_Amount":33333,"createDate":"2018-05-08 17:05:28","detailed_address":"33333333","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/2785a352-30d4-46ef-928a-4366ac6ea734hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525856854&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=4l53l8r7pLb6A2fKpK8QTI7Yoms%3D","is_counteroffer":"1","nick_name":"中单亚索  不给就送","order_Amount":33333,"order_EndDate":"2018-05-23 17:07:33","specialty_name":"家具","task_Status_code":"02","task_description":"33333333","task_name":"33333333","user_grade":"1"}
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
         * client_no : 2147483680
         * counteroffer_Amount : 33333
         * createDate : 2018-05-08 17:05:28
         * detailed_address : 33333333
         * headUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/2785a352-30d4-46ef-928a-4366ac6ea734hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525856854&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=4l53l8r7pLb6A2fKpK8QTI7Yoms%3D
         * is_counteroffer : 1
         * nick_name : 中单亚索  不给就送
         * order_Amount : 33333
         * order_EndDate : 2018-05-23 17:07:33
         * specialty_name : 家具
         * task_Status_code : 02
         * task_description : 33333333
         * task_name : 33333333
         * user_grade : 1
         */
        private String mobile_no;
        private String Status_name;
        private String client_no;
        private int counteroffer_Amount;
        private String createDate;
        private String detailed_address;
        private String order_no;

        public String getMobile_no() {
            return mobile_no;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        private String headUrl;
        private String is_counteroffer;
        private String nick_name;
        private int order_Amount;
        private String order_EndDate;
        private String specialty_name;
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

        public String getClient_no() {
            return client_no;
        }

        public void setClient_no(String client_no) {
            this.client_no = client_no;
        }

        public int getCounteroffer_Amount() {
            return counteroffer_Amount;
        }

        public void setCounteroffer_Amount(int counteroffer_Amount) {
            this.counteroffer_Amount = counteroffer_Amount;
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

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
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

        public int getOrder_Amount() {
            return order_Amount;
        }

        public void setOrder_Amount(int order_Amount) {
            this.order_Amount = order_Amount;
        }

        public String getOrder_EndDate() {
            return order_EndDate;
        }

        public void setOrder_EndDate(String order_EndDate) {
            this.order_EndDate = order_EndDate;
        }

        public String getSpecialty_name() {
            return specialty_name;
        }

        public void setSpecialty_name(String specialty_name) {
            this.specialty_name = specialty_name;
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
