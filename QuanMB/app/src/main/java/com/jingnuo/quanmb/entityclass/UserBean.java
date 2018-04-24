package com.jingnuo.quanmb.entityclass;

/**
 * Created by Administrator on 2018/3/30.
 */

public class UserBean {


    /**
     * code : 1
     * data : {"appuser":{"age":9,"agreement_status":"y","birthday":"2018-04-02 00:00:00","cer_no":"410323","cer_type":"1","client_no":"90000000009","createDate":"2018-04-23 09:45:07","createName":"root@192.168.1.184","mobile_no":"18539931923","name":"小猪佩奇","nick_name":"333","role":"1","sex":"0","status":"","updateDate":"2018-04-24 18:13:53","updateName":"root@192.168.1.184","user_grade":"1","user_id":"1000000006","user_name":"18539931923","user_password":"565491d704013245","user_reputation":"100"},"business_status":1,"helper_status":0,"user_token":"6895de7d97e204754b18a50efc37d351"}
     * message : 登录成功
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
         * appuser : {"age":9,"agreement_status":"y","birthday":"2018-04-02 00:00:00","cer_no":"410323","cer_type":"1","client_no":"90000000009","createDate":"2018-04-23 09:45:07","createName":"root@192.168.1.184","mobile_no":"18539931923","name":"小猪佩奇","nick_name":"333","role":"1","sex":"0","status":"","updateDate":"2018-04-24 18:13:53","updateName":"root@192.168.1.184","user_grade":"1","user_id":"1000000006","user_name":"18539931923","user_password":"565491d704013245","user_reputation":"100"}
         * business_status : 1
         * helper_status : 0
         * user_token : 6895de7d97e204754b18a50efc37d351
         */

        private AppuserBean appuser;
        private int business_status;
        private int helper_status;
        private String user_token;

        public AppuserBean getAppuser() {
            return appuser;
        }

        public void setAppuser(AppuserBean appuser) {
            this.appuser = appuser;
        }

        public int getBusiness_status() {
            return business_status;
        }

        public void setBusiness_status(int business_status) {
            this.business_status = business_status;
        }

        public int getHelper_status() {
            return helper_status;
        }

        public void setHelper_status(int helper_status) {
            this.helper_status = helper_status;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public static class AppuserBean {
            /**
             * age : 9
             * agreement_status : y
             * birthday : 2018-04-02 00:00:00
             * cer_no : 410323
             * cer_type : 1
             * client_no : 90000000009
             * createDate : 2018-04-23 09:45:07
             * createName : root@192.168.1.184
             * mobile_no : 18539931923
             * name : 小猪佩奇
             * nick_name : 333
             * role : 1
             * sex : 0
             * status :
             * updateDate : 2018-04-24 18:13:53
             * updateName : root@192.168.1.184
             * user_grade : 1
             * user_id : 1000000006
             * user_name : 18539931923
             * user_password : 565491d704013245
             * user_reputation : 100
             */

            private int age;
            private String agreement_status;
            private String birthday;
            private String cer_no;
            private String cer_type;
            private String client_no;
            private String createDate;
            private String createName;
            private String mobile_no;
            private String name;
            private String nick_name;
            private String role;
            private String sex;
            private String status;
            private String updateDate;
            private String updateName;
            private String user_grade;
            private String user_id;
            private String user_name;
            private String user_password;
            private String user_reputation;

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getAgreement_status() {
                return agreement_status;
            }

            public void setAgreement_status(String agreement_status) {
                this.agreement_status = agreement_status;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getCer_no() {
                return cer_no;
            }

            public void setCer_no(String cer_no) {
                this.cer_no = cer_no;
            }

            public String getCer_type() {
                return cer_type;
            }

            public void setCer_type(String cer_type) {
                this.cer_type = cer_type;
            }

            public String getClient_no() {
                return client_no;
            }

            public void setClient_no(String client_no) {
                this.client_no = client_no;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
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

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getUpdateName() {
                return updateName;
            }

            public void setUpdateName(String updateName) {
                this.updateName = updateName;
            }

            public String getUser_grade() {
                return user_grade;
            }

            public void setUser_grade(String user_grade) {
                this.user_grade = user_grade;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_password() {
                return user_password;
            }

            public void setUser_password(String user_password) {
                this.user_password = user_password;
            }

            public String getUser_reputation() {
                return user_reputation;
            }

            public void setUser_reputation(String user_reputation) {
                this.user_reputation = user_reputation;
            }
        }
    }
}
