package com.jingnuo.quanmb.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_liuyanqiangList;
import com.jingnuo.quanmb.Adapter.Adapter_shequ8kuai;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader22;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.CommunityNoticeBean;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.LiuyanqiangListBean;
import com.jingnuo.quanmb.popwinow.Popwindow_lookpic;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.youth.banner.Banner;
import com.jingnuo.quanmb.R;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class MyShequActivity extends BaseActivityother {
    View listheadView;
    //控件
    LinearLayout mLinearlayout_fabbu;
    RelativeLayout mRelayout_banner;
    Banner banner;
    TextView mTextview_text_notice;
    MyGridView mygrid_mokuai;
    ImageView mImageview_image_shuidian;
    PullToRefreshListView myListView;
    TextView textview_shequname;


    //对象
    Adapter_shequ8kuai adapter_shequ8kuai;
    LiuyanqiangListBean liuyanqiangListBean;
    Adapter_liuyanqiangList adapter_liuyanqiangList;
    CommunityNoticeBean communityNoticeBean;

    //数据
    List<GuanggaoBean.DataBean>mdata_image_GG;
    List<String>mList_mokuai;
    List<LiuyanqiangListBean.DataBean>mList_liuyan;
    List<String> imageview_urllist;




    String  shequcode=Staticdata.static_userBean.getData().getAppuser().getCommunity_code()+"";


    int  page=1;

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
        imageview_urllist=new ArrayList<>();
        mList_mokuai.add("1");mList_mokuai.add("2");mList_mokuai.add("3");mList_mokuai.add("4");
        mList_mokuai.add("5");mList_mokuai.add("6");

        adapter_shequ8kuai=new Adapter_shequ8kuai(mList_mokuai,this);
        mygrid_mokuai.setAdapter(adapter_shequ8kuai);
        adapter_liuyanqiangList=new Adapter_liuyanqiangList(mList_liuyan,this);
        myListView.setAdapter(adapter_liuyanqiangList);
        textview_shequname.setText(Staticdata.static_userBean.getData().getAppuser().getCommunity_name());
        request_GGLB();
        request(1);
        requestNotic();
    }

    @Override
    protected void initListener() {
        mygrid_mokuai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi", "点击"+position, "mygrid_mokuai");
                Intent intent;
                switch (position){
                    case 5://福利社
                        intent=new Intent(MyShequActivity.this,FulisheActivity.class);
                        startActivity(intent);
                        break;
//                    case 6://二手市场
//                        if(shequcode.equals(Staticdata.static_userBean.getData().getAppuser().getCommunity_code())){
//                            intent=new Intent(MyShequActivity.this,ErShoushichangActivity.class);
//                            startActivity(intent);
//                        }else {
//                            intent=new Intent(MyShequActivity.this,ErShoushichangActivity.class);
//                            intent.putExtra("type",1);
//                            intent.putExtra("shequcode",shequcode);
//                            startActivity(intent);
//                        }
//                        break;
                    case 4://家电维修
                        intent=new Intent(MyShequActivity.this,ShophallActivity.class);
                        intent.putExtra("FromShequZuhe","1203,1204,1205,1210,");
                        startActivity(intent);
                        break;
                    case 3://搬家运输
                        intent=new Intent(MyShequActivity.this,ShophallActivity.class);
                        intent.putExtra("FromShequ",106+"");
                        startActivity(intent);
                        break;
                    case 2://家政服务
                        intent=new Intent(MyShequActivity.this,ShophallActivity.class);
                        intent.putExtra("FromShequ",103+"");
                        startActivity(intent);
                        break;
//                    case 2://物业缴费
//                        if(shequcode.equals(Staticdata.static_userBean.getData().getAppuser().getCommunity_code())){
//                            intent=new Intent(MyShequActivity.this,ShuidianfeiActivity.class);
//                            startActivity(intent);
//                        }else {
//                            ToastUtils.showToast(MyShequActivity.this,"请进入你的社区缴费");
//                        }
//
//                        break;
                    case 1://宠物护理
                        intent=new Intent(MyShequActivity.this,ShophallActivity.class);
                        intent.putExtra("specialty_id",2300);
                        startActivity(intent);
                        break;
                    case 0://门禁钥匙
                        intent=new Intent(MyShequActivity.this,ShophallActivity.class);
                        intent.putExtra("specialty_id",1209);
                        startActivity(intent);
                        break;
                }
            }
        });
        mLinearlayout_fabbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_fabu=new Intent(MyShequActivity.this,MessageWallEditActivity.class);
                startActivity(intent_fabu);
            }
        });
        //下拉  上拉 加载刷新
        myListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                request(1);
                LogUtils.LOG("ceshi", "下拉刷新生效", "fragmentsquare");

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                request( ++page);
            }
        });
        final float[] mFirstY = {0};//按下时获取位置
        final float[] mCurrentY = {0};//得到滑动的位置
        myListView.getRefreshableView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(shequcode.equals(Staticdata.static_userBean.getData().getAppuser().getCommunity_code())){
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            mFirstY[0] = event.getY();//按下时获取位置
                            break;
                        case MotionEvent.ACTION_MOVE:
                            mCurrentY[0] = event.getY();//得到滑动的位置
                            if(mCurrentY[0] - mFirstY[0] > 5){//滑动的位置减去按下的位置大于最小滑动距离  则表示向下滑动
                                //down
                                LogUtils.LOG("ceshi", "下", "MyShequActivity");
                                mLinearlayout_fabbu.setVisibility(View.VISIBLE);
                            }else if(mFirstY[0] - mCurrentY[0] > 5){//反之向上滑动
                                //up
                                LogUtils.LOG("ceshi", "上", "MyShequActivity");
                                mLinearlayout_fabbu.setVisibility(View.INVISIBLE);
                            }
                            break;

                    }

                    return false;
                }
                return false;
            }
        });
        mImageview_image_shuidian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent;
                if(shequcode.equals(Staticdata.static_userBean.getData().getAppuser().getCommunity_code())){
                    intent  =new Intent(MyShequActivity.this,ErShoushichangActivity.class);
                            startActivity(intent);
                        }else {
                            intent=new Intent(MyShequActivity.this,ErShoushichangActivity.class);
                            intent.putExtra("type",1);
                            intent.putExtra("shequcode",shequcode);
                            startActivity(intent);
                        }
            }
        });
        textview_shequname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_shequ=new Intent(MyShequActivity.this,LookShequ.class);
                startActivityForResult(intent_shequ,1);
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
        textview_shequname=findViewById(R.id.textview_shequname);


        listheadView= LayoutInflater.from(this).inflate(R.layout.list_headview_myshequ,null,false);
        myListView.getRefreshableView().addHeaderView(listheadView);

        /**
         * headview  控件
         */
        mRelayout_banner=listheadView.findViewById(R.id.relativelayout_banner);
        mTextview_text_notice=listheadView.findViewById(R.id.text_notice);
        banner =  listheadView.findViewById(R.id.banner_myshequ);
        mygrid_mokuai=listheadView.findViewById(R.id.mygrid_mokuai);
        mImageview_image_shuidian=listheadView.findViewById(R.id.image_shuidian);
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth * 0.44));
        mRelayout_banner.setLayoutParams(mLayoutparams);
        mTextview_text_notice.setSelected(true);

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

                banner.setOnBannerListener(new OnBannerListener() {//banner  点击事件
                    @Override
                    public void OnBannerClick(int position) {
                        LogUtils.LOG("ceshiddd","wodianjile"+position,"我的社区");
                        if(mdata_image_GG.get(position).getActivity_url()!=null&&!mdata_image_GG.get(position).getActivity_url().equals("")){
                            Intent intent=new Intent(MyShequActivity.this,GuanggaoWeb.class);
                            intent.putExtra("web",mdata_image_GG.get(position).getActivity_url());
                            MyShequActivity.this.startActivity(intent);
                        }
                    }
                });
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }
            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui + Urls.shouyePic + "3", MyShequActivity.this, 0);
    }
    void requestNotic(){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshiaaa", "社区公告：" + respose, " 社区公告");
                communityNoticeBean=new Gson().fromJson(respose,CommunityNoticeBean.class);
                if(communityNoticeBean.getData().size()>0){
                    mTextview_text_notice.setText(communityNoticeBean.getData().get(0).getNotice_content());
                }
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_hu+Urls.CommunityNotice+Staticdata.static_userBean.getData().getUser_token()+"&community_code="
                +shequcode,MyShequActivity.this,0);
    }
    private  void  request(final int  page){
        LogUtils.LOG("ceshi", "留言墙：" + Urls.Baseurl+Urls.getliuyan+Staticdata.static_userBean.getData().getUser_token()+"&pageNo="+page, " 社区轮播");
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (myListView.isRefreshing()) {
                    myListView.onRefreshComplete();
                }
                LogUtils.LOG("ceshi", "留言墙：" + respose, " 社区轮播");
                liuyanqiangListBean=new  Gson().fromJson(respose,LiuyanqiangListBean.class);

                if(page==1){
                    mList_liuyan.clear();
                    mList_liuyan.addAll(liuyanqiangListBean.getData());
                    adapter_liuyanqiangList.notifyDataSetChanged();
                }else {
                    mList_liuyan.addAll(liuyanqiangListBean.getData());
                    adapter_liuyanqiangList.notifyDataSetChanged();

                }
            }
            @Override
            public void onError(int error) {
                if (myListView.isRefreshing()) {
                    myListView.onRefreshComplete();
                }
            }
        }).Http(Urls.Baseurl+Urls.getliuyan+Staticdata.static_userBean.getData().getUser_token()
                +"&community_code="+shequcode
                +"&pageNo="+page,MyShequActivity.this,0);

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        request(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2018820){
            String shequname=data.getStringExtra("shequname");
            textview_shequname.setText(shequname);
            shequcode=data.getStringExtra("community_code");
            request(1);
            requestNotic();
            if(!shequcode.equals(Staticdata.static_userBean.getData().getAppuser().getCommunity_code())){
                mLinearlayout_fabbu.setVisibility(View.INVISIBLE);
            }

        }

    }
}

