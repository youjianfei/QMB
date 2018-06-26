package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.Interence_shuaxinzhiding;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.ZhidingBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.List;


public class Popwindow_zhinengshuaxin {

    View view;
    PopupWindow mPopupWindow;
    Activity activity;

    //控件
    MyGridView myGridView_chooce;
    TextView mTextview_cancel;
    TextView mTextview_queren;
    TextView mTextview_textview_text_title;
    TextView mTextview_textview_type;

    Adater_chooce adater_chooce;
    List<String> mdata;
    String title = "";
    int  type=0;  //1  智能刷新   2  服务置顶



    Interence_shuaxinzhiding interence_shuaxinzhiding;

    public Popwindow_zhinengshuaxin(int type,   String title, Activity activity, Interence_shuaxinzhiding interence_shuaxinzhiding) {
       this.type=type;
        this.title = title;
        this.activity = activity;
        this.interence_shuaxinzhiding = interence_shuaxinzhiding;
    }

    public void showpop() {
        view = LayoutInflater.from(activity).inflate(R.layout.popwindow_zhinengshuaxin, null, false);
        mPopupWindow = new PopupWindow(view, (int) (Staticdata.ScreenWidth * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setAnimationStyle(R.style.popissue_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        Utils.setAlpha((float) 0.3, activity);

        initview();
        initdata();
        initlistenner();
    }

    private void initview() {
        mTextview_textview_type=view.findViewById(R.id.textview_text);
        mTextview_textview_text_title = view.findViewById(R.id.textview_text_title);
        myGridView_chooce = view.findViewById(R.id.grid_view);
        mTextview_cancel = view.findViewById(R.id.textview_cancle);
        mTextview_queren = view.findViewById(R.id.textview_submit);
    }

    private void initdata() {
        mTextview_textview_text_title.setText(title);
        if(type==1){
            mTextview_textview_type.setText("智能刷新");
        }else {
            mTextview_textview_type.setText("服务置顶");
        }
        mdata = new ArrayList<>();
        adater_chooce = new Adater_chooce(mdata, activity);
        myGridView_chooce.setAdapter(adater_chooce);
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "选择天数");
                mdata.clear();
                mdata.addAll(new Gson().fromJson(respose, ZhidingBean.class).getData().getList());
                adater_chooce.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.choseDays + Staticdata.static_userBean.getData().getUser_token(), activity, 0);
        LogUtils.LOG("ceshi", Urls.Baseurl + Urls.choseDays + Staticdata.static_userBean.getData().getUser_token(), "选择天数");
    }

    private void initlistenner() {
        mTextview_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                Utils.setAlpha((float) 1, activity);
            }
        });
        mTextview_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击确认

            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float) 1, activity);
            }
        });
        myGridView_chooce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adater_chooce.setSeclection(position);
                adater_chooce.notifyDataSetInvalidated();
            }
        });
    }

    class Adater_chooce extends BaseAdapter {
        List<String> mdata;
        LayoutInflater mInflater;
        int select;

        public Adater_chooce(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mdata = mDatas;
            mInflater = LayoutInflater.from(activity);
        }

        public void setSeclection(int select) {
            this.select = select;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.item_choocedays, null, false);
            TextView mTextview_days = convertView.findViewById(R.id.text_days);
            TextView mTextview_count = convertView.findViewById(R.id.text_needbiNum);
            if (select == position) {
                LogUtils.LOG("ceshi", "选择" + select, "位置");
            }

            if(type==1){
                mTextview_days.setText(mdata.get(position) + "天刷新");
            }else {
                mTextview_days.setText(mdata.get(position) + "天置顶");
            }


            return convertView;
        }
    }
}
