package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class HuiyuanTaocanBean {

    /**
     * code : 1
     * data : [{"member_id":3,"member_level":500,"package_name":"三个月会员","price":500,"spread_b":100},{"member_id":4,"member_level":800,"package_name":"六个月会员","price":800,"spread_b":300},{"member_id":1,"member_level":1000,"package_name":"一年会员","price":1000,"spread_b":500},{"member_id":2,"member_level":2200,"package_name":"两年会员","price":1800,"spread_b":1200},{"member_id":5,"member_level":3000,"package_name":"三年会员","price":2600,"spread_b":1800},{"member_id":6,"member_level":5000,"package_name":"五年会员","price":3500,"spread_b":2500}]
     * msg : 获取成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * member_id : 3
         * member_level : 500
         * package_name : 三个月会员
         * price : 500.0
         * spread_b : 100
         */

        private int member_id;
        private int member_level;
        private String package_name;
        private double price;
        private int spread_b;
        private int months;

        public int getMonths() {
            return months;
        }

        public void setMonths(int months) {
            this.months = months;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getMember_level() {
            return member_level;
        }

        public void setMember_level(int member_level) {
            this.member_level = member_level;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getSpread_b() {
            return spread_b;
        }

        public void setSpread_b(int spread_b) {
            this.spread_b = spread_b;
        }
    }
}
