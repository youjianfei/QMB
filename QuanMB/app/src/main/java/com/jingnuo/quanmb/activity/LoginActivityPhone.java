package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.Interface.SendYanZhengmaSuccess;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.class_.SendYanZhengMa;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.popwinow.ProgressDlog;
import com.jingnuo.quanmb.utils.InstalltionId;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;

public class LoginActivityPhone extends BaseActivityother {

    //控件
    Button button_yanzhengma;
    Button button_submit;
    EditText edit_account;
    EditText edit_yanzhengma;
    EditText edit_tuijianma;
    TextView textview_passwordLogin;
    TextView textview_yonghuxieyi;


    //数据
    String phonenumber = "";//电话号
    String yangzhengma = "";//验证码
    String tuijianma = "";//推荐码   选填
    boolean isregisted = false;


    //对象
    UserBean userBean;
    ProgressDlog progressDlog;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_login_phone;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        Staticdata.UUID = InstalltionId.id(this);//第一次运行生成一个id
        Staticdata.JpushID = JPushInterface.getRegistrationID(getApplicationContext());
        progressDlog = new ProgressDlog(this);
        phonenumber = SharedPreferencesUtils.getString(LoginActivityPhone.this, "QMB", "mobile");
        edit_account.setText(phonenumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                LogUtils.LOG("ceshi1","点击返回","~~~~login");
                Intent intent_main=new Intent(this,IssueTaskActivity.class);
                startActivity(intent_main);
                finish();
                break;
        }
    }

    @Override
    protected void initListener() {
        textview_passwordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityPhone.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonenumber = edit_account.getText().toString();
                if (phonenumber.equals("")) {
                    ToastUtils.showToast(LoginActivityPhone.this, "请输入手机号");
                    return;
                }
                yangzhengma = edit_yanzhengma.getText().toString().trim();
                if (yangzhengma.equals("")) {
                    ToastUtils.showToast(LoginActivityPhone.this, "请输入验证码");
                } else {
                    if (isregisted) {
                        LogUtils.LOG("ceshi", "走先注册后登陆流程", getPackageName());

                        //走先注册后登陆流程
                        tuijianma = edit_tuijianma.getText().toString();
                        Map map_register = new HashMap();
                        map_register.put("phoneNumbers", phonenumber);
                        map_register.put("ValidateCode", yangzhengma);
                        if (!tuijianma.equals("")) {
                            map_register.put("recommend_code", tuijianma);
                        }
                        request_regist(map_register);

                    } else {
                        //直接登陆流程
                        LogUtils.LOG("ceshi", "直接登陆流程", getPackageName());
                        tuijianma = edit_tuijianma.getText().toString();
//                        Userphonenumber = phonenumber;//将电话号设为全局变量
                        Map map = new HashMap();
                        map.put("ValidateCode", yangzhengma);
                        map.put("phoneNumbers", phonenumber);
                        map.put("uuid", Staticdata.UUID);
                        map.put("Jpush_id", Staticdata.JpushID);
                        map.put("recommend_code", tuijianma);

                        loginrequest(map);

                    }
                }
            }
        });
        button_yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonenumber = edit_account.getText().toString();
                if (phonenumber.equals("")) {
                    ToastUtils.showToast(LoginActivityPhone.this, "请输入手机号");
                } else {
                    SharedPreferencesUtils.putString(LoginActivityPhone.this, "QMB", "mobile", phonenumber);
                    Map map = new HashMap();
                    map.put("phoneNumbers", phonenumber);
                    sendZhanzhengma(map);//发送验证码请求
                }
            }
        });
    }

    @Override
    protected void initView() {


        button_yanzhengma = findViewById(R.id.button_yanzhengma);
        button_submit = findViewById(R.id.button_submit);
        edit_account = findViewById(R.id.edit_account);
        edit_yanzhengma = findViewById(R.id.edit_yanzhengma);
        edit_tuijianma = findViewById(R.id.edit_tuijianma);
        textview_passwordLogin = findViewById(R.id.textview_passwordLogin);
        textview_yonghuxieyi = findViewById(R.id.textview_yonghuxieyi);


        SpannableStringBuilder style = new SpannableStringBuilder();
        style.append("温馨提示：未注册全民帮账号的手机号，登录时将自动注册，且代表您已同意《用户服务协议》");
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(LoginActivityPhone.this, XieyZhuceiActivity.class);
                startActivity(intent);
            }
        };
        style.setSpan(clickableSpan, 34, 42, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textview_yonghuxieyi.setText(style);
        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(LoginActivityPhone.this.getResources().getColor(R.color.yellow_jianbian_start));
        style.setSpan(foregroundColorSpan, 34, 42, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        textview_yonghuxieyi.setMovementMethod(LinkMovementMethod.getInstance());
        textview_yonghuxieyi.setText(style);


    }

    //发送验证码的请求
    private void sendZhanzhengma(Map map) {
        new SendYanZhengMa(new SendYanZhengmaSuccess() {
            @Override
            public void onSuccesses(String yanzhengma) {
                if (yanzhengma.endsWith("00")) {
                    isregisted = true;
                    ToastUtils.showToast(LoginActivityPhone.this, yanzhengma.substring(0, yanzhengma.length() - 2));
                } else {
                    isregisted = false;
                    ToastUtils.showToast(LoginActivityPhone.this, yanzhengma);
                }

            }
        }, button_yanzhengma).sendyanzhengma(LoginActivityPhone.this, map);

    }

    //注册请求
    private void request_regist(Map map) {
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
                LogUtils.LOG("ceshi", "注册返回" + respose, getPackageName());
                if (status == 1) {
                    //注册成功之后直接登陆 设置全局变量islogin为 true
                    //直接登陆流程
//                    Userphonenumber = phonenumber;//将电话号设为全局变量
                    Map map = new HashMap();
                    map.put("ValidateCode", yangzhengma);
                    map.put("phoneNumbers", phonenumber);
                    map.put("uuid", Staticdata.UUID);
                    map.put("Jpush_id", Staticdata.JpushID);
                    loginrequest(map);
                } else {
                    ToastUtils.showToast(LoginActivityPhone.this, msg);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl + Urls.phoneRegister, this, 1, map);

    }

    //手机号登录请求
    private void loginrequest(Map map) {
        progressDlog.showPD("正在登录中");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                progressDlog.cancelPD();
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {//登录成功
                    userBean = new Gson().fromJson(respose, UserBean.class);
                    Staticdata.static_userBean = null;
                    Staticdata.static_userBean = userBean;
                    LogUtils.LOG("ceshi", respose + "1111111111", "fragment_account");
                    isLogin = true;
                    SharedPreferencesUtils.putString(LoginActivityPhone.this, "QMB", "phonenumber", Staticdata.static_userBean.getData().getAppuser().getUser_name());
                    SharedPreferencesUtils.putString(LoginActivityPhone.this,"QMB","usertoken",Staticdata.static_userBean.getData().getUser_token());//登录成功之后存token
                    Utils.connect(userBean.getData().getAppuser().getRongCloud_token());
                    Intent intent_login = new Intent(LoginActivityPhone.this, IssueTaskActivity.class);
                    LoginActivityPhone.this.startActivity(intent_login);
                    LoginActivityPhone.this.finish();
                } else {
                    ToastUtils.showToast(LoginActivityPhone.this, msg);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl + Urls.phoneLogin, LoginActivityPhone.this, 1, map);


    }
    @Override
    public void onBackPressed() {
        LogUtils.LOG("ceshi1","点击返回","~~~~login");
        Intent intent_main=new Intent(this,IssueTaskActivity.class);
        startActivity(intent_main);
        finish();
    }
}
