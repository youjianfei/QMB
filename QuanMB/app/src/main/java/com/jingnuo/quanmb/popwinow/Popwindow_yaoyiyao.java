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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.ZixunKefuWebActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.Utils;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2018/4/10.
 */

public class Popwindow_yaoyiyao {
    View view;
    PopupWindow mPopupWindow;
    private Activity activity;

    //控件
    ImageView image_cancell;
    GifImageView gifimageview;
    TextView textview_name;

    String imageURL = "";
    String name="";

    public Popwindow_yaoyiyao(Activity activity, String imageURL,String name) {
        this.activity = activity;
        this.name=name;
        this.imageURL=imageURL;
    }

    public void showpopwindow() {
        view = LayoutInflater.from(activity).inflate(R.layout.popwindow_yaoyiyao, null, false);
        mPopupWindow = new PopupWindow(view, Staticdata.ScreenWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true);
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
                Utils.setAlpha((float) 1, activity);
            }
        });


        image_cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//取消
                mPopupWindow.dismiss();
            }
        });
    }

    private void initview() {
        Utils.setAlpha((float) 0.3, activity);
        image_cancell = view.findViewById(R.id.image_cancell);
        gifimageview = view.findViewById(R.id.gifimageview);
        textview_name = view.findViewById(R.id.textview_name);
        textview_name.setText("恭喜您获得"+name);
        Glide.with(activity).load(imageURL).into(gifimageview);
    }


}
