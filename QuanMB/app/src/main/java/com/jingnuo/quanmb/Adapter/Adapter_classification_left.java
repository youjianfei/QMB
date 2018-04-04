package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

import java.util.List;


/**
 * Created by Administrator on 2017/6/9.
 */

public class Adapter_classification_left extends BaseAdapter {
    List<String> mData;
    Context mContext;
    LayoutInflater mInflater;
    private int selectedPosition = 0;// 选中的位置

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public Adapter_classification_left(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mData = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_listview_classification_left, null, false);
            holder.mTextview_name =  convertView.findViewById(R.id.taxtview_name);
            holder.mImage_leftLine =  convertView.findViewById(R.id.imageview_leftline);
            holder.mLinearlayout =  convertView.findViewById(R.id.linearlayout_left);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextview_name.setText(mData.get(position));

        if (selectedPosition == position) {//判断是否是点击的 item
            holder.mLinearlayout.setSelected(true);
            holder.mTextview_name.setTextColor(mContext.getResources().getColor(R.color.yellow_jianbian_end));
            holder.mImage_leftLine.setVisibility(View.VISIBLE);
        } else {
            holder.mLinearlayout.setSelected(false);
            holder.mTextview_name.setTextColor(mContext.getResources().getColor(R.color.black_text2));
            holder.mImage_leftLine.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {

        TextView mTextview_name;
        ImageView mImage_leftLine;
        LinearLayout mLinearlayout;

    }
}
