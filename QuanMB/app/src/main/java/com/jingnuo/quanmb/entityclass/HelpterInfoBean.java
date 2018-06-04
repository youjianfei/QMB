package com.jingnuo.quanmb.entityclass;

public class HelpterInfoBean {

    /**
     * code : 1
     * data : {"list":{"client_no":"90000000003","createDate":"2018-05-03 17:41:50","createName":"root@115.57.138.146","group_photo":"adsadad","helper_birthday":"1995-09-09 00:00:00","helper_cer_no":"123","helper_cer_type":"11","helper_grade":"1","helper_id":1,"helper_mobile_no":"18539931923","helper_name":"星星","helper_no":"1","helper_reputation":100,"helper_sex":"0","protrait_photo":"asdfasfd","reverse_photo":"sadad","status":"1","updateDate":"2018-05-07 19:21:17","updateName":"root@115.57.138.21"}}
     * message : 查看信息成功
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
         * list : {"client_no":"90000000003","createDate":"2018-05-03 17:41:50","createName":"root@115.57.138.146","group_photo":"adsadad","helper_birthday":"1995-09-09 00:00:00","helper_cer_no":"123","helper_cer_type":"11","helper_grade":"1","helper_id":1,"helper_mobile_no":"18539931923","helper_name":"星星","helper_no":"1","helper_reputation":100,"helper_sex":"0","protrait_photo":"asdfasfd","reverse_photo":"sadad","status":"1","updateDate":"2018-05-07 19:21:17","updateName":"root@115.57.138.21"}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * client_no : 90000000003
             * createDate : 2018-05-03 17:41:50
             * createName : root@115.57.138.146
             * group_photo : adsadad
             * helper_birthday : 1995-09-09 00:00:00
             * helper_cer_no : 123
             * helper_cer_type : 11
             * helper_grade : 1
             * helper_id : 1
             * helper_mobile_no : 18539931923
             * helper_name : 星星
             * helper_no : 1
             * helper_reputation : 100
             * helper_sex : 0
             * protrait_photo : asdfasfd
             * reverse_photo : sadad
             * status : 1
             * updateDate : 2018-05-07 19:21:17
             * updateName : root@115.57.138.21
             */

            private String client_no;
            private String createDate;
            private String createName;
            private String group_photo;
            private String helper_birthday;
            private String helper_cer_no;
            private String helper_cer_type;
            private String helper_grade;
            private int helper_id;
            private String helper_mobile_no;
            private String helper_name;
            private String helper_no;
            private int helper_reputation;
            private String helper_sex;
            private String protrait_photo;
            private String reverse_photo;
            private String status;
            private String updateDate;
            private String updateName;
            private String commission;

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

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

            public String getGroup_photo() {
                return group_photo;
            }

            public void setGroup_photo(String group_photo) {
                this.group_photo = group_photo;
            }

            public String getHelper_birthday() {
                return helper_birthday;
            }

            public void setHelper_birthday(String helper_birthday) {
                this.helper_birthday = helper_birthday;
            }

            public String getHelper_cer_no() {
                return helper_cer_no;
            }

            public void setHelper_cer_no(String helper_cer_no) {
                this.helper_cer_no = helper_cer_no;
            }

            public String getHelper_cer_type() {
                return helper_cer_type;
            }

            public void setHelper_cer_type(String helper_cer_type) {
                this.helper_cer_type = helper_cer_type;
            }

            public String getHelper_grade() {
                return helper_grade;
            }

            public void setHelper_grade(String helper_grade) {
                this.helper_grade = helper_grade;
            }

            public int getHelper_id() {
                return helper_id;
            }

            public void setHelper_id(int helper_id) {
                this.helper_id = helper_id;
            }

            public String getHelper_mobile_no() {
                return helper_mobile_no;
            }

            public void setHelper_mobile_no(String helper_mobile_no) {
                this.helper_mobile_no = helper_mobile_no;
            }

            public String getHelper_name() {
                return helper_name;
            }

            public void setHelper_name(String helper_name) {
                this.helper_name = helper_name;
            }

            public String getHelper_no() {
                return helper_no;
            }

            public void setHelper_no(String helper_no) {
                this.helper_no = helper_no;
            }

            public int getHelper_reputation() {
                return helper_reputation;
            }

            public void setHelper_reputation(int helper_reputation) {
                this.helper_reputation = helper_reputation;
            }

            public String getHelper_sex() {
                return helper_sex;
            }

            public void setHelper_sex(String helper_sex) {
                this.helper_sex = helper_sex;
            }

            public String getProtrait_photo() {
                return protrait_photo;
            }

            public void setProtrait_photo(String protrait_photo) {
                this.protrait_photo = protrait_photo;
            }

            public String getReverse_photo() {
                return reverse_photo;
            }

            public void setReverse_photo(String reverse_photo) {
                this.reverse_photo = reverse_photo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
}
