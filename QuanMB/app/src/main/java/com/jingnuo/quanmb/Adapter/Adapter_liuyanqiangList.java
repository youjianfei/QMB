package com.jingnuo.quanmb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.activity.BaseActivityother;
import com.jingnuo.quanmb.entityclass.LiuyanqiangListBean;
import com.jingnuo.quanmb.quanmb.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_liuyanqiangList extends BaseAdapter{
    private  Context mcontext;
    private LayoutInflater mInflater;
    List<LiuyanqiangListBean.DataBean>mList_liuyan;



    public Adapter_liuyanqiangList(List mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mcontext=mContext;
        this.mList_liuyan=mDatas;
        mInflater=LayoutInflater.from(mcontext);
    }

    @Override
    public int getItemViewType(int position) {
        if(mList_liuyan.get(position).getImages()==null){
            return 0;
        }
        if(mList_liuyan.get(position).getImagesUrlsList().size()==1){
            return 1;
        }
        if(mList_liuyan.get(position).getImagesUrlsList().size()==2){
            return 2;
        }
        if(mList_liuyan.get(position).getImagesUrlsList().size()==3){
            return 3;
        }
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder0 viewHolder0=null;
        ViewHolder1 viewHolder1=null;
        ViewHolder2 viewHolder2=null;
        ViewHolder3 viewHolder3=null;


        int type = getItemViewType(position);
        if (type==0){
            if(convertView==null){
                viewHolder0=new ViewHolder0();
                convertView=mInflater.inflate(R.layout.item_liuyanqinag0,null,false);
                viewHolder0.imageView_touxiang=convertView.findViewById(R.id.circle_touxiang);
                viewHolder0.mTextview_name=convertView.findViewById(R.id.text_name);
                viewHolder0.mTextview_time=convertView.findViewById(R.id.text_time);
                viewHolder0.mTextview_content=convertView.findViewById(R.id.text_content);
                convertView.setTag(viewHolder0);
            }else {
                viewHolder0= (ViewHolder0) convertView.getTag();
            }

            Glide.with(mcontext).load(mList_liuyan.get(position).getAvatar_URL()).into(viewHolder0.imageView_touxiang);
            viewHolder0.mTextview_name.setText(mList_liuyan.get(position).getNick_name());
            viewHolder0.mTextview_content.setText(mList_liuyan.get(position).getContent());
            viewHolder0.mTextview_time.setText(mList_liuyan.get(position).getCreateDate());
        }
        if (type==1){
            if(convertView==null){
                viewHolder1=new ViewHolder1();
                convertView=mInflater.inflate(R.layout.item_liuyanqinag1,null,false);
                viewHolder1.imageView_touxiang=convertView.findViewById(R.id.circle_touxiang);
                viewHolder1.mTextview_name=convertView.findViewById(R.id.text_name);
                viewHolder1.mTextview_time=convertView.findViewById(R.id.text_time);
                viewHolder1.mTextview_content=convertView.findViewById(R.id.text_content);
                viewHolder1.mImageview_one=convertView.findViewById(R.id.iamge_one);
                convertView.setTag(viewHolder1);
            }else {
                viewHolder1= (ViewHolder1) convertView.getTag();
            }

            Glide.with(mcontext).load(mList_liuyan.get(position).getAvatar_URL()).into(viewHolder1.imageView_touxiang);
            viewHolder1.mTextview_name.setText(mList_liuyan.get(position).getNick_name());
            viewHolder1.mTextview_content.setText(mList_liuyan.get(position).getContent());
            viewHolder1.mTextview_time.setText(mList_liuyan.get(position).getCreateDate());
            Glide.with(mcontext).load(mList_liuyan.get(position).getImagesUrlsList().get(0)).into(viewHolder1.mImageview_one);
        }
        if (type==2){
            if(convertView==null){
                viewHolder2=new ViewHolder2();
                convertView=mInflater.inflate(R.layout.item_liuyanqinag2,null,false);
                viewHolder2.imageView_touxiang=convertView.findViewById(R.id.circle_touxiang);
                viewHolder2.mTextview_name=convertView.findViewById(R.id.text_name);
                viewHolder2.mTextview_time=convertView.findViewById(R.id.text_time);
                viewHolder2.mTextview_content=convertView.findViewById(R.id.text_content);
                viewHolder2.mImageview_1=convertView.findViewById(R.id.iamge_1);
                viewHolder2.mImageview_2=convertView.findViewById(R.id.iamge_2);
                convertView.setTag(viewHolder2);
            }else {
                viewHolder2= (ViewHolder2) convertView.getTag();
            }

            Glide.with(mcontext).load(mList_liuyan.get(position).getAvatar_URL()).into(viewHolder2.imageView_touxiang);
            viewHolder2.mTextview_name.setText(mList_liuyan.get(position).getNick_name());
            viewHolder2.mTextview_content.setText(mList_liuyan.get(position).getContent());
            viewHolder2.mTextview_time.setText(mList_liuyan.get(position).getCreateDate());
            Glide.with(mcontext).load(mList_liuyan.get(position).getImagesUrlsList().get(0)).into(viewHolder2.mImageview_1);
            Glide.with(mcontext).load(mList_liuyan.get(position).getImagesUrlsList().get(1)).into(viewHolder2.mImageview_2);
        }
        if (type==3){
            if(convertView==null){
                viewHolder3=new ViewHolder3();
                convertView=mInflater.inflate(R.layout.item_liuyanqinag3,null,false);
                viewHolder3.imageView_touxiang=convertView.findViewById(R.id.circle_touxiang);
                viewHolder3.mTextview_name=convertView.findViewById(R.id.text_name);
                viewHolder3.mTextview_time=convertView.findViewById(R.id.text_time);
                viewHolder3.mTextview_content=convertView.findViewById(R.id.text_content);
                viewHolder3.mImageview_111=convertView.findViewById(R.id.iamge_11);
                viewHolder3.mImageview_222=convertView.findViewById(R.id.iamge_22);
                viewHolder3.mImageview_333=convertView.findViewById(R.id.iamge_33);
                convertView.setTag(viewHolder3);
            }else {
                viewHolder3= (ViewHolder3) convertView.getTag();
            }

            Glide.with(mcontext).load(mList_liuyan.get(position).getAvatar_URL()).into(viewHolder3.imageView_touxiang);
            viewHolder3.mTextview_name.setText(mList_liuyan.get(position).getNick_name());
            viewHolder3.mTextview_content.setText(mList_liuyan.get(position).getContent());
            viewHolder3.mTextview_time.setText(mList_liuyan.get(position).getCreateDate());
            Glide.with(mcontext).load(mList_liuyan.get(position).getImagesUrlsList().get(0)).into(viewHolder3.mImageview_111);
            Glide.with(mcontext).load(mList_liuyan.get(position).getImagesUrlsList().get(1)).into(viewHolder3.mImageview_222);
            Glide.with(mcontext).load(mList_liuyan.get(position).getImagesUrlsList().get(2)).into(viewHolder3.mImageview_333);
        }
        return convertView;
    }
    class  ViewHolder0 {
        CircleImageView imageView_touxiang;
        TextView mTextview_name;
        TextView mTextview_content;
        TextView mTextview_time;
    }
    class  ViewHolder1 {
        CircleImageView imageView_touxiang;
        TextView mTextview_name;
        TextView mTextview_content;
        TextView mTextview_time;
        ImageView mImageview_one;
    }
    class  ViewHolder2 {
        CircleImageView imageView_touxiang;
        TextView mTextview_name;
        TextView mTextview_content;
        TextView mTextview_time;
        ImageView mImageview_1;
        ImageView mImageview_2;
    }
    class  ViewHolder3 {
        CircleImageView imageView_touxiang;
        TextView mTextview_name;
        TextView mTextview_content;
        TextView mTextview_time;
        ImageView mImageview_111;
        ImageView mImageview_222;
        ImageView mImageview_333;
    }
}
