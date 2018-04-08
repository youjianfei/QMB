package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.activity.BaseActivityother;
import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class Adapter_shophall extends BaseAdapter {
    List<String> mData;
    Context mContext;
    LayoutInflater mInflater;

    public Adapter_shophall(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mData = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = null;
        if (viewholder == null) {
            viewholder=new Viewholder();
            convertView = mInflater.inflate(R.layout.item_shophalllist, null, false);
            viewholder.mImageview_shoppic = convertView.findViewById(R.id.image_shoppic);
            viewholder.mTextview_skillstitle = convertView.findViewById(R.id.text_shopskills);
            viewholder.mTextview_address = convertView.findViewById(R.id.textview_address);
            viewholder.mTextview_vip = convertView.findViewById(R.id.textview_vip);
            viewholder.mImage_call = convertView.findViewById(R.id.image_call);
            convertView.setTag(viewholder);
        }else {
            viewholder= (Viewholder) convertView.getTag();
        }
        viewholder.mTextview_skillstitle.setText(mData.get(position));

        return convertView;
    }

    class Viewholder {
        ImageView mImageview_shoppic;
        TextView mTextview_skillstitle;
        TextView mTextview_address;
        TextView mTextview_vip;
        ImageView mImage_call;
    }
}
