package com.jingnuo.quanmb.entityclass;

public class WoderenzhengBean {

    /**
     * code : 1
     * data : {"business_auth_status":1,"business_avatar_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/80bd2a3e-7984-4425-9eaa-839872d5533e201572710478.jpg.jpg","business_name":"awdawd","helper_auth_status":0}
     * message : 查看认证信息成功
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
         * business_auth_status : 1
         * business_avatar_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/80bd2a3e-7984-4425-9eaa-839872d5533e201572710478.jpg.jpg
         * business_name : awdawd
         * helper_auth_status : 0
         */

        private int business_auth_status;
        private String business_avatar_url;
        private String helper_avatar_url;
        private String business_name;
        private String helper_cer_no;
        private int helper_auth_status;

        public String getHelper_avatar_url() {
            return helper_avatar_url;
        }

        public void setHelper_avatar_url(String helper_avatar_url) {
            this.helper_avatar_url = helper_avatar_url;
        }

        public String getHelper_cer_no() {
            return helper_cer_no;
        }

        public void setHelper_cer_no(String helper_cer_no) {
            this.helper_cer_no = helper_cer_no;
        }

        public int getBusiness_auth_status() {
            return business_auth_status;
        }

        public void setBusiness_auth_status(int business_auth_status) {
            this.business_auth_status = business_auth_status;
        }

        public String getBusiness_avatar_url() {
            return business_avatar_url;
        }

        public void setBusiness_avatar_url(String business_avatar_url) {
            this.business_avatar_url = business_avatar_url;
        }

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public int getHelper_auth_status() {
            return helper_auth_status;
        }

        public void setHelper_auth_status(int helper_auth_status) {
            this.helper_auth_status = helper_auth_status;
        }
    }
}
