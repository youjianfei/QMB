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
import com.jingnuo.quanmb.utils.SizeUtils;

public class HelperguizeActivity extends BaseActivityother {
    TextView text_title;
    LinearLayout relative;

    String title="";

    int hight=0;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_helperguize;
    }

    @Override
    protected void setData() {
//        ImageView image = new ImageView(HelperguizeActivity.this);
        if(title.equals("帮手细则")){

            ImageView image = new ImageView(HelperguizeActivity.this);
            image.setBackgroundResource(R.mipmap.helperguize);
            hight=(int) (Staticdata.ScreenWidth * 5.16);
            LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(Staticdata.ScreenWidth,hight );
            image.setLayoutParams(mLayoutparams);
            relative.addView(image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else {

            ImageView image = new ImageView(HelperguizeActivity.this);
            ImageView image2 = new ImageView(HelperguizeActivity.this);
            ImageView image3 = new ImageView(HelperguizeActivity.this);
            image.setBackgroundResource(R.mipmap.shangjiaguize1);
            image2.setBackgroundResource(R.mipmap.shangjiaguize2);
            image3.setBackgroundResource(R.mipmap.shangjiaguize3);
            hight=(int) (Staticdata.ScreenWidth * 5.16);
            LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(Staticdata.ScreenWidth,hight );
            image.setLayoutParams(mLayoutparams);
            image2.setLayoutParams(mLayoutparams);
            image3.setLayoutParams(mLayoutparams);
            relative.addView(image);
            relative.addView(image2);
            relative.addView(image3);
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }


    }

    @Override
    protected void initData() {
        Staticdata.ScreenHight = SizeUtils.getScreenHeightPx(this);
        Staticdata.ScreenWidth = SizeUtils.getScreenWidthPx(this);
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
