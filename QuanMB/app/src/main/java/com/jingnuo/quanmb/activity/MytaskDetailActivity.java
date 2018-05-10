package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MytaskDetailActivity extends BaseActivityother {
    //控件

    TextView mTextview_taskstate;
    TextView mTextview_tasktitle;
    TextView mTextview_taskmoney;
    TextView mTextview_tasktime;
    TextView mTextview_taskdetails;
    TextView mTextview_taskstarttime;
    TextView mTextview_taskaddress;
    //    TextView mTextview_helplevle;
    CircleImageView mImageview_head;

    Button mButton_cancle;
    Button mButton_complete;

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
        ID = intent.getIntExtra("id", 0);
        map_taskdetail = new HashMap();
        map_taskdetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_taskdetail.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_taskdetail.put("id", ID + "");
        LogUtils.LOG("ceshi", map_taskdetail.toString(), "MytaskDetailActivity");
        request(map_taskdetail);

    }

    @Override
    protected void initListener() {
        mButton_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        int status = 0;
                        String msg = "";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//登录状态
                            msg = (String) object.get("message");//登录返回信息

                            if (status == 1) {
                                ToastUtils.showToast(MytaskDetailActivity.this, "撤回任务成功");
                                finish();
                            } else {
                                ToastUtils.showToast(MytaskDetailActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui + Urls.taskdetailscancle, MytaskDetailActivity.this, 1, map_taskdetail);

            }
        });
        mButton_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.completetask + Staticdata.static_userBean.getData().getUser_token() +
                        "&order_no=" + taskDetailBean.getData().getOrder_no() +
                        "&task_id=" + taskDetailBean.getData().getTask_ID(), "确认完成");
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi", respose, "确认完成");
                        int status = 0;
                        String msg = "";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//登录状态
                            msg = (String) object.get("message");//登录返回信息

                            if (status == 1) {
                                ToastUtils.showToast(MytaskDetailActivity.this, msg);
                                finish();
                            } else {
                                ToastUtils.showToast(MytaskDetailActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).Http(Urls.Baseurl_cui + Urls.completetask + Staticdata.static_userBean.getData().getUser_token() +
                        "&order_no=" + taskDetailBean.getData().getOrder_no() +
                        "&task_id=" + taskDetailBean.getData().getTask_ID(), MytaskDetailActivity.this, 0);
            }
        });

    }

    @Override
    protected void initView() {
        mTextview_taskstate = findViewById(R.id.text_taskstate);
        mTextview_tasktitle = findViewById(R.id.text_tasktitle);
        mTextview_taskmoney = findViewById(R.id.text_taskmoney);
        mTextview_tasktime = findViewById(R.id.text_tasktime);
        mTextview_taskdetails = findViewById(R.id.text_taskdetail);
        mTextview_taskstarttime = findViewById(R.id.text_time);
        mTextview_taskaddress = findViewById(R.id.text_address);
//        mTextview_helplevle = findViewById(R.id.text_tlevel);
        mImageview_head = findViewById(R.id.image_task);
        mButton_cancle = findViewById(R.id.button_cancle);
        mButton_complete = findViewById(R.id.button_complete);
    }

    void request(Map map) {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "MytaskDetailActivity");
                taskDetailBean = new Gson().fromJson(respose, TaskDetailBean.class);
                mTextview_taskstate.setText(taskDetailBean.getData().getStatus_name());
                mTextview_tasktitle.setText(taskDetailBean.getData().getTask_name());
                mTextview_taskmoney.setText("佣金：" + taskDetailBean.getData().getCommission() + "元");
                mTextview_tasktime.setText("发布时间：" + taskDetailBean.getData().getCreateDate());
                mTextview_taskdetails.setText(taskDetailBean.getData().getTask_description());

                long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
                long ago = Long.parseLong(Utils.getTime(taskDetailBean.getData().getTask_EndDate()));//任务过期时间
                String time = Utils.getDistanceTime(ago, now);//算出的差值
                mTextview_taskstarttime.setText(time);


                mTextview_taskaddress.setText(taskDetailBean.getData().getDetailed_address());
                String imageURL = taskDetailBean.getData().getAvatar_imgUrl().substring(0, taskDetailBean.getData().getAvatar_imgUrl().length() - 1);
                Glide.with(MytaskDetailActivity.this).load(imageURL).into(mImageview_head);
//                mTextview_helplevle.setText(taskDetailBean.getData().getUser_grade());
                if (taskDetailBean.getData().getTask_Status_code().equals("02") || taskDetailBean.getData().getTask_Status_code().equals("01") || taskDetailBean.getData().getTask_Status_code().equals("08")) {
                    mButton_cancle.setVisibility(View.VISIBLE);
                }
                if (taskDetailBean.getData().getTask_Status_code().equals("05")) {
                    mButton_complete.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.mytaskdetails, MytaskDetailActivity.this, 1, map);

    }
}
