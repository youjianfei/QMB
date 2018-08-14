package com.jingnuo.quanmb.fargment;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.IssueTaskNextActivity;
import com.jingnuo.quanmb.activity.PayActivity;
import com.jingnuo.quanmb.activity.SkillDetailActivity;
import com.jingnuo.quanmb.customview.SimpleRatingBar;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Matchshoplistbean;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("ValidFragment")
public class Fragment_shopdetail extends Fragment{
    View  rootview;
    TextView text_name;
    TextView text_type;
    TextView text_orders;
    ImageView image_vip;
    SimpleRatingBar simpleRatingBar;
//    TextView text_lv;
    CircleImageView image_head;
    ImageView imageView_call;
    Button button_choose;
    TextView text_money;

    //对象
    PermissionHelper mPermission;//动态申请权限

    Map map_choosebissness;
    String task_id="";

    double xingxing=0;


    //数据
    Matchshoplistbean.DataBean.MatchingBean matchingBean;
    @SuppressLint("ValidFragment")
    public Fragment_shopdetail(Matchshoplistbean.DataBean.MatchingBean matchingBean,String task_id) {
        this.matchingBean = matchingBean;
        this.task_id = task_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_shopdetails, container, false);
        mPermission = new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        initview();
        setdata();
        initlistenner();


        return rootview;
    }

    private void initlistenner() {
        imageView_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + matchingBean.getBusiness_mobile_no());
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

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
        button_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_money.getText().equals("等待商户出价")){
                    ToastUtils.showToast(getContext(),"该商户还未出价");
                }else {
                    map_choosebissness=new HashMap();
                    map_choosebissness.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                    map_choosebissness.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    map_choosebissness.put("task_id", task_id);
                    map_choosebissness.put("business_no", matchingBean.getBusiness_no());
                    map_choosebissness.put("counteroffer_amount", text_money.getText());
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
                                    timer.cancel();
                                    Intent intentpay = new Intent(getActivity(), PayActivity.class);
                                    intentpay.putExtra("title", "匹配商户成功付款");//支付需要传 isBargainPay:(是否还价支付,	Y：是	N：否)还价支付时必传Y，其他支付可不传或N
                                    intentpay.putExtra("amount", text_money.getText());
                                    intentpay.putExtra("order_no", data);
                                    intentpay.putExtra("taskid", task_id);
                                    startActivity(intentpay);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl_cui+Urls.chooseBusiness,getContext(),1,map_choosebissness);
                }

            }
        });
    }

    private void setdata() {
        text_name.setText(matchingBean.getBusiness_name());
        text_type.setText("主营："+matchingBean.getSpecialty_name());
//        text_lv.setText(matchingBean.getAppellation_name());
        Glide.with(getActivity()).load(matchingBean.getHeadUrl()).into(image_head);
        if(!matchingBean.getMemberImgUrl().equals("")){
            image_vip.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(matchingBean.getMemberImgUrl()).into(image_vip);
        }else {
            image_vip.setVisibility(View.INVISIBLE);
        }
        text_orders.setText("已完成"+matchingBean.getOverCount()+"单");
        setstar((float) matchingBean.getEvaluation_star());
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mhandler.sendEmptyMessage(0);
            }
        };
        timer.schedule(timerTask, 0, 100);
    }

    private void initview() {
        text_name=rootview.findViewById(R.id.text_name);
        text_type=rootview.findViewById(R.id.text_main);
        text_orders=rootview.findViewById(R.id.text_orders);
        image_vip=rootview.findViewById(R.id.image_vip);
        simpleRatingBar=rootview.findViewById(R.id.SimpleRatingBar);
//        text_lv=rootview.findViewById(R.id.text_level);
        image_head=rootview.findViewById(R.id.image_head);
        imageView_call=rootview.findViewById(R.id.image_callphone);
        button_choose=rootview.findViewById(R.id.button_choose);
        text_money=rootview.findViewById(R.id.text_money);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
    Timer timer;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    text_money.setText(Staticdata.price);
                    break;
            }
        }

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer=null;
    }
}