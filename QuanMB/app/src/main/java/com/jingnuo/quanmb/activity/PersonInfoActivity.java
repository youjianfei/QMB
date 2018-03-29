package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

public class PersonInfoActivity extends BaseActivityother {
    //控件
    TextView mtextview_phonenumber,mTextview_changepassword,mTextview_setsafepassword;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_person_info;
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
    }

    @Override
    protected void initView() {
        mtextview_phonenumber=findViewById(R.id.text_phonrnumber);
        mTextview_changepassword=findViewById(R.id.text_changephonenumber);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    switch (v.getId()){
        case R.id.text_changephonenumber:
            Intent intent_change=new Intent(this,ChangepasswordActivity.class);
            startActivity(intent_change);
            break;
    }
    }
}
