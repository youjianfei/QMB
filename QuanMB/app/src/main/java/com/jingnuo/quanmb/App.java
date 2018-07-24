package com.jingnuo.quanmb;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;

import com.jingnuo.quanmb.activity.LaunchActivity;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.LogUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.BuildConfig;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/4/9.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this, "5ad55925f43e4835c1000064"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(true);
        /**
         * 极光推送
         */
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String jpushid = JPushInterface.getRegistrationID(getApplicationContext());
        LogUtils.LOG("ceshi", jpushid, "app");
        Staticdata.JpushID = jpushid;

//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectAll()//监测所有内容
//                .penaltyLog()//违规对log日志
//                .penaltyDeath()//违规Crash
//                .build());
//
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectAll()//监测所以内容
//                .penaltyLog()//违规对log日志
//                .penaltyDeath()//违规Crash
//                .build());


        {
            PlatformConfig.setWeixin("wx1589c6a947d1f803", "aad4f32f43f69d06cdaf6df5e1237e8b");
            PlatformConfig.setSinaWeibo("3364493522", "90801d9b64840597f32ed0533e8a2834", "http://www.sina.com.cn/");
            PlatformConfig.setQQZone("1106726779", "1wAnKLtEKebMe8WI");
        }
    }


}
