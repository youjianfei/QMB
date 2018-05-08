package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.MySkillBean;
import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

public class Adapter_mystill  extends  BaseAdapter{
    List<MySkillBean.DataBean.ListBean> mData;
    Context mContext;
    LayoutInflater mInfalter;
    public Adapter_mystill(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext=mContext;
        this.mData=mDatas;
        mInfalter=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder=null;
        if(convertView==null){
            holder=new Viewholder();
            convertView=mInfalter.inflate(R.layout.item_mystill,null,false);
            holder.mTextview_title=convertView.findViewById(R.id.textview_titl);
            holder.mTextview_cerattime=convertView.findViewById(R.id.textview_issuename);
            holder.mTextview_content=convertView.findViewById(R.id.text_content);
            holder.mTextview_cancle=convertView.findViewById(R.id.text_cancle);
            convertView.setTag(holder);
        }else {
            holder= (Viewholder) convertView.getTag();
        }
        holder.mTextview_title.setText(mData.get(position).getTitle());
        holder.mTextview_cerattime.setText("发布时间："+mData.get(position).getRelease_date());
        holder.mTextview_content.setText(mData.get(position).getDescription());

        return convertView;
    }
    class Viewholder{
        TextView mTextview_title;
        TextView mTextview_cerattime;
        TextView mTextview_content;
        TextView mTextview_cancle;
    }

}
