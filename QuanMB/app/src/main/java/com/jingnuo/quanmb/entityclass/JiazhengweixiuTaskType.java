package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class JiazhengweixiuTaskType {

    /**
     * code : 1
     * data : [{"specialty_id":1201,"specialty_name":"房屋维修"},{"specialty_id":1202,"specialty_name":"家具维修"},{"specialty_id":1203,"specialty_name":"电脑维修"},{"specialty_id":1204,"specialty_name":"手机维修"},{"specialty_id":1205,"specialty_name":"空调维修"},{"specialty_id":1206,"specialty_name":"数码维修"},{"specialty_id":1207,"specialty_name":"管道疏通"},{"specialty_id":1208,"specialty_name":"电路维修"},{"specialty_id":1209,"specialty_name":"开锁修锁"},{"specialty_id":1210,"specialty_name":"家电维修"},{"specialty_id":1211,"specialty_name":"其他"},{"specialty_id":1300,"specialty_name":"家庭保洁"},{"specialty_id":1301,"specialty_name":"土地开荒"},{"specialty_id":1302,"specialty_name":"擦油烟机"},{"specialty_id":1303,"specialty_name":"擦玻璃"},{"specialty_id":1304,"specialty_name":"地板养护"},{"specialty_id":1305,"specialty_name":"搬家"},{"specialty_id":1306,"specialty_name":"送水"},{"specialty_id":1307,"specialty_name":"小时工"},{"specialty_id":1308,"specialty_name":"保姆"},{"specialty_id":1309,"specialty_name":"月嫂"},{"specialty_id":1310,"specialty_name":"其他"}]
     * message : 获取列表成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * specialty_id : 1201
         * specialty_name : 房屋维修
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
