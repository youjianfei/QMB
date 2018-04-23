package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;

public class PersonInfoActivity extends BaseActivityother {
    //控件
    TextView mtextview_phonenumber,mTextview_changepassword,mTextview_setsafepassword;
    TextView mtextview_nickname;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mtextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
        mtextview_phonenumber.setText(Staticdata.static_userBean.getData().getAppuser().getMobile_no());
    }

    @Override
    protected void initListener() {
        mTextview_changepassword.setOnClickListener(this);
        mtextview_nickname.setOnClickListener(this);
        mtextview_phonenumber.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mtextview_phonenumber=findViewById(R.id.text_phonrnumber);
        mTextview_changepassword=findViewById(R.id.text_changephonenumber);
        mtextview_nickname=findViewById(R.id.text_name);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    switch (v.getId()){
        case R.id.text_changephonenumber:
            Intent intent_change=new Intent(this,ChangepasswordActivity.class);
            startActivity(intent_change);
            break;
        case R.id.text_name:
            Intent intent_nickname =new Intent(this,SetNicknameActivity.class);
            startActivity(intent_nickname);
            break;
        case R.id.text_phonrnumber:
            Intent intent_change_phone =new Intent(this,ChangephoneNumberActivity.class);
            startActivity(intent_change_phone);
            break;


    }
    }
}
