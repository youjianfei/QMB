package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends BaseActivityother {
    //控件
    TextView mtextview_title;
    EditText mEdit_name;
    EditText mEdit_number;
    Button mButton_submit;
    ImageView mImage_nan;
    ImageView mImage_nv;
    ImageView mImage_setmoren;
    RelativeLayout mRelayout_setmoren;

    String  name="";
    String  phonenumber="";
    int  sex=0;
    Map map_lianxiren;


    String  type="";
    String id="";//编辑要用
    String URL="";
    String isDefult="N";




    @Override
    public int setLayoutResID() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        map_lianxiren=new HashMap();
        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        if (type.equals("xinzeng")){
            mtextview_title.setText("新增联系人");
            URL= Urls.Baseurl+Urls.addLianxiren;
        }else {
            mRelayout_setmoren.setVisibility(View.VISIBLE);
            URL= Urls.Baseurl+Urls.editLianxiren;
            mtextview_title.setText("编辑联系人");
            name=intent.getStringExtra("name");
            phonenumber=intent.getStringExtra("phonenumber");
            sex=intent.getIntExtra("sex",0);
            id=intent.getStringExtra("id");
            if(sex==0){
                mImage_nv.setSelected(false);
                mImage_nan.setSelected(true);
            }else {
                mImage_nv.setSelected(true);
                mImage_nan.setSelected(false);
            }
            mEdit_name.setText(name);
            mEdit_number.setText(phonenumber);

            map_lianxiren.put("id",id);
            map_lianxiren.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
            return;
        }
        mImage_nan.setSelected(true);
    }

    @Override
    protected void initListener() {
        mImage_nan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImage_nan.setSelected(true);
                mImage_nv.setSelected(false);
                sex=0;
            }
        });
        mImage_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImage_nan.setSelected(false);
                mImage_nv.setSelected(true);
                sex=1;
            }
        });
        mImage_setmoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDefult.equals("N")){
                    mImage_setmoren.setSelected(true);
                    isDefult="Y";
                }else {
                    mImage_setmoren.setSelected(false);
                    isDefult="N";
                }

            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init()){
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            int status = 0;
                            String msg = "";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");
                                msg = (String) object.get("msg");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ToastUtils.showToast(AddAddressActivity.this, msg);
                            if(status==1){
                                finish();
                            }
                        }
                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(URL,AddAddressActivity.this,1,map_lianxiren);
                }
            }
        });

    }

    @Override
    protected void initView() {
        mtextview_title=findViewById(R.id.textview_title);
        mEdit_name=findViewById(R.id.edit_name);
        mEdit_number=findViewById(R.id.edit_phonenumber);
        mButton_submit=findViewById(R.id.button_submitsave);
        mImage_nan=findViewById(R.id.image_choosejieshou);
        mImage_nv=findViewById(R.id.image_choosejujue);
        mImage_setmoren=findViewById(R.id.image_setmoren);
        mRelayout_setmoren=findViewById(R.id.relay_setmoren);
    }
    boolean init(){
        name=mEdit_name.getText()+"";
        if(name.equals("")){
            ToastUtils.showToast(this,"请输入联系人");
        return false;
        }
        phonenumber=mEdit_number.getText()+"";
        if(phonenumber.equals("")){
            ToastUtils.showToast(this,"请输入手机号");
            return false;
        }
        map_lianxiren.put("name",name);
        map_lianxiren.put("sex",sex+"");
        map_lianxiren.put("mobile_no",phonenumber);
        map_lianxiren.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        map_lianxiren.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_lianxiren.put("is_default",isDefult);//暂时设为 不是默认
        return true;
    }
}
