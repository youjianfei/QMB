package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;

public class PaySuccessActivity extends BaseActivityother {

    TextView textview_title;
    TextView textview_typesuccess;
    Button  mButton;
    ImageView imageView;

    String  title="";
    String  typesuccess="";
    String taskid="";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void setData() {
        textview_title.setText(title);
        textview_typesuccess.setText(typesuccess);
    }

    @Override
    protected void initData() {
        title=getIntent().getStringExtra("title");
        typesuccess=getIntent().getStringExtra("typesuccess");
        if(getIntent().getStringExtra("taskid")!=null){
            taskid=getIntent().getStringExtra("taskid");
        }
    }

    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(Staticdata.PayissuetaskSuccess){
                Intent intend_think = new Intent(PaySuccessActivity.this, OrderThinkActivity.class);
                intend_think.putExtra("task_id", taskid+ "");
                startActivity(intend_think);
                Staticdata. ispipei=false;
                LogUtils.LOG("payqq","11111","PaySuccessActivity");
                finish();
                return;
            }
//            if(Staticdata.PayissuetaskSuccess){
//                Intent mainIntent = new Intent(PaySuccessActivity.this, IssueTaskActivity.class);
//                Intent intent_bargain=new Intent(PaySuccessActivity.this, MyOrderActivity.class);
//                Intent[] intents = {mainIntent, intent_bargain};
//                Staticdata.PayissuetaskSuccess=false;
//                startActivities(intents);
//                LogUtils.LOG("payqq","222222","PaySuccessActivity");
//                finish();
//                return;
//            }
//                if(Staticdata.PayissuetaskSuccess){
                    Intent mainIntent = new Intent(PaySuccessActivity.this, IssueTaskActivity.class);
                    startActivity(mainIntent);
                    LogUtils.LOG("payqq","退款成功","PaySuccessActivity");
                    finish();
                    return;
//                }
            }
        });

    }

    @Override
    protected void initView() {
        textview_title=findViewById(R.id.textview_maintitle);
        textview_typesuccess=findViewById(R.id.text_type);
        mButton=findViewById(R.id.button_submit);
        imageView=findViewById(R.id.iv_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent  intent=new Intent(PaySuccessActivity.this,IssueTaskActivity.class);
                startActivity(intent);
                break;
        }
    }
}
