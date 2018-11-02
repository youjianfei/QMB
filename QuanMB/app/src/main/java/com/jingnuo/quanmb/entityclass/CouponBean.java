package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by 飞 on 2018/11/1.
 */

public class CouponBean {

    /**
     * code : 1
     * data : [{"amount":50,"bind":"102","client_no":"90000000002","condition":100,"coupon_code":"SCDFFFF","description":"仅维修可使用","enddate":"2018-11-29 00:00:00","startdate":"2018-10-29 00:00:00","status":"00","type":2},{"amount":30,"bind":"1203","client_no":"90000000002","condition":100,"coupon_code":"SCDFFFE","description":"仅电脑维修可用","enddate":"2018-11-02 15:10:22","startdate":"2018-11-01 15:10:19","status":"00","type":3},{"amount":30,"bind":"1203","client_no":"90000000002","condition":100,"coupon_code":"SCDFFFE","description":"仅电脑维修可用","enddate":"2018-11-02 15:10:22","startdate":"2018-11-01 15:10:19","status":"00","type":3},{"amount":10,"bind":"60000000046","client_no":"90000000002","condition":50,"coupon_code":"SCDFFFD","description":"仅强强科技可用","enddate":"2018-11-23 15:18:16","startdate":"2018-11-01 15:18:13","status":"00","type":4},{"amount":10,"bind":"60000000046","client_no":"90000000002","condition":50,"coupon_code":"SCDFFFD","description":"仅强强科技可用","enddate":"2018-11-23 15:18:16","startdate":"2018-11-01 15:18:13","status":"00","type":4}]
     * message : 获取列表成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * amount : 50.0
         * bind : 102
         * client_no : 90000000002
         * condition : 100.0
         * coupon_code : SCDFFFF
         * description : 仅维修可使用
         * enddate : 2018-11-29 00:00:00
         * startdate : 2018-10-29 00:00:00
         * status : 00
         * type : 2
         */

        private double amount;
        private String bind;
        private String client_no;
        private double condition;
        private String coupon_code;
        private String descriptionA;
        private String enddate;
        private String startdate;
        private String status;
        private int type;
        private int usable;

        public int getUsable() {
            return usable;
        }

        public void setUsable(int usable) {
            this.usable = usable;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getBind() {
            return bind;
        }

        public void setBind(String bind) {
            this.bind = bind;
        }

        public String getClient_no() {
            return client_no;
        }

        public void setClient_no(String client_no) {
            this.client_no = client_no;
        }

        public double getCondition() {
            return condition;
        }

        public void setCondition(double condition) {
            this.condition = condition;
        }

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public String getDescriptionA() {
            return descriptionA;
        }

        public void setDescriptionA(String description) {
            this.descriptionA = descriptionA;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
