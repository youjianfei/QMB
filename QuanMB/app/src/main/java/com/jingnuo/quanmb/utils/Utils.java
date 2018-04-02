package com.jingnuo.quanmb.utils;

import android.app.Activity;
import android.view.WindowManager;

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

}
