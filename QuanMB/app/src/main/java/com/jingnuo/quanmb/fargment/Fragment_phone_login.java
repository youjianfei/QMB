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
import com.jingnuo.quanmb.Interface.SendYanZhengmaSuccess;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.SetPasswordActivity;
import com.jingnuo.quanmb.class_.ProgressDlog;
import com.jingnuo.quanmb.class_.SendYanZhengMa;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.jingnuo.quanmb.data.Staticdata.Userphonenumber;
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
    ProgressDlog progressDlog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_phone_login, container, false);
        initview();
        initdata();
        initlinster();

        return rootview;
    }

    //发送验证码的请求
    private void sendZhanzhengma(Map map) {
       new SendYanZhengMa(new SendYanZhengmaSuccess() {
           @Override
           public void onSuccesses(String yanzhengma) {
               ToastUtils.showToast(getActivity(),yanzhengma);

           }
       },mButton_yanzhengma).sendyanzhengma(getActivity(),map);

    }

    //手机号登录请求
    private void loginrequest(Map map) {
        progressDlog.showPD("正在登录中");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                progressDlog.cancelPD();
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
                    Staticdata. static_userBean=userBean;
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
                    SharedPreferencesUtils.putString(getActivity(),"QMB","phonenumber",phonenumber);
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
                    Userphonenumber=phonenumber;//将电话号设为全局变量
                    Map map = new HashMap();
                    map.put("ValidateCode", yangzhengma);
                    map.put("phoneNumbers", phonenumber);
                    map.put("uuid", Staticdata.UUID);
                    map.put("Jpush_id", Staticdata.JpushID);
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
    private void initdata(){
        progressDlog=new ProgressDlog(getActivity());
        phonenumber=SharedPreferencesUtils.getString(getActivity(),"QMB","phonenumber");
        medit_phone.setText(phonenumber);

    }

}
