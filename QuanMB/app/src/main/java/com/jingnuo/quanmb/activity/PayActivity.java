package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.broadcastrReceiver.PaySuccessOrErroBroadcastReciver;
import com.jingnuo.quanmb.class_.WechatPay;
import com.jingnuo.quanmb.class_.ZhifubaoPay;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class PayActivity extends BaseActivityother {

    //控件
    CircleImageView mImageview_head;
    TextView mTextview_amount;
    TextView mTextview_order;
    RelativeLayout mRelayout_yue;
    RelativeLayout mRelayout_wechat;
    RelativeLayout mRelayout_zhifubao;
    ImageView image_yue;
    ImageView image_wechat;
    ImageView image_zhifubao;
    Button mButton_submit;


    //s数据
    private IntentFilter intentFilter_paysuccess;//定义广播过滤器；
    private PaySuccessOrErroBroadcastReciver paysuccess_BroadcastReciver;//定义广播监听器




    String title_pay="";
    String amount="";
    String taskid="";

    int pay=1;  //1 余额支付 2 微信支付  3 支付宝支付
    private IWXAPI api;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_pay;
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
                    Staticdata.map_task.put("payResult","1");
                    requast(Staticdata.map_task);//正式发布任务
                }
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(PayActivity.this, "支付失败");
                    }
                });
            }
        });
        registerReceiver(paysuccess_BroadcastReciver, intentFilter_paysuccess); //将广播监听器和过滤器注册在一起；
    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(PayActivity.this, Staticdata.WechatApi);//微信支付用到
        Intent intent=getIntent();
        title_pay=intent.getStringExtra("title");
        amount=intent.getStringExtra("amount");
        taskid=intent.getStringExtra("taskid");
        Glide.with(this).load(Staticdata.static_userBean.getData().getImg_url()).into(mImageview_head);
        mTextview_amount.setText("¥"+amount);
        mTextview_order.setText(Staticdata.map_task.get("tasktypename").toString()+"-"+Staticdata.map_task.get("task_id"));
        image_yue.setSelected(true);
    }

    @Override
    protected void initListener() {
        mRelayout_yue.setOnClickListener(this);
        mRelayout_wechat.setOnClickListener(this);
        mRelayout_zhifubao.setOnClickListener(this);
        mButton_submit.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mImageview_head = findViewById(R.id.image_viewHead);
        mTextview_amount = findViewById(R.id.textview);
        mTextview_order = findViewById(R.id.textview_order);
        mRelayout_yue = findViewById(R.id.relayoutyue);
        mRelayout_wechat = findViewById(R.id.relayout_wechat);
        mRelayout_zhifubao = findViewById(R.id.relayout_zhifubao);
        image_yue = findViewById(R.id.image_yue);
        image_wechat = findViewById(R.id.image_wechat);
        image_zhifubao = findViewById(R.id.image_zhifubao);
        mButton_submit=findViewById(R.id.button_submit);
    }
    void requast( Map map){//正式发布任务
        LogUtils.LOG("ceshi",Staticdata.map_task.toString(),"发布任务的map参数");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
//                progressDlog.cancelPD();
                LogUtils.LOG("ceshi","发布任务返回json","发布任务");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("status");//
                    msg = (String) object.get("msg");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(PayActivity.this,"任务发布成功");
                    Intent intent=new Intent(PayActivity.this,MainActivity.class);
                    startActivity(intent);
                    Staticdata.imagePathlist.clear();
                    Staticdata.map_task.clear();
                }else {
//                    count=0;
//                    mList_picID.clear();
//                    progressDlog.cancelPD();
                    ToastUtils.showToast(PayActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {
//                progressDlog.cancelPD();
//                count=0;
//                mList_picID.clear();
            }
        }).postHttp(Urls.Baseurl_cui+Urls.issuetask,this,1,map);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.relayoutyue:
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
                    //余额支付
                    ToastUtils.showToast(this,"对不起，余额不足");
                    Staticdata.map_task.put("payResult","1");
                    requast(Staticdata.map_task);//正式发布任务
                    return;
                }
                if(pay==2){
                    //微信支付
                    Map map_pay=new HashMap();
                    map_pay.put("body",title_pay);
                    map_pay.put("total_fee",amount);
                    map_pay.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    map_pay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    map_pay.put("task_id",taskid);
                    LogUtils.LOG("ceshi",map_pay.toString(),"充值");
                    new WechatPay(PayActivity.this,api,map_pay).wepay();
                    return;
                }
                if(pay==3){
                    //支付宝支付
                    Map map_zpay=new HashMap();
                    map_zpay.put("subject",title_pay);
                    map_zpay.put("total_fee",amount);
                    map_zpay.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    map_zpay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    map_zpay.put("task_id",taskid);
                    LogUtils.LOG("ceshi",map_zpay.toString(),"支付宝qingqiu接口");
                    LogUtils.LOG("ceshi", Urls.Baseurl_hu+Urls.zhifubaoPay,"支付宝qingqiu接口");
                    new ZhifubaoPay(PayActivity.this,map_zpay).zhifubaoPay();
                    return;
                }


                break;
        }
    }
}
