package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.NewMessage_Bean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.HashMap;
import java.util.Map;

public class MessageActivity extends BaseActivityother {

    //控件
    RelativeLayout mRelativelayout_bargain;
    RelativeLayout mRelativelayout_systemmessage;
    RelativeLayout mRelativelayout_dealmessage;
    RelativeLayout mRelativelayout_tuijianrenwu;

    TextView mTextview_systemmessage;
    TextView mTextview_bargainmessage;
    TextView mTextview_jiaoyimeaage;
    TextView mTextview_tuijianrenwu;

    static ImageView mImageView_dot1;
    static ImageView mImageView_dot2;
    static ImageView mImageView_dot3;
    static ImageView mImageView_dot4;


    //数据
    Map map_getnewmessage;

    NewMessage_Bean newMessage_bean;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_message;
    }

    @Override
    protected void setData() {
        map_getnewmessage=new HashMap();
        map_getnewmessage.put("receive_client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_getnewmessage.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        request();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mRelativelayout_bargain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend_bargain=new Intent(MessageActivity.this, BarginmessageListActivity.class);
                mImageView_dot2.setVisibility(View.GONE);
                startActivity(intend_bargain);
            }
        });
        mRelativelayout_systemmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_system=new Intent(MessageActivity.this, SystemMessageActivity.class);
                mImageView_dot1.setVisibility(View.GONE);
                startActivity(intent_system);
            }
        });
        mRelativelayout_dealmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_deal=new Intent(MessageActivity.this, DealActivity.class);
                mImageView_dot3.setVisibility(View.GONE);
                startActivity(intent_deal);
            }
        });
        mRelativelayout_tuijianrenwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tuijian=new Intent(MessageActivity.this, TuijianrenwuActivity.class);
                mImageView_dot4.setVisibility(View.GONE);
                startActivity(intent_tuijian);
            }
        });
    }

    @Override
    protected void initView() {
        mRelativelayout_bargain=findViewById(R.id.relativelayout_Kanprice);
        mRelativelayout_systemmessage=findViewById(R.id.relativelayout_systemnotice);
        mRelativelayout_dealmessage=findViewById(R.id.relativelayout_Jiaoyinotice);
        mRelativelayout_tuijianrenwu=findViewById(R.id.relativelayout_tuijianrenwu);
        mTextview_systemmessage=findViewById(R.id.text_systemnotice);
        mTextview_bargainmessage=findViewById(R.id.text_moneynotice);
        mTextview_jiaoyimeaage=findViewById(R.id.text_jiaoyitixing);
        mTextview_tuijianrenwu=findViewById(R.id.text_tujianrenwu);
        mImageView_dot1=findViewById(R.id.image_reddot1);
        mImageView_dot2=findViewById(R.id.image_reddot2);
        mImageView_dot3=findViewById(R.id.image_reddot3);
        mImageView_dot4=findViewById(R.id.image_reddot4);
    }
    public   void setDot(int num){
        LogUtils.LOG("ceshi",num+"","推送3");
        switch (num){
            case 1:
                mImageView_dot1.setVisibility(View.VISIBLE);
                break;
            case 2:
                mImageView_dot2.setVisibility(View.VISIBLE);
                break;
            case 3:
                mImageView_dot3.setVisibility(View.VISIBLE);
                break;
            case 4:
                mImageView_dot4.setVisibility(View.VISIBLE);
                break;
        }
    }
    void request(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                newMessage_bean=new Gson().fromJson(respose,NewMessage_Bean.class);
                mTextview_systemmessage.setText(newMessage_bean.getData().get(0).getContent());
                mTextview_bargainmessage.setText(newMessage_bean.getData().get(1).getContent());
                mTextview_jiaoyimeaage.setText(newMessage_bean.getData().get(2).getContent());
                mTextview_tuijianrenwu.setText(newMessage_bean.getData().get(3).getContent());
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.getNewmessage,MessageActivity.this,1,map_getnewmessage);

    }
    @Override
    public void onResume() {
        super.onResume();
        if(Staticdata.isLogin){
            map_getnewmessage.put("receive_client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
            map_getnewmessage.put("user_token",Staticdata.static_userBean.getData().getUser_token());
            request();
        }
    }
}
