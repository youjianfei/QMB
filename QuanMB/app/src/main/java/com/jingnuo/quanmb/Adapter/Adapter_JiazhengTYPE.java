package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.JiaZhengTypeBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Adapter_JiazhengTYPE extends BaseAdapter {
    List<JiaZhengTypeBean.TypeBean> mData;
    Context mContext;
    LayoutInflater mInflater;
    int height;
    public Adapter_JiazhengTYPE(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mData = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        height= (wm.getDefaultDisplay().getWidth() - SizeUtils.dip2px(mContext,100)) / 2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         convertView = mInflater.inflate(R.layout.item_gridview_jiazhengtype, null, false);
        TextView textview_price = convertView.findViewById(R.id.textview_price);//参考价格
        TextView textview_type = convertView.findViewById(R.id.textview_type);//类型
        ImageView mImageview_icon=convertView.findViewById(R.id.image_view);//图片
        ImageView image_selec=convertView.findViewById(R.id.image_select);//是否选择
        LinearLayout linearlayout_background=convertView.findViewById(R.id.linearlayout_background);//背景

        textview_price.setText(mData.get(position).getPrice());
        textview_type.setText(mData.get(position).getXiangmu());
        mImageview_icon.setBackgroundResource(mData.get(position).getImage());
        if(mData.get(position).isIsselect()){
            image_selec.setSelected(true);
        }else {
            image_selec.setSelected(false);
        }
        LogUtils.LOG("ceshi",mData.get(position).getXiangmu(),"gridview");
        ViewGroup.LayoutParams para2;
        para2 = linearlayout_background.getLayoutParams();
        para2.width = MATCH_PARENT;
        para2.height = height+SizeUtils.dip2px(mContext,120);
        linearlayout_background.setLayoutParams(para2);

        ViewGroup.LayoutParams para;
        para = mImageview_icon.getLayoutParams();
        para.height = height;
        para.width = height;
        mImageview_icon.setLayoutParams(para);




        return convertView;
    }

}
