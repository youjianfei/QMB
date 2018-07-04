package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class Message_tujianrenwu {

    /**
     * code : 1
     * data : [{"Id":506,"binding_id":"766","content":"这里有一个你可能感兴趣的任务","createDate":"2018-07-04 17:01:01","createName":"root@115.57.137.203","receive_client_no":"90000000057","status":"1","title":"这里有一个你可能感兴趣的任务","type":"4","updateDate":"2018-07-04 17:01:06","updateName":"root@115.57.137.203"},{"Id":505,"binding_id":"766","content":"您附近有匹配任务啦！快去接单吧！","createDate":"2018-07-04 16:59:25","createName":"root@115.57.137.203","receive_client_no":"90000000057","status":"1","title":"您附近有匹配任务啦！快去接单吧！","type":"4","updateDate":"2018-07-04 16:59:58","updateName":"root@115.57.137.203"}]
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
         * Id : 506
         * binding_id : 766
         * content : 这里有一个你可能感兴趣的任务
         * createDate : 2018-07-04 17:01:01
         * createName : root@115.57.137.203
         * receive_client_no : 90000000057
         * status : 1
         * title : 这里有一个你可能感兴趣的任务
         * type : 4
         * updateDate : 2018-07-04 17:01:06
         * updateName : root@115.57.137.203
         */

        private int Id;
        private String binding_id;
        private String content;
        private String createDate;
        private String createName;
        private String receive_client_no;
        private String status;
        private String title;
        private String type;
        private String updateDate;
        private String updateName;

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

        public String getReceive_client_no() {
            return receive_client_no;
        }

        public void setReceive_client_no(String receive_client_no) {
            this.receive_client_no = receive_client_no;
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

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
        }
    }
}
