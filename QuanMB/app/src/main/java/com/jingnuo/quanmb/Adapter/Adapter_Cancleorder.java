package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.CancelOrderBean;

import java.util.List;

public class Adapter_Cancleorder extends  BaseAdapter {
    List<CancelOrderBean.DataBean>mdata;
    Context mContext;
    LayoutInflater mInflater;


    public Adapter_Cancleorder(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mdata=mDatas;
        this.mContext=mContext;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

          convertView=mInflater.inflate(R.layout.item_cancleorder,null,false);
          TextView  textview_result=convertView.findViewById(R.id.textview_result);
          ImageView iamage_select=convertView.findViewById(R.id.iamage_select);
        textview_result.setText(mdata.get(position).getTask_des());
        if(mdata.get(position).isIsselect()){
            iamage_select.setSelected(true);
        }else {
            iamage_select.setSelected(false);
        }



        return convertView;
    }

}
