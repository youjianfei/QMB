package com.jingnuo.quanmb.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.SheQuListBean;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.utils.SizeUtils;

import java.util.List;

public class Adapter_FulisheList extends BaseAdapter {
    List<GuanggaoBean.DataBean> mData;
    Activity mContext;
    LayoutInflater mInfalter;

    public Adapter_FulisheList(List mDatas, Activity mContext) {
        super(mDatas, mContext);
        this.mContext = mContext;
        this.mData = mDatas;
        mInfalter = LayoutInflater.from(mContext);
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {

        return 2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder_xinxianshi viewholder_xinxianshi ;
        Viewholder_xianshimiaosha viewholder_xianshimiaosha ;
        Viewholder_zhoubian viewholder_zhoubian ;

        int type = getItemViewType(position);

        if(type==0){//新鲜事
            if (convertView == null) {
                viewholder_xinxianshi = new Viewholder_xinxianshi();
                convertView = mInfalter.inflate(R.layout.item_xinxianshi, null, false);
                viewholder_xinxianshi.mImage = convertView.findViewById(R.id.image_fuli);
                convertView.setTag(viewholder_xinxianshi);
            } else {
                viewholder_xinxianshi = (Viewholder_xinxianshi) convertView.getTag();
            }
            if(mData.get(position).getImg_url().equals("")){
                viewholder_xinxianshi.mImage.setBackgroundResource(R.mipmap.xinxianshi);
            }
//        Glide.with(mContext).load(mData.get(position).getImg_url()).into(holder.mImage);
            ViewGroup.LayoutParams para;
            para = viewholder_xinxianshi.mImage.getLayoutParams();
            para.width = Staticdata.ScreenWidth- SizeUtils.dip2px(mContext,20);
            para.height = (int) ( para.width*0.43);
            viewholder_xinxianshi.mImage.setLayoutParams(para);
        }
        if(type==1){//限时秒杀
            if (convertView == null) {
                viewholder_xianshimiaosha = new Viewholder_xianshimiaosha();
                convertView = mInfalter.inflate(R.layout.item_xianshimiaosha, null, false);
                viewholder_xianshimiaosha.mImage = convertView.findViewById(R.id.image_pic);
                viewholder_xianshimiaosha.textview_time = convertView.findViewById(R.id.textview_time);
                convertView.setTag(viewholder_xianshimiaosha);
            } else {
                viewholder_xianshimiaosha = (Viewholder_xianshimiaosha) convertView.getTag();
            }
            if(mData.get(position).getImg_url().equals("")){
                viewholder_xianshimiaosha.mImage.setBackgroundResource(R.mipmap.xinxianshi);
            }
//        Glide.with(mContext).load(mData.get(position).getImg_url()).into(holder.mImage);
            ViewGroup.LayoutParams para;
            para = viewholder_xianshimiaosha.mImage.getLayoutParams();
            para.width = Staticdata.ScreenWidth- SizeUtils.dip2px(mContext,20);
            para.height = (int) ( para.width*0.43);
            viewholder_xianshimiaosha.mImage.setLayoutParams(para);
        }
        if(type==2){//周边
            if (convertView == null) {
                viewholder_zhoubian = new Viewholder_zhoubian();
                convertView = mInfalter.inflate(R.layout.item_zhoubian, null, false);
                viewholder_zhoubian.mImage = convertView.findViewById(R.id.image_pic);
                viewholder_zhoubian.textview_title = convertView.findViewById(R.id.textview_title);
                viewholder_zhoubian.textview_price = convertView.findViewById(R.id.textview_price);
                convertView.setTag(viewholder_zhoubian);
            } else {
                viewholder_zhoubian = (Viewholder_zhoubian) convertView.getTag();
            }
            if(mData.get(position).getImg_url().equals("")){
                viewholder_zhoubian.mImage.setBackgroundResource(R.mipmap.xinxianshi);
            }
//        Glide.with(mContext).load(mData.get(position).getImg_url()).into(holder.mImage);
            ViewGroup.LayoutParams para;
            para = viewholder_zhoubian.mImage.getLayoutParams();
            para.width = -1;
            para.height = Staticdata.ScreenWidth- SizeUtils.dip2px(mContext,30);
            para.height =  para.height/2;
            viewholder_zhoubian.mImage.setLayoutParams(para);
        }

        return convertView;
    }
    class Viewholder_xinxianshi {
        ImageView mImage;
    }
    class Viewholder_xianshimiaosha {
        ImageView mImage;
        TextView textview_time;
    }
    class Viewholder_zhoubian {
        ImageView mImage;
        TextView textview_title;
        TextView textview_price;
    }
}
