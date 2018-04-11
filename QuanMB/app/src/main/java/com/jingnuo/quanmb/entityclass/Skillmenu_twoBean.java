package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Skillmenu_twoBean {


    /**
     * data : {"list":[{"specialty_id":2011,"specialty_name":"帮送"},{"specialty_id":2012,"specialty_name":"帮预约"},{"specialty_id":2013,"specialty_name":"帮排队"},{"specialty_id":2014,"specialty_name":"帮买"}]}
     * message : 获取2级列表成功
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * specialty_id : 2011
             * specialty_name : 帮送
             */

            private int specialty_id;
            private String specialty_name;

            public int getSpecialty_id() {
                return specialty_id;
            }

            public void setSpecialty_id(int specialty_id) {
                this.specialty_id = specialty_id;
            }

            public String getSpecialty_name() {
                return specialty_name;
            }

            public void setSpecialty_name(String specialty_name) {
                this.specialty_name = specialty_name;
            }
        }
    }
}
