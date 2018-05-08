package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class MySkillBean {


    /**
     * code : 1
     * data : {"list":[{"description":"修水管啦啦啦啦啦啦啦啦啦","release_date":"2018-05-03 18:39:34","release_specialty_id":1,"specialty_id":2011,"status":"1","title":"卖"}]}
     * message : 查看服务成功
     */

    private int code;
    private DataBean data;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
             * description : 修水管啦啦啦啦啦啦啦啦啦
             * release_date : 2018-05-03 18:39:34
             * release_specialty_id : 1
             * specialty_id : 2011
             * status : 1
             * title : 卖
             */

            private String description;
            private String release_date;
            private int release_specialty_id;
            private int specialty_id;
            private String status;
            private String title;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getRelease_date() {
                return release_date;
            }

            public void setRelease_date(String release_date) {
                this.release_date = release_date;
            }

            public int getRelease_specialty_id() {
                return release_specialty_id;
            }

            public void setRelease_specialty_id(int release_specialty_id) {
                this.release_specialty_id = release_specialty_id;
            }

            public int getSpecialty_id() {
                return specialty_id;
            }

            public void setSpecialty_id(int specialty_id) {
                this.specialty_id = specialty_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
