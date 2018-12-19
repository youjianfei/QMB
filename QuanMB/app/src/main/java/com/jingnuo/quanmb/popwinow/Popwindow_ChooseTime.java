package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.InterfaceDate_select;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.customview.EasyPickerView;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/4/12.
 */

public class Popwindow_ChooseTime {
    View conView;
    private Activity activity;
    PopupWindow mPopupWindow;

    //控件
    TextView mTextview_one;
    TextView mText_close;
    TextView text_submit;
    EasyPickerView easyPickerView_left;
    EasyPickerView easyPickerView_right;
    //对象

    InterfaceDate_select mInterface;
    //数据
    ArrayList<String> listdata_one;
    ArrayList<String> listdata_two;

    public Popwindow_ChooseTime(Activity activity, InterfaceDate_select mInterface) {
        this.activity = activity;
        this.mInterface = mInterface;
        listdata_one = new ArrayList<>();
        listdata_two = new ArrayList<>();
    }

    public void showPopwindow() {
        //初始化popwindow；
        conView = LayoutInflater.from(activity).inflate(R.layout.popwindow_chosetime, null, false);
        mPopupWindow = new PopupWindow(conView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popskilltype_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        mText_close = conView.findViewById(R.id.text_cancle);
        text_submit = conView.findViewById(R.id.text_submit);
        mTextview_one = conView.findViewById(R.id.text_one);
        easyPickerView_left = conView.findViewById(R.id.easeview_day);
        easyPickerView_right = conView.findViewById(R.id.easeview_time);
        Utils.setAlpha((float) 0.3, activity);
        initdata();
        initlistennr();
    }
    Calendar calendar;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    private void initdata() {
        //获取当前时间
        easyPickerView_left.setDataList(listdata_one);
         calendar = Calendar.getInstance();
         year   = calendar.get(Calendar.YEAR);
         month  = calendar.get(Calendar.MONTH)+1;
         day    = calendar.get(Calendar.DAY_OF_MONTH);
         hour   = calendar.get(Calendar.HOUR_OF_DAY);
         minute = calendar.get(Calendar.MINUTE);

        if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
            for(int i=0;i<31;i++){
                if(day>31){
                    day=1;
                    month=month+1;
                    if(month>12){
                        month=1;
                    }
                }
                String monthh=month+"";
                if(monthh.length()==1){
                    monthh="0"+month;
                }
                String dayy=day+"";
                if(dayy.length()==1){
                    dayy="0"+day;
                }
                    listdata_one.add(monthh+"月"+dayy+"日");
                day++;
            }
        }else if(month==4||month==6||month==9||month==11){
            for(int i=0;i<30;i++){
                if(day>30){
                    day=1;
                    month=month+1;
                    if(month>12){
                        month=1;
                    }
                }
                String monthh=month+"";
                if(monthh.length()==1){
                    monthh="0"+month;
                }
                String dayy=day+"";
                if(dayy.length()==1){
                    dayy="0"+day;
                }
                listdata_one.add(monthh+"月"+dayy+"日");
                day++;
            }
        }else {
            if(year%4==0&&year%100!=0||year%400==0){
                for(int i=0;i<29;i++){
                    if(day>30){
                        day=1;
                        month=month+1;
                        if(month>12){
                            month=1;
                        }
                    }
                    String monthh=month+"";
                    if(monthh.length()==1){
                        monthh="0"+month;
                    }
                    String dayy=day+"";
                    if(dayy.length()==1){
                        dayy="0"+day;
                    }
                    listdata_one.add(monthh+"月"+dayy+"日");
                    day++;
                }
            }else {
                for(int i=0;i<28;i++){
                    if(day>30){
                        day=1;
                        month=month+1;
                        if(month>12){
                            month=1;
                        }
                    }
                    String monthh=month+"";
                    if(monthh.length()==1){
                        monthh="0"+month;
                    }
                    String dayy=day+"";
                    if(dayy.length()==1){
                        dayy="0"+day;
                    }
                    listdata_one.add(monthh+"月"+dayy+"日");
                    day++;
                }
            }

        }
        listdata_two.add("08:30");listdata_two.add("09:00");
        listdata_two.add("09:30");listdata_two.add("10:00");
        listdata_two.add("10:30");listdata_two.add("11:00");
        listdata_two.add("11:30");listdata_two.add("12:00");
        listdata_two.add("12:30");listdata_two.add("13:00");
        listdata_two.add("13:30");listdata_two.add("14:00");
        listdata_two.add("14:30");listdata_two.add("15:00");
        listdata_two.add("15:30");listdata_two.add("16:00");
        listdata_two.add("16:30");listdata_two.add("17:00");
        listdata_two.add("17:30");listdata_two.add("18:00");
        listdata_two.add("18:30");listdata_two.add("19:00");
        listdata_two.add("19:30");listdata_two.add("20:00");
        listdata_two.add("20:30");listdata_two.add("21:00");
        listdata_two.add("21:30");listdata_two.add("22:00");
        listdata_two.add("22:30");listdata_two.add("23:00");
        listdata_two.add("23:30");listdata_two.add("00:00");
        listdata_two.add("00:30");listdata_two.add("01:00");
        listdata_two.add("01:30");listdata_two.add("02:00");
        listdata_two.add("02:30");listdata_two.add("03:00");
        listdata_two.add("03:30");listdata_two.add("04:00");
        listdata_two.add("04:30");listdata_two.add("05:00");
        listdata_two.add("05:30");listdata_two.add("06:00");
        listdata_two.add("06:30");listdata_two.add("07:00");
        listdata_two.add("07:30");listdata_two.add("08:00");

        easyPickerView_left.setDataList(listdata_one);
        easyPickerView_right.setDataList(listdata_two);


        LogUtils.LOG("ceshi","message"+month+day+hour+minute,"sdf");
    }


