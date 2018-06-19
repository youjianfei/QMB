package com.jingnuo.quanmb.entityclass;

public class QueRenHelp_Bean {


    /**
     * data : {"Status_name":"已接单","client_no":"90000000061","counteroffer_Amount":50,"createDate":"2018-06-19 09:08:59","detailed_address":"南阳路与宋寨北街升龙汇金广场2楼中庭","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1e60e99d-02a5-4962-a681-a59c1b11d3a10002.png","is_counteroffer":"1","mobile_no":"13526988096","nick_name":"18338772983","order_Amount":50,"order_EndDate":"2018-06-20 09:09:26","order_no":"QMB180619100000390","specialty_name":"帮送","task_Status_code":"02","task_description":"帮送快递到东区","task_id":439,"task_name":"帮送快递","user_level":1}
     * message : 确认帮助成功
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
         * Status_name : 已接单
         * client_no : 90000000061
         * counteroffer_Amount : 50.0
         * createDate : 2018-06-19 09:08:59
         * detailed_address : 南阳路与宋寨北街升龙汇金广场2楼中庭
         * headUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1e60e99d-02a5-4962-a681-a59c1b11d3a10002.png
         * is_counteroffer : 1
         * mobile_no : 13526988096
         * nick_name : 18338772983
         * order_Amount : 50.0
         * order_EndDate : 2018-06-20 09:09:26
         * order_no : QMB180619100000390
         * specialty_name : 帮送
         * task_Status_code : 02
         * task_description : 帮送快递到东区
         * task_id : 439
         * task_name : 帮送快递
         * user_level : 1
         */

        private String Status_name;
        private String client_no;
        private double counteroffer_Amount;
        private String createDate;
        private String detailed_address;
        private String headUrl;
        private String is_counteroffer;
        private String mobile_no;
        private String nick_name;
        private double order_Amount;
        private String order_EndDate;
        private String order_no;
        private String specialty_name;
        private String task_Status_code;
        private String task_description;
        private int task_id;
        private String task_name;
        private int user_level;

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

        public double getCounteroffer_Amount() {
            return counteroffer_Amount;
        }

        public void setCounteroffer_Amount(double counteroffer_Amount) {
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

        public double getOrder_Amount() {
            return order_Amount;
        }

        public void setOrder_Amount(double order_Amount) {
            this.order_Amount = order_Amount;
        }

        public String getOrder_EndDate() {
            return order_EndDate;
        }

        public void setOrder_EndDate(String order_EndDate) {
            this.order_EndDate = order_EndDate;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
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

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public String getTask_name() {
            return task_name;
        }

        public void setTask_name(String task_name) {
            this.task_name = task_name;
        }

        public int getUser_level() {
            return user_level;
        }

        public void setUser_level(int user_level) {
            this.user_level = user_level;
        }
    }
}
