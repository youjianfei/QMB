package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
            convertView = inflater.inflate(R.layout.item_lookic_gridview_nocha, parent, false);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.share_picture);
            holder.mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.REL_shareimg);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (shareImgs.get(position).equals("add")){

            holder.mImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.addpic));
        }else if(shareImgs.get(position).contains("http")){
            Glide.with(context).load(shareImgs.get(position)).into(holder.mImageView);
        }else {
            LogUtils.LOG("ceshi", shareImgs.get(position), "dizhi");
            Bitmap bitmap = BitmapFactory.decodeFile(shareImgs.get(position));
            Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap, 350, 350, true);
            LogUtils.LOG("ceshi", shareImgs.get(position)+mBitmap.toString(), "dizhi");
            holder.mImageView.setImageBitmap(mBitmap);
        }

        ViewGroup.LayoutParams para;
        para = holder.mImageView.getLayoutParams();
        para.height = weight;
        para.width = weight;
        holder.mImageView.setLayoutParams(para);

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
        RelativeLayout mRelativeLayout;
    }
}
