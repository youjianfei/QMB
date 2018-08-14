package com.jingnuo.quanmb.entityclass;

public class UpdataBean {

    /**
     * code : 1
     * data : {"download_link":"www.baidu.com","version_number":6}
     * msg : 获取版本号成功
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
         * download_link : www.baidu.com
         * version_number : 6
         */

        private String download_link;
        private int version_number;

        public String getDownload_link() {
            return download_link;
        }

        public void setDownload_link(String download_link) {
            this.download_link = download_link;
        }

        public int getVersion_number() {
            return version_number;
        }

        public void setVersion_number(int version_number) {
            this.version_number = version_number;
        }
    }
}
