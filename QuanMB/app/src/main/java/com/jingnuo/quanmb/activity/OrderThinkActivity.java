package com.jingnuo.quanmb.activity;




import android.widget.RatingBar;

import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;


public class OrderThinkActivity extends BaseActivityother {

    //控件
    RatingBar mRatinBar;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_order_think;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mRatinBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                LogUtils.LOG("ceshi",ratingBar.getNumStars()+"geshu"+rating+"rating"+fromUser+"fromUser","评价");
            }
        });

    }

    @Override
    protected void initView() {
        mRatinBar=findViewById(R.id.ratingbar);
    }
}
