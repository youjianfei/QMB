package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

public class IssueTaskActivity extends BaseActivityother {

    //控件
    TextView mTextview_skillAddress;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTextview_skillAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent_map=new Intent(IssueTaskActivity.this,LocationMapActivity.class);
                startActivity(mIntent_map);
            }
        });

    }

    @Override
    protected void initView() {
        mTextview_skillAddress=findViewById(R.id.text_chooseaddress);

    }
}
