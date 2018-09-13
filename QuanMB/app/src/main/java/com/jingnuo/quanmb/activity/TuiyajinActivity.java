package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.customview.PayFragment;
import com.jingnuo.quanmb.customview.PayPwdView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.MoneyTextWatcher;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TuiyajinActivity extends BaseActivityother implements PayPwdView.InputCallBack{
    //控件
    TextView mEdit_cash;
    EditText mEdit_realname;
    EditText mEdit_zhiAcount;
    TextView mTextview_cash_tip;
    Button mButton_submit;

    String name="";
    String zhifubao="";
    String amount="";

    Map map_cash;

    PayFragment fragment;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_tuiyajin;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

        map_cash=new HashMap();
    }

    @Override
    protected void initListener() {
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Staticdata.static_userBean.getData().getAppuser().getSecurity_code().equals("")){
                    ToastUtils.showToast(TuiyajinActivity.this,"请先设置安全密码");
                    Intent intent_setsafe=new Intent(TuiyajinActivity.this,SetSafepassword1Activity.class);
                    intent_setsafe.putExtra("change","nochange");
                    startActivity(intent_setsafe);
                    return;
                }
                if(initmap()){

                    Bundle bundle = new Bundle();
                    bundle.putString(PayFragment.EXTRA_CONTENT, "退款金额"+"：¥ 99.00" );
                    fragment = new PayFragment();
                    fragment.setArguments(bundle);
                    fragment.setPaySuccessCallBack(TuiyajinActivity.this);
                    fragment.show(getFragmentManager(), "Pay");
                }

            }
        });
    }

    @Override
    protected void initView() {
        mEdit_cash=findViewById(R.id.edit_cashAmount);
        mEdit_realname=findViewById(R.id.medit_realname);
        mEdit_zhiAcount=findViewById(R.id.medit_zhiacount);
        mTextview_cash_tip=findViewById(R.id.text_cash_tip);
        mButton_submit=findViewById(R.id.button_queren);
    }
    boolean initmap(){
        name=mEdit_realname.getText()+"";
        if(name.equals("")){
            ToastUtils.showToast(TuiyajinActivity.this,"请输入姓名");
            return false;
        }
        zhifubao=mEdit_zhiAcount.getText()+"";
        if(zhifubao.equals("")){
            ToastUtils.showToast(TuiyajinActivity.this,"请输入支付宝账号");
            return false;
        }


        map_cash.put("payee_account",zhifubao);
        map_cash.put("amount","99");
        map_cash.put("payee_real_name",name);
        map_cash.put("TransferType","4");
        map_cash.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_cash.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        return true;
    }

    @Override
    public void onInputFinish(String result) {
        if(result.equals("1")){
        LogUtils.LOG("ceshi", Urls.Baseurl_hu+Urls.cashmoney,"退押金金额网址");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"退押金金额");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("msg");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(TuiyajinActivity.this,msg);
                    if (fragment!=null){
                        fragment.dismiss();
                    }
                    Intent intent=new Intent(TuiyajinActivity.this,PaySuccessActivity.class);
                    intent.putExtra("title","退款成功");
                    intent.putExtra("typesuccess","退款成功");
                    startActivity(intent);
                    finish();

                }else {
                    ToastUtils.showToast(TuiyajinActivity.this,msg);
                    if (fragment!=null){
                        fragment.dismiss();
                    }
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.cashmoney,TuiyajinActivity.this,1,map_cash);
    }else {
        ToastUtils.showToast(this,"安全密码错误");
    }
    }
}
