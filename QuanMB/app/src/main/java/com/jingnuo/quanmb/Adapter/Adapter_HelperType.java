package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.R;
import java.util.List;
import java.util.Random;

public class Adapter_HelperType extends RecyclerView.Adapter<Adapter_HelperType.MyViewHolder> implements View.OnClickListener{

    List<Skillmenu_twoBean.DataBean.ListBean > mData;
    Context mContnt;
    LayoutInflater mInflater;
    private OnItemClickListener mItemClickListener;
    int select=0;

    public Adapter_HelperType( List<Skillmenu_twoBean.DataBean.ListBean> mData, Context mContnt) {
        this.mData = mData;
        this.mContnt = mContnt;
        mInflater=LayoutInflater.from(mContnt);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {//自定义点击事件
        mItemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate (R.layout.item_helperitem, parent, false);
        MyViewHolder viewHolder=new MyViewHolder(view) ;
        view.setOnClickListener(this);
        return viewHolder;
    }
    @Override
    public void onClick(View v) {//点击事件
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position).getSpecialty_name());
        holder.itemView.setTag(position);
        if(mData.get(position).isIs_select()){//是否选择 分配不同的背景
            Random rand = new Random();//随机数选择不同背景
            int i = rand.nextInt(4);
            switch (i){
                case 0:
                    holder.mTextView.setTextColor(mContnt.getResources().getColor(R.color.white));
                    holder.mTextView.setBackgroundResource(R.drawable.text_helpertype1);
                    break;
                case 1:
                    holder.mTextView.setTextColor(mContnt.getResources().getColor(R.color.white));
                    holder.mTextView.setBackgroundResource(R.drawable.text_helpertype2);
                    break;
                case 2:
                    holder.mTextView.setTextColor(mContnt.getResources().getColor(R.color.white));
                    holder.mTextView.setBackgroundResource(R.drawable.text_helpertype3);
                    break;
                case 3:
                    holder.mTextView.setTextColor(mContnt.getResources().getColor(R.color.white));
                    holder.mTextView.setBackgroundResource(R.drawable.text_helpertype4);
                    break;
                case 4:
                    holder.mTextView.setTextColor(mContnt.getResources().getColor(R.color.white));
                    holder.mTextView.setBackgroundResource(R.drawable.text_helpertype5);
                    break;
            }
        }else {
            holder.mTextView.setBackgroundResource(R.drawable.text_gray4);
            holder.mTextView.setTextColor(mContnt.getResources().getColor(R.color.black_text));
        }

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    //自定义的ViewHoder，持有item的所有控件
      class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public MyViewHolder(View view) {
            super (view);
            mTextView =  view.findViewById(R.id.text_text);
        }
    }

}
