package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.SizeUtils;


/**
 * Created by Administrator on 2017/9/15.
 */

public class Popwindow_matching {
    Activity activity;
    public  RelativeLayout progress_background;
    public PopupWindow mPopupWindow;
    InterfacePermission interfacePermission;


    public Popwindow_matching(Activity activity, InterfacePermission interfacePermission) {
        this.activity = activity;
        this.interfacePermission = interfacePermission;
    }

    public  void showPopwindow() {
//        if (mPopupWindow != null && mPopupWindow.isShowing()) {
//            return;
//        }
        int hight = SizeUtils.dip2px(activity, 60);
        //初始化popwindow；
        View conView = LayoutInflater.from(activity).inflate(R.layout.popwindow_matching, null, false);
        mPopupWindow = new PopupWindow(conView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        progress_background = conView.findViewById(R.id.progress_backgroud);
        RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(Staticdata.ScreenWidth, Staticdata.ScreenHight - hight);
        progress_background.setLayoutParams(mLayoutParams);
        interfacePermission.onResult(true);

//      Utils.setAlpha((float) 0.3,activity);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                interfacePermission.onResult(false);
            }
        });
    }

    public void dismissPopwindow() {

        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
//        Utils.setAlpha((float) 1,activity);

    }

}
