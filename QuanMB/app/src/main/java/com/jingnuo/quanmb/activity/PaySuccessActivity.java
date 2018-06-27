package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

public class PaySuccessActivity extends BaseActivityother {

    TextView textview_title;
    TextView textview_typesuccess;
    Button  mButton;

    String  title="";
    String  typesuccess="";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void setData() {
        textview_title.setText(title);
        textview_typesuccess.setText(typesuccess);
    }

    @Override
    protected void initData() {
        title=getIntent().getStringExtra("title");
        typesuccess=getIntent().getStringExtra("typesuccess");
    }

    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

    }

    @Override
    protected void initView() {
        textview_title=findViewById(R.id.textview_title);
        textview_typesuccess=findViewById(R.id.text_type);
        mButton=findViewById(R.id.button_submit);
    }
}