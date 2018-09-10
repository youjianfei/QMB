package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.BaseActivityother;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Skillmenu_oneBean;
import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class Popwindow_SkillType {
    View conView;
    private Activity activity;
    PopupWindow mPopupWindow;

    //控件
    TextView mTextview_one;
    TextView mTextview_two;
    ImageView mImage_close;
    GridView popListview;
    GridView Listview_addresspopwindow2;
    //对象
    Adapter_choose mAdapter;
    Adapter_choose2 mAdapter2;
    InterfacePopwindow_SkillType  mInterface;
    //数据
    List<Skillmenu_oneBean.DataBean.ListBean>listdata_one;
    List<Skillmenu_twoBean.DataBean.ListBean>listdata_two;

    int  level=1;
    public Popwindow_SkillType(Activity activity ,InterfacePopwindow_SkillType mInterface) {
        this.activity = activity;
        this.mInterface=mInterface;
        listdata_one=new ArrayList<>();
        listdata_two=new ArrayList<>();
    }

    public void showPopwindow() {
        //初始化popwindow；
        View conView= LayoutInflater.from(activity).inflate(R.layout.popwindow_skilltype,null,false);
        mPopupWindow=new PopupWindow(conView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popskilltype_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        popListview= conView.findViewById(R.id.Listview_addresspopwindow);
        Listview_addresspopwindow2= conView.findViewById(R.id.Listview_addresspopwindow2);
        mImage_close= conView.findViewById(R.id.ImageView_close);
        mTextview_one= conView.findViewById(R.id.text_one);
        mTextview_two=  conView.findViewById(R.id.text_two);
        Utils.setAlpha((float) 0.3,activity);
        initdata();
        initlistennr();
        popListview.setVisibility(View.VISIBLE);
        Listview_addresspopwindow2.setVisibility(View.GONE);
        request(Urls.Baseurl+Urls.Skillmenu_one,level);
    }

    private void initdata() {
        mAdapter=new Adapter_choose(listdata_one,activity);
        mAdapter2=new Adapter_choose2(listdata_one,activity);
        popListview.setAdapter(mAdapter);
        Listview_addresspopwindow2.setAdapter(mAdapter2);

    }

    private void request(String url_list , final int level_) {

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi","选择类型"+respose,"popwindow");
                listdata_one.clear();
                listdata_one.addAll(new Gson().fromJson(respose,Skillmenu_oneBean.class).getData().getList());
                mAdapter.notifyDataSetChanged();
                mAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(url_list,activity,0);

    }

    private void initlistennr() {
        mTextview_one.setOnClickListener(new View.OnClickListener() {//点击一级请选择
            @Override
            public void onClick(View view) {
                level=1;
                popListview.setVisibility(View.VISIBLE);
                Listview_addresspopwindow2 .setVisibility(View.GONE);
                request(Urls.Baseurl+Urls.Skillmenu_one,level);
                mTextview_two.setVisibility(View.GONE);
            }
        });

        mImage_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {//点击一级菜单请求二级菜单
            @Override
            public void onDismiss() {
                Utils.setAlpha(1,activity);
            }
        });
        popListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(level==1){
                    level=2;
                    popListview.setVisibility(View.GONE);
                    Listview_addresspopwindow2 .setVisibility(View.VISIBLE);
                    int  id=listdata_one.get(i).getSpecialty_id();
                    mTextview_one.setText(listdata_one.get(i).getSpecialty_name());
                    request(Urls.Baseurl+Urls.Skillmenu_right+"?specialty_id="+id,level);
                    mTextview_two.setVisibility(View.VISIBLE);
                }else {
                    popListview.setVisibility(View.VISIBLE);
                    Listview_addresspopwindow2 .setVisibility(View.GONE);
                    mTextview_two.setText(listdata_one.get(i).getSpecialty_name());
                    mInterface.onSuccesses(listdata_one.get(i).getSpecialty_name(),listdata_one.get(i).getSpecialty_id());
                    mPopupWindow.dismiss();
                }

            }
        });
        Listview_addresspopwindow2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                if(level==1){
                    level=2;
                    popListview.setVisibility(View.GONE);
                    Listview_addresspopwindow2 .setVisibility(View.VISIBLE);
                    int  id=listdata_one.get(i).getSpecialty_id();
                    mTextview_one.setText(listdata_one.get(i).getSpecialty_name());
                    request(Urls.Baseurl+Urls.Skillmenu_right+"?specialty_id="+id,level);
                    mTextview_two.setVisibility(View.VISIBLE);
                }else {
                    popListview.setVisibility(View.VISIBLE);
                    Listview_addresspopwindow2 .setVisibility(View.GONE);
                    mTextview_two.setText(listdata_one.get(i).getSpecialty_name());
                    mInterface.onSuccesses(listdata_one.get(i).getSpecialty_name(),listdata_one.get(i).getSpecialty_id());
                    mPopupWindow.dismiss();
                }
            }
        });
    }

    class Adapter_choose extends BaseAdapter {
        List<Skillmenu_oneBean.DataBean.ListBean> mData;
        Context mContext;
        LayoutInflater mInflater;

        public Adapter_choose(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mContext=mContext;
            this.mData=mDatas;
            mInflater=LayoutInflater.from(mContext);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           Viewholder  viewholder=null;
            if(convertView==null){
                viewholder=new Viewholder();
                convertView=mInflater.inflate(R.layout.item_text,null,false);
                viewholder.mtextview_choose=convertView.findViewById(R.id.text_text);
                viewholder.image_type=convertView.findViewById(R.id.image_type);
                convertView.setTag(viewholder);
            }else {
                viewholder= (Viewholder) convertView.getTag();
            }
            viewholder.mtextview_choose.setText(mData.get(position).getSpecialty_name());
            Glide.with(mContext).load(mData.get(position).getImg_url()).into(viewholder.image_type);

            return convertView;
        }
        class Viewholder {
            TextView mtextview_choose;
            ImageView image_type;
        }
    }
    class Adapter_choose2 extends BaseAdapter {
        List<Skillmenu_oneBean.DataBean.ListBean> mData;
        Context mContext;
        LayoutInflater mInflater;

        public Adapter_choose2(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mContext=mContext;
            this.mData=mDatas;
            mInflater=LayoutInflater.from(mContext);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder  viewholder=null;
            if(convertView==null){
                viewholder=new Viewholder();
                convertView=mInflater.inflate(R.layout.item_text2,null,false);
                viewholder.mtextview_choose=convertView.findViewById(R.id.text_text);
//                viewholder.image_type=convertView.findViewById(R.id.image_type);
                convertView.setTag(viewholder);
            }else {
                viewholder= (Viewholder) convertView.getTag();
            }
            viewholder.mtextview_choose.setText(mData.get(position).getSpecialty_name());
//            Glide.with(mContext).load(mData.get(position).getImg_url()).into(viewholder.image_type);

            return convertView;
        }
        class Viewholder {
            TextView mtextview_choose;
        }
    }

}
