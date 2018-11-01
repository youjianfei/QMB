package com.jingnuo.quanmb.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.R;

import java.util.List;

/**
 * Created by 飞 on 2018/11/1.
 */

public class Adapter_Coupon extends BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater mInflater;

    public Adapter_Coupon(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext = mContext;
        this.mData = mDatas;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;
        if(viewHolder==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_coupon,null,false);
            viewHolder.text_money=convertView.findViewById(R.id.text_money);
            viewHolder.textview_moneytext=convertView.findViewById(R.id.textview_moneytext);
            viewHolder.textview_coupontype=convertView.findViewById(R.id.textview_coupontype);
            viewHolder.textview_time=convertView.findViewById(R.id.textview_time);
            viewHolder.textview_overtime=convertView.findViewById(R.id.textview_overtime);
            viewHolder.textview_usenow=convertView.findViewById(R.id.textview_usenow);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.text_money.setText(mData.get(position));

        return convertView;
    }

    class ViewHolder {
        TextView text_money;//优惠券金额
        TextView textview_moneytext;//优惠券条件
        TextView textview_coupontype;//优惠券类型
        TextView textview_time;//优惠券使用时间
        TextView textview_overtime;//优惠券剩余时间
        TextView textview_usenow;//立即使用

    }
}
