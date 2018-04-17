package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class SkillsdetailsBean {


    /**
     * data : {"detail":[{"business_no":"1","client_no":"90000000007","contacts":"李师傅","createDate":1523951173000,"createName":"root@192.168.1.184","description":"买卖外卖","img_id":"1","mobile_no":"15729112602","release_address":"afsafsfwsfewfew","release_date":1523951172000,"release_specialty_id":1000000001,"service_area":"郑州","specialty_id":2011,"specialty_name":"帮送","title":"买卖","updateDate":1523958837000,"updateName":"root@192.168.1.184"}]}
     * message : 获取帖子详情成功
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
        private List<DetailBean> detail;

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * business_no : 1
             * client_no : 90000000007
             * contacts : 李师傅
             * createDate : 1523951173000
             * createName : root@192.168.1.184
             * description : 买卖外卖
             * img_id : 1
             * mobile_no : 15729112602
             * release_address : afsafsfwsfewfew
             * release_date : 1523951172000
             * release_specialty_id : 1000000001
             * service_area : 郑州
             * specialty_id : 2011
             * specialty_name : 帮送
             * title : 买卖
             * updateDate : 1523958837000
             * updateName : root@192.168.1.184
             */

            private String business_no;
            private String business_name;
            private String client_no;
            private String contacts;
            private long createDate;
            private String createName;
            private String description;
            private String img_id;
            private String mobile_no;
            private String release_address;
            private long release_date;
            private int release_specialty_id;
            private String service_area;
            private int specialty_id;
            private String specialty_name;
            private String title;
            private long updateDate;
            private String updateName;

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

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
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

            public String getRelease_address() {
                return release_address;
            }

            public void setRelease_address(String release_address) {
                this.release_address = release_address;
            }

            public long getRelease_date() {
                return release_date;
            }

            public void setRelease_date(long release_date) {
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

            public String getSpecialty_name() {
                return specialty_name;
            }

            public void setSpecialty_name(String specialty_name) {
                this.specialty_name = specialty_name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(long updateDate) {
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
