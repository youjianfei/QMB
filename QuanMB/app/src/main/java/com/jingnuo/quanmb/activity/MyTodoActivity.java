package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_mytodo;
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

public class MyTodoActivity extends BaseActivityother {

    //控件
    TabLayout mTablayout;
    PullToRefreshListView mListView;


    Adapter_mytodo adapter;

    //数据
    int  page=1;
    int type=0;

    List<String>  mdata;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_my_todo;
    }

    @Override
    protected void setData() {


    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        type=intent.getIntExtra("type",0);
        mdata=new ArrayList<>();
        mdata.add("sdfsdafasdf");
        mdata.add("sdfsdafasdf");
        mdata.add("sdfsdafasdf");
        mdata.add("sdfsdafasdf");
        mdata.add("sdfsdafasdf");
        mdata.add("sdfsdafasdf");
        request("",page);
        adapter=new Adapter_mytodo(mdata,this);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                request("",page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                request("",page);
            }
        });
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.LOG("ceshi",tab.getTag()+"","mytodo");

                String state= (String) tab.getTag();
                page=1;
                request(state,page);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initView() {
        mTablayout=findViewById(R.id.tablayout);
        mTablayout.addTab(mTablayout.newTab().setText("全部").setTag(""));
        mTablayout.addTab(mTablayout.newTab().setText("还价中").setTag(""));
        mTablayout.addTab(mTablayout.newTab().setText("进行中").setTag("05,"));
        mTablayout.addTab(mTablayout.newTab().setText("已完成").setTag("00,"));
        mTablayout.addTab(mTablayout.newTab().setText("已关闭").setTag("01,02"));

        mListView=findViewById(R.id.list_myorder);
    }
    void request(String state,int  page){
        LogUtils.LOG("ceshi",Urls.Baseurl+Urls.shoporder,"MyTodoActivity的URl");
        //todo  区分 帮手和商户的网址接口
        String URL="";
        if(type==1){
            URL=Urls.Baseurl+Urls.helporder+Staticdata.static_userBean.getData().getUser_token()+"&client_no="+
                    Staticdata.static_userBean.getData().getAppuser().getClient_no()+"order_status="+state+
                    "&curPageNo="+page;
        }else {
             URL=Urls.Baseurl+Urls.shoporder+Staticdata.static_userBean.getData().getUser_token()+"&client_no="+
                    Staticdata.static_userBean.getData().getAppuser().getClient_no()+"order_status="+state+
                    "&curPageNo="+page;
        }

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"MyTodoActivity");

            }

            @Override
            public void onError(int error) {

            }
        }).Http(URL,MyTodoActivity.this,0);


    }
}
