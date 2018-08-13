package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.jingnuo.quanmb.Adapter.Adapter_SearchAddress;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.List;


public class LocationMapActivity extends BaseActivityother implements AMap.OnCameraChangeListener,
        GeocodeSearch.OnGeocodeSearchListener,PoiSearch.OnPoiSearchListener{


//    GeocodeSearch.OnGeocodeSearchListener ,
    //控件
    TextView mText_location;
    EditText mEdit_search;
    TextView mTextview_nowaddress;
    Button mBUtton_queding;

    MapView mMapview;
    private ListView mListview_searchaddress;
    TextView mTextview_cancle;
    ImageView mImageview_location;
    //数据
    Adapter_SearchAddress mAdapter_address;
    List<PoiItem>  mData_searchaddress;

    CameraUpdate cameraUpdate;
    //POI城市检索
    PoiSearch.Query query;
    PoiSearch poiSearch;

    AMap aMap;
    String finallocation;//poi名称
    String address;//地址
    MyLocationStyle myLocationStyle;
    String xValue="";//纬度
    String yValue="";//经度
    String citycode="";//城市名

    boolean isSearch=true;//因为搜索后会走 地图移动方法，这里判断 不让走地图移动方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapview = findViewById(R.id.map);
        mMapview.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapview.getMap();
        }

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
//        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        aMap.setOnCameraChangeListener(this);// 对amap添加移动地图事件监听器
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);


        mData_searchaddress=new ArrayList<>();
        mAdapter_address=new Adapter_SearchAddress(mData_searchaddress,this);
        mListview_searchaddress.setAdapter(mAdapter_address);
    }

    @Override
    public int setLayoutResID() {
        return R.layout.activity_location_map;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {
        mEdit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = "";
                search = mEdit_search.getText() + "";
                if (search.equals("")) {
                } else {
                    query = new PoiSearch.Query(search, "", citycode);
                    //keyWord表示搜索字符串，
                    //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
                    //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
                    query.setPageSize(30);// 设置每页最多返回多少条poiitem
                    query.setPageNum(0);//设置查询页码

                    poiSearch = new PoiSearch(LocationMapActivity.this, query);
                    poiSearch.setOnPoiSearchListener(LocationMapActivity.this);
                    poiSearch.searchPOIAsyn();
                    isSearch=false;
                }

            }
        });
        mBUtton_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("address", mTextview_nowaddress.getText() + "");
                String add = mText_location.getText() + "";

                result.putExtra("address2", add);
                result.putExtra("xValue", xValue);
                result.putExtra("yValue", yValue);
                result.putExtra("citycode", citycode);
                setResult(2018418, result);
                finish();
            }
        });
        mTextview_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListview_searchaddress.setVisibility(View.GONE);
                mBUtton_queding.setVisibility(View.VISIBLE);
            }
        });

        mListview_searchaddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mEdit_search.setText("");
                mTextview_nowaddress.setText(mData_searchaddress.get(i).getTitle());
                mText_location.setText(mData_searchaddress.get(i).getSnippet());
                xValue=mData_searchaddress.get(i).getLatLonPoint().getLatitude()+"";
                yValue=mData_searchaddress.get(i).getLatLonPoint().getLongitude()+"";
                citycode=mData_searchaddress.get(i).getCityName()+"";



                LatLng latLng=new LatLng(Double.parseDouble(xValue),Double.parseDouble(yValue));
                //改变可视区域为指定位置
                //CameraPosition4个参数分别为位置，缩放级别，目标可视区域倾斜度，可视区域指向方向（正北逆时针算起，0-360）
//                cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,16,0,30));
                cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,16,0,0));
                aMap.animateCamera(cameraUpdate);//地图移向指定区域  带动画
//                aMap.moveCamera(cameraUpdate);//地图移向指定区域  不带动画
                mListview_searchaddress.setVisibility(View.GONE);
                mBUtton_queding.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void initView() {

        mListview_searchaddress = findViewById(R.id.list_searchaddresslist);
        mText_location = findViewById(R.id.textview_location);
        mEdit_search = findViewById(R.id.edit_searchaddress);
        mTextview_cancle = findViewById(R.id.iamge_cancle);
        mImageview_location = findViewById(R.id.iamge_location);
        mTextview_nowaddress = findViewById(R.id.text_mapget);
        mBUtton_queding = findViewById(R.id.button_submit);
    }

    /**
     * 定位SDK监听函数
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.LOG("ceshi","onDestroy","sss");
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapview.onDestroy();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapview.onSaveInstanceState(outState);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapview.onPause();
        finallocation = mText_location.getText() + "";
    }

    GeocodeSearch geocoderSearch;

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {//地图移动
        mImageview_location.setImageResource(R.mipmap.location_icon2);
        //执行搜索方法
            //        doSearchQuery("北京",latLng.latitude,latLng.longitude);
    }
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {//地图移动结束
        mImageview_location.setImageResource(R.mipmap.location_icon);

        LatLng latLng = cameraPosition.target;
        //泥逆地理
        xValue=latLng.latitude+"";//纬度
        yValue=latLng.longitude+"";//经度
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系

        aMap.clear();
////        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));//将定位图标移动到当前屏幕中心位置
//        aMap.addMarker(new MarkerOptions().position(cameraPosition.target).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
//                .decodeResource(getResources(),R.mipmap.location_icon))));
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latLng.latitude,latLng.longitude), 200,GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
//        doSearchQuery();
    }
    protected void doSearchQuery() {
        query = new PoiSearch.Query("", "", citycode);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);// 设置查第一页

            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(Double.parseDouble(xValue),
                    Double.parseDouble(yValue)), 1000,true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        LogUtils.LOG("ceshi",isSearch+"","bbbb");
        if(isSearch){
            String AOi=regeocodeResult.getRegeocodeAddress().getAois().get(0).getAoiName();
            String tichu=regeocodeResult.getRegeocodeAddress().getProvince()+regeocodeResult.getRegeocodeAddress().getCity();
            String all=regeocodeResult.getRegeocodeAddress().getFormatAddress();
            String xianshi=all.replace(tichu,"");
            mTextview_nowaddress.setText(AOi);
            citycode=regeocodeResult.getRegeocodeAddress().getCity();
            mText_location.setText(xianshi);
            LogUtils.LOG("ceshi",isSearch+"","s");
        }
       isSearch=true;


    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }



    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

        LogUtils.LOG("ceshi",i+"","poi检索接货");


        if(poiResult.getPois().size()==0){
            mData_searchaddress.clear();
            mAdapter_address.notifyDataSetChanged();
            mListview_searchaddress.setVisibility(View.GONE);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showToast(LocationMapActivity.this,"没有该地点");
                }
            });

        }else {
//            LogUtils.LOG("ceshi",poiResult.getPois().get(1).getSnippet().toString()+"&"+
//                            poiResult.getPois().get(1).getTitle().toString()+"&"+
//                            poiResult.getPois().get(1).getCityName()+"&"+
//                            poiResult.getPois().get(1).getProvinceName()+"&"
//
//                    ,"poi检索接货222");
            mData_searchaddress.clear();
            mData_searchaddress.addAll(poiResult.getPois());
            mAdapter_address.notifyDataSetChanged();
            if(mData_searchaddress.size()>0){
                mListview_searchaddress.setSelection(0);
            }
            mListview_searchaddress.setVisibility(View.VISIBLE);
            mBUtton_queding.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

}
