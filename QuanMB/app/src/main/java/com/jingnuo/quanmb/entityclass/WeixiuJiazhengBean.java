package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by 飞 on 2018/10/25.
 */

public class WeixiuJiazhengBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * specialty_id : 1201
         * specialty_name : 房屋维修
         * isselect : true
         */

        private int specialty_id;
        private String specialty_name;
        private boolean isselect;


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

        public boolean isIsselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
        }
    }
}
