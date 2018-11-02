package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.jingnuo.quanmb.entityclass.NewCouponBean;
import com.jingnuo.quanmb.fargment.Fragment_task_JiaZhengWeixiu;
import com.jingnuo.quanmb.fargment.Fragment_task_ZhaoShangHu;
import com.jingnuo.quanmb.fargment.Fragment_tsk_ZhaoRenShou;
import com.jingnuo.quanmb.popwinow.Popwindow_coupon;
import com.jingnuo.quanmb.utils.AutoUpdate;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;
import com.yancy.imageselector.ImageSelector;
import com.jingnuo.quanmb.R;

import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imkit.RongIM;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;


public class IssueTaskActivity extends FragmentActivity implements View.OnClickListener{
    public static IssueTaskActivity issueTaskActivity;
    /**
     * 公用
     */
    //控件
//    TabLayout mTablayout_task;
    TextView text_chooceaddress;
    TabLayout tablayout_issue;
    DrawerLayout drawerlayout_menu;
    CircleImageView image_dot;
    ImageView mImageview_message;//消息
    ImageView mImageview_iamge_person;//用户中心


    String xValue = "";//纬度
    String yValue = "";//经度
    String citycode = "";//城市名字
    String Aoi="";


    Fragment_task_ZhaoShangHu fragmentTaskZhaoShangHu;
    Fragment_tsk_ZhaoRenShou  fragmentTskZhaoRenShou;
    Fragment_task_JiaZhengWeixiu fragment_task_jiaZhengWeixiu;

    FragmentManager fragmetnmanager;
    FragmentTransaction transaction;
    PermissionHelper permissionHelper;

    NewCouponBean  newCouponBean;

    int Tag=0;//    0   维修   1  家政   2  其他
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_task);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
        //注册监听函数
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR| View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }2

