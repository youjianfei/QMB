package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.BuildConfig;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.ZixunKefuWebActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.Utils;

/**
 * Created by Administrator on 2018/4/10.
 */

public class Popwindow_Updata {
    View  view;
    PopupWindow mPopupWindow;
    private Activity activity;
    InterfacePermission interfacePermission;

    //控件
    LinearLayout linearLayout_coupon;
    ImageView image_cancell;

    Button button_cancle;
    Button button_updata;

    public Popwindow_Updata(Activity activity , InterfacePermission interfacePermission ) {
        this.activity = activity;
        this.interfacePermission=interfacePermission;
    }

    public  void showpopwindow(){
        view  = LayoutInflater.from(activity).inflate(R.layout.popwindow_updata,null,false);
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


        image_cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//取消
                mPopupWindow.dismiss();
            }
        });
        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//暂不更新
                mPopupWindow.dismiss();
            }
        });
        button_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//马上更新
                interfacePermission.onResult(true);
                mPopupWindow.dismiss();
            }
        });
    }

    private void initview() {
        Utils.setAlpha((float) 0.3,activity);
        linearLayout_coupon=view.findViewById(R.id.iamage_coupon);
        image_cancell=view.findViewById(R.id.image_cancell);
        button_cancle=view.findViewById(R.id.button_cancle);
        button_updata=view.findViewById(R.id.button_updata);
        ImageView image = new ImageView(activity);
        image.setBackgroundResource(R.mipmap.updata);
        int w=Staticdata.ScreenWidth-SizeUtils.dip2px(activity,50);
        int h= (int) (w*1.19);
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(w, h);
        image.setLayoutParams(mLayoutparams);
        linearLayout_coupon.addView(image);
    }


}
