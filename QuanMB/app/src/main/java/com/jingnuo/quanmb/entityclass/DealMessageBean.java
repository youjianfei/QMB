package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class DealMessageBean {

    /**
     * code : 1
     * data : [{"Id":153,"binding_id":"QMB180516100000178","content":"用户:擎天柱同意了您的还价请求，订单已生成。点击此处查看订单详情","createDate":"2018-05-16 20:15:27","createName":"root@115.57.142.105","receive_client_no":"90000000008","send_client_no":"90000000005","status":"2","title":"233","type":"3","updateDate":"2018-05-16 20:15:27"},{"Id":142,"binding_id":"QMB180516100000172","content":"您已接下了一个任务 点击此处查看","createDate":"2018-05-16 17:16:01","createName":"root@115.57.138.7","receive_client_no":"90000000008","send_client_no":"90000000005","status":"2","title":"321go","type":"3","updateDate":"2018-05-16 17:16:01"},{"Id":140,"binding_id":"QMB180516100000171","content":"您已接下了一个任务 点击此处查看","createDate":"2018-05-16 17:11:11","createName":"root@115.57.138.7","receive_client_no":"90000000008","send_client_no":"90000000005","status":"2","title":"我的天","type":"3","updateDate":"2018-05-16 17:11:11"},{"Id":131,"binding_id":"QMB180516100000169","content":"帮手隔壁老王以35.0元价格接下此任务","createDate":"2018-05-16 16:59:20","createName":"root@115.57.138.7","receive_client_no":"90000000008","send_client_no":"90000000005","status":"2","title":"我需要一名配型","type":"3","updateDate":"2018-05-16 16:59:20"},{"Id":123,"binding_id":"QMB180516100000168","content":"帮手adsa以15.0元价格接下此任务","createDate":"2018-05-16 16:51:32","createName":"root@115.57.138.7","receive_client_no":"90000000008","send_client_no":"90000000005","status":"2","title":"凯丽的强化器","type":"3","updateDate":"2018-05-16 16:51:32"},{"Id":116,"binding_id":"QMB180516100000167","content":"帮手隔壁老王以500.0元价格接下此任务","createDate":"2018-05-16 14:13:10","createName":"root@115.57.138.7","receive_client_no":"90000000008","send_client_no":"90000000008","status":"2","title":"修理电视机","type":"3","updateDate":"2018-05-16 14:13:10"},{"Id":115,"binding_id":"QMB180516100000167","content":"帮手隔壁老王以500.0元价格接下此任务","createDate":"2018-05-16 14:13:09","createName":"root@115.57.138.7","receive_client_no":"90000000008","send_client_no":"90000000005","status":"2","title":"修理电视机","type":"3","updateDate":"2018-05-16 14:13:09"},{"Id":111,"binding_id":"QMB180516100000166","content":"帮手null以35.0元价格接下此任务","createDate":"2018-05-16 09:07:39","createName":"root@115.57.138.7","receive_client_no":"90000000008","send_client_no":"90000000005","status":"2","title":"666","type":"3","updateDate":"2018-05-16 09:07:39"}]
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
         * Id : 153
         * binding_id : QMB180516100000178
         * content : 用户:擎天柱同意了您的还价请求，订单已生成。点击此处查看订单详情
         * createDate : 2018-05-16 20:15:27
         * createName : root@115.57.142.105
         * receive_client_no : 90000000008
         * send_client_no : 90000000005
         * status : 2
         * title : 233
         * type : 3
         * updateDate : 2018-05-16 20:15:27
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
