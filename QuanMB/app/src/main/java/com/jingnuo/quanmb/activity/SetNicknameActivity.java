package com.jingnuo.quanmb.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SetNicknameActivity extends BaseActivityother {

    //控件
    TextView mTextview_submit;
    EditText mEdit_nickname;
    //数据
    String nickname="";

    Map map_nickname;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_set_nickname;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        map_nickname=new HashMap();
    }

    @Override
    protected void initListener() {
        mTextview_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nickname=mEdit_nickname.getText()+"";
                if(nickname.equals("")){
                    ToastUtils.showToast(SetNicknameActivity.this,"请输入用户名");
                }else {
                        map_nickname.put("NickName",nickname);
                        map_nickname.put("user_token", Staticdata.token);
                    LogUtils.LOG("ceshi","修改昵称的map"+map_nickname.toString(),"SetNicknameActivity");
                    request(map_nickname);
                }
            }
        });

    }

    @Override
    protected void initView() {
        mTextview_submit=findViewById(R.id.text_queding);
        mEdit_nickname=findViewById(R.id.edit_oldpassword);

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
                    ToastUtils.showToast(SetNicknameActivity.this,msg);
                    finish();
                }else {
                    ToastUtils.showToast(SetNicknameActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.setnickname,SetNicknameActivity.this,1,map);



    }
}
