package com.jingnuo.quanmb.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchShopActivity extends AppCompatActivity  {

    //控件
    private ViewPager mViewPager;
    LinearLayout mtextview_change;
    TextView mTextview_taskdetails;//任务详情
    MyGridView imageGridview;
    TextView mTextview_yuyuetime;//预约时间
    TextView mTextview_guzhuName;//雇主姓名
    TextView mTextview_taskaddress;//地址

    //数据
    String ID = "";
    String respose="";
    List<Fragment> list_myfragments;
    List<Matchshoplistbean.DataBean.MatchingBean>list_matchbea;

    String image_url = "";
    List<String> imageview_urllist;
    Map map_taskdetail;



    //对象
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
        matchshoplistbean=new Gson().fromJson(respose,Matchshoplistbean.class);
        list_matchbea=new ArrayList<>();
        list_matchbea.clear();
        list_matchbea.addAll(matchshoplistbean.getData().getMatching());
        initview();
        initdata();
        initlistenner();
    }

    private void initdata() {
        imageview_urllist=new ArrayList<>();//图片展示
        adapter_gridviewpic=new Adapter_Gridviewpic_skillsdetails(imageview_urllist,MatchShopActivity.this);
        imageGridview.setAdapter(adapter_gridviewpic);
        list_myfragments=new ArrayList<>();
        for (int i=0;i<list_matchbea.size();i++){
            list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i)));
            list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i)));
            list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i)));
        }
        adapterFragment=new AdapterFragment(getSupportFragmentManager(),list_myfragments);
        mViewPager.setAdapter(adapterFragment);

        ID = getIntent().getStringExtra("id");
        map_taskdetail = new HashMap();
        map_taskdetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_taskdetail.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_taskdetail.put("id", ID + "");
        request(map_taskdetail);
    }

    private void initview() {
        mViewPager = findViewById(R.id.viewPager);
        mtextview_change=findViewById(R.id.textview_change);
        mTextview_taskdetails = findViewById(R.id.text_taskdetail);
        imageGridview = findViewById(R.id.GridView_PIC);
        mTextview_yuyuetime = findViewById(R.id.text_time);
        mTextview_guzhuName = findViewById(R.id.text_guzhuname);
        mTextview_taskaddress = findViewById(R.id.text_address);

    }
    private  void  initlistenner(){

        mtextview_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(MatchShopActivity.this,"点击刷新");
                list_myfragments.clear();
                list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(0)));
                adapterFragment.setFragments(list_myfragments);

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ToastUtils.showToast(MatchShopActivity.this,"weizhi"+position);
                if(position==2){

                }

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
                LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.mytaskdetails+"re"+respose, "MytaskDetailActivity");
                taskDetailBean = new Gson().fromJson(respose, TaskDetailBean.class);
                mTextview_guzhuName.setText(taskDetailBean.getData().getNick_name());
                mTextview_taskdetails.setText(taskDetailBean.getData().getTask_description());
                mTextview_yuyuetime.setText(taskDetailBean.getData().getTask_Time());
                mTextview_taskaddress.setText(taskDetailBean.getData().getRelease_address() + "-" + taskDetailBean.getData().getDetailed_address());
                String imageURL = taskDetailBean.getData().getAvatar_imgUrl().substring(0, taskDetailBean.getData().getAvatar_imgUrl().length() - 1);
                setImage(imageURL);
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.mytaskdetails, MatchShopActivity.this, 1, map);

    }

    void setImage(String image) {
        if (image == null || image.equals("")) {
        } else {
            String[] images = image.split(",");
            int len = images.length;
            LogUtils.LOG("ceshi", "图片的个数" + images.length, "SkillDetailActivity分隔图片");
            imageview_urllist.clear();
            for (int i = 0; i < len; i++) {
                imageview_urllist.add(images[i]);
            }
            adapter_gridviewpic.notifyDataSetChanged();

        }

    }
}
