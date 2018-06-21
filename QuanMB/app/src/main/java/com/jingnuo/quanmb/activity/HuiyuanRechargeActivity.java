package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_recharge_huiyuan;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.HuiyuanTaocanBean;
import com.jingnuo.quanmb.entityclass.TuiguangbiTaocanBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.List;

public class HuiyuanRechargeActivity extends BaseActivityother {

    //控件
    MyGridView myGridView;
    Button mButton_queren;


    List<HuiyuanTaocanBean.DataBean>mdata;
    Adapter_recharge_huiyuan adapter_recharge_huiyuan;

    int select=0;
    double price=0;   //选择的充值de钱数
    String VIP_unique="";

    @Override
    public int setLayoutResID() {
        return R.layout.activity_huiyuan_recharge;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mdata=new ArrayList<>();
        adapter_recharge_huiyuan=new Adapter_recharge_huiyuan(mdata,HuiyuanRechargeActivity.this);
        myGridView.setAdapter(adapter_recharge_huiyuan);
        requestHuiyuan_grid();
    }

    @Override
    protected void initListener() {
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select=position;
                adapter_recharge_huiyuan.setSeclection(position);
                adapter_recharge_huiyuan.notifyDataSetInvalidated();
                price=mdata.get(position).getPrice();
                VIP_unique=mdata.get(position).getMember_id()+"";
            }
        });

        mButton_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_payhuiyuan=new Intent(HuiyuanRechargeActivity.this,PaytuiguangbiActivity.class);
                intent_payhuiyuan.putExtra("neirong","会员");
                intent_payhuiyuan.putExtra("amount",price+"");
                intent_payhuiyuan.putExtra("VIP_unique",VIP_unique);
                startActivity(intent_payhuiyuan);

            }
        });
    }

    @Override
    protected void initView() {
        myGridView=findViewById(R.id.mygrid_huiyuanrecharge);
        mButton_queren=findViewById(R.id.button_queren);
    }
    void  requestHuiyuan_grid(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose+"","套餐列表");
                mdata.clear();
                mdata.addAll(new Gson().fromJson(respose,HuiyuanTaocanBean.class).getData());
                adapter_recharge_huiyuan.notifyDataSetChanged();
//                //默认选择第一个
                price=mdata.get(0).getPrice();
                VIP_unique=mdata.get(0).getMember_id()+"";
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_hu+Urls.huiyuan_taocan+ Staticdata.static_userBean.getData().getUser_token(),HuiyuanRechargeActivity.this,0);
    }
}
