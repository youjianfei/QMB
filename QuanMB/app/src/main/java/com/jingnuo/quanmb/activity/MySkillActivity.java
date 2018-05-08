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
import com.jingnuo.quanmb.Adapter.Adapter_mystill;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.MySkillBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.List;

public class MySkillActivity extends BaseActivityother {
    //控件
    PullToRefreshListView  mListview;


    //数据
    int  page=1;
    int type=0;   //1 帮手  2商家

    List<MySkillBean.DataBean.ListBean>mData;
    MySkillBean mySkillBean;
    Adapter_mystill adapter_mystill;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_my_skill;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        type=intent.getIntExtra("type",0);
        mData=new ArrayList();
        adapter_mystill=new Adapter_mystill(mData,this);
        mListview.setAdapter(adapter_mystill);
        request();


    }



    @Override
    protected void initListener() {
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                request();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                request();
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi","点击了第"+position,"ssfd");
                Intent intent=new Intent(MySkillActivity.this,SkillDetailActivity.class);
                intent.putExtra("id",mData.get(position-1).getRelease_specialty_id()+"");
                intent.putExtra("role",type+"");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initView() {
        mListview=findViewById(R.id.list_myskill);
    }
    private void request() {
        String URL="";
        if(type==1){
            URL= Urls.Baseurl+Urls.helpskill+ Staticdata.static_userBean.getData().getUser_token()+"&client_no="+
                    Staticdata.static_userBean.getData().getAppuser().getClient_no()+"&curPageNo="+page;
        }else {
            URL= Urls.Baseurl+Urls.shopkill+ Staticdata.static_userBean.getData().getUser_token()+"&client_no="+
                    Staticdata.static_userBean.getData().getAppuser().getClient_no()+"&curPageNo="+page;
        }

        LogUtils.LOG("ceshi",URL,"发布服务列表网址");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"发布服务列表网址");
                if (mListview.isRefreshing()) {
                    mListview.onRefreshComplete();
                }
                mySkillBean=new Gson().fromJson(respose,MySkillBean.class);
                if(page==1){
                    mData.clear();
                    if(mySkillBean.getData()!=null){
                        mData.addAll(mySkillBean.getData().getList());
                    }
                    adapter_mystill.notifyDataSetChanged();
                }else {
                    if(mySkillBean.getData()!=null){
                        mData.addAll(mySkillBean.getData().getList());
                    }
                    adapter_mystill.notifyDataSetChanged();
                }
            }
            @Override
            public void onError(int error) {

            }
        }).Http(URL,MySkillActivity.this,0);
    }
}
