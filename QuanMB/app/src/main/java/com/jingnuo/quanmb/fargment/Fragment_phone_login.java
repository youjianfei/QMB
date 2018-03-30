package com.jingnuo.quanmb.fargment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.SetPasswordActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;
import static com.jingnuo.quanmb.data.Staticdata.token;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_phone_login extends Fragment {
    View rootview;
    //控件
    Button mButton_login, mButton_yanzhengma;
    EditText medit_phone, medit_yanzhegnma;

    //数据
    String phonenumber = "";
    String yangzhengma = "";

    //对象
    UserBean userBean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_phone_login, container, false);
        initview();
        initlinster();

        return rootview;
    }

    //发送验证码的请求
    private void sendZhanzhengma(Map map) {
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
                    ToastUtils.showToast(getActivity(), "验证码已发送");
                    //验证码倒计时
                    timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            mhandler.sendEmptyMessage(0);
                        }
                    };
                    timer.schedule(timerTask, 0, 1000);
                    mButton_yanzhengma.setEnabled(false);
                } else {
                    ToastUtils.showToast(getActivity(), msg);
                }


            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.sendyanzhengma, getActivity(), 1, map);
    }

    //手机号登录请求
    private void loginrequest(Map map) {
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
                    userBean=new Gson().fromJson(respose,UserBean.class);
                    token=userBean.getData().getUser_token();
                    LogUtils.LOG("ceshi", respose + "1111111111", "fragment_account");
                    isLogin = true;
                    Intent intent_login = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent_login);
                }else {
                    ToastUtils.showToast(getActivity(),msg);
                }
//                //登陆成功后 设置全局变量islogin为 true
//                isLogin = true;
//                Intent intent_login = new Intent(getActivity(), SetPasswordActivity.class);
//                getActivity().startActivity(intent_login);
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl + Urls.phoneLogin, getActivity(), 1, map);


    }


    private void initlinster() {
        mButton_yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonenumber = medit_phone.getText().toString();
                if (phonenumber.equals("")) {
                    ToastUtils.showToast(getActivity(), "请输入手机号");
                } else {
                    Map map = new HashMap();
                    map.put("phoneNumbers", phonenumber);
                    sendZhanzhengma(map);//发送验证码请求
                }
            }
        });

        mButton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonenumber = medit_phone.getText().toString();
                yangzhengma = medit_yanzhegnma.getText().toString();
                if (phonenumber.equals("") || yangzhengma.equals("")) {
                    ToastUtils.showToast(getActivity(), "请输入手机号和验证码");
                } else {
                    Map map = new HashMap();
                    map.put("ValidateCode", yangzhengma);
                    map.put("phoneNumbers", phonenumber);
                    map.put("uuid", Staticdata.UUID);
                    loginrequest(map);


                }
            }
        });

    }

    private void initview() {
        mButton_login = rootview.findViewById(R.id.button_login_phone);
        mButton_yanzhengma = rootview.findViewById(R.id.button_yanzhengma);
        medit_phone = rootview.findViewById(R.id.edit_phonenumber);
        medit_yanzhegnma = rootview.findViewById(R.id.edit_yanzhengma);

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
                    mButton_yanzhengma.setText(time + " s");
                    time--;
                    if (time == 0) {
                        timer.cancel();
                        timer = null;
                        mButton_yanzhengma.setText("获取验证码");
                        mButton_yanzhengma.setEnabled(true);
                        time = 60;
                    }
                    break;
            }
        }


    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

    }
}
