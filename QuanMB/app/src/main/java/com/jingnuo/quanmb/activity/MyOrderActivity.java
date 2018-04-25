package com.jingnuo.quanmb.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.jingnuo.quanmb.quanmb.R;

public class MyOrderActivity extends BaseActivityother {
    //控件
    TabLayout  mTablayout;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTablayout=findViewById(R.id.tablayout);
        mTablayout.addTab(mTablayout.newTab().setText("全部").setTag(1));
        mTablayout.addTab(mTablayout.newTab().setText("待接单").setTag(2));
        mTablayout.addTab(mTablayout.newTab().setText("进行中").setTag(3));
        mTablayout.addTab(mTablayout.newTab().setText("已完成").setTag(4));
        mTablayout.addTab(mTablayout.newTab().setText("已关闭").setTag(4));

    }
}
