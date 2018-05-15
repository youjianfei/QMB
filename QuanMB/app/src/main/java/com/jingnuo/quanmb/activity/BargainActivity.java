package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.MarkerOptions;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interence_bargin;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_bargin;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.BargainMessagedetailsBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class BargainActivity extends BaseActivityother {
    TextView mTextview_message;
    TextView mTextview_time;
    TextView mTextview_1;
    TextView mTextview_name;
    TextView mTextview_taskstatename;
    TextView mTextview_tasktitle;
    TextView mTextview_money;
    TextView mTextview_yourmoney;
    TextView mTextview_mymoney;
    CircleImageView  mImageview;
    Button mButton_accept;
    Button mButton_goon;
    Button mButton_refuse;




    //数据
    String binding_id="";
    String receive_client_no="";
    String send_client_no="";

    Map map_bargainmessagedetail;
    BargainMessagedetailsBean bargainMessagedetailsBean;

    Popwindow_bargin popwindow_bargin;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_bargain;
    }

    @Override
    protected void setData() {
        popwindow_bargin=new Popwindow_bargin(BargainActivity.this, new Interence_bargin() {
            @Override
            public void onResult(String result) {
                String  URL_bargain="";
                Map map_goonbargain=new HashMap();
                if(bargainMessagedetailsBean.getData().getMark().equals("2")){
                    URL_bargain=Urls.Baseurl_cui+Urls.kehubargain;
                    map_goonbargain.put("binding_id",bargainMessagedetailsBean.getData().getBinding_id());
                    map_goonbargain.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    map_goonbargain.put("counteroffer_Amount",result);
                    map_goonbargain.put("send_client_no",bargainMessagedetailsBean.getData().getSend_client_no());
                }else {
                    URL_bargain=Urls.Baseurl_cui+Urls.helpterbargain;
                    map_goonbargain.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    map_goonbargain.put("task_id",bargainMessagedetailsBean.getData().getTask_id()+"");
                    map_goonbargain.put("counteroffer_Amount",result+"");
                }
                LogUtils.LOG("ceshi","map+"+map_goonbargain,"继续还价");
                LogUtils.LOG("ceshi",URL_bargain,"继续还价");
                new  Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi",respose,"继续还价");
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(URL_bargain,BargainActivity.this,1,map_goonbargain);

            }
        });
    }

    @Override
    protected void initData() {
        map_bargainmessagedetail=new HashMap();
        Intent intent=getIntent();
        binding_id=intent.getStringExtra("binding_id");
        receive_client_no=intent.getStringExtra("receive_client_no");
        send_client_no=intent.getStringExtra("send_client_no");
        map_bargainmessagedetail.put("binding_id",binding_id+"");
        map_bargainmessagedetail.put("receive_client_no",receive_client_no);
        map_bargainmessagedetail.put("send_client_no",send_client_no);
        map_bargainmessagedetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        LogUtils.LOG("ceshi","map+"+map_bargainmessagedetail,"还价消息详情");
        requestBargainmessage(map_bargainmessagedetail);

    }

    @Override
    protected void initListener() {
        mButton_accept.setOnClickListener(this);
        mButton_goon.setOnClickListener(this);
        mButton_refuse.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mTextview_message=findViewById(R.id.textmessagetitle);
        mTextview_time=findViewById(R.id.text_time);
        mTextview_1=findViewById(R.id.textview_shenfen);
        mTextview_name=findViewById(R.id.text_name);
        mTextview_taskstatename=findViewById(R.id.text_tasktype);
        mTextview_tasktitle=findViewById(R.id.text_taskname);
        mTextview_money=findViewById(R.id.taskprice);
        mTextview_yourmoney=findViewById(R.id.text_yourprice);
        mTextview_mymoney=findViewById(R.id.text_needprice);
        mImageview=findViewById(R.id.image_type);
        mButton_accept=findViewById(R.id.button_accect);
        mButton_goon=findViewById(R.id.button_goon);
        mButton_refuse=findViewById(R.id.button_refuse);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_accect://接受还价
                Map map_accept=new HashMap();
                map_accept.put("id",bargainMessagedetailsBean.getData().getTask_id()+"");
                map_accept.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                map_accept.put("is_accept","1");
                map_accept.put("counteroffer_Amount",bargainMessagedetailsBean.getData().getCounteroffer_Amount()+"");
                if(bargainMessagedetailsBean.getData().getHelper_no()!=null){
                    map_accept.put("helper_no",bargainMessagedetailsBean.getData().getHelper_no());
                }else {
                    map_accept.put("business_no",bargainMessagedetailsBean.getData().getBusiness_no());
                }
                LogUtils.LOG("ceshi",map_accept.toString(),"接受还价map");
                new  Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi",respose,"接受还价");
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
                            ToastUtils.showToast(BargainActivity.this,msg);
                        }else {
                            ToastUtils.showToast(BargainActivity.this,msg);
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui+Urls.acceptORrefuse,BargainActivity.this,1,map_accept);


                break;
            case R.id.button_goon://继续还价
                popwindow_bargin.showpop();


                break;
            case R.id.button_refuse://拒觉还价
                Map map_refuse=new HashMap();
                map_refuse.put("id",bargainMessagedetailsBean.getData().getTask_id()+"");
                map_refuse.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                map_refuse.put("is_accept","0");
                map_refuse.put("send_client_no",bargainMessagedetailsBean.getData().getReceive_client_no());
                map_refuse.put("counteroffer_Amount",bargainMessagedetailsBean.getData().getCounteroffer_Amount()+"");
                if(bargainMessagedetailsBean.getData().getHelper_no()!=null){
                    map_refuse.put("helper_no",bargainMessagedetailsBean.getData().getHelper_no());
                }else {
                    map_refuse.put("business_no",bargainMessagedetailsBean.getData().getBusiness_no());
                }
                LogUtils.LOG("ceshi",map_refuse.toString(),"拒接还价map");
                new  Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi",respose,"拒绝还价");
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
                            ToastUtils.showToast(BargainActivity.this,msg);
                        }else {
                            ToastUtils.showToast(BargainActivity.this,msg);
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui+Urls.acceptORrefuse,BargainActivity.this,1,map_refuse);

                break;
        }
    }

    void  requestBargainmessage(Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"还价消息详情");
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
                    bargainMessagedetailsBean=new Gson().fromJson(respose,BargainMessagedetailsBean.class);
                    if(bargainMessagedetailsBean.getData().getMark().equals("1")){
                        mTextview_message.setText("被雇主还价了！");
                        mTextview_1.setText("还价雇主");
                        mTextview_yourmoney.setVisibility(View.GONE);

                    }else {
                        mTextview_message.setText("你发布的任务被帮手还价了！");
                        mTextview_1.setText("还价帮手");
                        LogUtils.LOG("ceshi",bargainMessagedetailsBean.getData().getResponse_Amount()+"","sdafsadf");
                        if(bargainMessagedetailsBean.getData().getResponse_Amount()!=0.0){
                            mTextview_yourmoney.setVisibility(View.VISIBLE);
                            mTextview_yourmoney.setText("你的还价："+bargainMessagedetailsBean.getData().getResponse_Amount()+"元");
                        }

                    }
                    mTextview_time.setText(bargainMessagedetailsBean.getData().getCreateDate());
                    mTextview_name.setText(bargainMessagedetailsBean.getData().getName());
                    mTextview_taskstatename.setText(bargainMessagedetailsBean.getData().getSpecialty_name());
                    mTextview_tasktitle.setText(bargainMessagedetailsBean.getData().getTask_name());
                    if(bargainMessagedetailsBean.getData().getIs_helper_bid().equals("N")){
                        mTextview_money.setText(bargainMessagedetailsBean.getData().getCommission()+"");
                    }else {
                        mTextview_money.setText("帮手出价");
                    }

                    mTextview_mymoney.setText("还价价格："+bargainMessagedetailsBean.getData().getCounteroffer_Amount()+"元");
                    String URL_imagehead=bargainMessagedetailsBean.getData().getAvatar_imgUrl().substring(0,bargainMessagedetailsBean.getData().getAvatar_imgUrl().length()-1);
                    Glide.with(BargainActivity.this).load(bargainMessagedetailsBean.getData().getAvatar_imgUrl()).load(URL_imagehead).into(mImageview);

                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.bargainmessage,BargainActivity.this,1,map);
    }
}
