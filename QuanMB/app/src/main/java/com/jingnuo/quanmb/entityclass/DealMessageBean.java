package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class DealMessageBean {


    /**
     * code : 1
     * data : [{"Id":16594,"binding_id":"5757","content":"您发布的任务已被商家:挖矿大师完成啦！赶快去确认吧","createDate":"2018-11-15 17:19:33","createName":"root@47.95.254.3","order_no":"QMB181115100000901","receive_client_no":"90000000002","send_client_no":"60000000040","status":"1","task_Status_code":"05","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-11-15 17:19:33"},{"Id":16582,"binding_id":"5754","content":"您发布的任务已被商家:强强科技完成啦！赶快去确认吧","createDate":"2018-11-15 10:04:37","createName":"root@47.95.254.3","order_no":"QMB181115100000893","receive_client_no":"90000000002","send_client_no":"60000000046","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-11-15 10:04:37"},{"Id":16580,"binding_id":"5753","content":"您发布的任务已被商家:挖矿大师完成啦！赶快去确认吧","createDate":"2018-11-15 09:30:17","createName":"root@47.95.254.3","order_no":"QMB181115100000892","receive_client_no":"90000000002","send_client_no":"60000000040","status":"1","task_Status_code":"05","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-11-15 09:30:17"},{"Id":16563,"binding_id":"5748","content":"您发布的任务已被商家:挖矿大师完成啦！赶快去确认吧","createDate":"2018-11-14 19:15:54","createName":"root@47.95.254.3","order_no":"QMB181114100000888","receive_client_no":"90000000002","send_client_no":"60000000040","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-11-14 19:15:54"},{"Id":16552,"binding_id":"5745","content":"您发布的任务已被商家:挖矿大师完成啦！赶快去确认吧","createDate":"2018-11-14 18:04:52","createName":"root@47.95.254.3","order_no":"QMB181114100000886","receive_client_no":"90000000002","send_client_no":"60000000040","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-11-14 18:04:52"},{"Id":16551,"binding_id":"5746","content":"您发布的任务已被商家:挖矿大师完成啦！赶快去确认吧","createDate":"2018-11-14 18:04:45","createName":"root@47.95.254.3","order_no":"QMB181114100000887","receive_client_no":"90000000002","send_client_no":"60000000040","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-11-14 18:04:45"},{"Id":16550,"binding_id":"5592","content":"您发布的任务已被商家:强强科技完成啦！赶快去确认吧","createDate":"2018-11-14 18:04:12","createName":"root@47.95.254.3","order_no":"QMB181113100000834","receive_client_no":"90000000002","send_client_no":"60000000046","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-11-14 18:04:12"},{"Id":16549,"binding_id":"5743","content":"您发布的任务已被商家:强强科技完成啦！赶快去确认吧","createDate":"2018-11-14 18:04:04","createName":"root@47.95.254.3","order_no":"QMB181114100000884","receive_client_no":"90000000002","send_client_no":"60000000046","status":"1","task_Status_code":"06","title":"您发布的任务已被商家完成啦!","type":"3","updateDate":"2018-11-14 18:04:04"}]
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
         * Id : 16594
         * binding_id : 5757
         * content : 您发布的任务已被商家:挖矿大师完成啦！赶快去确认吧
         * createDate : 2018-11-15 17:19:33
         * createName : root@47.95.254.3
         * order_no : QMB181115100000901
         * receive_client_no : 90000000002
         * send_client_no : 60000000040
         * status : 1
         * task_Status_code : 05
         * title : 您发布的任务已被商家完成啦!
         * type : 3
         * updateDate : 2018-11-15 17:19:33
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

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
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
