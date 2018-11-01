package com.jingnuo.quanmb.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.CouponBean;
import com.jingnuo.quanmb.utils.Utils;

import java.util.List;

/**
 * Created by 飞 on 2018/11/1.
 */

public class Adapter_Coupon extends BaseAdapter {
    private Context mContext;
    private List<CouponBean.DataBean> mData;
    private LayoutInflater mInflater;
    private  String type="";
    int selectedPosition=0;

    public Adapter_Coupon(List mDatas, Context mContext,String type) {
        super(mDatas, mContext);
        this.mContext = mContext;
        this.mData = mDatas;
        this.type=type;
        mInflater = LayoutInflater.from(mContext);

    }
    //在选择优惠券界面使用position
    public void setSelectedPosition(int position) {
        selectedPosition = position;
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
            viewHolder.image_selectcoupon=convertView.findViewById(R.id.image_selectcoupon);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.text_money.setText(mData.get(position).getAmount()+"");
        viewHolder.textview_moneytext.setText("满"+mData.get(position).getCondition()+"元可用");
        viewHolder.textview_coupontype.setText(mData.get(position).getDescriptionA());
        String starattime=mData.get(position).getStartdate().substring(0,10);
        String endtime=mData.get(position).getEnddate().substring(0,10);
        viewHolder.textview_time.setText(starattime+"~"+endtime);

        long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
        long ago = Long.parseLong(Utils.getTime(mData.get(position).getEnddate()));//任务发布时间
        String time = Utils.getDistanceTime2(ago, now);//算出的差值
         viewHolder.textview_overtime.setText("将于"+time+"过期");
         if(type.equals("优惠券")){
             viewHolder.textview_usenow.setVisibility(View.VISIBLE);
             viewHolder.image_selectcoupon.setVisibility(View.INVISIBLE);
         }else {//选择优惠券   通过position来判断选择哪一个
             viewHolder.textview_usenow.setVisibility(View.INVISIBLE);
             if(selectedPosition==position){
             viewHolder.image_selectcoupon.setVisibility(View.VISIBLE);}
         }


        return convertView;
    }

    class ViewHolder {
        TextView text_money;//优惠券金额
        TextView textview_moneytext;//优惠券条件
        TextView textview_coupontype;//优惠券类型
        TextView textview_time;//优惠券使用时间
        TextView textview_overtime;//优惠券剩余时间
        TextView textview_usenow;//立即使用
        ImageView image_selectcoupon;//选择

    }
}
