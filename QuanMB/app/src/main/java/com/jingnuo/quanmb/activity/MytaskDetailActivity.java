package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_skillsdetails;
import com.jingnuo.quanmb.Interface.Interence_bargin;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.broadcastrReceiver.PaySuccessOrErroBroadcastReciver;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.popwinow.Popwindow_addPrice;
import com.jingnuo.quanmb.popwinow.Popwindow_lookpic;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MytaskDetailActivity extends BaseActivityother {
    //控件

    RelativeLayout mRelativylaout_background;
    RelativeLayout mRelativylaout_re1;
    RelativeLayout mRelativylaout_re4;
    TextView getmTextview_statejieshao;//例：正在等待帮手接单
    TextView mTextview_taskstate;// 任务类型
    TextView mTextview_taskmoney;//佣金
    TextView mTextview_tasktime;//发布时间
    TextView mTextview_taskdetails;//任务详情
    TextView mTextview_taskstarttime;//预约时间
    TextView mTextview_taskaddress;//地址
    TextView mTextview_guzhuName;//雇主姓名
    CircleImageView mImageview_head;//雇主头像

    TextView todoName;//帮手或商家姓名
    TextView todoVIP;//vip等级
    TextView todoShenfen;//身份
    CircleImageView mImageview_todo;//帮手或商家的头像
    ImageView mImageview_phonenumber;//帮手或商家的电话


    MyGridView imageGridview;

    LinearLayout mButton_cancle;
    LinearLayout mText_xiugaijiage;
    TextView mButton_complete;
    TextView mButton_completed;
    TextView mButton_again;

    //数据
    String app_type="";//判断是否是匹配单

    String ID = "";
    Map map_taskdetail;

    String  phonenumber="";

    String addprice="";//加价的金额


    String newID = "";
    boolean isIssueAgain = false;

    String image_url = "";
    List<String> imageview_urllist;
    //对象
    TaskDetailBean taskDetailBean;
    Popwindow_lookpic popwindow_lookpic;
    Adapter_Gridviewpic_skillsdetails adapter_gridviewpic;

    PermissionHelper mPermission;//动态申请权限

    Popwindow_Tip popwindow_tip;
    Popwindow_addPrice popwindow_addPrice;

    RequestManager glidee;
    private IntentFilter intentFilter_paysuccess;//定义广播过滤器；
    private PaySuccessOrErroBroadcastReciver paysuccess_BroadcastReciver;//定义广播监听器

    @Override
    public int setLayoutResID() {
        return R.layout.activity_mytask_detail;
    }

    @Override
    protected void setData() {
        imageview_urllist = new ArrayList<>();
        adapter_gridviewpic = new Adapter_Gridviewpic_skillsdetails(imageview_urllist, this);
        imageGridview.setAdapter(adapter_gridviewpic);
        popwindow_lookpic = new Popwindow_lookpic(this);
        intentFilter_paysuccess = new IntentFilter();
        intentFilter_paysuccess.addAction("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
        paysuccess_BroadcastReciver = new PaySuccessOrErroBroadcastReciver(new Interface_paySuccessOrerro() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "payResult");
                if (respose.equals("success")&&isIssueAgain) {//重新发布任务支付成功
                    request(map_taskdetail);
                }else {//增加价格支付成功
                    Map map_addprice=new HashMap();
                    map_addprice.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    map_addprice.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    map_addprice.put("task_id",taskDetailBean.getData().getTask_id()+"");
                    map_addprice.put("money",addprice+"");
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        int status = 0;
                        String msg = "";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//
                            msg = (String) object.get("message");//

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ToastUtils.showToast(MytaskDetailActivity.this,msg);

                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui+Urls.taskaddCommission,MytaskDetailActivity.this,1,map_addprice);
                }
            }

            @Override
            public void onError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(MytaskDetailActivity.this, "支付失败");
                    }
                });
            }
        });
        registerReceiver(paysuccess_BroadcastReciver, intentFilter_paysuccess); //将广播监听器和过滤器注册在一起；
    }

    @Override
    protected void initData() {
        glidee=Glide.with(MytaskDetailActivity.this);
        mPermission= new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        Intent intent = getIntent();
        ID = intent.getStringExtra("id");
        map_taskdetail = new HashMap();
        map_taskdetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_taskdetail.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_taskdetail.put("id", ID + "");
        LogUtils.LOG("ceshi", map_taskdetail.toString(), "MytaskDetailActivity");
        request(map_taskdetail);

    }

    @Override
    protected void initListener() {
        mTextview_taskaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!taskDetailBean.getData().getX_value().equals("")){
                    Intent intentAddressshow= new Intent(MytaskDetailActivity.this,AdressShowActivity.class);
                    intentAddressshow.putExtra("x_vlaue",taskDetailBean.getData().getX_value());
                    intentAddressshow.putExtra("y_vlaue",taskDetailBean.getData().getY_value());
                    startActivity(intentAddressshow);
                }
            }
        });

        mImageview_phonenumber.setOnClickListener(new View.OnClickListener() {//点击拨打电话
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + phonenumber);
                intent.setData(data);

                if (ActivityCompat.checkSelfPermission(MytaskDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

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

        imageGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popwindow_lookpic.showPopwindow(position, imageview_urllist);
            }
        });
        mButton_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                ToastUtils.showToast(MytaskDetailActivity.this, "撤回任务成功");
                                finish();
                            } else {
                                ToastUtils.showToast(MytaskDetailActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_cui + Urls.taskdetailscancle, MytaskDetailActivity.this, 1, map_taskdetail);

            }
        });
        mButton_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.completetask + Staticdata.static_userBean.getData().getUser_token() +
                        "&task_id=" + taskDetailBean.getData().getTask_id(), "确认完成");
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi", respose, "确认完成");
                        int status = 0;
                        String msg = "";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//登录状态
                            msg = (String) object.get("message");//登录返回信息

                            if (status == 1) {
                                ToastUtils.showToast(MytaskDetailActivity.this, msg);
//                                request(map_taskdetail);
                                Intent intend_think = new Intent(MytaskDetailActivity.this, OrderThinkActivity.class);
                                intend_think.putExtra("task_id", taskDetailBean.getData().getTask_id() + "");
                                if(taskDetailBean.getData().getBusiness_name().equals("")){
                                    intend_think.putExtra("helpername", taskDetailBean.getData().getHelper_name() + "");
                                }else {
                                    intend_think.putExtra("helpername", taskDetailBean.getData().getBusiness_name() + "");
                                }

                                intend_think.putExtra("orderno", taskDetailBean.getData().getSpecialty_name() + "");
                                intend_think.putExtra("imageurl", taskDetailBean.getData().getB_h_url() + "");
                                startActivity(intend_think);
                            } else {
                                ToastUtils.showToast(MytaskDetailActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).Http(Urls.Baseurl_cui + Urls.completetask + Staticdata.static_userBean.getData().getUser_token() +
                        "&task_id=" + taskDetailBean.getData().getTask_id(), MytaskDetailActivity.this, 0);
            }
        });
        mButton_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestTaskAgain();
            }
        });
        mText_xiugaijiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindow_addPrice=new Popwindow_addPrice(MytaskDetailActivity.this, new Interence_bargin() {
                    @Override
                    public void onResult(String result) {
                        addprice=result;
                        Intent intentaddprice = new Intent(MytaskDetailActivity.this, PayActivity.class);
                        intentaddprice.putExtra("title", "全民帮—任务加价");
                        intentaddprice.putExtra("order_no", "000000");
                        intentaddprice.putExtra("amount", result + "");
                        intentaddprice.putExtra("taskid", taskDetailBean.getData().getTask_id() + "");
                        startActivity(intentaddprice);
                    }
                });
                popwindow_addPrice.showpop();
            }
        });
    }

    @Override
    protected void initView() {
        mImageview_phonenumber = findViewById(R.id.image_phonenumber);
        mImageview_todo = findViewById(R.id.image_touxiang);
        todoShenfen = findViewById(R.id.text_shenfen);
        todoVIP = findViewById(R.id.text_vip);
        todoName = findViewById(R.id.text_todoname);
        mTextview_guzhuName = findViewById(R.id.text_guzhuname);
        mTextview_taskstate = findViewById(R.id.text_taskstate);
        mTextview_taskmoney = findViewById(R.id.text_taskmoney);
        mTextview_tasktime = findViewById(R.id.text_tasktime);
        mTextview_taskdetails = findViewById(R.id.text_taskdetail);
        mTextview_taskstarttime = findViewById(R.id.text_time);
        mTextview_taskaddress = findViewById(R.id.text_address);
        getmTextview_statejieshao = findViewById(R.id.text_state);
        mImageview_head = findViewById(R.id.image_task);
        mButton_cancle = findViewById(R.id.button_cancle);
        mText_xiugaijiage = findViewById(R.id._xiugaijiage);
        mButton_complete = findViewById(R.id.button_complete);
        mButton_completed = findViewById(R.id.button_completed);
        mButton_again = findViewById(R.id.button_again);
        imageGridview = findViewById(R.id.GridView_PIC);
        mRelativylaout_background = findViewById(R.id.RelativeLayout_background);
        mRelativylaout_re1 = findViewById(R.id.re1);
        mRelativylaout_re4 = findViewById(R.id.re4);
    }

    void requestTaskid() {

                isIssueAgain = true;
                Intent intentpay = new Intent(MytaskDetailActivity.this, PayActivity.class);
                intentpay.putExtra("title", "全民帮—任务付款");
                intentpay.putExtra("order_no", "000000");

                if (taskDetailBean.getData().getIs_helper_bid().equals("Y")) {
                    intentpay.putExtra("amount", "5");
                } else {
                    intentpay.putExtra("amount", taskDetailBean.getData().getCommission() + "");
                }

                intentpay.putExtra("taskid", taskDetailBean.getData().getTask_id() + "");
                startActivity(intentpay);



    }

    void requestTaskAgain() {
        LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.Issue_again + Staticdata.static_userBean.getData().getUser_token() + "&task_newid="
                + newID + "&task_id=" + taskDetailBean.getData().getTask_id() + "&payResult=" + 1, "重新发布");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "重新发布");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("message");//

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {//重新发布成功
                    map_taskdetail.put("id", taskDetailBean.getData().getTask_id() + "");
                    mButton_again.setVisibility(View.GONE);
                    requestTaskid();//支付
                } else {
                    ToastUtils.showToast(MytaskDetailActivity.this, msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui + Urls.Issue_again + Staticdata.static_userBean.getData().getUser_token() + "&task_id=" + taskDetailBean.getData().getTask_id() , MytaskDetailActivity.this, 0);
    }

    void request(Map map) {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.mytaskdetails, "MytaskDetailActivity");
                LogUtils.LOG("ceshi", respose, "MytaskDetailActivity");
                taskDetailBean = new Gson().fromJson(respose, TaskDetailBean.class);
                app_type=taskDetailBean.getData().getApp_type();
                if (taskDetailBean.getData().getTask_Status_code().equals("01") ||
                        taskDetailBean.getData().getTask_Status_code().equals("08") ||
                        taskDetailBean.getData().getTask_Status_code().equals("09") ||
                        taskDetailBean.getData().getTask_Status_code().equals("07") ||
                        taskDetailBean.getData().getTask_Status_code().equals("13")
                        ) {
//                    mRelativylaout_re1.setVisibility(View.VISIBLE);
                    mRelativylaout_re4.setVisibility(View.GONE);
                } else {
                   glidee.load(taskDetailBean.getData().getB_h_url()).into(mImageview_todo);

//                    mRelativylaout_re1.setVisibility(View.GONE);
                    mRelativylaout_re4.setVisibility(View.VISIBLE);
                    String vip=""+taskDetailBean.getData().getLevel();
                    if (vip.equals("")){
                        todoVIP.setVisibility(View.GONE);
                    }else {
                        todoVIP.setText(vip);
                    }
                    if (taskDetailBean.getData().getBusiness_name().equals("")) {
                        //没有商户名字   是帮手
                        todoName.setText(taskDetailBean.getData().getHelper_name());
                        todoShenfen.setText("个人帮手");
                        phonenumber=taskDetailBean.getData().getHelper_mobile_no();
                    } else {
                        //商户
                        todoName.setText(taskDetailBean.getData().getBusiness_name());
                        todoShenfen.setText("认证商户");
                        phonenumber=taskDetailBean.getData().getBusiness_mobile_no();
                    }
                }
                mTextview_guzhuName.setText(taskDetailBean.getData().getNick_name());
                mTextview_taskstate.setText(taskDetailBean.getData().getSpecialty_name());
                if(taskDetailBean.getData().getIs_delay().equals("Y")&&taskDetailBean.getData().getDelay().equals("")&&taskDetailBean.getData().getTask_Status_code().equals("02")){
                    String Tip="";
                    switch (taskDetailBean.getData().getDelay_time()){
                        case "1" :
                            Tip="帮手申请延时3小时完成任务";
                            break;
                        case "2" :
                            Tip="帮手申请延时一天完成任务";
                            break;
                        case "3" :
                            Tip="帮手申请延时三天完成任务";
                            break;
                        case "4" :
                            Tip="帮手申请延时七天完成任务";
                            break;
                        case "5" :
                            Tip="帮手申请延时十五天完成任务";
                            break;
                        case "6" :
                            Tip="帮手申请延时三十天完成任务";
                            break;
                    }
                    if(popwindow_tip==null){
                    popwindow_tip=new Popwindow_Tip(Tip, MytaskDetailActivity.this, new Interence_complteTask() {
                        @Override
                        public void onResult(boolean result) {
                            Map map_agreeorefuse =new HashMap();
                            map_agreeorefuse.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                            map_agreeorefuse.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                            map_agreeorefuse.put("task_id",taskDetailBean.getData().getTask_id()+"");
                            map_agreeorefuse.put("order_no",taskDetailBean.getData().getOrder_no()+"");
                            if(result){
                                map_agreeorefuse.put("delay","Y");
                            }else {
                                map_agreeorefuse.put("delay","N");
                            }

                            map_agreeorefuse.put("delay_time",taskDetailBean.getData().getDelay_time()+"");
                            LogUtils.LOG("ceshi",Urls.Baseurl_cui+Urls.agreeOrrefuse_longtime,"申请延时接口");
                            LogUtils.LOG("ceshi",map_agreeorefuse.toString(),"申请延时接口");
                            new  Volley_Utils(new Interface_volley_respose() {
                                @Override
                                public void onSuccesses(String respose) {
                                    int status = 0;
                                    String msg = "";
                                    try {
                                        JSONObject object = new JSONObject(respose);
                                        status = (Integer) object.get("code");//
                                        msg = (String) object.get("message");//
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    ToastUtils.showToast(MytaskDetailActivity.this,msg);

                                }

                                @Override
                                public void onError(int error) {

                                }
                            }).postHttp(Urls.Baseurl_cui+Urls.agreeOrrefuse_longtime,MytaskDetailActivity.this,1,map_agreeorefuse);
                            }


                    });
                    popwindow_tip.showPopwindow();
                    }
                }




                if (taskDetailBean.getData().getIs_helper_bid().equals("Y")) {
                    mTextview_taskmoney.setText("佣金：" + "帮手出价");
                } else {
                    mTextview_taskmoney.setText("佣金：" + taskDetailBean.getData().getCommission() + "元");
                }
                mTextview_tasktime.setText("发布时间：" + taskDetailBean.getData().getTask_Startdate());
                mTextview_taskdetails.setText(taskDetailBean.getData().getTask_description());

//                long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
//                long ago = Long.parseLong(Utils.getTime(taskDetailBean.getData().getTask_EndDate()));//任务过期时间
//                String time = Utils.getDistanceTime(ago, now);//算出的差值
                mTextview_taskstarttime.setText(taskDetailBean.getData().getTask_Time());


                mTextview_taskaddress.setText( taskDetailBean.getData().getDetailed_address());
                String imageURL = taskDetailBean.getData().getAvatar_imgUrl().substring(0, taskDetailBean.getData().getAvatar_imgUrl().length() - 1);

                LogUtils.LOG("ceshi", "调用glide", "wode renwu");
                Glide.with(MytaskDetailActivity.this).load(imageURL).into(mImageview_head);
//                mTextview_helplevle.setText(taskDetailBean.getData().getUser_grade());
                image_url = taskDetailBean.getData().getTask_ImgUrl();
                setImage(image_url);

                if (taskDetailBean.getData().getTask_Status_code().equals("02") || taskDetailBean.getData().getTask_Status_code().equals("01") || taskDetailBean.getData().getTask_Status_code().equals("08")) {
                    mButton_cancle.setVisibility(View.VISIBLE);
                    mButton_again.setVisibility(View.GONE);
                } else {
                    mButton_cancle.setVisibility(View.GONE);
                }
                if (taskDetailBean.getData().getTask_Status_code().equals("01")|| taskDetailBean.getData().getTask_Status_code().equals("08")) {
                    getmTextview_statejieshao.setText("正在等待帮手接单...");
                    mText_xiugaijiage.setVisibility(View.VISIBLE);//修改价格
                }else {
                    mText_xiugaijiage.setVisibility(View.GONE);
                }
                if (taskDetailBean.getData().getTask_Status_code().equals("02")) {
                    getmTextview_statejieshao.setText("帮手正在全速完成任务...");
                }
                if (taskDetailBean.getData().getTask_Status_code().equals("05")) {
                    mButton_complete.setVisibility(View.VISIBLE);
                    mButton_completed.setVisibility(View.GONE);
                    getmTextview_statejieshao.setText("帮手已经完成任务，快去确认订单");
                }
                if (taskDetailBean.getData().getTask_Status_code().equals("07") || taskDetailBean.getData().getTask_Status_code().equals("13")) {
                    mButton_cancle.setVisibility(View.GONE);
                    mButton_again.setVisibility(View.VISIBLE);
                    getmTextview_statejieshao.setText("任务已失效，请重新发布");
                }
                if(app_type.equals("1")){
                    if (taskDetailBean.getData().getTask_Status_code().equals("07") || taskDetailBean.getData().getTask_Status_code().equals("13")) {
                        mButton_cancle.setVisibility(View.GONE);
                        mButton_again.setVisibility(View.GONE);
                        getmTextview_statejieshao.setText("任务已失效");
                    }
                }
                if (taskDetailBean.getData().getTask_Status_code().equals("06")) {
                    mButton_completed.setVisibility(View.VISIBLE);
                    mButton_complete.setVisibility(View.GONE);
                    getmTextview_statejieshao.setText("很好的一次合作");
                }
                if (taskDetailBean.getData().getTask_Status_code().equals("09")) {
                    mButton_complete.setVisibility(View.GONE);
                    getmTextview_statejieshao.setText("抱歉，帮手未完成任务");
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.mytaskdetails, MytaskDetailActivity.this, 1, map);

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

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (!isIssueAgain) {
            request(map_taskdetail);
            LogUtils.LOG("ceshi1", "onPostResume", "onPostResume");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paysuccess_BroadcastReciver);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
