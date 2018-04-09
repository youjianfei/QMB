package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;

public class LocationMapActivity extends BaseActivityother {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位

    EditText mTextview_location;
    String finallocation;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_location_map;
    }

    @Override
    protected void setData() {
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
                //在此处理点击事件
                mBaiduMap.clear();
                LogUtils.LOG("ceshi","onMapClick"+point.toString(),"locationmapActivity");
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.address);      //构建Marker图标

                MarkerOptions options = new MarkerOptions()
                        .position(point)  //设置marker的位置
                        .icon(bitmap)  //设置marker图标
                        .zIndex(9)  //设置marker所在层级
                        .draggable(true);  //设置手势拖拽
                options.animateType(MarkerOptions.MarkerAnimateType.grow);
                //将marker添加到地图上
                mBaiduMap.addOverlay(options);
                bitmap.recycle();//回收bitmap
                mTextview_location.setText("");

            }
            public boolean onMapPoiClick(MapPoi poi) {
                //在此处理底图标注点击事件
                //在此处理点击事件
                mBaiduMap.clear();
                LogUtils.LOG("ceshi","onMapClick"+poi.getName(),"locationmapActivity");
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.address);      //构建Marker图标

                MarkerOptions options = new MarkerOptions()
                        .position(poi.getPosition())  //设置marker的位置
                        .icon(bitmap)  //设置marker图标
                        .zIndex(9)  //设置marker所在层级
                        .draggable(true);  //设置手势拖拽
                options.animateType(MarkerOptions.MarkerAnimateType.grow);
                //将marker添加到地图上
                mBaiduMap.addOverlay(options);
                bitmap.recycle();//回收bitmap
                finallocation=poi.getName()+"";
                mTextview_location.setText(finallocation);//设置textview文字信息
                return false;
            }
        });
    }

    @Override
    protected void initData() {

        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10000);

        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);//可选，默认false,设置是否使用gps

        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTextview_location= findViewById(R.id.textview_location);
        mMapView = findViewById(R.id.bmapView);

    }
    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(100).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(19.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                finallocation=location.getAddrStr();
                LatLng point = new LatLng(location.getLatitude(), location.getLongitude());  //定义Maker坐标点
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.address);      //构建Marker图标
                LogUtils.LOG("ceshi","getAddrStr"+location.getAddrStr(),"locationmapActivity");
                MarkerOptions options = new MarkerOptions()
                        .position(point)  //设置marker的位置
                        .icon(bitmap)  //设置marker图标
                        .zIndex(9);  //设置marker所在层级
                options.animateType(MarkerOptions.MarkerAnimateType.grow);
                //将marker添加到地图上
                mBaiduMap.addOverlay(options);
                bitmap.recycle();//回收bitmap

                LocationMapActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextview_location.setText(finallocation);//设置textview文字信息
                    }
                });
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocClient.stop();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理

        mMapView.onPause();
        mLocClient.stop();
        finallocation=mTextview_location.getText()+"";
    }
}
