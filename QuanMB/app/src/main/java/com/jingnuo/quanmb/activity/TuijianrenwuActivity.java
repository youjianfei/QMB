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
import com.jingnuo.quanmb.Adapter.Adapter_DealmessageList;
import com.jingnuo.quanmb.Adapter.Adapter_TuijianrenwumessageList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.DealMessageBean;
import com.jingnuo.quanmb.entityclass.Message_tujianrenwu;
import com.jingnuo.quanmb.entityclass.TuiguangbiTaocanBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuijianrenwuActivity extends BaseActivityother {
    //控件
    PullToRefreshListView mListview;

    Message_tujianrenwu message_tujianrenwuBean;

    List<Message_tujianrenwu.DataBean> mData;
    Adapter_TuijianrenwumessageList adapter_dealmessageList;
    Map map_message;
    int page=1;
    int  type=0;//  //1帮手  2  商户

    @Override
    public int setLayoutResID() {
        return R.layout.activity_tuijianrenwu;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mData=new ArrayList<>();
        adapter_dealmessageList=new Adapter_TuijianrenwumessageList(mData,this);
        mListview.setAdapter(adapter_dealmessageList);
        map_message=new HashMap();
        map_message.put("pageNo",page+"");
        map_message.put("type","4");
        if(Staticdata.static_userBean.getData()==null){
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        map_message.put("receive_client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_message.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        LogUtils.LOG("ceshi","系统消息内容map"+map_message,"DealMessageActivity");
        requestTuijianrenwumessage(map_message);
    }

    @Override
    protected void initListener() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi","点击了"+position,"交易信息");
                    String binding_id=mData.get(position-1).getId()+"";
                    Intent intent=new Intent(TuijianrenwuActivity.this,TaskDetailsActivity.class);
                    intent.putExtra("id",binding_id);
                    startActivity(intent);
            }
        });
//下拉  上拉 加载刷新
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                map_message.put("pageNo",page+"");
                requestTuijianrenwumessage(map_message);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                map_message.put("pageNo",page+"");
                requestTuijianrenwumessage(map_message);
            }
        });
    }

    @Override
    protected void initView() {
        mListview=findViewById(R.id.list_tuijianrenwu);
    }
    void requestTuijianrenwumessage(Map map){
        LogUtils.LOG("ceshi","交易消息内容URL"+ Urls.Baseurl_hu+Urls.pushMessage,"TuijianrenwuActivity");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (mListview.isRefreshing()) {
                    mListview.onRefreshComplete();
                }
                LogUtils.LOG("ceshi","交易消息内容"+respose,"DealMessageActivity");
                message_tujianrenwuBean=new Gson().fromJson(respose,Message_tujianrenwu.class);
                if(page==1){
                    mData.clear();
                    if(message_tujianrenwuBean.getData()!=null){
                        mData.addAll(message_tujianrenwuBean.getData());
                        adapter_dealmessageList.notifyDataSetChanged();
                    }
                }else {
                    if(message_tujianrenwuBean.getData()!=null){
                        mData.addAll(message_tujianrenwuBean.getData());
                        adapter_dealmessageList.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(int error) {
                if (mListview.isRefreshing()) {
                    mListview.onRefreshComplete();
                }
            }
        }).postHttp(Urls.Baseurl_hu+Urls.pushMessage,TuijianrenwuActivity.this,1,map);



    }
}
