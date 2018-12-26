package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class TaskDetailBean {


    /**
     * code : 1
     * data : {"Avatar_imgUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/3657847f-1883-4e8b-949d-5f44b0c422bb1537250201597.png,","Evaluation_star":4.8,"Status_name":"已接单","app_type":"1","b_h_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/avatar/business.png","business_mobile_no":"18539931923","business_name":"月嫂大师","business_no":"60000000061","client_name":"测试环境","client_no":"90000000002","client_sex":"0","commission":0,"count":17,"createDate":"2018-12-25 17:50:12","detailed_address":"裕华文清园","evaluate":["专业认真","技术过硬","上门速度快","服务态度好"],"is_counteroffer":"1","is_helper_bid":"Y","level":"VIP1","memberImgUrl":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/VIP1.png","member_enddate":"2019-01-31 00:00:00","member_level":1,"member_lv":"会员VIP1","mobile_no":"18539931923","nick_name":"测试环境","order_no":"QMB181225100001711","overCount":17,"release_address":"裕华文清园","specialty_name":"保姆月嫂","task_EndDate":"2018-12-28 17:50:12","task_ImgUrl":"","task_Startdate":"2018-12-25 17:50:12","task_Status_code":"02","task_Time":"尽快","task_description":"保姆","task_id":7011,"task_type":1308,"total_score":81,"user_level":122,"x_value":"34.799874","y_value":"113.632031"}
     * message : 获取信息成功
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
         * Avatar_imgUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/3657847f-1883-4e8b-949d-5f44b0c422bb1537250201597.png,
         * Evaluation_star : 4.8
         * Status_name : 已接单
         * app_type : 1
         * b_h_url : https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/avatar/business.png
         * business_mobile_no : 18539931923
         * business_name : 月嫂大师
         * business_no : 60000000061
         * client_name : 测试环境
         * client_no : 90000000002
         * client_sex : 0
         * commission : 0.0
         * count : 17
         * createDate : 2018-12-25 17:50:12
         * detailed_address : 裕华文清园
         * evaluate : ["专业认真","技术过硬","上门速度快","服务态度好"]
         * is_counteroffer : 1
         * is_helper_bid : Y
         * level : VIP1
         * memberImgUrl : https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/VIP1.png
         * member_enddate : 2019-01-31 00:00:00
         * member_level : 1
         * member_lv : 会员VIP1
         * mobile_no : 18539931923
         * nick_name : 测试环境
         * order_no : QMB181225100001711
         * overCount : 17
         * release_address : 裕华文清园
         * specialty_name : 保姆月嫂
         * task_EndDate : 2018-12-28 17:50:12
         * task_ImgUrl :
         * task_Startdate : 2018-12-25 17:50:12
         * task_Status_code : 02
         * task_Time : 尽快
         * task_description : 保姆
         * task_id : 7011
         * task_type : 1308
         * total_score : 81
         * user_level : 122
         * x_value : 34.799874
         * y_value : 113.632031
         */

        private String Avatar_imgUrl;
        private double Evaluation_star;
        private String Status_name;
        private String app_type;
        private String b_h_url;
        private String business_mobile_no;
        private String business_name;
        private String business_no;
        private String client_name;
        private String client_no;
        private String client_sex;
        private double commission;
        private int count;
        private String createDate;
        private String detailed_address;
        private String is_counteroffer;
        private String is_helper_bid;
        private String level;
        private String memberImgUrl="";
        private String member_enddate;
        private int member_level;
        private String member_lv;
        private String mobile_no;
        private String nick_name;
        private String order_no;
        private int overCount;
        private String release_address;
        private String specialty_name;
        private String task_EndDate;
        private String task_ImgUrl;
        private String task_Startdate;
        private String task_Status_code;
        private String task_Time;
        private String task_description;
        private int task_id;
        private int task_type;
        private int total_score;
        private int user_level;
        private String x_value;
        private String y_value;
        private List<String> evaluate;

        public String getAvatar_imgUrl() {
            return Avatar_imgUrl;
        }

        public void setAvatar_imgUrl(String Avatar_imgUrl) {
            this.Avatar_imgUrl = Avatar_imgUrl;
        }

        public double getEvaluation_star() {
            return Evaluation_star;
        }

        public void setEvaluation_star(double Evaluation_star) {
            this.Evaluation_star = Evaluation_star;
        }

        public String getStatus_name() {
            return Status_name;
        }

        public void setStatus_name(String Status_name) {
            this.Status_name = Status_name;
        }

        public String getApp_type() {
            return app_type;
        }

        public void setApp_type(String app_type) {
            this.app_type = app_type;
        }

        public String getB_h_url() {
            return b_h_url;
        }

        public void setB_h_url(String b_h_url) {
            this.b_h_url = b_h_url;
        }

        public String getBusiness_mobile_no() {
            return business_mobile_no;
        }

        public void setBusiness_mobile_no(String business_mobile_no) {
            this.business_mobile_no = business_mobile_no;
        }

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public String getBusiness_no() {
            return business_no;
        }

        public void setBusiness_no(String business_no) {
            this.business_no = business_no;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public String getIs_helper_bid() {
            return is_helper_bid;
        }

        public void setIs_helper_bid(String is_helper_bid) {
            this.is_helper_bid = is_helper_bid;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getMemberImgUrl() {
            return memberImgUrl;
        }

        public void setMemberImgUrl(String memberImgUrl) {
            this.memberImgUrl = memberImgUrl;
        }

        public String getMember_enddate() {
            return member_enddate;
        }

        public void setMember_enddate(String member_enddate) {
            this.member_enddate = member_enddate;
        }

        public int getMember_level() {
            return member_level;
        }

        public void setMember_level(int member_level) {
            this.member_level = member_level;
        }

        public String getMember_lv() {
            return member_lv;
        }

        public void setMember_lv(String member_lv) {
            this.member_lv = member_lv;
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

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public int getOverCount() {
            return overCount;
        }

        public void setOverCount(int overCount) {
            this.overCount = overCount;
        }

        public String getRelease_address() {
            return release_address;
        }

        public void setRelease_address(String release_address) {
            this.release_address = release_address;
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

        public String getTask_ImgUrl() {
            return task_ImgUrl;
        }

        public void setTask_ImgUrl(String task_ImgUrl) {
            this.task_ImgUrl = task_ImgUrl;
        }

        public String getTask_Startdate() {
            return task_Startdate;
        }

        public void setTask_Startdate(String task_Startdate) {
            this.task_Startdate = task_Startdate;
        }

        public String getTask_Status_code() {
            return task_Status_code;
        }

        public void setTask_Status_code(String task_Status_code) {
            this.task_Status_code = task_Status_code;
        }

        public String getTask_Time() {
            return task_Time;
        }

        public void setTask_Time(String task_Time) {
            this.task_Time = task_Time;
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

        public int getTask_type() {
            return task_type;
        }

        public void setTask_type(int task_type) {
            this.task_type = task_type;
        }

        public int getTotal_score() {
            return total_score;
        }

        public void setTotal_score(int total_score) {
            this.total_score = total_score;
        }

        public int getUser_level() {
            return user_level;
        }

        public void setUser_level(int user_level) {
            this.user_level = user_level;
        }

        public String getX_value() {
            return x_value;
        }

        public void setX_value(String x_value) {
            this.x_value = x_value;
        }

        public String getY_value() {
            return y_value;
        }

        public void setY_value(String y_value) {
            this.y_value = y_value;
        }

        public List<String> getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(List<String> evaluate) {
            this.evaluate = evaluate;
        }
    }
}
