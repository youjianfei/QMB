package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Adapter_classification_right extends BaseAdapter {
    List<Skillmenu_twoBean.DataBean.ListBean> mData;
    Context mContext;
    LayoutInflater mInflater;

    public Adapter_classification_right(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mData = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder = null;

        if (convertView == null) {
            holder = new viewholder();
            convertView = mInflater.inflate(R.layout.item_gridview_classification_right, null, false);
            holder.mTextview = convertView.findViewById(R.id.textview_grid_right);
            convertView.setTag(holder);
        } else {
            holder = (viewholder) convertView.getTag();
        }
        holder.mTextview.setText(mData.get(position).getSpecialty_name());


        return convertView;
    }

    class viewholder {
        TextView mTextview;

    }
}
