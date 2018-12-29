package com.jingnuo.quanmb.entityclass;

/**
 * Created by 飞 on 2018/12/28.
 */

public class YaoyiyaoBean {


    /**
     * code : 1
     * data : {"begin":1500,"end":10000,"gif_url":"https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/fudai4.png","id":4,"img_url":"0https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/zhongjiang4.png","prize_name":"30元余额红包"}
     * message : 获取信息成功
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
         * begin : 1500
         * end : 10000
         * gif_url : https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/fudai4.png
         * id : 4
         * img_url : 0https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/zhongjiang4.png
         * prize_name : 30元余额红包
         */

        private int begin;
        private int end;
        private String gif_url;
        private int id;
        private String img_url;
        private String prize_name;

        public int getBegin() {
            return begin;
        }

        public void setBegin(int begin) {
            this.begin = begin;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public String getGif_url() {
            return gif_url;
        }

        public void setGif_url(String gif_url) {
            this.gif_url = gif_url;
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

        public String getPrize_name() {
            return prize_name;
        }

        public void setPrize_name(String prize_name) {
            this.prize_name = prize_name;
        }
    }
}
