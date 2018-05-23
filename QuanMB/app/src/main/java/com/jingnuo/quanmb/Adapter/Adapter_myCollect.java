package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

public class Adapter_myCollect extends BaseAdapter {
    List<String> mData;
    Context mContext;
    LayoutInflater mInfalater;

    public Adapter_myCollect(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mData=mDatas;
        this.mContext=mContext;
        mInfalater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=mInfalater.inflate(R.layout.item_mycollectlist,null,false);
            holder.mImageview=convertView.findViewById(R.id.image_shoppic);
            holder.mTextview_title=convertView.findViewById(R.id.text_shopskills);
            holder.mTextview_name=convertView.findViewById(R.id.textview_address);
            holder.mTextview_time=convertView.findViewById(R.id.textview_time);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mTextview_name.setText(mData.get(position));

        return convertView;
    }
    class ViewHolder {
        ImageView mImageview;
        TextView mTextview_title;
        TextView mTextview_name;
        TextView mTextview_time;

    }
}
