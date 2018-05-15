package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.SizeUtils;

import java.util.List;

public class Adapter_Gridviewpic extends BaseAdapter{
    private Context context;
    private List<String> shareImgs;
    private LayoutInflater inflater;
    int weight;
    public Adapter_Gridviewpic(List<String> shareImgs, Context mContext) {
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
            holder.mImageView = (ImageView) convertView.findViewById(R.id.share_picture);
            holder.mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.REL_shareimg);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(shareImgs.get(position)).into(holder.mImageView);
        ViewGroup.LayoutParams para;
        para = holder.mImageView.getLayoutParams();
        para.height = weight;
        para.width = weight;
        holder.mImageView.setLayoutParams(para);

        return convertView;
    }

    class ViewHolder {
        ImageView mImageView;
        RelativeLayout mRelativeLayout;
    }
}
