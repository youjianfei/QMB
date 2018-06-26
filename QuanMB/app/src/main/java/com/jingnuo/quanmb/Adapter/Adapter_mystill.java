package com.jingnuo.quanmb.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interence_shuaxinzhiding;
import com.jingnuo.quanmb.Interface.InterfaceAdapterSuccess;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_zhinengshuaxin;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.MySkillBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Adapter_mystill extends BaseAdapter {
    List<MySkillBean.DataBean.ListBean> mData;
    Activity mContext;
    LayoutInflater mInfalter;
    InterfaceAdapterSuccess interfaceAdapterSuccess;

    public Adapter_mystill(List mDatas, Activity mContext, InterfaceAdapterSuccess interfaceAdapterSuccess) {
        super(mDatas, mContext);
        this.mContext = mContext;
        this.mData = mDatas;
        this.interfaceAdapterSuccess = interfaceAdapterSuccess;
        mInfalter = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder holder = null;
        if (convertView == null) {
            holder = new Viewholder();
            convertView = mInfalter.inflate(R.layout.item_mystill, null, false);
            holder.mTextview_title = convertView.findViewById(R.id.textview_titl);
            holder.mTextview_cerattime = convertView.findViewById(R.id.textview_issuename);
            holder.mTextview_content = convertView.findViewById(R.id.text_content);
            holder.mTextview_cancle = convertView.findViewById(R.id.text_cancle);
            holder.mTextview_putongshuxin = convertView.findViewById(R.id.text_putongshuaxin);
            holder.mTextview_zhinengshuxin = convertView.findViewById(R.id.text_zhinengshuaxin);
            holder.mTextview_zhuanyezhiding = convertView.findViewById(R.id.text_zhuanyezhiding);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        holder.mTextview_title.setText(mData.get(position).getTitle());
        holder.mTextview_cerattime.setText("发布时间：" + mData.get(position).getRelease_date());
        holder.mTextview_content.setText(mData.get(position).getDescription());
        if (mData.get(position).getStatus().equals("2")) {
            holder.mTextview_putongshuxin.setVisibility(View.GONE);
            holder.mTextview_zhinengshuxin.setVisibility(View.GONE);
            holder.mTextview_zhuanyezhiding.setVisibility(View.GONE);
        } else {
            holder.mTextview_putongshuxin.setVisibility(View.VISIBLE);
            holder.mTextview_zhinengshuxin.setVisibility(View.VISIBLE);
            holder.mTextview_zhuanyezhiding.setVisibility(View.VISIBLE);
        }

        holder.mTextview_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.LOG("ceshi", Urls.Baseurl + Urls.shopCancleSkill +
                        Staticdata.static_userBean.getData().getUser_token() +
                        "&id=" + mData.get(position).getRelease_specialty_id(), "撤消任务");
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        interfaceAdapterSuccess.onResult(true);
                    }

                    @Override
                    public void onError(int error) {

                    }
                }).Http(Urls.Baseurl + Urls.shopCancleSkill +
                        Staticdata.static_userBean.getData().getUser_token() +
                        "&id=" + mData.get(position).getRelease_specialty_id(), mContext, 0);
            }
        });
        holder.mTextview_putongshuxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.LOG("ceshi", Urls.Baseurl + Urls.putongshuaxin +
                        Staticdata.static_userBean.getData().getUser_token() +
                        "&release_id=" + mData.get(position).getRelease_specialty_id(), "普通刷新");
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        int status = 0;
                        String msg = "";
                        LogUtils.LOG("ceshi", respose, "普通刷新");
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//
                            msg = (String) object.get("message");//
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (status == 1) {
                            interfaceAdapterSuccess.onResult(true);
                            ToastUtils.showToast(mContext, "刷新成功");
                        } else {
                            ToastUtils.showToast(mContext, msg);
                        }

                    }

                    @Override
                    public void onError(int error) {

                    }
                }).Http(Urls.Baseurl + Urls.putongshuaxin +
                        Staticdata.static_userBean.getData().getUser_token() +
                        "&release_id=" + mData.get(position).getRelease_specialty_id(), mContext, 0);

            }
        });

        holder.mTextview_zhinengshuxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Popwindow_zhinengshuaxin(1,mData.get(position).getTitle(),mContext, new Interence_shuaxinzhiding() {
                    @Override
                    public void onResult(String result) {

                    }
                }).showpop();

            }
        });
        holder.mTextview_zhuanyezhiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Popwindow_zhinengshuaxin(2,mData.get(position).getTitle(),mContext, new Interence_shuaxinzhiding() {
                    @Override
                    public void onResult(String result) {

                    }
                }).showpop();

            }
        });

        return convertView;
    }

    class Viewholder {
        TextView mTextview_title;
        TextView mTextview_cerattime;
        TextView mTextview_content;
        TextView mTextview_cancle;
        TextView mTextview_putongshuxin;
        TextView mTextview_zhinengshuxin;
        TextView mTextview_zhuanyezhiding;
    }

}
