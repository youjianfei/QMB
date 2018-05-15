package com.jingnuo.quanmb.entityclass;

public class BargainMessagedetailsBean {


    /**
     * code : 1
     * data : {"Avatar_imgUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1e5d6f2f-f779-481f-ab24-b11d744c9fa6640fd925b71ff1bd.jpg,","Status_name":"已失效","commission":0,"counteroffer_Amount":20,"createDate":"2018-05-11 14:35:58","helper_no":"2","is_helper_bid":"Y","mark":"2","name":"隔壁老王","task_Status_code":"13","task_id":4,"task_name":"爱仕达"}
     * message : 操作成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    public static class DataBean {
        /**
         * Avatar_imgUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1e5d6f2f-f779-481f-ab24-b11d744c9fa6640fd925b71ff1bd.jpg,
         * Status_name : 已失效
         * commission : 0
         * counteroffer_Amount : 20
         * createDate : 2018-05-11 14:35:58
         * helper_no : 2
         * is_helper_bid : Y
         * mark : 2
         * name : 隔壁老王
         * task_Status_code : 13
         * task_id : 4
         * task_name : 爱仕达
         */

        private String Avatar_imgUrl;
        private String Status_name;
        private int commission;
        private int counteroffer_Amount;
        private String createDate;
        private String helper_no;
        private String business_no;
        private String is_helper_bid;
        private String specialty_name;
        private String mark;
        private String name;
        private String task_Status_code;
        private int task_id;
        private String task_name;
        private String binding_id;
        private String send_client_no;

        private double response_Amount;

        public String getSend_client_no() {
            return send_client_no;
        }

        public void setSend_client_no(String send_client_no) {
            this.send_client_no = send_client_no;
        }

        public String getBinding_id() {
            return binding_id;
        }

        public void setBinding_id(String binding_id) {
            this.binding_id = binding_id;
        }

        public String getBusiness_no() {
            return business_no;
        }

        public void setBusiness_no(String business_no) {
            this.business_no = business_no;
        }

        public double getResponse_Amount() {
            return response_Amount;
        }

        public void setResponse_Amount(double response_Amount) {
            this.response_Amount = response_Amount;
        }

        public String getAvatar_imgUrl() {
            return Avatar_imgUrl;
        }

        public void setAvatar_imgUrl(String Avatar_imgUrl) {
            this.Avatar_imgUrl = Avatar_imgUrl;
        }

        public String getStatus_name() {
            return Status_name;
        }

        public String getSpecialty_name() {
            return specialty_name;
        }

        public void setSpecialty_name(String specialty_name) {
            this.specialty_name = specialty_name;
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

        public String getHelper_no() {
            return helper_no;
        }

        public void setHelper_no(String helper_no) {
            this.helper_no = helper_no;
        }

        public String getIs_helper_bid() {
            return is_helper_bid;
        }

        public void setIs_helper_bid(String is_helper_bid) {
            this.is_helper_bid = is_helper_bid;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTask_Status_code() {
            return task_Status_code;
        }

        public void setTask_Status_code(String task_Status_code) {
            this.task_Status_code = task_Status_code;
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
    }
}
