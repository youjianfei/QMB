package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class ZhidingBean {


    /**
     * code : 1
     * data : {"list":[{"consume":80,"day":"5"},{"consume":112,"day":"7"},{"consume":240,"day":"15"},{"consume":480,"day":"30"}]}
     * msg : 获取天数列表成功
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * consume : 80
             * day : 5
             */

            private int consume;
            private String day;

            public int getConsume() {
                return consume;
            }

            public void setConsume(int consume) {
                this.consume = consume;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }
}
