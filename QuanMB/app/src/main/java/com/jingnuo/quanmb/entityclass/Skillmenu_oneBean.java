package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Skillmenu_oneBean {


    /**
     * data : {"list":[{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-Home@2x.png","specialty_id":113,"specialty_name":"家居日常"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-repair@2x.png","specialty_id":102,"specialty_name":"维修"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-Housekeeping@2x.png","specialty_id":103,"specialty_name":"家政"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-transport@2x.png","specialty_id":106,"specialty_name":"运输"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-business@2x.png","specialty_id":108,"specialty_name":"商务"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-internet@2x.png","specialty_id":104,"specialty_name":"互联网"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-design@2x.png","specialty_id":105,"specialty_name":"设计策划"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-automobile@2x.png","specialty_id":107,"specialty_name":"汽车服务"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-education@2x.png","specialty_id":109,"specialty_name":"教育培训"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-wedding@2x.png","specialty_id":110,"specialty_name":"婚庆摄影"},{"img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-building@2x.png","specialty_id":101,"specialty_name":"建材装修"}]}
     * message : 获取列表成功
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
             * img_url : https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/classify-Home@2x.png
             * specialty_id : 113
             * specialty_name : 家居日常
             */

            private String img_url;
            private int specialty_id;
            private String specialty_name;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
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
        }
    }
}
