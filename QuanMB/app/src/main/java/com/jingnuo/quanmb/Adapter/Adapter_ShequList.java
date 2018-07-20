package com.jingnuo.quanmb.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interence_shuaxinzhiding;
import com.jingnuo.quanmb.Interface.InterfaceAdapterSuccess;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.MySkillBean;
import com.jingnuo.quanmb.entityclass.SheQuListBean;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.popwinow.Popwindow_zhinengshuaxin;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Adapter_ShequList extends BaseAdapter {
    List<SheQuListBean.DataBean> mData;
    Activity mContext;
    LayoutInflater mInfalter;

    public Adapter_ShequList( List mDatas, Activity mContext) {
        super(mDatas, mContext);
        this.mContext = mContext;
        this.mData = mDatas;
        mInfalter = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder holder = null;
        if (convertView == null) {
            holder = new Viewholder();
            convertView = mInfalter.inflate(R.layout.item_shequ, null, false);
            holder.mTextview_name = convertView.findViewById(R.id.text_shequname);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();

        }
        holder.mTextview_name.setText(mData.get(position).getCommunity_name()+"");
        return convertView;
    }

    class Viewholder {
        TextView mTextview_name;
    }

}
