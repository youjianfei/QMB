package com.jingnuo.quanmb.popwinow;

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
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.LoveTypeBean;
import com.jingnuo.quanmb.entityclass.Skillmenu_oneBean;
import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class Popwindow_LoveType {
    View conView;
    private Activity activity;
    PopupWindow mPopupWindow;

    //控件
    TextView mTextview_one;
    ImageView mImage_close;
    ListView popListview;
    //对象
    Adapter_choose mAdapter;
    InterfacePopwindow_SkillType  mInterface;
    //数据
    List<LoveTypeBean.DataBean>listdata_one;

    public Popwindow_LoveType(Activity activity , InterfacePopwindow_SkillType mInterface) {
        this.activity = activity;
        this.mInterface=mInterface;
        listdata_one=new ArrayList<>();
    }

    public void showPopwindow() {
        //初始化popwindow；
        View conView= LayoutInflater.from(activity).inflate(R.layout.popwindow_lovetasktype,null,false);
        mPopupWindow=new PopupWindow(conView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popskilltype_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        popListview= conView.findViewById(R.id.Listview_addresspopwindow);
        mImage_close= conView.findViewById(R.id.ImageView_close);
        mTextview_one= conView.findViewById(R.id.text_one);
        Utils.setAlpha((float) 0.3,activity);
        initdata();
        initlistennr();
        request(Urls.Baseurl_cui+Urls.loveTaskType);
    }

    private void initdata() {
        mAdapter=new Adapter_choose(listdata_one,activity);
        popListview.setAdapter(mAdapter);
    }

    private void request(String url_list ) {

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"popwindow");
                listdata_one.clear();
                listdata_one.addAll(new Gson().fromJson(respose,LoveTypeBean.class).getData());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(url_list,activity,0);

    }

    private void initlistennr() {


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
                {
                    mInterface.onSuccesses(listdata_one.get(i).getType_name(),Integer.parseInt(listdata_one.get(i).getType_code()));
                    mPopupWindow.dismiss();
                }

            }
        });
    }

    class Adapter_choose extends BaseAdapter {
        List<LoveTypeBean.DataBean> mData;
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
            viewholder.mtextview_choose.setText(mData.get(position).getType_name());

            return convertView;
        }
        class Viewholder {
            TextView mtextview_choose;
        }
    }


}
