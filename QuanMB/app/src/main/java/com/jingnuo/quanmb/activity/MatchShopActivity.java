package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.Adapter.AdapterFragment;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_skillsdetails;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Matchshoplistbean;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.fargment.Fragment_shopdetail;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.popwinow.Popwindow_cancleorder;
import com.jingnuo.quanmb.popwinow.Popwindow_cancleorder_success;
import com.jingnuo.quanmb.popwinow.ProgressDlog;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.master.permissionhelper.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class MatchShopActivity extends AppCompatActivity  {

    //控件
    ImageView iv_back;
//    ImageView image_left;
//    ImageView image_right;
    private ViewPager mViewPager;
    LinearLayout mtextview_change;//换一批
//    TextView text_timelow;
    LinearLayout iamge_newacount;

    LinearLayout linearLayout_cancle;//撤销
    LinearLayout linearLayout_callphone;//电话
    LinearLayout linearlayout_zixun;//咨询
    LinearLayout linearLayout_choose;//预约下单



    //数据
    String ID = "";
    String order_no = "";
    String isshow = "";
    String respose="";
    List<Fragment> list_myfragments;
    List<Matchshoplistbean.DataBean.MatchingBean>list_matchbea;//匹配的商户对象数组

    List<String> imageview_urllist;
    Map map_choosebissness;//下单map

    int page=0;//viewpager  默认展示第1个
    int page_all=0;//viewpager  一共多少页；



    //对象
    KProgressHUD mKProgressHUD;

    AdapterFragment adapterFragment;
    Matchshoplistbean  matchshoplistbean;
    Matchshoplistbean.DataBean.MatchingBean matchingBean;

    TaskDetailBean taskDetailBean;
    Adapter_Gridviewpic_skillsdetails adapter_gridviewpic;//图片展示适配器

    PermissionHelper mPermission;//动态申请权限
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_shop);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
        respose=getIntent().getStringExtra("respose");
        mKProgressHUD = new KProgressHUD(MatchShopActivity.this);
        matchshoplistbean=new Gson().fromJson(respose,Matchshoplistbean.class);
        list_matchbea=new ArrayList<>();
        list_matchbea.clear();
        list_matchbea.addAll(matchshoplistbean.getData().getMatching());
        page_all=list_matchbea.size()-1;
        mPermission = new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        initview();
        initdata();
        initlistenner();
    }

    private void initdata() {
//        if(page_all==0){
//            image_right.setVisibility(View.INVISIBLE);
//        }
        Staticdata.ScreenHight = SizeUtils.getScreenHeightPx(this);
        Staticdata.ScreenWidth = SizeUtils.getScreenWidthPx(this);
        ID = getIntent().getStringExtra("id");
        order_no = matchshoplistbean.getData().getOrder_no();
        isshow = matchshoplistbean.getData().getIsShow();
        imageview_urllist=new ArrayList<>();//图片展示
        adapter_gridviewpic=new Adapter_Gridviewpic_skillsdetails(imageview_urllist,MatchShopActivity.this);
        list_myfragments=new ArrayList<>();
        for (int i=0;i<list_matchbea.size();i++){
            list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i),ID));
        }
        adapterFragment=new AdapterFragment(getSupportFragmentManager(),list_myfragments);
        mViewPager.setAdapter(adapterFragment);

    }

    private void initview() {
        iv_back = findViewById(R.id.iv_back);
//        image_left = findViewById(R.id.image_left);
//        image_right = findViewById(R.id.image_right);
        mViewPager = findViewById(R.id.viewPager);
        mtextview_change=findViewById(R.id.textview_change);
        linearLayout_callphone=findViewById(R.id.linearLayout_callphone);
        linearLayout_cancle=findViewById(R.id.linearLayout_cancle);
        linearLayout_choose=findViewById(R.id.linearLayout_choose);
        linearlayout_zixun=findViewById(R.id.linearlayout_zixun);
//        text_timelow=findViewById(R.id.text_timelow);
        iamge_newacount=findViewById(R.id.iamge_newacount);

        if(isshow.equals("1")){
            ImageView image = new ImageView(MatchShopActivity.this);
//            Glide.with(MatchShopActivity.this).load(matchshoplistbean.getData().getImg_url()).into(image);
            image.setBackgroundResource(R.mipmap.hongbaotip);
            int w=Staticdata.ScreenWidth;
            int h= (int) (w*0.15);
            LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(w, h);
            image.setLayoutParams(mLayoutparams);
            iamge_newacount.addView(image);
        }

    }
    private  void  initlistenner(){
        linearLayout_cancle.setOnClickListener(new View.OnClickListener() {//换一个
            @Override
            public void onClick(View v) {
                new Popwindow_cancleorder("您即将取消任务，是否继续？", MatchShopActivity.this, new Interence_complteTask() {
                    @Override
                    public void onResult(boolean result) {
                        if(result){
                            huanyipi();//请求换一批
                            ProgressDlog.showProgress(mKProgressHUD);
                        }else {

                            Map map_taskdetail = new HashMap();
                            map_taskdetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                            map_taskdetail.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                            map_taskdetail.put("id", ID);
                            LogUtils.LOG("ceshi", map_taskdetail.toString(), "cancleorder");
                            new Volley_Utils(new Interface_volley_respose() {
                                @Override
                                public void onSuccesses(String respose) {
                                    int status = 0;
                                    String msg = "";
                                    try {
                                        JSONObject object = new JSONObject(respose);
                                        status = (Integer) object.get("code");//登录状态
                                        msg = (String) object.get("message");//登录返回信息

                                        if (status == 1) {
                                            Intent intent1 = new Intent(MatchShopActivity.this, CancelloederActivity.class);
                                            intent1.putExtra("taskid", ID + "");
                                            startActivity(intent1);

                                        } else {
                                            ToastUtils.showToast(MatchShopActivity. this, msg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(int error) {

                                }
                            }).postHttp(Urls.Baseurl_cui + Urls.taskdetailscancle, MatchShopActivity.this, 1, map_taskdetail);


                        }
                    }
                }).showPopwindow();
            }
        });
        linearLayout_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Popwindow_Tip("是否选择此商家下单?", MatchShopActivity.this, new Interence_complteTask() {
                    @Override
                    public void onResult(boolean result) {
                        if(result) {
                            map_choosebissness=new HashMap();
                            map_choosebissness.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                            map_choosebissness.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                            map_choosebissness.put("task_id", ID);
                            map_choosebissness.put("business_no", matchshoplistbean.getData().getMatching().get(page).getBusiness_no());
//                    map_choosebissness.put("counteroffer_amount", text_money.getText());
                            new Volley_Utils(new Interface_volley_respose() {
                                @Override
                                public void onSuccesses(String respose) {
                                    int status = 0;
                                    String msg = "";
                                    String data = "";
                                    try {
                                        JSONObject object = new JSONObject(respose);
                                        status = (Integer) object.get("code");//
                                        msg = (String) object.get("message");//
                                        data = (String) object.get("data");//
                                        if(status==1){
//                                    timer.cancel();
                                            Intent intentpay = new Intent(MatchShopActivity.this, MytaskDetailActivity.class);
//                                    intentpay.putExtra("title", "匹配商户成功付款");//支付需要传 isBargainPay:(是否还价支付,	Y：是	N：否)还价支付时必传Y，其他支付可不传或N
                                            intentpay.putExtra("id", ID);
//                                    intentpay.putExtra("order_no", data);
//                                    intentpay.putExtra("taskid", task_id);
                                            startActivity(intentpay);
                                            MatchShopActivity.this.finish();

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onError(int error) {

                                }
                            }).postHttp(Urls.Baseurl_cui+Urls.chooseBusiness,MatchShopActivity.this,1,map_choosebissness);
                        }
                    }
                }).showPopwindow();
            }
        });
        linearlayout_zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(matchshoplistbean.getData().getMatching().get(page).getBusiness_no(),
                        matchshoplistbean.getData().getMatching().get(page).getBusiness_name(),
                        Uri.parse(matchshoplistbean.getData().getMatching().get(page).getHeadUrl())));
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(Staticdata.static_userBean.getData().getAppuser().getClient_no(),
                        Staticdata.static_userBean.getData().getAppuser().getNick_name(),
                        Uri.parse( Staticdata.static_userBean.getData().getImg_url())));
//                RongIM.getInstance().refreshUserInfoCache(new UserInfo(matchingBean.getClient_no(),
//                        matchingBean.getBusiness_name(),
//                        Uri.parse(matchingBean.getHeadUrl())));
//                RongIM.getInstance().setMessageAttachedUserInfo(true);
                RongIM.getInstance().startPrivateChat(MatchShopActivity.this,matchshoplistbean.getData().getMatching().get(page).getBusiness_no(),matchshoplistbean.getData().getMatching().get(page).getBusiness_name());
            }
        });
        linearLayout_callphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" +matchshoplistbean.getData().getMatching().get(page).getBusiness_mobile_no());
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(MatchShopActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

//                    ToastUtils.showToast(mContext,"拨打电话权限被你拒绝，请在手机设置中开启");
                    mPermission.request(new PermissionHelper.PermissionCallback() {
                        @Override
                        public void onPermissionGranted() {

                        }

                        @Override
                        public void onIndividualPermissionGranted(String[] grantedPermission) {

                        }

                        @Override
                        public void onPermissionDenied() {

                        }

                        @Override
                        public void onPermissionDeniedBySystem() {

                        }
                    });
                    return;
                }

                startActivity(intent);//调用具体方法
            }
        });
        iamge_newacount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isshow.equals("1")){
                    Urls.pipeijiemianhuodong=matchshoplistbean.getData().getActivity_url();
                    Intent intent_kefuzhongxin = new Intent(MatchShopActivity.this, ZixunKefuWebActivity.class);
                    intent_kefuzhongxin.putExtra("webtitle", "优惠活动");
                    intent_kefuzhongxin.putExtra("type", "匹配商家界面");
                    startActivity(intent_kefuzhongxin);

                }
            }
        });
