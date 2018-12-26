package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class MyorderBean {

    /**
     * code : 1
     * date : [{"Status_name":"待帮助","commission":23,"count":0,"detailed_address":"fgdsf","is_
     * counteroffer":"0","name":"小猪佩奇","task_EndDate":"2008-02-05 12:25:00","task_StartDate":"20
     * 18-04-23 10:11:24","task_Status_code":"01","task_description":"需要一个人武昌葱花嘚","task_id":31
     * ,"task_name":"充话费","user_grade":"1"},{"Status_name":"待帮助","commission":25,"count":0,"detailed_
     * address":"asdfdsaf","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/eb247f1e-ab6
     * c-48c2-ab11-7b11f8514767upload_57474945_162f04d8770__8000_00000000.tmp?Expires=1524453636&OSSAccessKeyId=
     * &Signature=b7ZGRCFBcxQZAs%2BAru4bL1j8vNc%3D","is_counteroffer":"1","name":"小猪佩奇","task_EndDate":"2018-
     * 04-18 10:00:00","task_StartDate":"2018-04-23 10:21:45","task_Status_code":"01","task_description":"dsafsda
     * f","task_id":32,"task_name":"sadfdsf","user_grade":"1"},{"Status_name":"待帮助","commission":23,"count":0,"
     * detailed_address":"qqqqqqqqqqq","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/
     * f1049bab-8d95-463e-b87f-26a46b471969upload_57474945_162f04d8770__8000_00000004.tmp?Expires=1524454061&OSSAcc
     * essKeyId=LTAIcYmxp0FtpOf4&Signature=FNuO1rczzX9qxHOovJDsHWGLg0A%3D","is_counteroffer":"1","name":"小猪佩奇",
     * "task_EndDate":"2018-04-23 10:00:00","task_StartDate":"2018-04-23 10:27:52","task_Status_code":"01","task_des
     * cription":"qqqqqqqqqqqq","task_id":33,"task_name":"qqqqqqqqqqq","user_grade":"1"},{"Status_name":"待帮助","com
     * mission":36,"count":0,"detailed_address":"来找我玩","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/230312ff-e9e4-4214-9f8f-8fe3f890f6cfupload__14fbf91b_162f15f9d2f__8000_00000036.tmp?Expires=1524475416&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=8S13aLZlPFlVjKJSHt9L2hczc4k%3D","is_counteroffer":"1","name":"小猪佩奇","task_EndDate":"2018-04-23 10:00:00","task_StartDate":"2018-04-23 16:23:53","task_Status_code":"01","task_description":"裤头子","task_id":34,"task_name":"突兀","user_grade":"1"},{"Status_name":"待帮助","commission":45,"count":0,"detailed_address":"TUT咯具体","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/1cf64ef3-dc48-46a1-8587-7b47839ff6a4upload__14fbf91b_162f15f9d2f__8000_00000038.tmp?Expires=1524475643&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=PRANoT5bnHNpWADqWu6Krjxmovw%3D","is_counteroffer":"0","name":"小猪佩奇","task_EndDate":"2018-05-19 16:20:00","task_StartDate":"2018-04-23 16:27:46","task_Status_code":"01","task_description":"TUTYY具体","task_id":35,"task_name":"阿狸了了","user_grade":"1"},{"Status_name":"待帮助","commission":45,"count":0,"detailed_address":"445646","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/taskImg/3e1de69b-efa6-4f8e-a30a-45b17a9fa857upload_6e89171e_162f576e049__8000_00000038.tmp?Expires=1524541829&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=Etwf1VqNDa6KcTe7krkOtjTW3hc%3D","is_counteroffer":"1","name":"小猪佩奇","task_EndDate":"2018-04-24 10:00:00","task_StartDate":"2018-04-24 10:50:49","task_Status_code":"01","task_description":"54654","task_id":36,"task_name":"5456","user_grade":"1"},{"Status_name":"待帮助","commission":69,"count":0,"detailed_address":"asdfsadf","is_counteroffer":"1","name":"小猪佩奇","task_EndDate":"2018-04-24 10:00:00","task_StartDate":"2018-04-25 14:35:10","task_Status_code":"01","task_description":"sadfsadfsf","task_id":37,"task_name":"sdfsafdasfasf","user_grade":"1"},{"Status_name":"待帮助","commission":69,"count":0,"detailed_address":"asdfsadf","is_counteroffer":"1","name":"小猪佩奇","task_EndDate":"2018-04-24 10:00:00","task_StartDate":"2018-04-25 14:35:10","task_Status_code":"01","task_description":"sadfsadfsf","task_id":38,"task_name":"sdfsafdasfasf","user_grade":"1"}]
     * message : 查询信息成功
     *
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> date) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Status_name : 待帮助
         * commission : 23
         * count : 0
         * detailed_address : fgdsf
         * is_counteroffer : 0
         * name : 小猪佩奇
         * task_EndDate : 2008-02-05 12:25:00
         * task_StartDate : 2018-04-23 10:11:24
         * task_Status_code : 01
         * task_description : 需要一个人武昌葱花嘚
         * task_id : 31
         * task_name : 充话费
         * user_grade : 1
         * img_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/eb247f1e-ab6c-48c2-ab11-7b11f8514767upload_57474945_162f04d8770__8000_00000000.tmp?Expires=1524453636&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=b7ZGRCFBcxQZAs%2BAru4bL1j8vNc%3D
         */

        private String Status_name="";
        private double commission;
        private double counteroffer_Amount;
        private int count;
        private String detailed_address="";
        private String is_counteroffer="";
        private String name="";
        private String task_EndDate="";
        private String task_StartDate="";
        private String task_Status_code="";
        private String task_description="";
        private int task_id;
        private String task_name="";
        private String user_grade="";
        private String img_url="";
        private String specialty_name="";
        private String is_helper_bid="";
        private String app_type="";
        private String order_no="";
        private String task_type_img_url="";

        public String getTask_type_img_url() {
            return task_type_img_url;
        }

        public void setTask_type_img_url(String task_type_img_url) {
            this.task_type_img_url = task_type_img_url;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getApp_type() {
            return app_type;
        }

        public void setApp_type(String app_type) {
            this.app_type = app_type;
        }

        public String getIs_helper_bid() {
            return is_helper_bid;
        }

        public void setIs_helper_bid(String is_helper_bid) {
            this.is_helper_bid = is_helper_bid;
        }

        public double getCounteroffer_Amount() {
            return counteroffer_Amount;
        }

        public void setCounteroffer_Amount(double counteroffer_Amount) {
            this.counteroffer_Amount = counteroffer_Amount;
        }

        public String getSpecialty_name() {
            return specialty_name;
        }

        public void setSpecialty_name(String specialty_name) {
            this.specialty_name = specialty_name;
        }

        public String getStatus_name() {
            return Status_name;
        }

        public void setStatus_name(String Status_name) {
            this.Status_name = Status_name;
        }

        public double getCommission() {
            return commission;
        }

        public void setCommission(double commission) {
            this.commission = commission;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public String getUser_grade() {
            return user_grade;
        }

        public void setUser_grade(String user_grade) {
            this.user_grade = user_grade;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
