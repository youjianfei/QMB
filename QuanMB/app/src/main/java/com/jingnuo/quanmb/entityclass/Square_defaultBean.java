package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Square_defaultBean {


    /**
     * data : {"list":[{"commission":50,"createDate":"2018-05-02 19:41:48","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412939&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=e97NNdexBI8FuG19cI380YOW7qA%3D","nick_name":"请问","release_Address":"asdasdasd","specialty_name":"帮买","task_ID":6,"task_Name":"12333","user_reputation":100},{"commission":50,"createDate":"2018-05-02 19:56:29","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412939&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=e97NNdexBI8FuG19cI380YOW7qA%3D","nick_name":"请问","release_Address":"郑州","specialty_name":"帮送","task_ID":8,"task_Name":"天天疼","user_reputation":100},{"commission":50,"createDate":"2018-05-02 19:56:31","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412939&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=e97NNdexBI8FuG19cI380YOW7qA%3D","nick_name":"请问","release_Address":"郑州","specialty_name":"帮送","task_ID":9,"task_Name":"天天疼","user_reputation":100},{"commission":50,"createDate":"2018-05-02 19:56:31","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412939&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=e97NNdexBI8FuG19cI380YOW7qA%3D","nick_name":"请问","release_Address":"郑州","specialty_name":"帮送","task_ID":10,"task_Name":"天天疼","user_reputation":100},{"commission":50,"createDate":"2018-05-02 19:56:31","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412939&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=e97NNdexBI8FuG19cI380YOW7qA%3D","nick_name":"请问","release_Address":"郑州","specialty_name":"帮送","task_ID":11,"task_Name":"天天疼","user_reputation":100},{"commission":50,"createDate":"2018-05-02 19:56:32","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412940&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=embEdjISzB1L1UyJ5Cy3S0dFYkI%3D","nick_name":"请问","release_Address":"郑州","specialty_name":"帮送","task_ID":12,"task_Name":"天天疼","user_reputation":100},{"commission":50,"createDate":"2018-05-02 19:56:32","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412940&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=embEdjISzB1L1UyJ5Cy3S0dFYkI%3D","nick_name":"请问","release_Address":"郑州","specialty_name":"帮送","task_ID":13,"task_Name":"天天疼","user_reputation":100},{"commission":50,"createDate":"2018-05-02 19:56:32","headUrl":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412940&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=embEdjISzB1L1UyJ5Cy3S0dFYkI%3D","nick_name":"请问","release_Address":"郑州","specialty_name":"帮送","task_ID":14,"task_Name":"天天疼","user_reputation":100}]}
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
             * commission : 50
             * createDate : 2018-05-02 19:41:48
             * headUrl : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bf441495-e4e3-4c95-9e88-477c0aaf421dbut_gift_normal.png?Expires=1525412939&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=e97NNdexBI8FuG19cI380YOW7qA%3D
             * nick_name : 请问
             * release_Address : asdasdasd
             * specialty_name : 帮买
             * task_ID : 6
             * task_Name : 12333
             * user_reputation : 100
             */

            private int commission;
            private String createDate;
            private String headUrl;
            private String nick_name;
            private String release_Address;
            private String specialty_name;
            private int task_ID;
            private String task_Name;
            private int user_reputation;

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

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
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

            public int getUser_reputation() {
                return user_reputation;
            }

            public void setUser_reputation(int user_reputation) {
                this.user_reputation = user_reputation;
            }
        }
    }
}
