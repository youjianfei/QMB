package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_BarginmessageList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.BargainMessageListBean;
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
    BargainMessageListBean bargainMessageListBean;
    List<BargainMessageListBean.DataBean> mData;

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
        adapter_barginmessageList=new Adapter_BarginmessageList(mData,this);
        mList_view.setAdapter(adapter_barginmessageList);

        map_message=new HashMap();
        map_message.put("pageNo",page+"");
        map_message.put("type","2");
        map_message.put("receive_client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_message.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        LogUtils.LOG("ceshi","系统消息内容map"+map_message,"SystemMessageActivity");
        requestBarginMessage(map_message);


    }

    @Override
    protected void initListener() {
        //下拉  上拉 加载刷新
        mList_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                map_message.put("pageNo",page+"");
                requestBarginMessage(map_message);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                map_message.put("pageNo",page+"");
            }
        });
        mList_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mList_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_bargain=new Intent(BarginmessageListActivity.this,BargainActivity.class);
                intent_bargain.putExtra("binding_id",mData.get(position-1).getBinding_id()+"");
                intent_bargain.putExtra("receive_client_no",mData.get(position-1).getReceive_client_no()+"");
                intent_bargain.putExtra("send_client_no",mData.get(position-1).getSend_client_no()+"");
                startActivity(intent_bargain);
            }
        });
    }

    @Override
    protected void initView() {
        mList_view=findViewById(R.id.list_bargin);

    }

    void requestBarginMessage(Map map){
        LogUtils.LOG("ceshi",Urls.Baseurl_hu+Urls.pushMessage,"还价消息");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (mList_view.isRefreshing()) {
                    mList_view.onRefreshComplete();
                }
                bargainMessageListBean=new Gson().fromJson(respose,BargainMessageListBean.class);
                if(page==1){
                    mData.clear();
                    mData.addAll(bargainMessageListBean.getData());
                    adapter_barginmessageList.notifyDataSetChanged();

                }else {
                    mData.addAll(bargainMessageListBean.getData());
                    adapter_barginmessageList.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.pushMessage,BarginmessageListActivity.this,1,map);
    }
}
