package com.jingnuo.quanmb.class_;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yancy.imageselector.ImageLoader;

/**
 * Created by Administrator on 2016/6/6.
 */
public class GlideLoader implements ImageLoader {

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
       Glide.with(context)
                .load(path)
                .placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
                .centerCrop()
                .into(imageView);
    }

}
