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
import com.jingnuo.quanmb.activity.PersonInfoActivity;
import com.jingnuo.quanmb.activity.SettingActivity;
import com.jingnuo.quanmb.activity.ShopInActivity;
import com.jingnuo.quanmb.quanmb.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_person extends Fragment implements View.OnClickListener{
    View rootview;

    //控件
    TextView mTextview_authentication,mTextview_shopin;
    RelativeLayout mRelativelayout_address;
    ImageView mImageview_setting;
    CircleImageView  mCircleImage;

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
        mTextview_authentication.setOnClickListener(this);
        mRelativelayout_address.setOnClickListener(this);
        mImageview_setting.setOnClickListener(this);
        mCircleImage.setOnClickListener(this);
        mTextview_shopin.setOnClickListener(this);
    }

    private void setdata() {
    }

    private void initdata() {
    }

    private void setview() {
    }

    private void initview() {
        mTextview_authentication = rootview.findViewById(R.id.textview_authentication);
        mRelativelayout_address=rootview.findViewById(R.id.relative_address);
        mImageview_setting=rootview.findViewById(R.id.image_setting);
        mCircleImage=rootview.findViewById(R.id.image_userpic);
        mTextview_shopin=rootview.findViewById(R.id.textview_shopin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textview_authentication:
                Intent intent_anthentication = new Intent(getActivity(), AuthenticationActivity.class);
                getActivity().startActivity(intent_anthentication);
                break;
            case R.id.relative_address:
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
            case  R.id.textview_shopin:
                Intent intent_shopin=new Intent(getActivity(), ShopInActivity.class);
                getActivity().startActivity(intent_shopin);
                break;
        }

    }
}
