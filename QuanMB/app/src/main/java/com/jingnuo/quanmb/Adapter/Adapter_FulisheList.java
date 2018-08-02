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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder holder = null;
        if (convertView == null) {
            holder = new Viewholder();
            convertView = mInfalter.inflate(R.layout.item_fulishe, null, false);
            holder.mImage = convertView.findViewById(R.id.image_fuli);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        Glide.with(mContext).load(mData.get(position).getImg_url()).into(holder.mImage);
        ViewGroup.LayoutParams para;
        para = holder.mImage.getLayoutParams();
        para.height = (int) (Staticdata.ScreenWidth*0.5);
        para.width = Staticdata.ScreenWidth;
        holder.mImage.setLayoutParams(para);

        return convertView;
    }
    class Viewholder {
        ImageView mImage;
    }

}
