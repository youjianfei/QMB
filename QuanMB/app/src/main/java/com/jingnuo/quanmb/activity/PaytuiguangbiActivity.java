package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.class_.WechatPay;
import com.jingnuo.quanmb.class_.ZhifubaoPay;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

public class PaytuiguangbiActivity extends BaseActivityother {
    //控件
    TextView mEditview_amount;
    RelativeLayout mRelativelayout_wechat;
    RelativeLayout mRelayout_zhifubao;
    ImageView mImageview_weixin;
    ImageView mImageview_zhidubao;
    Button mbutton_queren;

    //数据
    int pay=1;   //1  wechat    2  zhifubao

    String amount="";//金额
    String Task_id="";
    String total_spreadcoin="";//发送推广币个数


    private IWXAPI api;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_paytuiguangbi;
    }

    @Override
    protected void setData() {
        mEditview_amount.setText(amount);
        if (Staticdata.static_userBean.getData().getAppuser().getRole().equals("1")){//帮手
            Task_id=2+"";
        }
        if (Staticdata.static_userBean.getData().getAppuser().getRole().equals("2")){//商户
            Task_id=3+"";
        }

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        amount=intent.getStringExtra("amount");
        total_spreadcoin=intent.getStringExtra("total_spreadcoin");
        api = WXAPIFactory.createWXAPI(PaytuiguangbiActivity.this, Staticdata.WechatApi);//微信支付用到
        mImageview_weixin.setSelected(true);
    }

    @Override
    protected void initListener() {
        mRelativelayout_wechat.setOnClickListener(this);
        mRelayout_zhifubao.setOnClickListener(this);
        mbutton_queren.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mEditview_amount=findViewById(R.id.edit_rechangeAmount);
        mRelativelayout_wechat=findViewById(R.id.relayout_wechat);
        mRelayout_zhifubao=findViewById(R.id.relayout_zhifubao);
        mImageview_weixin=findViewById(R.id.image_wechat);
        mImageview_zhidubao=findViewById(R.id.image_zhifubao);
        mbutton_queren=findViewById(R.id.button_queren);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.relayout_wechat:
                mImageview_weixin.setSelected(true);
                mImageview_zhidubao.setSelected(false);
                pay=1;

                break;
            case R.id.relayout_zhifubao:
                mImageview_weixin.setSelected(false);
                mImageview_zhidubao.setSelected(true);
                pay=2;
                break;
            case R.id.button_queren:
                amount=mEditview_amount.getText()+"";
                    if(pay==1){
                        //微信支付
                        Map map_pay=new HashMap();
                        map_pay.put("isrecharge","Y");
                        map_pay.put("body","推广币套餐");
                        map_pay.put("total_fee",amount);
                        map_pay.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                        map_pay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                        map_pay.put("task_id",Task_id);//充值推广币  2
                        map_pay.put("total_spreadcoin",total_spreadcoin);//推广币的个数
                        LogUtils.LOG("ceshi",map_pay.toString(),"充值");
                        new WechatPay(PaytuiguangbiActivity.this,api,map_pay).wepay();
                        return;
                    }
                    if(pay==2){
                        //支付宝支付
                        Map map_zpay=new HashMap();
                        map_zpay.put("isrecharge","Y");
                        map_zpay.put("subject","推广币套餐");
                        map_zpay.put("total_fee","0.01");
                        map_zpay.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                        map_zpay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                        map_zpay.put("task_id",Task_id);
                        map_zpay.put("total_spreadcoin",total_spreadcoin);
                        LogUtils.LOG("ceshi",map_zpay.toString(),"支付宝推广币接口");
                        LogUtils.LOG("ceshi", Urls.Baseurl_hu+Urls.zhifubaoPay,"支付宝qingqiu接口");
                        new ZhifubaoPay(PaytuiguangbiActivity.this,map_zpay).zhifubaoPay();
                        return;
                    }

                break;

        }
    }
}
