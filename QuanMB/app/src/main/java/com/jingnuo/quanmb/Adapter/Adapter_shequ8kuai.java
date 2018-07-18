package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

public class Adapter_shequ8kuai extends BaseAdapter {
    Context mContext;
    List<String> mdatas;
    LayoutInflater mInflater;

    public Adapter_shequ8kuai(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext=mContext;
        this.mdatas=mDatas;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mInflater.inflate(R.layout.item_gridview_shequ,null,false);
        TextView mTextview=convertView.findViewById(R.id.textview_grid_right);
        ImageView mImage=convertView.findViewById(R.id.image_view);
        switch (mdatas.get(position)){
            case "1":
                mTextview.setText("门禁钥匙");
                mImage.setImageResource(R.mipmap.menjinyaoshi);
                break;
            case "2":
                mTextview.setText("宠物护理");
                mImage.setImageResource(R.mipmap.chongwuhuli);
                break;
            case "3":
                mTextview.setText("物业缴费");
                mImage.setImageResource(R.mipmap.wuyejiaofei);
                break;
            case "4":
                mTextview.setText("家政服务");
                mImage.setImageResource(R.mipmap.jiazhengfuwu);
                break;
            case "5":
                mTextview.setText("搬家运输");
                mImage.setImageResource(R.mipmap.banjiayunshu);
                break;
            case "6":
                mTextview.setText("家电维修");
                mImage.setImageResource(R.mipmap.jiadianweixiu);
                break;
            case "7":
                mTextview.setText("二手市场");
                mImage.setImageResource(R.mipmap.ershoushichang);
                break;
            case "8":
                mTextview.setText("福利社");
                mImage.setImageResource(R.mipmap.fulishe);
                break;
        }
        return convertView;

    }
}
