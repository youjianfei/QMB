package com.jingnuo.quanmb.activity;


import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.ShopcenterBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.Volley_Utils;


public class ShopCenterActivity extends BaseActivityother {
    //控件
    TextView mTextview_shopname;  //商铺名字
    TextView mTextview_address;//地址
    TextView mTextview_money;//佣金

    //对象
    ShopcenterBean shopcenterBean;






    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_center;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        requestshopcenter();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTextview_shopname=findViewById(R.id.text_shopname);
        mTextview_address=findViewById(R.id.textview_address);
        mTextview_money=findViewById(R.id.textview_money);

    }

    void  requestshopcenter(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                shopcenterBean=new Gson().fromJson(respose,ShopcenterBean.class);
                mTextview_shopname.setText(shopcenterBean.getData().getList().getBusiness_name());
                mTextview_address.setText(shopcenterBean.getData().getList().getBusiness_address());

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.shopcenter+ Staticdata.static_userBean.getData()
                .getUser_token()+"&client_no="+Staticdata.static_userBean.getData().getAppuser()
                .getClient_no(),ShopCenterActivity.this,0);
    }
}
