package com.jingnuo.quanmb.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.fargment.Fragment_task_JiaZhengWeixiu;
import com.jingnuo.quanmb.fargment.Fragment_task_ZhaoShangHu;
import com.jingnuo.quanmb.fargment.Fragment_tsk_ZhaoRenShou;
import com.jingnuo.quanmb.utils.LogUtils;
import com.yancy.imageselector.ImageSelector;
import com.jingnuo.quanmb.R;


public class IssueTaskActivity extends BaseActivityother {

    /**
     * 公用
     */
    TabLayout mTablayout_task;

    private int[] images = new int[]{
            R.drawable.tablayout_image_zhaoshanghu,
            R.drawable.tablayout_image_zhaorenshou,
            R.drawable.tablayout_image_zhaojiazheng};
    private String[] tabs = new String[]{"找商户", "找人手", "家政维修"};

    String xValue = "";//纬度
    String yValue = "";//经度
    String citycode = "";//城市名字
    String Aoi="";


    Fragment_task_ZhaoShangHu fragmentTaskZhaoShangHu;
    Fragment_tsk_ZhaoRenShou  fragmentTskZhaoRenShou;
    Fragment_task_JiaZhengWeixiu fragment_task_jiaZhengWeixiu;

    FragmentManager fragmetnmanager;
    FragmentTransaction transaction;

    int Tag=0;//   0找商户  1  找人手   2   家政维修


    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task;
    }

    @Override
    protected void setData() {
        setmapdata();
    }

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    LogUtils.LOG("ceshi", "定位成功" + aMapLocation.getAddress(), "发布任务");
                    xValue = aMapLocation.getLatitude() + "";//获取纬度
                    yValue = aMapLocation.getLongitude() + "";//获取经度
                    citycode = aMapLocation.getCity();//城市信息
                     Aoi = aMapLocation.getAoiName() + "";
                    Staticdata.aoi=Aoi;
                    if (Aoi.equals("")) {

                    } else {
                        if(fragmentTaskZhaoShangHu!=null){
                            fragmentTaskZhaoShangHu.setAddress(Aoi);
                        }
                    }

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                }
            }
        }
    };

    private void setmapdata() {
        //初始化定位
        mLocationClient = new AMapLocationClient(IssueTaskActivity.this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void initData() {
        fragmentTaskZhaoShangHu = new Fragment_task_ZhaoShangHu();
        fragmetnmanager = getFragmentManager();
        transaction = fragmetnmanager.beginTransaction();
        transaction.add(R.id.framelayout_main, fragmentTaskZhaoShangHu).commit();

    }

    @Override
    protected void initListener() {


        mTablayout_task.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.LOG("ceshi", tab.getTag() + "", "MyOrderActivity");
                transaction = fragmetnmanager.beginTransaction();
                if (tab.getTag().equals("找商户")) {
                    Tag=0;
                    if (fragmentTaskZhaoShangHu == null) {
                        fragmentTaskZhaoShangHu = new Fragment_task_ZhaoShangHu();
                        transaction.replace(R.id.framelayout_main, fragmentTaskZhaoShangHu).commit();
                    } else {
                        transaction.replace(R.id.framelayout_main, fragmentTaskZhaoShangHu).commit();
                    }
                }
                if (tab.getTag().equals("找人手")) {
                    Tag=1;
                    if (fragmentTskZhaoRenShou == null) {
                        fragmentTskZhaoRenShou = new Fragment_tsk_ZhaoRenShou();
                        transaction.replace(R.id.framelayout_main, fragmentTskZhaoRenShou).commit();
                    } else {
                        transaction.replace(R.id.framelayout_main, fragmentTskZhaoRenShou).commit();
                    }
                }
                if (tab.getTag().equals("家政维修")) {
                    Tag=2;
                    if (fragment_task_jiaZhengWeixiu == null) {
                        fragment_task_jiaZhengWeixiu = new Fragment_task_JiaZhengWeixiu();
                        transaction.replace(R.id.framelayout_main, fragment_task_jiaZhengWeixiu).commit();
                    } else {
                        transaction.replace(R.id.framelayout_main, fragment_task_jiaZhengWeixiu).commit();
                    }
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    @Override
    protected void initView() {
        mTablayout_task = findViewById(R.id.tablayout);
        mTablayout_task.addTab(mTablayout_task.newTab().setIcon(images[0]).setText(tabs[0]).setTag("找商户"), true);
        mTablayout_task.addTab(mTablayout_task.newTab().setIcon(images[1]).setText(tabs[1]).setTag("找人手"), false);
        mTablayout_task.addTab(mTablayout_task.newTab().setIcon(images[2]).setText(tabs[2]).setTag("家政维修"), false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            if(Tag==0&&fragmentTaskZhaoShangHu!=null){
                fragmentTaskZhaoShangHu.setview(data);
            }

            if(Tag==1&&fragmentTskZhaoRenShou!=null){
                fragmentTskZhaoRenShou.setview(data);
            }

            if(Tag==2&&fragment_task_jiaZhengWeixiu!=null){
                fragment_task_jiaZhengWeixiu.setview(data);
            }
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.LOG("ceshi", "onDestroy", "faburenwu");
        mLocationClient.onDestroy();//调用定位结束方法
    }
}
