package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;

public class SubmitSuccessActivity extends BaseActivityother {
    RelativeLayout mRelativelayout_back;
    Button mButton_close;
    TextView mtextview_bar;
    TextView mtextview_tip;

    String state="";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_submit_success;
    }

    @Override
    protected void setData() {
        ImageView image=new ImageView(this);
        if(state.equals("1")){
            image.setBackgroundResource(R.mipmap.shenhe1);
        }else if(state.equals("2")){
            image.setBackgroundResource(R.mipmap.shenhe2);
            mtextview_bar.setText("正在审核");
        }
        else if(state.equals("3")){
            image.setBackgroundResource(R.mipmap.shenhe2);
            mtextview_bar.setText("审核通过");
            mButton_close.setVisibility(View.VISIBLE);
            mtextview_tip.setText("认证通过，缴纳保证金即可接受任务赚钱");
        }else if(state.equals("4")){
            image.setBackgroundResource(R.mipmap.shenhe3);
            mtextview_bar.setText("审核失败");
            mButton_close.setText("重新认证");
            mButton_close.setVisibility(View.VISIBLE);
        }
        LinearLayout.LayoutParams mLayoutparams=new LinearLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth*0.57));
        image.setLayoutParams(mLayoutparams);
        mRelativelayout_back.addView(image);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        state=intent.getStringExtra("state");

    }

    @Override
    protected void initListener() {
        mButton_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state.equals("3")){
                    Intent intent_payBanzheng=new Intent(SubmitSuccessActivity.this,PayBaozhengmoneyActivity.class);
                    startActivity(intent_payBanzheng);
                    finish();
                }else if(state.equals("4")){
                    Intent intent_again=new Intent(SubmitSuccessActivity.this,AuthenticationActivity.class);
                    startActivity(intent_again);
                    finish();
                }
            }
        });
    }

    @Override
    protected void initView() {
        mtextview_bar=findViewById(R.id.text_bar);
        mButton_close=findViewById(R.id.button_close);
        mRelativelayout_back=findViewById(R.id.relativelayout_back);
        mtextview_tip=findViewById(R.id.text_state);
    }
}
