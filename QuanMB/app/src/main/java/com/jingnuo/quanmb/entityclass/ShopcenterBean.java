package com.jingnuo.quanmb.entityclass;

public class ShopcenterBean {

    /**
     * code : 1
     * data : {"list":{"business_address":"asd","business_grade":"1","business_id":1,"business_license":"1","business_mobile_no":"66688888","business_name":"ad","business_no":"1","business_reputation":100,"business_type_id":2011,"client_no":"90000000009","createDate":"2018-04-10 15:10:42","createName":"root@192.168.1.184","legal_person":"sad","legal_person_cer_no":"11","organization_name":"sds","social_credit_code":"11","status":"1","updateDate":"2018-04-23 09:47:12","updateName":"root@192.168.1.184"}}
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
         * list : {"business_address":"asd","business_grade":"1","business_id":1,"business_license":"1","business_mobile_no":"66688888","business_name":"ad","business_no":"1","business_reputation":100,"business_type_id":2011,"client_no":"90000000009","createDate":"2018-04-10 15:10:42","createName":"root@192.168.1.184","legal_person":"sad","legal_person_cer_no":"11","organization_name":"sds","social_credit_code":"11","status":"1","updateDate":"2018-04-23 09:47:12","updateName":"root@192.168.1.184"}
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
             * business_address : asd
             * business_grade : 1
             * business_id : 1
             * business_license : 1
             * business_mobile_no : 66688888
             * business_name : ad
             * business_no : 1
             * business_reputation : 100
             * business_type_id : 2011
             * client_no : 90000000009
             * createDate : 2018-04-10 15:10:42
             * createName : root@192.168.1.184
             * legal_person : sad
             * legal_person_cer_no : 11
             * organization_name : sds
             * social_credit_code : 11
             * status : 1
             * updateDate : 2018-04-23 09:47:12
             * updateName : root@192.168.1.184
             */

            private String business_address;
            private String business_grade;
            private int business_id;
            private String business_license;
            private String business_mobile_no;
            private String business_name;
            private String business_no;
            private int business_reputation;
            private int business_type_id;
            private String client_no;
            private String createDate;
            private String createName;
            private String legal_person;
            private String legal_person_cer_no;
            private String organization_name;
            private String social_credit_code;
            private String status;
            private String updateDate;
            private String updateName;

            public String getBusiness_address() {
                return business_address;
            }

            public void setBusiness_address(String business_address) {
                this.business_address = business_address;
            }

            public String getBusiness_grade() {
                return business_grade;
            }

            public void setBusiness_grade(String business_grade) {
                this.business_grade = business_grade;
            }

            public int getBusiness_id() {
                return business_id;
            }

            public void setBusiness_id(int business_id) {
                this.business_id = business_id;
            }

            public String getBusiness_license() {
                return business_license;
            }

            public void setBusiness_license(String business_license) {
                this.business_license = business_license;
            }

            public String getBusiness_mobile_no() {
                return business_mobile_no;
            }

            public void setBusiness_mobile_no(String business_mobile_no) {
                this.business_mobile_no = business_mobile_no;
            }

            public String getBusiness_name() {
                return business_name;
            }

            public void setBusiness_name(String business_name) {
                this.business_name = business_name;
            }

            public String getBusiness_no() {
                return business_no;
            }

            public void setBusiness_no(String business_no) {
                this.business_no = business_no;
            }

            public int getBusiness_reputation() {
                return business_reputation;
            }

            public void setBusiness_reputation(int business_reputation) {
                this.business_reputation = business_reputation;
            }

            public int getBusiness_type_id() {
                return business_type_id;
            }

            public void setBusiness_type_id(int business_type_id) {
                this.business_type_id = business_type_id;
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

            public String getLegal_person() {
                return legal_person;
            }

            public void setLegal_person(String legal_person) {
                this.legal_person = legal_person;
            }

            public String getLegal_person_cer_no() {
                return legal_person_cer_no;
            }

            public void setLegal_person_cer_no(String legal_person_cer_no) {
                this.legal_person_cer_no = legal_person_cer_no;
            }

            public String getOrganization_name() {
                return organization_name;
            }

            public void setOrganization_name(String organization_name) {
                this.organization_name = organization_name;
            }

            public String getSocial_credit_code() {
                return social_credit_code;
            }

            public void setSocial_credit_code(String social_credit_code) {
                this.social_credit_code = social_credit_code;
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
