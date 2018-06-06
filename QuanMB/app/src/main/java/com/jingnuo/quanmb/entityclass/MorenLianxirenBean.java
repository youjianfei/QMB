package com.jingnuo.quanmb.entityclass;

public class MorenLianxirenBean {

    /**
     * code : 1
     * data : {"client_no":"90000000005","createDate":"2018-05-31 17:35:26","createName":"root@115.57.140.92","id":32,"is_default":"Y","mobile_no":"15838833911","name":"song","sex":0,"updateDate":"2018-06-06 14:55:01","updateName":"root@115.57.136.96"}
     * msg : 获取默认联系人成功
     */

    private int code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * client_no : 90000000005
         * createDate : 2018-05-31 17:35:26
         * createName : root@115.57.140.92
         * id : 32
         * is_default : Y
         * mobile_no : 15838833911
         * name : song
         * sex : 0
         * updateDate : 2018-06-06 14:55:01
         * updateName : root@115.57.136.96
         */

        private String client_no;
        private String createDate;
        private String createName;
        private int id;
        private String is_default;
        private String mobile_no;
        private String name;
        private int sex;
        private String updateDate;
        private String updateName;

        public String getClient_no() {
            return client_no;
        }

        public void setClient_no(String client_no) {
            this.client_no = client_no;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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
