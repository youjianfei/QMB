package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_shophall;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_SquareSort;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.SkillmentlistBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;

import java.util.ArrayList;
import java.util.List;


public class ShophallActivity extends BaseActivityother {
    //控件
//    LinearLayout mLinerlayout_sort;
//    LinearLayout mLinearlayout_filter;
    PullToRefreshListView mListview;
    EditText mEdit_search;


    //对象
    Popwindow_SquareSort mPopwindow_square_sort;
    Adapter_shophall mAdapter_shophall;

    PermissionHelper mPermission;//动态申请权限
    //数据
    int specialty_id=0;
    String search="";
    int page=1;
    List<SkillmentlistBean.DataBean.ListBean> mData;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_shophall;
    }

    @Override
    protected void setData() {
            request(1);


    }

    @Override
    protected void initData() {
        mPermission= new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        specialty_id=getIntent().getIntExtra("specialty_id",0);
        search=getIntent().getStringExtra("search");
        mData = new ArrayList<>();

        mAdapter_shophall = new Adapter_shophall(mData, this,mPermission);
        mListview.setAdapter(mAdapter_shophall);
    }

    @Override
    protected void initListener() {
//        mLinerlayout_sort.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View view) {
////                mPopwindow_square_sort = new Popwindow_SquareSort(this, new InterfacePopwindow_square_sort() {
////                    @Override
////                    public void onSuccesses(String address, String id) {
////
////                    }
////                }, mRelativelayout_sort, 1);
////                mPopwindow_square_sort.showPopwindow();
//            }
//        });
//        mLinearlayout_filter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        mEdit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                search=Utils.ZhuanMa(s+"");
                search=s+"";
                request(1);

            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent_shopskillDetail = new Intent(ShophallActivity.this, SkillDetailActivity.class);

                mIntent_shopskillDetail.putExtra("id",mData.get(i-1).getRelease_specialty_id());
                LogUtils.LOG("ceshi",mData.get(i-1).getBusiness_no(),"ShophallActivity");
                if(mData.get(i-1).getBusiness_no().equals("")){
                    mIntent_shopskillDetail.putExtra("role",1+"");
                    LogUtils.LOG("ceshi",mData.get(i-1).getBusiness_no(),"ShophallActivity1");
                }else {
                    mIntent_shopskillDetail.putExtra("role",2+"");
                    LogUtils.LOG("ceshi",mData.get(i-1).getBusiness_no(),"ShophallActivity2");

                }
                startActivity(mIntent_shopskillDetail);
            }
        });
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                request(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                request(page);

            }
        });


    }

    @Override
    protected void initView() {
//        mLinerlayout_sort = findViewById(R.id.linearlayout_sort);
//        mLinearlayout_filter = findViewById(R.id.linearlayout_filter);
        mEdit_search = findViewById(R.id.edit_searchskill);
        mListview = findViewById(R.id.mlistview_shophall);
    }

    void request( final int page) {
        String URL="";
        if(specialty_id==0){
            URL=Urls.Baseurl+Urls.searchSkill+"?title="+search+"&curPageNo="+page;
        }else {
            URL=Urls.Baseurl+Urls.Skillmenulist+"?specialty_id="+specialty_id+"&curPageNo="+page;
        }
        LogUtils.LOG("ceshi","接口："+URL,"找专业列表");
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
        }).Http(URL,this,0);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
