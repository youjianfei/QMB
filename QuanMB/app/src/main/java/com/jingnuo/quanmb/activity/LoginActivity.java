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
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.fargment.Fragment_acountLogin;
import com.jingnuo.quanmb.fargment.Fragment_phone_login;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;

public class LoginActivity extends BaseActivityother {
    TabItem mtabitem_accont,mtabitem_phone;
    TabLayout mTabLayout_login;

    Fragment_acountLogin mFragment_acount;
    Fragment_phone_login mFragment_phone;

    FragmentManager fragmetnmanager;
    FragmentTransaction transaction;

    TextView mTextview_register;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

        mFragment_acount=new Fragment_acountLogin();
        fragmetnmanager=getFragmentManager();
        transaction=fragmetnmanager.beginTransaction();
        transaction.add(R.id.framelayout_login,mFragment_acount).commit();
    }

    @Override
    protected void initListener() {
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
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.text_register:
                Intent intent_register=new Intent(this,RegisterActivity.class);
                startActivity(intent_register);
                break;
        }


    }
    private void hideFragments(FragmentTransaction transaction) {//隐藏Fragment,以便点击时展映相应的Fragment
        if (mFragment_acount != null) {
            transaction.hide(mFragment_acount);
        }
        if (mFragment_phone != null) {
            transaction.hide(mFragment_phone);
        }

    }
}
