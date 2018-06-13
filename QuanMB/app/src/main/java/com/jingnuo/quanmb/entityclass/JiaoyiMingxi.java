package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class JiaoyiMingxi {

    /**
     * code : 1
     * data : [{"bill_createDate":"2018-06-11 05:30:25","bill_title":"支付佣金","client_no":"90000000046","createDate":"2018-06-11 17:30:25","createName":"root@47.95.254.3","id":53,"order_amoun":25.69,"order_no":"QMB20180611173025081002","pay_method":"QMB","status":1,"type":1,"updateDate":"2018-06-11 17:30:25"},{"bill_createDate":"2018-06-11 05:45:14","bill_title":"支付佣金","client_no":"90000000046","createDate":"2018-06-11 17:45:14","createName":"root@47.95.254.3","id":54,"order_amoun":5,"order_no":"QMB20180611174514010003","pay_method":"QMB","status":1,"type":1,"updateDate":"2018-06-11 17:45:14"},{"bill_createDate":"2018-06-11 05:50:43","bill_title":"支付佣金","client_no":"90000000046","createDate":"2018-06-11 17:50:43","createName":"root@47.95.254.3","id":55,"order_amoun":36.98,"order_no":"QMB20180611175043911004","pay_method":"QMB","status":1,"type":1,"updateDate":"2018-06-11 17:50:43"},{"bill_createDate":"2018-06-11 05:51:51","bill_title":"支付佣金","client_no":"90000000046","createDate":"2018-06-11 17:51:51","createName":"root@47.95.254.3","id":56,"order_amoun":5,"order_no":"QMB20180611175151368005","pay_method":"QMB","status":1,"type":1,"updateDate":"2018-06-11 17:51:51"},{"bill_createDate":"2018-06-12 09:24:48","bill_title":"支付佣金","client_no":"90000000046","createDate":"2018-06-12 09:24:48","createName":"root@47.95.254.3","id":57,"order_amoun":25,"order_no":"QMB20180612092448264006","pay_method":"QMB","status":1,"type":1,"updateDate":"2018-06-12 09:24:48"},{"bill_createDate":"2018-06-12 03:07:12","bill_title":"支付佣金","client_no":"90000000046","createDate":"2018-06-12 15:07:12","createName":"root@47.95.254.3","id":69,"order_amoun":99,"order_no":"QMB20180612150712220018","pay_method":"QMB","status":1,"type":1,"updateDate":"2018-06-12 15:07:12"}]
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
         * bill_createDate : 2018-06-11 05:30:25
         * bill_title : 支付佣金
         * client_no : 90000000046
         * createDate : 2018-06-11 17:30:25
         * createName : root@47.95.254.3
         * id : 53
         * order_amoun : 25.69
         * order_no : QMB20180611173025081002
         * pay_method : QMB
         * status : 1
         * type : 1
         * updateDate : 2018-06-11 17:30:25
         */

        private String bill_createDate;
        private String bill_title;
        private String client_no;
        private String createDate;
        private String createName;
        private int id;
        private double order_amoun;
        private String order_no;
        private String pay_method;
        private int status;
        private int type;
        private String updateDate;

        public String getBill_createDate() {
            return bill_createDate;
        }

        public void setBill_createDate(String bill_createDate) {
            this.bill_createDate = bill_createDate;
        }

        public String getBill_title() {
            return bill_title;
        }

        public void setBill_title(String bill_title) {
            this.bill_title = bill_title;
        }

        public String getClient_no() {
            return client_no;
        }

        public void setClient_no(String client_no) {
            this.client_no = client_no;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getOrder_amoun() {
            return order_amoun;
        }

        public void setOrder_amoun(double order_amoun) {
            this.order_amoun = order_amoun;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getPay_method() {
            return pay_method;
        }

        public void setPay_method(String pay_method) {
            this.pay_method = pay_method;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    }
}
