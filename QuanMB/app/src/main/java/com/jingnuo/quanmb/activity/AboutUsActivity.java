package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingnuo.quanmb.App;
import com.jingnuo.quanmb.BuildConfig;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;


public class AboutUsActivity extends BaseActivityother {

    TextView mTextview;
    TextView mTextview_suggest;
    TextView mTextview_index;


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
        mTextview_index.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mTextview=findViewById(R.id.text_banbenhao);
        mTextview_suggest=findViewById(R.id.textview_aboutus);
        mTextview_index=findViewById(R.id.textview_shareAPP);//官网
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

            case R.id.textview_aboutus :
                Intent intend_suggest=new Intent(this,SuggestActivity.class);
                startActivity(intend_suggest);
                break;
            case R.id.textview_shareAPP :
                Uri uri = Uri.parse(Urls.Baseurl_index);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;



        }
    }
}
