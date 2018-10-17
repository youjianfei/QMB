package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class Matchshoplistbean {

    /**
     * code : 1
     * data : {"Matching":[{"appellation_name":"LV.1","business_mobile_no":"13140066007","business_name":"阿里巴巴","business_type_id":"1101,1105,1203,1209,1302,","client_no":"90000000011","headUrl":"https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/business.png","push_register":"13165ffa4e5f360fa3a","specialty_name":"帮预约宠物护理电脑维修开锁修锁擦油烟机","user_reputation":100,"x_value":"34.80030517578125","y_value":"113.63192328559028"}],"task_id":340}
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
         * Matching : [{"appellation_name":"LV.1","business_mobile_no":"13140066007","business_name":"阿里巴巴","business_type_id":"1101,1105,1203,1209,1302,","client_no":"90000000011","headUrl":"https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/business.png","push_register":"13165ffa4e5f360fa3a","specialty_name":"帮预约宠物护理电脑维修开锁修锁擦油烟机","user_reputation":100,"x_value":"34.80030517578125","y_value":"113.63192328559028"}]
         * task_id : 340
         */

        private int task_id;
        private List<MatchingBean> Matching;

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

        public static class MatchingBean {
            /**
             * appellation_name : LV.1
             * business_mobile_no : 13140066007
             * business_name : 阿里巴巴
             * business_type_id : 1101,1105,1203,1209,1302,
             * client_no : 90000000011
             * headUrl : https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/business.png
             * push_register : 13165ffa4e5f360fa3a
             * specialty_name : 帮预约宠物护理电脑维修开锁修锁擦油烟机
             * user_reputation : 100
             * x_value : 34.80030517578125
             * y_value : 113.63192328559028
             */

            private String appellation_name;
            private String business_mobile_no;
            private String business_name;
            private String business_type_id;
            private String client_no;
            private String business_no;
            private String headUrl;
            private String push_register;
            private String specialty_name;
            private String memberImgUrl="";
            private String member_lv="";
            private int user_reputation;
            private int overCount;
            private double evaluation_star;

            private String x_value;
            private String y_value;

            public String getMember_lv() {
                return member_lv;
            }

            public void setMember_lv(String member_lv) {
                this.member_lv = member_lv;
            }

            public String getMemberImgUrl() {
                return memberImgUrl;
            }

            public void setMemberImgUrl(String memberImgUrl) {
                this.memberImgUrl = memberImgUrl;
            }

            public int getOverCount() {
                return overCount;
            }

            public void setOverCount(int overCount) {
                this.overCount = overCount;
            }

            public double getEvaluation_star() {
                return evaluation_star;
            }

            public void setEvaluation_star(double evaluation_star) {
                this.evaluation_star = evaluation_star;
            }

            public String getBusiness_no() {
                return business_no;
            }

            public void setBusiness_no(String business_no) {
                this.business_no = business_no;
            }

            public String getAppellation_name() {
                return appellation_name;
            }

            public void setAppellation_name(String appellation_name) {
                this.appellation_name = appellation_name;
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

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
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

            public int getUser_reputation() {
                return user_reputation;
            }

            public void setUser_reputation(int user_reputation) {
                this.user_reputation = user_reputation;
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
        }
    }
}
