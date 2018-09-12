package com.jingnuo.quanmb.entityclass;

/**
 * Created by Administrator on 2018/3/30.
 */

public class UserBean {


    /**
     * code : 1
     * data : {"appuser":{"avatar_img_id":"6","client_no":"2147483667","createDate":"2018-05-02 16:23:43","createName":"root@47.95.254.3","mobile_no":"18539931923","nick_name":"aaaaa","role":"","status":"","updateDate":"2018-05-03 11:44:02","updateName":"root@115.57.138.146","user_grade":"1","user_id":"6","user_name":"18539931923","user_password":"565491d704013245","user_reputation":"100"},"business_status":0,"helper_status":0,"img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/touxiang.png?Expires=1525405573&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=%2FLmSKaDGmoQp8LrbmUnKMeYXLtk%3D","user_token":"22c7c14a309c4245bb414e563183729a"}
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
         * appuser : {"avatar_img_id":"6","client_no":"2147483667","createDate":"2018-05-02 16:23:43","createName":"root@47.95.254.3","mobile_no":"18539931923","nick_name":"aaaaa","role":"","status":"","updateDate":"2018-05-03 11:44:02","updateName":"root@115.57.138.146","user_grade":"1","user_id":"6","user_name":"18539931923","user_password":"565491d704013245","user_reputation":"100"}
         * business_status : 0
         * helper_status : 0
         * img_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/touxiang.png?Expires=1525405573&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=%2FLmSKaDGmoQp8LrbmUnKMeYXLtk%3D
         * user_token : 22c7c14a309c4245bb414e563183729a
         */

        private AppuserBean appuser;
        private int business_status=0;
        private int helper_status=0;
        private String img_url;
        private String user_token;
        private String appellation_name;
        private String iconImgUrl;

        public String getAppellation_name() {
            return appellation_name;
        }

        public void setAppellation_name(String appellation_name) {
            this.appellation_name = appellation_name;
        }

        public String getIconImgUrl() {
            return iconImgUrl;
        }

        public void setIconImgUrl(String iconImgUrl) {
            this.iconImgUrl = iconImgUrl;
        }

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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public static class AppuserBean {
            /**
             * avatar_img_id : 6
             * client_no : 2147483667
             * createDate : 2018-05-02 16:23:43
             * createName : root@47.95.254.3
             * mobile_no : 18539931923
             * nick_name : aaaaa
             * role :
             * status :
             * updateDate : 2018-05-03 11:44:02
             * updateName : root@115.57.138.146
             * user_grade : 1
             * user_id : 6
             * user_name : 18539931923
             * user_password : 565491d704013245
             * user_reputation : 100
             */

            private String avatar_img_id;
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
            private String balance;
            private String push_register;
            private String security_code;
            private String community_code="";
            private String community_name="";
            private String rongCloud_token="";

            public String getRongCloud_token() {
                return rongCloud_token;
            }

            public void setRongCloud_token(String rongCloud_token) {
                this.rongCloud_token = rongCloud_token;
            }

            public String getCommunity_name() {
                return community_name;
            }

            public void setCommunity_name(String community_name) {
                this.community_name = community_name;
            }

            public String getCommunity_code() {
                return community_code;
            }

            public void setCommunity_code(String community_code) {
                this.community_code = community_code;
            }

            public String getPush_register() {
                return push_register;
            }

            public void setPush_register(String push_register) {
                this.push_register = push_register;
            }

            public String getSecurity_code() {
                return security_code;
            }

            public void setSecurity_code(String security_code) {
                this.security_code = security_code;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getAvatar_img_id() {
                return avatar_img_id;
            }

            public void setAvatar_img_id(String avatar_img_id) {
                this.avatar_img_id = avatar_img_id;
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
