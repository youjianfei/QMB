package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class Matchshoplistbean {


    /**
     * code : 1
     * data : {"Matching":[{"appellation_name":"LV.2","business_avatar":"3206","business_mobile_no":"15225906922","business_name":"肉夹馍1234","business_no":"60000000049","business_type_id":"1300,1204,1203,1205,1401","client_no":"90000000086","count":"12","evaluate":["专业认真","技术过硬","上门速度快","服务态度好"],"evaluation_star":5,"headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/7aa7625b-7d45-41a3-a36f-27b676e507181539227983246.png","member_level":"0","member_lv":"","overCount":16,"push_register":"140fe1da9e8542c5733","specialty_name":"家庭保洁、手机维修、电脑维修、空调维修、app开发","total_score":"60","x_value":"34.800276","y_value":"113.631177"}],"isShow":"0","task_id":6341}
     * message : 添加数据成功
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
         * Matching : [{"appellation_name":"LV.2","business_avatar":"3206","business_mobile_no":"15225906922","business_name":"肉夹馍1234","business_no":"60000000049","business_type_id":"1300,1204,1203,1205,1401","client_no":"90000000086","count":"12","evaluate":["专业认真","技术过硬","上门速度快","服务态度好"],"evaluation_star":5,"headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/7aa7625b-7d45-41a3-a36f-27b676e507181539227983246.png","member_level":"0","member_lv":"","overCount":16,"push_register":"140fe1da9e8542c5733","specialty_name":"家庭保洁、手机维修、电脑维修、空调维修、app开发","total_score":"60","x_value":"34.800276","y_value":"113.631177"}]
         * isShow : 0
         * task_id : 6341
         */

        private String isShow;
        private int task_id;
        private String order_no;
        private List<MatchingBean> Matching;
        private  String activity_url="";
        public String getIsShow() {
            return isShow;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public List<MatchingBean> getMatching() {
            return Matching;
        }

        public void setMatching(List<MatchingBean> Matching) {
            this.Matching = Matching;
        }

        public String getActivity_url() {
            return activity_url;
        }

        public void setActivity_url(String activity_url) {
            this.activity_url = activity_url;
        }

        public static class MatchingBean {
            /**
             * appellation_name : LV.2
             * business_avatar : 3206
             * business_mobile_no : 15225906922
             * business_name : 肉夹馍1234
             * business_no : 60000000049
             * business_type_id : 1300,1204,1203,1205,1401
             * client_no : 90000000086
             * count : 12
             * evaluate : ["专业认真","技术过硬","上门速度快","服务态度好"]
             * evaluation_star : 5
             * headUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/7aa7625b-7d45-41a3-a36f-27b676e507181539227983246.png
             * member_level : 0
             * member_lv :
             * overCount : 16
             * push_register : 140fe1da9e8542c5733
             * specialty_name : 家庭保洁、手机维修、电脑维修、空调维修、app开发
             * total_score : 60
             * x_value : 34.800276
             * y_value : 113.631177
             */

            private String appellation_name;
            private String business_avatar;
            private String business_mobile_no;
            private String memberImgUrl="";
            private String business_name;
            private String business_no;
            private String business_type_id;
            private String client_no;
            private String count;
            private double evaluation_star;
            private String headUrl;
            private String member_level;
            private String member_lv;
            private int overCount;
            private String push_register;
            private String specialty_name;
            private String total_score;
            private String x_value;
            private String y_value;
            private List<String> evaluate;

            public String getMemberImgUrl() {
                return memberImgUrl;
            }

            public void setMemberImgUrl(String memberImgUrl) {
                this.memberImgUrl = memberImgUrl;
            }

            public String getAppellation_name() {
                return appellation_name;
            }

            public void setAppellation_name(String appellation_name) {
                this.appellation_name = appellation_name;
            }

            public String getBusiness_avatar() {
                return business_avatar;
            }

            public void setBusiness_avatar(String business_avatar) {
                this.business_avatar = business_avatar;
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

            public String getBusiness_type_id() {
                return business_type_id;
            }

            public void setBusiness_type_id(String business_type_id) {
                this.business_type_id = business_type_id;
            }

            public String getClient_no() {
                return client_no;
            }

            public void setClient_no(String client_no) {
                this.client_no = client_no;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public double getEvaluation_star() {
                return evaluation_star;
            }

            public void setEvaluation_star(double evaluation_star) {
                this.evaluation_star = evaluation_star;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getMember_level() {
                return member_level;
            }

            public void setMember_level(String member_level) {
                this.member_level = member_level;
            }

            public String getMember_lv() {
                return member_lv;
            }

            public void setMember_lv(String member_lv) {
                this.member_lv = member_lv;
            }

            public int getOverCount() {
                return overCount;
            }

            public void setOverCount(int overCount) {
                this.overCount = overCount;
            }

            public String getPush_register() {
                return push_register;
            }

            public void setPush_register(String push_register) {
                this.push_register = push_register;
            }

            public String getSpecialty_name() {
                return specialty_name;
            }

            public void setSpecialty_name(String specialty_name) {
                this.specialty_name = specialty_name;
            }

            public String getTotal_score() {
                return total_score;
            }

            public void setTotal_score(String total_score) {
                this.total_score = total_score;
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
}
