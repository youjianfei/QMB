package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class SystemmessageBean
{

    /**
     * code : 1
     * data : [{"Id":486,"content":"ssss","createDate":"2018-05-31 09:29:55","createName":"root@115.57.140.92","status":"2","title":"sss","type":"1","updateDate":"2018-05-31 09:29:55"},{"Id":487,"content":"ssss","createDate":"2018-05-31 09:29:55","createName":"root@115.57.140.92","status":"2","title":"sss","type":"1","updateDate":"2018-05-31 09:29:55"},{"Id":485,"content":"wwww","createDate":"2018-05-31 09:10:40","createName":"root@115.57.140.92","status":"2","title":"mmmm","type":"1","updateDate":"2018-05-31 09:10:40"},{"Id":484,"content":"111","createDate":"2018-05-31 09:10:06","createName":"root@115.57.140.92","status":"2","title":"1111","type":"1","updateDate":"2018-05-31 09:10:06"},{"Id":482,"content":"adadad","createDate":"2018-05-31 09:09:53","createName":"root@115.57.140.92","status":"2","title":"asdada","type":"1","updateDate":"2018-05-31 09:09:53"},{"Id":483,"content":"adadad","createDate":"2018-05-31 09:09:53","createName":"root@115.57.140.92","status":"2","title":"asdada","type":"1","updateDate":"2018-05-31 09:09:53"}]
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
         * Id : 486
         * content : ssss
         * createDate : 2018-05-31 09:29:55
         * createName : root@115.57.140.92
         * status : 2
         * title : sss
         * type : 1
         * updateDate : 2018-05-31 09:29:55
         */

        private int Id;
        private String content;
        private String createDate;
        private String createName;
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
