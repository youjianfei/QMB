package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interence_bargin;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.broadcastrReceiver.PaySuccessOrErroBroadcastReciver;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.popwinow.Popwindow_bargin;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.BargainMessagedetailsBean;
import com.jingnuo.quanmb.R;
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

public class BargainActivity extends BaseActivityother {
    TextView mTextview_message;
    TextView mTextview_time;
    TextView mTextview_1;
    TextView mTextview_name;
    TextView mTextview_des;
    TextView mTextview_taskstatename;
    TextView mTextview_money;
    TextView mTextview_yourmoney;
    TextView mTextview_mymoney;
    CircleImageView mImageview;
    Button mButton_accept;
    Button mButton_goon;
    Button mButton_refuse;

    LinearLayout mLinearlayout_caozuo;
    TextView mTextview_bargainstate;


    //数据
    String binding_id = "";
    String receive_client_no = "";
    String send_client_no = "";

    double amount=0;//还价的金额；
    double money=0;//雇主付过的金额

    String content="";

    Map map_bargainmessagedetail;
    BargainMessagedetailsBean bargainMessagedetailsBean;

//    Popwindow_bargin popwindow_bargin;
    Popwindow_Tip popwindow_tip;

    private IntentFilter intentFilter_paysuccess;//定义广播过滤器；
    private PaySuccessOrErroBroadcastReciver paysuccess_BroadcastReciver;//定义广播监听器


