package com.jingnuo.quanmb.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jingnuo.quanmb.activity.BargainActivity;
import com.jingnuo.quanmb.activity.SystemMessageActivity;
import com.jingnuo.quanmb.quanmb.R;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_message extends Fragment {
    View rootview;
    //控件

    RelativeLayout mRelativelayout_bargain;
    RelativeLayout mRelativelayout_systemmessage;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_message,container,false);
        initview();
        initlistener();
        return  rootview;
    }

    private void initlistener() {
        mRelativelayout_bargain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend_bargain=new Intent(getActivity(), BargainActivity.class);
                getActivity().startActivity(intend_bargain);
            }
        });
        mRelativelayout_systemmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_system=new Intent(getActivity(), SystemMessageActivity.class);
                getActivity().startActivity(intent_system);
            }
        });
    }

    private void initview() {
        mRelativelayout_bargain=rootview.findViewById(R.id.relativelayout_Kanprice);
        mRelativelayout_systemmessage=rootview.findViewById(R.id.relativelayout_systemnotice);
    }
}
