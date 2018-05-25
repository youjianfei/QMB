package com.jingnuo.quanmb.broadcastrReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;


/**
 * Created by 00 on 2017/1/3.
 */

public class PaySuccessOrErroBroadcastReciver extends BroadcastReceiver {

   private Interface_paySuccessOrerro paySuccessOrErroBroadcastReciver;

    public PaySuccessOrErroBroadcastReciver(Interface_paySuccessOrerro paySuccessOrErroBroadcastReciver) {
        this.paySuccessOrErroBroadcastReciver = paySuccessOrErroBroadcastReciver;
    }

    @Override
        public void onReceive(Context context, Intent intent) {
        if(intent.getStringExtra("pay").equals("success")){
            paySuccessOrErroBroadcastReciver.onSuccesses("success");
        }else {
            paySuccessOrErroBroadcastReciver.onError("erro");
        }

    }
}
