package com.jingnuo.quanmb.entityclass;

/**
 * Created by 飞 on 2018/11/14.
 */

public class OrderThinkBean {


    /**
     * code : 1
     * data : {"actually_amount":"6.6","amount":"0","business_mobile_no":"18539931923","business_name":"挖矿大师","count":43,"evaluation_star":"5.0","order_amount":6.6,"total_score":"215"}
     * message : 操作成功
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
         * actually_amount : 6.6
         * amount : 0
         * business_mobile_no : 18539931923
         * business_name : 挖矿大师
         * count : 43
         * evaluation_star : 5.0
         * order_amount : 6.6
         * total_score : 215
         */

        private String actually_amount;
        private String amount;
        private String business_mobile_no;
        private String business_name;
        private int count;
        private String evaluation_star;
        private double order_amount;
        private String total_score;

        public String getActually_amount() {
            return actually_amount;
        }

        public void setActually_amount(String actually_amount) {
            this.actually_amount = actually_amount;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getEvaluation_star() {
            return evaluation_star;
        }

        public void setEvaluation_star(String evaluation_star) {
            this.evaluation_star = evaluation_star;
        }

        public double getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(double order_amount) {
            this.order_amount = order_amount;
        }

        public String getTotal_score() {
            return total_score;
        }

        public void setTotal_score(String total_score) {
            this.total_score = total_score;
        }
    }
}
