package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class Adapter_SearchAddress extends BaseAdapter{
//    List<PoiInfo> mdata;
    List<String> mdata;
    Context mContext;
    LayoutInflater mLayoutinflater;


    public Adapter_SearchAddress(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mdata=mDatas;
        this.mContext=mContext;
        mLayoutinflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder=null;
        if(convertView==null){
            viewholder=new Viewholder();
            convertView=mLayoutinflater.inflate(R.layout.item_searchaddress,null,false);
            viewholder.mTextview_name=convertView.findViewById(R.id.text_nameaddress);
            viewholder.mTextview_address=convertView.findViewById(R.id.text_addressaddress);
            convertView.setTag(viewholder);
        }else {
            viewholder= (Viewholder) convertView.getTag();
        }
        viewholder.mTextview_name.setText(mdata.get(position));
        viewholder.mTextview_address.setText(mdata.get(position));

        return convertView;
    }
    class  Viewholder{
        TextView mTextview_name;
        TextView mTextview_address;
    }
}
