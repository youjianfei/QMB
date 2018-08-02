package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.SystemmessageBean;
import com.jingnuo.quanmb.R;
import java.util.List;

public class Adapter_SystemmessageList  extends  BaseAdapter{
    List<SystemmessageBean.DataBean >  mData;
    Context mContext;
    LayoutInflater mInflater;

    public Adapter_SystemmessageList(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext=mContext;
        this.mData=mDatas;
        mInflater=LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder holder=null;
        if(convertView==null){
            holder=new viewHolder();
            convertView=mInflater.inflate(R.layout.item_text_systemmessage,null,false);
            holder.mTextview_content=convertView.findViewById(R.id.text_systemmessagetext);
            holder.mTextview_time=convertView.findViewById(R.id.textview_time);
            convertView.setTag(holder);
        }else {
            holder= (viewHolder) convertView.getTag();
        }
        holder.mTextview_content.setText(mData.get(position).getTitle());
        holder.mTextview_time.setText(mData.get(position).getCreateDate());


        return convertView;
    }
    class viewHolder{
        TextView mTextview_content;
        TextView mTextview_time;
    }
}
