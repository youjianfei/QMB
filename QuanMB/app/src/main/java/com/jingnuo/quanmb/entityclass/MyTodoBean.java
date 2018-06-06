package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class MyTodoBean {


    /**
     * code : 1
     * data : {"list":[{"order_amount":500,"order_no":"QMZ180503100000072","order_status":"已关闭","task_description":"小米新品发布会","task_name":"我需要预约后天的新品发布会","task_order_id":1},{"order_amount":150,"order_no":"QMZ180503100000073","order_status":"已关闭","task_description":"代购一顿炸药","task_name":"有可以帮忙代购的小伙伴吗","task_order_id":2},{"order_amount":100,"order_no":"QMZ180503100000074","order_status":"已完成","task_description":"帮我把烧饼送给有需要的人。","task_name":"帮送一个烧饼","task_order_id":3},{"order_amount":10009,"order_no":"QMB180504100000087","order_status":"待完成","task_description":"我需要一个人帮我","task_name":"我需要一个人帮我","task_order_id":15},{"order_amount":10,"order_no":"QMB180508100000090","order_status":"待完成","task_description":"帮我到邮政邮寄快递。","task_name":"我需要一名快递员","task_order_id":18},{"order_amount":5,"order_no":"QMB180508100000091","order_status":"待完成","task_description":"wqeewq","task_name":"qweewq","task_order_id":19},{"order_amount":36,"order_no":"QMB180508100000092","order_status":"待完成","task_description":"脱敏辛苦呜呜呜呜","task_name":"我需要一个人","task_order_id":20},{"order_amount":66,"order_no":"QMB180508100000093","order_status":"待完成","task_description":"哦了咯牙了呜呜呜","task_name":"一下继续需要","task_order_id":21}]}
     * message : 查看帮手订单成功
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * order_amount : 500
             * order_no : QMZ180503100000072
             * order_status : 已关闭
             * task_description : 小米新品发布会
             * task_name : 我需要预约后天的新品发布会
             * task_order_id : 1
             */

            private double order_amount;
            private String order_no;
            private String order_status;
            private String task_description;
            private String task_name;
            private int task_order_id;
            private String task_id;
            private String createDate;
            private String order_enddate;
            private String specialty_name;

            public String getSpecialty_name() {
                return specialty_name;
            }

            public void setSpecialty_name(String specialty_name) {
                this.specialty_name = specialty_name;
            }

            public String getOrder_enddate() {
                return order_enddate;
            }

            public void setOrder_enddate(String order_enddate) {
                this.order_enddate = order_enddate;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getTask_id() {
                return task_id;
            }

            public void setTask_id(String task_id) {
                this.task_id = task_id;
            }

            public double getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(double order_amount) {
                this.order_amount = order_amount;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getTask_description() {
                return task_description;
            }

            public void setTask_description(String task_description) {
                this.task_description = task_description;
            }

            public String getTask_name() {
                return task_name;
            }

            public void setTask_name(String task_name) {
                this.task_name = task_name;
            }

            public int getTask_order_id() {
                return task_order_id;
            }

            public void setTask_order_id(int task_order_id) {
                this.task_order_id = task_order_id;
            }
        }
    }
}
