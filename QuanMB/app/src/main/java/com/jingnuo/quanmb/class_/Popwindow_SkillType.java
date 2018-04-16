package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.BaseActivityother;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Skillmenu_oneBean;
import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
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
    ListView popListview;
    //对象
    Adapter_choose mAdapter;
    //数据
    List<Skillmenu_oneBean.DataBean.ListBean>listdata_one;
    List<Skillmenu_twoBean.DataBean.ListBean>listdata_two;

    public Popwindow_SkillType(Activity activity) {
        this.activity = activity;
        listdata_one=new ArrayList<>();
        listdata_two=new ArrayList<>();
    }

    public void showPopwindow() {
        //初始化popwindow；
        View conView= LayoutInflater.from(activity).inflate(R.layout.popwindow_skilltype,null,false);
        mPopupWindow=new PopupWindow(conView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        popListview= conView.findViewById(R.id.Listview_addresspopwindow);
        mImage_close= conView.findViewById(R.id.ImageView_close);
        mTextview_one= conView.findViewById(R.id.text_one);
        mTextview_two=  conView.findViewById(R.id.text_two);
        Utils.setAlpha((float) 0.3,activity);
        initdata();
        initlistennr();
        request();
    }

    private void initdata() {
        mAdapter=new Adapter_choose(listdata_one,activity);
        popListview.setAdapter(mAdapter);
    }

    private void request() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                listdata_one.clear();
                listdata_one.addAll(new Gson().fromJson(respose,Skillmenu_oneBean.class).getData().getList());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.Skillmenu_one,activity,0);

    }

    private void initlistennr() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha(1,activity);
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
                convertView.setTag(viewholder);
            }else {
                viewholder= (Viewholder) convertView.getTag();
            }

            return convertView;
        }
        class Viewholder {
            TextView mtextview_choose;
        }
    }


}
