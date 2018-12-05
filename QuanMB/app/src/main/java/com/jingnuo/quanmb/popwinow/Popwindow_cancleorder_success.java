package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.Utils;

public class Popwindow_cancleorder_success {
    View view;
    PopupWindow mPopupWindow;
    private Activity activity;

    //控件
    TextView mTextview_cancle;
    TextView mTextview_submit;
    TextView mTextview_tip;

    //对象
    Interence_complteTask interence_complteTask;


    public Popwindow_cancleorder_success(Activity activity, Interence_complteTask interence_complteTask) {
        this.activity = activity;
        this.interence_complteTask=interence_complteTask;
    }

    public void  showPopwindow(){
        view= LayoutInflater.from(activity).inflate(R.layout.popwindow_cancleorder_success,null,false);
        mPopupWindow=new PopupWindow(view, (int) (Staticdata.ScreenWidth*0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popissue_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        Utils.setAlpha((float) 0.3,activity);
        initview();
        initlistenner();
    }

    private void initlistenner() {
        mTextview_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interence_complteTask.onResult(false);
                mPopupWindow.dismiss();
            }
        });
        mTextview_submit.setOnClickListener(new View.OnClickListener() {
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
        mTextview_cancle=view.findViewById(R.id.textview_cancle);
        mTextview_submit=view.findViewById(R.id.textview_submit);
    }

}
