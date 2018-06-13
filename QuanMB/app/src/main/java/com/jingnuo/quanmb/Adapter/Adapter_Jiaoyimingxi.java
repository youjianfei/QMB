package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.JiaoyiMingxi;
import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

public class Adapter_Jiaoyimingxi extends BaseAdapter{
    List<JiaoyiMingxi.DataBean> mdata;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public Adapter_Jiaoyimingxi(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext=mContext;
        this.mdata=mDatas;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.item_jiaoyimingxi,null,false);
            holder.mTextview_type=convertView.findViewById(R.id.text_type);
            holder.mTextview_time=convertView.findViewById(R.id.text_time);
            holder.mTextview_money=convertView.findViewById(R.id.text_money);
            holder.mTextview_state=convertView.findViewById(R.id.text_state);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.mTextview_money.setText(mdata.get(position).getOrder_amoun()+"");
        holder.mTextview_time.setText(mdata.get(position).getCreateDate()+"");
        holder.mTextview_state.setText("支付成功");
        holder.mTextview_type.setText(mdata.get(position).getBill_title());

        return convertView;
    }
    class ViewHolder{
        TextView mTextview_type;
        TextView mTextview_time;
        TextView mTextview_money;
        TextView mTextview_state;
    }
}
