package com.jingnuo.quanmb.broadcastrReceiver;

import android.content.Context;

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

        return false;
    }
}
