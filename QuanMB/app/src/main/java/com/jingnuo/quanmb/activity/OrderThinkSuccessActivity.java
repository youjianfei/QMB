package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.popwinow.Popwindow_orderthinkShare;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Timer;
import java.util.TimerTask;

import static com.jingnuo.quanmb.data.Urls.Baseurl_index;

public class OrderThinkSuccessActivity extends BaseActivityother {
    //控件
    Button button_main;
    Button button_issue;

    Popwindow_orderthinkShare popwindow_orderthinkShare;

    Timer timer;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_order_think_success;
    }

    @Override
    protected void setData() {


    }
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                  popwindow_orderthinkShare.showPopwindow();
                    break;
            }
        }


    };
    @Override
    protected void initData() {
        popwindow_orderthinkShare=new Popwindow_orderthinkShare(this);
        //验证码倒计时
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mhandler.sendEmptyMessage(0);
            }
        };
        timer.schedule(timerTask, 400);
    }

    @Override
    protected void initListener() {
        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderThinkSuccessActivity.this,IssueTaskActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderThinkSuccessActivity.this,IssueTaskActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void initView() {
        button_main=findViewById(R.id.button_main);
        button_issue=findViewById(R.id.button_issue);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent intent=new Intent(OrderThinkSuccessActivity.this,IssueTaskActivity.class);
                startActivity(intent);
                finish();
                break;}
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(OrderThinkSuccessActivity.this,IssueTaskActivity.class);
        startActivity(intent);
        finish();
    }
}
