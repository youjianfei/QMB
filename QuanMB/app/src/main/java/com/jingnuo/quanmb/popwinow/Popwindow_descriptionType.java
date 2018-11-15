package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.InterfaceBaiduAddress;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.DescriptionTypeBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 飞 on 2018/11/15.
 */

public class Popwindow_descriptionType {
    View conView;
    private Activity activity;
    PopupWindow mPopupWindow;

    GridView mGridview;


    int typeid;
    DescriptionTypeBean descriptionTypeBean;


    InterfaceBaiduAddress interfaceBaiduAddress;

    List <DescriptionTypeBean.DataBean> mdata;

    Adapter_choose2 adapter_choose2;
    public Popwindow_descriptionType(int typeid,Activity activity , InterfaceBaiduAddress interfaceBaiduAddress) {
        this.activity = activity;
        this.interfaceBaiduAddress=interfaceBaiduAddress;
        this.typeid=typeid;

    }

    public void showPopwindow() {
        //初始化popwindow；
        View conView= LayoutInflater.from(activity).inflate(R.layout.popwindow_description_type,null,false);
        mPopupWindow=new PopupWindow(conView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popskilltype_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        mGridview=conView.findViewById(R.id.listview_description);

        Utils.setAlpha((float) 0.3,activity);
        initdata();
        initlistennr();

    }

    private void initdata() {
        mdata=new ArrayList();
        adapter_choose2=new Adapter_choose2(mdata,activity);
        mGridview.setAdapter(adapter_choose2);
        request(typeid);
    }
    private void initlistennr() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float) 1,activity);
            }
        });
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                interfaceBaiduAddress.onResult(mdata.get(position).getTask_des());
                mPopupWindow.dismiss();
            }
        });

    }
    private void  request(int tasktypeid){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"descriptionTYPE");
                descriptionTypeBean=new Gson().fromJson(respose,DescriptionTypeBean.class);
                if(descriptionTypeBean.getData()!=null){
                    mdata.clear();
                    mdata.addAll(descriptionTypeBean.getData());
                    adapter_choose2.notifyDataSetChanged();
                    interfaceBaiduAddress.onResult(mdata.get(0).getTask_des());
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.DescriptionType+tasktypeid,activity,0);
        LogUtils.LOG("ceshi",Urls.Baseurl_cui+Urls.DescriptionType+tasktypeid,"descriptionTYPE");
    }



    class Adapter_choose2 extends BaseAdapter {
        List<DescriptionTypeBean.DataBean> mData;
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
                convertView=mInflater.inflate(R.layout.item_text3,null,false);
                viewholder.mtextview_choose=convertView.findViewById(R.id.text_text);
//                viewholder.image_type=convertView.findViewById(R.id.image_type);
                convertView.setTag(viewholder);
            }else {
                viewholder= (Viewholder) convertView.getTag();
            }
            viewholder.mtextview_choose.setText(mData.get(position).getTask_des());
//            Glide.with(mContext).load(mData.get(position).getImg_url()).into(viewholder.image_type);

            return convertView;
        }
        class Viewholder {
            TextView mtextview_choose;
        }
    }
}
