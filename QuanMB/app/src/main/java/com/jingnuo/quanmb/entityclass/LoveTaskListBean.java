package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class LoveTaskListBean {

    /**
     * code : 1
     * data : [{"distance":8421351,"headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","task_description":"女聚聚兔兔","task_id":15,"task_name":"木头某"},{"distance":8421351,"headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","task_description":"joyo兔兔具体","task_id":14,"task_name":"兔兔"},{"distance":8421351,"headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png","task_description":"too婆婆很OK","task_id":13,"task_name":"哦KTV"}]
     * message : 获取数据成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * distance : 8421351
         * headUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/1278718f-7471-4cc4-b18a-7c6b5084fdf01531473456702.png
         * task_description : 女聚聚兔兔
         * task_id : 15
         * task_name : 木头某
         */

        private int distance;
        private String headUrl;
        private String task_description;
        private int task_id;
        private String task_name;
        private String nick_name;
        private String createDate;
        private String specialty_name;

        public String getSpecialty_name() {
            return specialty_name;
        }

        public void setSpecialty_name(String specialty_name) {
            this.specialty_name = specialty_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

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
