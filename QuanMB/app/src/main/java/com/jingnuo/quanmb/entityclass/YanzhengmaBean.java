package com.jingnuo.quanmb.entityclass;

/**
 * Created by 飞 on 2018/11/19.
 */

public class YanzhengmaBean {

    /**
     * code : 1
     * data : {"business_status":0,"helper_status":0,"registed":"01"}
     * message : 验证码发送成功
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
         * business_status : 0
         * helper_status : 0
         * registed : 01
         */

        private int business_status;
        private int helper_status;
        private String registed;

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

        public String getRegisted() {
            return registed;
        }

        public void setRegisted(String registed) {
            this.registed = registed;
        }
    }
}
