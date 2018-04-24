package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;

public class HelperOrderActivity extends BaseActivityother {

    TextView mTextview_state;//状态
    TextView mTextview_titile;//标题
    TextView mTextview_money;//佣金
    TextView mTextview_time;//发布时间
    TextView mTextview_peoplename;//雇主
    TextView mTextview_taskDetail;//任务描述
    TextView mTextview_resttime;//剩余时间
    TextView mTextview_address;//地点
    TextView mTextview_phonenumber;//客户电话




    @Override
    public int setLayoutResID() {
        return R.layout.activity_helper_order;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        if(Staticdata.queRenHelp_bean!=null){
            mTextview_titile.setText(Staticdata.queRenHelp_bean.getDate().getTask_name());
            mTextview_state.setText(Staticdata.queRenHelp_bean.getDate().getStatus_name());
            mTextview_money.setText(Staticdata.queRenHelp_bean.getDate().getCommission());
            mTextview_time.setText(Staticdata.queRenHelp_bean.getDate().getTask_StartDate());
            mTextview_peoplename.setText(Staticdata.queRenHelp_bean.getDate().getClient_name());
            mTextview_taskDetail.setText(Staticdata.queRenHelp_bean.getDate().getTask_description());
            mTextview_resttime.setText(Staticdata.queRenHelp_bean.getDate().getTask_EndDate());
            mTextview_address.setText(Staticdata.queRenHelp_bean.getDate().getDetailed_address());
            mTextview_phonenumber.setText(Staticdata.queRenHelp_bean.getDate().getMobile_no());
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTextview_state=findViewById(R.id.text_taskstate);
        mTextview_titile=findViewById(R.id.text_tasktitle);
        mTextview_money=findViewById(R.id.text_taskmoney);
        mTextview_time=findViewById(R.id.text_tasktime);
        mTextview_peoplename=findViewById(R.id.text_name);
        mTextview_taskDetail=findViewById(R.id.text_taskdetail);
        mTextview_resttime=findViewById(R.id.text_time);
        mTextview_address=findViewById(R.id.text_address);
        mTextview_phonenumber=findViewById(R.id.text_number);
    }
}
