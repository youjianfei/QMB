package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.quanmb.R;

public class MonryMingxiActivity extends BaseActivityother {

    PullToRefreshListView listView;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_monry_mingxi;
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
        listView=findViewById(R.id.list_mingxi);
    }
}
