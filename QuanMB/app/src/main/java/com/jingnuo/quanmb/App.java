package com.jingnuo.quanmb;

import android.app.Application;
import android.content.Intent;

import com.jingnuo.quanmb.activity.LaunchActivity;
import com.jingnuo.quanmb.class_.MyReceiveMessageListener;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.LogUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.push.RongPushClient;

/**
 * Created by Administrator on 2018/4/9.
 */

public class App extends Application  {

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
        Staticdata.JpushID = jpushid;
        LogUtils.LOG("ceshi","JpushId"+Staticdata.JpushID,"APP");

        /**
         * 融云IM
         */
        RongIM.init(this);
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
        RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener());
        RongIM.getInstance().setMessageAttachedUserInfo(true);

        {
            PlatformConfig.setWeixin("wx1589c6a947d1f803", "aad4f32f43f69d06cdaf6df5e1237e8b");
            PlatformConfig.setSinaWeibo("3364493522", "90801d9b64840597f32ed0533e8a2834", "http://www.sina.com.cn/");
            PlatformConfig.setQQZone("1106726779", "1wAnKLtEKebMe8WI");
        }

//        @Override
//        protected void attachBaseContext(Context base) {
//            super.attachBaseContext(base);
//            MultiDex.install(this);
//        }

        Thread.setDefaultUncaughtExceptionHandler(restartHandler);
    }

    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            restartApp();
        }
    };

    public void restartApp() {
        Intent intent = new Intent(getApplicationContext(), LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        RxBus.getDefault().post(RxBusConstant.FINISH);//把你退出APP的代码放在这，我这里使用了rxbus关闭所有Activity
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
