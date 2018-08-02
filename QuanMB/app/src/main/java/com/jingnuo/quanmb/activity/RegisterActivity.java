package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.Interface.SendYanZhengmaSuccess;
import com.jingnuo.quanmb.class_.RegularYanzheng;
import com.jingnuo.quanmb.class_.SendYanZhengMa;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.jingnuo.quanmb.data.Staticdata.UUID;
import static com.jingnuo.quanmb.data.Staticdata.Userphonenumber;
import static com.jingnuo.quanmb.data.Staticdata.isLogin;

public class RegisterActivity extends BaseActivityother {
    //控件
    EditText mEdit_phonenumber, mEdit_yanzhengma, mEdit_password, mEdit_passwordAgain;
    Button mButton_yanzhegnma, mButton_register;
    ImageView mImage_choose;
    ImageView mImage_hide;
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
        mImage_hide.setOnClickListener(this);
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
        mImage_hide=findViewById(R.id.image_hide);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.image_hide:
                if (mImage_hide.isSelected()) {
                    mImage_hide.setSelected(false);
                    //选择状态 --设置为不可见的密码
                    mEdit_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    mImage_hide.setSelected(true);
                    //未选择状态 显示明文--设置为可见的密码
                    mEdit_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
            case R.id.button_register://注册
                phonenumber = mEdit_phonenumber.getText().toString();
                yanzhengma = mEdit_yanzhengma.getText().toString();
                password = mEdit_password.getText().toString();
                passwordagain = mEdit_passwordAgain.getText().toString();
                if (phonenumber.equals("") ) {
                    ToastUtils.showToast(this, "信息填写不完整");
                    return;
                }
                if (! RegularYanzheng.ispassword(password)||password.length()<6||password.length()>18){
                    ToastUtils.showToast(this, "密码必须为6~18位且包含字母");
                    return;
                }
                if (phonenumber.equals("") || yanzhengma.equals("") || password.equals("") || passwordagain.equals("")) {
                    ToastUtils.showToast(this, "信息填写不完整");
                    return;
                }

                LogUtils.LOG("ceshi",RegularYanzheng.ispassword(password)+"","a");
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

                map_login = new HashMap();
                Userphonenumber=phonenumber;//将电话号设为全局变量
                map_login.put("username", phonenumber);
                map_login.put("password", passwordMM);
                map_login.put("Jpush_id", Staticdata.JpushID);
                map_login.put("uuid", UUID);

                request_regist(map_register);


                break;
            case R.id.button_getyanzhangma://获取验证码
                phonenumber = mEdit_phonenumber.getText().toString();
                LogUtils.LOG("ceshi","手机号"+phonenumber+"url:","注册界面");
                if (phonenumber.equals("")) {
                    LogUtils.LOG("ceshi","手机号"+phonenumber,getPackageName());
                    ToastUtils.showToast(this, "请输入手机号");
                } else {

                    map_register.put("phoneNumbers",phonenumber);
                    map_register.put("type","1");

                    new SendYanZhengMa(new SendYanZhengmaSuccess() {
                        @Override
                        public void onSuccesses(String yanzhengma) {
                            ToastUtils.showToast(RegisterActivity.this,yanzhengma);
                        }
                    },mButton_yanzhegnma).sendyanzhengma(RegisterActivity.this,map_register);

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

                    //注册成功之后直接登陆 设置全局变量islogin为 true
                    requestlogin(map_login);//登陆请求

                } else {
                    ToastUtils.showToast(RegisterActivity.this, msg);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.phoneRegister,this,1,map);

    }
    UserBean userBean;
    Map map_login;
    private void requestlogin(Map map) {
//        Request_retrofit.retrofit_post(map);

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
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
                    SharedPreferencesUtils.putString(RegisterActivity.this,"QMB","password",password);//登录成功之后存未加密de密码
                    userBean=new Gson().fromJson(respose,UserBean.class);
                    Staticdata. static_userBean=userBean;
                    LogUtils.LOG("ceshi", respose + "1111111111", "RegisterActivity");
                    isLogin = true;
                    Userphonenumber=userBean.getData().getAppuser().getMobile_no();//将电话号设为全局变量
                    Intent intent_login = new Intent(RegisterActivity.this, MainActivity.class);
                    RegisterActivity.this.startActivity(intent_login);
                    finish();
                }else {
                    ToastUtils.showToast(RegisterActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.login, RegisterActivity.this, 1, map);
    }

}
