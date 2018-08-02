package com.jingnuo.quanmb.fargment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.Matchshoplistbean;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.master.permissionhelper.PermissionHelper;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("ValidFragment")
public class Fragment_shopdetail extends Fragment{
    View  rootview;
    TextView text_name;
    TextView text_type;
    TextView text_lv;
    CircleImageView image_head;
    ImageView imageView_call;
    Button button_choose;

    //对象
    PermissionHelper mPermission;//动态申请权限



    //数据
    Matchshoplistbean.DataBean.MatchingBean matchingBean;
    @SuppressLint("ValidFragment")
    public Fragment_shopdetail(Matchshoplistbean.DataBean.MatchingBean matchingBean) {
        this.matchingBean = matchingBean;
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
        imageView_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(getContext(),"dainjhie");
            }
        });
    }

    private void setdata() {
        text_name.setText(matchingBean.getBusiness_name());
        text_type.setText("主营："+matchingBean.getSpecialty_name());
        text_lv.setText(matchingBean.getAppellation_name());
        Glide.with(getActivity()).load(matchingBean.getHeadUrl()).into(image_head);
    }

    private void initview() {
        text_name=rootview.findViewById(R.id.text_name);
        text_type=rootview.findViewById(R.id.text_main);
        text_lv=rootview.findViewById(R.id.text_level);
        image_head=rootview.findViewById(R.id.image_head);
        imageView_call=rootview.findViewById(R.id.image_callphone);
        button_choose=rootview.findViewById(R.id.button_choose);

    }


}
