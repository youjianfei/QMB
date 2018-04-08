package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

public class TaskDetailsActivity extends BaseActivityother {
    TextView mTextview_tasktitle;
    TextView mTextview_taskmoney;
    TextView mTextview_taskissuetime;
    TextView mTextview_name;
    TextView mTextview_taskdetails;
    TextView mTextview_tasktime;
    TextView mTextview_taskaddress;
    TextView mTextview_peoplelevel;




    @Override
    public int setLayoutResID() {
        return R.layout.activity_task_details;
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
        mTextview_tasktitle=findViewById(R.id.text_tasktitle);
        mTextview_taskmoney=findViewById(R.id.text_taskmoney);
        mTextview_taskissuetime=findViewById(R.id.text_tasktime);
        mTextview_name=findViewById(R.id.text_name);
        mTextview_taskdetails=findViewById(R.id.text_taskdetail);
        mTextview_tasktime=findViewById(R.id.text_time);
        mTextview_taskaddress=findViewById(R.id.text_address);
        mTextview_peoplelevel=findViewById(R.id.text_tlevel);
    }
}
