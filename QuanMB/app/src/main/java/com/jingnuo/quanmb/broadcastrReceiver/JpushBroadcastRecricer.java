package com.jingnuo.quanmb.broadcastrReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jingnuo.quanmb.utils.LogUtils;

import cn.jpush.android.api.JPushInterface;

public class JpushBroadcastRecricer extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);

        LogUtils.LOG("ceshi","接收的广播+"+title,"极光广播接收器");

    }
}
