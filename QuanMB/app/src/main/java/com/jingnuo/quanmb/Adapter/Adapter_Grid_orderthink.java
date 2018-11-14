package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.MenuBean;
import com.jingnuo.quanmb.entityclass.OrderThinkGridBean;

import java.util.List;

public class Adapter_Grid_orderthink extends BaseAdapter{
    List<OrderThinkGridBean> mdata;
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
        RelativeLayout relayout_main=convertView.findViewById(R.id.relayout_main);
        TextView mtextvieworder=convertView.findViewById(R.id.textname_order);
        mtextvieworder.setText(mdata.get(position).getName());
        if(mdata.get(position).isIsselect()){
            relayout_main.setSelected(true);
        }else {
            relayout_main.setSelected(false);

        }
        return convertView;
    }
}
