package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.Volley_Utils;

public class TaskDetailsActivity extends BaseActivityother {
    TextView mTextview_tasktitle;
    TextView mTextview_taskmoney;
    TextView mTextview_taskissuetime;
    TextView mTextview_name;
    TextView mTextview_taskdetails;
    TextView mTextview_tasktime;
    TextView mTextview_taskaddress;
    TextView mTextview_peoplelevel;

    //数据
    int ID=0;//任务id;
    //对象
    TaskDetailBean  mTaskData;




    @Override
    public int setLayoutResID() {
        return R.layout.activity_task_details;
    }

    @Override
    protected void setData() {
        requestTaseDetail();
    }

    @Override
    protected void initData() {
        Intent intend_id=getIntent();
        ID=intend_id.getIntExtra("id",0);


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

    void requestTaseDetail(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
            mTaskData=new Gson().fromJson(respose,TaskDetailBean.class);
                mTextview_tasktitle.setText(mTaskData.getData().get(0).getTask_name());
                mTextview_taskmoney.setText("佣金："+mTaskData.getData().get(0).getCommission()+"元");
                mTextview_taskissuetime.setText(mTaskData.getData().get(0).getTask_StartDate());
                mTextview_name.setText(mTaskData.getData().get(0).getName());
                mTextview_taskdetails.setText(mTaskData.getData().get(0).getTask_description());

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.taskdetails+"?id="+ID,this,0);



    }

}
