package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_mystill;
import com.jingnuo.quanmb.Interface.InterfaceAdapterSuccess;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.MySkillBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.List;

public class MySkillActivity extends BaseActivityother {
    //控件
    PullToRefreshListView  mListview;
    TabLayout mTablayout;

    //数据
    int  page=1;
    int type=0;   //1 帮手  2商家
    String release_status="1";//服务状态

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
        adapter_mystill=new Adapter_mystill(type,mData, this, new InterfaceAdapterSuccess() {
            @Override
            public void onResult(boolean result) {
                request(release_status,1);
            }
        });
        mListview.setAdapter(adapter_mystill);
        request(1+"",1);
    }



    @Override
    protected void initListener() {
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                request(release_status,page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                request(release_status,page);
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
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                release_status= (String) tab.getTag();
                page=1;
                request(release_status,page);
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
        mListview=findViewById(R.id.list_myskill);
        mTablayout=findViewById(R.id.tablayout);
        mTablayout.addTab(mTablayout.newTab().setText("发布中").setTag("1"));
        mTablayout.addTab(mTablayout.newTab().setText("已关闭").setTag("2"));
    }
    private void request(String release_status, final int page) {
        String URL="";
        if(type==1){
            URL= Urls.Baseurl+Urls.helpskill+ Staticdata.static_userBean.getData().getUser_token()+"&client_no="+
                    Staticdata.static_userBean.getData().getAppuser().getClient_no()+"&curPageNo="+page+"&release_status="+release_status;
        }else {
            URL= Urls.Baseurl+Urls.shopkill+ Staticdata.static_userBean.getData().getUser_token()+"&client_no="+
                    Staticdata.static_userBean.getData().getAppuser().getClient_no()+"&curPageNo="+page+"&release_status="+release_status;
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
