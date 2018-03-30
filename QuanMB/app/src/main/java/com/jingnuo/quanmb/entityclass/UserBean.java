package com.jingnuo.quanmb.entityclass;

/**
 * Created by Administrator on 2018/3/30.
 */

public class UserBean {


    /**
     * code : 1
     * data : {"user_token":"aeeb6dbe819bc356325ea07e54c9f3ec"}
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
         * user_token : aeeb6dbe819bc356325ea07e54c9f3ec
         */

        private String user_token;

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }
    }
}
