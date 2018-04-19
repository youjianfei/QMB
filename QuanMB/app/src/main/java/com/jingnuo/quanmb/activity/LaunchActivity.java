package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_Issue;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.InstalltionId;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.PasswordJiami;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.jingnuo.quanmb.data.Staticdata.ScreenWidth;
import static com.jingnuo.quanmb.data.Staticdata.UUID;
import static com.jingnuo.quanmb.data.Staticdata.Userphonenumber;
import static com.jingnuo.quanmb.data.Staticdata.isLogin;
import static com.jingnuo.quanmb.data.Staticdata.token;


public class LaunchActivity extends BaseActivityother {
    //布局
    LinearLayout mLinearlauout_root;
    //数据
    int Screenhight = 0;
    int Screenwidth = 0;

    String phonenumber="";
    String password="";
    String publicEncryptedResult = "";//加密的密码


    UserBean userBean;


    @Override
    public int setLayoutResID() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_launch;
    }

    @Override
    protected void setData() {
        phonenumber= SharedPreferencesUtils.getString(LaunchActivity.this,"QMB","phonenumber");
        password= SharedPreferencesUtils.getString(LaunchActivity.this,"QMB","password");

        if(phonenumber.equals("")||password.equals("")){
            mTimer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    mhandler.sendEmptyMessage(0);
                }
            };
            mTimer.schedule(timerTask, 1500);
        }else {
            Map map_autoLogind=new HashMap();
            publicEncryptedResult= PasswordJiami.passwordjiami(password);//对密码加密
            LogUtils.LOG("ceshi", publicEncryptedResult + "1111111111", "fragment_account");
            Userphonenumber=phonenumber;//将电话号设为全局变量
            map_autoLogind.put("username", phonenumber);
            map_autoLogind.put("password", publicEncryptedResult);
            map_autoLogind.put("uuid", UUID);
            //登陆成功后 设置全局变量islogin为 true
            request(map_autoLogind);

        }

        mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mhandler.sendEmptyMessage(0);
            }
        };
        mTimer.schedule(timerTask, 1500);
    }

    @Override
    protected void initData() {
        UUID = InstalltionId.id(this);//第一次运行生成一个id

        Screenhight = SizeUtils.getScreenHeightPx(this);
        Screenwidth = SizeUtils.getScreenWidthPx(this);
        Staticdata.ScreenHight = Screenhight;
        ScreenWidth = Screenwidth;
        ImageView image=new ImageView(this);
        image.setBackgroundResource(R.mipmap.launchpic);
        LinearLayout.LayoutParams mLayoutparams=new LinearLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth*0.6));
        image.setLayoutParams(mLayoutparams);
        mLinearlauout_root.addView(image);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mLinearlauout_root = findViewById(R.id.Linearlayout_launch);

    }

    void request(Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int  status=0;
                String msg="";
                try {
                    JSONObject object=new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){//登录成功
                    userBean=new Gson().fromJson(respose,UserBean.class);
                    Staticdata.static_userBean=userBean;
                    token=userBean.getData().getUser_token();
                    LogUtils.LOG("ceshi", userBean.getData().getUser_token() , "fragment_account");
                    isLogin = true;


                    mTimer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            mhandler.sendEmptyMessage(0);
                        }
                    };
                    mTimer.schedule(timerTask, 1000);
                }else {
                    Intent intent_ = new Intent(LaunchActivity.this, LoginActivity.class);
                    startActivity(intent_);
                }
            }

            @Override
            public void onError(int error) {
                Intent intent_ = new Intent(LaunchActivity.this, LoginActivity.class);
                startActivity(intent_);

            }
        }).postHttp(Urls.Baseurl+Urls.login, LaunchActivity.this, 1, map);



    }

    Timer mTimer;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mTimer.cancel();
//                    if (isFirstLogin) {
//                        Intent intent = new Intent(LaunchActivity.this, FirstLoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
                    Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    break;
            }
        }


    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
