package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.ShopcenterBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;


public class ShopCenterActivity extends BaseActivityother {
    //控件
    TextView mTextview_shopname;  //商铺名字
    TextView mTextview_address;//地址
    TextView mTextview_money;//佣金

    RelativeLayout mRealtivelayout_issue;
    RelativeLayout mRealtivelayout_myissue;
    RelativeLayout mRealtivelayout_myorder;
    RelativeLayout mRealtivelayout_myauthentication;

    //对象
    ShopcenterBean shopcenterBean;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_center;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        requestshopcenter();

    }

    @Override
    protected void initListener() {
        mRealtivelayout_issue.setOnClickListener(this);
        mRealtivelayout_myissue.setOnClickListener(this);
        mRealtivelayout_myorder.setOnClickListener(this);
        mRealtivelayout_myauthentication.setOnClickListener(this);

    }

    @Override
    protected void initView() {
        mTextview_shopname = findViewById(R.id.text_shopname);
        mTextview_address = findViewById(R.id.textview_address);
        mTextview_money = findViewById(R.id.textview_money);
        mRealtivelayout_issue = findViewById(R.id.relative_issuetask);
        mRealtivelayout_myissue = findViewById(R.id.relative_myissue);
        mRealtivelayout_myorder = findViewById(R.id.myorder);
        mRealtivelayout_myauthentication = findViewById(R.id.myauthentication);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.relative_issuetask:
                Intent intend_issue_skill=new Intent(ShopCenterActivity.this, IssueSkillActivity.class);
                ShopCenterActivity.this.startActivity(intend_issue_skill);
                break;

            case R.id.relative_myissue:


                break;

            case R.id.myorder:

                break;

            case R.id.myauthentication:

                break;
        }


    }

    void requestshopcenter() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi","商户中心："+respose,"ShopCenterActivity");
                shopcenterBean = new Gson().fromJson(respose, ShopcenterBean.class);
                mTextview_shopname.setText(shopcenterBean.getData().getList().getBusiness_name());
                mTextview_address.setText(shopcenterBean.getData().getList().getBusiness_address());

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.shopcenter + Staticdata.static_userBean.getData()
                .getUser_token() + "&client_no=" + Staticdata.static_userBean.getData().getAppuser()
                .getClient_no(), ShopCenterActivity.this, 0);
    }
}
