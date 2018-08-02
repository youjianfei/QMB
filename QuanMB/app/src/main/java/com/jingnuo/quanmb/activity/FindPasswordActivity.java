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
import com.jingnuo.quanmb.Interface.SendYanZhengmaSuccess;
import com.jingnuo.quanmb.class_.RegularYanzheng;
import com.jingnuo.quanmb.class_.SendYanZhengMa;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class FindPasswordActivity extends BaseActivityother {
    //控件
    EditText mEdit_phonenumber, mEdit_yanzhengma, mEdit_password,mEdit_fpassword_again;
    Button mButton_getyanzhengma, mButton_submit;
    ImageView mImageview_hide;
    //数据
    String phonenumber = "", yanzhengma = "", password = "",passwordagain = "";
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
        mEdit_fpassword_again = findViewById(R.id.edit_password_find_again);
        mEdit_yanzhengma = findViewById(R.id.edit_yanzhengma_find);
        mEdit_password = findViewById(R.id.edit_password_find);
        mButton_getyanzhengma = findViewById(R.id.button_yanzhengma_find);
        mButton_submit = findViewById(R.id.button_submit);
        mImageview_hide = findViewById(R.id.image_hide);
    }

    boolean initmap(){
        phonenumber = mEdit_phonenumber.getText().toString();
        if(phonenumber.equals("")){
            ToastUtils.showToast(this,"请输入手机号");
            return false;
        }
        yanzhengma = mEdit_yanzhengma.getText().toString();
        if(yanzhengma.equals("")){
            ToastUtils.showToast(this,"请输入验证码");
            return false;
        }
        password=mEdit_password.getText()+"";
        if(password.equals("")){
            ToastUtils.showToast(this,"请输入新密码");
            return false;
        }
        if (! RegularYanzheng.ispassword(password)||password.length()<6||password.length()>18){
            ToastUtils.showToast(this, "密码必须为6~18位且包含字母");
            return false;
        }
        passwordagain=mEdit_fpassword_again.getText()+"";
        if(!passwordagain.equals(password)){
            ToastUtils.showToast(this,"两次密码不一致");
            return false;
        }


        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_submit://提交

                if(initmap()){
                    String passwordMM= PasswordJiami.passwordjiami(password);
                    findpasswordMap.put("phoneNumbers",phonenumber);
                    findpasswordMap.put("ValidateCode",yanzhengma);
                    findpasswordMap.put("newPassword",passwordMM);
                    changePasswordQuester(findpasswordMap);//修改密码请求
                }

                break;
            case R.id.button_yanzhengma_find://获取验证码
                phonenumber = mEdit_phonenumber.getText().toString();
                if (phonenumber.equals("")) {
                    ToastUtils.showToast(this, "请填写手机号");
                    return;
                }
                findpasswordMap.put("phoneNumbers", phonenumber);


                new SendYanZhengMa(new SendYanZhengmaSuccess() {//得到验证码请求
                    @Override
                    public void onSuccesses(String yanzhengma) {
                        ToastUtils.showToast(FindPasswordActivity.this,yanzhengma);

                    }
                },mButton_getyanzhengma).sendyanzhengma(FindPasswordActivity.this,findpasswordMap);



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
                finish();
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl + Urls.findpassword, this, 1,map);

    }

}
