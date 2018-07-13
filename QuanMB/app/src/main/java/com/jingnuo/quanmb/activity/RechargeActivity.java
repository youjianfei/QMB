package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;
import com.jingnuo.quanmb.broadcastrReceiver.PaySuccessOrErroBroadcastReciver;
import com.jingnuo.quanmb.class_.WechatPay;
import com.jingnuo.quanmb.class_.ZhifubaoPay;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.MoneyTextWatcher;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

public class RechargeActivity extends BaseActivityother {

    //控件
    EditText mEditview_amount;
    RelativeLayout mRelativelayout_wechat;
    RelativeLayout mRelayout_zhifubao;
    ImageView mImageview_weixin;
    ImageView mImageview_zhidubao;
    Button mbutton_queren;

    private IntentFilter intentFilter_paysuccess;//定义广播过滤器；
    private PaySuccessOrErroBroadcastReciver paysuccess_BroadcastReciver;//定义广播监听器


    //数据
    int pay=1;   //1  wechat    2  zhifubao

    String amount="";

    private IWXAPI api;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void setData() {
        intentFilter_paysuccess = new IntentFilter();
        intentFilter_paysuccess.addAction("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
        paysuccess_BroadcastReciver=new PaySuccessOrErroBroadcastReciver(new Interface_paySuccessOrerro() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "payResult");
                if(respose.equals("success")){//支付成功
                    Intent intent=new Intent(RechargeActivity.this,PaySuccessActivity.class);
                    intent.putExtra("title","充值成功");
                    intent.putExtra("typesuccess","充值成功");
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(RechargeActivity.this, "支付失败");
                    }
                });
            }
        });
        registerReceiver(paysuccess_BroadcastReciver, intentFilter_paysuccess); //将广播监听器和过滤器注册在一起；
    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(RechargeActivity.this, Staticdata.WechatApi);//微信支付用到
        mImageview_weixin.setSelected(true);

    }

    @Override
    protected void initListener() {
        mRelativelayout_wechat.setOnClickListener(this);
        mRelayout_zhifubao.setOnClickListener(this);
        mbutton_queren.setOnClickListener(this);
        mEditview_amount.addTextChangedListener(new MoneyTextWatcher(mEditview_amount).setDigits(2));
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
                if(amount.equals("")||amount.equals(".")||amount.startsWith("00")){
                    ToastUtils.showToast(this,"请输入正确金额");
                }else {
                    if(pay==1){
                        //微信支付
                        Map map_pay=new HashMap();
                        map_pay.put("isrecharge","Y");
                        map_pay.put("body","充值");
                        map_pay.put("total_fee",amount);
                        map_pay.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                        map_pay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                        map_pay.put("task_id","0");
                        LogUtils.LOG("ceshi",map_pay.toString(),"充值");
                        new WechatPay(RechargeActivity.this,api,map_pay).wepay();
                        return;
                    }
                    if(pay==2){
                        //支付宝支付
                        Map map_zpay=new HashMap();
                        map_zpay.put("isrecharge","Y");
                        map_zpay.put("subject","充值");
                        map_zpay.put("total_fee",amount);
                        map_zpay.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                        map_zpay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                        map_zpay.put("task_id","0");
                        LogUtils.LOG("ceshi",map_zpay.toString(),"支付宝qingqiu接口");
                        LogUtils.LOG("ceshi", Urls.Baseurl_hu+Urls.zhifubaoPay,"支付宝qingqiu接口");
                        new ZhifubaoPay(RechargeActivity.this,map_zpay).zhifubaoPay();
                        return;
                    }

                }
                break;

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paysuccess_BroadcastReciver);
    }
}
