package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class IssueTaskNextActivity extends BaseActivityother {

    //控件
    Button mButton_submit;
    EditText mEdit_name;
    EditText mEdit_phonenumber;
    ImageView mImage_nan;
    ImageView mImage_nv;


    int  sex=0;   //0男1女
    String lianxiren="";
    String phonenumber="";





    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task_next;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

        LogUtils.LOG("ceshi", Staticdata.map_task.toString(),"发布任务map集合中的内容");

    }

    @Override
    protected void initListener() {
        mImage_nan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mImage_nan.isSelected()){
                    mImage_nan.setSelected(true);
                    mImage_nv.setSelected(false);
                    sex=0;
                }
            }
        });
        mImage_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mImage_nv.isSelected()){
                    mImage_nan.setSelected(false);
                    mImage_nv.setSelected(true);
                    sex=0;
                }
            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(initmap()){
                    requast(Staticdata.map_task);
                }
            }
        });


    }

    @Override
    protected void initView() {
        mButton_submit=findViewById(R.id.button_submit);
        mEdit_name=findViewById(R.id.edit_lianxiren);
        mEdit_phonenumber=findViewById(R.id.edit_phonenumber);
        mImage_nan=findViewById(R.id.image_choosenan);
        mImage_nv=findViewById(R.id.image_choosenv);
        mImage_nan.setSelected(true);

    }
    boolean  initmap(){
        lianxiren=mEdit_name.getText()+"";
        if(lianxiren.equals("")){
            ToastUtils.showToast(this,"请输入联系人");
            return false;
        }
        phonenumber=mEdit_phonenumber.getText()+"";
        if(phonenumber.equals("")){
            ToastUtils.showToast(this,"请输入联系电话");
            return  false;
        }

        Staticdata.map_task.put("mobile_no",phonenumber);
        Staticdata.map_task.put("client_name",lianxiren);
        Staticdata.map_task.put("client_sex",sex+"");
        return true;
    }

    void requast( Map map){
        LogUtils.LOG("ceshi",Staticdata.map_task.toString(),"发布任务的map参数");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi","发布任务返回json","发布任务");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("status");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(IssueTaskNextActivity.this,"任务发布成功");
                    Intent intent=new Intent(IssueTaskNextActivity.this,MainActivity.class);
                    startActivity(intent);

                }else {
                    ToastUtils.showToast(IssueTaskNextActivity.this,"发布失败");
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.issuetask,this,1,map);
    }
}
