package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_recharge_tuiguangbi;
import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.broadcastrReceiver.PaySuccessOrErroBroadcastReciver;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.TuiguangbiTaocanBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.List;

public class TuiguangbiWalletActivity extends BaseActivityother {

    MyGridView myGridView_rechargeTUi;
    TextView mTextview_count;
    Button mButton_queren;

    List<TuiguangbiTaocanBean.DataBean> mdata;
    Adapter_recharge_tuiguangbi adapter_recharge_tuiguangbi;


    private IntentFilter intentFilter_paysuccess;//定义广播过滤器；
    private PaySuccessOrErroBroadcastReciver paysuccess_BroadcastReciver;//定义广播监听器


    int select=0;
    int type=0;//身份
    int total_spreadcoin=0;//选择的充值推广币个数
    double price=0;   //选择的充值de钱数

    String TuiguangbiCount="";

    @Override
    public int setLayoutResID() {
        return R.layout.activity_tuiguangbi_wallet;
    }

    @Override
    protected void setData() {
        mTextview_count.setText(TuiguangbiCount);
        mdata=new ArrayList<>();
        adapter_recharge_tuiguangbi=new Adapter_recharge_tuiguangbi(mdata,this);
        myGridView_rechargeTUi.setAdapter(adapter_recharge_tuiguangbi);

        intentFilter_paysuccess = new IntentFilter();
        intentFilter_paysuccess.addAction("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
        paysuccess_BroadcastReciver=new PaySuccessOrErroBroadcastReciver(new Interface_paySuccessOrerro() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "payResult");
                if(respose.equals("success")){//支付成功
                    Intent intent=new Intent(TuiguangbiWalletActivity.this,PaySuccessActivity.class);
                    intent.putExtra("title","支付成功");
                    intent.putExtra("typesuccess","支付成功");
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(TuiguangbiWalletActivity.this, "支付失败");
                    }
                });
            }
        });
        registerReceiver(paysuccess_BroadcastReciver, intentFilter_paysuccess); //将广播监听器和过滤器注册在一起；
    }

    @Override
    protected void initData() {
     Intent intent=getIntent();
     type=intent.getIntExtra("type",0);
     TuiguangbiCount=intent.getStringExtra("tuiguangbi");
     requestTui_grid();
    }

    @Override
    protected void initListener() {
        myGridView_rechargeTUi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi",position+"","点击");
                    total_spreadcoin= mdata.get(position).getSpread_b();
                    price=mdata.get(position).getPrice();
                    select=position;
                    adapter_recharge_tuiguangbi.setSeclection(select);
                    adapter_recharge_tuiguangbi.notifyDataSetInvalidated();
            }
        });
        mButton_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_paytuiguangbi=new Intent(TuiguangbiWalletActivity.this,PaytuiguangbiActivity.class);
                intent_paytuiguangbi.putExtra("neirong","推广币");
                intent_paytuiguangbi.putExtra("amount",price+"");
                intent_paytuiguangbi.putExtra("total_spreadcoin",total_spreadcoin+"");
                startActivity(intent_paytuiguangbi);

            }
        });
    }

    @Override
    protected void initView() {
        myGridView_rechargeTUi=findViewById(R.id.mygrid_recharge);
        mTextview_count=findViewById(R.id.text_tuiCount);
        mButton_queren=findViewById(R.id.button_queren);
    }
    void  requestTui_grid(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose+"","套餐列表");
                mdata.clear();
                mdata.addAll(new Gson().fromJson(respose,TuiguangbiTaocanBean.class).getData());
                adapter_recharge_tuiguangbi.notifyDataSetChanged();
                //默认选择第一个
                total_spreadcoin= mdata.get(0).getSpread_b();
                price=mdata.get(0).getPrice();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_hu+Urls.tui_taocan+ Staticdata.static_userBean.getData().getUser_token(),TuiguangbiWalletActivity.this,0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paysuccess_BroadcastReciver);
    }
}
