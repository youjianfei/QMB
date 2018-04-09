package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_shophall;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_square_sort;
import com.jingnuo.quanmb.class_.Popwindow_SquareSort;
import com.jingnuo.quanmb.quanmb.R;

import java.util.ArrayList;
import java.util.List;


public class ShophallActivity extends BaseActivityother {
    //控件
    LinearLayout mLinerlayout_sort;
    LinearLayout mLinearlayout_filter;
    PullToRefreshListView  mListview;

    //对象
    Popwindow_SquareSort mPopwindow_square_sort;
    Adapter_shophall mAdapter_shophall;
    //数据
    List<String> mData;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_shophall;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        mData=new ArrayList<>();
        mData.add("专业开锁");
        mData.add("专业按摩");
        mData.add("主也是给这条评论点赞默");
        mData.add("然而现场观众刚开始都不知道");
        mData.add("蓉这个话题会激发重大的套路");
        mAdapter_shophall=new Adapter_shophall(mData,this);
        mListview.setAdapter(mAdapter_shophall);
    }

    @Override
    protected void initListener() {
        mLinerlayout_sort.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
//                mPopwindow_square_sort = new Popwindow_SquareSort(this, new InterfacePopwindow_square_sort() {
//                    @Override
//                    public void onSuccesses(String address, String id) {
//
//                    }
//                }, mRelativelayout_sort, 1);
//                mPopwindow_square_sort.showPopwindow();
            }
        });
        mLinearlayout_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent_shopskillDetail=new Intent(ShophallActivity.this,ShopDetailActivity.class);
                startActivity(mIntent_shopskillDetail);
            }
        });


    }

    @Override
    protected void initView() {
        mLinerlayout_sort=findViewById(R.id.linearlayout_sort);
        mLinearlayout_filter=findViewById(R.id.linearlayout_filter);

        mListview=findViewById(R.id.mlistview_shophall);
    }
}
