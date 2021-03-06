package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.entityclass.WeixiuJiazhengBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Adapter_WeixiuJiazheng extends BaseAdapter {
    List<WeixiuJiazhengBean.ListBean> mData;
    int [] images ;
    int [] images_select ;
    Context mContext;
    LayoutInflater mInflater;
    private int selectedPosition = 0;// 选中的位置
    public   boolean isShowAll;
    public Adapter_WeixiuJiazheng(List mDatas, Context mContext,int [] images,int [] images_select) {
        super(mDatas, mContext);
        this.mData = mDatas;
        this.mContext = mContext;
        this.images=images;
        this.images_select=images_select;
        mInflater = LayoutInflater.from(mContext);
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
    public void isShowAll(boolean isShowAll) {
        this.  isShowAll=isShowAll;
    }
    @Override
    public int getCount() {
        if(mDatas!=null){
            return isShowAll?mDatas.size():5;
        }else {
            return 0;
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder = null;

        if (convertView == null) {
            holder = new viewholder();
            convertView = mInflater.inflate(R.layout.item_gridview_weixiujiezheng, null, false);
            holder.mTextview = convertView.findViewById(R.id.textview_grid_right);
            holder.mImageview_icon=convertView.findViewById(R.id.image_view);
            convertView.setTag(holder);
        } else {
            holder = (viewholder) convertView.getTag();
        }
        holder.mTextview.setText(mData.get(position).getSpecialty_name());
        if(selectedPosition==position){
            holder.mImageview_icon.setBackgroundResource(images_select[position]);
            holder.mTextview.setTextColor(mContext.getResources().getColor(R.color.yellow_jianbian_start));

        }else {
            holder.mImageview_icon.setBackgroundResource(images[position]);
            holder.mTextview.setTextColor(mContext.getResources().getColor(R.color.black_text));

        }



        return convertView;
    }

    class viewholder {
        TextView mTextview;
        ImageView mImageview_icon;
    }
}
