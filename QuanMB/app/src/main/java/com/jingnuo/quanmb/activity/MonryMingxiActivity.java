package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_Jiaoyimingxi;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.JiaoyiMingxi;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonryMingxiActivity extends BaseActivityother {

    PullToRefreshListView listView;


    Map map_mingxi;

    List<JiaoyiMingxi.DataBean>mData;
    Adapter_Jiaoyimingxi adapter_jiaoyimingxi;
    JiaoyiMingxi jiaoyiMingxi;



    @Override
    public int setLayoutResID() {
        return R.layout.activity_monry_mingxi;
    }


    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mData=new ArrayList<>();
        adapter_jiaoyimingxi=new Adapter_Jiaoyimingxi(mData,MonryMingxiActivity.this);
        listView.setAdapter(adapter_jiaoyimingxi);
        map_mingxi=new HashMap();
        map_mingxi.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
//        map_mingxi.put("type", "");   类型（1：支付，2：提现，3：充值，4：退款）
//        map_mingxi.put("pay_method", "");  付款方式（微信：WX，支付宝：ZFB， 余额支付:QMB）
        map_mingxi.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        requestMingxi(map_mingxi);
    }

    @Override
    protected void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi",position+"","jiao易明细列表");
                Intent intent_moneydetails=new Intent(MonryMingxiActivity.this,MoneyMingxiDetailsActivity.class);
                intent_moneydetails.putExtra("money",mData.get(position-1).getOrder_amoun()+"");
                intent_moneydetails.putExtra("type",mData.get(position-1).getPay_method()+"");//zhi'付方式
                intent_moneydetails.putExtra("time",mData.get(position-1).getCreateDate()+"");
                intent_moneydetails.putExtra("order",mData.get(position-1).getOrder_no()+"");
                intent_moneydetails.putExtra("title",mData.get(position-1).getBill_title()+"");
                startActivity(intent_moneydetails);
            }
        });


    }

    @Override
    protected void initView() {
        listView=findViewById(R.id.list_mingxi);
    }
    void requestMingxi(Map map){
        LogUtils.LOG("ceshi",Urls.Baseurl_hu+Urls.jiaoyiMingxi,"请求交易明细");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"请求交易明细");
                jiaoyiMingxi=new Gson().fromJson(respose,JiaoyiMingxi.class);
                if(jiaoyiMingxi.getCode()==1){
                    mData.addAll(jiaoyiMingxi.getData());
                    adapter_jiaoyimingxi.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.jiaoyiMingxi,MonryMingxiActivity.this,1,map);

    }
}
