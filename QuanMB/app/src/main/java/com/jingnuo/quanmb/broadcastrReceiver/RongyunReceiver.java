package com.jingnuo.quanmb.broadcastrReceiver;

import android.content.Context;
import android.content.Intent;

import com.jingnuo.quanmb.activity.LaunchActivity;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.TuijianrenwuActivity;
import com.jingnuo.quanmb.utils.LogUtils;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

public class RongyunReceiver extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        LogUtils.LOG("rongyun",pushNotificationMessage.toString(),"融云广播接收器");
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        LogUtils.LOG("rongyun",pushNotificationMessage.toString(),"点击融云广播接收器");
        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainIntent);

        return false;
    }
}
