package com.jingnuo.quanmb.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.ShouyeRadios;
import com.jingnuo.quanmb.entityclass.YaoyiyaoBean;
import com.jingnuo.quanmb.popwinow.Popwindow_yaoyiyao;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ShakeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class ChoujiangActivity extends BaseActivityother {
    //控件
    GifImageView gifimageview;
    TextSwitcher tv_notice;
    TextView textview_count;
    ImageView image_close;

    ShakeUtils shakeUtils;
    YaoyiyaoBean yaoyiyaoBean;

    int time = 0;

    String ID = "";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_choujiang;
    }

    @Override
    protected void setData() {
        shakeUtils = new ShakeUtils(this);
        shakeUtils.setOnShakeListener(new ShakeUtils.OnShakeListener() {
            @Override
            public void onShake() {
                LogUtils.LOG("yyy", "摇过之后", "抽红包");
                time++;
                if (time == 1) {//网络请求中奖接口
//                    time=0;
                    request();
                }
            }
        });
        tv_notice.setFactory(new ViewSwitcher.ViewFactory() {
            // 这里用来创建内部的视图，这里创建TextView，用来显示文字
            @RequiresApi(api = Build.VERSION_CODES.M)
            public View makeView() {
                TextView tv = new TextView(ChoujiangActivity.this);
                // 设置文字的显示单位以及文字的大小
                tv.setTextSize(12);
                tv.setTextColor(getResources().getColor(R.color.gray_969696));
                return tv;
            }
        });
        tv_notice.setInAnimation(ChoujiangActivity.this,
                R.anim.slide_in_bottom);
        tv_notice.setOutAnimation(getApplicationContext(), R.anim.slide_out_up);
    }

    void request() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                yaoyiyaoBean = new Gson().fromJson(respose, YaoyiyaoBean.class);
                if (yaoyiyaoBean.getCode() == 1) {
                    textview_count.setText("0");
                    Vibrator vibrator = (Vibrator) ChoujiangActivity.this.getSystemService(Service.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                    new Popwindow_yaoyiyao(ChoujiangActivity.this,yaoyiyaoBean.getData().getGif_url(),
                            yaoyiyaoBean.getData().getPrize_name()).showpopwindow();

//                    switch (yaoyiyaoBean.getData().getId()) {
//                        case 1:
//                            Glide.with(ChoujiangActivity.this).load(yaoyiyaoBean.getData().getGif_url()).into(gifimageview);
////                            gifimageview.setImageResource(R.mipmap.jiangpingif1);
//                            break;
//                        case 2:
////                            gifimageview.setImageResource(R.mipmap.jiangpingif2);
//                            Glide.with(ChoujiangActivity.this).load(yaoyiyaoBean.getData().getGif_url()).into(gifimageview);
//
//                            break;
//                        case 3:
////                            gifimageview.setImageResource(R.mipmap.jiangpingif3);
//                            Glide.with(ChoujiangActivity.this).load(yaoyiyaoBean.getData().getGif_url()).into(gifimageview);
//
//                            break;
//                        case 4:
////                            gifimageview.setImageResource(R.mipmap.jiangpingif4);
//                            Glide.with(ChoujiangActivity.this).load(yaoyiyaoBean.getData().getGif_url()).into(gifimageview);
//
//                            break;
//                    }
                    ToastUtils.showToast(ChoujiangActivity.this, yaoyiyaoBean.getData().getPrize_name());
                } else {
                    textview_count.setText("0");
                    ToastUtils.showToast(ChoujiangActivity.this, "奖品与你擦肩而过");
                }

            }

            @Override
            public void onError(int error) {
                textview_count.setText("0");
                ToastUtils.showToast(ChoujiangActivity.this, "奖品与你擦肩而过");
            }
        }).Http(Urls.Baseurl_cui + Urls.shoujiangyaoyiyao + Staticdata.static_userBean.getData().getUser_token()+"&task_id="+ID, ChoujiangActivity.this, 0);

    }

    @Override
    protected void initData() {
        ID = getIntent().getStringExtra("task_id");
        data = new ArrayList<>();
        requestRadios();
    }

    @Override
    protected void initListener() {
        image_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend_think = new Intent(ChoujiangActivity.this, OrderThinkActivity.class);
                intend_think.putExtra("task_id", ID + "");
                startActivity(intend_think);
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        tv_notice = findViewById(R.id.tv_notice);
        gifimageview = findViewById(R.id.gifimageview);
        textview_count = findViewById(R.id.textview_count);
        image_close = findViewById(R.id.image_close);
    }

    List<ShouyeRadios.DataBean> data;

    void requestRadios() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose + "喇叭", "首页");
                data.clear();
                data.addAll(new Gson().fromJson(respose, ShouyeRadios.class).getData());
                mHandler.sendEmptyMessage(HOME_AD_RESULT);
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_hu + Urls.shouyeRadios, this, 0);
    }

    private final int HOME_AD_RESULT = 1;
    private int mSwitcherCount = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                // 广告
                case HOME_AD_RESULT:
                    tv_notice.setText(data.get(mSwitcherCount % data.size()).getContent());
                    mSwitcherCount++;
                    mHandler.sendEmptyMessageDelayed(HOME_AD_RESULT, 3000);
                    break;
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        shakeUtils.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeUtils.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent intend_think = new Intent(ChoujiangActivity.this, OrderThinkActivity.class);
        intend_think.putExtra("task_id", ID + "");
        startActivity(intend_think);
        finish();
    }
}
