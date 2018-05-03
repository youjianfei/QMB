package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MytaskDetailActivity extends BaseActivityother {
    //控件

    ImageView mImageview_task;
    TextView mTextview_taskstate;
    TextView mTextview_tasktitle;
    TextView mTextview_taskmoney;
    TextView mTextview_tasktime;
    TextView mTextview_taskdetails;
    TextView mTextview_taskstarttime;
    TextView mTextview_taskaddress;
//    TextView mTextview_helplevle;

    Button mButton_cancle;

    //数据
    int ID = 0;
    Map map_taskdetail;

    //对象
    TaskDetailBean taskDetailBean;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_mytask_detail;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        ID = intent.getIntExtra("ID", 0);
        map_taskdetail=new HashMap();
        map_taskdetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_taskdetail.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_taskdetail.put("id", ID+"");
        LogUtils.LOG("ceshi",map_taskdetail.toString(),"MytaskDetailActivity");
        request(map_taskdetail);

    }

    @Override
    protected void initListener() {
        mButton_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new  Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        int status = 0;
                        String msg = "";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//登录状态
                            msg = (String) object.get("message");//登录返回信息

                            if (status == 1) {
                                ToastUtils.showToast(MytaskDetailActivity.this,"撤回任务成功");
                                finish();
                            }else {
                                ToastUtils.showToast(MytaskDetailActivity.this,msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui+Urls.taskdetailscancle,MytaskDetailActivity.this,1,map_taskdetail);

            }
        });

    }

    @Override
    protected void initView() {
        mImageview_task = findViewById(R.id.image_task);
        mTextview_taskstate = findViewById(R.id.text_taskstate);
        mTextview_tasktitle = findViewById(R.id.text_tasktitle);
        mTextview_taskmoney = findViewById(R.id.text_taskmoney);
        mTextview_tasktime = findViewById(R.id.text_tasktime);
        mTextview_taskdetails = findViewById(R.id.text_taskdetail);
        mTextview_taskstarttime = findViewById(R.id.text_time);
        mTextview_taskaddress = findViewById(R.id.text_address);
//        mTextview_helplevle = findViewById(R.id.text_tlevel);
        mButton_cancle=findViewById(R.id.button_cancle);
    }
    void request(Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"MytaskDetailActivity");
                taskDetailBean=new Gson().fromJson(respose,TaskDetailBean.class);
                mTextview_taskstate.setText(taskDetailBean.getData().getStatus_name());
                mTextview_tasktitle.setText(taskDetailBean.getData().getTask_name());
                mTextview_taskmoney.setText(taskDetailBean.getData().getCommission()+"");
                mTextview_tasktime.setText(taskDetailBean.getData().getTask_StartDate());
                mTextview_taskdetails.setText(taskDetailBean.getData().getTask_description());
                mTextview_taskstarttime.setText(taskDetailBean.getData().getTask_EndDate());
                mTextview_taskaddress.setText(taskDetailBean.getData().getDetailed_address());
//                mTextview_helplevle.setText(taskDetailBean.getData().getUser_grade());
                if(taskDetailBean.getData().getTask_Status_code().equals("02")||taskDetailBean.getData().getTask_Status_code().equals("01")||taskDetailBean.getData().getTask_Status_code().equals("08")){
                    mButton_cancle.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.taskdetails,MytaskDetailActivity.this,1,map);

    }
}
