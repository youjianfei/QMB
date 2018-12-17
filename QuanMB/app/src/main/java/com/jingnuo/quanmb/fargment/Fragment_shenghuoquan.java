package com.jingnuo.quanmb.fargment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.jingnuo.quanmb.Adapter.Adapter_FulisheList;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 飞 on 2018/12/17.
 */

public class Fragment_shenghuoquan extends Fragment {
    View view;
    TabLayout  tablayout_title;
    GridView listview;


    Adapter_FulisheList adapter_fulisheList;



    List<GuanggaoBean.DataBean>mdata;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    view=inflater.inflate(R.layout.fragment_shenghuoquan,null,false);
    initview();
        initdata();


        return view;
    }

    private void initview() {
        listview=view.findViewById(R.id.listview);
        tablayout_title=view.findViewById(R.id.tablayout_title);
        tablayout_title.addTab(tablayout_title.newTab().setText("限时秒杀").setTag("0"));
        tablayout_title.addTab(tablayout_title.newTab().setText("周边").setTag("1"));
        tablayout_title.addTab(tablayout_title.newTab().setText("新鲜事").setTag("2"));

    }
    private  void initdata(){
        mdata=new ArrayList<>();
        GuanggaoBean.DataBean bean=new GuanggaoBean.DataBean();
        bean.setActivity_url("dfd");
        bean.setId(0);
        bean.setImg_url("");
        mdata.add(bean);
        GuanggaoBean.DataBean bean2=new GuanggaoBean.DataBean();
        bean2.setActivity_url("dfd");
        bean2.setId(0);
        bean2.setImg_url("");
        mdata.add(bean2);
        adapter_fulisheList=new Adapter_FulisheList(mdata,getActivity());
        listview.setAdapter(adapter_fulisheList);
    }


}
