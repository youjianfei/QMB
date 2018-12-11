package com.jingnuo.quanmb.entityclass;

/**
 * Created by 飞 on 2018/12/11.
 */

public class YaoqinghaoyouBean {

    /**
     * code : 1
     * data : {"amount":"0","bonus":"0.00","is_order":"0"}
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
         * amount : 0
         * bonus : 0.00
         * is_order : 0
         */

        private String amount;
        private String bonus;
        private String is_order;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public String getIs_order() {
            return is_order;
        }

        public void setIs_order(String is_order) {
            this.is_order = is_order;
        }
    }
}
