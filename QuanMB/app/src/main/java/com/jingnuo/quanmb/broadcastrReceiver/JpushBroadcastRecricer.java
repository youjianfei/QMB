package com.jingnuo.quanmb.broadcastrReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jingnuo.quanmb.activity.LaunchActivity;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.SettingActivity;
import com.jingnuo.quanmb.utils.LogUtils;

import cn.jpush.android.api.JPushInterface;

public class JpushBroadcastRecricer extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {


        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
        if(title!=null&&!title.equals("")){
            if(MainActivity.mainActivity!=null){
                MainActivity.mainActivity.setREDDOT(true);
            }
        }
        LogUtils.LOG("ceshi","接收的广播+"+title,"极光广播接收器");
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);

        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {

            Intent mainIntent = new Intent(context, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            Intent renzhengactivity=new Intent(context, SettingActivity.class);
//            renzhengactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//            Intent[] intents = {mainIntent, renzhengactivity};

            context.startActivity(mainIntent);

        }

    }
}
