package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.RegularYanzheng;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangepasswordActivity extends BaseActivityother {

    //控件
    EditText mEdit_oldpassword;
    EditText mEdit_newpassword;
    EditText mEdit_newpasswordagain;
    ImageView mImageview_hide;
    Button mButton;
    //数据
    String oldpassword="";
    String newpassword="";
    String newpasswordagain="";
    Map map_changpassword;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_changepassword;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        map_changpassword=new HashMap();
    }
    boolean initmap(){
        oldpassword=mEdit_oldpassword.getText()+"";
        if(oldpassword.equals("")){
            ToastUtils.showToast(this,"旧密码不能为空");
            return false;
        }
        newpassword=mEdit_newpassword.getText()+"";
        if(newpassword.equals("")){
            ToastUtils.showToast(this,"新密码不能为空");
            return false;
        }
        if (! RegularYanzheng.ispassword(newpassword)||newpassword.length()<6||newpassword.length()>18){
            ToastUtils.showToast(this, "密码必须为6~18位且包含字母");
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
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(initmap()){
                    String OldpasswordMM= PasswordJiami.passwordjiami(oldpassword);
                    String NewpasswordMM= PasswordJiami.passwordjiami(newpassword);
                    map_changpassword.put("oldPassword",OldpasswordMM);
                    map_changpassword.put("newPassword",NewpasswordMM);
                    map_changpassword.put("confirm",NewpasswordMM);
                    map_changpassword.put("user_token", Staticdata.static_userBean.getData().getUser_token());

                    request(map_changpassword);
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

    }

    @Override
    protected void initView() {
        mButton=findViewById(R.id.button_password);
        mEdit_oldpassword=findViewById(R.id.edit_oldpassword);
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
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(ChangepasswordActivity.this,msg);
                    finish();
                }else {
                    ToastUtils.showToast(ChangepasswordActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.changepassword,ChangepasswordActivity.this,1,map);
    }
}
