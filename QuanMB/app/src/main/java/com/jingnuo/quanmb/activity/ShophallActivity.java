package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_shophall;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_square_sort;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_SquareSort;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.SkillmentlistBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;

import java.util.ArrayList;
import java.util.List;


public class ShophallActivity extends BaseActivityother {
    //控件
    LinearLayout mLinerlayout_sort;
    LinearLayout mLinearlayout_filter;
    PullToRefreshListView mListview;

    //对象
    Popwindow_SquareSort mPopwindow_square_sort;
    Adapter_shophall mAdapter_shophall;

    PermissionHelper mPermission;//动态申请权限
    //数据
    int specialty_id=0;
    int page=1;
    List<SkillmentlistBean.DataBean.ListBean> mData;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_shophall;
    }

    @Override
    protected void setData() {
        request(specialty_id,1);
    }

    @Override
    protected void initData() {
        mPermission= new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        specialty_id=getIntent().getIntExtra("specialty_id",0);
        mData = new ArrayList<>();

        mAdapter_shophall = new Adapter_shophall(mData, this,mPermission);
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
                Intent mIntent_shopskillDetail = new Intent(ShophallActivity.this, ShopDetailActivity.class);
                startActivity(mIntent_shopskillDetail);
            }
        });
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                request(specialty_id,1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                request(specialty_id,page);

            }
        });


    }

    @Override
    protected void initView() {
        mLinerlayout_sort = findViewById(R.id.linearlayout_sort);
        mLinearlayout_filter = findViewById(R.id.linearlayout_filter);

        mListview = findViewById(R.id.mlistview_shophall);
    }

    void request(int specialty_id , final int page) {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (mListview.isRefreshing()) {
                    mListview.onRefreshComplete();
                }
                if(page==1&&new Gson().fromJson(respose,SkillmentlistBean.class).getData().getList()!=null){
                    mData.clear();
                    mData.addAll(new Gson().fromJson(respose,SkillmentlistBean.class).getData().getList());
                    mAdapter_shophall.notifyDataSetChanged();
                }else if(page!=1&&new Gson().fromJson(respose,SkillmentlistBean.class).getData().getList()!=null) {
                    mData.addAll(new Gson().fromJson(respose,SkillmentlistBean.class).getData().getList());
                    mAdapter_shophall.notifyDataSetChanged();
                }else {
                    ToastUtils.showToast(ShophallActivity.this,"没有更多内容");
                }


            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.Skillmenulist+"?specialty_id="+specialty_id+"&curPageNo="+page,this,0);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
