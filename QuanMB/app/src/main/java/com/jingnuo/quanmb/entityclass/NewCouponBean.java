package com.jingnuo.quanmb.entityclass;

/**
 * Created by 飞 on 2018/11/2.
 */

public class NewCouponBean {

    /**
     * code : 1
     * data : {"bk_img":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/home-activity-bk-20181101.png","but_img":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/home-activity-but-20181101.png","but_url":"https://www.baidu.com","coupon_code":"SCDFFFF","endDate":"2018-11-30 23:59:59","isShow":1,"startDate":"2018-11-01 00:00:00"}
     * message : 获取列表成功
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
         * bk_img : https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/home-activity-bk-20181101.png
         * but_img : https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/home-activity-but-20181101.png
         * but_url : https://www.baidu.com
         * coupon_code : SCDFFFF
         * endDate : 2018-11-30 23:59:59
         * isShow : 1
         * startDate : 2018-11-01 00:00:00
         */

        private String bk_img;
        private String but_img;
        private String but_url;
        private String coupon_code;
        private String endDate;
        private String isShow;
        private String startDate;

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getBk_img() {
            return bk_img;
        }

        public void setBk_img(String bk_img) {
            this.bk_img = bk_img;
        }

        public String getBut_img() {
            return but_img;
        }

        public void setBut_img(String but_img) {
            this.but_img = but_img;
        }

        public String getBut_url() {
            return but_url;
        }

        public void setBut_url(String but_url) {
            this.but_url = but_url;
        }

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }



        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }
}
