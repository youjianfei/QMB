package com.jingnuo.quanmb.utils;

import android.app.Activity;
import android.view.WindowManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2018/4/2.
 */

public class Utils {

    //设置背景遮罩颜色
    public static void setAlpha(float bgAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
    /**
     * 汉字转为url编码  原因   volley不支持含有汉字的网址
     */
    public static String  ZhuanMa(String string){

        //URL编码
        String nameStr= null;
        try {
            nameStr = new String(URLEncoder.encode(string,"utf-8").getBytes());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return nameStr;
    }

}
