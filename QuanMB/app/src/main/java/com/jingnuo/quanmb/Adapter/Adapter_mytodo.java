package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.MyTodoBean;
import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

public class Adapter_mytodo extends  BaseAdapter {
    List<MyTodoBean.DataBean.ListBean>mdata;
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
//            holder.mTextview_bargain=convertView.findViewById(R.id.text_bargain);
            holder.mTextview_complete=convertView.findViewById(R.id.text_taskscomplete);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mTextview_title.setText(mdata.get(position).getTask_name());
        holder.mTextview_content.setText(mdata.get(position).getTask_description());
        holder.mTextview_money.setText(mdata.get(position).getOrder_amount()+"元");
        holder.mTextview_state.setText(mdata.get(position).getOrder_status());


        return convertView;
    }
    class ViewHolder {
        TextView mTextview_title;
        TextView mTextview_content;
        TextView mTextview_money;
        TextView mTextview_state;
//        TextView mTextview_bargain;
        TextView mTextview_complete;
    }
}
