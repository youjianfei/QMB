package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.app.ProgressDialog;

import com.kaopiz.kprogresshud.KProgressHUD;

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

    public static void showProgress(KProgressHUD mKProgressHUD) {
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }


}
