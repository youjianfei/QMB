package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class BargainMessageListBean {


    /**
     * code : 1
     * data : [{"Avatar_img_id":"252","Id":120,"binding_id":"88","content":"帮手隔壁老王愿意出35.0元帮忙","createDate":"2018-05-16 15:31:03","createName":"root@115.57.138.7","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png","receive_client_no":"90000000005","send_client_no":"90000000008","status":"2","title":"帮买大盘鸡","type":"2","updateDate":"2018-05-16 15:31:03"},{"Avatar_img_id":"252","Id":113,"binding_id":"86","content":"帮手隔壁老王愿意出500.0元帮忙","createDate":"2018-05-16 14:08:23","createName":"root@115.57.138.7","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png","receive_client_no":"90000000005","send_client_no":"90000000008","status":"2","title":"修理电视机","type":"2","updateDate":"2018-05-16 14:08:23"},{"Avatar_img_id":"252","Id":109,"binding_id":"84","content":"帮手隔壁老王愿意出35.0元帮忙","createDate":"2018-05-16 09:05:01","createName":"root@115.57.138.7","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png","receive_client_no":"90000000005","send_client_no":"90000000008","status":"2","title":"666","type":"2","updateDate":"2018-05-16 09:05:01"},{"Avatar_img_id":"252","Id":107,"binding_id":"83","content":"用户:威震天同意了您的还价请求","createDate":"2018-05-15 20:47:53","createName":"root@115.57.138.7","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png","receive_client_no":"90000000005","send_client_no":"90000000008","status":"2","title":"qqq","type":"2","updateDate":"2018-05-15 20:47:53"},{"Avatar_img_id":"252","Id":105,"binding_id":"82","content":"帮手隔壁老王愿意出36.0元帮忙","createDate":"2018-05-15 19:48:52","createName":"root@115.57.138.7","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png","receive_client_no":"90000000005","send_client_no":"90000000008","status":"2","title":"qqq","type":"2","updateDate":"2018-05-15 19:48:52"},{"Avatar_img_id":"252","Id":104,"binding_id":"81","content":"帮手隔壁老王愿意出36.0元帮忙","createDate":"2018-05-15 19:46:30","createName":"root@115.57.138.7","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png","receive_client_no":"90000000005","send_client_no":"90000000008","status":"2","title":"456345","type":"2","updateDate":"2018-05-15 19:46:30"},{"Avatar_img_id":"252","Id":103,"binding_id":"80","content":"帮手隔壁老王愿意出36.0元帮忙","createDate":"2018-05-15 19:44:42","createName":"root@115.57.138.7","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png","receive_client_no":"90000000005","send_client_no":"90000000008","status":"2","title":"456345","type":"2","updateDate":"2018-05-15 19:44:42"},{"Avatar_img_id":"252","Id":102,"binding_id":"79","content":"帮手隔壁老王愿意出35.0元帮忙","createDate":"2018-05-15 19:41:28","createName":"root@115.57.138.7","img_url":"http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png","receive_client_no":"90000000005","send_client_no":"90000000008","status":"2","title":"666","type":"2","updateDate":"2018-05-15 19:41:28"}]
     * msg : 获取消息列表成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Avatar_img_id : 252
         * Id : 120
         * binding_id : 88
         * content : 帮手隔壁老王愿意出35.0元帮忙
         * createDate : 2018-05-16 15:31:03
         * createName : root@115.57.138.7
         * img_url : http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/bd7a959d-0e41-41ce-81fb-0853bcce97b50004.png
         * receive_client_no : 90000000005
         * send_client_no : 90000000008
         * status : 2
         * title : 帮买大盘鸡
         * type : 2
         * updateDate : 2018-05-16 15:31:03
         */

        private String Avatar_img_id;
        private int Id;
        private String binding_id;
        private String content;
        private String createDate;
        private String createName;
        private String img_url;
        private String receive_client_no;
        private String send_client_no;
        private String status;
        private String title;
        private String type;
        private String updateDate;

        public String getAvatar_img_id() {
            return Avatar_img_id;
        }

        public void setAvatar_img_id(String Avatar_img_id) {
            this.Avatar_img_id = Avatar_img_id;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getBinding_id() {
            return binding_id;
        }

        public void setBinding_id(String binding_id) {
            this.binding_id = binding_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getReceive_client_no() {
            return receive_client_no;
        }

        public void setReceive_client_no(String receive_client_no) {
            this.receive_client_no = receive_client_no;
        }

        public String getSend_client_no() {
            return send_client_no;
        }

        public void setSend_client_no(String send_client_no) {
            this.send_client_no = send_client_no;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    }
}
