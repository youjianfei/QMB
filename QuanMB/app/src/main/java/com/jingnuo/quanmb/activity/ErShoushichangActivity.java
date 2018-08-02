package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_ErshoushichangList;
import com.jingnuo.quanmb.Adapter.Adapter_LovetaskList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader22;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.EeshoushichangListBean;
import com.jingnuo.quanmb.entityclass.ErshoushichangDetailsBean;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.LoveTaskListBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.youth.banner.Banner;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErShoushichangActivity extends BaseActivityother {
    View listheadView;

    //控件
    PullToRefreshListView mListview;
    LinearLayout mLinearlayout_fabu;//发布按钮


    List<EeshoushichangListBean.DataBean> mdata;
    Adapter_ErshoushichangList adapter_ershoushichangList;

    int page = 1;//分页加载；
    Map map_ershoushichang;

    EeshoushichangListBean eeshoushichangListBean;

    //头视图
    Banner banner;
    List<GuanggaoBean.DataBean>mdata_image_GG;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_er_shoushichang;
    }

    @Override
    protected void setData() {
        adapter_ershoushichangList=new Adapter_ErshoushichangList(mdata,this);
        mListview.setAdapter(adapter_ershoushichangList);
    }

    @Override
    protected void initData() {
        //设置图片加载器
        banner.setImageLoader(new GlideLoader22());
        mdata_image_GG=new ArrayList<>();
        mdata=new ArrayList<>();
        map_ershoushichang=new HashMap();
        map_ershoushichang.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_ershoushichang.put("community_code",Staticdata.static_userBean.getData().getAppuser().getCommunity_code());

        request(page);
        request_GGLB();
    }

    @Override
    protected void initListener() {
        mLinearlayout_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Staticdata.isLogin){
                    Intent intent_isslovetask=new Intent(ErShoushichangActivity.this,IssueErshoushichangActivity.class);
                    startActivity(intent_isslovetask);
                }else {
                    Intent intent_login = new Intent(ErShoushichangActivity.this, LoginActivity.class);
                    startActivity(intent_login);
                }
            }
        });
        //下拉  上拉 加载刷新
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                request( page);
                LogUtils.LOG("ceshi", "下拉刷新生效", "fragmentsquare");

            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                request( ++page);
            }
        });
        //listview的条目点击事件
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.LOG("ceshi","i"+i,"dianjie");
                Intent intent_lovetaskdetails=new Intent(ErShoushichangActivity.this,ErshouxinxiDetailsActivity.class);
                intent_lovetaskdetails.putExtra("taskid",mdata.get(i-2).getTask_id()+"");
                startActivity(intent_lovetaskdetails);
            }
        });
        final float[] mFirstY = {0};//按下时获取位置
        final float[] mCurrentY = {0};//得到滑动的位置
        mListview.getRefreshableView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mFirstY[0] = event.getY();//按下时获取位置
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentY[0] = event.getY();//得到滑动的位置
                        if(mCurrentY[0] - mFirstY[0] > 5){//滑动的位置减去按下的位置大于最小滑动距离  则表示向下滑动
                            //down
                            LogUtils.LOG("ceshi", "下", "MyShequActivity");
                            mLinearlayout_fabu.setVisibility(View.VISIBLE);
                        }else if(mFirstY[0] - mCurrentY[0] > 5)
                            {//反之向上滑动
                            //up
                            LogUtils.LOG("ceshi", "上", "MyShequActivity");
                            mLinearlayout_fabu.setVisibility(View.INVISIBLE);
                        }
                        break;

                }

                return false;
            }
        });
    }

    @Override
    protected void initView() {
        mListview=findViewById(R.id.list_lovetask);
        mLinearlayout_fabu=findViewById(R.id.linearlayout_fabu);//发布按钮

        listheadView= LayoutInflater.from(this).inflate(R.layout.list_headview_lovetask,null,false);
        mListview.getRefreshableView().addHeaderView(listheadView);
        /**
         * headview  控件
         */
        RelativeLayout relativeLayout_headbackground=listheadView.findViewById(R.id.relativeLayout_headbackground);
        AbsListView.LayoutParams mLayoutparams = new AbsListView.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth * 0.44));
        relativeLayout_headbackground.setLayoutParams(mLayoutparams);

        banner = listheadView. findViewById(R.id.banner_lovetask);
    }
    private void request_GGLB(){//请求网络轮播图
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshiddd", "轮播图片：" + respose, "LoveTaskActivity");
                mdata_image_GG.clear();
                mdata_image_GG.addAll(new Gson().fromJson(respose,GuanggaoBean.class).getData());
                List<String> images=new ArrayList<>();
                for(int i=0;i<mdata_image_GG.size();i++){
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
        }).Http(Urls.Baseurl_cui+Urls.shouyePic+"5",ErShoushichangActivity.this,0);
    }
    void request(final int page){
        map_ershoushichang.put("pageNum",page+"");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"二手市场列表");
                if (mListview.isRefreshing()) {
                    mListview.onRefreshComplete();
                }
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("message");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {
                    eeshoushichangListBean = new Gson().fromJson(respose, EeshoushichangListBean.class);

                    if (page == 1 && eeshoushichangListBean.getData() != null) {
                        mdata.clear();
                        mdata.addAll(eeshoushichangListBean.getData());

                        adapter_ershoushichangList.notifyDataSetChanged();
                    } else if (page != 1 && eeshoushichangListBean.getData() != null) {
                        mdata.addAll(eeshoushichangListBean.getData());
                        adapter_ershoushichangList.notifyDataSetChanged();
                    }

                } else {
                    ToastUtils.showToast(ErShoushichangActivity.this, msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.Ershoushichang,ErShoushichangActivity.this,1,map_ershoushichang);

        LogUtils.LOG("ceshi",Urls.Baseurl_cui+Urls.LovetaskList,"爱心帮列表");
        LogUtils.LOG("ceshi",map_ershoushichang.toString(),"爱心帮列表");
    }
}
