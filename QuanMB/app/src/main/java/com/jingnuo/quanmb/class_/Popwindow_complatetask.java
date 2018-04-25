package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.Utils;

/**
 * Created by 飞 on 2018/4/25.
 */

public class Popwindow_complatetask {
    View view;
    PopupWindow mPopupWindow;
    private Activity activity;


    public Popwindow_complatetask(Activity activity) {
        this.activity = activity;
    }

    public void  showPopwindow(){
        view= LayoutInflater.from(activity).inflate(R.layout.popwindow_complatetask,null,false);
        mPopupWindow=new PopupWindow(view, (int) (Staticdata.ScreenWidth*0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setAnimationStyle(R.style.popissue_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        Utils.setAlpha((float) 0.3,activity);
    }
}
