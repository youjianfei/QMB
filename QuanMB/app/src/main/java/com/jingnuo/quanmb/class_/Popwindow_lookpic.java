package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.quanmb.R;

public class Popwindow_lookpic {
    View view;
    PopupWindow mPopupWindow;
    private Activity activity;
    String Url = "";

    //控件
    ImageView imageView_lookpic;


    public Popwindow_lookpic(Activity activity) {
        this.activity = activity;
    }

    public void showPopwindow(String picUrl) {
        Url = picUrl;
        view = LayoutInflater.from(activity).inflate(R.layout.popwindow_lookpic, null, false);

        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.pop_lookpic_background));//// 设置背景图片，不能在布局中设置，要通过代码来设置
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style); // 设置动画
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);//定位pop位置
        initview();
        setview(Url);
    }

    private void setview(String picUrl) {
        Glide.with(activity).load(picUrl).into(imageView_lookpic);
    }

    private void initview() {
        imageView_lookpic = view.findViewById(R.id.image_pic);

    }


}
