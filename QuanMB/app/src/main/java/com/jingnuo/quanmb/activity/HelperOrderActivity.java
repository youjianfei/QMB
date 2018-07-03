package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interence_complteTask_time;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.popwinow.Popwindow_CompleteTime;
import com.jingnuo.quanmb.popwinow.Popwindow_complatetask;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.HelpOrderBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class HelperOrderActivity extends BaseActivityother {

    CircleImageView mImageview_head;
    TextView mTextview_state;//状态
    TextView mTextview_titile;//标题
    TextView mTextview_money;//佣金
    TextView mTextview_time;//发布时间
    TextView mTextview_peoplename;//雇主
    TextView mTextview_taskDetail;//任务描述
    TextView mTextview_resttime;//剩余时间
    TextView mTextview_resttime_tip;//剩余时间OR申请完成
    TextView mTextview_text_timecancle;//倒计时
    TextView mTextview_address;//地点
    //    TextView mTextview_phonenumber;//客户电话
    LinearLayout mLinearlayout_tel;
    LinearLayout mLinearlayout_linearlayout_timecancle;

    TextView mButton_queren;


    MyGridView imageGridview;
    String image_url = "";
    List<String> imageview_urllist;
    Adapter_Gridviewpic adapter_gridviewpic;


    //对象
    Popwindow_complatetask popwindow_complatetask;
    HelpOrderBean helpOrderBean;
    Popwindow_CompleteTime popwindow_completeTime;
    //数据
    String order_no = "";
    int type = 0;  //1帮手  2  商户

    String tel = "";//雇主电话
    PermissionHelper mPermission;//动态申请权限

    long chazhi = 0;//时间戳差值

    @Override
    public int setLayoutResID() {
        return R.layout.activity_helper_order;
    }

    @Override
    protected void setData() {
        imageview_urllist = new ArrayList<>();
        adapter_gridviewpic = new Adapter_Gridviewpic(imageview_urllist, this);
        imageGridview.setAdapter(adapter_gridviewpic);

        popwindow_complatetask = new Popwindow_complatetask(this, new Interence_complteTask() {
            @Override
            public void onResult(boolean result) {
                if (result) {
                    LogUtils.LOG("ceshi", "申请完成任务接口+" + Urls.Baseurl_cui + Urls.applycompletetask + Staticdata.static_userBean.getData().getUser_token() +
                            "&order_no=" + helpOrderBean.getData().getDetail().getOrder_no() +
                            "&task_id=" + helpOrderBean.getData().getDetail().getTask_id(), "申请完成任务");
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi", respose, "申请完成");
                            int status = 0;
                            String msg = "";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");//登录状态
                                msg = (String) object.get("message");//登录返回信息
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (status == 1) {
                                ToastUtils.showToast(HelperOrderActivity.this, msg);
                                request();
                            } else {
                                request();
                                ToastUtils.showToast(HelperOrderActivity.this, msg);
                            }
                        }

                        @Override
                        public void onError(int error) {
                            request();
                        }
                    }).Http(Urls.Baseurl_cui + Urls.applycompletetask + Staticdata.static_userBean.getData().getUser_token() +
                            "&order_no=" + helpOrderBean.getData().getDetail().getOrder_no() +
                            "&task_id=" + helpOrderBean.getData().getDetail().getTask_id(), HelperOrderActivity.this, 0);

                }

            }
        });

    }

    @Override
    protected void initData() {
        mLinearlayout_linearlayout_timecancle.setEnabled(false);
        mPermission = new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        order_no = getIntent().getStringExtra("order_no");
        type = getIntent().getIntExtra("type", 0);
        popwindow_completeTime = new Popwindow_CompleteTime(HelperOrderActivity.this, new Interence_complteTask_time() {
            @Override
            public void onResult(String result, int tag) {//选择申请延时的时间后请求申请延时接口
                LogUtils.LOG("ceshi", tag + "", "选择时长");
                Map map_longtime=new HashMap();
                map_longtime.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                map_longtime.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                map_longtime.put("order_no",helpOrderBean.getData().getDetail().getOrder_no());
                map_longtime.put("delay_time",tag+"");
                LogUtils.LOG("ceshi", map_longtime + "", "选择时长");
                new  Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi",respose,"申请延时re");
                        int status = 0;
                        String msg = "";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//
                            msg = (String) object.get("message");//
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ToastUtils.showToast(HelperOrderActivity.this,msg);
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui+Urls.apply_longtime,HelperOrderActivity.this,1,map_longtime);
                LogUtils.LOG("ceshi", Urls.Baseurl+Urls.apply_longtime + "", "选择时长");
            }
        });
        request();

    }
    void setImage(String image) {
        if (image == null || image.equals("")) {

        } else {
            String[] images = image.split(",");
            int len = images.length;
            LogUtils.LOG("ceshi", "图片的个数" + images.length, "SkillDetailActivity分隔图片");
            imageview_urllist.clear();
            for (int i = 0; i < len; i++) {
                imageview_urllist.add(images[i]);
            }
            adapter_gridviewpic.notifyDataSetChanged();

        }

    }
    void request() {
        LogUtils.LOG("ceshi", "帮手订单+" + Urls.Baseurl + Urls.shoporderdetail + Staticdata.static_userBean.getData().getUser_token() + "&orderId=" + order_no, "帮手订单网址");
        String URL = "";
        if (type == 1) {
            URL = Urls.Baseurl + Urls.helporderdetail + Staticdata.static_userBean.getData().getUser_token() + "&order_no=" + order_no;
        } else {
            URL = Urls.Baseurl + Urls.shoporderdetail + Staticdata.static_userBean.getData().getUser_token() + "&order_no=" + order_no;
        }

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                mButton_queren.setEnabled(true);
                LogUtils.LOG("ceshi", "帮手订单+" + respose, "帮手订单网址");
                helpOrderBean = new Gson().fromJson(respose, HelpOrderBean.class);
                Glide.with(HelperOrderActivity.this).load(helpOrderBean.getData().getDetail().getHeadUrl()).into(mImageview_head);
                mTextview_titile.setText(helpOrderBean.getData().getDetail().getTask_name());
                mTextview_state.setText(helpOrderBean.getData().getDetail().getOrder_status());
                mTextview_money.setText("佣金：" + helpOrderBean.getData().getDetail().getOrder_amount() + "元");
                mTextview_time.setText("发布时间：" + helpOrderBean.getData().getDetail().getTask_StartDate());

                long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
                long ago = Long.parseLong(Utils.getTime(helpOrderBean.getData().getDetail().getOrder_enddate()));//任务过期时间
//                String time = Utils.getDistanceTime(ago, now);//算出的差值

                if (now < ago) {
                    chazhi = ago - now;
                } else {
                    chazhi = now - ago;
                }
                if (chazhi > 0) {
                    timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            mhandler.sendEmptyMessage(0);
                        }
                    };
                    timer.schedule(timerTask, 0, 1000);
                } else {
                    mLinearlayout_linearlayout_timecancle.setVisibility(View.INVISIBLE);
                }

                LogUtils.LOG("ceshitime", ago - now + "", "shijian");

