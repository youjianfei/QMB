package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
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
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


public class LocationMapActivity extends BaseActivityother implements AMap.OnCameraChangeListener,
        GeocodeSearch.OnGeocodeSearchListener ,PoiSearch.OnPoiSearchListener{


    //控件
    EditText mEdit_location;
    EditText mEdit_search;
    TextView mTextview_nowaddress;
    Button mBUtton_queding;

    MapView mMapview;
    private ListView mListview_searchaddress;
    ImageView mImageview_cancle;
    //数据
    Adapter_SearchAddress mAdapter_address;
    List<PoiItem>  mData_searchaddress;

    //POI城市检索
    PoiSearch.Query query;
    PoiSearch poiSearch;

    AMap aMap;
    String finallocation;//poi名称
    String address;//地址

    String xValue="";//纬度
    String yValue="";//经度
    String citycode="";//城市名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapview = findViewById(R.id.map);
        mMapview.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapview.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
//        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        aMap.setOnCameraChangeListener(this);
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
        //监听键盘确定按钮，以便直接搜索
        mEdit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    String address = mEdit_search.getText() + "";
                    if (address.equals("")) {
                    } else {
                        query = new PoiSearch.Query(address, "", "");
                        //keyWord表示搜索字符串，
                        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
                        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
                        query.setPageSize(20);// 设置每页最多返回多少条poiitem
                        query.setPageNum(0);//设置查询页码

                        poiSearch = new PoiSearch(LocationMapActivity.this, query);
                        poiSearch.setOnPoiSearchListener(LocationMapActivity.this);

                        poiSearch.searchPOIAsyn();
                    }

                }
                return false;
            }
        });
        mBUtton_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("address", mTextview_nowaddress.getText() + "");
                String add = mEdit_location.getText() + "";
//                if (add.equals("")) {
//                    ToastUtils.showToast(LocationMapActivity.this, "请输入自定义名称");
//                    return;
//                }
                result.putExtra("address2", add);
                result.putExtra("xValue", xValue);
                result.putExtra("yValue", yValue);
                result.putExtra("citycode", citycode);
                setResult(2018418, result);
                finish();
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
                mTextview_nowaddress.setText(mData_searchaddress.get(i).getTitle());
                xValue=mData_searchaddress.get(i).getLatLonPoint().getLatitude()+"";
                yValue=mData_searchaddress.get(i).getLatLonPoint().getLongitude()+"";
                citycode=mData_searchaddress.get(i).getCityName()+"";

//                mEdit_location.setText("");
//                Intent result = new Intent();
//                result.putExtra("address", mTextview_nowaddress.getText() + "");
//                String add = mEdit_location.getText() + "";
//                if (add.equals("")) {
//                    ToastUtils.showToast(LocationMapActivity.this, "请输入自定义名称");
//                    return;
//                }
//                result.putExtra("address2", add);
//                setResult(2018418, result);
//                finish();
            }
        });
    }

    @Override
    protected void initView() {

        mListview_searchaddress = findViewById(R.id.list_searchaddresslist);
        mEdit_location = findViewById(R.id.textview_location);
        mEdit_search = findViewById(R.id.edit_searchaddress);
        mImageview_cancle = findViewById(R.id.iamge_cancle);
        mTextview_nowaddress = findViewById(R.id.text_mapget);
        mBUtton_queding = findViewById(R.id.button_submit);
    }

    /**
     * 定位SDK监听函数
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapview.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapview.onPause();
        finallocation = mEdit_location.getText() + "";
    }

    GeocodeSearch geocoderSearch;

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {//地图移动
        LatLng latLng = cameraPosition.target;
//泥逆地理
        xValue=latLng.latitude+"";//纬度
        yValue=latLng.longitude+"";//经度
// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系

        aMap.clear();
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));//将定位图标移动到当前屏幕中心位置
        aMap.addMarker(new MarkerOptions().position(cameraPosition.target).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.location_icon))));
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latLng.latitude,latLng.longitude), 200,GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
//执行搜索方法
//        doSearchQuery("北京",latLng.latitude,latLng.longitude);
    }
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {//地图移动结束

    }


    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
//        LogUtils.LOG("ceshi",regeocodeResult.getRegeocodeAddress().getStreetNumber().getStreet()+"1"+
//                        regeocodeResult.getRegeocodeAddress().getStreetNumber().getNumber()+"1"+
//                        regeocodeResult.getRegeocodeAddress().getStreetNumber().getDistance()+"1"+
//                        regeocodeResult.getRegeocodeAddress().getStreetNumber().getLatLonPoint()+"1"+
//                        regeocodeResult.getRegeocodeAddress().getCity()+"2"+
//                        regeocodeResult.getRegeocodeAddress().getDistrict()+"3"+
//                        regeocodeResult.getRegeocodeAddress().getFormatAddress()+"4"+
//                        regeocodeResult.getRegeocodeAddress().getNeighborhood()+"5"
//                ,"skdafjskafjsadf");
        String tichu=regeocodeResult.getRegeocodeAddress().getProvince()+regeocodeResult.getRegeocodeAddress().getCity();
        String all=regeocodeResult.getRegeocodeAddress().getFormatAddress();
        String xianshi=all.replace(tichu,"");
        mTextview_nowaddress.setText(xianshi);
        citycode=regeocodeResult.getRegeocodeAddress().getCity();

//        mEdit_location.setText(regeocodeResult.getRegeocodeAddress().getFormatAddress());
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
                    ToastUtils.showToast(LocationMapActivity.this,"没有此数据");
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
            mListview_searchaddress.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
