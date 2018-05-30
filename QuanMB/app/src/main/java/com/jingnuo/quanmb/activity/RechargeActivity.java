package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.ToastUtils;

public class RechargeActivity extends BaseActivityother {

    //控件
    EditText mEditview_amount;
    RelativeLayout mRelativelayout_wechat;
    RelativeLayout mRelayout_zhifubao;
    ImageView mImageview_weixin;
    ImageView mImageview_zhidubao;
    Button mbutton_queren;

    //数据
    int pay=1;   //1  wechat    2  zhifubao

    String amount="";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mImageview_weixin.setSelected(true);

    }

    @Override
    protected void initListener() {
        mRelativelayout_wechat.setOnClickListener(this);
        mRelayout_zhifubao.setOnClickListener(this);
        mbutton_queren.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mEditview_amount=findViewById(R.id.edit_rechangeAmount);
        mRelativelayout_wechat=findViewById(R.id.relayout_wechat);
        mRelayout_zhifubao=findViewById(R.id.relayout_zhifubao);
        mImageview_weixin=findViewById(R.id.image_wechat);
        mImageview_zhidubao=findViewById(R.id.image_zhifubao);
        mbutton_queren=findViewById(R.id.button_queren);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.relayout_wechat:
                mImageview_weixin.setSelected(true);
                mImageview_zhidubao.setSelected(false);
                pay=1;

                break;
            case R.id.relayout_zhifubao:
                mImageview_weixin.setSelected(false);
                mImageview_zhidubao.setSelected(true);
                pay=2;
                break;
            case R.id.button_queren:
                amount=mEditview_amount.getText()+"";
                if(amount.equals("")||amount.equals(".")||amount.startsWith("00")){
                    ToastUtils.showToast(this,"请输入正确金额");
                }else {

                }
                break;

        }
    }
}
