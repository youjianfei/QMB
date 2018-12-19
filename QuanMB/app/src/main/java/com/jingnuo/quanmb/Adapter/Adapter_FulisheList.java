package com.jingnuo.quanmb.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.activity.ZixunKefuWebActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.SheQuListBean;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.entityclass.ShenghuoquanBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.Utils;

import java.util.List;

public class Adapter_FulisheList extends BaseAdapter {
    List<ShenghuoquanBean.DataBean> mData;
    Activity mContext;
    LayoutInflater mInfalter;

    private SparseArray<CountDownTimer> countDownCounters;
    String type;

    public Adapter_FulisheList(List mDatas, Activity mContext,String type) {
        super(mDatas, mContext);
        this.mContext = mContext;
        this.mData = mDatas;
        mInfalter = LayoutInflater.from(mContext);
        this.type=type;
        this.countDownCounters = new SparseArray<>();
    }
    public void cancelAllTimers() {
        if (countDownCounters == null) {
            return;
        }
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            CountDownTimer cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        switch (type){
            case"1"://限时秒杀
                return 1;
            case"2"://周边
                return 2;
            case"3"://新鲜事
                return 0;
        }
        return 1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Viewholder_xinxianshi viewholder_xinxianshi ;
        final Viewholder_xianshimiaosha viewholder_xianshimiaosha ;
        Viewholder_zhoubian viewholder_zhoubian ;

        int type = getItemViewType(position);

        if(type==0){//新鲜事
            if (convertView == null) {
                viewholder_xinxianshi = new Viewholder_xinxianshi();
                convertView = mInfalter.inflate(R.layout.item_xinxianshi, null, false);
                viewholder_xinxianshi.mImage = convertView.findViewById(R.id.image_fuli);
                convertView.setTag(viewholder_xinxianshi);
            } else {
                viewholder_xinxianshi = (Viewholder_xinxianshi) convertView.getTag();
            }
//            if(mData.get(position).getImg_url().equals("")){
//                viewholder_xinxianshi.mImage.setBackgroundResource(R.mipmap.xinxianshi);
//            }
        Glide.with(mContext).load(mData.get(position).getImg_url()).into(viewholder_xinxianshi.mImage);
            ViewGroup.LayoutParams para;
            para = viewholder_xinxianshi.mImage.getLayoutParams();
            para.width = Staticdata.ScreenWidth- SizeUtils.dip2px(mContext,20);
            para.height = (int) ( para.width*0.43);
            viewholder_xinxianshi.mImage.setLayoutParams(para);
        }
        if(type==1){//限时秒杀
            if (convertView == null) {
                viewholder_xianshimiaosha = new Viewholder_xianshimiaosha();
                convertView = mInfalter.inflate(R.layout.item_xianshimiaosha, null, false);
                viewholder_xianshimiaosha.mImage = convertView.findViewById(R.id.image_pic);
                viewholder_xianshimiaosha.textview_time = convertView.findViewById(R.id.textview_time);
                viewholder_xianshimiaosha.linearlayout_qianggou = convertView.findViewById(R.id.linearlayout_qianggou);
                convertView.setTag(viewholder_xianshimiaosha);
            } else {
                viewholder_xianshimiaosha = (Viewholder_xianshimiaosha) convertView.getTag();
            }
//            if(mData.get(position).getImg_url().equals("")){
//                viewholder_xianshimiaosha.mImage.setBackgroundResource(R.mipmap.xinxianshi);
//            }
        Glide.with(mContext).load(mData.get(position).getImg_url()).into(viewholder_xianshimiaosha.mImage);
            ViewGroup.LayoutParams para;
            para = viewholder_xianshimiaosha.mImage.getLayoutParams();
            para.width = Staticdata.ScreenWidth- SizeUtils.dip2px(mContext,20);
            para.height = (int) ( para.width*0.43);
            viewholder_xianshimiaosha.mImage.setLayoutParams(para);
             long nowtime=Long.parseLong(mData.get(position).getNow_date_code())*1000;
             long starttime=Long.parseLong(mData.get(position).getStart_date_code())*1000;
            long endtime=Long.parseLong(mData.get(position).getEnd_date_code())*1000;
            LogUtils.LOG("zeshii","no"+starttime+"qqqqqq"+position,"倒计时");
            viewholder_xianshimiaosha.linearlayout_qianggou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, ZixunKefuWebActivity.class);
                    intent.putExtra("webtitle", "");
                    intent.putExtra("type", "生活圈");
                    intent.putExtra("URL", mData.get(position).getClick_url());
                    mContext.startActivity(intent);
                }
            });
            CountDownTimer countDownTimer = countDownCounters.get(viewholder_xianshimiaosha.textview_time.hashCode());
            if (countDownTimer != null) {
                //将复用的倒计时清除
                countDownTimer.cancel();
            }

            if(nowtime-starttime>0){//活动已经开始
                if(endtime-nowtime>0){//活动中
                    viewholder_xianshimiaosha.linearlayout_qianggou.setVisibility(View.VISIBLE);
                    countDownTimer =  new CountDownTimer((endtime-nowtime),1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                           String daojishi= Utils.getDistanceTime4(millisUntilFinished);
//                            LogUtils.LOG("ceshi", Utils.getDistanceTime4(millisUntilFinished)+"","倒计时");
                            viewholder_xianshimiaosha.textview_time.setText("倒计时:"+daojishi);
                        }

                        @Override
                        public void onFinish() {
                            viewholder_xianshimiaosha.textview_time.setText("活动已结束");
                        }
                    }.start();
                    //将此 countDownTimer 放入list.
                    countDownCounters.put(viewholder_xianshimiaosha.textview_time.hashCode(), countDownTimer);
                }else {//活动已经结束
                    viewholder_xianshimiaosha.textview_time.setText("活动已结束");
                    viewholder_xianshimiaosha.linearlayout_qianggou.setVisibility(View.GONE);
                }
            }else {//活动未开始
                viewholder_xianshimiaosha.linearlayout_qianggou.setVisibility(View.GONE);
                String data=mData.get(position).getStart_date().substring(5);
                viewholder_xianshimiaosha.textview_time.setText(data+" 活动开始");
            }
        }
        if(type==2){//周边
            if (convertView == null) {
                viewholder_zhoubian = new Viewholder_zhoubian();
                convertView = mInfalter.inflate(R.layout.item_zhoubian, null, false);
                viewholder_zhoubian.mImage = convertView.findViewById(R.id.image_pic);
                convertView.setTag(viewholder_zhoubian);
            } else {
                viewholder_zhoubian = (Viewholder_zhoubian) convertView.getTag();
            }
//            if(mData.get(position).getImg_url().equals("")){
//                viewholder_zhoubian.mImage.setBackgroundResource(R.mipmap.xinxianshi);
//            }
        Glide.with(mContext).load(mData.get(position).getImg_url()).into(viewholder_zhoubian.mImage);
            ViewGroup.LayoutParams para;
            para = viewholder_zhoubian.mImage.getLayoutParams();
            para.width = -1;
            para.height = Staticdata.ScreenWidth- SizeUtils.dip2px(mContext,30);
            para.height =  para.height/2;
            para.height = (int) (para.height*1.28);
            viewholder_zhoubian.mImage.setLayoutParams(para);
        }
        return convertView;

    }
    class Viewholder_xinxianshi {
        ImageView mImage;
    }
    class Viewholder_xianshimiaosha {
        ImageView mImage;
        TextView textview_time;
        LinearLayout linearlayout_qianggou;
    }
    class Viewholder_zhoubian {
        ImageView mImage;
    }
}
