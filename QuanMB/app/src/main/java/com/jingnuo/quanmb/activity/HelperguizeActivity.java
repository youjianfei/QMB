package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;

public class HelperguizeActivity extends BaseActivityother {
    TextView text_title;
    RelativeLayout relative;

    String title="";
    int hight=0;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_helperguize;
    }

    @Override
    protected void setData() {
        ImageView image = new ImageView(HelperguizeActivity.this);
        if(title.equals("帮手细则")){
            image.setBackgroundResource(R.mipmap.helperguize);
            hight=(int) (Staticdata.ScreenWidth * 5.16);
            SharedPreferencesUtils.putBoolean(this, "QMB", "bangshou", false);

        }else {
            image.setBackgroundResource(R.mipmap.shangjiaguize);
            hight=(int) (Staticdata.ScreenWidth * 12.1);
            SharedPreferencesUtils.putBoolean(this, "QMB", "shanghu", false);


        }
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(Staticdata.ScreenWidth,hight );
        image.setLayoutParams(mLayoutparams);
        relative.addView(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        title=getIntent().getStringExtra("title");
        text_title.setText(title);
    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initView() {
        text_title=findViewById(R.id.text_title);
        relative=findViewById(R.id.relative);

    }
}
