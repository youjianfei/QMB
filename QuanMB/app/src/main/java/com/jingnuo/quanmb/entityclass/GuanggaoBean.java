package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class GuanggaoBean {

    /**
     * data : [{"activity_url":"www.baidu.com","id":0,"img_url":"https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/icon/%E5%B9%BF%E5%91%8A%E5%9B%BE1.png"}]
     * message : 获取列表成功
     * status : 1
     */

    private String message;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * activity_url : www.baidu.com
         * id : 0
         * img_url : https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/icon/%E5%B9%BF%E5%91%8A%E5%9B%BE1.png
         */

        private String activity_url="";
        private int id;
        private String img_url;

        public String getActivity_url() {
            return activity_url;
        }

        public void setActivity_url(String activity_url) {
            this.activity_url = activity_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
