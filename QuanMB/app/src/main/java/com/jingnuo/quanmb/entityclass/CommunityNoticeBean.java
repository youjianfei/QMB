package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class CommunityNoticeBean {

    /**
     * code : 1
     * data : [{"community_code":"10000000000","createDate":"2018-07-20 13:39:45","createName":"root@115.57.138.80","endDate":"2018-07-23 13:38:55","id":1,"notice_content":"it's so hot","startDate":"2018-07-18 13:38:51","updateDate":"2018-07-20 14:54:48","updateName":"root@115.57.138.80"},{"community_code":"10000000000","createDate":"2018-07-20 13:42:19","createName":"root@115.57.138.80","endDate":"2018-07-22 13:42:08","id":2,"notice_content":"hot again","startDate":"2018-07-17 13:42:02","updateDate":"2018-07-20 13:42:27","updateName":"root@115.57.138.80"}]
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
         * community_code : 10000000000
         * createDate : 2018-07-20 13:39:45
         * createName : root@115.57.138.80
         * endDate : 2018-07-23 13:38:55
         * id : 1
         * notice_content : it's so hot
         * startDate : 2018-07-18 13:38:51
         * updateDate : 2018-07-20 14:54:48
         * updateName : root@115.57.138.80
         */

        private String community_code;
        private String createDate;
        private String createName;
        private String endDate;
        private int id;
        private String notice_content;
        private String startDate;
        private String updateDate;
        private String updateName;

        public String getCommunity_code() {
            return community_code;
        }

        public void setCommunity_code(String community_code) {
            this.community_code = community_code;
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

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNotice_content() {
            return notice_content;
        }

        public void setNotice_content(String notice_content) {
            this.notice_content = notice_content;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
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
