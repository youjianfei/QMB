package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by PC on 2017/1/9.
 */

public abstract class BaseAdapter<T>  extends android.widget.BaseAdapter{
    public List<T> mDatas;
    private Context mContext;

    public BaseAdapter(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public  abstract View getView(int position, View convertView, ViewGroup parent) ;
}
