package com.jingnuo.quanmb.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.AuthenticationActivity;
import com.jingnuo.quanmb.activity.CashoutActivity;
import com.jingnuo.quanmb.activity.DatailAddressActivity;
import com.jingnuo.quanmb.activity.LoginActivity;
import com.jingnuo.quanmb.activity.MyOrderActivity;
import com.jingnuo.quanmb.activity.MySkillCollectActivity;
import com.jingnuo.quanmb.activity.PayActivity;
import com.jingnuo.quanmb.activity.PersonInfoActivity;
import com.jingnuo.quanmb.activity.RechargeActivity;
import com.jingnuo.quanmb.activity.SettingActivity;
import com.jingnuo.quanmb.activity.ShopCenterActivity;
import com.jingnuo.quanmb.activity.ShopInActivity;
import com.jingnuo.quanmb.activity.ShopInNextActivity;
import com.jingnuo.quanmb.activity.SubmitSuccessActivity;
import com.jingnuo.quanmb.activity.WalletActivity;
import com.jingnuo.quanmb.class_.WechatPay;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_person extends Fragment implements View.OnClickListener{
    View rootview;
    private UMShareAPI mShareAPI;//第三方登录登录

    //控件
    RelativeLayout mTextview_shopcenter;
    RelativeLayout mTextview_address;
    RelativeLayout mTextview_banghsou;
    ImageView mImageview_setting;
    CircleImageView  mCircleImage;
    ImageView mimage_chengwei;
    TextView mTextview_nickname;
    TextView mTextview_moneycount;
    TextView mTextview_chengwei;
    RelativeLayout mTextview_myorder;
    RelativeLayout mTextview_mycollect;
    RelativeLayout mTextview_aboutus;
    RelativeLayout mTextview_logout;
    Button mButton_rechange;//充值
    Button mButton_cashout;//提现
    LinearLayout mLearlayout_wallet;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_person, container, false);
        initview();
        setview();
        initdata();
        setdata();
        initlistener();


        return rootview;
    }

    private void initlistener() {
        mTextview_banghsou.setOnClickListener(this);
        mTextview_address.setOnClickListener(this);
        mImageview_setting.setOnClickListener(this);
        mCircleImage.setOnClickListener(this);
        mTextview_shopcenter.setOnClickListener(this);
        mTextview_logout.setOnClickListener(this);
        mTextview_myorder.setOnClickListener(this);
        mTextview_mycollect.setOnClickListener(this);
        mTextview_aboutus.setOnClickListener(this);
        mButton_rechange.setOnClickListener(this);
        mButton_cashout.setOnClickListener(this);
        mLearlayout_wallet.setOnClickListener(this);
    }

    private void setdata() {

    }

    private void initdata() {
        mTextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
//        if(Staticdata.static_userBean.getData().getBusiness_status()==1){
//            mTextview_chengwei.setText("商户");
//        }else if(Staticdata.static_userBean.getData().getHelper_status()==1){
//            mTextview_chengwei.setText("帮手");
//        }else {
//            mTextview_chengwei.setText("未认证");
//        }
        mTextview_chengwei.setText(Staticdata.static_userBean.getData().getAppellation_name());
        Glide.with(this).load(Staticdata.static_userBean.getData().getImg_url()).into(mCircleImage);

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.LOG("ceshi","onResume","fragmentperson");
//        mTextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
//        LogUtils.LOG("ceshi",Staticdata.static_userBean.getData().getImg_url(),"touxaing");
//        Glide.with(this).load(Staticdata.static_userBean.getData().getImg_url()).into(mCircleImage);
//        Glide.with(this).load(Staticdata.static_userBean.getData().getIconImgUrl()).into(mimage_chengwei);
        requestInfo();
    }

    void requestInfo(){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                String state = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    LogUtils.LOG("ceshi",respose,"个人中心");
                    Staticdata.static_userBean=new Gson().fromJson(respose, UserBean.class);
                    mTextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
                    LogUtils.LOG("ceshi",Staticdata.static_userBean.getData().getImg_url(),"touxaing");
                    Glide.with(getActivity()).load(Staticdata.static_userBean.getData().getImg_url()).into(mCircleImage);
                    Glide.with(getActivity()).load(Staticdata.static_userBean.getData().getIconImgUrl()).into(mimage_chengwei);
                    mTextview_moneycount.setText(Staticdata.static_userBean.getData().getAppuser().getBalance()+"");
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.personinfo+Staticdata.static_userBean.getData().getUser_token(),getActivity(),0);
    }
    private void setview() {
        requestInfo();
    }

    private void initview() {
        mTextview_banghsou = rootview.findViewById(R.id.textview_bangshou);
        mTextview_address=rootview.findViewById(R.id.textview_address);
        mTextview_moneycount=rootview.findViewById(R.id.textview_2);
        mImageview_setting=rootview.findViewById(R.id.image_setting);
        mCircleImage=rootview.findViewById(R.id.image_userpic);
        mTextview_shopcenter=rootview.findViewById(R.id.textview_shopcenter);
        mTextview_nickname=rootview.findViewById(R.id.text_username);
        mTextview_chengwei=rootview.findViewById(R.id.textview_phonenumber);
        mTextview_myorder=rootview.findViewById(R.id.text_myorder);
        mTextview_logout=rootview.findViewById(R.id.textview_logout);
        mTextview_mycollect=rootview.findViewById(R.id.textview_colllect);
        mTextview_aboutus=rootview.findViewById(R.id.textview_aboutus);
        mButton_rechange=rootview.findViewById(R.id.button_recharge);
        mButton_cashout=rootview.findViewById(R.id.button_tixian);
        mimage_chengwei=rootview.findViewById(R.id.image_chengwei);
        mLearlayout_wallet=rootview.findViewById(R.id.linearlayout_wallete);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearlayout_wallete://钱包
                Intent intent_wallet=new Intent(getActivity(), WalletActivity.class);
                intent_wallet.putExtra("money",Staticdata.static_userBean.getData().getAppuser().getBalance()+"");
                startActivity(intent_wallet);
                break;
            case R.id.button_tixian://提现
                Intent intent_cash=new Intent(getActivity(), CashoutActivity.class);
                intent_cash.putExtra("money",Staticdata.static_userBean.getData().getAppuser().getBalance()+"");
                intent_cash.putExtra("TransferType","1");
                startActivity(intent_cash);

                break;
            case R.id.button_recharge://充值
                Intent intent_recharge=new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent_recharge);

                break;
            case R.id.textview_aboutus://关于我们

                break;

            case R.id.textview_colllect:
                Intent intent_collect=new Intent(getActivity(),MySkillCollectActivity.class);
                startActivity(intent_collect);
                break;
            case R.id.textview_bangshou:
                if(Staticdata.static_userBean.getData().getAppuser().getRole().equals("1")){
                    Intent intent_shopcenter=new Intent(getActivity(), ShopCenterActivity.class);
                    intent_shopcenter.putExtra("type",1);//1  帮手
                    getActivity().startActivity(intent_shopcenter);
                }else if(Staticdata.static_userBean.getData().getAppuser().getRole().equals("2")) {//即时帮手也是商户
                    ToastUtils.showToast(getContext(),"你已经是商户啦！");
                }
                else {//申请帮手界面
//                    Intent intent_anthentication = new Intent(getActivity(), AuthenticationActivity.class);
//                    getActivity().startActivity(intent_anthentication);
                    Map map=new HashMap();
                    map.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                    map.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi",respose,"帮手审核状态");
                            int status = 0;
                            String msg = "";
                            String state = "";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");//
                                msg = (String) object.get("msg");//
                                state = (String) object.get("status");//
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (state.equals("2")){//审核通过

                                Intent intent_submit=new Intent(getActivity(), SubmitSuccessActivity.class);
                                intent_submit.putExtra("state","3");
                                getActivity().startActivity(intent_submit);
                            }else if(status==0){//没提交
                                Intent intent_shopin=new Intent(getActivity(), AuthenticationActivity.class);
                                getActivity().startActivity(intent_shopin);

                            }else if(state.equals("1")){//正在审核

                                Intent intent_submit=new Intent(getActivity(),SubmitSuccessActivity.class);
                                intent_submit.putExtra("state","2");
                                startActivity(intent_submit);

                            }else if(state.equals("3")){//审核失败
                                Intent intent_submit=new Intent(getActivity(),SubmitSuccessActivity.class);
                                intent_submit.putExtra("state","4");
                                startActivity(intent_submit);
                            }

                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl_hu+Urls.helpIn_state,getActivity(),1,map);
                    LogUtils.LOG("ceshi",Urls.Baseurl_hu+Urls.helpIn_state,"帮手审核状态");
                    LogUtils.LOG("ceshi",map.toString(),"帮手审核状态map");
                }
                break;
            case R.id.textview_address:
                Intent intent_datiladdress = new Intent(getActivity(), DatailAddressActivity.class);
                getActivity().startActivity(intent_datiladdress);
                break;
            case R.id.image_setting:
                Intent intent_setting=new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent_setting);
                break;
            case  R.id.image_userpic:
                Intent intent_personInfo=new Intent(getActivity(), PersonInfoActivity.class);
                getActivity().startActivity(intent_personInfo);
                break;
            case  R.id.textview_shopcenter://1  商户  0  不是商户
                LogUtils.LOG("ceshi",Urls.Baseurl+Urls.shopIn_state+Staticdata.static_userBean.getData().getUser_token(),"检测商户审核状态接口");
                if(Staticdata.static_userBean.getData().getAppuser().getRole().equals("2")){
                    LogUtils.LOG("ceshi","检测商户审核状态dfgdfsgfd","检测商户审核状态");

                    Intent intent_shopcenter=new Intent(getActivity(), ShopCenterActivity.class);
                    intent_shopcenter.putExtra("type",2);//2  商户
                    getActivity().startActivity(intent_shopcenter);

                }else {
                    LogUtils.LOG("ceshi","检测商户审核状态"+Staticdata.static_userBean.getData().getAppuser().getRole(),"检测商户审核状态");
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {

                            int status = 0;
                            String msg = "";
                            String state = "";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");//
                                msg = (String) object.get("message");//
                                state = (String) object.get("status");//
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (state.equals("00")){//审核通过
                                Intent intent_shopcenter=new Intent(getActivity(), ShopCenterActivity.class);
                                intent_shopcenter.putExtra("type",2);//2  商户
                                getActivity().startActivity(intent_shopcenter);
                            }else if(state.equals("01")){//没提交
                                Intent intent_shopin=new Intent(getActivity(), ShopInActivity.class);
                                getActivity().startActivity(intent_shopin);

                            }else if(state.equals("02")){//正在审核
//                            Intent intent_shopinext=new Intent(getActivity(), ShopInNextActivity.class);
//                            getActivity().startActivity(intent_shopinext);
                                Intent intent_submit=new Intent(getActivity(),SubmitSuccessActivity.class);
                                intent_submit.putExtra("state","2");
                                startActivity(intent_submit);

                            }else if(state.equals("03")){//没提交审核
                                Intent intent_shopin=new Intent(getActivity(), ShopInActivity.class);
                                getActivity().startActivity(intent_shopin);
                            }
                        }
                        @Override
                        public void onError(int error) {

                        }
                    }).Http(Urls.Baseurl+Urls.shopIn_state+Staticdata.static_userBean.getData().getUser_token(),getContext(),0);

                }
                 break;
            case R.id.text_myorder:
                Intent intent_myorder=new Intent(getActivity(), MyOrderActivity.class);
                getActivity().startActivity(intent_myorder);

                break;

            case R.id.textview_logout:
                logout();
                SharedPreferencesUtils.putString(getActivity(),"QMB","password","");
                Staticdata.isLogin=false;
                Staticdata.static_userBean.setData(null);//用户信息清空
                Intent intent_logout=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent_logout);
                getActivity().finish();
                break;
        }

    }

    public void  logout(){//登出注销微信授权
        mShareAPI = UMShareAPI.get(getActivity());
        mShareAPI.deleteOauth(getActivity(), SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }


}
