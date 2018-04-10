package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
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
import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends BaseActivityother {
    //控件
    EditText mEdit_phonenumber, mEdit_yanzhengma, mEdit_password, mEdit_passwordAgain;
    Button mButton_yanzhegnma, mButton_register;
    ImageView mImage_choose;
    TextView mTextview_xieyi, mTextview_login;

    //数据
    String phonenumber = "", yanzhengma = "", password = "", passwordagain = "";

    //对象

    Map map_register;




    @Override
    public int setLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        map_register=new HashMap();
    }

    @Override
    protected void initListener() {
        mTextview_login.setOnClickListener(this);
        mTextview_xieyi.setOnClickListener(this);
        mButton_yanzhegnma.setOnClickListener(this);
        mButton_register.setOnClickListener(this);
        mImage_choose.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mEdit_phonenumber = findViewById(R.id.edit_phonenumber);
        mEdit_yanzhengma = findViewById(R.id.edit_yanzhegnma);
        mEdit_password = findViewById(R.id.edit_password);
        mEdit_passwordAgain = findViewById(R.id.edit_passwordagain);
        mButton_yanzhegnma = findViewById(R.id.button_getyanzhangma);
        mButton_register = findViewById(R.id.button_register);
        mImage_choose = findViewById(R.id.image_choose);
        mTextview_xieyi = findViewById(R.id.textview_xieyi);
        mTextview_login = findViewById(R.id.textview_login);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_register://注册
                phonenumber = mEdit_phonenumber.getText().toString();
                yanzhengma = mEdit_yanzhengma.getText().toString();
                password = mEdit_password.getText().toString();
                passwordagain = mEdit_passwordAgain.getText().toString();
                if (phonenumber.equals("") || yanzhengma.equals("") || password.equals("") || passwordagain.equals("")) {
                    ToastUtils.showToast(this, "信息填写不完整");
                    return;
                }
                if(!password.equals(passwordagain)){
                    ToastUtils.showToast(this, "两次密码填写不一致");
                    return;
                }
                if (!mImage_choose.isSelected()) {
                    ToastUtils.showToast(this, "请阅读全民帮用户协议并同意");
                    return;
                }
                String passwordMM= PasswordJiami.passwordjiami(password);
                map_register.put("phoneNumbers",phonenumber);
                map_register.put("password",passwordMM);
                map_register.put("ValidateCode",yanzhengma);
                map_register.remove("type");


                LogUtils.LOG("ceshi",yanzhengma,"注册");
                LogUtils.LOG("ceshi","加密后"+passwordMM,getPackageName());
                LogUtils.LOG("ceshi","电话号"+map_register.get("phoneNumbers")+"密码"+map_register.get("password")+"验证码"+map_register.get("ValidateCode"),getPackageName());

                request_regist(map_register);


                break;
            case R.id.button_getyanzhangma://获取验证码
                phonenumber = mEdit_phonenumber.getText().toString();
                LogUtils.LOG("ceshi","手机号"+phonenumber,"注册界面");
                if (phonenumber.equals("")) {
                    LogUtils.LOG("ceshi","手机号"+phonenumber,getPackageName());
                    ToastUtils.showToast(this, "请输入手机号");
                } else {

                    map_register.put("phoneNumbers",phonenumber);
                    map_register.put("type","1");
                    getzhuceyanzhengma(map_register);

                }

                break;
            case R.id.textview_login://右上角登录
//                Intent intent_login=new Intent(this,LoginActivity.class);
//                startActivity(intent_login);
                finish();
                break;
            case R.id.image_choose://是否接受协议
                if (mImage_choose.isSelected()) {
                    mImage_choose.setSelected(false);
                } else {
                    mImage_choose.setSelected(true);
                }

                break;
            case R.id.textview_xieyi://协议
                ToastUtils.showToast(this, "此处跳转协议界面");
                break;

        }


    }
    //注册发送手机验证码
    private  void  getzhuceyanzhengma(Map map){
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
                if (status == 1) {
                    ToastUtils.showToast(RegisterActivity.this, "验证码已发送");
                    //验证码倒计时
                    timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            mhandler.sendEmptyMessage(0);
                        }
                    };
                    timer.schedule(timerTask, 0, 1000);
                    mButton_yanzhegnma.setEnabled(false);
                } else {
                    ToastUtils.showToast(RegisterActivity.this, msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.sendzhuceyanzhengma,this,1,map);
    }
    //注册请求
    private void request_regist(Map map){
        new  Volley_Utils(new Interface_volley_respose() {
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
                LogUtils.LOG("ceshi","注册返回"+respose,getPackageName());
                if (status == 1) {
                    ToastUtils.showToast(RegisterActivity.this, "新用户注册成功");
                    finish();
                } else {
//                    ToastUtils.showToast(RegisterActivity.this, msg);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.phoneRegister,this,1,map);

    }


    //验证码倒计时
    int time = 60;
    Timer timer;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mButton_yanzhegnma.setText( time + " s");
                    time--;
                    if (time == 0) {
                        timer.cancel();
                        timer=null;
                        mButton_yanzhegnma.setText("获取验证码");
                        mButton_yanzhegnma.setEnabled(true);
                        time = 60;
                    }
                    break;
            }
        }


    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer=null;
        }

    }
}
