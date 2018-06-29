package com.jingnuo.quanmb.entityclass;

/**
 * Created by Administrator on 2018/4/17.
 */

public class SkillsdetailsBean {


    /**
     * code : 1
     * data : {"detail":{"addmissionDate":1528967650000,"addmission_month":"1","auth_name":"awdawd","auto_refresh_enddate":1531384495000,"avatar_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/80bd2a3e-7984-4425-9eaa-839872d5533e201572710478.jpg.jpg","browse_number":1,"business_avatar":1077,"business_no":"60000000060","business_reputation":117,"client_no":"90000000057","collection_status":1,"contacts":"杨腾飞","createDate":1529459124000,"createName":"root@47.95.254.3","description":"帮我修电视","detail_address":"郑州市南阳路192号","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/1a009009-ba0f-4672-bf60-7309b5e53d8953b67e3a84729.jpg.jpg,","is_auto_refresh":"Y","is_top":"Y","mobile_no":"135","release_address":"升龙汇金\\广场写字楼","release_date":1529459124000,"release_num":20,"release_specialty_id":7,"service_area":"郑州","specialty_id":1203,"specialty_name":"电脑维修","status":"1","title":"修电视","top_enddate":1530530974000,"updateDate":1530234000000,"updateName":"root@47.95.254.3"}}
     * message : 查看服务详情成功
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
         * detail : {"addmissionDate":1528967650000,"addmission_month":"1","auth_name":"awdawd","auto_refresh_enddate":1531384495000,"avatar_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/80bd2a3e-7984-4425-9eaa-839872d5533e201572710478.jpg.jpg","browse_number":1,"business_avatar":1077,"business_no":"60000000060","business_reputation":117,"client_no":"90000000057","collection_status":1,"contacts":"杨腾飞","createDate":1529459124000,"createName":"root@47.95.254.3","description":"帮我修电视","detail_address":"郑州市南阳路192号","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/1a009009-ba0f-4672-bf60-7309b5e53d8953b67e3a84729.jpg.jpg,","is_auto_refresh":"Y","is_top":"Y","mobile_no":"135","release_address":"升龙汇金\\广场写字楼","release_date":1529459124000,"release_num":20,"release_specialty_id":7,"service_area":"郑州","specialty_id":1203,"specialty_name":"电脑维修","status":"1","title":"修电视","top_enddate":1530530974000,"updateDate":1530234000000,"updateName":"root@47.95.254.3"}
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
             * addmissionDate : 1528967650000
             * addmission_month : 1
             * auth_name : awdawd
             * auto_refresh_enddate : 1531384495000
             * avatar_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/80bd2a3e-7984-4425-9eaa-839872d5533e201572710478.jpg.jpg
             * browse_number : 1
             * business_avatar : 1077
             * business_no : 60000000060
             * business_reputation : 117
             * client_no : 90000000057
             * collection_status : 1
             * contacts : 杨腾飞
             * createDate : 1529459124000
             * createName : root@47.95.254.3
             * description : 帮我修电视
             * detail_address : 郑州市南阳路192号
             * img_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/1a009009-ba0f-4672-bf60-7309b5e53d8953b67e3a84729.jpg.jpg,
             * is_auto_refresh : Y
             * is_top : Y
             * mobile_no : 135
             * release_address : 升龙汇金\广场写字楼
             * release_date : 1529459124000
             * release_num : 20
             * release_specialty_id : 7
             * service_area : 郑州
             * specialty_id : 1203
             * specialty_name : 电脑维修
             * status : 1
             * title : 修电视
             * top_enddate : 1530530974000
             * updateDate : 1530234000000
             * updateName : root@47.95.254.3
             */

            private long addmissionDate;
            private String addmission_month;
            private String auth_name;
            private long auto_refresh_enddate;
            private String avatar_url;
            private int browse_number;
            private int business_avatar;
            private String business_no="";
            private int business_reputation;
            private String client_no;
            private int collection_status;
            private String contacts;
            private long createDate;
            private String createName;
            private String description;
            private String detail_address;
            private String img_url;
            private String is_auto_refresh;
            private String is_top;
            private String mobile_no;
            private String release_address;
            private long release_date;
            private int release_num;
            private int release_specialty_id;
            private String service_area;
            private int specialty_id;
            private String specialty_name;
            private String status;
            private String title;
            private long top_enddate;
            private long updateDate;
            private String updateName;

            public long getAddmissionDate() {
                return addmissionDate;
            }

            public void setAddmissionDate(long addmissionDate) {
                this.addmissionDate = addmissionDate;
            }

            public String getAddmission_month() {
                return addmission_month;
            }

            public void setAddmission_month(String addmission_month) {
                this.addmission_month = addmission_month;
            }

            public String getAuth_name() {
                return auth_name;
            }

            public void setAuth_name(String auth_name) {
                this.auth_name = auth_name;
            }

            public long getAuto_refresh_enddate() {
                return auto_refresh_enddate;
            }

            public void setAuto_refresh_enddate(long auto_refresh_enddate) {
                this.auto_refresh_enddate = auto_refresh_enddate;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public int getBrowse_number() {
                return browse_number;
            }

            public void setBrowse_number(int browse_number) {
                this.browse_number = browse_number;
            }

            public int getBusiness_avatar() {
                return business_avatar;
            }

            public void setBusiness_avatar(int business_avatar) {
                this.business_avatar = business_avatar;
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

            public String getClient_no() {
                return client_no;
            }

            public void setClient_no(String client_no) {
                this.client_no = client_no;
            }

            public int getCollection_status() {
                return collection_status;
            }

            public void setCollection_status(int collection_status) {
                this.collection_status = collection_status;
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

            public String getIs_auto_refresh() {
                return is_auto_refresh;
            }

            public void setIs_auto_refresh(String is_auto_refresh) {
                this.is_auto_refresh = is_auto_refresh;
            }

            public String getIs_top() {
                return is_top;
            }

            public void setIs_top(String is_top) {
                this.is_top = is_top;
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

            public int getRelease_num() {
                return release_num;
            }

            public void setRelease_num(int release_num) {
                this.release_num = release_num;
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

            public long getTop_enddate() {
                return top_enddate;
            }

            public void setTop_enddate(long top_enddate) {
                this.top_enddate = top_enddate;
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
