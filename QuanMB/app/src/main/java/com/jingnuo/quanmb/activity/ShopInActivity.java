package com.jingnuo.quanmb.activity;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;

public class ShopInActivity extends BaseActivityother {
    //布局
    private LinearLayout mLineatlayout_addview;



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


    }

    @Override
    protected void initView() {
        mLineatlayout_addview=findViewById(R.id.linrarlayout_shop);



    }
}
