package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class DealMessageBean {


    /**
     * code : 1
     * data : [{"Id":17615,"binding_id":"6770","content":"您发布的任务已被商家:强强科技完成啦！赶快去确认吧","createDate":"2018-12-12 17:21:11","createName":"root@47.95.254.3","order_no":"QMB181211100001499","receive_client_no":"90000000002","send_client_no":"60000000046","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-12-12 17:21:11"},{"Id":17569,"binding_id":"6852","content":"您发布的任务已被商家:月嫂大师完成啦！赶快去确认吧","createDate":"2018-12-11 22:01:27","createName":"root@47.95.254.3","order_no":"QMB181211100001581","receive_client_no":"90000000002","send_client_no":"60000000061","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-12-11 22:01:27"},{"Id":17565,"binding_id":"6851","content":"您发布的任务已被商家:月嫂大师完成啦！赶快去确认吧","createDate":"2018-12-11 22:00:47","createName":"root@47.95.254.3","order_no":"QMB181211100001580","receive_client_no":"90000000002","send_client_no":"60000000061","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-12-11 22:00:47"},{"Id":17563,"binding_id":"6850","content":"您发布的任务已被商家:月嫂大师完成啦！赶快去确认吧","createDate":"2018-12-11 21:59:20","createName":"root@47.95.254.3","order_no":"QMB181211100001579","receive_client_no":"90000000002","send_client_no":"60000000061","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-12-11 21:59:20"},{"Id":17559,"binding_id":"6848","content":"您发布的任务已被商家:月嫂大师完成啦！赶快去确认吧","createDate":"2018-12-11 21:52:08","createName":"root@47.95.254.3","order_no":"QMB181211100001577","receive_client_no":"90000000002","send_client_no":"60000000061","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-12-11 21:52:08"},{"Id":17551,"binding_id":"6845","content":"您发布的任务已被商家:月嫂大师完成啦！赶快去确认吧","createDate":"2018-12-11 21:46:13","createName":"root@47.95.254.3","order_no":"QMB181211100001574","receive_client_no":"90000000002","send_client_no":"60000000061","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-12-11 21:46:13"},{"Id":17549,"binding_id":"6844","content":"您发布的任务已被商家:月嫂大师完成啦！赶快去确认吧","createDate":"2018-12-11 21:44:59","createName":"root@47.95.254.3","order_no":"QMB181211100001573","receive_client_no":"90000000002","send_client_no":"60000000061","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-12-11 21:44:59"},{"Id":17524,"binding_id":"6838","content":"您发布的任务已被商家:月嫂大师完成啦！赶快去确认吧","createDate":"2018-12-11 21:26:29","createName":"root@47.95.254.3","order_no":"QMB181211100001567","receive_client_no":"90000000002","send_client_no":"60000000061","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-12-11 21:26:29"}]
     * msg : 获取消息列表成功
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
         * Id : 17615
         * binding_id : 6770
         * content : 您发布的任务已被商家:强强科技完成啦！赶快去确认吧
         * createDate : 2018-12-12 17:21:11
         * createName : root@47.95.254.3
         * order_no : QMB181211100001499
         * receive_client_no : 90000000002
         * send_client_no : 60000000046
         * status : 1
         * task_Status_code : 06
         * title : 您发布的任务已被商家完成啦!
         * type : 3
         * updateDate : 2018-12-12 17:21:11
         */

        private int Id;
        private String binding_id;
        private String content;
        private String createDate;
        private String createName;
        private String order_no;
        private String receive_client_no;
        private String send_client_no;
        private String status;
        private String task_Status_code;
        private String title;
        private String type;
        private String updateDate;
        private String click_url="";
        private String img_url="";
        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getClick_url() {
            return click_url;
        }

        public void setClick_url(String click_url) {
            this.click_url = click_url;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getBinding_id() {
            return binding_id;
        }

        public void setBinding_id(String binding_id) {
            this.binding_id = binding_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getReceive_client_no() {
            return receive_client_no;
        }

        public void setReceive_client_no(String receive_client_no) {
            this.receive_client_no = receive_client_no;
        }

        public String getSend_client_no() {
            return send_client_no;
        }

        public void setSend_client_no(String send_client_no) {
            this.send_client_no = send_client_no;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTask_Status_code() {
            return task_Status_code;
        }

        public void setTask_Status_code(String task_Status_code) {
            this.task_Status_code = task_Status_code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
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
