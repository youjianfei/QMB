package com.jingnuo.quanmb.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.Adapter.AdapterFragment;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.Matchshoplistbean;
import com.jingnuo.quanmb.fargment.Fragment_shopdetail;
import com.jingnuo.quanmb.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MatchShopActivity extends AppCompatActivity  {

    //控件
    private ViewPager mViewPager;
    LinearLayout mtextview_change;



    //数据
    String respose="";
    List<Fragment> list_myfragments;
    List<Matchshoplistbean.DataBean.MatchingBean>list_matchbea;


    //对象
    AdapterFragment adapterFragment;
    Matchshoplistbean  matchshoplistbean;

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
        list_myfragments=new ArrayList<>();
        for (int i=0;i<list_matchbea.size();i++){
            list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i)));
            list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i)));
            list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i)));
        }
        adapterFragment=new AdapterFragment(getSupportFragmentManager(),list_myfragments);
        mViewPager.setAdapter(adapterFragment);
    }

    private void initview() {
        mViewPager = findViewById(R.id.viewPager);
        mtextview_change=findViewById(R.id.textview_change);
    }
    private  void  initlistenner(){

        mtextview_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(MatchShopActivity.this,"点击抓新");
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



}
