package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

public class MoneyMingxiDetailsActivity extends BaseActivityother {

    //控件
    TextView mTextview_money;
    TextView mTextview_state;
    TextView mTextview_type;
    TextView mTextview_time;
    TextView mTextview_order;

    //数据
    String  money="";
    String  title="";
    String  type="";
    String  order="";
    String  time="";

    @Override
    public int setLayoutResID() {
        return R.layout.activity_money_mingxi_details;
    }

    @Override
    protected void setData() {
        mTextview_money.setText(money);
        mTextview_order.setText(order);
        mTextview_time.setText(time);
        mTextview_state.setText(title);
        if(type.equals("QMB")){
            mTextview_type.setText("余额支付");
        }else  if(type.equals("WX")){
            mTextview_type.setText("微信支付");
        }else {
            mTextview_type.setText("支付宝支付");
        }
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        money=intent.getStringExtra("money");
        title=intent.getStringExtra("title");
        type=intent.getStringExtra("type");
        order=intent.getStringExtra("order");
        time=intent.getStringExtra("time");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTextview_money=findViewById(R.id.text_money);
        mTextview_state=findViewById(R.id.text_state);
        mTextview_type=findViewById(R.id.text_fangshi);
        mTextview_time=findViewById(R.id.text_time);
        mTextview_order=findViewById(R.id.text_order);

    }
}
