package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jingnuo.quanmb.Interface.SendYanZhengmaSuccess;
import com.jingnuo.quanmb.class_.SendYanZhengMa;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

public class ThreeRegisterActivity extends BaseActivityother {

    //控件
    EditText mEdit_phonenumber;
    EditText mEdit_password;
    EditText mEdit_yanzhengma;
    EditText getmEdit_passwordagain;
//    ImageView mImage_choose;
    Button mButton_getyanzhengma;
    Button mButton_complte;

    //数据
    String phonenumber = "";
    String password = "";
    String passwordagain = "";
    String yanzhengma = "";

    Map map_relogin;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_three_register;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        map_relogin = new HashMap();
    }

    @Override
    protected void initListener() {
        mButton_getyanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonenumber = mEdit_phonenumber.getText() + "";
                if (yanzhengma.equals("")) {
                    ToastUtils.showToast(ThreeRegisterActivity.this, "请输入手机号");
                    return;
                }else {

                    map_relogin.put("phoneNumbers",phonenumber);
                    map_relogin.put("type","1");

                    new SendYanZhengMa(new SendYanZhengmaSuccess() {
                        @Override
                        public void onSuccesses(String yanzhengma) {
                            ToastUtils.showToast(ThreeRegisterActivity.this,yanzhengma);
                        }
                    },mButton_getyanzhengma).sendyanzhengma(ThreeRegisterActivity.this,map_relogin);

                }
            }
        });

    }

    @Override
    protected void initView() {
        mEdit_phonenumber = findViewById(R.id.edit_phonenumber);
        mEdit_password = findViewById(R.id.edit_password);
        mEdit_yanzhengma = findViewById(R.id.edit_yanzhegnma);
        getmEdit_passwordagain = findViewById(R.id.edit_passwordagain);
        mButton_getyanzhengma = findViewById(R.id.button_getyanzhangma);
//        mImage_choose = findViewById(R.id.image_choose);
        mButton_complte = findViewById(R.id.button_register);
    }

    boolean initmap() {
        phonenumber=mEdit_phonenumber.getText()+"";
        if(phonenumber.equals("")){
            ToastUtils.showToast(ThreeRegisterActivity.this,"请输入手机号");
            return false;
        }
        yanzhengma=mEdit_yanzhengma.getText()+"";
        if(yanzhengma.equals("")){
            ToastUtils.showToast(ThreeRegisterActivity.this,"请输入验证码");
            return false;
        }
        password=mEdit_password.getText()+"";
        if(password.equals("")){
            ToastUtils.showToast(ThreeRegisterActivity.this,"请输入密码");
            return false;
        }
        if(passwordagain.equals(password)){

        }else {
            ToastUtils.showToast(ThreeRegisterActivity.this,"两次输入密码不一致");
            return false;
        }

        return true;


    }
}
