package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interence_bargin;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_bargin;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.QueRenHelp_Bean;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    int ID = 0;//任务id;
    String is_counteroffer = "";

    //对象
    TaskDetailBean mTaskData;

    Popwindow_bargin popwindow_bargin;


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
        Intent intend_id = getIntent();
        ID = intend_id.getIntExtra("id", 0);
        popwindow_bargin = new Popwindow_bargin(this, new Interence_bargin() {
            @Override
            public void onResult(String result) {//还价网络请求
                ToastUtils.showToast(TaskDetailsActivity.this, result);
                Map map_bargin = new HashMap();
                map_bargin.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                map_bargin.put("task_id", "" + ID);
                map_bargin.put("counteroffer_Amount", result);
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi", "任务还价+" + respose, "TaskDetailsActivity");
                        int status = 0;
                        String msg = "";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//登录状态
                            msg = (String) object.get("message");//登录返回信息
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (status == 1) {
                            ToastUtils.showToast(TaskDetailsActivity.this, "还价申请已发出");
                        } else {
                            ToastUtils.showToast(TaskDetailsActivity.this, msg);
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui + Urls.barginmonry, TaskDetailsActivity.this, 1, map_bargin);


            }
        });


    }

    @Override
    protected void initListener() {
        //确认帮助请求
        mButton_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Staticdata.isLogin) {//是否登录
                    LogUtils.LOG("ceshi", "确认帮助网址+" + Urls.Baseurl_cui + Urls.helptask + "?tid=" + ID + "&user_token=" + Staticdata.static_userBean.getData().getUser_token(), "TaskDetailsActivity");
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi", "确认帮助+" + respose, "TaskDetailsActivity");
                            Staticdata.queRenHelp_bean = new Gson().fromJson(respose, QueRenHelp_Bean.class);
                            if (Staticdata.queRenHelp_bean.getStatus() == 1) {
                                Intent intent_querenhelp = new Intent(TaskDetailsActivity.this, HelperOrderActivity.class);
                                startActivity(intent_querenhelp);
                                finish();


                            } else {
                                ToastUtils.showToast(TaskDetailsActivity.this, Staticdata.queRenHelp_bean.getMessage());
                            }
                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).Http(Urls.Baseurl_cui + Urls.helptask + "?tid=" + ID + "&user_token=" + Staticdata.static_userBean.getData().getUser_token(), TaskDetailsActivity.this, 0);

                } else {
                    Intent intent_login = new Intent(TaskDetailsActivity.this, LoginActivity.class);
                    startActivity(intent_login);
                }
            }
        });
        //还价
        mButton_counteroffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popwindow_bargin.showpop();
            }
        });
    }

    @Override
    protected void initView() {
        mTextview_state = findViewById(R.id.text_taskstate);
        mTextview_tasktitle = findViewById(R.id.text_tasktitle);
        mTextview_taskmoney = findViewById(R.id.text_taskmoney);
        mTextview_taskissuetime = findViewById(R.id.text_tasktime);
        mTextview_name = findViewById(R.id.text_name);
        mTextview_taskdetails = findViewById(R.id.text_taskdetail);
        mTextview_tasktime = findViewById(R.id.text_time);
        mTextview_taskaddress = findViewById(R.id.text_address);
        mTextview_peoplelevel = findViewById(R.id.text_tlevel);
        mButton_help = findViewById(R.id.button_help);
        mButton_counteroffer = findViewById(R.id.button_bargain);
    }

    void requestTaseDetail() {
        LogUtils.LOG("ceshi", "任务详情接口+" + Urls.Baseurl_cui + Urls.taskdetails + "?id=" + ID, "TaskDetailsActivity");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", "任务详情返回信息" + respose, "TaskDetailsActivity");
                mTaskData = new Gson().fromJson(respose, TaskDetailBean.class);
                mTextview_state.setText(mTaskData.getData().getStatus_name());
                mTextview_tasktitle.setText(mTaskData.getData().getTask_name());
                mTextview_taskmoney.setText("佣金：" + mTaskData.getData().getCommission() + "元");
                mTextview_taskissuetime.setText("发布时间："+mTaskData.getData().getTask_StartDate());
                mTextview_name.setText(mTaskData.getData().getName());
                mTextview_taskdetails.setText(mTaskData.getData().getTask_description());
                mTextview_tasktime.setText(mTaskData.getData().getTask_EndDate());
                mTextview_taskaddress.setText(mTaskData.getData().getDetailed_address());
                mTextview_peoplelevel.setText(mTaskData.getData().getUser_grade());
                is_counteroffer = mTaskData.getData().getIs_counteroffer();
                if (is_counteroffer.equals("1")) {
                    mButton_counteroffer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int error) {


            }
        }).Http(Urls.Baseurl_cui + Urls.taskdetails + "?id=" + ID, this, 0);


    }

}
