package com.jingnuo.quanmb.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.activity.AuthenticationActivity;
import com.jingnuo.quanmb.activity.DatailAddressActivity;
import com.jingnuo.quanmb.activity.LoginActivity;
import com.jingnuo.quanmb.activity.MyOrderActivity;
import com.jingnuo.quanmb.activity.PersonInfoActivity;
import com.jingnuo.quanmb.activity.SettingActivity;
import com.jingnuo.quanmb.activity.ShopCenterActivity;
import com.jingnuo.quanmb.activity.ShopInActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_person extends Fragment implements View.OnClickListener{
    View rootview;
    private UMShareAPI mShareAPI;//第三方登录登录

    //控件
    TextView mTextview_banghsou,mTextview_shopcenter;
    TextView mTextview_address;
    ImageView mImageview_setting;
    CircleImageView  mCircleImage;
    TextView mTextview_nickname;
    TextView mTextview_phonenumber;
    TextView mTextview_myorder;


    TextView mTextview_logout;


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
    }

    private void setdata() {
    }

    private void initdata() {
        mTextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
        mTextview_phonenumber.setText(Staticdata.static_userBean.getData().getAppuser().getMobile_no());
        Glide.with(this).load(Staticdata.static_userBean.getData().getImg_url()).into(mCircleImage);

    }
//    @Override
//    public void onHiddenChanged(boolean hidden) {//重新显示fragment 需要执行的操作
//        super.onHiddenChanged(hidden);
//        LogUtils.LOG("ceshi","onHiddenChanged"+hidden,"fragmentperson");
//        if(!hidden){
//
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.LOG("ceshi","onResume","fragmentperson");
        mTextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
        mTextview_phonenumber.setText(Staticdata.static_userBean.getData().getAppuser().getMobile_no());
        LogUtils.LOG("ceshi",Staticdata.static_userBean.getData().getImg_url(),"touxaing");
        Glide.with(this).load(Staticdata.static_userBean.getData().getImg_url()).into(mCircleImage);
    }

    private void setview() {
    }

    private void initview() {
        mTextview_banghsou = rootview.findViewById(R.id.textview_bangshou);
        mTextview_address=rootview.findViewById(R.id.textview_address);
        mImageview_setting=rootview.findViewById(R.id.image_setting);
        mCircleImage=rootview.findViewById(R.id.image_userpic);
        mTextview_shopcenter=rootview.findViewById(R.id.textview_shopcenter);
        mTextview_nickname=rootview.findViewById(R.id.text_username);
        mTextview_phonenumber=rootview.findViewById(R.id.textview_phonenumber);
        mTextview_myorder=rootview.findViewById(R.id.text_myorder);
        mTextview_logout=rootview.findViewById(R.id.textview_logout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textview_bangshou:
                if(Staticdata.static_userBean.getData().getHelper_status()==1){
                    Intent intent_shopcenter=new Intent(getActivity(), ShopCenterActivity.class);
                    intent_shopcenter.putExtra("type",1);//1  帮手
                    getActivity().startActivity(intent_shopcenter);
                }else {//申请帮手界面
                    Intent intent_anthentication = new Intent(getActivity(), AuthenticationActivity.class);
                    getActivity().startActivity(intent_anthentication);
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
                if(Staticdata.static_userBean.getData().getBusiness_status()==1){
                    Intent intent_shopcenter=new Intent(getActivity(), ShopCenterActivity.class);
                    intent_shopcenter.putExtra("type",2);//2  商户
                    getActivity().startActivity(intent_shopcenter);

                }else {//申请商户界面
                    Intent intent_shopin=new Intent(getActivity(), ShopInActivity.class);
                    getActivity().startActivity(intent_shopin);
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
