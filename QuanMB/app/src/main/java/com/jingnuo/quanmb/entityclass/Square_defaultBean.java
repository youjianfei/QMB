package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Square_defaultBean {


    /**
     * data : {"list":[{"client_no":"2147483681","commission":123,"createDate":"2018-05-03 19:38:39","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/d2389f24-a789-4d0e-9760-442ab37386b4hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525484590&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=allMbmC%2BKCqfENWu4raWqCJ2YUU%3D","release_Address":"郑州","specialty_name":"房屋","task_ID":9,"task_Name":"12312","task_description":"123","user_reputation":100},{"client_no":"2147483672","commission":50,"createDate":"2018-05-04 09:13:43","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/316e6c34-62bb-4be0-a628-f75f5dbcec70mmexport1525309537560.jpg?Expires=1525484590&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=3aCv2F5CLU3rTIuI3VvWKCLsvL0%3D","release_Address":"郑州","specialty_name":"帮买","task_ID":10,"task_Name":"买个囊","task_description":"着急用","user_reputation":100},{"client_no":"2147483678","commission":63,"createDate":"2018-05-04 09:40:00","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/39ae9297-2376-4668-a92d-33056c9af298MYXJ_20180501183347_fast.jpg?Expires=1525484590&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=OcXF%2BlLATUeUsBdkBR02oFo94ik%3D","release_Address":"郑州","specialty_name":"玻璃","task_ID":11,"task_Name":"sdafasdf","task_description":"sadfsadfsadf","user_reputation":100}]}
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
             * client_no : 2147483681
             * commission : 123
             * createDate : 2018-05-03 19:38:39
             * headUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/d2389f24-a789-4d0e-9760-442ab37386b4hdImg_c196266f837d14e0b693f961bee37b661525346391833.jpg?Expires=1525484590&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=allMbmC%2BKCqfENWu4raWqCJ2YUU%3D
             * release_Address : 郑州
             * specialty_name : 房屋
             * task_ID : 9
             * task_Name : 12312
             * task_description : 123
             * user_reputation : 100
             */

            private String client_no="";
            private double commission;
            private String createDate;
            private String task_Startdate;
            private String headUrl;
            private String release_Address;
            private String specialty_name;
            private int task_ID;
            private String task_Name;
            private String task_description;
            private String is_helper_bid;
            private String nick_name="全民帮";
            private int user_reputation;
            private int distance=0;

            public String getTask_Startdate() {
                return task_Startdate;
            }

            public void setTask_Startdate(String task_Startdate) {
                this.task_Startdate = task_Startdate;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getIs_helper_bid() {
                return is_helper_bid;
            }

            public void setIs_helper_bid(String is_helper_bid) {
                this.is_helper_bid = is_helper_bid;
            }

            public String getClient_no() {
                return client_no;
            }

            public void setClient_no(String client_no) {
                this.client_no = client_no;
            }

            public double getCommission() {
                return commission;
            }

            public void setCommission(double commission) {
                this.commission = commission;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getRelease_Address() {
                return release_Address;
            }

            public void setRelease_Address(String release_Address) {
                this.release_Address = release_Address;
            }

            public String getSpecialty_name() {
                return specialty_name;
            }

            public void setSpecialty_name(String specialty_name) {
                this.specialty_name = specialty_name;
            }

            public int getTask_ID() {
                return task_ID;
            }

            public void setTask_ID(int task_ID) {
                this.task_ID = task_ID;
            }

            public String getTask_Name() {
                return task_Name;
            }

            public void setTask_Name(String task_Name) {
                this.task_Name = task_Name;
            }

            public String getTask_description() {
                return task_description;
            }

            public void setTask_description(String task_description) {
                this.task_description = task_description;
            }

            public int getUser_reputation() {
                return user_reputation;
            }

            public void setUser_reputation(int user_reputation) {
                this.user_reputation = user_reputation;
            }
        }
    }
}
