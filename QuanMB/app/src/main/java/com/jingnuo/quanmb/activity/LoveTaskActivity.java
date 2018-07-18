package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_LovetaskList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader22;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.LoveTaskListBean;
import com.jingnuo.quanmb.entityclass.Square_defaultBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoveTaskActivity extends BaseActivityother {

    View listheadView;

    //控件
    PullToRefreshListView  mListview;
    LinearLayout mLinearlayout_fabu;//发布按钮


    List<LoveTaskListBean.DataBean> mdata;
    Adapter_LovetaskList adapter_lovetaskList;

    int page = 1;//分页加载；
    Map map_lovetask;

    LoveTaskListBean loveTaskListBean;

    //头视图
    Banner banner;
    List<GuanggaoBean.DataBean>mdata_image_GG;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_love_task;
    }

    @Override
    protected void setData() {
        adapter_lovetaskList=new Adapter_LovetaskList(mdata,this);
        mListview.setAdapter(adapter_lovetaskList);
    }

    @Override
    protected void initData() {
        //设置图片加载器
        banner.setImageLoader(new GlideLoader22());
        mdata_image_GG=new ArrayList<>();
        mdata=new ArrayList<>();
        map_lovetask=new HashMap();
        map_lovetask.put("city_code",Staticdata.city_location);
        map_lovetask.put("y_value",Staticdata.yValue);
        map_lovetask.put("x_value",Staticdata.xValue);

        request(page);
        request_GGLB();
    }

    @Override
    protected void initListener() {
        mLinearlayout_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Staticdata.isLogin){
                    Intent intent_isslovetask=new Intent(LoveTaskActivity.this,IssuelovetaskActivity.class);
                    startActivity(intent_isslovetask);
                }else {
                    Intent intent_login = new Intent(LoveTaskActivity.this, LoginActivity.class);
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
               Intent intent_lovetaskdetails=new Intent(LoveTaskActivity.this,LoveTaskDetailsActivity.class);
                intent_lovetaskdetails.putExtra("taskid",mdata.get(i-2).getTask_id()+"");
               startActivity(intent_lovetaskdetails);
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
        new  Volley_Utils(new Interface_volley_respose() {
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
        }).Http(Urls.Baseurl_cui+Urls.shouyePic+"2",LoveTaskActivity.this,0);
    }
    void request(final int page){
        map_lovetask.put("pageNum",page+"");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"爱心帮列表");
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
                    loveTaskListBean = new Gson().fromJson(respose, LoveTaskListBean.class);

                    if (page == 1 && loveTaskListBean.getData() != null) {
                        mdata.clear();
                        mdata.addAll(loveTaskListBean.getData());

                        adapter_lovetaskList.notifyDataSetChanged();
                    } else if (page != 1 && loveTaskListBean.getData() != null) {
                        mdata.addAll(loveTaskListBean.getData());
                        adapter_lovetaskList.notifyDataSetChanged();
                    }

                } else {
                    ToastUtils.showToast(LoveTaskActivity.this, msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.LovetaskList,LoveTaskActivity.this,1,map_lovetask);

    }
}
