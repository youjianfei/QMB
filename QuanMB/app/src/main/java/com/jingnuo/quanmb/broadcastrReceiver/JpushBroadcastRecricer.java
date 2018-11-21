package com.jingnuo.quanmb.broadcastrReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jingnuo.quanmb.activity.BarginmessageListActivity;
import com.jingnuo.quanmb.activity.DealActivity;
import com.jingnuo.quanmb.activity.IssueTaskActivity;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.PayActivity;
import com.jingnuo.quanmb.activity.SystemMessageActivity;
import com.jingnuo.quanmb.activity.TuijianrenwuActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

import static com.jingnuo.quanmb.activity.IssueTaskActivity.issueTaskActivity;
import static com.jingnuo.quanmb.activity.MainActivity.mainActivity;


public class JpushBroadcastRecricer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String type = "";//判断跳转页面

        String order_no="";//订单号，用于跳转支付页
        String taskid="";//任务号，用于跳转支付页

        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
        if (bundle.getString(JPushInterface.EXTRA_EXTRA) != null) {

        }
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtils.LOG("ceshi", "接收的广播+" + extras, "极光广播接收器");
        if (extras != null && !extras.equals("")) {
            if (issueTaskActivity != null) {
                issueTaskActivity.setdot();
            }
            try {
                JSONObject object = new JSONObject(extras);
                type = (String) object.get("type");
                order_no = (String) object.get("order_no");
                taskid = (String) object.get("task_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LogUtils.LOG("ceshi", type, "推送1");
        if (type.equals("1")) {
            Staticdata.newmessageTYpe = "type1";
        } else if (type.equals("2")) {
            Staticdata.newmessageTYpe = "type2";
        } else if (type.equals("3")) {
            Staticdata.newmessageTYpe = "type3";
        } else if (type.equals("4")) {
            Staticdata.newmessageTYpe = "type4";
        }
        else if (type.equals("5")) {
            Staticdata.newmessageTYpe = "type3";
            Intent mainIntent = new Intent(context, IssueTaskActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Intent intent1=new Intent(context, PayActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("title", "商户任务付款");
            intent1.putExtra("order_no", order_no);
            intent1.putExtra("taskid", taskid);
            Intent[] intents = {mainIntent, intent1};
            context.startActivities(intents);
        }

        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            if (type.equals("2")) {
                Intent mainIntent = new Intent(context, IssueTaskActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent intent_bargain = new Intent(context, BarginmessageListActivity.class);
                intent_bargain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent[] intents = {mainIntent, intent_bargain};
                context.startActivities(intents);
            } else if (type.equals("3")) {
                Intent mainIntent = new Intent(context, IssueTaskActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent intent_deal = new Intent(context, DealActivity.class);
                intent_deal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent[] intents = {mainIntent, intent_deal};
                context.startActivities(intents);
            } else if (type.equals("1")) {
                Intent mainIntent = new Intent(context, IssueTaskActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent intent_system = new Intent(context, SystemMessageActivity.class);
                intent_system.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent[] intents = {mainIntent, intent_system};
                context.startActivities(intents);
            } else if (type.equals("4")) {
                Intent mainIntent = new Intent(context, IssueTaskActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent intent_system = new Intent(context, TuijianrenwuActivity.class);
                intent_system.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent[] intents = {mainIntent, intent_system};
                context.startActivities(intents);
            }

        }

    }
}
