package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.customview.SimpleRatingBar;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.TaskDetailBean;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.utils.LogUtils;
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

public class Mytaskdetails_JinxingzhongActivity extends BaseActivityother {
    //控件
    CircleImageView image_head; //商家头像
    SimpleRatingBar simpleRatingBar;//星级
    TextView text_name;//商户名字
    TextView text_vip;//会员商家
    TextView text_orders;//商户接单数
    TextView textview_yuyuetime;//预约时间
    TextView textview_yuyueaddress;//预约地点
    GridView gridview_userthink;//用户评价
    LinearLayout  linearLayout_cancle;//撤销
    LinearLayout  linearlayout_zixun;//客服
    LinearLayout  linearLayout_callphone;//打电话


    //数据
    String  ID;//任务id
    Map map_taskdetail;
    String  shopnumber="";

    //对象
    PermissionHelper mPermission;//动态申请权限
    TaskDetailBean taskDetailBean;

    Adapter_userthing  adapter_userthing;
    List<String> mData;//用户评价

    @Override
    public int setLayoutResID() {
        return R.layout.activity_mytaskdetails__jinxingzhong;
    }

    @Override
    protected void setData() {
        mPermission = new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);

    }

    @Override
    protected void initData() {
        ID=getIntent().getStringExtra("taskid");
        mData=new ArrayList<>();
        map_taskdetail = new HashMap();
        map_taskdetail.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_taskdetail.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_taskdetail.put("id", ID );
        LogUtils.LOG("ceshi", map_taskdetail.toString(), "MytaskDetailActivity");
        request(map_taskdetail);
    }

    @Override
    protected void initListener() {
        //撤销任务
        linearLayout_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tip = "";
                if (taskDetailBean.getData().getTask_Status_code().equals("01") || taskDetailBean.getData().getTask_Status_code().equals("08")) {
                    tip = "是否取消任务？";
                } else {
                    tip = "是否取消任务？";
                }
                new Popwindow_Tip(tip, Mytaskdetails_JinxingzhongActivity.this, new Interence_complteTask() {
                    @Override
                    public void onResult(boolean result) {
                        if (result) {
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
                                            Intent intent1 = new Intent(Mytaskdetails_JinxingzhongActivity.this, CancelloederActivity.class);
                                            intent1.putExtra("taskid", ID + "");
                                            startActivity(intent1);

                                        } else {
                                            ToastUtils.showToast(Mytaskdetails_JinxingzhongActivity. this, msg);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onError(int error) {

                                }
                            }).postHttp(Urls.Baseurl_cui + Urls.taskdetailscancle, Mytaskdetails_JinxingzhongActivity.this, 1, map_taskdetail);
                        }
                    }
                }).showPopwindow();
            }
        });
        //客服中心
        linearlayout_zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mytaskdetails_JinxingzhongActivity.this, ZixunKefuWebActivity.class);
                intent.putExtra("webtitle", "全民帮客服中心");
                intent.putExtra("type", "全民帮客服中心");
                intent.putExtra("URL", Urls.Baseurl_zixunkefu);
                startActivity(intent);
            }
        });
        //打电话
        linearLayout_callphone.setOnClickListener(new View.OnClickListener() {//打电话
            @Override
            public void onClick(View v) {
                if(shopnumber.equals("")){
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" +shopnumber );
                intent.setData(data);

                if (ActivityCompat.checkSelfPermission(Mytaskdetails_JinxingzhongActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

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
    }

    @Override
    protected void initView() {
        image_head=findViewById(R.id.image_head);
        simpleRatingBar=findViewById(R.id.simpleRatingBar);
        text_name=findViewById(R.id.text_name);
        text_vip=findViewById(R.id.text_vip);
        text_orders=findViewById(R.id.text_orders);
        textview_yuyuetime=findViewById(R.id.textview_yuyuetime);
        textview_yuyueaddress=findViewById(R.id.textview_yuyueaddress);
        gridview_userthink=findViewById(R.id.gridview_userthink);
        linearLayout_cancle=findViewById(R.id.linearLayout_cancle);
        linearlayout_zixun=findViewById(R.id.linearlayout_zixun);
        linearLayout_callphone=findViewById(R.id.linearLayout_callphone);
    }
    void setstar(float count) {
        simpleRatingBar.setNumberOfStars(5);
        simpleRatingBar.setFillColor(getResources().getColor(R.color.yellow_jianbian_start));
        simpleRatingBar.setStarBackgroundColor(getResources().getColor(R.color.gray_background));
        simpleRatingBar.setStepSize((float) 0.1);
        simpleRatingBar.setRating(count);
        simpleRatingBar.setDrawBorderEnabled(false);
        simpleRatingBar.setStarsSeparation(1);
    }

    void request(Map map) {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.mytaskdetails, "MytaskDetailActivity");
                LogUtils.LOG("ceshi", respose, "MytaskDetailActivity");
                taskDetailBean = new Gson().fromJson(respose, TaskDetailBean.class);
                Staticdata.taskDetailBeanStatic = taskDetailBean;
                shopnumber=taskDetailBean.getData().getBusiness_mobile_no();
                textview_yuyuetime.setText(taskDetailBean.getData().getTask_Time());
                textview_yuyueaddress.setText(taskDetailBean.getData().getRelease_address());
                text_name.setText(taskDetailBean.getData().getBusiness_name());
                Glide.with(Mytaskdetails_JinxingzhongActivity.this).load(taskDetailBean.getData().getB_h_url()).into(image_head);
               //用户评价
                mData.addAll( taskDetailBean.getData().getEvaluate() );
                adapter_userthing=new Adapter_userthing(mData,Mytaskdetails_JinxingzhongActivity.this);
                gridview_userthink.setAdapter(adapter_userthing);
                setstar((float) taskDetailBean.getData().getEvaluation_star());
                text_orders.setText(taskDetailBean.getData().getOverCount()+"单");
                if(taskDetailBean.getData().getMemberImgUrl()==null||taskDetailBean.getData().getMemberImgUrl().equals("")){
                    text_vip.setVisibility(View.INVISIBLE);
                }else {
                    text_vip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.mytaskdetails, Mytaskdetails_JinxingzhongActivity.this, 1, map);

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
    class Adapter_userthing extends BaseAdapter {
        List<String> mData;
        Context mContext;
        LayoutInflater mInflater;

        public Adapter_userthing(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mContext=mContext;
            this.mData=mDatas;
            mInflater=LayoutInflater.from(mContext);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder  viewholder=null;
            if(convertView==null){
                viewholder=new Viewholder();
                convertView=mInflater.inflate(R.layout.item_text4,null,false);
                viewholder.mtextview_choose=convertView.findViewById(R.id.text_text);
                convertView.setTag(viewholder);
            }else {
                viewholder= (Viewholder) convertView.getTag();
            }
            viewholder.mtextview_choose.setText(mData.get(position));

            return convertView;
        }
        class Viewholder {
            TextView mtextview_choose;
        }
    }
}

