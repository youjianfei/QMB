package com.jingnuo.quanmb.entityclass;

public class ShopcenterBean {


    /**
     * code : 1
     * data : {"list":{"avatar_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/3539bdf1-1be4-4334-9f27-edf9cab898e0magazine-unlock-01-2.3.968-_d03b446f06574c478ba844bd2c37514b.jpg?Expires=1525868739&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=kHXlI2d6iSzdums8T6i92O4MZys%3D","business_address":"中州大道","business_grade":"1","business_id":2,"business_license":"141","business_mobile_no":"18539931923","business_name":"奔腾cpu","business_no":"1","business_reputation":100,"business_type_id":2024,"business_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/29a5aabd-cb96-4999-8278-ee5bb11efb2005_02_2018_20_46_05_7264.png..png?Expires=1525868739&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=g%2Boz3ZECFuP%2F4UgbzhbZXnit4wA%3D,","client_no":"90000000003","createDate":"2018-05-03 19:36:57","createName":"root@115.57.138.146","legal_person":"李催化","legal_person_cer_no":"002","organization_name":"转专业","social_credit_code":"001","status":"1","updateDate":"2018-05-08 20:19:19","updateName":"root@115.57.140.29"}}
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
         * list : {"avatar_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/3539bdf1-1be4-4334-9f27-edf9cab898e0magazine-unlock-01-2.3.968-_d03b446f06574c478ba844bd2c37514b.jpg?Expires=1525868739&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=kHXlI2d6iSzdums8T6i92O4MZys%3D","business_address":"中州大道","business_grade":"1","business_id":2,"business_license":"141","business_mobile_no":"18539931923","business_name":"奔腾cpu","business_no":"1","business_reputation":100,"business_type_id":2024,"business_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/29a5aabd-cb96-4999-8278-ee5bb11efb2005_02_2018_20_46_05_7264.png..png?Expires=1525868739&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=g%2Boz3ZECFuP%2F4UgbzhbZXnit4wA%3D,","client_no":"90000000003","createDate":"2018-05-03 19:36:57","createName":"root@115.57.138.146","legal_person":"李催化","legal_person_cer_no":"002","organization_name":"转专业","social_credit_code":"001","status":"1","updateDate":"2018-05-08 20:19:19","updateName":"root@115.57.140.29"}
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
             * avatar_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/3539bdf1-1be4-4334-9f27-edf9cab898e0magazine-unlock-01-2.3.968-_d03b446f06574c478ba844bd2c37514b.jpg?Expires=1525868739&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=kHXlI2d6iSzdums8T6i92O4MZys%3D
             * business_address : 中州大道
             * business_grade : 1
             * business_id : 2
             * business_license : 141
             * business_mobile_no : 18539931923
             * business_name : 奔腾cpu
             * business_no : 1
             * business_reputation : 100
             * business_type_id : 2024
             * business_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/29a5aabd-cb96-4999-8278-ee5bb11efb2005_02_2018_20_46_05_7264.png..png?Expires=1525868739&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=g%2Boz3ZECFuP%2F4UgbzhbZXnit4wA%3D,
             * client_no : 90000000003
             * createDate : 2018-05-03 19:36:57
             * createName : root@115.57.138.146
             * legal_person : 李催化
             * legal_person_cer_no : 002
             * organization_name : 转专业
             * social_credit_code : 001
             * status : 1
             * updateDate : 2018-05-08 20:19:19
             * updateName : root@115.57.140.29
             */

            private String avatar_url;
            private String business_address;
            private String business_grade;
            private int business_id;
            private String business_license;
            private String business_mobile_no;
            private String business_name;
            private String business_no;
            private int business_reputation;
            private String business_type_id;
            private String business_url;
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
            private String introduction;
            private String  commission;
            private int  business_level;
            private int  spread_b;

            public int getSpread_b() {
                return spread_b;
            }

            public void setSpread_b(int spread_b) {
                this.spread_b = spread_b;
            }

            public int getBusiness_level() {
                return business_level;
            }

            public void setBusiness_level(int business_level) {
                this.business_level = business_level;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public ListBean(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

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

            public String getBusiness_type_id() {
                return business_type_id;
            }

            public void setBusiness_type_id(String business_type_id) {
                this.business_type_id = business_type_id;
            }

            public String getBusiness_url() {
                return business_url;
            }

            public void setBusiness_url(String business_url) {
                this.business_url = business_url;
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
