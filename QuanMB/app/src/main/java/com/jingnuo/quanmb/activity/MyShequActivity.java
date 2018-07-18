package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jingnuo.quanmb.quanmb.R;

public class MyShequActivity extends BaseActivityother {
    //控件
    LinearLayout mLinearlayout_fabbu;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_my_shequ;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mLinearlayout_fabbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_fabu=new Intent(MyShequActivity.this,MessageWallEditActivity.class);
                startActivity(intent_fabu);
            }
        });
    }

    @Override
    protected void initView() {
        mLinearlayout_fabbu=findViewById(R.id.linearlayout_fabu);
    }
}
