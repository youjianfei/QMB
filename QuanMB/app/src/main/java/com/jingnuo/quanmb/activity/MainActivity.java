package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.ShouyeRadios;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;
import com.jingnuo.quanmb.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    public static MainActivity mainActivity;
    //控件
    DrawerLayout drawerlayout_menu;

    CircleImageView image_dot;
    ImageView mImageview_message;//消息
    ImageView mImageview_iamge_person;//用户中心
    ImageView mImageview_help;//帮忙
    ImageView mImageview_needhelp;//求助
    private TextSwitcher tv_notice;//轮播通知文字

    TextView mTextview_neerbytask;//附近服务
    TextView mTextview_lovetask;//爱心帮
    TextView mTextview_myshequ;//我的社区

    PermissionHelper permissionHelper;



    //高德定位
    int locationtime=0;



    private String[] mAdvertisements ;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
        //注册监听函数
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR| View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

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
                    Staticdata. city_location=aMapLocation.getCity();//城市信息
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
        drawerlayout_menu=findViewById(R.id.drawerlayout_menu);
        image_dot=findViewById(R.id.image_dot);
        mImageview_message=findViewById(R.id.iamge_message);
        mImageview_iamge_person=findViewById(R.id.iamge_person);
        mImageview_help=findViewById(R.id.image_help);
        mImageview_needhelp=findViewById(R.id.image_needhelp);
        mTextview_neerbytask=findViewById(R.id.text_neerbytask);
        mTextview_lovetask=findViewById(R.id.text_lovetask);
        mTextview_myshequ=findViewById(R.id.text_myshequ);
        tv_notice = findViewById(R.id.tv_notice);
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


    }

    public void initlinstenner() {
        mImageview_message.setOnClickListener(this);
        mImageview_help.setOnClickListener(this);
        mImageview_needhelp.setOnClickListener(this);
        mTextview_neerbytask.setOnClickListener(this);
        mTextview_lovetask.setOnClickListener(this);
        mTextview_myshequ.setOnClickListener(this);
        mImageview_iamge_person.setOnClickListener(this);

    }

    public void setview() {
        tv_notice.setFactory(new ViewSwitcher.ViewFactory() {
            // 这里用来创建内部的视图，这里创建TextView，用来显示文字
            @RequiresApi(api = Build.VERSION_CODES.M)
            public View makeView() {
                TextView tv = new TextView(MainActivity.this);
                // 设置文字的显示单位以及文字的大小
                tv.setTextSize(12);
                tv.setTextColor(getColor(R.color.gray_969696));
                return tv;
            }
        });
        tv_notice.setInAnimation(MainActivity.this,
                R.anim.slide_in_bottom);
        tv_notice.setOutAnimation(getApplicationContext(), R.anim.slide_out_up);

    }
    private final int HOME_AD_RESULT = 1;
    private int mSwitcherCount=0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                // 广告
                case HOME_AD_RESULT:
                    tv_notice.setText(data.get(mSwitcherCount % data.size()).getContent());
                    mSwitcherCount++;
                    mHandler.sendEmptyMessageDelayed(HOME_AD_RESULT, 3000);
                    break;
            }

        }
    };
    public void setdata() {
    if(isLogin){
        drawerlayout_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);//打开滑动
    }else {
        drawerlayout_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止滑动
    }
        data=new ArrayList<>();
        requestRadios();
    }
    List<ShouyeRadios.DataBean> data;
    void requestRadios(){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose+"喇叭","首页");
                data.clear();
                data.addAll(new Gson().fromJson(respose,ShouyeRadios.class).getData());
                mHandler.sendEmptyMessage(HOME_AD_RESULT);
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_hu+Urls.shouyeRadios,this,0);
    }

    @Override
    public void onClick(View view) {
            Intent intent;
        switch (view.getId()) {
            case R.id.image_help://广场列表
                intent=new Intent(MainActivity.this,SquareActuvity.class);
                startActivity(intent);

                break;
            case R.id.text_myshequ://我的社区
                if (Staticdata.isLogin){
                    if(Staticdata.static_userBean.getData().getAppuser().getCommunity_code().equals("")){
                        ToastUtils.showToast(this,"请先绑定社区");
                         intent=new Intent(this, ShezhishequActivity.class);
                        startActivity(intent);
                        return;
                    }
                    intent=new Intent(this, MyShequActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.image_needhelp://发布服务
                if (isLogin) {
                     intent = new Intent(this, IssueTaskActivity.class);
                    this.startActivity(intent);
                } else {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.iamge_message://消息界面
                if (isLogin) {
                    image_dot.setVisibility(View.INVISIBLE);
                    intent=new Intent(MainActivity.this,MessageActivity.class);
                    intent.putExtra("newmessageTYpe",Staticdata.newmessageTYpe);
                    startActivity(intent);
                    Staticdata.newmessageTYpe="notype";//跳转完之后归0
                } else {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.text_lovetask://爱心帮
                intent=new Intent(MainActivity.this, LoveTaskActivity.class);
                startActivity(intent);
                break;

            case R.id.iamge_person://个人中心
                if(isLogin){
                    drawerlayout_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);//打开滑动
                    drawerlayout_menu.openDrawer(Gravity.LEFT);
                }else {
                    drawerlayout_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止滑动
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.text_neerbytask:
                intent=new Intent(this,SkillActivity.class);
                startActivity(intent);
                break;
        }

    }

    public  void setdot(){
        image_dot.setVisibility(View.VISIBLE);
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
            mLocationClient.onDestroy();//调用定位结束方法
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LogUtils.LOG("ceshi1","onPostResume111","~~~~main");
        if(!Staticdata.isLogin){
        }
    }
}


