package com.jingnuo.quanmb.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_liuyanqiangList;
import com.jingnuo.quanmb.Adapter.Adapter_shequ8kuai;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader22;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.LiuyanqiangListBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MyShequActivity extends BaseActivityother {
    //控件
    LinearLayout mLinearlayout_fabbu;
    RelativeLayout mRelayout_banner;
    Banner banner;
    MyGridView mygrid_mokuai;
    MyListView myListView;


    //对象
    Adapter_shequ8kuai adapter_shequ8kuai;
    LiuyanqiangListBean liuyanqiangListBean;
    Adapter_liuyanqiangList adapter_liuyanqiangList;


    //数据
    List<GuanggaoBean.DataBean>mdata_image_GG;
    List<String>mList_mokuai;
    List<LiuyanqiangListBean.DataBean>mList_liuyan;


    int  page=0;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_my_shequ;
    }

    @Override
    protected void setData() {
        //设置图片加载器
        banner.setImageLoader(new GlideLoader22());
    }

    @Override
    protected void initData() {
        mdata_image_GG=new ArrayList<>();
        mList_mokuai=new ArrayList<>();
        mList_liuyan=new ArrayList();
        mList_mokuai.add("1");mList_mokuai.add("2");mList_mokuai.add("3");mList_mokuai.add("4");
        mList_mokuai.add("5");mList_mokuai.add("6");mList_mokuai.add("7");mList_mokuai.add("8");

        adapter_shequ8kuai=new Adapter_shequ8kuai(mList_mokuai,this);
        mygrid_mokuai.setAdapter(adapter_shequ8kuai);
        adapter_liuyanqiangList=new Adapter_liuyanqiangList(mList_liuyan,this);
        myListView.setAdapter(adapter_liuyanqiangList);
        request_GGLB();
        request(1);
    }

    @Override
    protected void initListener() {
        mLinearlayout_fabbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_fabu=new Intent(MyShequActivity.this,MessageWallEditActivity.class);
                startActivity(intent_fabu);
            }
        });
        myListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                LogUtils.LOG("ceshiddd", "scrollState：" + scrollState, " 社区轮播");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    @Override
    protected void initView() {
        mLinearlayout_fabbu=findViewById(R.id.linearlayout_fabu);
        mRelayout_banner=findViewById(R.id.relativelayout_banner);
        banner =  findViewById(R.id.banner_myshequ);
        mygrid_mokuai=findViewById(R.id.mygrid_mokuai);
        myListView=findViewById(R.id.listview_myshequ);

        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth * 0.44));
        mRelayout_banner.setLayoutParams(mLayoutparams);
    }
    private void request_GGLB() {//请求网络轮播图
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshiddd", "轮播图片：" + respose, " 社区轮播");
                mdata_image_GG.clear();
                mdata_image_GG.addAll(new Gson().fromJson(respose, GuanggaoBean.class).getData());
                List<String> images = new ArrayList<>();
                for (int i = 0; i < mdata_image_GG.size(); i++) {
                    images.add(mdata_image_GG.get(i).getImg_url());
                }
                //设置图片集合
                banner.setImages(images);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }
            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui + Urls.shouyePic + "3", MyShequActivity.this, 0);
    }
    private  void  request(final int  page){
        LogUtils.LOG("ceshiddd", "留言墙：" + Urls.Baseurl+Urls.getliuyan+Staticdata.static_userBean.getData().getUser_token()+"&pageNo="+page, " 社区轮播");
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshiddd", "留言墙：" + respose, " 社区轮播");
                liuyanqiangListBean=new  Gson().fromJson(respose,LiuyanqiangListBean.class);
                if(page==1){
                    mList_liuyan.clear();
                    mList_liuyan.addAll(liuyanqiangListBean.getData());
                    adapter_liuyanqiangList.notifyDataSetChanged();
                }
            }
            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.getliuyan+Staticdata.static_userBean.getData().getUser_token()
                +"&community_code="+Staticdata.static_userBean.getData().getAppuser().getCommunity_code()
                +"&pageNo="+page,MyShequActivity.this,0);

    }



    @Override
    protected void onPostResume() {
        super.onPostResume();
        request(1);
    }
}

