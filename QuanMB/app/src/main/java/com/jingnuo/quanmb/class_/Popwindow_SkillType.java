package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.Utils;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class Popwindow_SkillType {
    View conView;
    private Activity activity;
    PopupWindow mPopupWindow;

    //控件
    TextView mTextview_one;
    TextView mTextview_two;
    ImageView mImage_close;
    ListView popListview;

    public Popwindow_SkillType(Activity activity) {
        this.activity = activity;
    }

    public void showPopwindow() {

        //初始化popwindow；
        View conView= LayoutInflater.from(activity).inflate(R.layout.popwindow_skilltype,null,false);
        mPopupWindow=new PopupWindow(conView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        popListview= conView.findViewById(R.id.Listview_addresspopwindow);
        mImage_close= conView.findViewById(R.id.ImageView_close);
        mTextview_one= conView.findViewById(R.id.text_one);
        mTextview_two=  conView.findViewById(R.id.text_two);
        Utils.setAlpha((float) 0.3,activity);
        initlistennr();

    }

    private void initlistennr() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha(1,activity);
            }
        });
    }

}
