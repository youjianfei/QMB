package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingnuo.quanmb.App;
import com.jingnuo.quanmb.BuildConfig;
import com.jingnuo.quanmb.R;


public class AboutUsActivity extends BaseActivityother {

    TextView mTextview;
    TextView mTextview_suggest;


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
        mTextview_suggest.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mTextview=findViewById(R.id.text_banbenhao);
        mTextview_suggest=findViewById(R.id.textview_aboutus);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

            case R.id.textview_aboutus :
                Intent intend_suggest=new Intent(this,SuggestActivity.class);
                startActivity(intend_suggest);
                break;


        }
    }
}
