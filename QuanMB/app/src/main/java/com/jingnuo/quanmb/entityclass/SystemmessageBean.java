package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class SystemmessageBean
{


    /**
     * code : 1
     * data : [{"Id":17558,"binding_id":"","click_url":"","content":"任务发起人:事与愿违已确认完成你的订单","createDate":"2018-12-11 21:49:03","createName":"root@47.95.254.3","img_url":"","push_date":"","receive_client_no":"90000000002","send_client_no":"90000000116","status":"1","title":"奖励金到账啦!","type":"1","updateDate":"2018-12-11 21:49:03"},{"Id":17555,"binding_id":"","click_url":"","content":"任务发起人:事与愿违已确认完成你的订单","createDate":"2018-12-11 21:46:53","createName":"root@47.95.254.3","img_url":"","push_date":"","receive_client_no":"90000000002","send_client_no":"90000000116","status":"1","title":"奖励金到账啦!","type":"1","updateDate":"2018-12-11 21:46:53"},{"Id":17546,"binding_id":"","click_url":"","content":"任务发起人:事与愿违已确认完成你的订单","createDate":"2018-12-11 21:38:03","createName":"root@47.95.254.3","img_url":"","push_date":"","receive_client_no":"90000000002","send_client_no":"90000000116","status":"1","title":"奖励金到账啦!","type":"1","updateDate":"2018-12-11 21:38:03"},{"Id":17541,"binding_id":"","click_url":"","content":"任务发起人:事与愿违已确认完成你的订单","createDate":"2018-12-11 21:35:53","createName":"root@47.95.254.3","img_url":"","push_date":"","receive_client_no":"90000000002","send_client_no":"90000000116","status":"1","title":"奖励金到账啦!","type":"1","updateDate":"2018-12-11 21:35:53"},{"Id":17538,"binding_id":"","click_url":"","content":"任务发起人:事与愿违已确认完成你的订单","createDate":"2018-12-11 21:34:58","createName":"root@47.95.254.3","img_url":"","push_date":"","receive_client_no":"90000000002","send_client_no":"90000000116","status":"1","title":"奖励金到账啦!","type":"1","updateDate":"2018-12-11 21:34:58"},{"Id":17498,"binding_id":"","click_url":"","content":"您邀请的好友:测试环境你好已下单成功，奖励金20元已发到您的账户，请注意查收。","createDate":"2018-12-11 20:56:37","createName":"root@47.95.254.3","img_url":"","push_date":"","receive_client_no":"90000000002","send_client_no":"90000000116","status":"1","title":"奖励金到账啦!","type":"1","updateDate":"2018-12-11 20:56:37"},{"Id":17495,"binding_id":"","click_url":"","content":"您邀请的好友:测试环境你好已下单成功，奖励金20元已发到您的账户，请注意查收。","createDate":"2018-12-11 20:52:29","createName":"root@47.95.254.3","img_url":"","push_date":"","receive_client_no":"90000000002","send_client_no":"90000000116","status":"1","title":"奖励金到账啦!","type":"1","updateDate":"2018-12-11 20:52:29"},{"Id":17487,"binding_id":"","click_url":"","content":"您领取的30元全场通用优惠卷已到账！","createDate":"2018-12-11 20:43:15","createName":"root@115.60.6.173","img_url":"","push_date":"2018-12-11 20:43:15","receive_client_no":"","send_client_no":"","status":"1","title":"您有新的余额到帐！","type":"1","updateDate":"2018-12-11 20:43:15"}]
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
         * Id : 17558
         * binding_id :
         * click_url :
         * content : 任务发起人:事与愿违已确认完成你的订单
         * createDate : 2018-12-11 21:49:03
         * createName : root@47.95.254.3
         * img_url :
         * push_date :
         * receive_client_no : 90000000002
         * send_client_no : 90000000116
         * status : 1
         * title : 奖励金到账啦!
         * type : 1
         * updateDate : 2018-12-11 21:49:03
         */

        private int Id;
        private String binding_id;
        private String click_url="";
        private String img_url="";
        private String content;
        private String createDate;
        private String createName;
        private String push_date;
        private String receive_client_no;
        private String send_client_no;
        private String status;
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

        public String getClick_url() {
            return click_url;
        }

        public void setClick_url(String click_url) {
            this.click_url = click_url;
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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getPush_date() {
            return push_date;
        }

        public void setPush_date(String push_date) {
            this.push_date = push_date;
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
