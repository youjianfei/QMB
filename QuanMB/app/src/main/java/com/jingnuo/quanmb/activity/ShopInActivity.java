package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;

public class ShopInActivity extends BaseActivityother {
    //布局
    private LinearLayout mLineatlayout_addview;

    //控件
    private Button mButton_submit;



    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_in;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        //添加头部3个步骤的图片
        ImageView imageview_shopin=new ImageView(this);
        imageview_shopin.setBackgroundResource(R.mipmap.shopin_1);
        LinearLayout.LayoutParams mLayoutparams=new LinearLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth*0.27));
        imageview_shopin.setLayoutParams(mLayoutparams);
        mLineatlayout_addview.addView(imageview_shopin);
    }

    @Override
    protected void initListener() {
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent_shopIn_next=new Intent(ShopInActivity.this,ShopInNextActivity.class);
                startActivity(intent_shopIn_next);
            }
        });


    }

    @Override
    protected void initView() {
        mLineatlayout_addview=findViewById(R.id.linrarlayout_shop);
        mButton_submit=findViewById(R.id.button_submit);


    }
}
