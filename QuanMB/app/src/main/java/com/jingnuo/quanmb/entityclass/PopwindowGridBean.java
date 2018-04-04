package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class PopwindowGridBean {
    private List<FilterBean> mFilterList;

    public List<FilterBean> getmFilterList() {
        return mFilterList;
    }

    public void setmFilterList(List<FilterBean> mFilterList) {
        this.mFilterList = mFilterList;
    }

    public  static  class  FilterBean{
        int id;
        String text;
        boolean isChoose;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }
    }

}
