package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.WoderenzhengBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

public class MyAuthenticationActivity extends BaseActivityother {

    ImageView mImageView_helper;
    ImageView mImageView_shop;
    TextView mTextview_helper_state;
    TextView mTextview_shop_state;
    TextView mTextview_idcard;
    TextView mTextview_shopname;
    RelativeLayout  relativeLayout_shop;

    WoderenzhengBean woderenzhengBean;


    int  type=0;





    @Override
    public int setLayoutResID() {
        return R.layout.activity_my_authentication;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        type=getIntent().getIntExtra("type",0);
        if(type==1){
            relativeLayout_shop.setVisibility(View.GONE);
        }
        request();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        relativeLayout_shop=findViewById(R.id.relativealyout_shop);
        mImageView_helper=findViewById(R.id.iamge_1);
        mImageView_shop=findViewById(R.id.iamge_2);
        mTextview_helper_state=findViewById(R.id.renzheng_state);
        mTextview_shop_state=findViewById(R.id.renzheng_state2);
        mTextview_idcard=findViewById(R.id.text_idcard);
        mTextview_shopname=findViewById(R.id.text_qiyeaddress);


    }
    void request(){
        LogUtils.LOG("ceshi",Urls.Baseurl+Urls.woderenzheng+ Staticdata.static_userBean.getData().getUser_token()+
                "&client_no=" +Staticdata.static_userBean.getData().getAppuser().getClient_no(),"qode认证");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"qode认证");

                woderenzhengBean=new Gson().fromJson(respose,WoderenzhengBean.class);

                if(woderenzhengBean.getData().getHelper_auth_status()==1){
                    mTextview_helper_state.setText("已认证");
                    mTextview_idcard.setText(woderenzhengBean.getData().getHelper_cer_no());
                    Glide.with(MyAuthenticationActivity.this).load(woderenzhengBean.getData().getHelper_avatar_url()).into(mImageView_helper);
                }
                if(woderenzhengBean.getData().getBusiness_auth_status()==1){
                    mTextview_shop_state.setText("已认证");
                    mTextview_shopname.setText(woderenzhengBean.getData().getBusiness_name());
                    Glide.with(MyAuthenticationActivity.this).load(woderenzhengBean.getData().getBusiness_avatar_url()).into(mImageView_shop);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.woderenzheng+ Staticdata.static_userBean.getData().getUser_token()+
        "&client_no=" +Staticdata.static_userBean.getData().getAppuser().getClient_no(),
                MyAuthenticationActivity.this,0);
    }
}
