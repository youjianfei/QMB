package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingnuo.quanmb.quanmb.R;

public class SettingActivity extends BaseActivityother {
    //控件
    Button mButton_changepassword;
    Button mButton_changephonenumber;
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
        mButton_changepassword.setOnClickListener(this);
        mButton_changephonenumber.setOnClickListener(this);

    }

    @Override
    protected void initView() {
    mButton_changepassword=findViewById(R.id.change_password);
        mButton_changephonenumber=findViewById(R.id.change_phonenumber);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.change_password :
                Intent intent_changepassward=new Intent(this,ChangepasswordActivity.class);
                startActivity(intent_changepassward);

                break;
            case R.id.change_phonenumber :
                Intent intent_changephonenumber=new Intent(this,ChangephoneNumberActivity.class);
                startActivity(intent_changephonenumber);

                break;
        }
    }
}
