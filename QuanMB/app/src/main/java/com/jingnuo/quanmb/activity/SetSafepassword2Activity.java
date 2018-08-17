package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.class_.RegularYanzheng;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SetSafepassword2Activity extends BaseActivityother {

    //控件
    TextView mTextview_textview_title;
    EditText mEdit_newpassword;
    EditText mEdit_newpasswordagain;
    ImageView mImageview_hide;
    Button mButton;
    //数据
    String newpassword="";
    String newpasswordagain="";
    Map map_setsafepassword;


    String change="";
    @Override
    public int setLayoutResID() {
        return R.layout.activity_set_safepassword2;
    }

    @Override
    protected void setData() {
        map_setsafepassword=new HashMap();
    }
    boolean initmap(){

        newpassword=mEdit_newpassword.getText()+"";
        if(newpassword.equals("")){
            ToastUtils.showToast(this,"支付密码不能为空");
            return false;
        }
        if(newpassword.length()!=6){
            ToastUtils.showToast(this,"支付密码必须为6位数字");
            return false;
        }
        newpasswordagain=mEdit_newpasswordagain.getText()+"";
        if(newpasswordagain.equals("")){
            ToastUtils.showToast(this,"确认密码不能为空");
            return false;
        }
        if(!newpassword.equals(newpasswordagain)){
            ToastUtils.showToast(this,"两次密码填写不一致");
            return false;
        }

        return true;
    }
    @Override
    protected void initData() {
        change=getIntent().getStringExtra("change");
        if(change.equals("change")){
            mTextview_textview_title.setText("修改支付密码");
        }
    }

    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(initmap()){
                    String NewpasswordMM= PasswordJiami.passwordjiami(newpassword);
                    map_setsafepassword.put("security_code",NewpasswordMM);
                    map_setsafepassword.put("user_token", Staticdata.static_userBean.getData().getUser_token());

                    request(map_setsafepassword);
                }

            }
        });
        mImageview_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImageview_hide.isSelected()) {
                    mImageview_hide.setSelected(false);
                    //选择状态 --设置为不可见的密码
                    mEdit_newpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    mImageview_hide.setSelected(true);
                    //未选择状态 显示明文--设置为可见的密码
                    mEdit_newpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
        mEdit_newpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ss=s+"";
                if(ss.length()>6){
                    mEdit_newpassword.setText(ss.substring(0,6));
                    mEdit_newpassword.setSelection(mEdit_newpassword.getText().toString().length());
                }


            }
        });

    }

    @Override
    protected void initView() {
        mTextview_textview_title=findViewById(R.id.textview_title);
        mButton=findViewById(R.id.button_password);
        mEdit_newpassword=findViewById(R.id.edit_newpassword);
        mEdit_newpasswordagain=findViewById(R.id.edit_newpasswordagain);
        mImageview_hide=findViewById(R.id.image_hide);
    }
    void request(Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(SetSafepassword2Activity.this,msg);
                    Staticdata.static_userBean.getData().getAppuser().setSecurity_code("00000000");
                    finish();
                }else {
                    ToastUtils.showToast(SetSafepassword2Activity.this,msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.setSafepassword,SetSafepassword2Activity.this,1,map);
    }
}
