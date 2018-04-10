package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jingnuo.quanmb.activity.IssueSkillActivity;
import com.jingnuo.quanmb.activity.IssueTaskActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.Utils;

/**
 * Created by Administrator on 2018/4/10.
 */

public class Popwindow_Issue {
    View  view;
    PopupWindow mPopupWindow;
    private Activity activity;

    //控件
    RelativeLayout mRelativelayout;
    ImageView mImageview_issuetask;
    ImageView mImageview_issueskill;
    ImageView mImageview_cancell;


    public Popwindow_Issue(Activity activity) {
        this.activity = activity;
    }

    public  void showpopwindow(){
        view  = LayoutInflater.from(activity).inflate(R.layout.popwindow_issue,null,false);
        mPopupWindow=new PopupWindow(view, Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth*0.64),true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setAnimationStyle(R.style.popissue_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
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
        mImageview_issuetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend_issue_task=new Intent(activity, IssueTaskActivity.class);
                activity.startActivity(intend_issue_task);
                mPopupWindow.dismiss();
            }
        });
        mImageview_issueskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend_issue_skill=new Intent(activity, IssueSkillActivity.class);
                activity.startActivity(intend_issue_skill);
                mPopupWindow.dismiss();
            }
        });
        mImageview_cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
    }

    private void initview() {
        Utils.setAlpha((float) 0.3,activity);
        mRelativelayout=view.findViewById(R.id.relativelayout);
        mImageview_issuetask=view.findViewById(R.id.image_issuetask);
        mImageview_issueskill=view.findViewById(R.id.image_issueskill);
        mImageview_cancell=view.findViewById(R.id.image_cancell);
    }


}
