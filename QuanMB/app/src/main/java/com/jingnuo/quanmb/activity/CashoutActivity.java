package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.MoneyTextWatcher;

public class CashoutActivity extends BaseActivityother {
    //控件
    EditText mEdit_cash;



    @Override
    public int setLayoutResID() {
        return R.layout.activity_cashout;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mEdit_cash.addTextChangedListener(new MoneyTextWatcher(mEdit_cash).setDigits(2));
    }

    @Override
    protected void initView() {
        mEdit_cash=findViewById(R.id.edit_cashAmount);
    }
}
