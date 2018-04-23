package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.TimePicker;


import com.jingnuo.quanmb.Interface.InterfaceDate_select;
import com.jingnuo.quanmb.utils.LogUtils;

import java.util.Calendar;

/**
 * Created by PC on 2017/3/17.
 */

public class DataTime_select {

    InterfaceDate_select mInterfaceDare;

    DatePickerDialog mDatePicker;
    TimePickerDialog mTimePicker;
    Calendar month = Calendar.getInstance();
    int YEAR,MONTH,DAY;
    String stardata;
    String starclock;
    String startime;
    private  static  DataTime_select dataTime_select;
    private Context context;
    public DataTime_select(Context context, InterfaceDate_select mInterfaceDare){
        this.context=context;
        this.mInterfaceDare=mInterfaceDare;
    }


    public  void timeSelect(final Activity activity){//时间选择器  先选择日期，在选择时间  0:开始；1:结束
        YEAR=month.get(Calendar.YEAR);
        MONTH=month.get(Calendar.MONTH);
        DAY=month.get(Calendar.DAY_OF_MONTH);
        mDatePicker=new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                    stardata= year+"-"+month+"-"+dayOfMonth;
            }
        },YEAR,MONTH,DAY);
        mDatePicker.show();
        mDatePicker.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                mTimePicker=new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            String h= hourOfDay>9? hourOfDay+"":"0"+hourOfDay;
                            String m= minute>9? minute+"":"0"+minute;
                            starclock=h+":"+m+":00";
                            startime=stardata+" "+starclock;
                        LogUtils.LOG("ceshi",startime+"","时间选择器class");
                        mInterfaceDare.onResult(startime);

                    }
                },10,00,true);

                mTimePicker.show();


            }
        });

    }

}
