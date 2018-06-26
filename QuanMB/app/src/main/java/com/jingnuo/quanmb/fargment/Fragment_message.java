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

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.BargainActivity;
import com.jingnuo.quanmb.activity.BarginmessageListActivity;
import com.jingnuo.quanmb.activity.DealActivity;
import com.jingnuo.quanmb.activity.SystemMessageActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.NewMessage_Bean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_message extends Fragment {
    View rootview;
    public static Fragment_message mFragment_message;
    //控件
    RelativeLayout mRelativelayout_bargain;
    RelativeLayout mRelativelayout_systemmessage;
    RelativeLayout mRelativelayout_dealmessage;

    TextView mTextview_systemmessage;
    TextView mTextview_bargainmessage;
    TextView mTextview_jiaoyimeaage;

    static ImageView mImageView_dot1;
    static ImageView mImageView_dot2;
    static ImageView mImageView_dot3;


    //数据
    Map map_getnewmessage;

    NewMessage_Bean newMessage_bean;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_message,container,false);
        initview();
        setdata();
        initlistener();
        return  rootview;
    }

    private void setdata() {
        mFragment_message=this;
        map_getnewmessage=new HashMap();
        map_getnewmessage.put("receive_client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_getnewmessage.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        request();
    }

    private void initlistener() {
        mRelativelayout_bargain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend_bargain=new Intent(getActivity(), BarginmessageListActivity.class);
                mImageView_dot2.setVisibility(View.GONE);
                getActivity().startActivity(intend_bargain);
            }
        });
        mRelativelayout_systemmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_system=new Intent(getActivity(), SystemMessageActivity.class);
                mImageView_dot1.setVisibility(View.GONE);
                getActivity().startActivity(intent_system);
            }
        });
        mRelativelayout_dealmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_deal=new Intent(getActivity(), DealActivity.class);
                mImageView_dot3.setVisibility(View.GONE);
                getActivity().startActivity(intent_deal);
            }
        });
    }
    public   void setDot(int num){
        LogUtils.LOG("ceshi",num+"","推送3");
        switch (num){
            case 1:
                mImageView_dot1.setVisibility(View.VISIBLE);
                break;
            case 2:
                mImageView_dot2.setVisibility(View.VISIBLE);
                break;
            case 3:
                mImageView_dot3.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initview() {
        mRelativelayout_bargain=rootview.findViewById(R.id.relativelayout_Kanprice);
        mRelativelayout_systemmessage=rootview.findViewById(R.id.relativelayout_systemnotice);
        mRelativelayout_dealmessage=rootview.findViewById(R.id.relativelayout_Jiaoyinotice);
        mTextview_systemmessage=rootview.findViewById(R.id.text_systemnotice);
        mTextview_bargainmessage=rootview.findViewById(R.id.text_moneynotice);
        mTextview_jiaoyimeaage=rootview.findViewById(R.id.text_jiaoyitixing);
        mImageView_dot1=rootview.findViewById(R.id.image_reddot1);
        mImageView_dot2=rootview.findViewById(R.id.image_reddot2);
        mImageView_dot3=rootview.findViewById(R.id.image_reddot3);
    }
    void request(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                newMessage_bean=new Gson().fromJson(respose,NewMessage_Bean.class);
                mTextview_systemmessage.setText(newMessage_bean.getData().get(0).getContent());
                mTextview_bargainmessage.setText(newMessage_bean.getData().get(1).getContent());
                mTextview_jiaoyimeaage.setText(newMessage_bean.getData().get(2).getContent());
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.getNewmessage,getActivity(),1,map_getnewmessage);

    }
    @Override
    public void onHiddenChanged(boolean hidden) {//fragment显示与隐藏状态监听
        super.onHiddenChanged(hidden);
        if(!hidden){
            request();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        request();
    }
}
