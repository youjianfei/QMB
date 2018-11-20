package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.activity.IssueSkillActivity;
import com.jingnuo.quanmb.activity.IssueTaskActivity;
import com.jingnuo.quanmb.activity.ZixunKefuWebActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.R;
/**
 * Created by Administrator on 2018/4/10.
 */

public class Popwindow_coupon {
    View  view;
    PopupWindow mPopupWindow;
    private Activity activity;
    private  String ImageBG;
    private  String Imagesub;

    //控件
    LinearLayout linearLayout_coupon;
    ImageView image_cancell;
    ImageView image_get;


    public Popwindow_coupon(Activity activity,String ImageBG,String Imagesub ) {
        this.activity = activity;
        this.ImageBG=ImageBG;
        this.Imagesub=Imagesub;
    }

    public  void showpopwindow(){
        view  = LayoutInflater.from(activity).inflate(R.layout.popwindow_coupon,null,false);
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
        image_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//立即领取
//                Uri uri = Uri.parse(Urls.newpeoplecoupon);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                activity.startActivity(intent);
                Intent intent_kefuzhongxin = new Intent(activity, ZixunKefuWebActivity.class);
                intent_kefuzhongxin.putExtra("webtitle", "优惠活动");
                intent_kefuzhongxin.putExtra("type", "首页弹窗");
                activity.startActivity(intent_kefuzhongxin);
                mPopupWindow.dismiss();
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
        Utils.setAlpha((float) 0.3,activity);
        linearLayout_coupon=view.findViewById(R.id.iamage_coupon);
        image_cancell=view.findViewById(R.id.image_cancell);
        image_get=view.findViewById(R.id.image_get);
        Glide.with(activity).load(Imagesub).into(image_get);
        ImageView image = new ImageView(activity);
        Glide.with(activity).load(ImageBG).into(image);
        image.setBackgroundResource(R.mipmap.maincoupon);
        int w=Staticdata.ScreenWidth-SizeUtils.dip2px(activity,20);
        int h= (int) (w*1.08);
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(w, h);
        image.setLayoutParams(mLayoutparams);
        linearLayout_coupon.addView(image);
    }


}
