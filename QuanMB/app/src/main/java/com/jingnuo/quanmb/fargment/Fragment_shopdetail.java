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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.IssueTaskNextActivity;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.MytaskDetailActivity;
import com.jingnuo.quanmb.activity.SkillDetailActivity;
import com.jingnuo.quanmb.customview.SimpleRatingBar;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Matchshoplistbean;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.utils.SizeUtils;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

import static io.rong.imlib.model.Conversation.ConversationType.PRIVATE;

@SuppressLint("ValidFragment")
public class Fragment_shopdetail extends Fragment{
    View  rootview;
    TextView text_name;
    TextView text_type;
    TextView text_orders;
    ImageView text_vip;
    SimpleRatingBar simpleRatingBar;
//    TextView text_lv;
    CircleImageView image_head;
//    LinearLayout button_choose;
//    LinearLayout linearlayout_zixun;

    //对象

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
        initview();
        setdata();
        initlistenner();


        return rootview;
    }

    private void initlistenner() {

//        button_choose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Popwindow_Tip("是否选择此商家下单?", getActivity(), new Interence_complteTask() {
//                    @Override
//                    public void onResult(boolean result) {
//                        if(result) {
//                            map_choosebissness=new HashMap();
//                            map_choosebissness.put("user_token", Staticdata.static_userBean.getData().getUser_token());
//                            map_choosebissness.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
//                            map_choosebissness.put("task_id", task_id);
//                            map_choosebissness.put("business_no", matchingBean.getBusiness_no());
////                    map_choosebissness.put("counteroffer_amount", text_money.getText());
//                            new Volley_Utils(new Interface_volley_respose() {
//                                @Override
//                                public void onSuccesses(String respose) {
//                                    int status = 0;
//                                    String msg = "";
//                                    String data = "";
//                                    try {
//                                        JSONObject object = new JSONObject(respose);
//                                        status = (Integer) object.get("code");//
//                                        msg = (String) object.get("message");//
//                                        data = (String) object.get("data");//
//                                        if(status==1){
////                                    timer.cancel();
//                                            Intent intentpay = new Intent(getActivity(), MytaskDetailActivity.class);
////                                    intentpay.putExtra("title", "匹配商户成功付款");//支付需要传 isBargainPay:(是否还价支付,	Y：是	N：否)还价支付时必传Y，其他支付可不传或N
//                                            intentpay.putExtra("id", task_id);
////                                    intentpay.putExtra("order_no", data);
////                                    intentpay.putExtra("taskid", task_id);
//                                            startActivity(intentpay);
//                                            getActivity().finish();
//
//                                        }
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                }
//
//                                @Override
//                                public void onError(int error) {
//
//                                }
//                            }).postHttp(Urls.Baseurl_cui+Urls.chooseBusiness,getContext(),1,map_choosebissness);
//                        }
//                    }
//                }).showPopwindow();
//            }
//        });
//        linearlayout_zixun.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RongIM.getInstance().setMessageAttachedUserInfo(true);
//                RongIM.getInstance().setCurrentUserInfo(new UserInfo(matchingBean.getBusiness_no(),
//                        matchingBean.getBusiness_name(),
//                        Uri.parse(matchingBean.getHeadUrl())));
//                RongIM.getInstance().setCurrentUserInfo(new UserInfo(Staticdata.static_userBean.getData().getAppuser().getClient_no(),
//                        Staticdata.static_userBean.getData().getAppuser().getNick_name(),
//                        Uri.parse( Staticdata.static_userBean.getData().getImg_url())));
////                RongIM.getInstance().refreshUserInfoCache(new UserInfo(matchingBean.getClient_no(),
////                        matchingBean.getBusiness_name(),
////                        Uri.parse(matchingBean.getHeadUrl())));
////                RongIM.getInstance().setMessageAttachedUserInfo(true);
//                RongIM.getInstance().startPrivateChat(getActivity(),matchingBean.getBusiness_no(),matchingBean.getBusiness_name());
//            }
//        });
    }

    private void setdata() {
        text_name.setText(matchingBean.getBusiness_name());
        text_type.setText("主营："+matchingBean.getSpecialty_name());
        Glide.with(getActivity()).load(matchingBean.getHeadUrl()).into(image_head);
        if(!matchingBean.getMemberImgUrl().equals("")){
            text_vip.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(matchingBean.getMemberImgUrl()).into(text_vip);

        }else {
            text_vip.setVisibility(View.INVISIBLE);
        }
        text_orders.setText(matchingBean.getOverCount()+"单");
        setstar((float) matchingBean.getEvaluation_star());
//        timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                mhandler.sendEmptyMessage(0);
//            }
//        };
//        timer.schedule(timerTask, 0, 100);
    }

    private void initview() {
        text_name=rootview.findViewById(R.id.text_name);
        text_type=rootview.findViewById(R.id.text_main);
        text_orders=rootview.findViewById(R.id.text_orders);
        text_vip=rootview.findViewById(R.id.text_vip);
        simpleRatingBar=rootview.findViewById(R.id.SimpleRatingBar);
        image_head=rootview.findViewById(R.id.image_head);
//        button_choose=rootview.findViewById(R.id.button_choose);
//        linearlayout_zixun=rootview.findViewById(R.id.linearlayout_zixun);
//        iamge_newacount=rootview.findViewById(R.id.iamge_newacount);


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


//    Timer timer;
//    private Handler mhandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    text_money.setText(Staticdata.price);
//                    break;
//            }
//        }
//
//    };

    @Override
    public void onDestroy() {
        super.onDestroy();
//        timer.cancel();
//        timer=null;
    }
}
