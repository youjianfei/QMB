package com.jingnuo.quanmb.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingnuo.quanmb.Adapter.Adapter_classification_left;
import com.jingnuo.quanmb.Adapter.Adapter_classification_right;
import com.jingnuo.quanmb.activity.ShophallActivity;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.quanmb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_still  extends Fragment{
    View rootview;
    //控件
    EditText mEdit_serch;//搜索关键字
    TextView mtextview_classification_title;

    ListView mListview_left;
    MyGridView mGridview_right;

    //数据


    //对象
    Adapter_classification_left mAdapter_classification_left;

    Adapter_classification_right mAdapter_classification_right;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_still,container,false);
        initview();
        initdata();
        initlistenner();

        return rootview;
    }

    private void initlistenner() {
        mListview_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapter_classification_left.setSelectedPosition(i);
                mAdapter_classification_left.notifyDataSetInvalidated();
            }
        });
        mGridview_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent_shophalllist=new Intent(getActivity(), ShophallActivity.class);
                getActivity().startActivity(intent_shophalllist);
            }
        });
    }

    private void initdata() {
        List<String> mList_left=new ArrayList<>();
        mList_left.add("热门任务");
        mList_left.add("配送");
        mList_left.add("运输");
        mList_left.add("跑腿");
        mList_left.add("维修");
        mList_left.add("导游");
        mList_left.add("辅导");
        mList_left.add("托教");
        mAdapter_classification_left=new Adapter_classification_left(mList_left,getActivity());
        mListview_left.setAdapter(mAdapter_classification_left);


        List<String> mList_right=new ArrayList<>();
        mList_right.add("汽车美容");
        mList_right.add("汽车维修");
        mList_right.add("跑腿代办");
        mList_right.add("宠物");
        mList_right.add("漂流");
        mList_right.add("代驾");
        mList_right.add("租车位");
        mList_right.add("月嫂");
        mAdapter_classification_right=new Adapter_classification_right(mList_right,getActivity());
        mGridview_right.setAdapter(mAdapter_classification_right);
    }

    private void initview() {
        mtextview_classification_title=rootview.findViewById(R.id.textviw_classification);
        mListview_left=rootview.findViewById(R.id.gridview_left);
        mGridview_right=rootview.findViewById(R.id.gridview_right);
    }
}
