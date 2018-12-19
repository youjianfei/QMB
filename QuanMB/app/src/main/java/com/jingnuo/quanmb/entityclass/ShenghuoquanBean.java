package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by 飞 on 2018/12/19.
 */

public class ShenghuoquanBean {


    /**
     * code : 1
     * data : [{"click_url":"","end_date":"2018-12-19 18:38:10","end_date_code":"1545215890","img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/xinxian1.png","now_date_code":"1545219367","start_date":"2018-12-17 18:46:05","start_date_code":"1545043565"},{"click_url":"","end_date":"2018-12-21 18:46:10","end_date_code":"1545389170","img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/xinxian1.png","now_date_code":"1545219367","start_date":"2018-12-20 18:46:05","start_date_code":"1545302765"},{"click_url":"","end_date":"2018-12-21 18:46:10","end_date_code":"1545389170","img_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/xinxian1.png","now_date_code":"1545219367","start_date":"2018-12-18 18:46:05","start_date_code":"1545129965"}]
     * message : 查询信息成功
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
         * click_url :
         * end_date : 2018-12-19 18:38:10
         * end_date_code : 1545215890
         * img_url : https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/xinxian1.png
         * now_date_code : 1545219367
         * start_date : 2018-12-17 18:46:05
         * start_date_code : 1545043565
         */

        private String click_url="";
        private String end_date;
        private String end_date_code;
        private String img_url;
        private String now_date_code;
        private String start_date;
        private String start_date_code;

        public String getClick_url() {
            return click_url;
        }

        public void setClick_url(String click_url) {
            this.click_url = click_url;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getEnd_date_code() {
            return end_date_code;
        }

        public void setEnd_date_code(String end_date_code) {
            this.end_date_code = end_date_code;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getNow_date_code() {
            return now_date_code;
        }

        public void setNow_date_code(String now_date_code) {
            this.now_date_code = now_date_code;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getStart_date_code() {
            return start_date_code;
        }

        public void setStart_date_code(String start_date_code) {
            this.start_date_code = start_date_code;
        }
    }
}
