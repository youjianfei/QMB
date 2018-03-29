package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class Adapter_SquareList extends  BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater mInflater;

    public Adapter_SquareList(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mData=mDatas;
        this.mContext=mContext;
        mInflater=LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_square_list,null,false);
            holder.mText_task=convertView.findViewById(R.id.text_square_task);
            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mText_task.setText(mData.get(position));


        return convertView;
    }
    class  ViewHolder {
        TextView mText_task;
    }
}
