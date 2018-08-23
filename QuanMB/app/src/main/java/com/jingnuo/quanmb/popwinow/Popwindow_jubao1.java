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

public class Popwindow_jubao1 {
    View view;
    Activity activity;
    PopupWindow mPopupWindow;

    //控件
    TextView textView_jybao;
    TextView textView_cancle;


    Interence_jubao interence_jubao;

    public Popwindow_jubao1(Activity activity, Interence_jubao interence_jubao) {
        this.activity = activity;
        this.interence_jubao = interence_jubao;
    }

    public void  showPopwindow(){
        view= LayoutInflater.from(activity).inflate(R.layout.popwindow_jubao1,null,false);
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
        textView_jybao=view.findViewById(R.id.text_one);
        textView_cancle=view.findViewById(R.id.text_two);
    }

    private void initlistenner() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float) 1,activity);

            }
        });
        textView_jybao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                interence_jubao.onResult("jubao");

            }
        });
        textView_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interence_jubao.onResult("cancel");
                mPopupWindow.dismiss();
            }
        });
    }

}
