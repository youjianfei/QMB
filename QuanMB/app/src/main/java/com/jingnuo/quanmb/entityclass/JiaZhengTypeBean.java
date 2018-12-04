package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by 飞 on 2018/12/4.
 */

public class JiaZhengTypeBean {

    private List<TypeBean> type;

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class TypeBean {
        /**
         * xiangmu : 日常保洁
         * price : 50元
         * image : 1
         * isselect : false
         */

        private String xiangmu;
        private String price;
        private int image;
        private boolean isselect;

        public String getXiangmu() {
            return xiangmu;
        }

        public void setXiangmu(String xiangmu) {
            this.xiangmu = xiangmu;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public boolean isIsselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
        }
    }
}
