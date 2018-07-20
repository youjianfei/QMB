package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.broadcastrReceiver.PaySuccessOrErroBroadcastReciver;
import com.jingnuo.quanmb.class_.WechatPay;
import com.jingnuo.quanmb.class_.ZhifubaoPay;
import com.jingnuo.quanmb.customview.PayFragment;
import com.jingnuo.quanmb.customview.PayPwdView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PayBaozhengmoneyActivity extends BaseActivityother implements PayPwdView.InputCallBack{
    //控件
    RelativeLayout mRelativeLayout_yue;
    RelativeLayout mRelativeLayout_wechat;
    RelativeLayout mRelativeLayout_zhifubao;

    ImageView image_yue;
    ImageView image_wechat;
    ImageView image_zhifubao;

    Button mButton_submit;

    PayFragment fragment;
    double balance=0;//余额
    int pay=1;  //1 余额支付 2 微信支付  3 支付宝支付
    String title_pay="缴纳保证金";
    private IWXAPI api;

    private IntentFilter intentFilter_paysuccess;//定义广播过滤器；
    private PaySuccessOrErroBroadcastReciver paysuccess_BroadcastReciver;//定义广播监听器
    @Override
    public int setLayoutResID() {
        return R.layout.activity_pay_baozhengmoney;
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
                    new  Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi",respose,"缴纳保证金成功");
                            int status = 0;
                            String msg = "";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");//
                                msg = (String) object.get("msg");//
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ToastUtils.showToast(PayBaozhengmoneyActivity.this,msg);
                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).Http(Urls.Baseurl_hu+Urls.BaoSuccess+Staticdata.static_userBean.getData().getUser_token()+
                    "&client_no="+Staticdata.static_userBean.getData().getAppuser().getClient_no(),PayBaozhengmoneyActivity.this,0);
                }
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(PayBaozhengmoneyActivity.this, "支付失败");
                    }
                });
            }
        });
        registerReceiver(paysuccess_BroadcastReciver, intentFilter_paysuccess); //将广播监听器和过滤器注册在一起；
    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(PayBaozhengmoneyActivity.this, Staticdata.WechatApi);//微信支付用到
        requestYue();
        image_yue.setSelected(true);
    }

    @Override
    protected void initListener() {
        mRelativeLayout_yue.setOnClickListener(this);
        mRelativeLayout_wechat.setOnClickListener(this);
        mRelativeLayout_zhifubao.setOnClickListener(this);
        mButton_submit.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mRelativeLayout_yue=findViewById(R.id.relayoutyue);
        mRelativeLayout_wechat=findViewById(R.id.relayout_wechat);
        mRelativeLayout_zhifubao=findViewById(R.id.relayout_zhifubao);
        image_yue = findViewById(R.id.image_yue);
        image_wechat = findViewById(R.id.image_wechat);
        image_zhifubao = findViewById(R.id.image_zhifubao);
        mButton_submit=findViewById(R.id.button_submit);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.relayoutyue:
                if(balance<99){
                    ToastUtils.showToast(this,"余额不足");
                    return;
                }
                image_yue.setSelected(true);
                image_wechat.setSelected(false);
                image_zhifubao.setSelected(false);
                pay=1;
                break;
            case R.id.relayout_wechat:
                image_yue.setSelected(false);
                image_wechat.setSelected(true);
                image_zhifubao.setSelected(false);
                pay=2;
                break;
            case R.id.relayout_zhifubao:
                image_yue.setSelected(false);
                image_wechat.setSelected(false);
                image_zhifubao.setSelected(true);
                pay=3;
                break;
            case R.id.button_submit:
                if(pay==1){
                    if(Staticdata.static_userBean.getData().getAppuser().getSecurity_code().equals("")){
                        ToastUtils.showToast(this,"请先设置安全密码");
                        Intent intent_setsafe=new Intent(PayBaozhengmoneyActivity.this,SetSafepassword1Activity.class);
                        intent_setsafe.putExtra("change","nochange");
                        startActivity(intent_setsafe);
                        return;
                    }
                    //余额支付
                    Bundle bundle = new Bundle();
                    bundle.putString(PayFragment.EXTRA_CONTENT, "缴纳保证金"+"：¥ " + 99);
                     fragment = new PayFragment();
                    fragment.setArguments(bundle);
                    fragment.setPaySuccessCallBack(PayBaozhengmoneyActivity.this);
                    fragment.show(this.getFragmentManager(), "Pay");

                    return;
                }
                if(pay==2){
                    //微信支付
                    Map map_pay=new HashMap();
                    map_pay.put("isrecharge","N");
                    map_pay.put("body",title_pay);
                    map_pay.put("total_fee","99");
                    map_pay.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    map_pay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    map_pay.put("task_id","1");
                    LogUtils.LOG("ceshi",map_pay.toString(),"缴纳保证金");
                    new WechatPay(PayBaozhengmoneyActivity.this,api,map_pay).wepay();
                    return;
                }
                if(pay==3){
                    //支付宝支付
                    Map map_zpay=new HashMap();
                    map_zpay.put("isrecharge","N");
                    map_zpay.put("subject",title_pay);
                    map_zpay.put("total_fee","99");
                    map_zpay.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    map_zpay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    map_zpay.put("task_id","1");
                    LogUtils.LOG("ceshi",map_zpay.toString(),"支付宝qingqiu接口");
                    LogUtils.LOG("ceshi", Urls.Baseurl_hu+Urls.zhifubaoPay,"支付宝qingqiu接口");
                    new ZhifubaoPay(PayBaozhengmoneyActivity.this,map_zpay).zhifubaoPay();
                    return;
                }


                break;
        }
    }
    void balancePay(Map map){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                String balan="";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                    Intent intent = new Intent("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
                    if(status==1){
                        Staticdata.map_task.put("payResult","1");
                        intent.putExtra("pay","success");
                        PayBaozhengmoneyActivity.this. sendBroadcast(intent);
                        finish();
                    }else {
                        if(fragment!=null){
                            fragment.dismiss();
                        }
                        intent.putExtra("pay","erro");
                        PayBaozhengmoneyActivity.this. sendBroadcast(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.balancePay,this,1,map);
    }
    void requestYue(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"显示余额");
                int status = 0;
                String msg = "";
                String balan="";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                    if(status==1){
                        balan=(String) object.get("balance");
                        balance= Double.parseDouble(balan);
                        if(balance<99){
                            image_yue.setSelected(false);
                            image_wechat.setSelected(true);
                            image_zhifubao.setSelected(false);
                            pay=2;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_hu+Urls.getBalance+Staticdata.static_userBean.getData().getUser_token()+"&client_no="+
                Staticdata.static_userBean.getData().getAppuser().getClient_no(),this,0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paysuccess_BroadcastReciver);
    }

    @Override
    public void onInputFinish(String result) {
        if(result.equals("1")){
            Map map_yue=new HashMap();
            map_yue.put("user_token", Staticdata.static_userBean.getData().getUser_token());
            map_yue.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
            map_yue.put("pay_money","99");
            map_yue.put("task_id","1");
            balancePay(map_yue);
        }else {
            ToastUtils.showToast(this,"支付密码错误");
        }
    }
}
