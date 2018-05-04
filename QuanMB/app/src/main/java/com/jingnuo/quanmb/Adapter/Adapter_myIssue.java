package com.jingnuo.quanmb.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.MyorderBean;
import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

public class Adapter_myIssue extends BaseAdapter {
    List<MyorderBean.DateBean> mData;
    Context mContext;
    LayoutInflater mInlayout;

    public Adapter_myIssue(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext = mContext;
        this.mData = mDatas;
        mInlayout = LayoutInflater.from(mContext);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolde viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolde();
            convertView = mInlayout.inflate(R.layout.item_myissue_list, null, false);
            viewHolder.mTextview_title = convertView.findViewById(R.id.textview_titl);
            viewHolder.mTextview_issuetime = convertView.findViewById(R.id.textview_issuename);
            viewHolder.mTextview_money = convertView.findViewById(R.id.textview_moneyy);
            viewHolder.mTextview_content = convertView.findViewById(R.id.text_content);
            viewHolder.mTextview_taskstate = convertView.findViewById(R.id.text_taskstate);
//            viewHolder.mTextview_startime = convertView.findViewById(R.id.text_resttime);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolde) convertView.getTag();
        }
        viewHolder.mTextview_title.setText(mData.get(position).getTask_name());
        viewHolder.mTextview_issuetime.setText("发布时间：" + mData.get(position).getTask_StartDate());
        viewHolder.mTextview_money.setText("佣金：" + mData.get(position).getCommission() + "元");
        viewHolder.mTextview_content.setText(mData.get(position).getTask_description());
        viewHolder.mTextview_taskstate.setText(mData.get(position).getStatus_name());
//        viewHolder.mTextview_startime.setText("预约时间：" + mData.get(position).getTask_EndDate());

        return convertView;
    }

    class viewHolde {
        TextView mTextview_title;
        TextView mTextview_issuetime;
        TextView mTextview_money;
        TextView mTextview_content;
        TextView mTextview_taskstate;
//        TextView mTextview_startime;
    }
}
