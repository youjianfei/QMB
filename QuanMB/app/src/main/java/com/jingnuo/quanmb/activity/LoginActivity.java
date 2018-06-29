package com.jingnuo.quanmb.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.fargment.Fragment_acountLogin;
import com.jingnuo.quanmb.fargment.Fragment_phone_login;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.InstalltionId;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;

public class LoginActivity extends BaseActivityother {

    TabItem mtabitem_accont,mtabitem_phone;
    TabLayout mTabLayout_login;

    Fragment_acountLogin mFragment_acount;
    Fragment_phone_login mFragment_phone;

    FragmentManager fragmetnmanager;
    FragmentTransaction transaction;

    TextView mTextview_register;

    ImageView mImageview_wxlogin,mImage_qqlogin,mImage_xlLogin;

    private UMShareAPI mShareAPI;//第三方登录登录

    //数据



    @Override
    public int setLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void setData() {
        Staticdata.UUID = InstalltionId.id(this);//第一次运行生成一个id

    }

    @Override
    protected void initData() {
        mShareAPI = UMShareAPI.get(this);
        mFragment_acount=new Fragment_acountLogin();
        fragmetnmanager=getFragmentManager();
        transaction=fragmetnmanager.beginTransaction();
        transaction.add(R.id.framelayout_login,mFragment_acount).commit();

    }

    @Override
    protected void initListener() {
        mImage_qqlogin.setOnClickListener(this);
        mImage_xlLogin.setOnClickListener(this);
        mImageview_wxlogin.setOnClickListener(this);
        mTextview_register.setOnClickListener(this);
        mTabLayout_login.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.LOG("ceshi","选中tab"+tab.getPosition(),"loginactivity");
                transaction = fragmetnmanager.beginTransaction();
                hideFragments(transaction);//隐藏所有Fragment,需要哪个显示哪一个
                int i=tab.getPosition();
                switch (i){
                    case 0:
                        if(mFragment_acount==null){
                            mFragment_acount=new Fragment_acountLogin();
                            transaction.add(R.id.framelayout_login,mFragment_acount).commit();
                        }else{
                            transaction.show(mFragment_acount).commit();
                        }

                        break;
                    case 1:
                        if(mFragment_phone==null){
                            mFragment_phone=new Fragment_phone_login();
                            transaction.add(R.id.framelayout_login,mFragment_phone).commit();
                        }else{
                            transaction.show(mFragment_phone).commit();
                        }
                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtils.LOG("ceshi","未选中tab","loginactivity");

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtils.LOG("ceshi","再次选中tab","loginactivity");

            }
        });

    }

    @Override
    protected void initView() {
        mTextview_register=findViewById(R.id.text_register);
        mTabLayout_login=findViewById(R.id.tablayout_login);
        mtabitem_accont=findViewById(R.id.tab_account);
        mtabitem_phone=findViewById(R.id.tab_phone);
        mImageview_wxlogin=findViewById(R.id.image_wxLogin);
        mImage_qqlogin=findViewById(R.id.image_qqLogin);
        mImage_xlLogin=findViewById(R.id.image_xlLogin);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.text_register:
                Intent intent_register=new Intent(this,RegisterActivity.class);
                startActivity(intent_register);
                break;
            case R.id.image_xlLogin://新浪登录
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.SINA, umAuthListener);
                break;
            case R.id.image_qqLogin://QQ登录
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.image_wxLogin://微信登录
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;

        }

    }

    /**
     * 第三方登录回调监听
     * @param transaction
     */
    private UMAuthListener umAuthListener=new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

            LogUtils.LOG("ceshi", "三方开始登录:" +share_media,"三方登录回调");
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {


            switch (share_media){
                case WEIXIN:
//                    LogUtils.LOG("ceshi", "微信登陆成功" + map.toString(),"三方登录回调");
                     Staticdata.map_wechat=new HashMap();
                    Staticdata.map_wechat.put("unionid",map.get("unionid"));
                    Staticdata.map_wechat.put("uuid",Staticdata.UUID);
                    Staticdata.map_wechat.put("nick_name",map.get("name"));
                    Staticdata.map_wechat.put("Jpush_id", Staticdata.JpushID);
                     if (map.get("gender").equals("男")){
                         Staticdata.map_wechat.put("sex","0");
                     }else {
                         Staticdata.map_wechat.put("sex","1");

                     }
                    wechatLogin(Staticdata.map_wechat);

                    break;
                case SINA:

                    break;
                case QQ:

                    break;
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            LogUtils.LOG("ceshi", "登录方法:" +share_media,"三方登录回调");
            LogUtils.LOG("ceshi", "登陆失败" + throwable.getMessage(),"三方登录回调");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            LogUtils.LOG("ceshi", "登陆取消" + share_media,"三方登录回调");
        }
    };
    void wechatLogin(Map map){
        LogUtils.LOG("ceshi", "微信map" + map.toString(),"三方登录回调");
        LogUtils.LOG("ceshi", "微信mapURL" +Urls.Baseurl+Urls.wechatlogin,"三方登录回调");

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", "微信登录结果" + respose,"三方登录回调");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    Staticdata. static_userBean=new Gson().fromJson(respose,UserBean.class);
                    isLogin = true;
                    Intent intent_login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent_login);
                    ToastUtils.showToast(LoginActivity.this,msg);
                    finish();
                }else {
                    Intent intent_wechat = new Intent(LoginActivity.this, ThreeRegisterActivity.class);
                    startActivity(intent_wechat);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.wechatlogin,LoginActivity.this,1,map);

    }


    private void hideFragments(FragmentTransaction transaction) {//隐藏Fragment,以便点击时展映相应的Fragment
        if (mFragment_acount != null) {
            transaction.hide(mFragment_acount);
        }
        if (mFragment_phone != null) {
            transaction.hide(mFragment_phone);
        }

    }
    @Override
    public void onBackPressed() {
        LogUtils.LOG("ceshi1","点击返回","~~~~login");
        Intent intent_main=new Intent(this,MainActivity.class);
        startActivity(intent_main);
    }

}
