package com.jingnuo.quanmb.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SuggestActivity extends BaseActivityother {

    EditText mEdit_view;
    Button mButton;

    String mysuggest="";
    Map map_suggest;

    String release_specialty_id="";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_suggest;
    }

    @Override
    protected void setData() {
    map_suggest=new HashMap();
    map_suggest.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
    map_suggest.put("user_token",Staticdata.static_userBean.getData().getUser_token());
    if(release_specialty_id!=null){
        map_suggest.put("release_specialty_id",release_specialty_id);
    }

    }

    @Override
    protected void initData() {
        release_specialty_id=getIntent().getStringExtra("release_specialty_id");
    }

    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysuggest=mEdit_view.getText()+"";
                if(mysuggest.equals("")){
                    ToastUtils.showToast(SuggestActivity.this,"投诉和建议不能为空");
                }else {
                    map_suggest.put("content",mysuggest);
                    LogUtils.LOG("ceshi",Urls.Baseurl+Urls.mySuggest,"投诉和建议");
                    LogUtils.LOG("ceshi",map_suggest.toString(),"投诉和建议");
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
                                ToastUtils.showToast(SuggestActivity.this,"我们已经收到你的建议");
                                finish();
                            }else {
                                ToastUtils.showToast(SuggestActivity.this,msg);
                            }


                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl+Urls.mySuggest,SuggestActivity.this,1,map_suggest);
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
