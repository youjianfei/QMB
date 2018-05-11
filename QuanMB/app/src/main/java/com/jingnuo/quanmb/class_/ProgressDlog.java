package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.app.ProgressDialog;

public class ProgressDlog {
    ProgressDialog pd;
    Activity activity;

    public ProgressDlog(Activity activity ) {
        this.activity = activity;
    }

    public void  showPD( String  text){
        pd = new ProgressDialog(activity);
        pd.setMessage(text);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }
    public void cancelPD(){
        if (pd!=null&&pd.isShowing()){
            pd.dismiss();
        }

    }

}
