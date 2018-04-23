package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;

public class SettingActivity extends BaseActivityother {
    //控件

    Button mButton_logout;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mButton_logout.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mButton_logout=findViewById(R.id.button_logout);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

            case R.id.button_logout :
                SharedPreferencesUtils.putString(SettingActivity.this,"QMB","password","");
                Staticdata.isLogin=false;
                Intent intent_logout=new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(intent_logout);
                break;




        }
    }
}
