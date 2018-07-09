package com.jingnuo.quanmb.entityclass;

public class HelpterInfoBean {

    /**
     * code : 1
     * data : {"list":{"avatar_url":"https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/helper.png","client_no":"90000000005","commission":"0.00","createDate":"2018-06-07 17:51:48","createName":"root@115.57.136.96","group_photo":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/85f12e4d-f9d0-4f40-8bbe-81a3a950f8a6S80428-165557.jpg.jpg","helper_age":26,"helper_avatar":7,"helper_birthday":"1992-09-20 00:00:00","helper_cer_no":"410185199209202516","helper_cer_type":"1","helper_id":51,"helper_level":0,"helper_mobile_no":"18539931923","helper_name":"监控","helper_no":"1","helper_reputation":100,"helper_sex":"0","protrait_photo":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/ada97dfc-a706-4fe3-bfd3-8911d3f8a7c5S80428-165557.jpg.jpg","reverse_photo":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/711d4985-088a-41e7-a1c9-8ca90b2ebf0fS80428-165557.jpg.jpg","status":"2","updateDate":"2018-06-07 17:56:17","updateName":"root@115.57.136.96"}}
     * message : 查看帮手信息成功
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
         * list : {"avatar_url":"https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/helper.png","client_no":"90000000005","commission":"0.00","createDate":"2018-06-07 17:51:48","createName":"root@115.57.136.96","group_photo":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/85f12e4d-f9d0-4f40-8bbe-81a3a950f8a6S80428-165557.jpg.jpg","helper_age":26,"helper_avatar":7,"helper_birthday":"1992-09-20 00:00:00","helper_cer_no":"410185199209202516","helper_cer_type":"1","helper_id":51,"helper_level":0,"helper_mobile_no":"18539931923","helper_name":"监控","helper_no":"1","helper_reputation":100,"helper_sex":"0","protrait_photo":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/ada97dfc-a706-4fe3-bfd3-8911d3f8a7c5S80428-165557.jpg.jpg","reverse_photo":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/711d4985-088a-41e7-a1c9-8ca90b2ebf0fS80428-165557.jpg.jpg","status":"2","updateDate":"2018-06-07 17:56:17","updateName":"root@115.57.136.96"}
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
             * avatar_url : https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/helper.png
             * client_no : 90000000005
             * commission : 0.00
             * createDate : 2018-06-07 17:51:48
             * createName : root@115.57.136.96
             * group_photo : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/85f12e4d-f9d0-4f40-8bbe-81a3a950f8a6S80428-165557.jpg.jpg
             * helper_age : 26
             * helper_avatar : 7
             * helper_birthday : 1992-09-20 00:00:00
             * helper_cer_no : 410185199209202516
             * helper_cer_type : 1
             * helper_id : 51
             * helper_level : 0
             * helper_mobile_no : 18539931923
             * helper_name : 监控
             * helper_no : 1
             * helper_reputation : 100
             * helper_sex : 0
             * protrait_photo : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/ada97dfc-a706-4fe3-bfd3-8911d3f8a7c5S80428-165557.jpg.jpg
             * reverse_photo : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/idcard/711d4985-088a-41e7-a1c9-8ca90b2ebf0fS80428-165557.jpg.jpg
             * status : 2
             * updateDate : 2018-06-07 17:56:17
             * updateName : root@115.57.136.96
             */

            private String avatar_url;
            private String iconImgUrl="";//等级图片
            private String memberImgUrl="";//vip等级图片
            private String client_no;
            private String commission;
            private String createDate;
            private String createName;
            private String group_photo;
            private int helper_age;
            private int helper_avatar;
            private String helper_birthday;
            private String helper_cer_no;
            private String helper_cer_type;
            private int helper_id;
            private int helper_level;
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
            private String member_enddate;
            private int  spread_b;

            public String getMemberImgUrl() {
                return memberImgUrl;
            }

            public void setMemberImgUrl(String memberImgUrl) {
                this.memberImgUrl = memberImgUrl;
            }

            public String getIconImgUrl() {
                return iconImgUrl;
            }

            public void setIconImgUrl(String iconImgUrl) {
                this.iconImgUrl = iconImgUrl;
            }

            public String getMember_enddate() {
                return member_enddate;
            }

            public void setMember_enddate(String member_enddate) {
                this.member_enddate = member_enddate;
            }

            public int getSpread_b() {
                return spread_b;
            }

            public void setSpread_b(int spread_b) {
                this.spread_b = spread_b;
            }
            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getClient_no() {
                return client_no;
            }

            public void setClient_no(String client_no) {
                this.client_no = client_no;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
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

            public int getHelper_age() {
                return helper_age;
            }

            public void setHelper_age(int helper_age) {
                this.helper_age = helper_age;
            }

            public int getHelper_avatar() {
                return helper_avatar;
            }

            public void setHelper_avatar(int helper_avatar) {
                this.helper_avatar = helper_avatar;
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

            public int getHelper_id() {
                return helper_id;
            }

            public void setHelper_id(int helper_id) {
                this.helper_id = helper_id;
            }

            public int getHelper_level() {
                return helper_level;
            }

            public void setHelper_level(int helper_level) {
                this.helper_level = helper_level;
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
