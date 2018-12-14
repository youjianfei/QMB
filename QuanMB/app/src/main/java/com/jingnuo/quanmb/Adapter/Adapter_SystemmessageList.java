package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.entityclass.SystemmessageBean;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.utils.SizeUtils;

import java.util.List;

public class Adapter_SystemmessageList  extends  BaseAdapter{
    List<SystemmessageBean.DataBean >  mData;
    Context mContext;
    LayoutInflater mInflater;
    int weight;
    public Adapter_SystemmessageList(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext=mContext;
        this.mData=mDatas;
        mInflater=LayoutInflater.from(mContext);
        weight= Staticdata.ScreenWidth - SizeUtils.dip2px(mContext,80);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder holder=null;
        if(convertView==null){
            holder=new viewHolder();
            convertView=mInflater.inflate(R.layout.item_text_systemmessage,null,false);
            holder.mTextview_title=convertView.findViewById(R.id.text_systemmessagetext);
            holder.text_content=convertView.findViewById(R.id.text_content);
            holder.mTextview_time=convertView.findViewById(R.id.textview_time);
            holder.imageviewPic=convertView.findViewById(R.id.imageviewPic  );
            convertView.setTag(holder);
        }else {
            holder= (viewHolder) convertView.getTag();
        }
        holder.mTextview_title.setText(mData.get(position).getTitle());
        holder.text_content.setText(mData.get(position).getContent());
        holder.mTextview_time.setText(mData.get(position).getCreateDate());

        if(mData.get(position).getImg_url().equals("")){
            holder.imageviewPic.setVisibility(View.GONE);
        }else {
            Glide.with(mContext).load(mData.get(position).getImg_url()).into(holder.imageviewPic);
            ViewGroup.LayoutParams para;
            para = holder.imageviewPic.getLayoutParams();
            para.height = (int) (weight*0.46);
            para.width = weight;
            holder.imageviewPic.setLayoutParams(para);
            holder.imageviewPic.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
    class viewHolder{
        TextView mTextview_title;
        TextView text_content;
        TextView mTextview_time;
        ImageView imageviewPic;
    }
}
