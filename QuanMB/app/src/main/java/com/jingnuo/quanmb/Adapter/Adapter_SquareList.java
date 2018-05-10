package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.class_.Task_type;
import com.jingnuo.quanmb.entityclass.Square_defaultBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class Adapter_SquareList extends  BaseAdapter {
    private Context mContext;
    private List<Square_defaultBean.DataBean.ListBean> mData;
    private LayoutInflater mInflater;

    public Adapter_SquareList(List<Square_defaultBean.DataBean.ListBean> mDatas, Context mContext) {
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
            holder.mText_task_des=convertView.findViewById(R.id.text_square_task);
            holder.mText_task_type=convertView.findViewById(R.id.text_square_type);
            holder.mText_task_creattime=convertView.findViewById(R.id.text_square_time);
            holder.mText_task_username=convertView.findViewById(R.id.text_square_personname);
            holder.mText_task_address=convertView.findViewById(R.id.text_square_address);
            holder.mText_task_price=convertView.findViewById(R.id.text_square_price);
            holder.mTextview_yuan=convertView.findViewById(R.id.textyuan);
            holder.mImage_view=convertView.findViewById(R.id.image_square_person);
            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.mText_task_des.setText(mData.get(position).getTask_description()+"");
        holder.mText_task_creattime.setText(mData.get(position).getCreateDate()+"");
        holder.mText_task_username.setText(mData.get(position).getTask_Name()+"");
        holder.mText_task_address.setText(mData.get(position).getRelease_Address()+"");
        if(mData.get(position).getCommission()==0){
            holder.mTextview_yuan.setText("帮手出价");
            holder.mText_task_price.setVisibility(View.INVISIBLE);
        }else {
            holder.mText_task_price.setText(mData.get(position).getCommission()+"");
            holder.mTextview_yuan.setText("元");
            holder.mText_task_price.setVisibility(View.VISIBLE);
        }
        holder.mText_task_type.setText(mData.get(position).getSpecialty_name()+"");
        Glide.with(mContext).load(mData.get(position).getHeadUrl()).into(holder.mImage_view);

        return convertView;
    }
    class  ViewHolder {
        TextView mText_task_des;//任务描述
        TextView mText_task_type ;//任务类型
        TextView mText_task_creattime ;//任务创建时间
        TextView mText_task_username ;//任务的标题
        TextView mText_task_address ;//发布任务的地址
        TextView mText_task_price ;//任务的佣金
        TextView mTextview_yuan;
        ImageView mImage_view;//头像
    }
}
