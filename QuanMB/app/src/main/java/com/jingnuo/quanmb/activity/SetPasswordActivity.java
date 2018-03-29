package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingnuo.quanmb.quanmb.R;

public class SetPasswordActivity extends BaseActivityother {
    //控件
    Button mButton_setpassword;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    mButton_setpassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent_login=new Intent(SetPasswordActivity.this, MainActivity.class);
            startActivity(intent_login);
        }
    });
    }

    @Override
    protected void initView() {
        mButton_setpassword=findViewById(R.id.button_setpassword);

    }
}
