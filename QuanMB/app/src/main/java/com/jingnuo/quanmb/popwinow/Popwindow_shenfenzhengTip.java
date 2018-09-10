package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.Utils;

public class Popwindow_shenfenzhengTip {
    View view;
    Activity activity;
    PopupWindow mPopupWindow;

    ImageView   mImageview;


    //对象
    Interence_complteTask interence_complteTask;

    int  type=0;


    public Popwindow_shenfenzhengTip(  int  type, Activity activity, Interence_complteTask interence_complteTask) {
        this.activity = activity;
        this.interence_complteTask = interence_complteTask;
        this.type=type;
    }

    public void  showPopwindow(){
        view= LayoutInflater.from(activity).inflate(R.layout.popwindow_shenfenzheng_tip,null,false);
        mPopupWindow=new PopupWindow(view, (int) (Staticdata.ScreenWidth*0.7),(int) (Staticdata.ScreenWidth*0.84));
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popissue_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        Utils.setAlpha((float) 0.3,activity);
        initview();
        initlistenner();
    }
    private void initlistenner() {
        mImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interence_complteTask.onResult(true);
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float) 1,activity);
            }
        });
    }

    private void initview() {
        mImageview=view.findViewById(R.id.image_shenfenzhengtip);
        switch (type){
            case 1:
                mImageview.setImageResource(R.mipmap.zhengmian);
                break;
            case 2:
                mImageview.setImageResource(R.mipmap.fanmian);
                break;
            case 3:
                mImageview.setImageResource(R.mipmap.shouchi);
                break;

        }
    }
}
