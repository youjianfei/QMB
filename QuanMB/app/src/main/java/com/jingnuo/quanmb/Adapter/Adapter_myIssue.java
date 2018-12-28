package com.jingnuo.quanmb.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.CancelloederActivity;
import com.jingnuo.quanmb.activity.MatchShopActivity;
import com.jingnuo.quanmb.activity.MytaskDetailActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.MyorderBean;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter_myIssue extends BaseAdapter {
    List<MyorderBean.DataBean> mData;
    Context mContext;
    LayoutInflater mInlayout;

    public Adapter_myIssue(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext = mContext;
        this.mData = mDatas;
        mInlayout = LayoutInflater.from(mContext);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolde viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolde();
            convertView = mInlayout.inflate(R.layout.item_myissue_list, null, false);
            viewHolder.mTextview_type = convertView.findViewById(R.id.text_type);
            viewHolder.mTextview_title = convertView.findViewById(R.id.textview_titl);
            viewHolder.mTextview_issuetime = convertView.findViewById(R.id.textview_issuename);
            viewHolder.mTextview_taskstate = convertView.findViewById(R.id.text_taskstate);
            viewHolder.mTextview_cancel=convertView.findViewById(R.id.text_taskcancle);
            viewHolder.image_typePIC=convertView.findViewById(R.id.image_typePIC);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolde) convertView.getTag();
        }
        Glide.with(mContext).load(mData.get(position).getTask_type_img_url()).into(viewHolder.image_typePIC);
        viewHolder.mTextview_type.setText(mData.get(position).getSpecialty_name());
        viewHolder.mTextview_title.setText(mData.get(position).getTask_description());
        viewHolder.mTextview_issuetime.setText("发布时间：" + mData.get(position).getTask_StartDate());
        viewHolder.mTextview_taskstate.setText(mData.get(position).getStatus_name());

        if(mData.get(position).getStatus_name().equals("待帮助")){

            viewHolder.mTextview_taskstate.setBackgroundResource(R.drawable.text_green2);

        }else if(mData.get(position).getStatus_name().equals("已完成")||mData.get(position).getStatus_name().equals("已失效")
                ||mData.get(position).getStatus_name().equals("取消任务")){
            viewHolder.mTextview_taskstate.setBackgroundResource(R.drawable.text_gray2);
        }else {
            viewHolder.mTextview_taskstate.setBackgroundResource(R.drawable.text_red);
        }
        if(mData.get(position).getTask_Status_code().equals("01")||mData.get(position).getTask_Status_code().equals("02")||
        mData.get(position).getTask_Status_code().equals("08")){
            viewHolder.mTextview_cancel.setVisibility(View.VISIBLE);
        }else {
            viewHolder.mTextview_cancel.setVisibility(View.INVISIBLE);
        }
        viewHolder.mTextview_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Popwindow_Tip("是否取消任务?", (Activity) mContext, new Interence_complteTask() {
                    @Override
                    public void onResult(boolean result) {
                        if (result){

                            Intent intent1=new Intent(mContext,CancelloederActivity.class);
                            intent1.putExtra("taskid",mData.get(position).getTask_id()+"");
                            mContext.startActivity(intent1);
                        }

                    }
                }).showPopwindow();


            }
        });

        return convertView;
    }

    class viewHolde {
        ImageView image_typePIC;
        TextView mTextview_type;
        TextView mTextview_title;
        TextView mTextview_issuetime;
//        TextView mTextview_money;
        TextView mTextview_taskstate;
//        TextView mTextview_resttime;
        TextView mTextview_cancel;
//        ImageView mImage_resttime;
//        RelativeLayout resttime;
    }
}
