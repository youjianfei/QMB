package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

public class Adapter_mytodo extends  BaseAdapter {
    List<String>mdata;
    Context mContext;
    LayoutInflater mInflater;


    public Adapter_mytodo(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mdata=mDatas;
        this.mContext=mContext;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_mytodo,null,false);
            holder.mTextview_title=convertView.findViewById(R.id.textview_titl);
            holder.mTextview_content=convertView.findViewById(R.id.text_content);
            holder.mTextview_money=convertView.findViewById(R.id.textview_moneyy);
            holder.mTextview_state=convertView.findViewById(R.id.textview_state2);
            holder.mTextview_bargain=convertView.findViewById(R.id.text_bargain);
            holder.mTextview_complete=convertView.findViewById(R.id.text_taskscomplete);
        }

        return convertView;
    }
    class ViewHolder {
        TextView mTextview_title;
        TextView mTextview_content;
        TextView mTextview_money;
        TextView mTextview_state;
        TextView mTextview_bargain;
        TextView mTextview_complete;
    }
}
