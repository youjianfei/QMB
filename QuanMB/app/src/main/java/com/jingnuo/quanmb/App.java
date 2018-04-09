package com.jingnuo.quanmb;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Administrator on 2018/4/9.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());//初始化Baidu地图
    }
}
