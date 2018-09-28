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
import com.jingnuo.quanmb.Interface.Interence_jubao;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.popwinow.Popwindow_bargin;
import com.jingnuo.quanmb.popwinow.Popwindow_jubao1;
import com.jingnuo.quanmb.popwinow.Popwindow_jubao2;
import com.jingnuo.quanmb.popwinow.Popwindow_lookpic;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.QueRenHelp_Bean;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class TaskDetailsActivity extends BaseActivityother {
    TextView mTextview_state;
    TextView mTextview_taskmoney;
    TextView mTextview_taskissuetime;
    TextView mTextview_name;
    TextView mTextview_taskdetails;
    TextView mTextview_tasktime;
    TextView mTextview_taskaddress;
    CircleImageView imageView_head;
    MyGridView imageGridview;
    RelativeLayout re3;

    Button mButton_help;
    Button mButton_help2;
    Button mButton_counteroffer;
    Button mButton_counteroffer2;
    LinearLayout linearlayout_huanjiaqueren;
    LinearLayout linearlayout_zixun;
    ImageView iv_3dian;
    //数据
    String ID = "";//任务id;
    String is_counteroffer = "";
    double commison=0;
    String tel = "";//雇主电话

    String app_type="";//判断广场单和匹配单

    String popTitle="还价金额";// 报价金额   还价金额


    String image_url="";
    List<String> imageview_urllist;

    //对象
    TaskDetailBean mTaskData;
    Popwindow_lookpic popwindow_lookpic;
    Adapter_Gridviewpic_skillsdetails adapter_gridviewpic;
    PermissionHelper mPermission;//动态申请权限
    RequestManager glide;
    Popwindow_jubao1 popwindow_jubao1;
    Popwindow_jubao2 popwindow_jubao2;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_task_details;
    }

    @Override
    protected void setData() {
        Staticdata.ScreenHight = SizeUtils.getScreenHeightPx(this);
        Staticdata.ScreenWidth =SizeUtils.getScreenWidthPx(this);
        imageview_urllist=new ArrayList<>();
        adapter_gridviewpic=new Adapter_Gridviewpic_skillsdetails(imageview_urllist,this);
        imageGridview.setAdapter(adapter_gridviewpic);
        popwindow_lookpic=new Popwindow_lookpic(this);
        requestTaseDetail();
        popwindow_jubao1=new Popwindow_jubao1(TaskDetailsActivity.this, new Interence_jubao() {
            @Override
            public void onResult(String result) {
                if(result.equals("jubao")){
//                    Utils.setAlpha((float) 1,ErshouxinxiDetailsActivity.this);
                    popwindow_jubao2.showPopwindow();
                }
            }
        });
        popwindow_jubao2=new Popwindow_jubao2(this, new Interence_jubao() {
            @Override
            public void onResult(String result) {
                switch (result){
                    case "tousu":
                        Intent intent=new Intent(TaskDetailsActivity.this,JubaoActivity.class);
                        intent.putExtra("jubaoid",mTaskData.getData().getTask_id()+"");
                        intent.putExtra("typeID","1");
                        TaskDetailsActivity.this.startActivity(intent);
                        break;
                    case "xujia":
                        jubao("虚假信息");
                        break;
                    case "feifa":
                        jubao("非法信息");
                        break;
                }

            }
        });
    }
    void  jubao(String jubaoneirong){
        Map map=new HashMap();
        map.put("inform_id",mTaskData.getData().getTask_id()+"");
        map.put("type","1");
        map.put("inform_content",jubaoneirong);
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

                if(status==1){
                    ToastUtils.showToast(TaskDetailsActivity.this,msg);
                }else {
                    ToastUtils.showToast(TaskDetailsActivity.this,msg);
                }


            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.myJubao,TaskDetailsActivity.this,1,map);

    }

    @Override
    protected void initData() {
        glide=Glide.with(TaskDetailsActivity.this);
        Intent intend_id = getIntent();
        ID = intend_id.getStringExtra("id");


    }

    @Override
    protected void initListener() {
        linearlayout_zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(mTaskData.getData().getClient_no(),
                        mTaskData.getData().getNick_name(),
                        Uri.parse( mTaskData.getData().getAvatar_imgUrl().substring(0, mTaskData.getData().getAvatar_imgUrl().length() - 1))));
                RongIM.getInstance().startPrivateChat(TaskDetailsActivity.this,mTaskData.getData().getClient_no(),mTaskData.getData().getNick_name());
            }
        });
        iv_3dian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindow_jubao1.showPopwindow();
            }
        });
        mTextview_taskaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTaskData.getData().getX_value().equals("")){
                    Intent intentAddressshow= new Intent(TaskDetailsActivity.this,AdressShowActivity.class);
                    intentAddressshow.putExtra("x_vlaue",mTaskData.getData().getX_value());
                    intentAddressshow.putExtra("y_vlaue",mTaskData.getData().getY_value());
                    startActivity(intentAddressshow);
                }
            }
        });
        imageGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popwindow_lookpic.showPopwindow(position,imageview_urllist);
            }
        });

        //确认帮助请求
        mButton_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                querenbangzhu();
            }
        });
        //还价
        mButton_counteroffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baojia();
            }
        });
        //确认帮助请求
        mButton_help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                querenbangzhu();
            }
        });
        //还价
        mButton_counteroffer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baojia();
            }
        });
        //拨打电话
