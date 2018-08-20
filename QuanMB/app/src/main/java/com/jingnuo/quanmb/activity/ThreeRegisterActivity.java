package com.jingnuo.quanmb.activity;

import android.content.Intent;
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
import com.jingnuo.quanmb.class_.SendYanZhengMa;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;

public class ThreeRegisterActivity extends BaseActivityother {

    //控件
    EditText mEdit_phonenumber;
    EditText mEdit_password;
    EditText mEdit_yanzhengma;
    EditText getmEdit_passwordagain;
    EditText mEdit_tuijianma;
    String tuijianma = "";
//    ImageView mImage_choose;
    Button mButton_getyanzhengma;
    Button mButton_complte;
    TextView mTextview_bindnow;
    ImageView mImageview_hide;

    //数据
    UserBean userBean;
    String phonenumber = "";
    String password = "";
    String passwordagain = "";
    String yanzhengma = "";
    String publicEncryptedResult = "";//加密后密码
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
                if (phonenumber.equals("")) {
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
        mImageview_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageview_hide.isSelected()) {
                    mImageview_hide.setSelected(false);
                    //选择状态 --设置为不可见的密码
                    mEdit_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    mImageview_hide.setSelected(true);
                    //未选择状态 显示明文--设置为可见的密码
                    mEdit_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });

        mButton_complte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(initmap()){

                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi",respose,"注册绑定");
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
                                SharedPreferencesUtils.putString(ThreeRegisterActivity.this,"QMB","password",password);//登录成功之后存未加密de密码
                                userBean=new Gson().fromJson(respose,UserBean.class);
                                Staticdata. static_userBean=userBean;
                                isLogin = true;
                                Intent intent_login = new Intent(ThreeRegisterActivity.this, MainActivity.class);
                                ThreeRegisterActivity.this.startActivity(intent_login);
                                finish();
                            }else {
                                ToastUtils.showToast(ThreeRegisterActivity.this,msg);
                            }
                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl+Urls.registerBind,ThreeRegisterActivity.this,1,map_relogin);
                }
            }
        });

        mTextview_bindnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_bind=new Intent(ThreeRegisterActivity.this,ThreeLoginBindActivity.class);
                startActivity(intent_bind);

            }
        });
    }

    @Override
    protected void initView() {
        mEdit_phonenumber = findViewById(R.id.edit_phonenumber);
        mEdit_password = findViewById(R.id.edit_password);
        mEdit_yanzhengma = findViewById(R.id.edit_yanzhegnma);
        getmEdit_passwordagain = findViewById(R.id.edit_passwordagain);
        mEdit_tuijianma = findViewById(R.id.edit_tuijianma);
        mButton_getyanzhengma = findViewById(R.id.button_getyanzhangma);
//        mImage_choose = findViewById(R.id.image_choose);
        mButton_complte = findViewById(R.id.button_register);
        mTextview_bindnow=findViewById(R.id.textview_bind);
        mImageview_hide=findViewById(R.id.image_hide);
    }

    boolean initmap() {
        phonenumber=mEdit_phonenumber.getText()+"";
        if(phonenumber.equals("")){
            ToastUtils.showToast(ThreeRegisterActivity.this,"请输入手机号");
            return false;
        }
        map_relogin.put("phoneNumbers",phonenumber);
        yanzhengma=mEdit_yanzhengma.getText()+"";
        if(yanzhengma.equals("")){
            ToastUtils.showToast(ThreeRegisterActivity.this,"请输入验证码");
            return false;
        }
        map_relogin.put("ValidateCode",yanzhengma);
        password=mEdit_password.getText()+"";
        if(password.equals("")){
            ToastUtils.showToast(ThreeRegisterActivity.this,"请输入密码");
            return false;
        }
        publicEncryptedResult= PasswordJiami.passwordjiami(password);//对密码加密
        map_relogin.put("password",publicEncryptedResult);
        passwordagain=getmEdit_passwordagain.getText()+"";
        if(!passwordagain.equals(password)){
            ToastUtils.showToast(ThreeRegisterActivity.this,"两次输入密码不一致");
            return false;
        }
        map_relogin.remove("type");
        map_relogin.put("confirm",publicEncryptedResult);
        map_relogin.put("nick_name", Staticdata.map_wechat.get("nick_name"));
//        map_relogin.put("sex",Staticdata.map_wechat.get("sex"));
        map_relogin.put("unionid",Staticdata.map_wechat.get("unionid"));
        map_relogin.put("uuid",Staticdata.UUID);
        map_relogin.put("Jpush_id", Staticdata.JpushID);
        tuijianma=mEdit_tuijianma.getText()+"";
        if(!tuijianma.equals("")){
            map_relogin.put("recommend_code",tuijianma);
        }

        return true;


    }
}
