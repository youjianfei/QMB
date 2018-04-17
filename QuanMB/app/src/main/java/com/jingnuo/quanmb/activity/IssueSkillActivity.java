package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.class_.Popwindow_SkillType;
import com.jingnuo.quanmb.quanmb.R;

public class IssueSkillActivity extends BaseActivityother {


    //控件
    TextView mTextview_chooce;

    //对象
    Popwindow_SkillType mPopwindow_skilltype;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_skill;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTextview_chooce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopwindow_skilltype=new Popwindow_SkillType(IssueSkillActivity.this, new InterfacePopwindow_SkillType() {
                    @Override
                    public void onSuccesses(String type, int id) {
                        mTextview_chooce.setText(type);
                    }
                });
                mPopwindow_skilltype.showPopwindow();
            }
        });
    }

    @Override
    protected void initView() {
        mTextview_chooce=findViewById(R.id.text_chooce);

    }
}
