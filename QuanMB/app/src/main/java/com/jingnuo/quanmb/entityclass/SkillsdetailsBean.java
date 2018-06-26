package com.jingnuo.quanmb.entityclass;

/**
 * Created by Administrator on 2018/4/17.
 */

public class SkillsdetailsBean {


    /**
     * code : 1
     * data : {"detail":{"business_name":"ad","business_no":"1","client_no":"90000000007","contacts":"李师傅","createDate":1523951173000,"createName":"root@192.168.1.184","description":"买卖外卖","detail_address":"","img_url":"d:aaa,c:ccc,f:fff,","mobile_no":"15729112602","release_address":"afsafsfwsfewfew","release_date":1523951172000,"release_specialty_id":1000000001,"service_area":"郑州","specialty_id":2011,"specialty_name":"帮送","status":"","title":"买卖","updateDate":1524293126000,"updateName":"root@192.168.1.184"}}
     * message : 获取帖子详情成功
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
         * detail : {"business_name":"ad","business_no":"1","client_no":"90000000007","contacts":"李师傅","createDate":1523951173000,"createName":"root@192.168.1.184","description":"买卖外卖","detail_address":"","img_url":"d:aaa,c:ccc,f:fff,","mobile_no":"15729112602","release_address":"afsafsfwsfewfew","release_date":1523951172000,"release_specialty_id":1000000001,"service_area":"郑州","specialty_id":2011,"specialty_name":"帮送","status":"","title":"买卖","updateDate":1524293126000,"updateName":"root@192.168.1.184"}
         */

        private DetailBean detail;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * business_name : ad
             * business_no : 1
             * client_no : 90000000007
             * contacts : 李师傅
             * createDate : 1523951173000
             * createName : root@192.168.1.184
             * description : 买卖外卖
             * detail_address :
             * img_url : d:aaa,c:ccc,f:fff,
             * mobile_no : 15729112602
             * release_address : afsafsfwsfewfew
             * release_date : 1523951172000
             * release_specialty_id : 1000000001
             * service_area : 郑州
             * specialty_id : 2011
             * specialty_name : 帮送
             * status :
             * title : 买卖
             * updateDate : 1524293126000
             * updateName : root@192.168.1.184
             */

            private String auth_name;
            private String business_no;
            private String client_no;
            private String contacts;
            private long createDate;
            private String createName;
            private String description;
            private String detail_address;
            private String img_url;
            private String mobile_no;
            private String release_address;
            private long release_date;
            private int release_specialty_id;
            private String service_area;
            private int specialty_id;
            private int collection_status;
            private int release_num;
            private String specialty_name;
            private String status;
            private String title;
            private long updateDate;
            private String updateName;
            private String avatar_url;

            public int getRelease_num() {
                return release_num;
            }

            public void setRelease_num(int release_num) {
                this.release_num = release_num;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public int getCollection_status() {
                return collection_status;
            }

            public void setCollection_status(int collection_status) {
                this.collection_status = collection_status;
            }

            public String getAuth_name() {
                return auth_name;
            }

            public void setAuth_name(String auth_name) {
                this.auth_name = auth_name;
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

            public String getDetail_address() {
                return detail_address;
            }

            public void setDetail_address(String detail_address) {
                this.detail_address = detail_address;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
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
