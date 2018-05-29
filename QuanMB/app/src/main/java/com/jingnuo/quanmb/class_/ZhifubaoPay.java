package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ZhifubaoPay {
    Activity activity;
    Map map;

    public ZhifubaoPay(Activity activity, Map map) {
        this.activity = activity;
        this.map = map;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1001:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    Intent intent = new Intent("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                        intent.putExtra("pay","success");
                       activity. sendBroadcast(intent);
                    } else {
                        Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
                        intent.putExtra("pay","erro");
                        activity. sendBroadcast(intent);
                    }
                    break;
            }
        }
    };

    public void zhifubaoPay() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "支付宝qingqiu接口");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("orderStr");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap("2018052260224087", true);
//                //构造支付订单参数信息
//                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//                //对支付参数信息进行签名
//                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, true);
//                //订单信息
//                final String orderInfo = "alipay_sdk=alipay-sdk-java-3.0.52.ALL&app_id=2018052260224087&biz_content=%7B%22out_trade_no%22%3A%22QMB20180529135512336000%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%85%A8%E6%B0%91%E5%B8%AE%E2%80%94%E5%85%85%E5%80%BC%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fwww.quanminbang.top%2Fv1.0%2FALiPay%2FaliPay_notify&sign=tDRvt%2BroTI9Tjw1IZgQJdgu0Sm6e5prACJW6tPR0aCB%2FZr1JJ0PViJtSxJjBJbD52S24zto8PIsOBi1GSKg%2BCVsofLONjMuryLWsfIG0uvFYat5vUHlpEvCjPFn0u%2BzYop2zygkLEPfYp9DAL6JNoU8iPWtv1yaYmvIWTZKEbfxDuGugM%2FnO2Y8nr4T5oihS9BlJ1pKRBXNcZLSWWSrdJeGsb6QiABqgpoOTUvBEWoua1jee5ujzM%2FtqsyjiCOulzKaTd8huFiAs0PWa84AUptKdhelXEDEoSjfsvEHIaeaM9q9vZVolP338Yaa715FwNVV77ckVZ%2FMbzPpkLyVX7g%3D%3D&sign_type=RSA2×tamp=2018-05-29+13%3A55%3A12&version=1.0";
                //异步处理
                if (status == 1) {

                    final String orderInfo = msg;

                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            //新建任务
                            PayTask alipay = new PayTask(activity);
                            //获取支付结果
                            Map<String, String> result = alipay.payV2(orderInfo, true);
                            Message msg = new Message();
                            msg.what = 1001;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();

                }


            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu + Urls.zhifubaoPay, activity, 1, map);


    }
}
