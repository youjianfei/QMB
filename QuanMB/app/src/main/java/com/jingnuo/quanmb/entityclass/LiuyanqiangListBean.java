package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class LiuyanqiangListBean {


    /**
     * code : 1
     * data : [{"Avatar_URL":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","Id":18,"client_no":"90000000002","community_code":"10000000000","content":"啦啦啦","createDate":"2018-07-18 10:25:04","createName":"root@47.95.254.3","imagesUrlsList":[null],"nick_name":"又见飞","updateDate":"2018-07-18 14:58:18","updateName":"root@115.57.142.30"},{"Avatar_URL":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","Id":17,"client_no":"90000000002","community_code":"10000000000","content":"   ","createDate":"2018-07-18 10:24:48","createName":"root@47.95.254.3","imagesUrlsList":[null],"nick_name":"又见飞","updateDate":"2018-07-18 14:58:16","updateName":"root@115.57.142.30"},{"Avatar_URL":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","Id":16,"client_no":"90000000002","community_code":"10000000000","content":"老K就老K了了","createDate":"2018-07-18 10:24:42","createName":"root@47.95.254.3","imagesUrlsList":[null],"nick_name":"又见飞","updateDate":"2018-07-18 14:58:16","updateName":"root@115.57.142.30"},{"Avatar_URL":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","Id":15,"client_no":"90000000002","community_code":"10000000000","content":"","createDate":"2018-07-18 10:21:26","createName":"root@47.95.254.3","images":"1549,","imagesUrlsList":["http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/0b2d1e1f-93aa-4eac-8bb2-691c1a0e5c0d1531880452294.png"],"nick_name":"又见飞","updateDate":"2018-07-18 14:58:15","updateName":"root@115.57.142.30"},{"Avatar_URL":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","Id":14,"client_no":"90000000002","community_code":"10000000000","content":"","createDate":"2018-07-18 10:21:22","createName":"root@47.95.254.3","imagesUrlsList":[null],"nick_name":"又见飞","updateDate":"2018-07-18 14:58:15","updateName":"root@115.57.142.30"},{"Avatar_URL":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","Id":13,"client_no":"90000000002","community_code":"10000000000","content":"","createDate":"2018-07-18 10:21:19","createName":"root@47.95.254.3","imagesUrlsList":[null],"nick_name":"又见飞","updateDate":"2018-07-18 14:58:14","updateName":"root@115.57.142.30"},{"Avatar_URL":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","Id":12,"client_no":"90000000002","community_code":"10000000000","content":"椅子舞","createDate":"2018-07-18 10:17:04","createName":"root@47.95.254.3","images":"1548,1547,1546,","imagesUrlsList":["http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/b131b997-b62b-4b83-a812-e9cbcd8324eb1531880221148.png","http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/9e52a906-4e91-4500-8345-face656f719b1531880222014.png","http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/383b9f7a-0eeb-41e2-b500-3471248421711531880222446.png"],"nick_name":"又见飞","updateDate":"2018-07-18 14:58:13","updateName":"root@115.57.142.30"},{"Avatar_URL":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","Id":11,"client_no":"90000000002","community_code":"10000000000","content":"SOHO肉我","createDate":"2018-07-18 10:16:54","createName":"root@47.95.254.3","images":"1545,1544,","imagesUrlsList":["http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/3952df80-da9b-4f47-8a28-ce66a9b561751531880213124.png","http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/da631faa-928d-4827-a5e5-c16b990398121531880213182.png"],"nick_name":"又见飞","updateDate":"2018-07-18 14:58:13","updateName":"root@115.57.142.30"}]
     * msg : 查询用户信息成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Avatar_URL : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png
         * Id : 18
         * client_no : 90000000002
         * community_code : 10000000000
         * content : 啦啦啦
         * createDate : 2018-07-18 10:25:04
         * createName : root@47.95.254.3
         * imagesUrlsList : [null]
         * nick_name : 又见飞
         * updateDate : 2018-07-18 14:58:18
         * updateName : root@115.57.142.30
         * images : 1549,
         */

        private String Avatar_URL;
        private int Id;
        private String client_no;
        private String community_code;
        private String content;
        private String createDate;
        private String createName;
        private String nick_name;
        private String updateDate;
        private String updateName;
        private String images;
        private List<String> imagesUrlsList;

        public String getAvatar_URL() {
            return Avatar_URL;
        }

        public void setAvatar_URL(String Avatar_URL) {
            this.Avatar_URL = Avatar_URL;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getClient_no() {
            return client_no;
        }

        public void setClient_no(String client_no) {
            this.client_no = client_no;
        }

        public String getCommunity_code() {
            return community_code;
        }

        public void setCommunity_code(String community_code) {
            this.community_code = community_code;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
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

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public List<String> getImagesUrlsList() {
            return imagesUrlsList;
        }

        public void setImagesUrlsList(List<String> imagesUrlsList) {
            this.imagesUrlsList = imagesUrlsList;
        }
    }
}
