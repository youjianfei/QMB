package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

import com.jingnuo.quanmb.quanmb.R;

public class ChangephoneNumberActivity extends BaseActivityother {
//控件
    Button mButton_submit;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_changephone_number;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_bindnewphonenumber=new Intent(ChangephoneNumberActivity.this,ChangephoneNumberNextActivity.class);
                    startActivity(intent_bindnewphonenumber);
            }
        });

    }

    @Override
    protected void initView() {
        mButton_submit=findViewById(R.id.buttion_bind);
    }
}
