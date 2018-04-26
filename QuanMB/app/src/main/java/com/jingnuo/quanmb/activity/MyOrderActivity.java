package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_myIssue;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.MyorderBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderActivity extends BaseActivityother {
    //控件
    TabLayout  mTablayout;
    PullToRefreshListView mListView;

    //对象
    Adapter_myIssue adapter_myIssue;
    MyorderBean myorderBean;

    //数据
    List<MyorderBean.DateBean>mData;
    Map map_myorder;

    int page=1;



    @Override
    public int setLayoutResID() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {//  code  01/08待帮助  02/03/05 进行中  06  已完成  07/13已关闭
        map_myorder=new HashMap();
        map_myorder.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_myorder.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_myorder.put("code","");
        mData=new ArrayList();
        adapter_myIssue=new Adapter_myIssue(mData,this);
        mListView.setAdapter(adapter_myIssue);

        request(map_myorder,page);
    }

    @Override
    protected void initListener() {
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.LOG("ceshi",tab.getTag()+"","MyOrderActivity");
                map_myorder.put("code",tab.getTag());
                request(map_myorder,page);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                request(map_myorder,page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            page++;
                request(map_myorder,page);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.LOG("ceshi","点击的条目+"+i,"MyOrderActivity");

                Intent intent_mytaskdetail=new Intent(MyOrderActivity.this,MytaskDetailActivity.class);
                startActivity(intent_mytaskdetail);

            }
        });
    }

    @Override
    protected void initView() {
        mTablayout=findViewById(R.id.tablayout);
        mTablayout.addTab(mTablayout.newTab().setText("全部").setTag(""));
        mTablayout.addTab(mTablayout.newTab().setText("待接单").setTag("01"));
        mTablayout.addTab(mTablayout.newTab().setText("进行中").setTag("02"));
        mTablayout.addTab(mTablayout.newTab().setText("已完成").setTag("06"));
        mTablayout.addTab(mTablayout.newTab().setText("已关闭").setTag("07"));

        mListView=findViewById(R.id.list_myorder);

    }
    void request(Map  map, final int page){
        map.put("pageNum",page+"");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (mListView.isRefreshing()) {
                    mListView.onRefreshComplete();
                }
                myorderBean=new Gson().fromJson(respose,MyorderBean.class);
                if(page==1){
                    mData.clear();
                    mData.addAll(myorderBean.getDate());
                    adapter_myIssue.notifyDataSetChanged();
                }else {
                    mData.addAll(myorderBean.getDate());
                    adapter_myIssue.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.myorderlist,MyOrderActivity.this,1,map);

    }
}
