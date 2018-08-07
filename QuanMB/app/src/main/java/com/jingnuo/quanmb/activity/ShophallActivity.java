package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_shophall;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.popwinow.Popwindow_SquareSort;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.SkillmentlistBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.List;


public class ShophallActivity extends BaseActivityother {
    //控件
//    LinearLayout mLinerlayout_sort;
//    LinearLayout mLinearlayout_filter;
    PullToRefreshListView mListview;
    EditText mEdit_search;


    //对象
    Adapter_shophall mAdapter_shophall;

    PermissionHelper mPermission;//动态申请权限
    //数据
    int specialty_id=0;
    String search="";
    String search2="";
    int page=1;
    List<SkillmentlistBean.DataBean.ListBean> mData;

    String NO="";//查看帮手和商家的所有服务使用
    String FromShequ="";//从社区跳转过来的一级查询服务列表
    String FromShequZuhe="";//从社区跳转过来的二级组合查询服务列表

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

        NO=getIntent().getStringExtra("NO");
        FromShequ=getIntent().getStringExtra("FromShequ");
        FromShequZuhe=getIntent().getStringExtra("FromShequZuhe");

        mData = new ArrayList<>();
        mAdapter_shophall = new Adapter_shophall(mData, this,mPermission);
        mListview.setAdapter(mAdapter_shophall);
    }

    @Override
    protected void initListener() {
        mEdit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search=Utils.ZhuanMa(s+"");
                search2=Utils.ZhuanMa(s+"");
//                search=s+"";
                request(1);

            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent_shopskillDetail = new Intent(ShophallActivity.this, SkillDetailActivity.class);

                mIntent_shopskillDetail.putExtra("id",mData.get(i-1).getRelease_specialty_id());
                LogUtils.LOG("ceshi",mData.get(i-1).getBusiness_no(),"ShophallActivity");
                if(mData.get(i-1).getBusiness_no()==null||mData.get(i-1).getBusiness_no().equals("")){
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
        LogUtils.LOG("ceshi","接口："+NO,"找专业列表");
        String URL="";
        if(NO!=null&&NO.startsWith("H")){//查看某个帮手的所有服务
            URL=Urls.Baseurl+Urls.BHSkissAll+"?helper_no="+NO.substring(1)+"&type="+1+"&curPageNo="+page;
        }else   if(NO!=null&&NO.startsWith("B")){//查看某个商家的所有服务
            URL=Urls.Baseurl+Urls.BHSkissAll+"?business_no="+NO.substring(1)+"&type="+2+"&curPageNo="+page;
        }
        if(specialty_id==0&&NO==null){//查看所有专业  可以搜索
            URL=Urls.Baseurl+Urls.searchSkill+"?title="+search+"&curPageNo="+page;
        }else if(specialty_id!=0&&NO==null){//查看某二级专业下所有服务
            URL=Urls.Baseurl+Urls.Skillmenulist+"?specialty_id="+specialty_id+"&title="+search2+"&curPageNo="+page;
        }
        if(FromShequ!=null&&!FromShequ.equals("")){//社区跳转来的
            URL=Urls.Baseurl+Urls.FromShequSkiss+"?specialty_id="+FromShequ+"&curPageNo="+page+"&user_token="+ Staticdata.static_userBean.getData().getUser_token();
        }
        if(FromShequZuhe!=null&&!FromShequZuhe.equals("")){//社区跳转来的
            URL=Urls.Baseurl+Urls.zhidiengShequSkiss+"?specialtyidlist="+FromShequZuhe+"&curPageNo="+page+"&user_token="+ Staticdata.static_userBean.getData().getUser_token();
        }
        LogUtils.LOG("ceshi","接口："+URL,"找专业列表");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi","接口："+respose,"找专业列表");
                if (mListview.isRefreshing()) {
                    mListview.onRefreshComplete();
                }
                if(page==1&&new Gson().fromJson(respose,SkillmentlistBean.class).getData()!=null){
                    mData.clear();
                    mData.addAll(new Gson().fromJson(respose,SkillmentlistBean.class).getData().getList());
                    mAdapter_shophall.notifyDataSetChanged();
                }else if(page!=1&&new Gson().fromJson(respose,SkillmentlistBean.class).getData()!=null) {
                    mData.addAll(new Gson().fromJson(respose,SkillmentlistBean.class).getData().getList());
                    mAdapter_shophall.notifyDataSetChanged();
                }else {

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
