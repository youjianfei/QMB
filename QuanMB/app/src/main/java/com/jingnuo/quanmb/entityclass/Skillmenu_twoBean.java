package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class Skillmenu_twoBean {
    /**
     * data : {"list":[{"img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/%E5%B8%AE%E9%80%81.png?Expires=1525865362&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=p4OtMua0UEcqYiZq865l%2Bi6GQlk%3D","specialty_id":2011,"specialty_name":"帮送"},{"img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/%E9%A2%84%E7%BA%A6.png?Expires=1525865362&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=B0Qaej5SRe%2BAxh3cWCCDPl8lXHw%3D","specialty_id":2012,"specialty_name":"帮预约"},{"img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/%E6%8E%92%E9%98%9F.png?Expires=1525865362&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=2VwGoFCAYLaZ308NbCfIJUBywlw%3D","specialty_id":2013,"specialty_name":"帮排队"},{"img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/%E5%B8%AE%E4%B9%B0.png?Expires=1525865362&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=fvuY4AqzykDgBrPLxp7EtS9zsNI%3D","specialty_id":2014,"specialty_name":"帮买"}]}
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
             * img_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/specialtyImg/%E5%B8%AE%E9%80%81.png?Expires=1525865362&OSSAccessKeyId=LTAIcYmxp0FtpOf4&Signature=p4OtMua0UEcqYiZq865l%2Bi6GQlk%3D
             * specialty_id : 2011
             * specialty_name : 帮送
             */

            private String img_url;
            private int specialty_id;
            private String specialty_name;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

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
