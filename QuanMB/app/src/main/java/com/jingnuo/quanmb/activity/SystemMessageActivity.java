package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_SystemmessageList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.SystemmessageBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemMessageActivity extends BaseActivityother {

    //控件
    TextView textview_maintitle;
    PullToRefreshListView  mListview;
    ImageView mImage_view_empty;

    //数据
    Map map_message;

    List<SystemmessageBean.DataBean>mData;

    int page=1;

    //对象
    Adapter_SystemmessageList adapter_systemmessageList;
    SystemmessageBean systemmessageBean;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_system_message;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mData=new ArrayList<>();
        adapter_systemmessageList=new Adapter_SystemmessageList(mData,this);
        mListview.setAdapter(adapter_systemmessageList);

        map_message=new HashMap();
        map_message.put("pageNo",page+"");
        map_message.put("type","1");
        if(Staticdata.isLogin){
            map_message.put("receive_client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());//获取系统消息不用客户号
            map_message.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        }
        LogUtils.LOG("ceshi","系统消息内容map"+map_message,"SystemMessageActivity");
        requestSystermyMessage(map_message);
    }

    @Override
    protected void initListener() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_messagedetail=new Intent(SystemMessageActivity.this,SystemmessageDetailActivity.class);
                intent_messagedetail.putExtra("title",mData.get(position-1).getTitle());
                intent_messagedetail.putExtra("content",mData.get(position-1).getContent());
                intent_messagedetail.putExtra("time",mData.get(position-1).getCreateDate());
                intent_messagedetail.putExtra("url",mData.get(position-1).getClick_url());
                startActivity(intent_messagedetail);
            }
        });
        //下拉  上拉 加载刷新
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                map_message.put("pageNo",page+"");
                requestSystermyMessage(map_message);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                map_message.put("pageNo",page+"");
                requestSystermyMessage(map_message);
            }
        });
    }

    @Override
    protected void initView() {
        textview_maintitle=findViewById(R.id.textview_maintitle);
        textview_maintitle.setText("系统消息");
        mListview=findViewById(R.id.list_systerme);
        mImage_view_empty=findViewById(R.id.image_empty);
    }

    void requestSystermyMessage(Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (mListview.isRefreshing()) {
                    mListview.onRefreshComplete();
                }
                LogUtils.LOG("ceshi","系统消息内容"+respose,"SystemMessageActivity");

                systemmessageBean=new Gson().fromJson(respose,SystemmessageBean.class);
                if(page==1){
                    mData.clear();
                    mData.addAll(systemmessageBean.getData());
                    adapter_systemmessageList.notifyDataSetChanged();
                    mImage_view_empty.setVisibility(mData.size()==0? View.VISIBLE:View.GONE);
                }else {
                    mData.addAll(systemmessageBean.getData());
                    adapter_systemmessageList.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.pushMessage,SystemMessageActivity.this,1,map);



    }
}
