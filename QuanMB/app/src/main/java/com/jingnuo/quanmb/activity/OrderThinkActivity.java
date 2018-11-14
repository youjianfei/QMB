package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.Adapter.Adapter_Grid_orderthink;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.SimpleRatingBar;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;

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


    List<String>order_thinkList;









    Map map_think;

    //对象
    Adapter_Grid_orderthink adapter_grid_orderthink;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_order_think;
    }

    @Override
    protected void setData() {
//        textVieworderno.setText("类型-" + orderno);
        mTextview_name.setText(name);
        Glide.with(this).load(imageurl).error(R.mipmap.user_pic).into(imageView);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
//        Taskid= intent.getStringExtra("task_id");
//        imageurl= intent.getStringExtra("imageurl");
//        name= intent.getStringExtra("helpername");
//        orderno= intent.getStringExtra("orderno");

        map_think = new HashMap();
        initmap();
        setstar();
        order_thinkList=new ArrayList<>();
        order_thinkList.add("专业认真");
        order_thinkList.add("服务态度好");
        order_thinkList.add("技术过硬");
        order_thinkList.add("上门速度快");
        adapter_grid_orderthink=new Adapter_Grid_orderthink(order_thinkList,OrderThinkActivity.this);
        myGridview_think.setAdapter(adapter_grid_orderthink);
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

//        mRatinBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                LogUtils.LOG("ceshi",ratingBar.getNumStars()+"geshu"+(int)rating+"rating"+fromUser+"fromUser","评价");
//                map_think.put("star_level", (int)rating+"");
//
//            }
//        });
        mRatinBar.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(SimpleRatingBar simpleRatingBar, float rating, boolean fromUser) {
                map_think.put("star_level", (int)rating+"");

            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenttext = mEdit_think.getText() + "";
                map_think.put("evaluation_content", contenttext);
                request();
            }
        });

    }

    @Override
    protected void initView() {
//        textVieworderno = findViewById(R.id.textview_helperorder);
        mTextview_name = findViewById(R.id.textview_helpername);
        imageView = findViewById(R.id.imageview_helper);
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
//        map_think.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
//        map_think.put("user_token", Staticdata.static_userBean.getData().getUser_token());
//        map_think.put("task_id", Taskid);
//        map_think.put("evaluation_content", contenttext);
//        map_think.put("star_level", "5");
    }

    void request() {
        LogUtils.LOG("ceshi", map_think.toString(), "评价");
//        LogUtils.LOG("ceshi",Urls.Baseurl_cui+Urls.orderthink,"评价");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "评价");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息

                    if (status == 1) {
                        ToastUtils.showToast(OrderThinkActivity.this, msg);
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
}
