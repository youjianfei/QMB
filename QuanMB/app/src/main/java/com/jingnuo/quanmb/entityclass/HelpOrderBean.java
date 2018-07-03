package com.jingnuo.quanmb.entityclass;

public class HelpOrderBean {

    /**
     * code : 1
     * data : {"detail":{"Avatar_img_id":"195","client_no":"2147483680","detailed_address":"升龙汇金","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/2785a352-30d4-46ef-928a-4366ac6ea734hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525932019&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=nQ5PKfl%2BBe2eDADVUuKY%2B2Glti0%3D","mobile_no":"110","nick_name":"中单亚索  不给就送","order_amount":500,"order_no":"QMZ180503100000072","order_status":"01","order_status_name":"已关闭","task_StartDate":"2018-05-03 17:30:16","task_description":"小米新品发布会","task_id":5,"task_name":"我需要预约后天的新品发布会"}}
     * message : 查看订单详情成功
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
         * detail : {"Avatar_img_id":"195","client_no":"2147483680","detailed_address":"升龙汇金","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/2785a352-30d4-46ef-928a-4366ac6ea734hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525932019&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=nQ5PKfl%2BBe2eDADVUuKY%2B2Glti0%3D","mobile_no":"110","nick_name":"中单亚索  不给就送","order_amount":500,"order_no":"QMZ180503100000072","order_status":"01","order_status_name":"已关闭","task_StartDate":"2018-05-03 17:30:16","task_description":"小米新品发布会","task_id":5,"task_name":"我需要预约后天的新品发布会"}
         */

        private DetailBean detail;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * Avatar_img_id : 195
             * client_no : 2147483680
             * detailed_address : 升龙汇金
             * headUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/2785a352-30d4-46ef-928a-4366ac6ea734hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525932019&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=nQ5PKfl%2BBe2eDADVUuKY%2B2Glti0%3D
             * mobile_no : 110
             * nick_name : 中单亚索  不给就送
             * order_amount : 500
             * order_no : QMZ180503100000072
             * order_status : 01
             * order_status_name : 已关闭
             * task_StartDate : 2018-05-03 17:30:16
             * task_description : 小米新品发布会
             * task_id : 5
             * task_name : 我需要预约后天的新品发布会
             */

            private String Avatar_img_id;
            private String client_no;
            private String detailed_address;
            private String headUrl;
            private String mobile_no;
            private String nick_name;
            private double order_amount;
            private String order_no;
            private String order_status;
            private String task_StartDate;
            private String task_description;
            private int task_id;
            private String task_name;
            private String task_EndDate;
            private String order_enddate;
            private String task_Img_Url;

            public String getTask_Img_Url() {
                return task_Img_Url;
            }

            public void setTask_Img_Url(String task_Img_Url) {
                this.task_Img_Url = task_Img_Url;
            }

            public String getOrder_enddate() {
                return order_enddate;
            }

            public void setOrder_enddate(String order_enddate) {
                this.order_enddate = order_enddate;
            }

            public String getTask_EndDate() {
                return task_EndDate;
            }

            public void setTask_EndDate(String task_EndDate) {
                this.task_EndDate = task_EndDate;
            }

            public String getAvatar_img_id() {
                return Avatar_img_id;
            }

            public void setAvatar_img_id(String Avatar_img_id) {
                this.Avatar_img_id = Avatar_img_id;
            }

            public String getClient_no() {
                return client_no;
            }

            public void setClient_no(String client_no) {
                this.client_no = client_no;
            }

            public String getDetailed_address() {
                return detailed_address;
            }

            public void setDetailed_address(String detailed_address) {
                this.detailed_address = detailed_address;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getMobile_no() {
                return mobile_no;
            }

            public void setMobile_no(String mobile_no) {
                this.mobile_no = mobile_no;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
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

            public String getTask_StartDate() {
                return task_StartDate;
            }

            public void setTask_StartDate(String task_StartDate) {
                this.task_StartDate = task_StartDate;
            }

            public String getTask_description() {
                return task_description;
            }

            public void setTask_description(String task_description) {
                this.task_description = task_description;
            }

            public int getTask_id() {
                return task_id;
            }

            public void setTask_id(int task_id) {
                this.task_id = task_id;
            }

            public String getTask_name() {
                return task_name;
            }

            public void setTask_name(String task_name) {
                this.task_name = task_name;
            }
        }
    }
}
