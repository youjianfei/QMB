package com.jingnuo.quanmb.entityclass;

/**
 * Created by 飞 on 2018/12/28.
 */

public class YaoyiyaoBean {

    /**
     * data : {"begin":500,"end":9000,"id":2,"img_url":"0","prize_name":"50积分奖励"}
     * message : 获取信息成功
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
        /**
         * begin : 500
         * end : 9000
         * id : 2
         * img_url : 0
         * prize_name : 50积分奖励
         */

        private int begin;
        private int end;
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
