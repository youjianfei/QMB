package com.jingnuo.quanmb;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.jingnuo.quanmb.utils.LogUtils;
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
        SDKInitializer.initialize(getApplicationContext());//初始化Baidu地图
        UMConfigure.init(this,"5ad55925f43e4835c1000064"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        UMConfigure.setLogEnabled(true);
        /**
         * 极光推送
         */
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
       String jjjj= JPushInterface.getRegistrationID(getApplicationContext());
        LogUtils.LOG("ceshi",jjjj,"app");

    }
    {
        PlatformConfig.setWeixin("wx1589c6a947d1f803", "aad4f32f43f69d06cdaf6df5e1237e8b");
        PlatformConfig.setSinaWeibo("3364493522", "90801d9b64840597f32ed0533e8a2834","http://www.sina.com.cn/");
        PlatformConfig.setQQZone("1106726779", "1wAnKLtEKebMe8WI");
    }
}
