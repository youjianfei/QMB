package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.jingnuo.quanmb.Adapter.Adapter_Coupon;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.customview.MyListView;

import java.util.ArrayList;
import java.util.List;

public class CouponActivity extends BaseActivityother {

    ListView mylistview_coupon;

    List<String> mData;



    Adapter_Coupon  adapter_coupon;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mData=new ArrayList<>();
        mData.add("56");
        mData.add("120");
        mData.add("56");
        mData.add("5");
        mData.add("51");
        mData.add("31");
        mData.add("61");
        mData.add("11");
        mData.add("51");
        adapter_coupon=new Adapter_Coupon(mData,this);
        mylistview_coupon.setAdapter(adapter_coupon);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mylistview_coupon=findViewById(R.id.Mylistview_coupon);
    }
}
