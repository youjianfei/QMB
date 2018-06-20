package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class TuiguangbiTaocanBean {

    /**
     * code : 1
     * data : [{"discount":"9.9","id":1,"price":99,"spread_b":100},{"discount":"9.5","id":2,"price":190,"spread_b":200},{"discount":"9.0","id":3,"price":270,"spread_b":300},{"discount":"8.0","id":4,"price":400,"spread_b":500},{"discount":"7.0","id":5,"price":700,"spread_b":1000},{"discount":"6.0","id":6,"price":3000,"spread_b":5000}]
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
         * discount : 9.9
         * id : 1
         * price : 99.0
         * spread_b : 100
         */

        private String discount;
        private int id;
        private double price;
        private int spread_b;

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
