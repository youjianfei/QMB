package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.class_.Popwindow_SkillType;
import com.jingnuo.quanmb.quanmb.R;

public class IssueTaskActivity extends BaseActivityother {

    //控件
    TextView mTextview_skillAddress;
    TextView mTextview_choose;
    TextView mTextview_title;


    //对象
    Popwindow_SkillType mPopwindow_skilltype;



    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTextview_skillAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent_map=new Intent(IssueTaskActivity.this,LocationMapActivity.class);
                startActivityForResult(mIntent_map,2018418);
            }
        });
        mTextview_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        mPopwindow_skilltype=new Popwindow_SkillType(IssueTaskActivity.this, new InterfacePopwindow_SkillType() {
                            @Override
                            public void onSuccesses(String type, int id) {
                                mTextview_choose.setText(type);
                            }
                        });
                        mPopwindow_skilltype.showPopwindow();
            }
        });

    }

    @Override
    protected void initView() {
        mTextview_skillAddress=findViewById(R.id.text_chooseaddress);
        mTextview_choose=findViewById(R.id.text_chooce);
        mTextview_title=findViewById(R.id.edit_tasktitle);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2018418&&resultCode==2018418){
            String address=data.getStringExtra("address");
            mTextview_skillAddress.setText(address);
        }

    }
}
