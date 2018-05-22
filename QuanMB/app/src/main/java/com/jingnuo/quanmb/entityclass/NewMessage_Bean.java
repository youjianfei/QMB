package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class NewMessage_Bean {

    /**
     * code : 1
     * data : [{"content":"qweqew","type":1},{"content":"帮手隔壁老王愿意出333.0元帮忙","type":2},{"content":"您发起的任务被帮手隔壁老王接单啦!快去确认订单吧 ","type":3},{"content":"暂无新消息","type":4}]
     * msg : 获取消息列表成功
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
         * content : qweqew
         * type : 1
         */

        private String content;
        private int type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
