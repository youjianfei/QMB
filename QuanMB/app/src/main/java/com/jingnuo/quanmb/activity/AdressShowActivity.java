package com.jingnuo.quanmb.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.R;

import java.util.ArrayList;

public class AdressShowActivity extends Activity implements View.OnClickListener{

    ImageView mImageview;
    MapView mMapview;

    AMap aMap;
    CameraUpdate cameraUpdate;
    private UiSettings mUiSettings;
    String x_vlaue="";
    String y_vlaue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress_show);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
        mMapview = findViewById(R.id.map);
        mImageview=findViewById(R.id.iv_back);
        mImageview.setOnClickListener(this);

        mMapview.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapview.getMap();
        }
        Intent intent=getIntent();
        x_vlaue=intent.getStringExtra("x_vlaue");
        y_vlaue=intent.getStringExtra("y_vlaue");
        mUiSettings=aMap.getUiSettings();
        mUiSettings.setMyLocationButtonEnabled(false);
        mUiSettings.setScrollGesturesEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        LatLng latLng=new LatLng(Double.parseDouble(x_vlaue),Double.parseDouble(y_vlaue));
        //改变可视区域为指定位置
        //CameraPosition4个参数分别为位置，缩放级别，目标可视区域倾斜度，可视区域指向方向（正北逆时针算起，0-360）
//                cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,16,0,30));
        cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,16,0,0));
        aMap.animateCamera(cameraUpdate);//地图移向指定区域  带动画
//                aMap.moveCamera(cameraUpdate);//地图移向指定区域  不带动画
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    }
}

