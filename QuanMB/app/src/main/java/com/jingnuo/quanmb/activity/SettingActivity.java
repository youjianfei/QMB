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
    TextView mTextview_changephonenumber;
    TextView mTextview_changepassword;

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
        mTextview_changepassword.setOnClickListener(this);
        mTextview_changephonenumber.setOnClickListener(this);
        mButton_logout.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mTextview_changephonenumber=findViewById(R.id.textview_changephone);
        mTextview_changepassword=findViewById(R.id.textview_changepassword);
        mButton_logout=findViewById(R.id.button_logout);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.textview_changepassword :
                Intent intent_changepassward=new Intent(this,ChangepasswordActivity.class);
                startActivity(intent_changepassward);

                break;
            case R.id.textview_changephone :
                Intent intent_changephonenumber=new Intent(this,ChangephoneNumberActivity.class);
                startActivity(intent_changephonenumber);

                break;
            case R.id.button_logout :
                SharedPreferencesUtils.putString(SettingActivity.this,"QMB","password","");
                Staticdata.isLogin=false;
                Intent intent_logout=new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(intent_logout);
                break;




        }
    }
}
