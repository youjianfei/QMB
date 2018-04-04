package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class TaskTypeBean {

    /**
     * date : {"list":[{"specialty_id":101,"specialty_name":"同城帮"},{"specialty_id":102,"specialty_name":"维修"},{"specialty_id":103,"specialty_name":"家政"},{"specialty_id":104,"specialty_name":"互联网"},{"specialty_id":105,"specialty_name":"设计"},{"specialty_id":106,"specialty_name":"运输"},{"specialty_id":107,"specialty_name":"代购"},{"specialty_id":108,"specialty_name":"商务"},{"specialty_id":109,"specialty_name":"其他"}]}
     * message : 获取列表成功
     * status : 1
     */

    private DateBean date;
    private String message;
    private int status;

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
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

    public static class DateBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * specialty_id : 101
             * specialty_name : 同城帮
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
