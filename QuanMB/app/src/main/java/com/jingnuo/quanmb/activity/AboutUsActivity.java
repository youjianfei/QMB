package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jingnuo.quanmb.App;
import com.jingnuo.quanmb.BuildConfig;
import com.jingnuo.quanmb.R;


public class AboutUsActivity extends BaseActivityother {

    TextView mTextview;
    String versionName = BuildConfig.VERSION_NAME;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mTextview.setText(versionName);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTextview=findViewById(R.id.text_banbenhao);
    }
}
