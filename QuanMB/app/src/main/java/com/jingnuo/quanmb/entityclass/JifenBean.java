package com.jingnuo.quanmb.entityclass;

/**
 * Created by 飞 on 2018/12/29.
 */

public class JifenBean {

    /**
     * code : 1
     * data : {"integral":300}
     * message : 查询用户邀请信息成功
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
         * integral : 300.0
         */

        private int integral;

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }
    }
}
