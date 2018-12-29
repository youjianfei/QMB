package com.jingnuo.quanmb.fargment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_FulisheList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.ZixunKefuWebActivity;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.JifenBean;
import com.jingnuo.quanmb.entityclass.ShenghuoquanBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 飞 on 2018/12/17.
 */

public class Fragment_shenghuoquan extends Fragment {
    View view;
    TabLayout  tablayout_title;
    CardView cardview_jifen;
    PullToRefreshListView listview;
    PullToRefreshGridView gridview;
    TextView textview_jifenCount;
//    CountDownUtil countDownUtil;

    Adapter_FulisheList adapter_fulisheList;

    List<ShenghuoquanBean.DataBean>mdata;

    String type="1";

    int page=1;

    ShenghuoquanBean shenghuoquanBean;
    JifenBean jifenBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view=inflater.inflate(R.layout.fragment_shenghuoquan,null,false);
        initview();
        initdata();
        initlistenner();
        requestjifen();

        return view;
    }

    private void initlistenner() {
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                request(type,page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                request(type,page);
            }
        });
      gridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
          @Override
          public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
              page=1;
              request(type,page);
          }

          @Override
          public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
              page++;
              request(type,page);
          }
      });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {//点击列表
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi",position-1+"oooooo","点击listview");
                if(mdata.get(position-1).getClick_url()!=null&&!mdata.get(position-1).getClick_url().equals("")){
                    Intent intent=new Intent(getActivity(), ZixunKefuWebActivity.class);
                    intent.putExtra("webtitle", "");
                    intent.putExtra("type", "生活圈");
                    intent.putExtra("URL", mdata.get(position-1).getClick_url());
                    startActivity(intent);
                    LogUtils.LOG("ceshi",position-1+"oooooo","点击listview");
                }
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi",position+"oooooo","点击gridview");
                if(mdata.get(position).getClick_url()!=null&&!mdata.get(position).getClick_url().equals("")){
                    Intent intent=new Intent(getActivity(), ZixunKefuWebActivity.class);
                    intent.putExtra("webtitle", "");
                    intent.putExtra("type", "生活圈");
                    intent.putExtra("URL", mdata.get(position).getClick_url());
                    startActivity(intent);
                    LogUtils.LOG("ceshi",position+"oooooo","点击gridview");


                }
            }
        });
        tablayout_title.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getTag().toString()){
                    case "1"://限时秒杀
                        type="1";
                        request(type,1);
                        cardview_jifen.setVisibility(View.GONE);
                        break;
                    case "2"://周边
                        type="2";
                        request(type,1);
                        cardview_jifen.setVisibility(View.VISIBLE);
                        break;
                    case "3"://新鲜事
                        type="3";
                        request(type,1);
                        cardview_jifen.setVisibility(View.GONE);

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initview() {
        textview_jifenCount=view.findViewById(R.id.textview_jifenCount);
        listview=view.findViewById(R.id.listview);
        gridview=view.findViewById(R.id.gridview);
        cardview_jifen=view.findViewById(R.id.cardview_jifen);
        tablayout_title=view.findViewById(R.id.tablayout_title);
        tablayout_title.addTab(tablayout_title.newTab().setText("限时秒杀").setTag("1"));
        tablayout_title.addTab(tablayout_title.newTab().setText("周边").setTag("2"));
        tablayout_title.addTab(tablayout_title.newTab().setText("新鲜事").setTag("3"));
        listview.setMode(PullToRefreshBase.Mode.BOTH);//设置你需要的模式
        gridview.setMode(PullToRefreshBase.Mode.BOTH);//设置你需要的模式

    }
    private  void initdata(){
        request(type,1);
        mdata=new ArrayList<>();
    }
    private void request(final String   type, final int  papageNum){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (gridview.isRefreshing()) {
                    gridview.onRefreshComplete();
                }
                if (listview.isRefreshing()) {
                    listview.onRefreshComplete();
                }
                shenghuoquanBean= new Gson().fromJson(respose,ShenghuoquanBean.class);
                if(type.equals("2")){
                    gridview.setVisibility(View.VISIBLE);
                    listview.setVisibility(View.GONE);
                    LogUtils.LOG("ceshi",respose,"生活圈");
                    if(papageNum==1){
                        if(shenghuoquanBean.getData()!=null){
                            adapter_fulisheList=null;
                            adapter_fulisheList=new Adapter_FulisheList(mdata,getActivity(),type);
                            gridview.setAdapter(adapter_fulisheList);
                            mdata.clear();
                            mdata.addAll(new Gson().fromJson(respose,ShenghuoquanBean.class).getData());
                            adapter_fulisheList.notifyDataSetInvalidated();
                        }

                    }else {
                        if(shenghuoquanBean.getData()!=null){
                            mdata.addAll(new Gson().fromJson(respose,ShenghuoquanBean.class).getData());
                            adapter_fulisheList.notifyDataSetChanged();
                        }
                    }

                }else {
                    LogUtils.LOG("ceshi",respose,"生活圈");
                    gridview.setVisibility(View.GONE);
                    listview.setVisibility(View.VISIBLE);
                    if(papageNum==1){
                        if(shenghuoquanBean.getData()!=null){
                            adapter_fulisheList=null;
                            adapter_fulisheList=new Adapter_FulisheList(mdata,getActivity(),type);
                            listview.setAdapter(adapter_fulisheList);
                            mdata.clear();
                            mdata.addAll(new Gson().fromJson(respose,ShenghuoquanBean.class).getData());
                            adapter_fulisheList.notifyDataSetInvalidated();
                        }

                    }else {
                        if(shenghuoquanBean.getData()!=null){
                            mdata.addAll(new Gson().fromJson(respose,ShenghuoquanBean.class).getData());
                            adapter_fulisheList.notifyDataSetChanged();
                        }

                    }

                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.shenghuoquan+type+"&pageNum="+papageNum,getActivity(),0);

    }
     void requestjifen(){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"生活圈  积分数");
                jifenBean=new Gson().fromJson(respose,JifenBean.class);
                textview_jifenCount.setText(jifenBean.getData().getIntegral()+"");
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.jifencount+ Staticdata.static_userBean.getData().getUser_token(),getActivity(),0);

     }


    @Override
    public void onPause() {
        super.onPause();
//
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        type="1";
        if(adapter_fulisheList!=null){
            adapter_fulisheList.cancelAllTimers();
        }
        LogUtils.LOG("ceshi","onDestroy()","生活圈");

    }
}
