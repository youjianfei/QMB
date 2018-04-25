package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_BarginmessageList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarginmessageListActivity extends BaseActivityother {

    //控件
    PullToRefreshListView mList_view;
    //数据
    Map map_message;
    int page=1;

    List<String> mData;

    //对象
    Adapter_BarginmessageList adapter_barginmessageList;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_barginmessage_list;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mData=new ArrayList<>();
        mData.add("sdfdsfasf");
        mData.add("防守打法");
        mData.add("第三方");
        mData.add("结婚后");
        adapter_barginmessageList=new Adapter_BarginmessageList(mData,this);
        mList_view.setAdapter(adapter_barginmessageList);



        map_message=new HashMap();
        map_message.put("pageNo",page+"");
        map_message.put("type","2");
        map_message.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_message.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        LogUtils.LOG("ceshi","系统消息内容map"+map_message,"SystemMessageActivity");
        requestBarginMessage(map_message);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mList_view=findViewById(R.id.list_bargin);

    }

    void requestBarginMessage(Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {

                LogUtils.LOG("ceshi","系统消息内容"+respose,"SystemMessageActivity");

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.pushMessage,BarginmessageListActivity.this,1,map);
    }
}
