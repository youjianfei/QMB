package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.Utils;

public class Popwindow_helperfirst {
    View view;
    double hight;
    int type;  //1bangshou  2商家
    PopupWindow mPopupWindow;
    Activity activity;


    RelativeLayout relative;

    public Popwindow_helperfirst(Activity activity,int type,double hight) {
        this.activity = activity;
        this.hight=hight;
        this.type=type;
    }

    public  void  showpop(){
        view  = LayoutInflater.from(activity).inflate(R.layout.popwindow_helperfirst,null,false);
        mPopupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.pop_lookpic_background));//// 设置背景图片，不能在布局中设置，要通过代码来设置
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//        Utils.setAlpha((float) 0.3,activity);

        initview();
        initlistenner();
    }

    private void initlistenner() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                Utils.setAlpha((float) 1,activity);
            }
        });
    }

    private void initview() {
        relative=view.findViewById(R.id.relative);
        ImageView image = new ImageView(activity);
        if(type==1){
            image.setBackgroundResource(R.mipmap.helperguize);
        }else {
            image.setBackgroundResource(R.mipmap.shangjiaguize);
        }
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth * hight));
        image.setLayoutParams(mLayoutparams);
        relative.addView(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }
}
