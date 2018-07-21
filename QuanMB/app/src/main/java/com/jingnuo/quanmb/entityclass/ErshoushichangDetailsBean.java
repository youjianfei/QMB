package com.jingnuo.quanmb.entityclass;

public class ErshoushichangDetailsBean {


    /**
     * code : 1
     * data : {"createDate":"2018-07-17 16:56:29","distance":0,"headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","nick_name":"又见飞","specialty_name":"帮送","task_ImgUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/dfc1d065-b818-45a6-885c-79dee3eaf4a81531817711776.png,","task_Img_id":"1529,","task_description":"公民婆婆GZ","task_id":23,"task_name":"轮波图"}
     * msg : 查询成功
     */

    private int code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * createDate : 2018-07-17 16:56:29
         * distance : 0
         * headUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png
         * nick_name : 又见飞
         * specialty_name : 帮送
         * task_ImgUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/dfc1d065-b818-45a6-885c-79dee3eaf4a81531817711776.png,
         * task_Img_id : 1529,
         * task_description : 公民婆婆GZ
         * task_id : 23
         * task_name : 轮波图
         */

        private String createDate;
        private int distance;
        private String headUrl;
        private String nick_name;
        private String type_name;
        private String task_ImgUrl;
        private String task_Img_id;
        private String task_description;
        private int task_id;
        private String task_name;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getTask_ImgUrl() {
            return task_ImgUrl;
        }

        public void setTask_ImgUrl(String task_ImgUrl) {
            this.task_ImgUrl = task_ImgUrl;
        }

        public String getTask_Img_id() {
            return task_Img_id;
        }

        public void setTask_Img_id(String task_Img_id) {
            this.task_Img_id = task_Img_id;
        }

        public String getTask_description() {
            return task_description;
        }

        public void setTask_description(String task_description) {
            this.task_description = task_description;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public String getTask_name() {
            return task_name;
        }

        public void setTask_name(String task_name) {
            this.task_name = task_name;
        }
    }
}
