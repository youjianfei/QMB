package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangepasswordActivity extends BaseActivityother {

    //控件
    EditText mEdit_oldpassword;
    EditText mEdit_newpassword;
    Button mButton;
    //数据
    String oldpassword="";
    String newpassword="";
    Map map_changpassword;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_changepassword;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        map_changpassword=new HashMap();
    }

    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpassword=mEdit_oldpassword.getText()+"";
                newpassword=mEdit_newpassword.getText()+"";
                if(oldpassword.equals("")||newpassword.equals("")){
                    ToastUtils.showToast(ChangepasswordActivity.this,"信息填写不完整");
                    return;
                }
                String OldpasswordMM= PasswordJiami.passwordjiami(oldpassword);
                String NewpasswordMM= PasswordJiami.passwordjiami(newpassword);
                map_changpassword.put("oldPassword",OldpasswordMM);
                map_changpassword.put("newPassword",NewpasswordMM);
                map_changpassword.put("confirm",NewpasswordMM);
                map_changpassword.put("user_token", Staticdata.static_userBean.getData().getUser_token());

                request(map_changpassword);
            }
        });

    }

    @Override
    protected void initView() {
        mButton=findViewById(R.id.button_password);
        mEdit_oldpassword=findViewById(R.id.edit_oldpassword);
        mEdit_newpassword=findViewById(R.id.edit_newpassword);

    }

    void request(Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
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
                    ToastUtils.showToast(ChangepasswordActivity.this,msg);
                    finish();
                }else {
                    ToastUtils.showToast(ChangepasswordActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.changepassword,ChangepasswordActivity.this,1,map);
    }
}
