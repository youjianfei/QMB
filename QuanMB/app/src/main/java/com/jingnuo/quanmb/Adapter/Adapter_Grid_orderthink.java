package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.MenuBean;

import java.util.List;

public class Adapter_Grid_orderthink extends BaseAdapter{
    List<String> mdata;
    Context mContext;
    LayoutInflater mLayoutInflater;
    public Adapter_Grid_orderthink(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mdata=mDatas;
        this.mContext=mContext;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mLayoutInflater.inflate(R.layout.item_orderthink,null,false);
        TextView mtextvieworder=convertView.findViewById(R.id.textname_order);
        mtextvieworder.setText(mdata.get(position));
        return convertView;
    }
}
