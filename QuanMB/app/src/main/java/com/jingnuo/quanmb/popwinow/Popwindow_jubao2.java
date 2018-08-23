package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interence_jubao;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.utils.Utils;

public class Popwindow_jubao2 {

    View view;
    Activity activity;
    PopupWindow mPopupWindow;
    //控件
    TextView textView_xujia;
    TextView textView_feifa;
    TextView textView_tousu;




    Interence_jubao interence_jubao;

    public Popwindow_jubao2(Activity activity, Interence_jubao interence_jubao) {
        this.activity = activity;
        this.interence_jubao = interence_jubao;
    }
    public void  showPopwindow(){
        view= LayoutInflater.from(activity).inflate(R.layout.popwindow_jubao2,null,false);
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
        textView_xujia=view.findViewById(R.id.text_one1);
        textView_feifa=view.findViewById(R.id.text_one2);
        textView_tousu=view.findViewById(R.id.text_one3);
    }

    private void initlistenner() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float) 1,activity);

            }
        });
        textView_xujia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interence_jubao.onResult("xujia");
                mPopupWindow.dismiss();
            }
        });
        textView_feifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interence_jubao.onResult("feifa");
                mPopupWindow.dismiss();
            }
        });
        textView_tousu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interence_jubao.onResult("tousu");
                mPopupWindow.dismiss();
            }
        });
    }
}
