package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.fargment.Fragment_message;
import com.jingnuo.quanmb.fargment.Fragment_person;
import com.jingnuo.quanmb.fargment.Fragment_square;
import com.jingnuo.quanmb.fargment.Fragment_still;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;

import static com.jingnuo.quanmb.data.Staticdata.isLogin;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //布局
    Fragment_square mFragment_square;
    Fragment_still mFragment_stilll;
    Fragment_message mFragment_menssage;
    Fragment_person mFragment_person;

    FragmentManager fragmetnmanager;
    FragmentTransaction transaction;
    //控件
    private RelativeLayout mRelativeLayout_square, mRelativeLayout_still,  mRelativeLayout_message, mRelativeLayout_person;
    ImageView mImage_release;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.statebar), 0);//状态栏颜色
        initview();
        initdata();
        initlinstenner();
        setview();
        setdata();

    }

    public void initview() {
        mRelativeLayout_square = findViewById(R.id.relative_square);
        mRelativeLayout_still = findViewById(R.id.relative_still);
        mRelativeLayout_message = findViewById(R.id.relative_message);
        mRelativeLayout_person = findViewById(R.id.relative_person);
        mImage_release=findViewById(R.id.image_release);
    }

    public void initdata() {
        mFragment_square = new Fragment_square();
        fragmetnmanager = getSupportFragmentManager();
        transaction = fragmetnmanager.beginTransaction();
    }

    public void initlinstenner() {
        mRelativeLayout_square.setOnClickListener(this);
        mRelativeLayout_still.setOnClickListener(this);
        mImage_release.setOnClickListener(this);
        mRelativeLayout_message.setOnClickListener(this);
        mRelativeLayout_person.setOnClickListener(this);
    }

    public void setview() {
        transaction.add(R.id.framelayout_main, mFragment_square).commit();
        ChangeBottomButton(mRelativeLayout_square);//设置帮帮广场为选中状态

    }

    public void setdata() {


    }


    @Override
    public void onClick(View view) {
        transaction = fragmetnmanager.beginTransaction();
        hideFragments(transaction);//隐藏所有Fragment,需要哪个显示哪一个

        switch (view.getId()) {
            case R.id.relative_square:
                LogUtils.LOG("ceshi", "帮帮广场被点击", "mainactivity");
                ChangeBottomButton(mRelativeLayout_square);
                if (mFragment_square == null) {
                    mFragment_square = new Fragment_square();
                    transaction.add(R.id.framelayout_main, mFragment_square).commit();
                } else {
                    transaction.show(mFragment_square).commit();
                }

                break;
            case R.id.relative_still:
                LogUtils.LOG("ceshi", "技能被点击", "mainactivity");
                ChangeBottomButton(mRelativeLayout_still);
                if (mFragment_stilll == null) {
                    mFragment_stilll = new Fragment_still();
                    transaction.add(R.id.framelayout_main, mFragment_stilll).commit();
                } else {
                    transaction.show(mFragment_stilll).commit();
                }

                break;
            case R.id.image_release:
                if(isLogin){
                    Intent intent_issue=new Intent(this,IssueTaskActivity.class);
                    startActivity(intent_issue);
                }else{
                    Intent intent_login=new Intent(this,LoginActivity.class);
                    startActivity(intent_login);
                }

                break;
            case R.id.relative_message:
                LogUtils.LOG("ceshi", "消息被点击", "mainactivity");
                ChangeBottomButton(mRelativeLayout_message);
                if (mFragment_menssage == null) {
                    mFragment_menssage = new Fragment_message();
                    transaction.add(R.id.framelayout_main, mFragment_menssage).commit();
                } else {
                    transaction.show(mFragment_menssage).commit();
                }
                break;
            case R.id.relative_person:
                LogUtils.LOG("ceshi", "个人中心被点击"+isLogin, "mainactivity");
                if(isLogin){
                    ChangeBottomButton(mRelativeLayout_person);
                    if (mFragment_person == null) {
                        mFragment_person = new Fragment_person();
                        transaction.add(R.id.framelayout_main, mFragment_person).commit();
                    } else {
                        transaction.show(mFragment_person).commit();
                    }
                }else {
                    Intent intent_login=new Intent(this,LoginActivity.class);
                    startActivity(intent_login);
                }



                break;
        }

    }

    private void hideFragments(FragmentTransaction transaction) {//隐藏Fragment,以便点击时展映相应的Fragment
        if (mFragment_square != null) {
            transaction.hide(mFragment_square);
        }
        if (mFragment_stilll != null) {
            transaction.hide(mFragment_stilll);
        }
        if (mFragment_menssage != null) {
            transaction.hide(mFragment_menssage);
        }
        if (mFragment_person != null) {
            transaction.hide(mFragment_person);
        }
    }
    private void ChangeBottomButton(RelativeLayout rl) {//控制底部按钮颜色的变化
        mRelativeLayout_square.getChildAt(0).setSelected(false);
        mRelativeLayout_square.getChildAt(1).setSelected(false);
        mRelativeLayout_still.getChildAt(0).setSelected(false);
        mRelativeLayout_still.getChildAt(1).setSelected(false);
        mRelativeLayout_message.getChildAt(0).setSelected(false);
        mRelativeLayout_message.getChildAt(1).setSelected(false);
        mRelativeLayout_person.getChildAt(0).setSelected(false);
        mRelativeLayout_person.getChildAt(1).setSelected(false);
        rl.getChildAt(0).setSelected(true);
        rl.getChildAt(1).setSelected(true);
    }


    /**
     * 再点一次退出
     */
    private long mLastTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastTime > 2000) {
            // 两次返回时间超出两秒
            Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
            mLastTime = System.currentTimeMillis();
        } else {
//                    if(fragment_classification!=null){
//                        fragment_classification=null;
//                    }
            // 两次返回时间小于两秒，可以退出
            finish();
        }
    }
}


