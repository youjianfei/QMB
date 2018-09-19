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

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.HelperguizeActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.Utils;

public class Popwindow_helperfirst {
    View view;
    double hight;
    int type;  //1bangshou  2商家
    PopupWindow mPopupWindow;
    ImageView image_cancel;
    Activity activity;


    RelativeLayout relative;

    public Popwindow_helperfirst(Activity activity,int type,double hight) {
        this.activity = activity;
        this.hight=hight;
        this.type=type;
    }

    public  void  showpop(){
        view  = LayoutInflater.from(activity).inflate(R.layout.popwindow_helperfirst,null,false);
        mPopupWindow=new PopupWindow(view, (int) (Staticdata.ScreenWidth*0.8), ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setOutsideTouchable(false);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        Utils.setAlpha((float) 0.3,activity);

        initview();
        initlistenner();
    }

    private void initlistenner() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float) 1,activity);
            }
        });
        image_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }

    private void initview() {
        relative=view.findViewById(R.id.relative);
        image_cancel=view.findViewById(R.id.image_cancel);
        ImageView image = new ImageView(activity);
        if(type==1){
            SharedPreferencesUtils.putBoolean(activity, "QMB", "bangshou", false);
            image.setBackgroundResource(R.mipmap.helperxuexi);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent_shopcenter=new Intent(activity, HelperguizeActivity.class);
                intent_shopcenter.putExtra("title","帮手细则");
                activity.startActivity(intent_shopcenter);
                    mPopupWindow.dismiss();
                }
            });
        }else {
            SharedPreferencesUtils.putBoolean(activity, "QMB", "shanghu", false);
            image.setBackgroundResource(R.mipmap.shanghuxuexi);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_shopcenter=new Intent(activity, HelperguizeActivity.class);
                    intent_shopcenter.putExtra("title","接单规则");
                    activity. startActivity(intent_shopcenter);
                    mPopupWindow.dismiss();

                }
            });
        }
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams((int) (Staticdata.ScreenWidth*0.8), (int) (Staticdata.ScreenWidth *0.8* hight));
        image.setLayoutParams(mLayoutparams);
        relative.addView(image);

    }
}