//        image_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mViewPager.setCurrentItem(--page);
//            }
//        });
//        image_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mViewPager.setCurrentItem(++page);
//            }
//        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                new Popwindow_Tip("商户已接单，是否返回？", MatchShopActivity.this, new Interence_complteTask() {
//                    @Override
//                    public void onResult(boolean result) {
//                        if (result){
//                            Intent intent=new Intent(MatchShopActivity.this,IssueTaskActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                }).showPopwindow();
            }
        });
        mtextview_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToast(MatchShopActivity.this,"点击刷新");
                huanyipi();//请求换一批
                ProgressDlog.showProgress(mKProgressHUD);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.LOG("ceshi","选择了第一页"+position,"匹配商家");
                page=position;
//                if(position==page_all){
//                    image_right.setVisibility(View.INVISIBLE);
//                }else {
//                    image_right.setVisibility(View.VISIBLE);
//
//                }
//                if(position==0){
//                    image_left.setVisibility(View.INVISIBLE);
//                }else {
//                    image_left.setVisibility(View.VISIBLE);
//
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    void request(Map map) {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.mytaskdetails+"查订单"+respose, "MytaskDetailActivity");
                taskDetailBean = new Gson().fromJson(respose, TaskDetailBean.class);
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.mytaskdetails, MatchShopActivity.this, 1, map);

    }
    void huanyipi(){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int  status=0;
                String msg="";
                try {
                    JSONObject object=new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mKProgressHUD.dismiss();
                LogUtils.LOG("ceshi",respose,"换一批");
                LogUtils.LOG("ceshi",Urls.Baseurl_cui+Urls.issuetask_huanyipi
                        +Staticdata.static_userBean.getData().getUser_token()+"&task_id="
                        +ID+"&order_no="+order_no,"换一批");
                mtextview_change.setVisibility(View.INVISIBLE);
//                text_timelow.setVisibility(View.VISIBLE);

                if(status==1){
//                    matchingBean=new Gson().fromJson(respose,Matchshoplistbean.class).getData().getMatching().get(0);
                    matchshoplistbean=new Gson().fromJson(respose,Matchshoplistbean.class);
                    list_matchbea.clear();
                    list_matchbea.addAll(matchshoplistbean.getData().getMatching());
                    list_myfragments.clear();
                    for (int i=0;i<list_matchbea.size();i++){
                        list_myfragments.add(new Fragment_shopdetail(matchshoplistbean.getData().getMatching().get(i),ID));
                    }
                    adapterFragment.setNewFragments(list_myfragments);
//                    timer = new Timer();
//                    TimerTask timerTask = new TimerTask() {
//                        @Override
//                        public void run() {
//                            mhandler.sendEmptyMessage(0);
//                        }
//                    };
//                    timer.schedule(timerTask, 0, 1000);
                }else {
                    ToastUtils.showToast(MatchShopActivity.this,msg);
                }
            }

            @Override
            public void onError(int error) {
                mKProgressHUD.dismiss();
            }
        }).Http(Urls.Baseurl_cui+Urls.issuetask_huanyipi
                +Staticdata.static_userBean.getData().getUser_token()+"&task_id="
                +ID+"&order_no="+order_no,MatchShopActivity.this,0);
    }

    Timer timer;
    int time=16;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    time--;
//                    text_timelow.setText(time+"s");
                    if(time==0){
                        timer.cancel();
                        mtextview_change.setVisibility(View.VISIBLE);
//                        text_timelow.setVisibility(View.INVISIBLE);
                        time=16;
                    }
                    break;
            }
        }
//
//
    };
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
    }

}
