package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Square_defaultBean {

    /**
     * data : {"list":[{"client_no":"1","commission":1,"createDate":"2018-04-03 17:31:11","createName":"root","img_url":"","release_address":"release_address","task_Img_id":"1","task_StartDate":"2018-04-03 17:31:12","task_Status_code":"1","task_description":"task_EndDate","task_id":2,"task_name":"task_name","task_type":101,"updateDate":"2018-04-03 17:31:12","updateName":"UpdateName","user_reputation":"100"},{"client_no":"1","commission":1,"createDate":"2018-04-03 17:32:54","createName":"root","img_url":"","release_address":"release_address","task_Img_id":"1","task_StartDate":"2018-04-03 17:32:56","task_Status_code":"1","task_description":"task_EndDate","task_id":3,"task_name":"task_name","task_type":101,"updateDate":"2018-04-03 17:32:56","updateName":"UpdateName","user_reputation":"100"}]}
     * message : 获取列表成功
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
             * client_no : 1
             * commission : 1
             * createDate : 2018-04-03 17:31:11
             * createName : root
             * img_url :
             * release_address : release_address
             * task_Img_id : 1
             * task_StartDate : 2018-04-03 17:31:12
             * task_Status_code : 1
             * task_description : task_EndDate
             * task_id : 2
             * task_name : task_name
             * task_type : 101
             * updateDate : 2018-04-03 17:31:12
             * updateName : UpdateName
             * user_reputation : 100
             */

            private String client_no;
            private int commission;
            private String createDate;
            private String createName;
            private String img_url;
            private String release_address;
            private String task_Img_id;
            private String task_StartDate;
            private String task_Status_code;
            private String task_description;
            private int task_id;
            private String task_name;
            private int task_type;
            private String updateDate;
            private String updateName;
            private String user_reputation;

            public String getClient_no() {
                return client_no;
            }

            public void setClient_no(String client_no) {
                this.client_no = client_no;
            }

            public int getCommission() {
                return commission;
            }

            public void setCommission(int commission) {
                this.commission = commission;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getRelease_address() {
                return release_address;
            }

            public void setRelease_address(String release_address) {
                this.release_address = release_address;
            }

            public String getTask_Img_id() {
                return task_Img_id;
            }

            public void setTask_Img_id(String task_Img_id) {
                this.task_Img_id = task_Img_id;
            }

            public String getTask_StartDate() {
                return task_StartDate;
            }

            public void setTask_StartDate(String task_StartDate) {
                this.task_StartDate = task_StartDate;
            }

            public String getTask_Status_code() {
                return task_Status_code;
            }

            public void setTask_Status_code(String task_Status_code) {
                this.task_Status_code = task_Status_code;
            }

            public String getTask_description() {
                return task_description;
            }

            public void setTask_description(String task_description) {
                this.task_description = task_description;
            }

            public int getTask_id() {
                return task_id;
            }

            public void setTask_id(int task_id) {
                this.task_id = task_id;
            }

            public String getTask_name() {
                return task_name;
            }

            public void setTask_name(String task_name) {
                this.task_name = task_name;
            }

            public int getTask_type() {
                return task_type;
            }

            public void setTask_type(int task_type) {
                this.task_type = task_type;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getUpdateName() {
                return updateName;
            }

            public void setUpdateName(String updateName) {
                this.updateName = updateName;
            }

            public String getUser_reputation() {
                return user_reputation;
            }

            public void setUser_reputation(String user_reputation) {
                this.user_reputation = user_reputation;
            }
        }
    }
}
