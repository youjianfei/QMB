package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

public class SystemmessageDetailActivity extends BaseActivityother {
    TextView mTextview_title;
    TextView mTextview_content;
    TextView mTextview_time;

    String title="";
    String content="";
    String time="";



    @Override
    public int setLayoutResID() {
        return R.layout.activity_systemmessage_detail;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        title= intent.getStringExtra("title");
        content=intent.getStringExtra("content");
        time=intent.getStringExtra("time");

        mTextview_title.setText(title);
        mTextview_time.setText(time);
        mTextview_content.setText(content);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTextview_title=findViewById(R.id.text_title);
        mTextview_content=findViewById(R.id.text_content);
        mTextview_time=findViewById(R.id.text_time);

    }
}
