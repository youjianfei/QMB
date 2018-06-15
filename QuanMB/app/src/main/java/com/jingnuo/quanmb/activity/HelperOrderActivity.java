package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_complatetask;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.HelpOrderBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class HelperOrderActivity extends BaseActivityother {

    CircleImageView mImageview_head;
    TextView mTextview_state;//状态
    TextView mTextview_titile;//标题
    TextView mTextview_money;//佣金
    TextView mTextview_time;//发布时间
    TextView mTextview_peoplename;//雇主
    TextView mTextview_taskDetail;//任务描述
    TextView mTextview_resttime;//剩余时间
    TextView mTextview_address;//地点
    TextView mTextview_phonenumber;//客户电话

    Button mButton_queren;

    //对象
    Popwindow_complatetask popwindow_complatetask;
    HelpOrderBean helpOrderBean;

    //数据
    String order_no="";
    int type=0;  //1帮手  2  商户


    @Override
    public int setLayoutResID() {
        return R.layout.activity_helper_order;
    }

    @Override
    protected void setData() {


        popwindow_complatetask=new Popwindow_complatetask(this, new Interence_complteTask() {
            @Override
            public void onResult(boolean result) {
                if(result){
                    LogUtils.LOG("ceshi","申请完成任务接口+"+Urls.Baseurl_cui+Urls.applycompletetask+Staticdata.static_userBean.getData().getUser_token()+
                            "&order_no="+helpOrderBean.getData().getDetail().getOrder_no()+
                            "&task_id="+helpOrderBean.getData().getDetail().getTask_id(),"申请完成任务");
                    new  Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi",respose,"申请完成");
                            int status = 0;
                            String msg = "";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");//登录状态
                                msg = (String) object.get("message");//登录返回信息
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(status==1){
                                ToastUtils.showToast(HelperOrderActivity.this,msg);
                                request();
                            }else {
                                request();
                                ToastUtils.showToast(HelperOrderActivity.this,msg);
                            }
                        }

                        @Override
                        public void onError(int error) {
                            request();
                        }
                    }).Http(Urls.Baseurl_cui+Urls.applycompletetask+Staticdata.static_userBean.getData().getUser_token()+
                            "&order_no="+helpOrderBean.getData().getDetail().getOrder_no()+
                            "&task_id="+helpOrderBean.getData().getDetail().getTask_id(),HelperOrderActivity.this,0);

                }

            }
        });

    }

    @Override
    protected void initData() {
        order_no=getIntent().getStringExtra("order_no");
        type=getIntent().getIntExtra("type",0);
        request();

    }
    void  request(){
        LogUtils.LOG("ceshi","帮手订单+"+Urls.Baseurl+Urls.shoporderdetail+Staticdata.static_userBean.getData().getUser_token()+"&orderId="+order_no,"帮手订单网址");
        String URL="";
        if(type==1){
            URL=Urls.Baseurl+Urls.helporderdetail+Staticdata.static_userBean.getData().getUser_token()+"&order_no="+order_no;
        }else {
            URL=Urls.Baseurl+Urls.shoporderdetail+Staticdata.static_userBean.getData().getUser_token()+"&order_no="+order_no;
        }

        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                mButton_queren.setEnabled(true);
                LogUtils.LOG("ceshi","帮手订单+"+respose,"帮手订单网址");
                helpOrderBean=new Gson().fromJson(respose,HelpOrderBean.class);
                Glide.with(HelperOrderActivity.this).load(helpOrderBean.getData().getDetail().getHeadUrl()).into(mImageview_head);
                mTextview_titile.setText(helpOrderBean.getData().getDetail().getTask_name());
                mTextview_state.setText(helpOrderBean.getData().getDetail().getOrder_status_name());
                mTextview_money.setText("佣金："+helpOrderBean.getData().getDetail().getOrder_amount()+"元");
                mTextview_time.setText("发布时间："+helpOrderBean.getData().getDetail().getTask_StartDate());

                long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
                long ago = Long.parseLong(Utils.getTime(helpOrderBean.getData().getDetail().getTask_EndDate()));//任务过期时间
                String time = Utils.getDistanceTime(ago, now);//算出的差值
                mTextview_resttime.setText(time);

                mTextview_peoplename.setText(helpOrderBean.getData().getDetail().getNick_name());
                mTextview_taskDetail.setText(helpOrderBean.getData().getDetail().getTask_description());
                mTextview_address.setText(helpOrderBean.getData().getDetail().getTask_description());
                mTextview_phonenumber.setText(helpOrderBean.getData().getDetail().getMobile_no());
                if (helpOrderBean.getData().getDetail().getOrder_status().equals("待确认")){
                    mButton_queren.setEnabled(false);
                    mButton_queren.setText("等待雇主确认");
                }else if(helpOrderBean.getData().getDetail().getOrder_status().equals("已完成")){
                    mButton_queren.setEnabled(false);
                    mButton_queren.setText("已完成");
                }else if(helpOrderBean.getData().getDetail().getOrder_status().equals("已关闭")){
                    mButton_queren.setEnabled(false);
                    mButton_queren.setText("已关闭");
                } else if(helpOrderBean.getData().getDetail().getOrder_status().equals("已关闭")){
                    mButton_queren.setEnabled(false);
                    mButton_queren.setText("逾期未完成");
                }
                else {
                    mButton_queren.setEnabled(true);
                }

            }

            @Override
            public void onError(int error) {
                request();
            }
        }).Http(URL,HelperOrderActivity.this,0);
    }
    @Override
    protected void initListener() {
        mButton_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popwindow_complatetask.showPopwindow();
            }
        });

    }

    @Override
    protected void initView() {
        mImageview_head=findViewById(R.id.image_task);
        mTextview_state=findViewById(R.id.text_taskstate);
        mTextview_titile=findViewById(R.id.text_tasktitle);
        mTextview_money=findViewById(R.id.text_taskmoney_);
        mTextview_time=findViewById(R.id.text_tasktime);
        mTextview_peoplename=findViewById(R.id.text_name);
        mTextview_taskDetail=findViewById(R.id.text_taskdetail);
        mTextview_resttime=findViewById(R.id.text_time);
        mTextview_address=findViewById(R.id.text_address);
        mTextview_phonenumber=findViewById(R.id.text_number);
        mButton_queren=findViewById(R.id.button_bargain);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
