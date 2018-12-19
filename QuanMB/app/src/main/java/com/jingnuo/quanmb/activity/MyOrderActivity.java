package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_myIssue;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.MyorderBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderActivity extends BaseActivityother {
    //控件
    TextView textview_maintitle;
    TabLayout  mTablayout;
    PullToRefreshListView mListView;
    ImageView imageview_empty;

    //对象
    Adapter_myIssue adapter_myIssue;
    MyorderBean myorderBean;

    //数据
    List<MyorderBean.DataBean>mData;
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
    protected void initData() {//  code  01,08待帮助  02,03,05 进行中  06  已完成  07,13已关闭
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
                page=1;
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



                if(mData.get(i-1).getStatus_name().equals("待确认")&&mData.get(i-1).getApp_type().equals("1")){
                    Staticdata.ispipei = true;
                    Intent intent_mytaskdetail=new Intent(MyOrderActivity.this,PayActivity.class);
                    intent_mytaskdetail.putExtra("title", "商户任务付款");
                    intent_mytaskdetail.putExtra("order_no", mData.get(i-1).getOrder_no());
                    intent_mytaskdetail.putExtra("taskid", mData.get(i-1).getTask_id()+"");
                    startActivity(intent_mytaskdetail);
                }else {
                    Intent intent_mytaskdetail=new Intent(MyOrderActivity.this,MytaskDetailActivity.class);
                    intent_mytaskdetail.putExtra("id",mData.get(i-1).getTask_id()+"");
                    startActivity(intent_mytaskdetail);
                }


            }
        });
    }

    @Override
    protected void initView() {
        textview_maintitle=findViewById(R.id.textview_maintitle);
        textview_maintitle.setText("我的发布");
        mTablayout=findViewById(R.id.tablayout);
        mTablayout.addTab(mTablayout.newTab().setText("全部").setTag(""));
//        mTablayout.addTab(mTablayout.newTab().setText("待接单").setTag("01,08"));
        mTablayout.addTab(mTablayout.newTab().setText("进行中").setTag("02,03,05"));
        mTablayout.addTab(mTablayout.newTab().setText("已完成").setTag("06"));
        mTablayout.addTab(mTablayout.newTab().setText("已关闭").setTag("07,13,09"));

        mListView=findViewById(R.id.list_myorder);
        imageview_empty=findViewById(R.id.imageview_empty);

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
                    if(myorderBean.getData()!=null){
                        mData.addAll(myorderBean.getData());
                        adapter_myIssue.notifyDataSetChanged();

                    }
                }else {
                    if (myorderBean.getData()!=null){
                        mData.addAll(myorderBean.getData());
                        adapter_myIssue.notifyDataSetChanged();
                    }
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
        }).postHttp(Urls.Baseurl_cui+Urls.myorderlist,MyOrderActivity.this,1,map);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent intent=new Intent(MyOrderActivity.this,IssueTaskActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.LOG("ceshi","onRestart","MyOrderActivity");
        page=1;
        request(map_myorder,page);

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(MyOrderActivity.this,IssueTaskActivity.class);
        startActivity(intent);
        finish();
    }
}
