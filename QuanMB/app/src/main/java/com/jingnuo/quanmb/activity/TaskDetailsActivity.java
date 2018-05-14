package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interence_bargin;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_bargin;
import com.jingnuo.quanmb.class_.Popwindow_lookpic;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.QueRenHelp_Bean;
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

public class TaskDetailsActivity extends BaseActivityother {
    TextView mTextview_state;
    TextView mTextview_tasktitle;
    TextView mTextview_taskmoney;
    TextView mTextview_taskissuetime;
    TextView mTextview_name;
    TextView mTextview_taskdetails;
    TextView mTextview_tasktime;
    TextView mTextview_taskaddress;
    //    TextView mTextview_peoplelevel;
    CircleImageView imageView_head;
    ImageView mImageview_skill1,mImageview_skill2,mImageview_skill3;

    Button mButton_help;
    Button mButton_counteroffer;

    //数据
    int ID = 0;//任务id;
    String is_counteroffer = "";


    String image_url="";
    String image_url1="";
    String image_url2="";
    String image_url3="";

    //对象
    TaskDetailBean mTaskData;
    Popwindow_lookpic popwindow_lookpic;
    Popwindow_bargin popwindow_bargin;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_task_details;
    }

    @Override
    protected void setData() {
        popwindow_lookpic=new Popwindow_lookpic(this);
        requestTaseDetail();
    }

    @Override
    protected void initData() {
        Intent intend_id = getIntent();
        ID = intend_id.getIntExtra("id", 0);
        popwindow_bargin = new Popwindow_bargin(this, new Interence_bargin() {
            @Override
            public void onResult(String result) {//还价网络请求
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
                            status = (Integer) object.get("status");//登录状态
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
        mImageview_skill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindow_lookpic.showPopwindow(image_url1);
            }
        });
        mImageview_skill2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindow_lookpic.showPopwindow(image_url2);
            }
        });
        mImageview_skill3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindow_lookpic.showPopwindow(image_url3);
            }
        });
        //确认帮助请求
        mButton_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Staticdata.isLogin) {//是否登录
                    if (Staticdata.static_userBean.getData().getAppuser().getRole().equals("0")) {
                        ToastUtils.showToast(TaskDetailsActivity.this, "请先认证帮手");
                        Intent intent_renzheng = new Intent(TaskDetailsActivity.this, AuthenticationActivity.class);
                        startActivity(intent_renzheng);
                    }

                    LogUtils.LOG("ceshi", "确认帮助网址+" + Urls.Baseurl_cui + Urls.helptask + "?tid=" + ID + "&user_token=" + Staticdata.static_userBean.getData().getUser_token(), "TaskDetailsActivity");
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi", "确认帮助+" + respose, "TaskDetailsActivity");
                            QueRenHelp_Bean queRenHelp_bean = new Gson().fromJson(respose, QueRenHelp_Bean.class);
                            if (queRenHelp_bean.getStatus() == 1) {
                                Intent intent_querenhelp = new Intent(TaskDetailsActivity.this, HelperOrderActivity.class);
                                intent_querenhelp.putExtra("order_no", queRenHelp_bean.getDate().getOrder_no());
                                startActivity(intent_querenhelp);
                                finish();
                            } else {
//                                ToastUtils.showToast(TaskDetailsActivity.this, Staticdata.queRenHelp_bean.getMessage());
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
                if (Staticdata.isLogin) {
                    if (Staticdata.static_userBean.getData().getAppuser().getRole().equals("0")) {
                        ToastUtils.showToast(TaskDetailsActivity.this, "请先认证帮手");
                        Intent intent_renzheng = new Intent(TaskDetailsActivity.this, AuthenticationActivity.class);
                        startActivity(intent_renzheng);
                    } else {
                        popwindow_bargin.showpop();
                    }
                } else {
                    Intent intent_login = new Intent(TaskDetailsActivity.this, LoginActivity.class);
                    startActivity(intent_login);
                    finish();
                }

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
//        mTextview_peoplelevel = findViewById(R.id.text_tlevel);
        mButton_help = findViewById(R.id.button_help);
        mButton_counteroffer = findViewById(R.id.button_bargain);
        imageView_head = findViewById(R.id.image_task);
        mImageview_skill1=findViewById(R.id.iamge_skillPIC1);
        mImageview_skill2=findViewById(R.id.iamge_skillPIC2);
        mImageview_skill3=findViewById(R.id.iamge_skillPIC3);
    }

    void requestTaseDetail() {
        LogUtils.LOG("ceshi", "任务详情接口+" + Urls.Baseurl_cui + Urls.taskdetails + "?id=" + ID, "TaskDetailsActivity");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", "任务详情返回信息" + respose, "TaskDetailsActivity");
                mTaskData = new Gson().fromJson(respose, TaskDetailBean.class);
                mTextview_state.setText(mTaskData.getData().getSpecialty_name());
                mTextview_tasktitle.setText(mTaskData.getData().getTask_name());
                mTextview_taskmoney.setText("佣金：" + mTaskData.getData().getCommission() + "元");
                mTextview_taskissuetime.setText("发布时间：" + mTaskData.getData().getCreateDate());
                mTextview_name.setText(mTaskData.getData().getNick_name());
                mTextview_taskdetails.setText(mTaskData.getData().getTask_description());
                long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
                long ago = Long.parseLong(Utils.getTime(mTaskData.getData().getTask_EndDate()));//任务过期时间
                String time = Utils.getDistanceTime(ago, now);//算出的差值
                mTextview_tasktime.setText(time);

                mTextview_taskaddress.setText(mTaskData.getData().getDetailed_address());
//                mTextview_peoplelevel.setText(mTaskData.getData().getUser_grade());
                is_counteroffer = mTaskData.getData().getIs_counteroffer();
                String imageURL = mTaskData.getData().getAvatar_imgUrl().substring(0, mTaskData.getData().getAvatar_imgUrl().length() - 1);
                Glide.with(TaskDetailsActivity.this).load(imageURL).into(imageView_head);
                image_url=mTaskData.getData().getTask_ImgUrl();
                setImage(image_url);

                if (is_counteroffer.equals("1")) {
                    mButton_counteroffer.setVisibility(View.VISIBLE);
                }
                if(mTaskData.getData().getCommission()==0){
                    mButton_counteroffer.setVisibility(View.VISIBLE);
                    mButton_help.setVisibility(View.GONE);
                    mTextview_taskmoney.setText("佣金：帮手出价" );
                }
            }

            @Override
            public void onError(int error) {


            }
        }).Http(Urls.Baseurl_cui + Urls.taskdetails + "?id=" + ID, this, 0);


    }
    void setImage(String  image){
        if(image==null||image.equals("")){

        }else {
            String []images=image.split(",");
            int len=images.length;
            LogUtils.LOG("ceshi","图片的个数"+images.length,"SkillDetailActivity分隔图片");
            switch (len){
                case 1:
                    LogUtils.LOG("ceshi","图片的地址"+images[0],"SkillDetailActivity分隔图片");
                    image_url1=images[0];
                    mImageview_skill1.setVisibility(View.VISIBLE);
                    Glide.with(TaskDetailsActivity.this).load(image_url1).into(mImageview_skill1);
                    break;
                case 2:
                    image_url1=images[0];
                    image_url2=images[1];

                    mImageview_skill1.setVisibility(View.VISIBLE);
                    mImageview_skill2.setVisibility(View.VISIBLE);
                    Glide.with(TaskDetailsActivity.this).load(images[0]).into(mImageview_skill1);
                    Glide.with(TaskDetailsActivity.this).load(images[1]).into(mImageview_skill2);
                    break;
                case 3:
                    image_url1=images[0];
                    image_url2=images[1];
                    image_url3=images[2];
                    mImageview_skill1.setVisibility(View.VISIBLE);
                    mImageview_skill2.setVisibility(View.VISIBLE);
                    mImageview_skill3.setVisibility(View.VISIBLE);
                    Glide.with(TaskDetailsActivity.this).load(images[0]).into(mImageview_skill1);
                    Glide.with(TaskDetailsActivity.this).load(images[1]).into(mImageview_skill2);
                    Glide.with(TaskDetailsActivity.this).load(images[2]).into(mImageview_skill3);
                    break;
            }


        }

    }

}
