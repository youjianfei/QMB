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

import com.jingnuo.quanmb.activity.AuthenticationActivity;
import com.jingnuo.quanmb.activity.DatailAddressActivity;
import com.jingnuo.quanmb.activity.LoginActivity;
import com.jingnuo.quanmb.activity.PersonInfoActivity;
import com.jingnuo.quanmb.activity.SettingActivity;
import com.jingnuo.quanmb.activity.ShopInActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_person extends Fragment implements View.OnClickListener{
    View rootview;

    //控件
    TextView mTextview_banghsou,mTextview_shopcenter;
    TextView mTextview_address;
    ImageView mImageview_setting;
    CircleImageView  mCircleImage;
    TextView mTextview_nickname;
    TextView mTextview_phonenumber;


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
    }

    private void setdata() {
    }

    private void initdata() {
        mTextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
        mTextview_phonenumber.setText(Staticdata.static_userBean.getData().getAppuser().getMobile_no());

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
        mTextview_logout=rootview.findViewById(R.id.textview_logout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textview_bangshou:
                Intent intent_anthentication = new Intent(getActivity(), AuthenticationActivity.class);
                getActivity().startActivity(intent_anthentication);
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
            case  R.id.textview_shopcenter:
                Intent intent_shopin=new Intent(getActivity(), ShopInActivity.class);
                getActivity().startActivity(intent_shopin);
                break;

            case R.id.textview_logout:
                SharedPreferencesUtils.putString(getActivity(),"QMB","password","");
                Staticdata.isLogin=false;
                Intent intent_logout=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent_logout);
                getActivity().finish();
                break;
        }

    }
}
