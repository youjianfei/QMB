package com.jingnuo.quanmb.class_;

import com.jingnuo.quanmb.utils.LogUtils;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    @Override
    public boolean onReceived(Message message, int i) {

        LogUtils.LOG("rongyun",message+""+i,"rongyun接收器");

        return false;
    }
}
