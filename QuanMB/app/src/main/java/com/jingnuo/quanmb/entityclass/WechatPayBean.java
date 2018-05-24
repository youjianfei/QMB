package com.jingnuo.quanmb.entityclass;

public class WechatPayBean {

    /**
     * code : 1
     * data : {"appid":"wx1589c6a947d1f803","device_info":"WEB","mch_id":"1501850171","nonce_str":"EdWoIPxJ8eAbQ5Cv","prepay_id":"wx24093953194206ab07c10df80632599443","result_code":"SUCCESS","return_code":"SUCCESS","return_msg":"OK","sign":"4D5CB97FAF6C0F656E28132DA7C6AC87","timestamp":"1527125993","trade_type":"APP"}
     * msg : 统一下单成功！
     */

    private int code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * appid : wx1589c6a947d1f803
         * device_info : WEB
         * mch_id : 1501850171
         * nonce_str : EdWoIPxJ8eAbQ5Cv
         * prepay_id : wx24093953194206ab07c10df80632599443
         * result_code : SUCCESS
         * return_code : SUCCESS
         * return_msg : OK
         * sign : 4D5CB97FAF6C0F656E28132DA7C6AC87
         * timestamp : 1527125993
         * trade_type : APP
         */

        private String appid;
        private String device_info;
        private String mch_id;
        private String nonce_str;
        private String prepay_id;
        private String result_code;
        private String return_code;
        private String return_msg;
        private String sign;
        private String timestamp;
        private String trade_type;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getDevice_info() {
            return device_info;
        }

        public void setDevice_info(String device_info) {
            this.device_info = device_info;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }
    }
}
