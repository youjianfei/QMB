package com.jingnuo.quanmb.utils;

import android.app.Activity;
import android.view.WindowManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    //字符串转时间戳
    public static String getTime(String timeString) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date d;
        try {
            d = sdf.parse(timeString);
            long l = d.getTime();
            timeStamp = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    //时间戳转字符串
    public static String getStrTime(String timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

}
