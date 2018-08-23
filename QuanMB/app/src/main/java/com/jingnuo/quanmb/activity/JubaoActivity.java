package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JubaoActivity extends BaseActivityother {

    EditText mEdit_view;
    Button mButton;

    String mysuggest="";
    String ID="";
    String typeID="";

    Map map_suggest;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_jubao;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        ID=getIntent().getStringExtra("jubaoid");
        typeID=getIntent().getStringExtra("typeID");
        map_suggest=new HashMap();
        map_suggest.put("inform_id",ID);
        map_suggest.put("type",typeID);

    }

    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysuggest=mEdit_view.getText()+"";
                if(mysuggest.equals("")){
                    ToastUtils.showToast(JubaoActivity.this,"请输入举报内容");
                }else {
                    map_suggest.put("inform_content",mysuggest);

                    LogUtils.LOG("ceshi", Urls.Baseurl+Urls.myJubao,"请输入举报内容");
                    LogUtils.LOG("ceshi",map_suggest.toString(),"请输入举报内容");
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
                                ToastUtils.showToast(JubaoActivity.this,msg);
                                finish();
                            }else {
                                ToastUtils.showToast(JubaoActivity.this,msg);
                            }


                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl+Urls.myJubao,JubaoActivity.this,1,map_suggest);
                }


            }
        });
    }

    @Override
    protected void initView() {
        mEdit_view=findViewById(R.id.edit_suggest);
        mButton=findViewById(R.id.button_submit);
    }
}
