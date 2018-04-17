package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.BaseActivityother;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Skillmenu_oneBean;
import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

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
    ListView popListview;
    //对象
    Adapter_choose mAdapter;
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
        mPopupWindow.setAnimationStyle(R.style.popskilltype_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        popListview= conView.findViewById(R.id.Listview_addresspopwindow);
        mImage_close= conView.findViewById(R.id.ImageView_close);
        mTextview_one= conView.findViewById(R.id.text_one);
        mTextview_two=  conView.findViewById(R.id.text_two);
        Utils.setAlpha((float) 0.3,activity);
        initdata();
        initlistennr();
        request(Urls.Baseurl+Urls.Skillmenu_one,level);
    }

    private void initdata() {
        mAdapter=new Adapter_choose(listdata_one,activity);
        popListview.setAdapter(mAdapter);
    }

    private void request(String url_list , final int level_) {

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
        }).Http(url_list,activity,0);

    }

    private void initlistennr() {
        mTextview_one.setOnClickListener(new View.OnClickListener() {//点击一级请选择
            @Override
            public void onClick(View view) {
                level=1;
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
                    int  id=listdata_one.get(i).getSpecialty_id();
                    mTextview_one.setText(listdata_one.get(i).getSpecialty_name());
                    request(Urls.Baseurl+Urls.Skillmenu_right+"?specialty_id="+id,level);
                    mTextview_two.setVisibility(View.VISIBLE);
                }else {
                    mTextview_two.setText(listdata_one.get(i).getSpecialty_name());
                    mInterface.onSuccesses(listdata_one.get(i).getSpecialty_name(),listdata_one.get(i).getSpecialty_id());
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
                convertView.setTag(viewholder);
            }else {
                viewholder= (Viewholder) convertView.getTag();
            }
            viewholder.mtextview_choose.setText(mData.get(position).getSpecialty_name());

            return convertView;
        }
        class Viewholder {
            TextView mtextview_choose;
        }
    }


}
