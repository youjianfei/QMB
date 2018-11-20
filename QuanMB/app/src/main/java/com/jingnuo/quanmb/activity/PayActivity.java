package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.broadcastrReceiver.PaySuccessOrErroBroadcastReciver;
import com.jingnuo.quanmb.class_.WechatPay;
import com.jingnuo.quanmb.class_.ZhifubaoPay;
import com.jingnuo.quanmb.customview.PayFragment;
import com.jingnuo.quanmb.customview.PayPwdView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.OrderThinkBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.jingnuo.quanmb.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PayActivity extends BaseActivityother implements PayPwdView.InputCallBack {

    //控件
    CircleImageView mImageview_head;
    TextView mTextview_amount;
    RelativeLayout mRelayout_yue;
    RelativeLayout mRelayout_wechat;
    RelativeLayout mRelayout_zhifubao;
    RelativeLayout relayout_selectCoupon;//选择优惠券
    ImageView image_yue;
    ImageView image_wechat;
    ImageView image_zhifubao;
    ImageView image_phonenumber;
    Button mButton_submit;
    TextView text_couponTextContent;
    TextView text_select;
    TextView textview_kefu;
    TextView txtview_shopname;//商户名字
    TextView textview_startcounts;//商户星级值


    //数据
    private IntentFilter intentFilter_paysuccess;//定义广播过滤器；
    private PaySuccessOrErroBroadcastReciver paysuccess_BroadcastReciver;//定义广播监听器

    PayFragment fragment;

    double balance = 0;//余额
    String title_pay = "";
    String amount = "";//订单金额
    double pay_amount;//要付的金额

    String taskid = "";
    String coupon_code = "";//优惠券码
    String tasktypeid = "";
    String order_no = "";
    String business_no = "";
    String shopPhonenumber="";//商家电话号

    double coupon_amout;//优惠券金额
    int coupon_possition = 100;

    int pay = 1;  //1 余额支付 2 微信支付  3 支付宝支付
    private IWXAPI api;


    OrderThinkBean orderThinkBean;
    PermissionHelper mPermission;//动态申请权限


    @Override
    public int setLayoutResID() {
        return R.layout.activity_pay;
    }

    @Override
    protected void setData() {
        intentFilter_paysuccess = new IntentFilter();
        intentFilter_paysuccess.addAction("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
        paysuccess_BroadcastReciver = new PaySuccessOrErroBroadcastReciver(new Interface_paySuccessOrerro() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "payResult");
                if (respose.equals("success")) {//支付成功
                    Staticdata.PayissuetaskSuccess = true;
                    Intent intent = new Intent(PayActivity.this, PaySuccessActivity.class);
                    intent.putExtra("title", "支付成功");
                    intent.putExtra("typesuccess", "支付成功");
                    startActivity(intent);
                    finish();
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
        mPermission = new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        api = WXAPIFactory.createWXAPI(PayActivity.this, Staticdata.WechatApi);//微信支付用到
        Intent intent = getIntent();
        title_pay = intent.getStringExtra("title");
//        amount = intent.getStringExtra("amount");//订单金额
//        pay_amount = Double.parseDouble(amount);//要付的金额
        taskid = intent.getStringExtra("taskid");
        order_no = intent.getStringExtra("order_no");
//        business_no = intent.getStringExtra("business_no");
//        tasktypeid = intent.getStringExtra("tasktypeid");


        image_yue.setSelected(true);

        requestInfo();//请求商户信息
    }
    void requestInfo(){
        Map map=new HashMap();
        map.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        map.put("task_id",taskid);
        LogUtils.LOG("ceshi",map.toString(),"评价");

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"评价");
                orderThinkBean=new Gson().fromJson(respose,OrderThinkBean.class);
                if(orderThinkBean.getCode()==1){
                    txtview_shopname.setText(orderThinkBean.getData().getBusiness_name());
                    textview_startcounts.setText(orderThinkBean.getData().getEvaluation_star()+"");
                    shopPhonenumber=orderThinkBean.getData().getBusiness_mobile_no();
                    Glide.with(PayActivity.this).load(orderThinkBean.getData().getBus_head_url()).into(mImageview_head);
                    business_no=orderThinkBean.getData().getBusiness_no();
                    tasktypeid = orderThinkBean.getData().getTask_type();
                    amount=orderThinkBean.getData().getOrder_amount();
                    pay_amount = Double.parseDouble(amount);//要付的金额
                    mTextview_amount.setText("" + amount);


                    requestYue();//请求实时余额
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.getorderinfo,PayActivity.this,1,map);
    }

    void requestYue() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                String balan = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                    if (status == 1) {
                        balan = (String) object.get("balance");
                        balance = Double.parseDouble(balan);
                        if (balance < pay_amount) {
                            image_yue.setSelected(false);
                            image_wechat.setSelected(true);
                            image_zhifubao.setSelected(false);
                            pay = 2;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_hu + Urls.getBalance + Staticdata.static_userBean.getData().getUser_token() + "&client_no=" +
                Staticdata.static_userBean.getData().getAppuser().getClient_no(), this, 0);
    }

    @Override
    protected void initListener() {
        mRelayout_yue.setOnClickListener(this);
        mRelayout_wechat.setOnClickListener(this);
        mRelayout_zhifubao.setOnClickListener(this);
        mButton_submit.setOnClickListener(this);
        relayout_selectCoupon.setOnClickListener(this);
        textview_kefu.setOnClickListener(this);
        image_phonenumber.setOnClickListener(this);

    }

    @Override
    protected void initView() {
        mImageview_head = findViewById(R.id.image_viewHead);
        mTextview_amount = findViewById(R.id.textview);
        mRelayout_yue = findViewById(R.id.relayoutyue);
        mRelayout_wechat = findViewById(R.id.relayout_wechat);
        mRelayout_zhifubao = findViewById(R.id.relayout_zhifubao);
        relayout_selectCoupon = findViewById(R.id.relayout_selectCoupon);
        image_yue = findViewById(R.id.image_yue);
        image_wechat = findViewById(R.id.image_wechat);
        image_zhifubao = findViewById(R.id.image_zhifubao);
        image_phonenumber = findViewById(R.id.image_phonenumber);
        text_select = findViewById(R.id.text_select);
        textview_kefu = findViewById(R.id.textview_kefu);
        txtview_shopname = findViewById(R.id.txtview_shopname);
        textview_startcounts = findViewById(R.id.textview_startcounts);
        text_couponTextContent = findViewById(R.id.text_couponTextContent);
        mButton_submit = findViewById(R.id.button_submit);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.image_phonenumber:
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + shopPhonenumber);
                intent.setData(data);

                if (ActivityCompat.checkSelfPermission(PayActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

//                    ToastUtils.showToast(mContext,"拨打电话权限被你拒绝，请在手机设置中开启");
                    mPermission.request(new PermissionHelper.PermissionCallback() {
                        @Override
                        public void onPermissionGranted() {

                        }

                        @Override
                        public void onIndividualPermissionGranted(String[] grantedPermission) {

                        }

                        @Override
                        public void onPermissionDenied() {

                        }

                        @Override
                        public void onPermissionDeniedBySystem() {

                        }
                    });
                    return;
                }
                startActivity(intent);//调用具体方法

                break;
            case R.id.textview_kefu:
                Intent intent_kefuzhongxin = new Intent(PayActivity.this, ZixunKefuWebActivity.class);
                intent_kefuzhongxin.putExtra("webtitle", "全民帮客服中心");
                intent_kefuzhongxin.putExtra("type", "全民帮客服中心");
                startActivity(intent_kefuzhongxin);
                break;
            case R.id.relayout_selectCoupon:
                Intent intent_selectcoupon = new Intent(PayActivity.this, CouponActivity.class);
                intent_selectcoupon.putExtra("type", "选择优惠券");
                intent_selectcoupon.putExtra("task_type_id", tasktypeid + "");
                intent_selectcoupon.putExtra("business_no", business_no);
                intent_selectcoupon.putExtra("selectposition", coupon_possition);
                intent_selectcoupon.putExtra("order_no", order_no);
                startActivityForResult(intent_selectcoupon, 1637);

                break;
            case R.id.relayoutyue:
                if (balance < pay_amount) {
                    ToastUtils.showToast(this, "余额不足");
                    return;
                }
                image_yue.setSelected(true);
                image_wechat.setSelected(false);
                image_zhifubao.setSelected(false);
                pay = 1;
                break;
            case R.id.relayout_wechat:
                image_yue.setSelected(false);
                image_wechat.setSelected(true);
                image_zhifubao.setSelected(false);
                pay = 2;
                break;
            case R.id.relayout_zhifubao:
                image_yue.setSelected(false);
                image_wechat.setSelected(false);
                image_zhifubao.setSelected(true);
                pay = 3;
                break;
            case R.id.button_submit:
                if(orderThinkBean.getCode()!=1){
                    ToastUtils.showToast(PayActivity.this,"订单异常");
                    return;
                }
                if (pay == 1) {
                    if (Staticdata.static_userBean.getData().getAppuser().getSecurity_code().equals("")) {
                        ToastUtils.showToast(this, "请先设置安全密码");
                        Intent intent_setsafe = new Intent(PayActivity.this, SetSafepassword1Activity.class);
                        intent_setsafe.putExtra("change", "nochange");
                        startActivity(intent_setsafe);
                        return;
                    }
                    //余额支付
                    Bundle bundle = new Bundle();
                    bundle.putString(PayFragment.EXTRA_CONTENT, title_pay + "：¥ " + pay_amount);
                    fragment = new PayFragment();
                    fragment.setArguments(bundle);
                    fragment.setPaySuccessCallBack(PayActivity.this);
                    fragment.show(this.getFragmentManager(), "Pay");
                    return;
                }
                if (pay == 2) {
                    //微信支付
                    Map map_pay = new HashMap();
                    map_pay.put("isrecharge", "N");
                    map_pay.put("body", title_pay);
                    map_pay.put("total_fee", pay_amount + "");
                    map_pay.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    map_pay.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                    map_pay.put("task_id", taskid);
                    map_pay.put("coupon_code", coupon_code);//优惠券代码
                    map_pay.put("order_no", order_no);
                    if (title_pay.equals("匹配商户成功付款") || title_pay.equals("任务补差价")) {
                        map_pay.put("isBargainPay", "Y");
                    }
                    if (title_pay.equals("商户任务付款")) {
                        map_pay.put("isMatchPay", "Y");
                    }
                    if (title_pay.equals("全民帮—修改金额")) {
                        map_pay.put("isAddCommissionPay", "Y");
                    } else {
                        map_pay.put("isAddCommissionPay", "N");

                    }
                    LogUtils.LOG("ceshi", map_pay.toString(), "充值");
                    new WechatPay(PayActivity.this, api, map_pay).wepay();
                    return;
                }
                if (pay == 3) {
                    //支付宝支付
                    Map map_zpay = new HashMap();
                    map_zpay.put("isrecharge", "N");
                    map_zpay.put("subject", title_pay);
                    map_zpay.put("total_fee", pay_amount + "");
                    map_zpay.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    map_zpay.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                    map_zpay.put("task_id", taskid);
                    map_zpay.put("coupon_code", coupon_code);//优惠券代码
                    map_zpay.put("order_no", order_no);
                    if (title_pay.equals("匹配商户成功付款") || title_pay.equals("任务补差价")) {
                        map_zpay.put("isBargainPay", "Y");
                    }
                    if (title_pay.equals("商户任务付款")) {
                        map_zpay.put("isMatchPay", "Y");
                    }
                    if (title_pay.equals("全民帮—修改金额")) {
                        map_zpay.put("isAddCommissionPay", "Y");
                    } else {
                        map_zpay.put("isAddCommissionPay", "N");

                    }
                    LogUtils.LOG("ceshi", map_zpay.toString(), "支付宝qingqiu接口");
                    LogUtils.LOG("ceshi", Urls.Baseurl_hu + Urls.zhifubaoPay, "支付宝qingqiu接口");
                    new ZhifubaoPay(PayActivity.this, map_zpay).zhifubaoPay();
                    return;
                }


                break;
        }
    }

    void balancePay(Map map) {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                String balan = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                    Intent intent = new Intent("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
                    if (status == 1) {
                        Staticdata.map_task.put("payResult", "1");
                        intent.putExtra("pay", "success");
                        PayActivity.this.sendBroadcast(intent);

                    } else {
                        intent.putExtra("pay", "erro");
                        PayActivity.this.sendBroadcast(intent);
                        if (fragment != null) {
                            fragment.dismiss();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu + Urls.balancePay, this, 1, map);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1637 && resultCode == 1637) {
            coupon_amout = data.getDoubleExtra("amount", 0.0);
            coupon_possition = data.getIntExtra("position", 0);
            coupon_code = data.getStringExtra("coupon_code");
            text_couponTextContent.setText("已抵用" + coupon_amout + "元");
            text_select.setVisibility(View.VISIBLE);
            pay_amount = Double.parseDouble(amount) - coupon_amout;
            mButton_submit.setText("立即支付" + pay_amount + "元");
            LogUtils.LOG("ceshi", "优惠金额" + coupon_amout + "条目" + coupon_possition + "现价" + pay_amount, "payactivity");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paysuccess_BroadcastReciver);
    }

    @Override
    public void onInputFinish(String result) {
        if (result.equals("1")) {
            Map map_yue = new HashMap();
            map_yue.put("user_token", Staticdata.static_userBean.getData().getUser_token());
            map_yue.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
            map_yue.put("pay_money", pay_amount + "");
            map_yue.put("task_id", taskid);
            map_yue.put("coupon_code", coupon_code);//优惠券代码
            map_yue.put("order_no", order_no);
            LogUtils.LOG("ceshi", title_pay.toString(), "title_pay");
            if (title_pay.equals("匹配商户成功付款") || title_pay.equals("任务补差价")) {
                map_yue.put("isBargainPay", "Y");
                LogUtils.LOG("ceshi", map_yue.toString(), "余额付款");
            }
            if (title_pay.equals("商户任务付款")) {
                map_yue.put("isMatchPay", "Y");
            }
            if (title_pay.equals("全民帮—修改金额")) {
                map_yue.put("isAddCommissionPay", "Y");
            } else {
                map_yue.put("isAddCommissionPay", "N");

            }
            balancePay(map_yue);
        } else {
            ToastUtils.showToast(this, "支付密码错误");
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
