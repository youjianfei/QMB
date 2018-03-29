package com.jingnuo.quanmb.fargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingnuo.quanmb.quanmb.R;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_message extends Fragment {
    View rootview;
    //控件



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_message,container,false);
        return  rootview;
    }
}
