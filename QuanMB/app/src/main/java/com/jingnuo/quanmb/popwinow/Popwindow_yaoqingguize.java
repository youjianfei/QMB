package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.ZixunKefuWebActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.Utils;

/**
 * Created by Administrator on 2018/4/10.
 */

public class Popwindow_yaoqingguize {
    View  view;
    PopupWindow mPopupWindow;
    private Activity activity;

    //控件
    LinearLayout linearLayout_coupon;
    ImageView iamge_cancle;

    public Popwindow_yaoqingguize(Activity activity  ) {
        this.activity = activity;
    }

    public  void showpopwindow(){
        view  = LayoutInflater.from(activity).inflate(R.layout.popwindow_yaoqingguize,null,false);
        mPopupWindow=new PopupWindow(view, Staticdata.ScreenWidth, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popissue_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        initview();
        initlistenner();

    }

    private void initlistenner() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float)1,activity);
            }
        });

    }

    private void initview() {
        Utils.setAlpha((float) 0.3,activity);
        linearLayout_coupon=view.findViewById(R.id.iamage_coupon);
        iamge_cancle=view.findViewById(R.id.iamge_cancle);
        ImageView image = new ImageView(activity);
        image.setBackgroundResource(R.mipmap.yaoqingtankuang);
        int w=Staticdata.ScreenWidth-SizeUtils.dip2px(activity,40);
        int h= w;
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(w, h);
        image.setLayoutParams(mLayoutparams);
        linearLayout_coupon.addView(image);
        iamge_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }


}
