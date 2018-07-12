package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.fargment.Fragment_message;
import com.jingnuo.quanmb.fargment.Fragment_person;
import com.jingnuo.quanmb.fargment.Fragment_square;
import com.jingnuo.quanmb.fargment.Fragment_still;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;
import static com.jingnuo.quanmb.data.Staticdata.static_userBean;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    public static MainActivity mainActivity;
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
    ImageView image_reddot;


    PermissionHelper permissionHelper;

    //高德定位
    int locationtime=0;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
        //注册监听函数
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR| View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }



//        ActionBar actionBar = getActionBar();
//        actionBar.setCustomView(R.mipmap.aboutus);

        initview();
        initdata();
        initlinstenner();
        setview();
        setdata();

    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                    LogUtils.LOG("ceshiqq","定位成功,纬度："+aMapLocation.getLatitude()+"精度："+ aMapLocation.getLongitude(),"mainactivity");

                    Staticdata.xValue =aMapLocation.getLatitude()+"";//获取纬度
                    Staticdata.yValue=aMapLocation.getLongitude()+"";//获取经度
                    aMapLocation.getCity();//城市信息
                    ++locationtime;
                    LogUtils.LOG("ceshiqq","定位次数："+locationtime,"mainactivity");
                    if(locationtime==1){
                        Intent intent = new Intent("com.jingnuo.quanmb.ADDRESS");
                        intent.putExtra("address",aMapLocation.getCity());
                        sendBroadcast(intent);
                    }
                    if(Staticdata.isLogin){
                        new Volley_Utils(new Interface_volley_respose() {
                            @Override
                            public void onSuccesses(String respose) {

                            }

                            @Override
                            public void onError(int error) {

                            }
                        }).Http(Urls.Baseurl+Urls.updataXYDU+Staticdata.static_userBean.getData().getUser_token()+"&x_value=" +
                                Staticdata.xValue+"&y_value="+Staticdata.yValue,MainActivity.this,0);

                    }

                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                }
            }
        }
    };

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    private void setmapdata() {
    //初始化定位
        if(mLocationClient==null){
            mLocationClient = new AMapLocationClient(getApplicationContext());
            //设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);

            //初始化AMapLocationClientOption对象
            mLocationOption = new AMapLocationClientOption();
            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.setOnceLocation(true);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(100000);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();

        }

    }

    public void initview() {
        mRelativeLayout_square = findViewById(R.id.relative_square);
        mRelativeLayout_still = findViewById(R.id.relative_still);
        mRelativeLayout_message = findViewById(R.id.relative_message);
        mRelativeLayout_person = findViewById(R.id.relative_person);
        mImage_release = findViewById(R.id.image_release);
        image_reddot = findViewById(R.id.image_reddot);
    }

    public void initdata() {
        if(mainActivity==null){
            mainActivity=this;
        }
        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        Permissionmanage permissionmanage = new Permissionmanage(permissionHelper, new InterfacePermission() {
            @Override
            public void onResult(boolean result) {
                LogUtils.LOG("ceshi", result + "", "");
                if (result) {//定位权限
                    setmapdata();// 百度地图配置参数

                } else {
                    ToastUtils.showToast(MainActivity.this, "请允许开启定位功能");
                }
            }
        });
        permissionmanage.requestpermission();


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
        LogUtils.LOG("ceshi1","onPostResume222","~~~~main");
        transaction.add(R.id.framelayout_main, mFragment_square).commit();
        ChangeBottomButton(mRelativeLayout_square);//设置帮帮广场为选中状态

    }

    public void setdata() {


    }
    public void setREDDOT(boolean isshow) {
        if(isshow){
            image_reddot.setVisibility(View.VISIBLE);
        }else {
            image_reddot.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View view) {
        transaction = fragmetnmanager.beginTransaction();
        hideFragments(transaction);//隐藏所有Fragment,需要哪个显示哪一个

        switch (view.getId()) {
            case R.id.relative_square:
//                StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
                ChangeBottomButton(mRelativeLayout_square);
                if (mFragment_square == null) {
                    mFragment_square = new Fragment_square();
                    transaction.add(R.id.framelayout_main, mFragment_square).commit();
                } else {
                    transaction.show(mFragment_square).commit();
                }

                break;
            case R.id.relative_still:
//                StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
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
//                    Intent intent_issue = new Intent(this, IssueTaskActivity.class);
//                    startActivity(intent_issue);
//                    Popwindow_Issue popwindow_issue=new Popwindow_Issue(MainActivity.this);
//                    popwindow_issue.showpopwindow();
                    Intent intend_issue_task = new Intent(this, IssueTaskActivity.class);
                    this.startActivity(intend_issue_task);
                } else {

                    Intent intent_login = new Intent(this, LoginActivity.class);
                    startActivity(intent_login);
                }

                break;
            case R.id.relative_message:
//                StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
                if (isLogin) {
                    setREDDOT(false);
                    ChangeBottomButton(mRelativeLayout_message);
                    if (mFragment_menssage == null) {
                        mFragment_menssage = new Fragment_message();
                        transaction.add(R.id.framelayout_main, mFragment_menssage).commit();
                    } else {
                        transaction.show(mFragment_menssage).commit();
                    }
                } else {
                    Intent intent_login = new Intent(this, LoginActivity.class);
                    startActivity(intent_login);
                }
                break;
            case R.id.relative_person:
//                StatusBarUtil.setColor(this, getResources().getColor(R.color.statebar), 0);//状态栏颜色
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient!=null){
            mLocationClient.stopLocation();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LogUtils.LOG("ceshi1","onPostResume111","~~~~main");
        if(!Staticdata.isLogin){
            transaction = fragmetnmanager.beginTransaction();
            ChangeBottomButton(mRelativeLayout_square);
            hideFragments(transaction);//隐藏所有Fragment,需要哪个显示哪一个
            if (mFragment_square == null) {
                mFragment_square = new Fragment_square();
                transaction.add(R.id.framelayout_main, mFragment_square).commit();
            } else {
                transaction.show(mFragment_square).commit();
            }
        }
    }
}


