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

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_SquareList;
import com.jingnuo.quanmb.activity.TaskDetailsActivity;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_square extends Fragment{

    View  rootview;
    //控件
    EditText mEdit_serchSquare;
    PullToRefreshListView  mListview_square;

    Adapter_SquareList mAdapter_SquareList;

    //数据
    List<String> mDate_square;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_square, container, false);
        initview();
        initdata();
        setview();
        initlistenner();

        return rootview;
    }
    Intent intend_taskdrtails;
    private void initlistenner() {
        mListview_square.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.LOG("ceshi",""+i,"fragmentsquare");
                 intend_taskdrtails=new Intent(getActivity(), TaskDetailsActivity.class);
                getActivity().startActivity(intend_taskdrtails);

            }
        });
    }

    private void setview() {

    }

    private void initdata() {
        mDate_square=new ArrayList<>();
        mDate_square.add("全民帮,这里是发布的需求");
        mDate_square.add("不知小伙伴们有没有看《声临其境》总决赛，作为马后炮的我就来详聊一下观后感受，全员都很棒" +
                "，个个是大佬，只不过这其中才华与美貌集于一身的奇女子韩雪再次颠覆了我的印象，她…怎么可以这么…厉害，给跪给跪还不行么");
        mDate_square.add("最厉害的当然要属逆天的数据支持，虽然热搜已过，但还是必须立图为证啊！");
        mDate_square.add("全民帮");
        mDate_square.add("全民帮");
        mAdapter_SquareList=new Adapter_SquareList(mDate_square,getContext());
        mListview_square.setAdapter(mAdapter_SquareList);
    }

    private void initview() {
        mEdit_serchSquare=rootview.findViewById(R.id.edit_searchSquare);
        mListview_square=rootview.findViewById(R.id.list_square);
    }

}
