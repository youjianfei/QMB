package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;

import java.util.List;

public class Adapter_Gridviewpic_UPLoad extends BaseAdapter{
    private Context context;
    private List<Bitmap> shareImgs;
    private LayoutInflater inflater;
    int weight;
    public Adapter_Gridviewpic_UPLoad(List<Bitmap> shareImgs, Context mContext) {
        super(shareImgs, mContext);
        this.shareImgs = shareImgs;
        this.context = mContext;
        this.inflater = LayoutInflater.from(context);
        //           /*  获得 屏幕宽  高 的  方法1*/
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        weight = (wm.getDefaultDisplay().getWidth() - SizeUtils.dip2px(mContext,80)) / 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_lookic_gridview, parent, false);
            holder.mImageView =  convertView.findViewById(R.id.share_picture);
            holder.mImageView_cancel =  convertView.findViewById(R.id.image_cancel);
            holder.mRelativeLayout =  convertView.findViewById(R.id.REL_shareimg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImageView.setImageBitmap(shareImgs.get(position));
        holder.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ViewGroup.LayoutParams para;
        para = holder.mImageView.getLayoutParams();
        para.height = weight;
        para.width = weight;
        holder.mImageView.setLayoutParams(para);
        if(position==mDatas.size()-1){
            LogUtils.LOG("ceshi","weizhi+"+position,"适配器");
            holder.mImageView_cancel.setVisibility(View.GONE);
        }else {
            holder.mImageView_cancel.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        if(mDatas.size()>3){
            return 3;
        }else {
            return super.getCount();
        }

    }

    class ViewHolder {
        ImageView mImageView;
        ImageView mImageView_cancel;
        RelativeLayout mRelativeLayout;

    }
}
