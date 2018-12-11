package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.class_.ShareGoodWeb;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.DataCleanManager;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.util.Map;

import io.rong.imkit.RongIM;

import static com.jingnuo.quanmb.utils.Utils.deleteAllFiles;

public class SettingActivity extends BaseActivityother {
    private UMShareAPI mShareAPI;
    //控件

    TextView textview_maintitle;
    TextView mTextview_cleancache;
    TextView mTextview_textview_cleancacheSize;
    TextView mTextview_share;
    TextView textview_aboutus;
    TextView textview_logout;


    //对象

    ShareGoodWeb shareClass;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void setData() {
        try {
            mTextview_textview_cleancacheSize.setText( DataCleanManager. getTotalCacheSize(SettingActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        shareClass = new ShareGoodWeb(this);
    }

    @Override
    protected void initListener() {
        mTextview_cleancache.setOnClickListener(this);
        mTextview_share.setOnClickListener(this);
        textview_aboutus.setOnClickListener(this);
        textview_logout.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        textview_maintitle=findViewById(R.id.textview_maintitle);
        textview_maintitle.setText("设置");
        mTextview_cleancache=findViewById(R.id.textview_cleancache);
        mTextview_textview_cleancacheSize=findViewById(R.id.textview_cleancacheSize);
        mTextview_share=findViewById(R.id.textview_shareAPP);
        textview_aboutus=findViewById(R.id.textview_aboutus);
        textview_logout=findViewById(R.id.textview_logout);
    }
    File root;//分享的图片要放的文件夹
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case  R.id.textview_logout:
                logout();
                SharedPreferencesUtils.putString(SettingActivity.this, "QMB", "password", "");
                SharedPreferencesUtils.putString(SettingActivity.this, "QMB", "usertoken", "");
                Staticdata.isLogin = false;
                Staticdata.static_userBean.setData(null);//用户信息清空
                RongIM.getInstance().disconnect();
                Intent intent_logout = new Intent(SettingActivity.this, LoginActivityPhone.class);
                startActivity(intent_logout);
               finish();


                break;
            case R.id.textview_aboutus :
                Intent intent=new Intent(SettingActivity.this,AboutUsActivity.class);
                startActivity(intent);
                break;

            case R.id.textview_cleancache:

                root = new File(Environment.getExternalStorageDirectory() + "/picyasuo/");//找到根目录下DICM文件夹
                if (!root.exists()) {
                    root.mkdirs();
                }
                deleteAllFiles(root);//删除上次分享的残余图片
                try {
                    DataCleanManager.clearAllCache(SettingActivity.this);
                    ToastUtils.showToast(this,"清除缓存成功");
                    mTextview_textview_cleancacheSize.setText( DataCleanManager. getTotalCacheSize(SettingActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.textview_shareAPP:
                if(Utils.isWxInstall(this)){
                 shareClass.shareapp();
                }else {
                    ToastUtils.showToast(this,"未安装微信");
                }
                break;
        }
    }
    public void logout() {

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "退出登录");
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.logout + Staticdata.static_userBean.getData().getUser_token(), SettingActivity.this, 0);
        //登出注销微信授权
        mShareAPI = UMShareAPI.get(SettingActivity.this);
        mShareAPI.deleteOauth(SettingActivity.this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }
}
