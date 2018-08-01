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
//                        Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
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
