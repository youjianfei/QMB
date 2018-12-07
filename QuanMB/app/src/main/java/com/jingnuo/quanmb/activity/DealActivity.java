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
import com.jingnuo.quanmb.Adapter.Adapter_DealmessageList;
import com.jingnuo.quanmb.Adapter.Adapter_SystemmessageList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.DealMessageBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealActivity extends BaseActivityother {


    //控件
    TextView textview_maintitle;
    PullToRefreshListView mListview;
    ImageView mImage_view_empty;
    //数据
    Map map_message;

    DealMessageBean dealMessageBean;
    List<DealMessageBean.DataBean> mData;

    int page=1;
    int  type=0;//  //1帮手  2  商户

    //对象
    Adapter_DealmessageList adapter_dealmessageList;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_deal;
    }

    @Override
    protected void setData() {
        if(Staticdata.static_userBean.getData().getHelper_status()==1){
            type=1;//帮手身份
        }
        if(Staticdata.static_userBean.getData().getBusiness_status()==1){
            type=2;//商户身份
        }


    }

    @Override
    protected void initData() {
        mData=new ArrayList<>();
        adapter_dealmessageList=new Adapter_DealmessageList(mData,this);
        mListview.setAdapter(adapter_dealmessageList);

        map_message=new HashMap();
        map_message.put("pageNo",page+"");
        map_message.put("type","3");
        if(Staticdata.static_userBean.getData()==null){
            Intent intent=new Intent(this,LoginActivityPhone.class);
            startActivity(intent);
            finish();
            return;
        }
        map_message.put("receive_client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_message.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        LogUtils.LOG("ceshi","系统消息内容map"+map_message,"DealMessageActivity");
        requestDealmessage(map_message);
    }

    @Override
    protected void initListener() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi","点击了"+position,"交易信息");
                String binding_id=mData.get(position-1).getBinding_id();

                if(binding_id.contains("QMB")){
                    Intent intent=new Intent(DealActivity.this,HelperOrderActivity.class);
                    intent.putExtra("type",type);
                    intent.putExtra("order_no",binding_id);
                    startActivity(intent);
                }else {
                    if(mData.get(position-1).getTask_Status_code().equals("05")){
                        Staticdata.ispipei = true;
                        Intent intent_mytaskdetail=new Intent(DealActivity.this,PayActivity.class);
                        intent_mytaskdetail.putExtra("title", "商户任务付款");
                        intent_mytaskdetail.putExtra("order_no", mData.get(position-1).getOrder_no());
                        intent_mytaskdetail.putExtra("taskid", mData.get(position-1).getBinding_id()+"");
                        startActivity(intent_mytaskdetail);
                    }else {
                        Intent intent=new Intent(DealActivity.this,MytaskDetailActivity.class);
                        intent.putExtra("id",binding_id);
                        startActivity(intent);

                    }

                }
            }
        });


        //下拉  上拉 加载刷新
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                map_message.put("pageNo",page+"");
                requestDealmessage(map_message);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                map_message.put("pageNo",page+"");
                requestDealmessage(map_message);
            }
        });

    }

    @Override
    protected void initView() {
        textview_maintitle=findViewById(R.id.textview_maintitle);
        textview_maintitle.setText("交易提醒");
        mListview=findViewById(R.id.list_dealmessage);
        mImage_view_empty=findViewById(R.id.image_empty);
    }


    void requestDealmessage(Map map){
        LogUtils.LOG("ceshi","交易消息内容URL"+Urls.Baseurl_hu+Urls.pushMessage,"DealMessageActivity");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (mListview.isRefreshing()) {
                    mListview.onRefreshComplete();
                }
                LogUtils.LOG("ceshi","交易消息内容"+respose,"DealMessageActivity");
                dealMessageBean=new Gson().fromJson(respose,DealMessageBean.class);
                if(page==1){
                    mData.clear();
                    if(dealMessageBean.getData()!=null){
                        mData.addAll(dealMessageBean.getData());
                        adapter_dealmessageList.notifyDataSetChanged();
                        mImage_view_empty.setVisibility(mData.size()==0? View.VISIBLE:View.GONE);
                    }
                }else {
                    if(dealMessageBean.getData()!=null){
                        mData.addAll(dealMessageBean.getData());
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
        }).postHttp(Urls.Baseurl_hu+Urls.pushMessage,DealActivity.this,1,map);



    }
}
