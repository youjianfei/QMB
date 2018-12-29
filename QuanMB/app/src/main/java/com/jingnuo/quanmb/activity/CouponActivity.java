package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Coupon;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.CouponBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CouponActivity extends BaseActivityother {

    TextView     text_title;
    ImageView imageview_empty;
    ListView mylistview_coupon;


    String title="";
    String GetURL="";
    CouponBean couponBean;
    List<CouponBean.DataBean> mData;

    String  tasktyoeID="";
    String  business_no="";
    String  order_no="";

    int  selectposition=0;

    Adapter_Coupon  adapter_coupon;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void setData() {

        if(title.equals("优惠券")){
            GetURL= Urls.Baseurl_cui+Urls.myCoupon+ Staticdata.static_userBean.getData().getUser_token();
        }else if(title.equals("选择优惠券")){
            GetURL= Urls.Baseurl_cui+Urls.useableCoupon+ Staticdata.static_userBean.getData().getUser_token()+"&task_type_id="
                    +tasktyoeID+"&business_no="+business_no+"&order_no="+order_no;
        }

        request(GetURL);
    }
    void request(String  URL){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi","优惠券："+respose,"优惠券");
                couponBean=new Gson().fromJson(respose,CouponBean.class);
                mData.clear();
                if(couponBean.getData().size()>0){
                    mData.addAll(couponBean.getData());
//                    adapter_coupon.notifyDataSetChanged();
                    adapter_coupon.setSelectedPosition(selectposition);
                    adapter_coupon.notifyDataSetInvalidated();
                }
                if(mData.size()==0){
                    imageview_empty.setVisibility(View.VISIBLE);
                }else {
                    imageview_empty.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(int error) {

            }
        }).Http(URL,CouponActivity.this,0);

    }

    @Override
    protected void initData() {
        title=getIntent().getStringExtra("type");
        tasktyoeID=getIntent().getStringExtra("task_type_id");
        business_no=getIntent().getStringExtra("business_no");
        order_no=getIntent().getStringExtra("order_no");
        selectposition=getIntent().getIntExtra("selectposition",0);
        text_title.setText(title);
        mData=new ArrayList<>();
        adapter_coupon=new Adapter_Coupon(mData,this,title);
        mylistview_coupon.setAdapter(adapter_coupon);
    }

    @Override
    protected void initListener() {
        mylistview_coupon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi","优惠券ID"+mData.get(position).getCoupon_id()+"position"+position,"选择优惠券");
                if(title.equals("优惠券")){
                    Intent  intent=new Intent(CouponActivity.this,IssueTaskActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    if(mData.get(position).getUsable()==0){
                        ToastUtils.showToast(CouponActivity.this,"该优惠券不可用");
                    }else {
                        Intent result = new Intent();
                        result.putExtra("position", position);
                        result.putExtra("amount", mData.get(position).getAmount());
                        result.putExtra("coupon_code", mData.get(position).getCoupon_code());
                        result.putExtra("coupon_id", mData.get(position).getCoupon_id()+"");
                        setResult(1637, result);
                        finish();
                    }

                }
            }
        });
    }

    @Override
    protected void initView() {
        text_title=findViewById(R.id.textview_maintitle);
        mylistview_coupon=findViewById(R.id.Mylistview_coupon);
        imageview_empty=findViewById(R.id.imageview_empty);
    }
}
