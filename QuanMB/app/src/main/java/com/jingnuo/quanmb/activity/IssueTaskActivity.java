package com.jingnuo.quanmb.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.fargment.Fragment_task_JiaZhengWeixiu;
import com.jingnuo.quanmb.fargment.Fragment_task_ZhaoShangHu;
import com.jingnuo.quanmb.fargment.Fragment_tsk_ZhaoRenShou;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.yancy.imageselector.ImageSelector;
import com.jingnuo.quanmb.R;


public class IssueTaskActivity extends BaseActivityother {

    /**
     * 公用
     */
    //控件
//    TabLayout mTablayout_task;
    TextView text_chooceaddress;
    TabLayout tablayout_issue;


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
                    xValue = aMapLocation.getLatitude() + "";//获取纬度
                    yValue = aMapLocation.getLongitude() + "";//获取经度
                    citycode = aMapLocation.getCity();//城市信息
                     Aoi = aMapLocation.getAoiName() + "";
                    LogUtils.LOG("ceshi", "定位成功" + aMapLocation.getPoiName()+"11"+Aoi, "发布任务");

                    if (Aoi.equals("")) {
                        Staticdata.aoi=aMapLocation.getPoiName();
                        text_chooceaddress.setText(Staticdata.aoi);
//                        if(fragmentTaskZhaoShangHu!=null){
//                            fragmentTaskZhaoShangHu.setAddress(Staticdata.aoi);
//                        }
                    } else {
                        Staticdata.aoi=Aoi;
                        text_chooceaddress.setText(Aoi);
//                        if(fragmentTaskZhaoShangHu!=null){
//                            fragmentTaskZhaoShangHu.setAddress(Aoi);
//                        }
                    }
                    if(aMapLocation.getPoiName().equals("")&&Aoi.equals("")){
                        Staticdata.aoi="选择地址" ;
                        text_chooceaddress.setText(Staticdata.aoi);
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
        Staticdata.suijiAcount= Utils.getNum(500,1000);
        fragment_task_jiaZhengWeixiu = new Fragment_task_JiaZhengWeixiu();
        fragmetnmanager = getFragmentManager();
        transaction = fragmetnmanager.beginTransaction();
        transaction.add(R.id.framelayout_main, fragment_task_jiaZhengWeixiu).commit();
    }

    @Override
    protected void initListener() {
                text_chooceaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent_map = new Intent(IssueTaskActivity.this, LocationMapActivity.class);
                startActivityForResult(mIntent_map, 2018418);
            }
        });
    tablayout_issue.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
           switch (tab.getTag().toString()){
               case "0":
                   //逻辑
                   Tag=0;
                   transaction = fragmetnmanager.beginTransaction();
                   if (fragment_task_jiaZhengWeixiu == null) {
                       fragment_task_jiaZhengWeixiu = new Fragment_task_JiaZhengWeixiu();
                       transaction.replace(R.id.framelayout_main, fragment_task_jiaZhengWeixiu).commit();
                   } else {
                       transaction.replace(R.id.framelayout_main, fragment_task_jiaZhengWeixiu).commit();
                   }
                   break;
               case "1":
                   Tag=1;
                   transaction = fragmetnmanager.beginTransaction();
                   if (fragmentTskZhaoRenShou == null) {
                       fragmentTskZhaoRenShou = new Fragment_tsk_ZhaoRenShou();
                       transaction.replace(R.id.framelayout_main, fragmentTskZhaoRenShou).commit();
                   } else {
                       transaction.replace(R.id.framelayout_main, fragmentTskZhaoRenShou).commit();
                   }
                   break;
               case "2":
                   Tag=2;
                   transaction = fragmetnmanager.beginTransaction();
                   if (fragmentTaskZhaoShangHu == null) {
                       fragmentTaskZhaoShangHu = new Fragment_task_ZhaoShangHu();
                       transaction.replace(R.id.framelayout_main, fragmentTaskZhaoShangHu).commit();
                   } else {
                       transaction.replace(R.id.framelayout_main, fragmentTaskZhaoShangHu).commit();
                   }
                   break;
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
        text_chooceaddress=findViewById(R.id.text_chooceaddress);
        tablayout_issue=findViewById(R.id.tablayout_issue);
        tablayout_issue.addTab(tablayout_issue.newTab().setText("维修").setTag("0"));
        tablayout_issue.addTab(tablayout_issue.newTab().setText("家政").setTag("1"));
        tablayout_issue.addTab(tablayout_issue.newTab().setText("其他").setTag("2"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            if(Tag==0&&fragment_task_jiaZhengWeixiu!=null){
                fragment_task_jiaZhengWeixiu.setview(data);
            }

            if(Tag==1&&fragmentTskZhaoRenShou!=null){
                fragmentTskZhaoRenShou.setview(data);
            }
            if(Tag==2&&fragmentTaskZhaoShangHu!=null){
                fragmentTaskZhaoShangHu.setview(data);
            }

//            if(Tag==2&&fragment_task_jiaZhengWeixiu!=null){
//                fragment_task_jiaZhengWeixiu.setview(data);
//            }
        }
        if (requestCode == 2018418 && resultCode == 2018418) {
            text_chooceaddress.setText(data.getStringExtra("address"));
            if(Tag==0&&fragment_task_jiaZhengWeixiu!=null){
                fragment_task_jiaZhengWeixiu.setAddress(data);
            }

            if(Tag==1&&fragmentTskZhaoRenShou!=null){
                fragmentTskZhaoRenShou.setAddress(data);
            }
            if(Tag==2&&fragmentTaskZhaoShangHu!=null){
                fragmentTaskZhaoShangHu.setAddress(data);
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
