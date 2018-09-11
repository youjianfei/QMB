package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.Adapter.AdapterFragment;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_skillsdetails;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Matchshoplistbean;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.fargment.Fragment_shopdetail;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.popwinow.ProgressDlog;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MatchShopActivity extends AppCompatActivity  {

    //控件
    ImageView iv_back;
    private ViewPager mViewPager;
    LinearLayout mtextview_change;
    TextView mTextview_taskdetails;//任务详情
    MyGridView imageGridview;
    TextView mTextview_yuyuetime;//预约时间
//    TextView mTextview_guzhuName;//雇主姓名
    TextView mTextview_taskaddress;//地址

    //数据
    String ID = "";
    String respose="";
    List<Fragment> list_myfragments;
    List<Matchshoplistbean.DataBean.MatchingBean>list_matchbea;//匹配的商户对象数组

    List<String> imageview_urllist;
    Map map_taskdetail;//任务详情map
    Map map_price;//请求价格map



    //对象
    KProgressHUD mKProgressHUD;

    AdapterFragment adapterFragment;
    Matchshoplistbean  matchshoplistbean;

    TaskDetailBean taskDetailBean;
    Adapter_Gridviewpic_skillsdetails adapter_gridviewpic;//图片展示适配器

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_shop);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
        respose=getIntent().getStringExtra("respose");
        mKProgressHUD = new KProgressHUD(MatchShopActivity.this);
        matchshoplistbean=new Gson().fromJson(respose,Matchshoplistbean.class);
        list_matchbea=new ArrayList<>();
        list_matchbea.clear();
        list_matchbea.addAll(matchshoplistbean.getData().getMatching());
        initview();
        initdata();
        initlistenner();
    }

    private void initdata() {
        ID = getIntent().getStringExtra("id");
        imageview_urllist=new ArrayList<>();//图片展示
        adapter_gridviewpic=new Adapter_Gridviewpic_skillsdetails(imageview_urllist,MatchShopActivity.this);
        imageGridview.setAdapter(adapter_gridviewpic);
        list_myfragments=new ArrayList<>();
        for (int i=0;i<list_matchbea.size();i++){
            list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i),ID));
        }
        adapterFragment=new AdapterFragment(getSupportFragmentManager(),list_myfragments);
        mViewPager.setAdapter(adapterFragment);

        map_taskdetail = new HashMap();
        map_taskdetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_taskdetail.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_taskdetail.put("id", ID + "");
        request(map_taskdetail);//请求任务详情
        map_price=new HashMap();
        map_price.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_price.put("task_id", ID + "");
        map_price.put("business_no", list_matchbea.get(0).getBusiness_no());
