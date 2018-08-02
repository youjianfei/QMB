package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.TuiguangbiTaocanBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.R;
import java.util.List;

public class Adapter_recharge_tuiguangbi extends BaseAdapter {
    int select;
    List<TuiguangbiTaocanBean.DataBean> mdata;
    Context mContext;
    LayoutInflater mInflater;
    public Adapter_recharge_tuiguangbi(List mDatas, Context mContext) {
        super(mDatas, mContext);
        LogUtils.LOG("ceshi","zhuangtaigaibian","适配器");
        this.mdata=mDatas;
        this.mContext=mContext;
        mInflater=LayoutInflater.from(mContext);
    }
    public void setSeclection(int select) {
        this.select = select;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mInflater.inflate(R.layout.item_tuiguangbi_recharge,null,false);
        LinearLayout mlinearlayout=convertView.findViewById(R.id.layout_back);
        TextView mtext_price=convertView.findViewById(R.id.text_tui_price);
        TextView mtext_tuiCount=convertView.findViewById(R.id.text_tui);
        TextView mtext_text_dazhe=convertView.findViewById(R.id.text_dazhe);

        if(select==position){
            mlinearlayout.setBackgroundResource(R.mipmap.tuiguang_y);
            mtext_price.setTextColor(mContext.getResources().getColor(R.color.yellow_jianbian_end));
            mtext_tuiCount.setTextColor(mContext.getResources().getColor(R.color.yellow_jianbian_end));
        }
        mtext_price.setText(mdata.get(position).getPrice()+"元");
        mtext_tuiCount.setText(mdata.get(position).getSpread_b()+"个推广币");
        mtext_text_dazhe.setText(mdata.get(position).getDiscount()+"折");


        return convertView;
    }
}
