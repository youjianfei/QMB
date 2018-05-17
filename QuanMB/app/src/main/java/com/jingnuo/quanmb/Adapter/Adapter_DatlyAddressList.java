package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class Adapter_DatlyAddressList extends BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater mInflater;

    public Adapter_DatlyAddressList(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mData=mDatas;
        this.mContext=mContext;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_datiladdress_list,null,false);
            holder.mText_name=convertView.findViewById(R.id.text_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mText_name.setText(mData.get(position));

        return convertView;
    }
    class  ViewHolder {
        TextView mText_name;
    }
}
