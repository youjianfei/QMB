package com.jingnuo.quanmb.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.activity.BaseActivityother;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.entityclass.SkillmentlistBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.master.permissionhelper.PermissionHelper;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class Adapter_shophall extends BaseAdapter {
    List<SkillmentlistBean.DataBean.ListBean> mData;
    Activity mContext;
    LayoutInflater mInflater;
    PermissionHelper mPermission;

    public Adapter_shophall(List mDatas, Activity mContext, PermissionHelper mPermission) {
        super(mDatas, mContext);
        this.mData = mDatas;
        this.mContext = mContext;
        this.mPermission = mPermission;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = null;
        if (viewholder == null) {
            viewholder = new Viewholder();
            convertView = mInflater.inflate(R.layout.item_shophalllist, null, false);
            viewholder.mImageview_shoppic = convertView.findViewById(R.id.image_shoppic);
            viewholder.mTextview_skillstitle = convertView.findViewById(R.id.text_shopskills);
            viewholder.mTextview_address = convertView.findViewById(R.id.textview_address);
            viewholder.mTextview_vip = convertView.findViewById(R.id.textview_vip);
            viewholder.mImage_call = convertView.findViewById(R.id.image_call);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }
        viewholder.mTextview_skillstitle.setText(mData.get(position).getTitle());
        viewholder.mTextview_address.setText(mData.get(position).getRelease_address());
        Glide.with(mContext).load(mData.get(position).getAvatar_url()).into(viewholder.mImageview_shoppic);
        if(mData.get(position).getBusiness_no().equals("")){
            viewholder.mTextview_vip.setText("认证帮手");
        }else {
            viewholder.mTextview_vip.setText("认证商户");
        }

        //点击拨打电话
        viewholder.mImage_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Staticdata.isLogin){
                    ToastUtils.showToast(mContext,"请先登录");
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + mData.get(position).getMobile_no());
                intent.setData(data);

                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

//                    ToastUtils.showToast(mContext,"拨打电话权限被你拒绝，请在手机设置中开启");
                    mPermission.request(new PermissionHelper.PermissionCallback() {
                        @Override
                        public void onPermissionGranted() {

                        }

                        @Override
                        public void onIndividualPermissionGranted(String[] grantedPermission) {

                        }

                        @Override
                        public void onPermissionDenied() {

                        }

                        @Override
                        public void onPermissionDeniedBySystem() {

                        }
                    });
                    return;
                }
                mContext.startActivity(intent);//调用具体方法

            }
        });
        return convertView;
    }

    class Viewholder {
        ImageView mImageview_shoppic;
        TextView mTextview_skillstitle;
        TextView mTextview_address;
        TextView mTextview_vip;
        ImageView mImage_call;
    }
}
