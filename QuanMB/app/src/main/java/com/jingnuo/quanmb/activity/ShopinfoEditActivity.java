package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.quanmb.R;

public class ShopinfoEditActivity extends BaseActivityother {
    //控件
    Button mButton_submit;
    EditText mEdit_jianjie;
    MyGridView  myGridView;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_shopinfo_edit;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mButton_submit=findViewById(R.id.button_submit);
        mEdit_jianjie=findViewById(R.id.edit_jianjie);
        myGridView=findViewById(R.id.GridView_PIC);
    }
}
