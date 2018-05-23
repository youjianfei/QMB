package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.quanmb.R;

public class MySkillCollectActivity extends BaseActivityother {

    //控件
    PullToRefreshListView  mListview;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_my_skill_collect;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mListview=findViewById(R.id.mlistview_skillcollect);

    }
}