//        timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                mhandler.sendEmptyMessage(0);
//            }
//        };
//        timer.schedule(timerTask, 0, 3000);
    }

    private void initview() {
        iv_back = findViewById(R.id.iv_back);
        mViewPager = findViewById(R.id.viewPager);
        mtextview_change=findViewById(R.id.textview_change);
        mTextview_taskdetails = findViewById(R.id.text_taskdetail);
        imageGridview = findViewById(R.id.GridView_PIC);
        mTextview_yuyuetime = findViewById(R.id.text_time);
//        mTextview_guzhuName = findViewById(R.id.text_guzhuname);
        mTextview_taskaddress = findViewById(R.id.text_address);

    }
    private  void  initlistenner(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Popwindow_Tip("放弃任务？", MatchShopActivity.this, new Interence_complteTask() {
                    @Override
                    public void onResult(boolean result) {
                        if (result){
                            Intent intent=new Intent(MatchShopActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).showPopwindow();
            }
        });
        mtextview_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(MatchShopActivity.this,"点击刷新");
                huanyipi();//请求换一批
                ProgressDlog.showProgress(mKProgressHUD);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                ProgressDlog.showProgress(mKProgressHUD);
//                map_price.put("business_no", list_matchbea.get(position).getBusiness_no());//确定请求哪一个商户的出价
//                timer.cancel();
//                timer=null;
//                timer = new Timer();
//                TimerTask timerTask = new TimerTask() {
//                    @Override
//                    public void run() {
//                        mhandler.sendEmptyMessage(0);
//                    }
//                };
//                timer.schedule(timerTask, 0, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    void request(Map map) {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.mytaskdetails+"查订单"+respose, "MytaskDetailActivity");
                taskDetailBean = new Gson().fromJson(respose, TaskDetailBean.class);
                String sex=taskDetailBean.getData().getClient_sex().equals("0")?"（先生）":"（女士）";
//                mTextview_guzhuName.setText(taskDetailBean.getData().getClient_name()+sex+taskDetailBean.getData().getMobile_no());
                mTextview_taskdetails.setText(taskDetailBean.getData().getTask_description());
                mTextview_yuyuetime.setText(taskDetailBean.getData().getTask_Time());
                mTextview_taskaddress.setText(taskDetailBean.getData().getRelease_address() );
                String imageURL =taskDetailBean.getData().getTask_ImgUrl();
                setImage(imageURL);
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.mytaskdetails, MatchShopActivity.this, 1, map);

    }
    void huanyipi(){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                mKProgressHUD.dismiss();
                LogUtils.LOG("ceshi",respose,"换一批");
                matchshoplistbean=new Gson().fromJson(respose,Matchshoplistbean.class);
                if(matchshoplistbean.getCode()==1){
                    list_matchbea.clear();
                    list_matchbea.addAll(matchshoplistbean.getData().getMatching());
                    list_myfragments.clear();
                    for (int i=0;i<list_matchbea.size();i++){
                        list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i),ID));
                    }
                    adapterFragment.setNewFragments(list_myfragments);
                }else {
                    ToastUtils.showToast(MatchShopActivity.this,"附近没有此类型商户");
                }
            }

            @Override
            public void onError(int error) {
                mKProgressHUD.dismiss();
            }
        }).Http(Urls.Baseurl_cui+Urls.issuetask_huanyipi
                +Staticdata.static_userBean.getData().getUser_token()+"&task_id="
                +ID,MatchShopActivity.this,0);
    }
//    void getPrice(Map map){
//        LogUtils.LOG("ceshi","商户出价"+map,"商户出价map");
//        new  Volley_Utils(new Interface_volley_respose() {
//            @Override
//            public void onSuccesses(String respose) {
//                LogUtils.LOG("ceshi","商户出价"+respose,"商户出价");
//                mKProgressHUD.dismiss();
//                int status = 0;
//                String msg = "";
//                try {
//                    JSONObject object = new JSONObject(respose);
//                    status = (Integer) object.get("code");//
//                    msg = (String) object.get("data");//
//                    Staticdata.price=msg;
//                    if(status==1){
////                        timer.cancel();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onError(int error) {
//
//            }
//        }).postHttp(Urls.Baseurl_cui+Urls.issuetask_getprice,MatchShopActivity.this,1,map);
//    }

    void setImage(String image) {
        if (image == null || image.equals("")) {
            imageGridview.setVisibility(View.GONE);
        } else {
            String[] images = image.split(",");
            int len = images.length;
            LogUtils.LOG("ceshi", "图片的个数" + images.length, "SkillDetailActivity分隔图片");
            imageview_urllist.clear();
            for (int i = 0; i < len; i++) {
                imageview_urllist.add(images[i]);
            }
            if(imageview_urllist.size()>0){
                imageGridview.setVisibility(View.VISIBLE);
            }else {
                imageGridview.setVisibility(View.GONE);
            }
            adapter_gridviewpic.notifyDataSetChanged();

        }

    }

//    Timer timer;
//    private Handler mhandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    getPrice(map_price);//请求商户出价
//                    break;
//            }
//        }
//
//
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        timer.cancel();
//        timer=null;
    }
    @Override
    public void onBackPressed() {
        new Popwindow_Tip("放弃任务？", MatchShopActivity.this, new Interence_complteTask() {
            @Override
            public void onResult(boolean result) {
                if (result){
                    Intent intent=new Intent(MatchShopActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }).showPopwindow();
    }
}