//        linearlayout_tel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!tel.equals("")) {
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    Uri data = Uri.parse("tel:" + tel);
//                    intent.setData(data);
//
//                    if (ActivityCompat.checkSelfPermission(TaskDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//
////                    ToastUtils.showToast(mContext,"拨打电话权限被你拒绝，请在手机设置中开启");
//                        mPermission.request(new PermissionHelper.PermissionCallback() {
//                            @Override
//                            public void onPermissionGranted() {
//
//                            }
//
//                            @Override
//                            public void onIndividualPermissionGranted(String[] grantedPermission) {
//
//                            }
//
//                            @Override
//                            public void onPermissionDenied() {
//
//                            }
//
//                            @Override
//                            public void onPermissionDeniedBySystem() {
//
//                            }
//                        });
//                        return;
//                    }
//                    startActivity(intent);//调用具体方法
//                }
//            }
//        });

    }

    @Override
    protected void initView() {
        mTextview_state = findViewById(R.id.text_taskstate);
        mTextview_taskmoney = findViewById(R.id.text_taskmoney);
        mTextview_taskissuetime = findViewById(R.id.text_tasktime);
        mTextview_name = findViewById(R.id.text_name);
        mTextview_taskdetails = findViewById(R.id.text_taskdetail);
        mTextview_tasktime = findViewById(R.id.text_time);
        mTextview_taskaddress = findViewById(R.id.text_address);
        imageGridview=findViewById(R.id.GridView_PIC);
        iv_3dian=findViewById(R.id.iv_3dian);
        mButton_help2 = findViewById(R.id.button_help2);
        mButton_help = findViewById(R.id.button_help);
        mButton_counteroffer = findViewById(R.id.button_bargain);
        mButton_counteroffer2 = findViewById(R.id.button_bargain2);
        linearlayout_huanjiaqueren = findViewById(R.id.linearlayout_tel);
        linearlayout_zixun = findViewById(R.id.linearlayout_zixun);
        imageView_head = findViewById(R.id.image_task);
        re3 = findViewById(R.id.re3);
    }

    void requestTaseDetail() {
        String  URL="";
        if(Staticdata.static_userBean.getData()==null){
            URL=Urls.Baseurl_cui + Urls.taskdetails + "?id=" + ID;
        }else {
            URL=Urls.Baseurl_cui + Urls.taskdetails + "?id=" + ID+"&user_token="+Staticdata.static_userBean.getData().getUser_token();
        }
        LogUtils.LOG("ceshi", "任务详情接口+" + Urls.Baseurl_cui + Urls.taskdetails + "?id=" + ID, "TaskDetailsActivity");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", "任务详情返回信息" + respose, "TaskDetailsActivity");
                mTaskData = new Gson().fromJson(respose, TaskDetailBean.class);
                tel=mTaskData.getData().getMobile_no();
                app_type=mTaskData.getData().getApp_type();
                mTextview_state.setText(mTaskData.getData().getSpecialty_name());
                mTextview_taskmoney.setText(mTaskData.getData().getCommission() + "元");
                commison=mTaskData.getData().getCommission();
                mTextview_taskissuetime.setText("发布时间：" + mTaskData.getData().getTask_Startdate());
                mTextview_name.setText(mTaskData.getData().getNick_name());
                mTextview_taskdetails.setText(mTaskData.getData().getTask_description());
//                long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
//                long ago = Long.parseLong(Utils.getTime(mTaskData.getData().getTask_EndDate()));//任务过期时间
//                String time = Utils.getDistanceTime(ago, now);//算出的差值
                mTextview_tasktime.setText(mTaskData.getData().getTask_Time());

                mTextview_taskaddress.setText(mTaskData.getData().getDetailed_address());
//                mTextview_peoplelevel.setText(mTaskData.getData().getUser_grade());
                is_counteroffer = mTaskData.getData().getIs_counteroffer();
                String imageURL = mTaskData.getData().getAvatar_imgUrl().substring(0, mTaskData.getData().getAvatar_imgUrl().length() - 1);
               if(TaskDetailsActivity.this!=null){
                   glide.load(imageURL).into(imageView_head);
               }
                image_url=mTaskData.getData().getTask_ImgUrl();
                setImage(image_url);
                if(mTaskData.getData().getIs_helper_bid().equals("Y")){//由帮手出价
                    mButton_counteroffer.setVisibility(View.VISIBLE);
                    mButton_counteroffer.setText("报价");
                    mButton_help.setVisibility(View.GONE);
                    linearlayout_huanjiaqueren.setVisibility(View.GONE);
                    mTextview_taskmoney.setText("帮手出价" );
                    commison=5;
                    popTitle="报价金额";
                }
                if (is_counteroffer.equals("1")&&mTaskData.getData().getIs_helper_bid().equals("N")) {
                    mButton_counteroffer.setVisibility(View.GONE);
                    mButton_help.setVisibility(View.GONE);
                    linearlayout_huanjiaqueren.setVisibility(View.VISIBLE);
                    popTitle="还价金额";
                }
                if (is_counteroffer.equals("0")) {
                    mButton_counteroffer.setVisibility(View.GONE);
                    mButton_help.setVisibility(View.VISIBLE);
                    linearlayout_huanjiaqueren.setVisibility(View.GONE);
                }

                if(!mTaskData.getData().getTask_Status_code().equals("01")){
                    mButton_counteroffer.setVisibility(View.GONE);
                    mButton_help.setVisibility(View.GONE);
                    linearlayout_huanjiaqueren.setVisibility(View.GONE);

                }
                if(app_type.equals("1")){
                    mButton_help.setVisibility(View.GONE);
                    re3.setVisibility(View.INVISIBLE);
                    linearlayout_huanjiaqueren.setVisibility(View.GONE);
                    linearlayout_zixun.setVisibility(View.VISIBLE);
                    mButton_counteroffer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(int error) {


            }
        }).Http(URL, this, 0);


    }
    void querenbangzhu(){
        if (Staticdata.isLogin) {//是否登录
            if (!Staticdata.static_userBean.getData().getAppuser().getRole().contains("1")) {
                ToastUtils.showToast(TaskDetailsActivity.this, "请先认证帮手");
                Intent intent_renzheng = new Intent(TaskDetailsActivity.this, AuthenticationActivity.class);
                startActivity(intent_renzheng);
                return;
            }

            new Popwindow_Tip("是否帮助此任务？", TaskDetailsActivity.this, new Interence_complteTask() {
                @Override
                public void onResult(boolean result) {
                    if(result){
                        new Volley_Utils(new Interface_volley_respose() {
                            @Override
                            public void onSuccesses(String respose) {
                                LogUtils.LOG("ceshi", "确认帮助+" + respose, "TaskDetailsActivity");
                                QueRenHelp_Bean queRenHelp_bean = new Gson().fromJson(respose, QueRenHelp_Bean.class);
                                if (queRenHelp_bean.getStatus() == 1) {
                                    Intent intent_querenhelp = new Intent(TaskDetailsActivity.this, HelperOrderActivity.class);
                                    intent_querenhelp.putExtra("order_no", queRenHelp_bean.getData().getOrder_no());
                                    intent_querenhelp.putExtra("whichactivity", 1);
                                    startActivity(intent_querenhelp);
                                    finish();
                                } else {
                                    ToastUtils.showToast(TaskDetailsActivity.this, queRenHelp_bean.getMessage());
                                }
                            }

                            @Override
                            public void onError(int error) {

                            }
                        }).Http(Urls.Baseurl_cui + Urls.helptask + "?tid=" + ID + "&user_token=" + Staticdata.static_userBean.getData().getUser_token(), TaskDetailsActivity.this, 0);

                    }

                }
            }).showPopwindow();
            LogUtils.LOG("ceshi", "确认帮助网址+" + Urls.Baseurl_cui + Urls.helptask + "?tid=" + ID + "&user_token=" + Staticdata.static_userBean.getData().getUser_token(), "TaskDetailsActivity");

        } else {
            Intent intent_login = new Intent(TaskDetailsActivity.this, LoginActivity.class);
            startActivity(intent_login);
        }
    }
    void baojia(){
        if (Staticdata.isLogin) {
            if (!Staticdata.static_userBean.getData().getAppuser().getRole().contains("1")) {
                ToastUtils.showToast(TaskDetailsActivity.this, "请先认证帮手");
                Intent intent_renzheng = new Intent(TaskDetailsActivity.this, AuthenticationActivity.class);
                startActivity(intent_renzheng);
            } else {
                new Popwindow_bargin(TaskDetailsActivity.this, new Interence_bargin() {
                    @Override
                    public void onResult(String result) {//还价网络请求
                        String URL="";
                        if(app_type.equals("1")){//1   匹配单
                            URL=Urls.Baseurl_cui + Urls.barginPiPei;
                        }else {//广场单
                            URL=Urls.Baseurl_cui + Urls.barginmonry;
                            if(commison>Double.parseDouble(result)){
                                ToastUtils.showToast(TaskDetailsActivity.this,"还价金额低于雇主出价");
                                return;
                            }
                        }

                        Map map_bargin = new HashMap();
                        map_bargin.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                        map_bargin.put("task_id", "" + ID);
                        map_bargin.put("counteroffer_Amount", result);
                        new Volley_Utils(new Interface_volley_respose() {
                            @Override
                            public void onSuccesses(String respose) {
                                LogUtils.LOG("ceshi", "任务还价+" + respose, "TaskDetailsActivity");
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
                                    ToastUtils.showToast(TaskDetailsActivity.this, msg);
                                    requestTaseDetail();
                                } else {
                                    ToastUtils.showToast(TaskDetailsActivity.this, msg);
                                }
                            }
                            @Override
                            public void onError(int error) {

                            }
                        }).postHttp(URL, TaskDetailsActivity.this, 1, map_bargin);
                    }
                },popTitle).showpop();
            }
        } else {
            Intent intent_login = new Intent(TaskDetailsActivity.this, LoginActivity.class);
            startActivity(intent_login);
            finish();
        }
    }

    void setImage(String  image){
        if(image==null||image.equals("")){

        }else {
            imageview_urllist.clear();
            String []images=image.split(",");
            int len=images.length;
            LogUtils.LOG("ceshi","图片的个数"+images.length,"SkillDetailActivity分隔图片");
            for(int i=0;i<len;i++){
                imageview_urllist.add(images[i]);
            }
            adapter_gridviewpic.notifyDataSetChanged();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
