package com.jingnuo.quanmb.class_;

import com.jingnuo.quanmb.utils.LogUtils;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

import static com.jingnuo.quanmb.activity.IssueTaskActivity.issueTaskActivity;
import static com.jingnuo.quanmb.activity.MainActivity.mainActivity;

public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    @Override
    public boolean onReceived(Message message, int i) {

        LogUtils.LOG("rongyun",message+""+i,"rongyun接收器");
        if (issueTaskActivity != null) {
            issueTaskActivity.setdot();
            LogUtils.LOG("rongyun",message+""+i,"rongyun接收器hongdian");
        }
        return false;
    }
}
