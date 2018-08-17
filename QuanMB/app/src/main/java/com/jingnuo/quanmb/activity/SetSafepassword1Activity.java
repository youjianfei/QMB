package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.Interface.SendYanZhengmaSuccess;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.class_.SendYanZhengMa;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SetSafepassword1Activity extends BaseActivityother {
    //控件
    TextView textview_titile;
    Button mButton_submit;
    Button mButton_getyanzhengma;
    TextView mtextview_phonenumber;
    EditText mEdit_yanchengma;

    Map map_oldphonenumber;

    String change="";

    @Override
    public int setLayoutResID() {
        return R.layout.activity_set_safepassword1;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        change=getIntent().getStringExtra("change");
        if(change.equals("change")){
            textview_titile.setText("修改支付密码");
        }
        mtextview_phonenumber.setText(Staticdata.Userphonenumber);
        map_oldphonenumber  =new HashMap();
        map_oldphonenumber.put("phoneNumbers",Staticdata.Userphonenumber);
    }

    @Override
    protected void initListener() {
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yanzhegnma= mEdit_yanchengma.getText()+"";
                if(yanzhegnma.equals("")){
                    ToastUtils.showToast(SetSafepassword1Activity.this,"请输入验证码");
                    return;
                }else {
                    map_oldphonenumber.put("ValidateCode",yanzhegnma);
                    map_oldphonenumber.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    request(map_oldphonenumber);
                }


            }
        });

        mButton_getyanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SendYanZhengMa(new SendYanZhengmaSuccess() {//调用发送验证码的方法
                    @Override
                    public void onSuccesses(String yanzhengma) {
                        ToastUtils.showToast(SetSafepassword1Activity.this,yanzhengma);

                    }
                },mButton_getyanzhengma).sendyanzhengma(SetSafepassword1Activity.this,map_oldphonenumber);

            }
        });
    }

    @Override
    protected void initView() {
        mButton_submit=findViewById(R.id.buttion_bind);
        mButton_getyanzhengma=findViewById(R.id.button_yanzhengma);
        mtextview_phonenumber=findViewById(R.id.text_oldphonenumber);
        textview_titile=findViewById(R.id.textview_title);
        mEdit_yanchengma=findViewById(R.id.edit_yanzhengma);
    }
    void  request(Map map){

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("message");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(status==1){
                    Intent intent=new Intent(SetSafepassword1Activity.this,SetSafepassword2Activity.class);
                    if(change.equals("change")){
                        intent.putExtra("change","change");
                    }else {
                        intent.putExtra("change","nochange");
                    }

                    startActivity(intent);
                    finish();

                }else {
                    ToastUtils.showToast(SetSafepassword1Activity.this,msg);
                }


            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.changephonenumber,SetSafepassword1Activity.this,1,map);
    }
}
