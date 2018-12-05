package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.SizeUtils;


/**
 * Created by Administrator on 2017/9/15.
 */

public class Popwindow_matching {
    public static RelativeLayout progress_background;


    public static PopupWindow mPopupWindow;


    public static void showPopwindow(Activity activity) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            return;
        }
        int hight = SizeUtils.dip2px(activity, 64);
        //初始化popwindow；
        View conView = LayoutInflater.from(activity).inflate(R.layout.popwindow_matching, null, false);
        mPopupWindow = new PopupWindow(conView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        progress_background = conView.findViewById(R.id.progress_backgroud);
        RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(Staticdata.ScreenWidth, Staticdata.ScreenHight - hight);
        progress_background.setLayoutParams(mLayoutParams);

//      Utils.setAlpha((float) 0.3,activity);
    }

    public static void dismissPopwindow(Activity activity) {

        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
//        Utils.setAlpha((float) 1,activity);

    }

}
