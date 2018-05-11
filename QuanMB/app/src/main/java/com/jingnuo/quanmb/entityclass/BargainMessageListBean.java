package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class BargainMessageListBean {

    /**
     * code : 1
     * data : [{"Id":69,"binding_id":"85","content":"商家万世荣光俱乐部愿意出2.0元帮忙","createDate":"2018-05-11 09:34:35","createName":"root@115.57.141.35","receive_client_no":"90000000003","send_client_no":"2147483680","status":"2","title":"sdfdsf","type":"2","updateDate":"2018-05-11 09:34:35"},{"Id":68,"binding_id":"85","content":"商家万世荣光俱乐部愿意出20.0元帮忙","createDate":"2018-05-11 09:33:39","createName":"root@115.57.141.35","receive_client_no":"90000000003","send_client_no":"2147483680","status":"2","title":"sdfdsf","type":"2","updateDate":"2018-05-11 09:33:39"},{"Id":67,"binding_id":"85","content":"商家万世荣光俱乐部愿意出2.0元帮忙","createDate":"2018-05-11 09:32:23","createName":"root@115.57.141.35","receive_client_no":"90000000003","send_client_no":"2147483680","status":"2","title":"sdfdsf","type":"2","updateDate":"2018-05-11 09:32:23"},{"Id":66,"binding_id":"85","content":"商家万世荣光俱乐部愿意出2.0元帮忙","createDate":"2018-05-11 09:32:02","createName":"root@115.57.141.35","receive_client_no":"90000000003","send_client_no":"2147483680","status":"2","title":"sdfdsf","type":"2","updateDate":"2018-05-11 09:32:02"},{"Id":64,"binding_id":"85","content":"商家万世荣光俱乐部愿意出25.0元帮忙","createDate":"2018-05-10 17:56:26","createName":"root@115.57.141.35","receive_client_no":"90000000003","send_client_no":"2147483680","status":"2","title":"sdfdsf","type":"2","updateDate":"2018-05-10 17:56:26"},{"Id":65,"binding_id":"85","content":"商家万世荣光俱乐部愿意出25.0元帮忙","createDate":"2018-05-10 17:56:26","createName":"root@115.57.141.35","receive_client_no":"90000000003","send_client_no":"2147483680","status":"2","title":"sdfdsf","type":"2","updateDate":"2018-05-10 17:56:26"}]
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
         * Id : 69
         * binding_id : 85
         * content : 商家万世荣光俱乐部愿意出2.0元帮忙
         * createDate : 2018-05-11 09:34:35
         * createName : root@115.57.141.35
         * receive_client_no : 90000000003
         * send_client_no : 2147483680
         * status : 2
         * title : sdfdsf
         * type : 2
         * updateDate : 2018-05-11 09:34:35
         */

        private int Id;
        private String binding_id;
        private String content;
        private String createDate;
        private String createName;
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
