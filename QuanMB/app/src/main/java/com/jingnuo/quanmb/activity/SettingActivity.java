package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.DataCleanManager;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;

import java.io.File;

import static com.jingnuo.quanmb.utils.Utils.deleteAllFiles;

public class SettingActivity extends BaseActivityother {
    //控件

    Button mButton_logout;
    TextView mTextview_cleancache;
    TextView mTextview_suggest;
    TextView mTextview_textview_cleancacheSize;
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

    }

    @Override
    protected void initListener() {
        mButton_logout.setOnClickListener(this);
        mTextview_cleancache.setOnClickListener(this);
        mTextview_suggest.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mButton_logout=findViewById(R.id.button_logout);
        mTextview_cleancache=findViewById(R.id.textview_cleancache);
        mTextview_suggest=findViewById(R.id.textview_suggest);
        mTextview_textview_cleancacheSize=findViewById(R.id.textview_cleancacheSize);
    }
    File root;//分享的图片要放的文件夹
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

            case R.id.button_logout :
//                SharedPreferencesUtils.putString(SettingActivity.this,"QMB","password","");
//                Staticdata.isLogin=false;
//                Intent intent_logout=new Intent(SettingActivity.this,LoginActivity.class);
//                startActivity(intent_logout);
//                finish();
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
            case R.id.textview_suggest:
                Intent intend_suggest=new Intent(this,SuggestActivity.class);
                startActivity(intend_suggest);
                break;

        }
    }
}
