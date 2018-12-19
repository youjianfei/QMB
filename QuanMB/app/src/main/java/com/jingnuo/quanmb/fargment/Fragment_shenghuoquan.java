package com.jingnuo.quanmb.fargment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_FulisheList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.ZixunKefuWebActivity;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.ShenghuoquanBean;
import com.jingnuo.quanmb.utils.CountDownUtil;
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
    MyListView listview;
    MyGridView gridview;
//    CountDownUtil countDownUtil;

    Adapter_FulisheList adapter_fulisheList;

    List<ShenghuoquanBean.DataBean>mdata;

    String type="1";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view=inflater.inflate(R.layout.fragment_shenghuoquan,null,false);
        initview();
        initdata();
        initlistenner();


        return view;
    }

    private void initlistenner() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {//点击列表
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mdata.get(position).getClick_url()!=null&&!mdata.get(position).getClick_url().equals("")&&type.equals("3")){
                    Intent intent=new Intent(getActivity(), ZixunKefuWebActivity.class);
                    intent.putExtra("webtitle", "");
                    intent.putExtra("type", "生活圈");
                    intent.putExtra("URL", mdata.get(position).getClick_url());
                    startActivity(intent);
                }
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mdata.get(position).getClick_url()!=null&&!mdata.get(position).getClick_url().equals("")){
                    Intent intent=new Intent(getActivity(), ZixunKefuWebActivity.class);
                    intent.putExtra("webtitle", "");
                    intent.putExtra("type", "生活圈");
                    intent.putExtra("URL", mdata.get(position).getClick_url());
                    startActivity(intent);
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
                        break;
                    case "2"://周边
                        type="2";
                        request(type,1);

                        break;
                    case "3"://新鲜事
                        type="3";
                        request(type,1);

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
        listview=view.findViewById(R.id.listview);
        gridview=view.findViewById(R.id.gridview);
        tablayout_title=view.findViewById(R.id.tablayout_title);
        tablayout_title.addTab(tablayout_title.newTab().setText("限时秒杀").setTag("1"));
        tablayout_title.addTab(tablayout_title.newTab().setText("周边").setTag("2"));
        tablayout_title.addTab(tablayout_title.newTab().setText("新鲜事").setTag("3"));

    }
    private  void initdata(){
        request(type,1);
        mdata=new ArrayList<>();
    }
    private void request(final String   type, int  papageNum){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if(type.equals("2")){
                    gridview.setVisibility(View.VISIBLE);
                    listview.setVisibility(View.GONE);
                    LogUtils.LOG("ceshi",respose,"生活圈");
                    adapter_fulisheList=null;
                    adapter_fulisheList=new Adapter_FulisheList(mdata,getActivity(),type);
                    gridview.setAdapter(adapter_fulisheList);
                    mdata.clear();
                    mdata.addAll(new Gson().fromJson(respose,ShenghuoquanBean.class).getData());
                    adapter_fulisheList.notifyDataSetInvalidated();
                }else {
                    LogUtils.LOG("ceshi",respose,"生活圈");
                    gridview.setVisibility(View.GONE);
                    listview.setVisibility(View.VISIBLE);
                    adapter_fulisheList=null;
                    adapter_fulisheList=new Adapter_FulisheList(mdata,getActivity(),type);
                    listview.setAdapter(adapter_fulisheList);
                    mdata.clear();
                    mdata.addAll(new Gson().fromJson(respose,ShenghuoquanBean.class).getData());
                    adapter_fulisheList.notifyDataSetInvalidated();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.shenghuoquan+type+"&pageNum="+papageNum,getActivity(),0);

    }

    @Override
    public void onPause() {
        super.onPause();
        type="1";
        adapter_fulisheList.cancelAllTimers();
    }
}
