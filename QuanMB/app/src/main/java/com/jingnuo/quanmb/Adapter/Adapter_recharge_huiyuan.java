package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.HuiyuanTaocanBean;
import com.jingnuo.quanmb.R;
import java.util.List;

public class Adapter_recharge_huiyuan extends BaseAdapter {
    int select;
    List<HuiyuanTaocanBean.DataBean> mdata;
    Context mContext;
    LayoutInflater mInflater;
    public Adapter_recharge_huiyuan(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mdata=mDatas;
        this.mContext=mContext;
        mInflater=LayoutInflater.from(mContext);
    }
    public void setSeclection(int select) {
        this.select = select;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mInflater.inflate(R.layout.item_huiyuan_recharge,null,false);
        LinearLayout mlinearlayout=convertView.findViewById(R.id.layout_back);
        TextView mTextview_price=convertView.findViewById(R.id.text_hui_price);
        TextView mTextview_huiyuan=convertView.findViewById(R.id.text_huiyuan);
        TextView mTextview_tui=convertView.findViewById(R.id.text_tui);
        if(select==position){
            mlinearlayout.setBackgroundResource(R.mipmap.tuiguang_y);
            mTextview_price.setTextColor(mContext.getResources().getColor(R.color.yellow_jianbian_end));
            mTextview_huiyuan.setTextColor(mContext.getResources().getColor(R.color.yellow_jianbian_end));
            mTextview_tui.setTextColor(mContext.getResources().getColor(R.color.yellow_jianbian_end));
        }
        mTextview_price.setText("￥"+mdata.get(position).getPrice());
        mTextview_huiyuan.setText(mdata.get(position).getPackage_name());
        mTextview_tui.setText("送"+mdata.get(position).getSpread_b()+"个推广币");

        return convertView;
    }
}
