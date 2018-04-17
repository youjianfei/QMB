package com.jingnuo.quanmb;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

/**
 * Created by Administrator on 2018/4/9.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());//初始化Baidu地图
        UMConfigure.init(this,"5ad55925f43e4835c1000064"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        UMConfigure.setLogEnabled(true);
    }
}
