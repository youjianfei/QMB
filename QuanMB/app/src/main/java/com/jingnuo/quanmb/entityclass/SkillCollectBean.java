package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class SkillCollectBean {

    /**
     * code : 1
     * data : {"list":[{"business_no":"","client_no":"90000000008","contacts":"婆婆需要","createDate":"2018-05-11 16:52:22","createName":"root@115.57.141.35","description":"T恤五需要","detail_address":"某些","helper_no":"2","img_id":"","mobile_no":"488","release_address":"郑州","release_date":"2018-05-11 16:52:05","release_specialty_id":3,"service_area":"郑州","specialty_id":2011,"status":"1","title":"哦Holly我","updateDate":"2018-05-11 16:52:22"},{"business_no":"","client_no":"90000000008","contacts":"11","createDate":"2018-05-11 20:57:23","createName":"root@47.95.254.3","description":"么么哒","detail_address":"没有","helper_no":"2","img_id":"","mobile_no":"123","release_address":"郑州","release_date":"2018-05-11 20:57:23","release_specialty_id":9,"service_area":"郑州","specialty_id":2011,"status":"1","title":"维修","updateDate":"2018-05-11 20:57:23"},{"business_no":"","client_no":"90000000005","contacts":"DFS","createDate":"2018-05-15 19:22:44","createName":"root@115.57.138.7","description":"SADF","detail_address":"ASDF","helper_no":"1","img_id":"356,","mobile_no":"36","release_address":"郑州","release_date":"2018-05-15 19:22:23","release_specialty_id":11,"service_area":"郑州","specialty_id":2011,"status":"1","title":"ASDF","updateDate":"2018-05-15 19:22:44"},{"business_no":"","client_no":"90000000005","contacts":"aaa","createDate":"2018-05-16 11:24:52","createName":"root@115.57.138.7","description":"aaaaaaaaaa","detail_address":"aaa","helper_no":"1","img_id":"","mobile_no":"555","release_address":"郑州","release_date":"2018-05-16 11:24:30","release_specialty_id":12,"service_area":"郑州","specialty_id":2011,"status":"1","title":"aaaa","updateDate":"2018-05-16 11:24:52"}]}
     * message : 获取收藏列表成功
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * business_no :
             * client_no : 90000000008
             * contacts : 婆婆需要
             * createDate : 2018-05-11 16:52:22
             * createName : root@115.57.141.35
             * description : T恤五需要
             * detail_address : 某些
             * helper_no : 2
             * img_id :
             * mobile_no : 488
             * release_address : 郑州
             * release_date : 2018-05-11 16:52:05
             * release_specialty_id : 3
             * service_area : 郑州
             * specialty_id : 2011
             * status : 1
             * title : 哦Holly我
             * updateDate : 2018-05-11 16:52:22
             */

            private String business_no;
            private String client_no;
            private String contacts;
            private String createDate;
            private String createName;
            private String description;
            private String detail_address;
            private String helper_no;
            private String img_id;
            private String mobile_no;
            private String release_address;
            private String release_date;
            private int release_specialty_id;
            private String service_area;
            private int specialty_id;
            private String status;
            private String title;
            private String avatar_url;
            private String updateDate;

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getBusiness_no() {
                return business_no;
            }

            public void setBusiness_no(String business_no) {
                this.business_no = business_no;
            }

            public String getClient_no() {
                return client_no;
            }

            public void setClient_no(String client_no) {
                this.client_no = client_no;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetail_address() {
                return detail_address;
            }

            public void setDetail_address(String detail_address) {
                this.detail_address = detail_address;
            }

            public String getHelper_no() {
                return helper_no;
            }

            public void setHelper_no(String helper_no) {
                this.helper_no = helper_no;
            }

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getMobile_no() {
                return mobile_no;
            }

            public void setMobile_no(String mobile_no) {
                this.mobile_no = mobile_no;
            }

            public String getRelease_address() {
                return release_address;
            }

            public void setRelease_address(String release_address) {
                this.release_address = release_address;
            }

            public String getRelease_date() {
                return release_date;
            }

            public void setRelease_date(String release_date) {
                this.release_date = release_date;
            }

            public int getRelease_specialty_id() {
                return release_specialty_id;
            }

            public void setRelease_specialty_id(int release_specialty_id) {
                this.release_specialty_id = release_specialty_id;
            }

            public String getService_area() {
                return service_area;
            }

            public void setService_area(String service_area) {
                this.service_area = service_area;
            }

            public int getSpecialty_id() {
                return specialty_id;
            }

            public void setSpecialty_id(int specialty_id) {
                this.specialty_id = specialty_id;
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

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }
        }
    }
}
