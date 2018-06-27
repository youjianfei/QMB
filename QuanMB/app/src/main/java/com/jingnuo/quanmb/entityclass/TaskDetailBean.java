package com.jingnuo.quanmb.entityclass;

public class TaskDetailBean {
    /**
     * data : {"Status_name":"待帮助","client_name":"看看","client_no":"90000000003","client_sex":"0","commission":63,"createDate":"2018-05-09 09:08:23","detailed_address":"了了了我我","is_counteroffer":"1","mobile_no":"2556","nick_name":"天天","specialty_name":"app开发","task_EndDate":"2018-05-12 09:08:23","task_ID":46,"task_Status_code":"01","task_Time":5,"task_description":"开发炸弹","task_name":"我选","url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/05cdaca5-5fa8-4e99-a439-b3bfb73f3d21dts_featured_ath_clr100.png?Expires=1525919126&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=eLBGuzFvy8PxOb1LSGBnSFOQ0gI%3D,","user_grade":"1"}
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
         * client_name : 看看
         * client_no : 90000000003
         * client_sex : 0
         * commission : 63
         * createDate : 2018-05-09 09:08:23
         * detailed_address : 了了了我我
         * is_counteroffer : 1
         * mobile_no : 2556
         * nick_name : 天天
         * specialty_name : app开发
         * task_EndDate : 2018-05-12 09:08:23
         * task_ID : 46
         * task_Status_code : 01
         * task_Time : 5
         * task_description : 开发炸弹
         * task_name : 我选
         * url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/05cdaca5-5fa8-4e99-a439-b3bfb73f3d21dts_featured_ath_clr100.png?Expires=1525919126&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=eLBGuzFvy8PxOb1LSGBnSFOQ0gI%3D,
         * user_grade : 1
         */

        private String Status_name;
        private String client_name;
        private String client_no;
        private String client_sex;
        private double commission;
        private String createDate;
        private String detailed_address;
        private String is_counteroffer;
        private String mobile_no;
        private String nick_name;
        private String specialty_name;
        private String task_EndDate;
        private int task_id;
        private String task_Status_code;
        private String task_hope;
        private String task_description;
        private String is_helper_bid;
        private String task_name;
        private String Avatar_imgUrl;
        private String task_ImgUrl;
        private String user_grade;
        private String order_no;
        private String release_address;

        public String getRelease_address() {
            return release_address;
        }

        public void setRelease_address(String release_address) {
            this.release_address = release_address;
        }

        public String getIs_helper_bid() {
            return is_helper_bid;
        }

        public void setIs_helper_bid(String is_helper_bid) {
            this.is_helper_bid = is_helper_bid;
        }

        public String getAvatar_imgUrl() {
            return Avatar_imgUrl;
        }

        public void setAvatar_imgUrl(String avatar_imgUrl) {
            Avatar_imgUrl = avatar_imgUrl;
        }

        public String getTask_ImgUrl() {
            return task_ImgUrl;
        }

        public void setTask_ImgUrl(String task_ImgUrl) {
            this.task_ImgUrl = task_ImgUrl;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

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

        public String getClient_no() {
            return client_no;
        }

        public void setClient_no(String client_no) {
            this.client_no = client_no;
        }

        public String getClient_sex() {
            return client_sex;
        }

        public void setClient_sex(String client_sex) {
            this.client_sex = client_sex;
        }

        public double getCommission() {
            return commission;
        }

        public void setCommission(double commission) {
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

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
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

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public String getTask_Status_code() {
            return task_Status_code;
        }

        public void setTask_Status_code(String task_Status_code) {
            this.task_Status_code = task_Status_code;
        }


        public String getTask_hope() {
            return task_hope;
        }

        public void setTask_hope(String task_hope) {
            this.task_hope = task_hope;
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