    private IWXAPI api;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_bargain;
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
//                    acceptBargain();
                    requestBargainmessage(map_bargainmessagedetail);//刷新状态
                }
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(BargainActivity.this, "支付失败");
                    }
                });
            }
        });
        registerReceiver(paysuccess_BroadcastReciver, intentFilter_paysuccess); //将广播监听器和过滤器注册在一起；

    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(BargainActivity.this, Staticdata.WechatApi);//微信支付用到

        map_bargainmessagedetail = new HashMap();
        Intent intent = getIntent();
        binding_id = intent.getStringExtra("binding_id");
        receive_client_no = intent.getStringExtra("receive_client_no");
        send_client_no = intent.getStringExtra("send_client_no");
        content = intent.getStringExtra("content");
        map_bargainmessagedetail.put("binding_id", binding_id + "");
        map_bargainmessagedetail.put("receive_client_no", receive_client_no);
        map_bargainmessagedetail.put("send_client_no", send_client_no);
        map_bargainmessagedetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        LogUtils.LOG("ceshi", "map+" + map_bargainmessagedetail, "还价消息详情");
        requestBargainmessage(map_bargainmessagedetail);

    }
    void acceptBargain(){//补差价支付成功

        Map map_accept = new HashMap();
        map_accept.put("id", bargainMessagedetailsBean.getData().getTask_id() + "");
        map_accept.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_accept.put("is_accept", "1");
        map_accept.put("binding_id", binding_id + "");
        map_accept.put("mark", bargainMessagedetailsBean.getData().getMark() + "");
        map_accept.put("send_client_no", bargainMessagedetailsBean.getData().getSend_client_no());
        map_accept.put("counteroffer_Amount", bargainMessagedetailsBean.getData().getCounteroffer_Amount() + "");
        if (bargainMessagedetailsBean.getData().getHelper_no() != null) {
            map_accept.put("helper_no", bargainMessagedetailsBean.getData().getHelper_no());
        } else {
            map_accept.put("business_no", bargainMessagedetailsBean.getData().getBusiness_no());
        }
        LogUtils.LOG("ceshi", map_accept.toString(), "接受还价map");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "接受还价");
                int status = 0;
                String msg = "";
                String data="";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");
                    msg = (String) object.get("message");
                    data = (String) object.get("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {
                    final double amount_need=amount-money;
                    if(amount_need>0){

                        final String finalData = data;
                        popwindow_tip=new Popwindow_Tip("需要补差价"+amount_need+"元", BargainActivity.this, new Interence_complteTask() {
                            @Override
                            public void onResult(boolean result) {
                                if(result){
                                    Intent intentpay = new Intent(BargainActivity.this, PayActivity.class);
                                    intentpay.putExtra("title", "任务补差价");//支付需要传 isBargainPay:(是否还价支付,	Y：是	N：否)还价支付时必传Y，其他支付可不传或N
                                    intentpay.putExtra("amount", amount_need + "");
                                    intentpay.putExtra("order_no", finalData + "");
                                    intentpay.putExtra("taskid", bargainMessagedetailsBean.getData().getTask_id() + "");
                                    startActivity(intentpay);
                                }

                            }
                        });
                        popwindow_tip.showPopwindow();

                    }else {
                        requestBargainmessage(map_bargainmessagedetail);//刷新状态
                    }
                } else {
                    ToastUtils.showToast(BargainActivity.this, msg);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.acceptORrefuse, BargainActivity.this, 1, map_accept);

    }


    @Override
    protected void initListener() {
        mButton_accept.setOnClickListener(this);
        mButton_goon.setOnClickListener(this);
        mButton_refuse.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mTextview_message = findViewById(R.id.textmessagetitle);
        mTextview_time = findViewById(R.id.text_time);
        mTextview_1 = findViewById(R.id.textview_shenfen);
        mTextview_name = findViewById(R.id.text_name);
        mTextview_des = findViewById(R.id.taskdes);
        mTextview_taskstatename = findViewById(R.id.text_tasktype);
        mTextview_money = findViewById(R.id.taskprice);
        mTextview_yourmoney = findViewById(R.id.text_yourprice);
        mTextview_mymoney = findViewById(R.id.text_needprice);
        mImageview = findViewById(R.id.image_type);
        mButton_accept = findViewById(R.id.button_accect);
        mButton_goon = findViewById(R.id.button_goon);
        mButton_refuse = findViewById(R.id.button_refuse);
        mLinearlayout_caozuo = findViewById(R.id.linearlayout_caozuo);
        mTextview_bargainstate = findViewById(R.id.text_bargainstate);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_accect://接受还价

                acceptBargain();

                break;
            case R.id.button_goon://继续还价
//                popwindow_bargin.showpop();


                break;
            case R.id.button_refuse://拒觉还价
                Map map_refuse = new HashMap();
                map_refuse.put("id", bargainMessagedetailsBean.getData().getTask_id() + "");
                map_refuse.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                map_refuse.put("is_accept", "0");
                map_refuse.put("binding_id", binding_id + "");
                map_refuse.put("mark", bargainMessagedetailsBean.getData().getMark() + "");
                map_refuse.put("send_client_no", bargainMessagedetailsBean.getData().getSend_client_no());
                map_refuse.put("counteroffer_Amount", bargainMessagedetailsBean.getData().getCounteroffer_Amount() + "");
                if (bargainMessagedetailsBean.getData().getHelper_no() != null) {
                    map_refuse.put("helper_no", bargainMessagedetailsBean.getData().getHelper_no());
                } else {
                    map_refuse.put("business_no", bargainMessagedetailsBean.getData().getBusiness_no());
                }
                LogUtils.LOG("ceshi", map_refuse.toString(), "拒接还价map");
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi", respose, "拒绝还价");
                        int status = 0;
                        String msg = "";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//登录状态
                            msg = (String) object.get("message");//登录返回信息
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (status == 1) {
                            requestBargainmessage(map_bargainmessagedetail);//刷新状态

                            ToastUtils.showToast(BargainActivity.this, msg);
                        } else {
                            ToastUtils.showToast(BargainActivity.this, msg);
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui + Urls.acceptORrefuse, BargainActivity.this, 1, map_refuse);

                break;
        }
    }

    void requestBargainmessage(Map map) {
        LogUtils.LOG("ceshi","还价消息详情网址+"+Urls.Baseurl_cui + Urls.bargainmessage,"还价消息详情");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "还价消息详情");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {
                    bargainMessagedetailsBean = new Gson().fromJson(respose, BargainMessagedetailsBean.class);
                    //判断  is_accept  是否操作过
                    if (bargainMessagedetailsBean.getData().getIs_accept() == null || bargainMessagedetailsBean.getData().getIs_accept().equals("")) {
                        mLinearlayout_caozuo.setVisibility(View.VISIBLE);
                        mTextview_bargainstate.setVisibility(View.GONE);
                    }
                    else if (bargainMessagedetailsBean.getData().getIs_accept().equals("0") && bargainMessagedetailsBean.getData().getMark().equals("2")) {
                        mLinearlayout_caozuo.setVisibility(View.GONE);
                        mTextview_bargainstate.setVisibility(View.VISIBLE);
                        mTextview_bargainstate.setText("已拒绝");
                        mTextview_message.setText("你拒绝了帮手的还价！");
                    }
                    else if (bargainMessagedetailsBean.getData().getIs_accept().equals("1") && bargainMessagedetailsBean.getData().getMark().equals("2")) {
                        mLinearlayout_caozuo.setVisibility(View.GONE);
                        mTextview_bargainstate.setVisibility(View.VISIBLE);
                        mTextview_bargainstate.setText("已接受");
                        mTextview_message.setText("你接受了帮手的还价！");
                    } else if (bargainMessagedetailsBean.getData().getIs_accept().equals("2") && bargainMessagedetailsBean.getData().getMark().equals("1")) {
                        mLinearlayout_caozuo.setVisibility(View.GONE);
                        mTextview_bargainstate.setVisibility(View.VISIBLE);
                        mTextview_bargainstate.setText("还价中");
                        mTextview_message.setText("该任务被雇主还价了！");
                    } else if (bargainMessagedetailsBean.getData().getIs_accept().equals("2") && bargainMessagedetailsBean.getData().getMark().equals("2")) {
                        mLinearlayout_caozuo.setVisibility(View.GONE);
                        mTextview_bargainstate.setVisibility(View.VISIBLE);
                        mTextview_bargainstate.setText("还价中");
                        mTextview_message.setText("你发布的任务被帮手还价了！");
                    }
                    if(bargainMessagedetailsBean.getData().getStatus_name().equals("已失效")){
                        mLinearlayout_caozuo.setVisibility(View.GONE);
                        mTextview_bargainstate.setVisibility(View.GONE);
                        mTextview_message.setText("此任务已失效");
                    }else if(bargainMessagedetailsBean.getData().getStatus_name().equals("已接单")){
                        mLinearlayout_caozuo.setVisibility(View.GONE);
                        mTextview_bargainstate.setVisibility(View.GONE);
                        mTextview_message.setText("此任务已被接单");
                    }

                    //由于mark问题  帮手被拒绝改为下面判断
                    if(content.contains("拒绝")){
                        mLinearlayout_caozuo.setVisibility(View.GONE);
                        mTextview_bargainstate.setVisibility(View.VISIBLE);
                        mTextview_bargainstate.setText("被拒绝");
                        mTextview_message.setText("你的还价被雇主拒绝了！");
                    }

                    if (bargainMessagedetailsBean.getData().getMark().equals("1")) {
                        mTextview_1.setText("还价雇主");
                        mTextview_yourmoney.setVisibility(View.GONE);
                        mButton_refuse.setVisibility(View.GONE);

                    } else {
                        mTextview_1.setText("还价帮手");
                        LogUtils.LOG("ceshi", bargainMessagedetailsBean.getData().getResponse_Amount() + "", "sdafsadf");
                        if (bargainMessagedetailsBean.getData().getResponse_Amount() != 0.0) {
                            mTextview_yourmoney.setVisibility(View.VISIBLE);
                            mTextview_yourmoney.setText("你的还价：" + bargainMessagedetailsBean.getData().getResponse_Amount() + "元");
                        }

                    }
                    mTextview_time.setText(bargainMessagedetailsBean.getData().getCreateDate());
                    mTextview_name.setText(bargainMessagedetailsBean.getData().getName());
                    mTextview_des.setText(bargainMessagedetailsBean.getData().getTask_description());
                    mTextview_taskstatename.setText(bargainMessagedetailsBean.getData().getSpecialty_name());
                    if (bargainMessagedetailsBean.getData().getIs_helper_bid().equals("N")) {
                        money=bargainMessagedetailsBean.getData().getCommission();
                        mTextview_money.setText( money+ "元");
                    } else {
                        mTextview_money.setText("帮手出价");
                        money=5;
                    }
                    amount=bargainMessagedetailsBean.getData().getCounteroffer_Amount();
                    mTextview_mymoney.setText("还价价格：" + amount + "元");
                    String URL_imagehead = bargainMessagedetailsBean.getData().getAvatar_imgUrl().substring(0, bargainMessagedetailsBean.getData().getAvatar_imgUrl().length() - 1);
                    Glide.with(BargainActivity.this).load(bargainMessagedetailsBean.getData().getAvatar_imgUrl()).load(URL_imagehead).into(mImageview);

                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.bargainmessage, BargainActivity.this, 1, map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paysuccess_BroadcastReciver);
    }
}
