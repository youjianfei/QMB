package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Cancleorder;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.CancelOrderBean;
import com.jingnuo.quanmb.popwinow.Popwindow_cancleorder_success;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CancelloederActivity extends BaseActivityother {
    //数据
    String   ID="";
    String res="";

    boolean isCancel=false;

    String result="{\"data\":[{\"isselect\":true,\"task_des\":\"师傅未按约定时间到岗\"}," +
            "{\"isselect\":false,\"task_des\":\"下错单，重新下单\"}," +
            "{\"isselect\":false,\"task_des\":\"等待时间太长，和师傅约定不了合适的时间\"}," +
            "{\"isselect\":false,\"task_des\":\"师傅报价太贵\"}," +
            "{\"isselect\":false,\"task_des\":\"我不想下单了\"}," +
            "{\"isselect\":false,\"task_des\":\"其他原因\"}]}";
    List<CancelOrderBean.DataBean> mData;

    //控件
    MyListView mylistview_result;
    EditText edit_think;
    Button button_submit;


    //对象
    Adapter_Cancleorder adapter_cancleorder;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_cancelloeder;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        ID=getIntent().getStringExtra("taskid");
        mData=new ArrayList<>();
        mData.addAll(new Gson().fromJson(result,CancelOrderBean.class).getData());
        adapter_cancleorder=new Adapter_Cancleorder(mData,this);
        mylistview_result.setAdapter(adapter_cancleorder);
    }

    @Override
    protected void initListener() {
        mylistview_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i=0;i<mData.size();i++){
                    mData.get(i).setIsselect(false);
                }
                mData.get(position).setIsselect(true);
                adapter_cancleorder.notifyDataSetChanged();
                if(position==5){
                    edit_think.setVisibility(View.VISIBLE);
                }else {
                    edit_think.setVisibility(View.GONE);
                }
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交取消订单原因
                for (int i=0;i<mData.size();i++){
                    if(mData.get(i).isIsselect()==true){
                        res=mData.get(i).getTask_des();
                    }
                }
                res=res+edit_think.getText()+"";
                Map  map_taskdetail = new HashMap();
                map_taskdetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                map_taskdetail.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                map_taskdetail.put("id", ID );
                map_taskdetail.put("cause",res);
                LogUtils.LOG("ceshi",map_taskdetail.toString(),"cancleorder");
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
                                isCancel=true;
                                mylistview_result.setEnabled(false);
                                button_submit.setEnabled(false);
                                new Popwindow_cancleorder_success(CancelloederActivity.this, new Interence_complteTask() {
                                    @Override
                                    public void onResult(boolean result) {
                                        if(result){
                                            Intent intent=new Intent(CancelloederActivity.this,IssueTaskActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else {
                                            Intent intent=new Intent(CancelloederActivity.this,MyOrderActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }).showPopwindow();

                            } else {
                                ToastUtils.showToast(CancelloederActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui + Urls.taskdetailscancle, CancelloederActivity.this, 1, map_taskdetail);


            }
        });
    }

    @Override
    protected void initView() {
        mylistview_result=findViewById(R.id.mylistview_result);
        edit_think=findViewById(R.id.edit_think);
        button_submit=findViewById(R.id.button_submit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                if(!isCancel){
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(!isCancel){
            finish();
        }
    }
}
