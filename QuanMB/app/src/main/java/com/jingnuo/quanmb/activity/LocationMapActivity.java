package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.jingnuo.quanmb.Adapter.Adapter_SearchAddress;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LocationMapActivity extends BaseActivityother {


    //控件
    EditText mEdit_location;
    TextView mTextview_nowaddress;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private ListView mListview_searchaddress;
    ImageView mImageview_cancle;
    //数据
    List<PoiInfo> mData_searchaddress;
    Adapter_SearchAddress  mAdapter_address;
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true; // 是否首次定位

    //POI城市检索
    PoiSearch mPoiSearch;
    OnGetPoiSearchResultListener poiListener;


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
                mEdit_location.setText("");

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
                mTextview_nowaddress.setText("当前位置："+finallocation);//设置textview文字信息
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        mPoiSearch = PoiSearch.newInstance();//poi检索实例化
        mData_searchaddress=new ArrayList<>();
        mAdapter_address=new Adapter_SearchAddress(mData_searchaddress,this);
        mListview_searchaddress.setAdapter(mAdapter_address);

        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
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

        poiListener = new OnGetPoiSearchResultListener(){

            public void onGetPoiResult(PoiResult result){
//                获取POI检索结果
                LogUtils.LOG("ceshi","onGetPoiResult"+result.toString(),"地图检索结果1");
                //如果搜索到的结果不为空，并且没有错误
                if (result != null && result.error == PoiResult.ERRORNO.NO_ERROR) {
                    LogUtils.LOG("ceshi","onGetPoiResult"+result.getAllPoi(),"地图检索结果1");
                    mListview_searchaddress.setVisibility(View.VISIBLE);
                    mImageview_cancle.setVisibility(View.VISIBLE);
                    mData_searchaddress.clear();
                    mData_searchaddress.addAll(result.getAllPoi());
                    mAdapter_address.notifyDataSetChanged();

                } else {
                    Toast.makeText(getApplication(), "搜索不到你需要的信息！", Toast.LENGTH_SHORT).show();
                }
            }

            public void onGetPoiDetailResult(PoiDetailResult result){
                //获取Place详情页检索结果
                LogUtils.LOG("ceshi","onGetPoiDetailResult"+result.toString(),"地图检索结果2");
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                LogUtils.LOG("ceshi","onGetPoiDetailResult"+poiIndoorResult.toString(),"地图检索结果3");
            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
    }

    @Override
    protected void initListener() {
        //监听键盘确定按钮，以便直接搜索
        mEdit_location.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    LogUtils.LOG("ceshi", "点击了确定按钮", "百度地图搜索地址");
                    String address=mEdit_location.getText()+"";
                    if(address.equals("")){
                    }else {
                        mPoiSearch.searchInCity((new PoiCitySearchOption())
                                .city("郑州")
                                .keyword(address).pageCapacity(20)
                                .pageNum(1));
                    }

                }
                return false;
            }
        });
        mImageview_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageview_cancle.setVisibility(View.GONE);
                mListview_searchaddress.setVisibility(View.GONE);
            }
        });

        mListview_searchaddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent result=new Intent();
                result.putExtra("address", mData_searchaddress.get(i).address);
                setResult(2018418,result);
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        mListview_searchaddress=findViewById(R.id.list_searchaddresslist);
        mEdit_location= findViewById(R.id.textview_location);
        mMapView = findViewById(R.id.bmapView);
        mImageview_cancle=findViewById(R.id.iamge_cancle);
        mTextview_nowaddress=findViewById(R.id.text_mapget);
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
                        mTextview_nowaddress.setText(finallocation);//设置textview文字信息
                    }
                });
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocClient.stop();
        mPoiSearch.destroy();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理

        mMapView.onPause();
        mLocClient.stop();
        mPoiSearch.destroy();
        finallocation=mEdit_location.getText()+"";
    }
}