//        ActionBar actionBar = getActionBar();
//        actionBar.setCustomView(R.mipmap.aboutus);

        initView();
        initData();
        initListener();
        setData();
    }
    //推迟显示pop
    String URL_popwindow="";
    int time = 0;
    Timer timer;
    TimerTask timerTask;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    requestCouponPopwindow(URL_popwindow);
                        if(timer!=null){
                            timer.cancel();
                            timerTask.cancel();
                        }
                        timer=null;
                    break;
            }
        }


    };
    protected void setData() {
    if(Staticdata.static_userBean.getData()==null){
        URL_popwindow=Urls.Baseurl_cui+Urls.couponPopwindow+"";
    }else {
        URL_popwindow=Urls.Baseurl_cui+Urls.couponPopwindow+Staticdata.static_userBean.getData().getUser_token();
    }
        //随机数动态变化
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mhandler.sendEmptyMessage(0);
            }
        };
        timer.schedule(timerTask, 1500);
    }
    void requestCouponPopwindow(String URL){
        LogUtils.LOG("ceshi","新用户弹窗"+URL,"tanchuang");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", "新用户弹窗" + respose, "发布任务");
                newCouponBean=new Gson().fromJson(respose,NewCouponBean.class);
                if(newCouponBean.getCode()==1){
                    if(newCouponBean.getData().getIsShow().equals("1")){
                        Urls.newpeoplecoupon=newCouponBean.getData().getBut_url();
                        new Popwindow_coupon(IssueTaskActivity.this,newCouponBean.getData().getBk_img(),newCouponBean.getData().getBut_img()).showpopwindow();
                    }
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(URL,IssueTaskActivity.this,0);
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
                    Staticdata.xValue = aMapLocation.getLatitude() + "";//获取纬度
                    Staticdata.yValue = aMapLocation.getLongitude() + "";//获取经度
                    Staticdata.city_location = aMapLocation.getCity();//城市信息
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
                    if (Staticdata.isLogin) {
                        new Volley_Utils(new Interface_volley_respose() {
                            @Override
                            public void onSuccesses(String respose) {

                            }

                            @Override
                            public void onError(int error) {

                            }
                        }).Http(Urls.Baseurl + Urls.updataXYDU + Staticdata.static_userBean.getData().getUser_token() + "&x_value=" +
                                Staticdata.xValue + "&y_value=" + Staticdata.yValue, IssueTaskActivity.this, 0);

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


    protected void initData() {

        Staticdata.suijiAcount= Utils.getNum(500,1000);
        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        Permissionmanage permissionmanage = new Permissionmanage(permissionHelper, new InterfacePermission() {
            @Override
            public void onResult(boolean result) {
                LogUtils.LOG("ceshi", result + "", "");
                if (result) {//定位权限
                    setmapdata();// 高德地图配置参数
                    updata();
                    return;
                } else {
                    ToastUtils.showToast(IssueTaskActivity.this, "请允许开启定位功能");
                }

            }
        });
        permissionmanage.requestpermission();
        if (issueTaskActivity == null) {
            issueTaskActivity = this;
        }
        fragment_task_jiaZhengWeixiu = new Fragment_task_JiaZhengWeixiu();
        fragmetnmanager = getFragmentManager();
        transaction = fragmetnmanager.beginTransaction();
        transaction.add(R.id.framelayout_main, fragment_task_jiaZhengWeixiu).commit();
        if (isLogin) {
            drawerlayout_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);//打开滑动
        } else {
            drawerlayout_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止滑动
        }

    }


    protected void initListener() {
        mImageview_iamge_person.setOnClickListener(this);
        mImageview_message.setOnClickListener(this);
                text_chooceaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent_map = new Intent(IssueTaskActivity.this, LocationMapActivity.class);
                startActivityForResult(mIntent_map, 418);
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
    protected void onRestart() {
        super.onRestart();
        drawerlayout_menu.closeDrawer(Gravity.LEFT);
    }

    protected void initView() {
        image_dot = findViewById(R.id.image_dot);
        drawerlayout_menu = findViewById(R.id.drawerlayout_menu);
        text_chooceaddress=findViewById(R.id.text_chooceaddress);
        tablayout_issue=findViewById(R.id.tablayout_issue);
        mImageview_message = findViewById(R.id.iamge_message);
        mImageview_iamge_person = findViewById(R.id.iamge_person);
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
        if (requestCode == 418 && resultCode == 418) {
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
    AutoUpdate autoUpdate;
    void updata() {

        Permissionmanage permissionmanage = new Permissionmanage(permissionHelper, new InterfacePermission() {
            @Override
            public void onResult(boolean result) {
                if (result) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//安卓7.0权限 代替了FileProvider方式   https://blog.csdn.net/xiaoyu940601/article/details/54406725
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());
                    }
                    //检测是否更新
                    autoUpdate = new AutoUpdate(IssueTaskActivity.this);
                    autoUpdate.requestVersionData();

                } else {
                    Toast.makeText(IssueTaskActivity.this, "请开启存储权限,以便安装最新版本", Toast.LENGTH_SHORT).show();
                }
            }
        });
        permissionmanage.requestpermission();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.LOG("ceshi", "onDestroy", "faburenwu");
        mLocationClient.onDestroy();//调用定位结束方法
        Staticdata.isyanshi=true;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iamge_message://消息界面
//                if (isLogin) {
//                    image_dot.setVisibility(View.INVISIBLE);
//                    intent=new Intent(MainActivity.this,MessageActivity.class);
//                    intent.putExtra("newmessageTYpe",Staticdata.newmessageTYpe);
//                    startActivity(intent);
//                    Staticdata.newmessageTYpe="notype";//跳转完之后归0
//                } else {
//                    intent = new Intent(this, LoginActivity.class);
//                    startActivity(intent);
//                }
                if (isLogin) {
                    image_dot.setVisibility(View.INVISIBLE);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);

                    intent = new Intent(IssueTaskActivity.this, ConversationListActivity.class);
                    intent.putExtra("newmessageTYpe", Staticdata.newmessageTYpe);
                    startActivity(intent);
                    Staticdata.newmessageTYpe = "notype";//跳转完之后归0
                } else {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.iamge_person://个人中心
                if (isLogin) {
                    drawerlayout_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);//打开滑动
                    drawerlayout_menu.openDrawer(Gravity.LEFT);
                } else {
                    drawerlayout_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止滑动
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
    public void setdot() {
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
}
