package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.class_.RegularYanzheng;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SetPasswordActivity extends BaseActivityother {
    //控件
    Button mButton_setpassword;
    EditText edit_newpassword;
    EditText edit_newpasswordagain;
    //数据
    String  password="";
    String  passwordagain="";
    String  passwordagain_jiami="";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    mButton_setpassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            password=edit_newpassword.getText().toString().trim();
            if(password.equals("")){
                ToastUtils.showToast(SetPasswordActivity.this,"新密码不能为空");
                return ;
            }
            if (! RegularYanzheng.ispassword(password)||password.length()<6||password.length()>18){
                ToastUtils.showToast(SetPasswordActivity.this, "密码必须为6~18位且包含字母");
                return ;
            }
            passwordagain=edit_newpasswordagain.getText()+"";
            if(passwordagain.equals("")){
                ToastUtils.showToast(SetPasswordActivity.this,"确认密码不能为空");
                return ;
            }
            if(!password.equals(passwordagain)){
                ToastUtils.showToast(SetPasswordActivity.this,"两次密码填写不一致");
                return ;
            }
            passwordagain_jiami= PasswordJiami.passwordjiami(password);
            Map map=new HashMap();
            map.put("user_token",Staticdata.static_userBean.getData().getUser_token());
            map.put("newPassword",passwordagain_jiami);
            map.put("confirm",passwordagain_jiami);
            request(map);

        }
    });
    }

    @Override
    protected void initView() {
        mButton_setpassword=findViewById(R.id.button_password);
        edit_newpassword=findViewById(R.id.edit_newpassword);
        edit_newpasswordagain=findViewById(R.id.edit_newpasswordagain);

    }
  void   request  (Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"设置密码界面");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(SetPasswordActivity.this,msg);
                    Staticdata.static_userBean.getData().getAppuser().setPassworded("01");
                    finish();
                }else {
                    ToastUtils.showToast(SetPasswordActivity.this,"修改失败，请稍后重试");

                }


            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.setpassword,this,1,map);
  }
}
