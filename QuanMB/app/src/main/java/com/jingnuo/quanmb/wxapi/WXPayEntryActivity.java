package com.jingnuo.quanmb.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.jingnuo.quanmb.R;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpayentry);
        api = WXAPIFactory.createWXAPI(this, "wx1589c6a947d1f803");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if(baseResp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            Log.i("ceshi","onPayFinish,errCode="+baseResp.errCode);
            Intent intent = new Intent("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
            if(baseResp.errCode==0){
                intent.putExtra("pay","success");
                sendBroadcast(intent);


                finish();
            }else{
                //把要发送的广播值传入Intent对象

                //调用Context的 sendBroadcast()方法发送广播
                intent.putExtra("pay","erro");
                sendBroadcast(intent);


                finish();
            }
        }

    }
}
