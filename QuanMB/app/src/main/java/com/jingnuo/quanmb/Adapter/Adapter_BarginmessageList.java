package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.entityclass.BargainMessageListBean;
import com.jingnuo.quanmb.quanmb.R;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_BarginmessageList extends BaseAdapter{
    Context mContext;
    List<BargainMessageListBean.DataBean> mData;
    LayoutInflater mInflater;

    public Adapter_BarginmessageList(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mContext=mContext;
        mData=mDatas;
        mInflater=LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHoder hoder=null;
        if(convertView==null){
            hoder=new viewHoder();
            convertView=mInflater.inflate(R.layout.item_barginmessage,null,false);
            hoder.mTextview_content=convertView.findViewById(R.id.text_taskcontent);
            hoder.mTextview_type=convertView.findViewById(R.id.text_tasktype);//还价状态
            hoder.mTextview_title=convertView.findViewById(R.id.text_tasktitle);
            hoder.mTextview_time=convertView.findViewById(R.id.text_tasktime);
            hoder.mImageview_head=convertView.findViewById(R.id.image_task);
            convertView.setTag(hoder);
        }else {
            hoder= (viewHoder) convertView.getTag();
        }
        hoder.mTextview_title.setText(mData.get(position).getTitle());
        hoder.mTextview_content.setText(mData.get(position).getContent());
        hoder.mTextview_time.setText(mData.get(position).getCreateDate());
        Glide.with(mContext).load(mData.get(position).getImg_url()).into(hoder.mImageview_head);
        return convertView;
    }

    class viewHoder{
        TextView mTextview_type;
        TextView mTextview_title;
        TextView mTextview_content;
        TextView mTextview_time;
        CircleImageView mImageview_head;
    }


}
