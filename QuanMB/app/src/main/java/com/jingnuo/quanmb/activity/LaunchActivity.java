package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;

import java.util.Timer;
import java.util.TimerTask;

import static com.jingnuo.quanmb.data.Staticdata.ScreenWidth;


public class LaunchActivity extends BaseActivityother {
    //布局
    LinearLayout mLinearlauout_root;
    //数据
    int Screenhight = 0;
    int Screenwidth = 0;


    @Override
    public int setLayoutResID() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_launch;
    }

    @Override
    protected void setData() {
        mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mhandler.sendEmptyMessage(0);
            }
        };
        mTimer.schedule(timerTask, 1500);
    }

    @Override
    protected void initData() {
        Screenhight = SizeUtils.getScreenHeightPx(this);
        Screenwidth = SizeUtils.getScreenWidthPx(this);
        Staticdata.ScreenHight = Screenhight;
        ScreenWidth = Screenwidth;
        ImageView image=new ImageView(this);
        image.setBackgroundResource(R.mipmap.launchpic);
        LinearLayout.LayoutParams mLayoutparams=new LinearLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth*0.6));
        image.setLayoutParams(mLayoutparams);
        mLinearlauout_root.addView(image);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mLinearlauout_root = findViewById(R.id.Linearlayout_launch);

    }

    Timer mTimer;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mTimer.cancel();
//                    if (isFirstLogin) {
//                        Intent intent = new Intent(LaunchActivity.this, FirstLoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
                    Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    break;
            }
        }


    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
