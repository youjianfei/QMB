package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class SkillmentlistBean {


    /**
     * data : {"list":[{"business_no":"1","client_no":"1","contacts":"陈","createDate":"2018-04-10 15:18:03.0","createName":"root","description":"帮忙送外卖","img_id":"1","mobile_no":"15729112902","price":0,"release_address":"南阳路","release_date":"2018-04-10 15:18:04.0","release_specialty_id":"6","service_area":"郑州","specialty_id":"2011","title":"卖12","updateDate":"2018-04-12 16:09:56.0","updateName":"root@192.168.1.184"}]}
     * message : 获取帖子列表成功
     * status : 1
     */

    private DataBean data;
    private String message;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
             * business_no : 1
             * client_no : 1
             * contacts : 陈
             * createDate : 2018-04-10 15:18:03.0
             * createName : root
             * description : 帮忙送外卖
             * img_id : 1
             * mobile_no : 15729112902
             * price : 0
             * release_address : 南阳路
             * release_date : 2018-04-10 15:18:04.0
             * release_specialty_id : 6
             * service_area : 郑州
             * specialty_id : 2011
             * title : 卖12
             * updateDate : 2018-04-12 16:09:56.0
             * updateName : root@192.168.1.184
             */

            private String business_no;
            private String helper_no;
            private String client_no;
            private String contacts;
            private String createDate;
            private String createName;
            private String description;
            private String img_id;
            private String mobile_no;
            private int price;
            private String release_address;
            private String release_date;
            private String release_specialty_id;
            private String service_area;
            private String specialty_id;
            private String is_top;
            private String title;
            private String updateDate;
            private String updateName;
            private  String avatar_url;

            public String getIs_top() {
                return is_top;
            }

            public void setIs_top(String is_top) {
                this.is_top = is_top;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getHelper_no() {
                return helper_no;
            }

            public void setHelper_no(String helper_no) {
                this.helper_no = helper_no;
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
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

            public String getRelease_specialty_id() {
                return release_specialty_id;
            }

            public void setRelease_specialty_id(String release_specialty_id) {
                this.release_specialty_id = release_specialty_id;
            }

            public String getService_area() {
                return service_area;
            }

            public void setService_area(String service_area) {
                this.service_area = service_area;
            }

            public String getSpecialty_id() {
                return specialty_id;
            }

            public void setSpecialty_id(String specialty_id) {
                this.specialty_id = specialty_id;
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

            public String getUpdateName() {
                return updateName;
            }

            public void setUpdateName(String updateName) {
                this.updateName = updateName;
            }
        }
    }
}
