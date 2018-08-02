package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.MenuBean;
import com.jingnuo.quanmb.R;
import java.util.List;

public class Adapter_menu  extends BaseAdapter{
    List<MenuBean> mdata;
    Context mContext;
    LayoutInflater mLayoutInflater;
    public Adapter_menu(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mdata=mDatas;
        this.mContext=mContext;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mLayoutInflater.inflate(R.layout.item_menu,null,false);
        TextView mtextviewname=convertView.findViewById(R.id.textname_menu);
        ImageView mImageview=convertView.findViewById(R.id.image_123);
        mtextviewname.setText(mdata.get(position).getMenu_name());
        mImageview.setImageBitmap(mdata.get(position).getmBitmap());
        return convertView;
    }
}
