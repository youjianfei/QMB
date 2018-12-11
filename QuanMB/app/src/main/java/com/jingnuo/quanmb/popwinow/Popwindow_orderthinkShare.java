package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interence_jubao;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.OrderThinkActivity;
import com.jingnuo.quanmb.activity.SharefriendActivity;
import com.jingnuo.quanmb.activity.YaoqingHaoyouActivity;
import com.jingnuo.quanmb.utils.Utils;

public class Popwindow_orderthinkShare {
    View view;
    Activity activity;
    PopupWindow mPopupWindow;

    //控件
   ImageView image_close;
   ImageView image_share;



    public Popwindow_orderthinkShare(Activity activity) {
        this.activity = activity;
    }

    public void  showPopwindow(){
        view= LayoutInflater.from(activity).inflate(R.layout.popwindow_orderthinkshare,null,false);
        mPopupWindow=new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popskilltype_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        initview();
        initlistenner();
        Utils.setAlpha((float) 0.3,activity);
    }
    private void initview() {
        image_close=view.findViewById(R.id.image_close);
        image_share=view.findViewById(R.id.image_share);
    }

    private void initlistenner() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float) 1,activity);

            }
        });
        image_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();

            }
        });
        image_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击分享
                Intent intent_kefuzhongxin = new Intent(activity, YaoqingHaoyouActivity.class);
//                intent_kefuzhongxin.putExtra("webtitle", "优惠活动");
                activity.startActivity(intent_kefuzhongxin);
//                activity.finish();
            }
        });
    }

}