    private void initlistennr() {
//        easyPickerView_left.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged(int curIndex) {
////                mInterface.onResult(listdata_one.get(easyPickerView_left.getCurIndex())+listdata_two.get(easyPickerView_right.getCurIndex()));
//            }
//
//            @Override
//            public void onScrollFinished(int curIndex) {
////                LogUtils.LOG("ceshi","getCurIndex"+easyPickerView_left.getCurIndex(),"sdf");
////                mInterface.onResult(listdata_one.get(easyPickerView_left.getCurIndex())+listdata_two.get(easyPickerView_right.getCurIndex()));
//
//            }
//        });
//        easyPickerView_right.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged(int curIndex) {
////                mInterface.onResult(listdata_one.get(easyPickerView_left.getCurIndex())+listdata_two.get(easyPickerView_right.getCurIndex()));
//            }
//
//            @Override
//            public void onScrollFinished(int curIndex) {
////                mInterface.onResult(listdata_one.get(easyPickerView_left.getCurIndex())+listdata_two.get(easyPickerView_right.getCurIndex()));
//
//            }
//        });
        text_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.LOG("ceshi",Integer.parseInt(listdata_two.get(easyPickerView_right.getCurIndex()).substring(0,2))+".","墙砖");
                if(easyPickerView_left.getCurIndex()==0&&
               Integer.parseInt(listdata_two.get(easyPickerView_right.getCurIndex()).substring(0,2))<=hour){
                    ToastUtils.showToast(activity,"预约时间不得早于现在");
                    return;
                }


                mInterface.onResult(listdata_one.get(easyPickerView_left.getCurIndex())+listdata_two.get(easyPickerView_right.getCurIndex()));
                mPopupWindow.dismiss();
            }
        });
        mText_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {//点击一级菜单请求二级菜单
            @Override
            public void onDismiss() {
                Utils.setAlpha(1, activity);
            }
        });

    }


}
