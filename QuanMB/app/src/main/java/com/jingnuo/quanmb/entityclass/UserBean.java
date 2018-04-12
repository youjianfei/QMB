package com.jingnuo.quanmb.entityclass;

/**
 * Created by Administrator on 2018/3/30.
 */

public class UserBean {


    /**
     * code : 1
     * data : {"appuser":{"client_no":"","createDate":"2018-04-11 17:20:25","createName":"root@192.168.1.184","mobile_no":"18539931923","nick_name":"123","role":"","status":"","updateDate":"2018-04-12 09:44:15","updateName":"root@192.168.1.184","user_grade":"1","user_id":"34","user_name":"18539931923","user_password":"0ba03e4f53f18469","user_reputation":"100"},"user_token":"8d8a6a616f52159528aa5b2ae722a6a9"}
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
         * appuser : {"client_no":"","createDate":"2018-04-11 17:20:25","createName":"root@192.168.1.184","mobile_no":"18539931923","nick_name":"123","role":"","status":"","updateDate":"2018-04-12 09:44:15","updateName":"root@192.168.1.184","user_grade":"1","user_id":"34","user_name":"18539931923","user_password":"0ba03e4f53f18469","user_reputation":"100"}
         * user_token : 8d8a6a616f52159528aa5b2ae722a6a9
         */

        private AppuserBean appuser;
        private String user_token;

        public AppuserBean getAppuser() {
            return appuser;
        }

        public void setAppuser(AppuserBean appuser) {
            this.appuser = appuser;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public static class AppuserBean {
            /**
             * client_no :
             * createDate : 2018-04-11 17:20:25
             * createName : root@192.168.1.184
             * mobile_no : 18539931923
             * nick_name : 123
             * role :
             * status :
             * updateDate : 2018-04-12 09:44:15
             * updateName : root@192.168.1.184
             * user_grade : 1
             * user_id : 34
             * user_name : 18539931923
             * user_password : 0ba03e4f53f18469
             * user_reputation : 100
             */

            private String client_no;
            private String createDate;
            private String createName;
            private String mobile_no;
            private String nick_name;
            private String role;
            private String status;
            private String updateDate;
            private String updateName;
            private String user_grade;
            private String user_id;
            private String user_name;
            private String user_password;
            private String user_reputation;

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
