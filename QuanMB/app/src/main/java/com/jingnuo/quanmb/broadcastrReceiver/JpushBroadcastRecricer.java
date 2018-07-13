package com.jingnuo.quanmb.broadcastrReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jingnuo.quanmb.activity.BarginmessageListActivity;
import com.jingnuo.quanmb.activity.DealActivity;
import com.jingnuo.quanmb.activity.LaunchActivity;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.SettingActivity;
import com.jingnuo.quanmb.activity.SystemMessageActivity;
import com.jingnuo.quanmb.activity.TaskDetailsActivity;
import com.jingnuo.quanmb.activity.TuijianrenwuActivity;
import com.jingnuo.quanmb.fargment.Fragment_message;
import com.jingnuo.quanmb.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

import static com.jingnuo.quanmb.fargment.Fragment_message.mFragment_message;

public class JpushBroadcastRecricer extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String type = "";//判断跳转页面

        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
        if(bundle.getString(JPushInterface.EXTRA_EXTRA)!=null){
            if(MainActivity.mainActivity!=null){
                MainActivity.mainActivity.setREDDOT(true);
            }
        }
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtils.LOG("ceshi","接收的广播+"+extras,"极光广播接收器");
        if (extras!=null&&!extras.equals("")){

            try {
                JSONObject object = new JSONObject(extras);
                type = (String) object.get("type");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LogUtils.LOG("ceshi",type,"推送1");
        if(type.equals("1")){
            if(mFragment_message!=null){
                mFragment_message.setDot(1);
                LogUtils.LOG("ceshi",type,"推送2");
            }
        }else if (type.equals("2")){
            if(mFragment_message!=null){
                mFragment_message.setDot(2);
            }
        }else if(type.equals("3")){
            if(mFragment_message!=null){
                mFragment_message.setDot(3);
            }
        }
        else if(type.equals("4")){
            if(mFragment_message!=null){
                mFragment_message.setDot(4);
            }
        }

        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            if (type.equals("2")){
                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent intent_bargain=new Intent(context, BarginmessageListActivity.class);
                intent_bargain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent[] intents = {mainIntent, intent_bargain};
                context.startActivities(intents);
            }else if(type.equals("3")){
                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent intent_deal=new Intent(context, DealActivity.class);
                intent_deal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent[] intents = {mainIntent, intent_deal};
                context.startActivities(intents);
            }else if(type.equals("1")){
                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent intent_system=new Intent(context, SystemMessageActivity.class);
                intent_system.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent[] intents = {mainIntent, intent_system};
                context.startActivities(intents);
            }
            else if(type.equals("4")){
                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent intent_system=new Intent(context, TuijianrenwuActivity.class);
                intent_system.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent[] intents = {mainIntent, intent_system};
                context.startActivities(intents);
            }

        }

    }
}
