package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class SheQuListBean {

    /**
     * code : 1
     * data : [{"area":"郑州市","community_code":"10000000000","community_name":"升龙1","id":1,"status":1},{"area":"郑州市","community_code":"10000000001","community_name":"祥云2","id":2,"status":1},{"area":"郑州市","community_code":"10000000002","community_name":"万科1","id":3,"status":1},{"area":"郑州市","community_code":"10000000003","community_name":"汇锦2","id":4,"status":1},{"area":"郑州市","community_code":"10000000004","community_name":"万达32","id":5,"status":1},{"area":"郑州市区","community_code":"10000000005","community_name":"升龙汇金","id":6,"status":1}]
     * msg : 获取成功
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
         * area : 郑州市
         * community_code : 10000000000
         * community_name : 升龙1
         * id : 1
         * status : 1
         */

        private String area;
        private String community_code;
        private String community_name;
        private int id;
        private int status;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCommunity_code() {
            return community_code;
        }

        public void setCommunity_code(String community_code) {
            this.community_code = community_code;
        }

        public String getCommunity_name() {
            return community_name;
        }

        public void setCommunity_name(String community_name) {
            this.community_name = community_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
