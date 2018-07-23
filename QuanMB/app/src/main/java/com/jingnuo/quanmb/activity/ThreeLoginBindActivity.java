package com.jingnuo.quanmb.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;

public class ThreeLoginBindActivity extends BaseActivityother {
    //控件
    EditText mEdit_account;
    EditText mEdit_password;
    TextView mTextview_forgetpassword;
    Button mButton_complete;

    //数据
    Map map_bind;
    UserBean userBean;
    String phonenumber="";
    String password="";
    String publicEncryptedResult = "";//加密后密码
    @Override
    public int setLayoutResID() {
        return R.layout.activity_three_login_bind;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        map_bind=new HashMap();
    }

    @Override
    protected void initListener() {
        mTextview_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_findpassword=new Intent(ThreeLoginBindActivity.this,FindPasswordActivity.class);
                startActivity(intent_findpassword);
            }
        });

        mButton_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(initmap()){
                    LogUtils.LOG("ceshi",map_bind.toString(),"绑定map");
                    request_bind(map_bind);
                }
            }
        });

    }

    @Override
    protected void initView() {
        mEdit_account=findViewById(R.id.edit_account);
        mEdit_password=findViewById(R.id.edit_password);
        mTextview_forgetpassword=findViewById(R.id.textview_forgotpassword);
        mButton_complete=findViewById(R.id.button_register);
    }

    void request_bind(Map  map){

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"登录绑定");
                int  status=0;
                String msg="";
                try {
                    JSONObject object=new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){//登录成功
                    SharedPreferencesUtils.putString(ThreeLoginBindActivity.this,"QMB","password",password);//登录成功之后存未加密de密码
                    userBean=new Gson().fromJson(respose,UserBean.class);
                    Staticdata. static_userBean=userBean;
                    LogUtils.LOG("ceshi", respose + "1111111111", "微信绑定已有账号");
                    isLogin = true;
                    Intent intent_login = new Intent(ThreeLoginBindActivity.this, MainActivity.class);
                    ThreeLoginBindActivity.this.startActivity(intent_login);
                    finish();
                }else {
                    ToastUtils.showToast(ThreeLoginBindActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.existsBind,ThreeLoginBindActivity.this,1,map);



    }
    boolean initmap(){
        phonenumber=mEdit_account.getText()+"";
        if(phonenumber.equals("")){
            ToastUtils.showToast(ThreeLoginBindActivity.this,"请输入手机号");
            return false;
        }
        map_bind.put("username",phonenumber);
        password=mEdit_password.getText()+"";
        if(password.equals("")){
            ToastUtils.showToast(ThreeLoginBindActivity.this,"请输入密码");
            return false;
        }
        publicEncryptedResult= PasswordJiami.passwordjiami(password);//对密码加密
        map_bind.put("password",publicEncryptedResult);
        map_bind.put("nick_name", Staticdata.map_wechat.get("nick_name"));
//        map_bind.put("sex",Staticdata.map_wechat.get("sex"));
        map_bind.put("unionid",Staticdata.map_wechat.get("unionid"));
        map_bind.put("uuid",Staticdata.UUID);
        map_bind.put("Jpush_id", Staticdata.JpushID);

        return  true;

    }
}
