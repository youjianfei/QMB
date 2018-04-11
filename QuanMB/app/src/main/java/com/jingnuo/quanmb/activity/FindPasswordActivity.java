package com.jingnuo.quanmb.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class FindPasswordActivity extends BaseActivityother {
    //控件
    EditText mEdit_phonenumber, mEdit_yanzhengma, mEdit_password;
    Button mButton_getyanzhengma, mButton_submit;
    ImageView mImageview_hide;
    //数据
    String phonenumber = "", yanzhengma = "", password = "";
    Map findpasswordMap;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        findpasswordMap = new HashMap();

    }

    @Override
    protected void initListener() {
        mButton_getyanzhengma.setOnClickListener(this);
        mButton_submit.setOnClickListener(this);
        mImageview_hide.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mEdit_phonenumber = findViewById(R.id.edit_phonenumber_find);
        mEdit_yanzhengma = findViewById(R.id.edit_yanzhengma_find);
        mEdit_password = findViewById(R.id.edit_password_find);
        mButton_getyanzhengma = findViewById(R.id.button_yanzhengma_find);
        mButton_submit = findViewById(R.id.button_submit);
        mImageview_hide = findViewById(R.id.image_hide);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_submit://提交
                phonenumber = mEdit_phonenumber.getText().toString();
                yanzhengma = mEdit_yanzhengma.getText().toString();
                password = mEdit_password.getText().toString();
                if (phonenumber.equals("") || yanzhengma.equals("") || password.equals("")) {
                    ToastUtils.showToast(this, "信息填写不完整");
                    return;
                }
                String passwordMM= PasswordJiami.passwordjiami(password);
                findpasswordMap.put("phoneNumbers",phonenumber);
                findpasswordMap.put("ValidateCode",yanzhengma);
                findpasswordMap.put("newPassword",passwordMM);
                changePasswordQuester(findpasswordMap);//修改密码请求
                break;
            case R.id.button_yanzhengma_find://获取验证码
                phonenumber = mEdit_phonenumber.getText().toString();
                if (phonenumber.equals("")) {
                    ToastUtils.showToast(this, "请填写手机号");
                    return;
                }
                findpasswordMap.put("phoneNumbers", phonenumber);

                getyanzhengma(findpasswordMap);//得到验证码请求

                break;
            case R.id.image_hide://隐藏显示密码
                if (mImageview_hide.isSelected()) {
                    mImageview_hide.setSelected(false);
                    //选择状态 --设置为不可见的密码
                    mEdit_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    mImageview_hide.setSelected(true);
                    //未选择状态 显示明文--设置为可见的密码
                    mEdit_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }


                break;
        }
    }

    //发送手机验证码
    private void getyanzhengma(Map map) {
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
                    ToastUtils.showToast(FindPasswordActivity.this, "验证码已发送");
                    //验证码倒计时
                    timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            mhandler.sendEmptyMessage(0);
                        }
                    };
                    timer.schedule(timerTask, 0, 1000);
                    mButton_getyanzhengma.setEnabled(false);
                } else {
                    ToastUtils.showToast(FindPasswordActivity.this, msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl + Urls.sendzhuceyanzhengma, this, 1, map);
    }

    void changePasswordQuester(Map map) {
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
                ToastUtils.showToast(FindPasswordActivity.this, msg);

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl + Urls.findpassword, this, 1,map);


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
                    mButton_getyanzhengma.setText(time + " s");
                    time--;
                    if (time == 0) {
                        timer.cancel();
                        timer = null;
                        mButton_getyanzhengma.setText("获取验证码");
                        mButton_getyanzhengma.setEnabled(true);
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