//                mTextview_resttime.setText(time);
                image_url = helpOrderBean.getData().getDetail().getTask_Img_Url();
                setImage(image_url);
                mTextview_peoplename.setText(helpOrderBean.getData().getDetail().getNick_name());
                mTextview_taskDetail.setText(helpOrderBean.getData().getDetail().getTask_description());
                mTextview_address.setText(helpOrderBean.getData().getDetail().getDetailed_address());
//                mTextview_phonenumber.setText(helpOrderBean.getData().getDetail().getMobile_no());
                tel = helpOrderBean.getData().getDetail().getMobile_no();
                if (helpOrderBean.getData().getDetail().getOrder_status().equals("待确认")) {
                    mButton_queren.setEnabled(false);
                    mButton_queren.setText("等待雇主确认");
                } else if (helpOrderBean.getData().getDetail().getOrder_status().equals("已完成")) {
                    mButton_queren.setEnabled(false);
                    mButton_queren.setText("已完成");
                } else if (helpOrderBean.getData().getDetail().getOrder_status().equals("已关闭")) {
                    mButton_queren.setEnabled(false);
                    mButton_queren.setText("已关闭");
                } else if (helpOrderBean.getData().getDetail().getOrder_status().equals("已关闭")) {
                    mButton_queren.setEnabled(false);
                    mButton_queren.setText("逾期未完成");
                } else {
                    mButton_queren.setEnabled(true);
                }

            }

            @Override
            public void onError(int error) {
                request();
            }
        }).Http(URL, HelperOrderActivity.this, 0);
    }

    @Override
    protected void initListener() {
        mLinearlayout_linearlayout_timecancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//申请延长时间

                popwindow_completeTime.showPopwindow();
            }

        });
        mButton_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popwindow_complatetask.showPopwindow();
            }
        });
        mLinearlayout_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tel.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + tel);
                    intent.setData(data);

                    if (ActivityCompat.checkSelfPermission(HelperOrderActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

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
            }
        });

    }

    //剩余完成时间长倒计时
    Timer timer;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    chazhi = chazhi - 1000;
                    LogUtils.LOG("ceshi", "倒计时" + chazhi, "倒计时");
                    mTextview_text_timecancle.setText(Utils.getDistanceTime4(chazhi));
                    if (chazhi < 600000) {
                        mLinearlayout_linearlayout_timecancle.setEnabled(true);
                        mTextview_resttime_tip.setText("申请延时");
                        mLinearlayout_linearlayout_timecancle.setBackgroundResource(R.mipmap.time_green);
                    }
                    if (chazhi < 0) {
                        mLinearlayout_linearlayout_timecancle.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }


    };

    @Override
    protected void initView() {
        mImageview_head = findViewById(R.id.image_task);
        mTextview_state = findViewById(R.id.text_taskstate);
        mTextview_titile = findViewById(R.id.text_tasktitle);
        mTextview_money = findViewById(R.id.text_taskmoney_);
        mTextview_time = findViewById(R.id.text_tasktime);
        mTextview_peoplename = findViewById(R.id.text_name);
        mTextview_taskDetail = findViewById(R.id.text_taskdetail);
        mTextview_resttime = findViewById(R.id.text_time);
        mTextview_text_timecancle = findViewById(R.id.text_timecancle);//倒计时显示
        mTextview_resttime_tip = findViewById(R.id.resttime_tip);//剩余时间or 申请完成
        mTextview_address = findViewById(R.id.text_address);
//        mTextview_phonenumber=findViewById(R.id.text_number);
        imageGridview = findViewById(R.id.GridView_PIC);
        mButton_queren = findViewById(R.id.button_bargain);
        mLinearlayout_tel = findViewById(R.id.linearlayout_tel);
        mLinearlayout_linearlayout_timecancle = findViewById(R.id.linearlayout_timecancle);
    }

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
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
    }
}
