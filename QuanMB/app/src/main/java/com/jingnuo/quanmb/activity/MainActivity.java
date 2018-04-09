package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.fargment.Fragment_message;
import com.jingnuo.quanmb.fargment.Fragment_person;
import com.jingnuo.quanmb.fargment.Fragment_square;
import com.jingnuo.quanmb.fargment.Fragment_still;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //布局
    Fragment_square mFragment_square;
    Fragment_still mFragment_stilll;
    Fragment_message mFragment_menssage;
    Fragment_person mFragment_person;

    FragmentManager fragmetnmanager;
    FragmentTransaction transaction;
    //控件
    private RelativeLayout mRelativeLayout_square, mRelativeLayout_still, mRelativeLayout_message, mRelativeLayout_person;
    ImageView mImage_release;


    //百度地图相关
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.statebar), 0);//状态栏颜色
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        setmapdata();// 百度地图配置参数
        initview();
        initdata();
        initlinstenner();
        setview();
        setdata();

    }

    private void setmapdata() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标
        option.setScanSpan(0);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
    }

    public void initview() {
        mRelativeLayout_square = findViewById(R.id.relative_square);
        mRelativeLayout_still = findViewById(R.id.relative_still);
        mRelativeLayout_message = findViewById(R.id.relative_message);
        mRelativeLayout_person = findViewById(R.id.relative_person);
        mImage_release = findViewById(R.id.image_release);
    }

    public void initdata() {
        mLocationClient.start();//百度地图获取当前位置经纬度
        mFragment_square = new Fragment_square();
        fragmetnmanager = getSupportFragmentManager();
        transaction = fragmetnmanager.beginTransaction();
    }

    public void initlinstenner() {
        mRelativeLayout_square.setOnClickListener(this);
        mRelativeLayout_still.setOnClickListener(this);
        mImage_release.setOnClickListener(this);
        mRelativeLayout_message.setOnClickListener(this);
        mRelativeLayout_person.setOnClickListener(this);
    }

    public void setview() {
        transaction.add(R.id.framelayout_main, mFragment_square).commit();
        ChangeBottomButton(mRelativeLayout_square);//设置帮帮广场为选中状态

    }

    public void setdata() {


    }


    @Override
    public void onClick(View view) {
        transaction = fragmetnmanager.beginTransaction();
        hideFragments(transaction);//隐藏所有Fragment,需要哪个显示哪一个

        switch (view.getId()) {
            case R.id.relative_square:
                LogUtils.LOG("ceshi", "帮帮广场被点击", "mainactivity");
                ChangeBottomButton(mRelativeLayout_square);
                if (mFragment_square == null) {
                    mFragment_square = new Fragment_square();
                    transaction.add(R.id.framelayout_main, mFragment_square).commit();
                } else {
                    transaction.show(mFragment_square).commit();
                }

                break;
            case R.id.relative_still:
                LogUtils.LOG("ceshi", "技能被点击", "mainactivity");
                ChangeBottomButton(mRelativeLayout_still);
                if (mFragment_stilll == null) {
                    mFragment_stilll = new Fragment_still();
                    transaction.add(R.id.framelayout_main, mFragment_stilll).commit();
                } else {
                    transaction.show(mFragment_stilll).commit();
                }

                break;
            case R.id.image_release:
                if (isLogin) {
                    Intent intent_issue = new Intent(this, IssueTaskActivity.class);
                    startActivity(intent_issue);
                } else {
                    Intent intent_login = new Intent(this, LoginActivity.class);
                    startActivity(intent_login);
                }

                break;
            case R.id.relative_message:
                LogUtils.LOG("ceshi", "消息被点击", "mainactivity");
                ChangeBottomButton(mRelativeLayout_message);
                if (mFragment_menssage == null) {
                    mFragment_menssage = new Fragment_message();
                    transaction.add(R.id.framelayout_main, mFragment_menssage).commit();
                } else {
                    transaction.show(mFragment_menssage).commit();
                }
                break;
            case R.id.relative_person:
                LogUtils.LOG("ceshi", "个人中心被点击" + isLogin, "mainactivity");
                if (isLogin) {
                    ChangeBottomButton(mRelativeLayout_person);
                    if (mFragment_person == null) {
                        mFragment_person = new Fragment_person();
                        transaction.add(R.id.framelayout_main, mFragment_person).commit();
                    } else {
                        transaction.show(mFragment_person).commit();
                    }
                } else {
                    Intent intent_login = new Intent(this, LoginActivity.class);
                    startActivity(intent_login);
                }


                break;
        }

    }

    private void hideFragments(FragmentTransaction transaction) {//隐藏Fragment,以便点击时展映相应的Fragment
        if (mFragment_square != null) {
            transaction.hide(mFragment_square);
        }
        if (mFragment_stilll != null) {
            transaction.hide(mFragment_stilll);
        }
        if (mFragment_menssage != null) {
            transaction.hide(mFragment_menssage);
        }
        if (mFragment_person != null) {
            transaction.hide(mFragment_person);
        }
    }

    private void ChangeBottomButton(RelativeLayout rl) {//控制底部按钮颜色的变化
        mRelativeLayout_square.getChildAt(0).setSelected(false);
        mRelativeLayout_square.getChildAt(1).setSelected(false);
        mRelativeLayout_still.getChildAt(0).setSelected(false);
        mRelativeLayout_still.getChildAt(1).setSelected(false);
        mRelativeLayout_message.getChildAt(0).setSelected(false);
        mRelativeLayout_message.getChildAt(1).setSelected(false);
        mRelativeLayout_person.getChildAt(0).setSelected(false);
        mRelativeLayout_person.getChildAt(1).setSelected(false);
        rl.getChildAt(0).setSelected(true);
        rl.getChildAt(1).setSelected(true);
    }


    /**
     * 再点一次退出
     */
    private long mLastTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastTime > 2000) {
            // 两次返回时间超出两秒
            Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
            mLastTime = System.currentTimeMillis();
        } else {
//                    if(fragment_classification!=null){
//                        fragment_classification=null;
//                    }
            // 两次返回时间小于两秒，可以退出
            finish();
        }
    }
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f

            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

            LogUtils.LOG("ceshi","纬度信息"+latitude+"经度信息"+longitude+"定位精度"+radius+"经纬度坐标类型"+coorType+"错误返回码"+errorCode,"mainactivity");
        }
    }

}


