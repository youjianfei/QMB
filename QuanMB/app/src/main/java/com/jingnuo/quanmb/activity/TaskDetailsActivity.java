package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

public class TaskDetailsActivity extends BaseActivityother {
    TextView mTextview_state;
    TextView mTextview_tasktitle;
    TextView mTextview_taskmoney;
    TextView mTextview_taskissuetime;
    TextView mTextview_name;
    TextView mTextview_taskdetails;
    TextView mTextview_tasktime;
    TextView mTextview_taskaddress;
    TextView mTextview_peoplelevel;

    Button mButton_help;
    Button mButton_counteroffer;

    //数据
    int ID=0;//任务id;
    String is_counteroffer="";

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
        //确认帮助请求//todo   确认帮助请求
        mButton_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new Volley_Utils(new Interface_volley_respose() {
                @Override
                public void onSuccesses(String respose) {


                }

                @Override
                public void onError(int error) {

                }
            }).Http(Urls.Baseurl_cui+Urls.helptask,TaskDetailsActivity.this,0);
            }
        });

    }

    @Override
    protected void initView() {
        mTextview_state=findViewById(R.id.text_taskstate);
        mTextview_tasktitle=findViewById(R.id.text_tasktitle);
        mTextview_taskmoney=findViewById(R.id.text_taskmoney);
        mTextview_taskissuetime=findViewById(R.id.text_tasktime);
        mTextview_name=findViewById(R.id.text_name);
        mTextview_taskdetails=findViewById(R.id.text_taskdetail);
        mTextview_tasktime=findViewById(R.id.text_time);
        mTextview_taskaddress=findViewById(R.id.text_address);
        mTextview_peoplelevel=findViewById(R.id.text_tlevel);
        mButton_help=findViewById(R.id.button_help);
        mButton_counteroffer=findViewById(R.id.button_bargain);
    }

    void requestTaseDetail(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi","任务详情返回信息"+respose,"TaskDetailsActivity");
                 mTaskData=new Gson().fromJson(respose,TaskDetailBean.class);
                mTextview_state.setText(mTaskData.getData().getStatus_name());
                mTextview_tasktitle.setText(mTaskData.getData().getTask_name());
                mTextview_taskmoney.setText("佣金："+mTaskData.getData().getCommission()+"元");
                mTextview_taskissuetime.setText(mTaskData.getData().getTask_StartDate());
                mTextview_name.setText(mTaskData.getData().getName());
                mTextview_taskdetails.setText(mTaskData.getData().getTask_description());
                mTextview_tasktime.setText(mTaskData.getData().getTask_EndDate());
                mTextview_taskaddress.setText(mTaskData.getData().getDetailed_address());
                mTextview_peoplelevel.setText(mTaskData.getData().getUser_grade());
                is_counteroffer=mTaskData.getData().getIs_counteroffer();
                if(is_counteroffer.equals("1")){
                    mButton_counteroffer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.taskdetails+"?id="+ID,this,0);



    }

}
