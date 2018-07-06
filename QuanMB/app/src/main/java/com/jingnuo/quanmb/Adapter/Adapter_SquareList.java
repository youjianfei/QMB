package com.jingnuo.quanmb.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_square_sort;
import com.jingnuo.quanmb.class_.Task_type;
import com.jingnuo.quanmb.entityclass.Square_defaultBean;
import com.jingnuo.quanmb.popwinow.Popwindow_SquareSort;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class Adapter_SquareList extends  BaseAdapter {
    private Activity mContext;
    private List<Square_defaultBean.DataBean.ListBean> mData;
    private LayoutInflater mInflater;
    Popwindow_SquareSort mPopwindow_square_sort;
    public Adapter_SquareList(List<Square_defaultBean.DataBean.ListBean> mDatas, Activity mContext) {
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
            holder.mText_task_username=convertView.findViewById(R.id.text_taskpeoplename);
            holder.mText_task_address=convertView.findViewById(R.id.text_square_address);
            holder.mText_task_price=convertView.findViewById(R.id.text_square_price);
            holder.mTextview_distance=convertView.findViewById(R.id.text_taskdistance);
            holder.mImage_view=convertView.findViewById(R.id.image_square_person);




            holder.relative_shaixuan=convertView.findViewById(R.id.relative_shaixuan);

            convertView.setTag(holder);

        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        if(position ==0){
            holder.relative_shaixuan.setVisibility(View.VISIBLE);
        }else {
            holder.relative_shaixuan.setVisibility(View.GONE);
        }
        final ViewHolder finalHolder = holder;
        holder.relative_shaixuan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                mPopwindow_square_sort = new Popwindow_SquareSort(mContext, new InterfacePopwindow_square_sort() {
                    @Override
                    public void onSuccesses(String address, String id) {
//                        page=1;
//                        LogUtils.LOG("ceshi",address+id,"排序方式");
//                        initMap(MinCommission+"",MaxCommission+"",page+"","","",id+"");
//                        request_square(map_filter_sort,page);

                    }
                }, finalHolder.relative_shaixuan, 1);
                mPopwindow_square_sort.showPopwindow();

            }
        });

        holder.mText_task_des.setText(mData.get(position).getTask_Name()+"");

        long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
        long ago = Long.parseLong(Utils.getTime(mData.get(position).getCreateDate()));//任务发布时间
        String time = Utils.getDistanceTime2(ago, now);//算出的差值
        holder.mText_task_creattime.setText(time);
//        holder.mText_task_creattime.setText(mData.get(position).getCreateDate()+"");
        holder.mTextview_distance.setText("距你 "+(double)mData.get(position).getDistance()/1000+"km");
        holder.mText_task_username.setText(mData.get(position).getNick_name()+"");
        holder.mText_task_address.setText(mData.get(position).getRelease_Address()+"");
        if(mData.get(position).getIs_helper_bid().equals("Y")){
            holder.mText_task_price.setText("帮手出价");
        }else {
            holder.mText_task_price.setText(mData.get(position).getCommission()+"元");
        }
        holder.mText_task_type.setText(mData.get(position).getSpecialty_name()+"");
        Glide.with(mContext).load(mData.get(position).getHeadUrl()).into(holder.mImage_view);

        return convertView;
    }
    class  ViewHolder {
        TextView mText_task_des;//任务描述
        TextView mText_task_type ;//任务类型
        TextView mText_task_creattime ;//任务创建时间
        TextView mText_task_username ;//任务的发帖人
        TextView mText_task_address ;//发布任务的地址
        TextView mText_task_price ;//任务的佣金
        TextView mTextview_distance;
        ImageView mImage_view;//头像


        RelativeLayout relative_shaixuan;
    }
}
