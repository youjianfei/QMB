package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.MoneyTextWatcher;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CashoutActivity extends BaseActivityother {
    //控件
    EditText mEdit_cash;
    EditText mEdit_realname;
    EditText mEdit_zhiAcount;
    TextView mTextview_amount;
    TextView mTextview_allcash;
    Button mButton_submit;

    String name="";
    String zhifubao="";
    String amount="";
    String money="";

    Map map_cash;



    @Override
    public int setLayoutResID() {
        return R.layout.activity_cashout2;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        money=intent.getStringExtra("money");
        mTextview_amount.setText("可提现金额 "+money);
        map_cash=new HashMap();
    }

    @Override
    protected void initListener() {
        mEdit_cash.addTextChangedListener(new MoneyTextWatcher(mEdit_cash).setDigits(1));
        mTextview_allcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdit_cash.setText(money);
            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(initmap()){
                if(Double.parseDouble(money)<Double.parseDouble(amount)){
                    ToastUtils.showToast(CashoutActivity.this,"提现金额有误");
                    return;
                }
                    LogUtils.LOG("ceshi",Urls.Baseurl_hu+Urls.cashmoney,"tixian金额网址");
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi",respose,"tixian金额");
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
                                ToastUtils.showToast(CashoutActivity.this,msg);
                            }else {
                                ToastUtils.showToast(CashoutActivity.this,msg);
                            }
                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl_hu+Urls.cashmoney,CashoutActivity.this,1,map_cash);
                }

            }
        });
    }

    @Override
    protected void initView() {
        mEdit_cash=findViewById(R.id.edit_cashAmount);
        mEdit_realname=findViewById(R.id.medit_realname);
        mEdit_zhiAcount=findViewById(R.id.medit_zhiacount);
        mTextview_amount=findViewById(R.id.text_iscash);
        mTextview_allcash=findViewById(R.id.text_allcash);
        mButton_submit=findViewById(R.id.button_queren);
    }
    boolean initmap(){
        name=mEdit_realname.getText()+"";
        if(name.equals("")){
            ToastUtils.showToast(CashoutActivity.this,"请输入姓名");
            return false;
        }
        zhifubao=mEdit_zhiAcount.getText()+"";
        if(zhifubao.equals("")){
            ToastUtils.showToast(CashoutActivity.this,"请输入支付宝账号");
            return false;
        }
        amount=mEdit_cash.getText()+"";
        if(amount.equals("")){
            ToastUtils.showToast(CashoutActivity.this,"请输入金额");
            return false;
        }
        map_cash.put("payee_account",zhifubao);
        map_cash.put("amount",amount);
        map_cash.put("payee_real_name",name);
        map_cash.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_cash.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        return true;
    }
}
