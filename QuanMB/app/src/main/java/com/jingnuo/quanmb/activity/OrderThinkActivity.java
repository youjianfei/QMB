package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Grid_orderthink;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.SimpleRatingBar;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.OrderThinkBean;
import com.jingnuo.quanmb.entityclass.OrderThinkGridBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import com.master.permissionhelper.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class OrderThinkActivity extends BaseActivityother {

    //控件
    SimpleRatingBar mRatinBar;
    String Taskid = "";
    EditText mEdit_think;
    Button mButton_submit;
    TextView mTextview_name;
    TextView textview_startcounts;
    TextView textview_shiji_amount;
    TextView textview_coupon;
    ImageView image_phonenumber;
    CircleImageView imageView;
//    TextView textVieworderno;
    LinearLayout linearlayout_share;
    MyGridView myGridview_think;

    //数据
    String contenttext = "";
    String xingxingcount = "5";


    String imageurl = "";//头像
    String orderno = "";//订单号
    String name = "";//名字
    String shopPhonenumber="";


    List<OrderThinkGridBean>order_thinkList;



    Map map_think;

    //对象
    Adapter_Grid_orderthink adapter_grid_orderthink;
    OrderThinkBean orderThinkBean;
    PermissionHelper mPermission;//动态申请权限
    @Override
    public int setLayoutResID() {
        return R.layout.activity_order_think;
    }

    @Override
    protected void setData() {
//        textVieworderno.setText("类型-" + orderno);

    }

    @Override
    protected void initData() {
        mPermission = new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        Intent intent = getIntent();
        Taskid= intent.getStringExtra("task_id");
//        imageurl= intent.getStringExtra("imageurl");
//        name= intent.getStringExtra("helpername");
//        orderno= intent.getStringExtra("orderno");

        map_think = new HashMap();
        initmap();
        setstar();
        order_thinkList=new ArrayList<>();
        for(int i=0;i<4;i++){
            switch (i){
                case 0:
                    OrderThinkGridBean orderThinkGridBean=new OrderThinkGridBean();
                    orderThinkGridBean.setName("专业认真");
                    orderThinkGridBean.setIsselect(false);
                    order_thinkList.add(orderThinkGridBean);
                    break;
                case 1:
                    OrderThinkGridBean orderThinkGridBean1=new OrderThinkGridBean();
                    orderThinkGridBean1.setName("服务态度好");
                    orderThinkGridBean1.setIsselect(false);
                    order_thinkList.add(orderThinkGridBean1);

                    break;
                case 2:
                    OrderThinkGridBean orderThinkGridBean2=new OrderThinkGridBean();
                    orderThinkGridBean2.setName("技术过硬");
                    orderThinkGridBean2.setIsselect(false);
                    order_thinkList.add(orderThinkGridBean2);

                    break;
                case 3:
                    OrderThinkGridBean orderThinkGridBean3=new OrderThinkGridBean();
                    orderThinkGridBean3.setName("上门速度快");
                    orderThinkGridBean3.setIsselect(false);
                    order_thinkList.add(orderThinkGridBean3);
                    break;
            }

        }
        adapter_grid_orderthink=new Adapter_Grid_orderthink(order_thinkList,OrderThinkActivity.this);
        myGridview_think.setAdapter(adapter_grid_orderthink);

        requestInfo();
    }
    void requestInfo(){
        Map map=new HashMap();
        map.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        map.put("task_id",Taskid);
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"评价");
                orderThinkBean=new Gson().fromJson(respose,OrderThinkBean.class);
                if(orderThinkBean.getCode()==1){
                    mTextview_name.setText(orderThinkBean.getData().getBusiness_name());
                    textview_startcounts.setText(orderThinkBean.getData().getEvaluation_star()+"");
                    textview_shiji_amount.setText(orderThinkBean.getData().getActually_amount()+"");
                    textview_coupon.setText("优惠券已抵扣"+orderThinkBean.getData().getAmount()+"元");
                    shopPhonenumber=orderThinkBean.getData().getBusiness_mobile_no();
                    Glide.with(OrderThinkActivity.this).load(orderThinkBean.getData().getBus_head_url()).error(R.mipmap.user_pic).into(imageView);

                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.getorderinfo,OrderThinkActivity.this,1,map);
    }

    void setstar() {
        mRatinBar.setNumberOfStars(5);
        mRatinBar.setFillColor(getResources().getColor(R.color.yellow_jianbian_start));
        mRatinBar.setStarBackgroundColor(getResources().getColor(R.color.gray_background));
        mRatinBar.setPressedFillColor(getResources().getColor(R.color.yellow_jianbian_start));
        mRatinBar.setPressedStarBackgroundColor(getResources().getColor(R.color.gray_background));
        mRatinBar.setStepSize((float) 1);
        mRatinBar.setRating(5);
        mRatinBar.setDrawBorderEnabled(false);
        mRatinBar.setStarsSeparation(1);
    }

    @Override
    protected void initListener() {
        linearlayout_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_kefuzhongxin = new Intent(OrderThinkActivity.this, SharefriendActivity.class);
//                intent_kefuzhongxin.putExtra("webtitle", "优惠活动");
                startActivity(intent_kefuzhongxin);
            }
        });
        myGridview_think.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(order_thinkList.get(position).isIsselect()){
                    order_thinkList.get(position).setIsselect(false);
                    LogUtils.LOG("ceshi","取消","dfdfdd");
                }else {
                    order_thinkList.get(position).setIsselect(true);
                    LogUtils.LOG("ceshi","选择","dfdfdd");

                }
                adapter_grid_orderthink.notifyDataSetChanged();
            }
        });
        image_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + shopPhonenumber);
                intent.setData(data);

                if (ActivityCompat.checkSelfPermission(OrderThinkActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

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
        mRatinBar.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(SimpleRatingBar simpleRatingBar, float rating, boolean fromUser) {
                map_think.put("star_level", (int)rating+"");

            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<4;i++){
                    if(order_thinkList.get(i).isIsselect()){
                        contenttext=contenttext+order_thinkList.get(i).getName()+",";
                    }
                }
                contenttext =contenttext+ mEdit_think.getText() + "";

                map_think.put("evaluation_content", contenttext);
                request();
            }
        });

    }

    @Override
    protected void initView() {
//        textVieworderno = findViewById(R.id.textview_helperorder);
        mTextview_name = findViewById(R.id.textview_helpername);
        textview_startcounts = findViewById(R.id.textview_startcounts);
        textview_shiji_amount = findViewById(R.id.textview_shiji_amount);
        textview_coupon = findViewById(R.id.textview_coupon);
        imageView = findViewById(R.id.imageview_helper);
        image_phonenumber = findViewById(R.id.image_phonenumber);
        mRatinBar = findViewById(R.id.ratingbar);
        mEdit_think = findViewById(R.id.edit_think);
        mButton_submit = findViewById(R.id.button_submit);
        linearlayout_share = findViewById(R.id.linearlayout_share);
        myGridview_think = findViewById(R.id.myGridview_think);



        ImageView image = new ImageView(OrderThinkActivity.this);
        image.setBackgroundResource(R.mipmap.share_linear);
        int w=Staticdata.ScreenWidth- SizeUtils.dip2px(this,20);
        int h= (int) (w*0.2);
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(w, h);
        image.setLayoutParams(mLayoutparams);
        linearlayout_share.addView(image);

    }

    void initmap() {
        map_think.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_think.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_think.put("task_id", Taskid);
        map_think.put("evaluation_content", contenttext);
        map_think.put("star_level", "5");
    }

    void request() {
        LogUtils.LOG("ceshi", map_think.toString(), "评价");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "评价");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("message");//

                    if (status == 1) {
                       Intent intent =new Intent(OrderThinkActivity.this,OrderThinkSuccessActivity.class);
                       startActivity(intent);
                        finish();
                    } else {
                        ToastUtils.showToast(OrderThinkActivity.this, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.orderthink, this, 1, map_think);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
