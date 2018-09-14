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
    }

    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(Staticdata.PayissuetaskSuccess&&Staticdata.ispipei){
                Intent intend_think = new Intent(PaySuccessActivity.this, OrderThinkActivity.class);
                intend_think.putExtra("task_id", Staticdata. taskDetailBeanStatic.getData().getTask_id() + "");
                if(Staticdata. taskDetailBeanStatic.getData().getBusiness_name().equals("")){
                    intend_think.putExtra("helpername", Staticdata. taskDetailBeanStatic.getData().getHelper_name() + "");
                }else {
                    intend_think.putExtra("helpername", Staticdata. taskDetailBeanStatic.getData().getBusiness_name() + "");
                }

                intend_think.putExtra("orderno", Staticdata. taskDetailBeanStatic.getData().getSpecialty_name() + "");
                intend_think.putExtra("imageurl", Staticdata. taskDetailBeanStatic.getData().getB_h_url() + "");
                startActivity(intend_think);
                Staticdata. ispipei=false;
                finish();
                return;
            }
            if(Staticdata.PayissuetaskSuccess){
                Intent mainIntent = new Intent(PaySuccessActivity.this, MainActivity.class);
                Intent intent_bargain=new Intent(PaySuccessActivity.this, MyOrderActivity.class);
                Intent[] intents = {mainIntent, intent_bargain};
                Staticdata.PayissuetaskSuccess=false;
                startActivities(intents);
                LogUtils.LOG("pay","222222","PaySuccessActivity");
                finish();
                return;
            }
            }
        });

    }

    @Override
    protected void initView() {
        textview_title=findViewById(R.id.textview_title);
        textview_typesuccess=findViewById(R.id.text_type);
        mButton=findViewById(R.id.button_submit);
        imageView=findViewById(R.id.iv_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent  intent=new Intent(PaySuccessActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
