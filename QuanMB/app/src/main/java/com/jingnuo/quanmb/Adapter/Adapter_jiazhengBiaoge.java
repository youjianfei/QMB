package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.JiazhengbiaogeBean;

import java.util.List;

/**
 * Created by 飞 on 2018/11/21.
 */

public class Adapter_jiazhengBiaoge extends  BaseAdapter{
    private Context mContext;
    List<JiazhengbiaogeBean.DataBean.AppTaskReferenceFormBean> mDatas;
    private LayoutInflater mInflater;



    public Adapter_jiazhengBiaoge(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext=mContext;
        this.mDatas=mDatas;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mInflater.inflate(R.layout.item_jiazhengbiaoge,null,false);
        TextView textView1=convertView.findViewById(R.id.textview_1);
        TextView textView2=convertView.findViewById(R.id.textview_2);
        TextView textView3=convertView.findViewById(R.id.textview_3);
        ImageView iamge_1=convertView.findViewById(R.id.iamge_1);
        ImageView iamge_2=convertView.findViewById(R.id.iamge_2);
        textView1.setText(mDatas.get(position).getTable1());
        textView2.setText(mDatas.get(position).getTable2());
        textView3.setText(mDatas.get(position).getTable3());
        if(position==0){
            iamge_1.setVisibility(View.GONE);
            iamge_2.setVisibility(View.GONE);
        }else {
            iamge_1.setVisibility(View.VISIBLE);
            iamge_2.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
