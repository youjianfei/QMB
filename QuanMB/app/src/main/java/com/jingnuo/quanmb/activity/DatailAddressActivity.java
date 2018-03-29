package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.jingnuo.quanmb.Adapter.Adapter_DatlyAddressList;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.quanmb.R;

import java.util.ArrayList;
import java.util.List;

public class DatailAddressActivity extends BaseActivityother {
    //控件
    Button mButton_addnewaddress;
    ListView mListview_address;

    Adapter_DatlyAddressList mAdapter_datilyaddress;

    //数据
    List<String> mData_address;




    @Override
    public int setLayoutResID() {
        return R.layout.activity_datail_address;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mData_address=new ArrayList<>();
        mData_address.add("常用地址：在神话故事中，女娲是创造了“人”的神，");
        mData_address.add("我们知道布局文件可以让我们很方便的对各个UI控件进行位置安排跟属性设置");
        mData_address.add("我们知道布局文件可以让我们很方便的对各个UI控件进行位置安排跟属性设置");
        mData_address.add("我们知道布局文件可以让我们很方便的对各个UI控件进行位置安排跟属性设置");
        mData_address.add("我们知道布局文件可以让我们很方便的对各个UI控件进行位置安排跟属性设置");
        mData_address.add("我们知道布局文件可以让我们很方便的对各个UI控件进行位置安排跟属性设置");
        mData_address.add("中华人民共和国河南省人民大会堂");
        mData_address.add("中华人民共和国河南省人民大会堂");
        mAdapter_datilyaddress=new Adapter_DatlyAddressList(mData_address,this);
        mListview_address.setAdapter(mAdapter_datilyaddress);
    }

    @Override
    protected void initListener() {
        mButton_addnewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_addnewaddress=new Intent(DatailAddressActivity.this,AddAddressActivity.class);
                startActivity(intent_addnewaddress);
            }
        });
    }

    @Override
    protected void initView() {
        mButton_addnewaddress=findViewById(R.id.button_addnewaddress);
        mListview_address=findViewById(R.id.list_datiladdress);

    }

}
