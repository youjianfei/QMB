package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.Interface.SendYanZhengmaSuccess;
import com.jingnuo.quanmb.activity.RegisterActivity;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.YanzhengmaBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/4/11.
 */

public class SendYanZhengMa {
    SendYanZhengmaSuccess  minterfaceSueecss;
    Button view;
    YanzhengmaBean yanzhengmaBean;

    public SendYanZhengMa(SendYanZhengmaSuccess minterfaceSueecss,Button view) {
        this.minterfaceSueecss = minterfaceSueecss;
        this.view=view;
    }

    public void sendyanzhengma(Activity activity, Map map){
        LogUtils.LOG("ceshi","发送+"+Urls.Baseurl+Urls.sendzhuceyanzhengma,"发送验证码位置");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi","发送+"+respose,"发送验证码位置");
                yanzhengmaBean=new Gson().fromJson(respose,YanzhengmaBean.class);
                int status = yanzhengmaBean.getCode();
                String msg = yanzhengmaBean.getMessage();
                if (status == 1) {
                    String registed=yanzhengmaBean.getData().getRegisted();
                    if(registed.equals("00")){
                        minterfaceSueecss.onSuccesses(msg+"00");
                    }else {
                        minterfaceSueecss.onSuccesses(msg);
                    }

                    //验证码倒计时
                    timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            mhandler.sendEmptyMessage(0);
                        }
                    };
                    timer.schedule(timerTask, 0, 1000);
                    view.setEnabled(false);

                } else {
                    minterfaceSueecss.onSuccesses(msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.sendzhuceyanzhengma,activity,1,map);




    }
    //验证码倒计时
    int time = 60;
    Timer timer;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    view.setText( time + " s");
                    time--;
                    if (time == 0) {
                        timer.cancel();
                        timer=null;
                        view.setText("获取验证码");
                        view.setEnabled(true);
                        time = 60;
                    }
                    break;
            }
        }


    };

}
