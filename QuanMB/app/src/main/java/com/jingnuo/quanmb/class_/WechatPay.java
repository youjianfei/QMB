package com.jingnuo.quanmb.class_;

import android.app.Activity;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.WechatPayBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.jingnuo.quanmb.data.Staticdata.WechatApi;
import static com.jingnuo.quanmb.data.Staticdata.WechatApipay;

public class WechatPay {
    Activity activity;
    private IWXAPI api;
    WechatPayBean wechatPayBean;
    Map map;

    public WechatPay(Activity activity, IWXAPI api, Map map) {
        this.activity = activity;
        this.api = api;
        this.map = map;
    }

    public void wepay(){
        LogUtils.LOG("ceshi",map.toString(),"充值");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi","请求服务器统一下单"+ Urls.Baseurl_hu+Urls.wechatPay,"关于我们");
                wechatPayBean=new Gson().fromJson(respose,WechatPayBean.class);
                PayReq request = new PayReq();
                request.appId = WechatApi;
                request.partnerId = wechatPayBean.getData().getMch_id();
                request.prepayId =wechatPayBean.getData().getPrepay_id();
                request.packageValue = "Sign=WXPay";
                request.nonceStr = wechatPayBean.getData().getNonce_str();
                request.timeStamp = wechatPayBean.getData().getTimestamp();


                Map<String ,String> json=new HashMap();
                json.put("appid",WechatApi);
                json.put("partnerid",request.partnerId);
                json.put("prepayid",request.prepayId);
                json.put("package","Sign=WXPay");
                json.put("noncestr",request.nonceStr);
                json.put("timestamp", request.timeStamp);


                String[] keys = json.keySet().toArray(new String[0]);
                Arrays.sort(keys);

                // 2. 按照排序拼接参数名与参数值
                StringBuffer paramBuffer = new StringBuffer();
                for (String key : keys) {
                    paramBuffer.append(key + "=").append(json.get(key)).append("&");
                }
                // 需要签名的数据
                String stringSignTemp = paramBuffer + "key=" +WechatApipay;
                request.sign =stringSignTemp;

                api.sendReq(request);

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.wechatPay,activity,1,map);
    }






}
