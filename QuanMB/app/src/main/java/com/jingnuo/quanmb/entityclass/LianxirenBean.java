package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class LianxirenBean {

    /**
     * code : 1
     * data : {"list":[{"id":2,"is_default":"N","mobile_no":"15838833911","name":"song","sex":0},{"id":1,"is_default":"Y","mobile_no":"15729102602","name":"chen","sex":1}]}
     * msg : 获取常用联系人列表成功
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
             * id : 2
             * is_default : N
             * mobile_no : 15838833911
             * name : song
             * sex : 0
             */

            private int id;
            private String is_default;
            private String mobile_no;
            private String name;
            private int sex;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getMobile_no() {
                return mobile_no;
            }

            public void setMobile_no(String mobile_no) {
                this.mobile_no = mobile_no;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }
        }
    }
}
