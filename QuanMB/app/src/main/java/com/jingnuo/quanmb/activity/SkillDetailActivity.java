package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.SkillsdetailsBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;


public class SkillDetailActivity extends BaseActivityother {
    //控件
    TextView mTextview_more;
    TextView mTextview_skillintroduction;
    TextView mTextview_title;
    TextView mTextview_issuetime;
    TextView mTextview_peoplelook;
    TextView mTextview_skilltype;
    TextView mTextview_name;
    TextView mTextview_shopaddress;
    TextView mTextview_content;
    TextView mTextview_shopname;
    LinearLayout mLinearlayout_phonenumber;

    //对象
    SkillsdetailsBean   mSkilldetailsbean;
    PermissionHelper mPermission;//动态申请权限





    //数据
    private final int MAX_LINE_COUNT = 3;
    private final int STATE_UNKNOW = -1;
    private final int STATE_NOT_OVERFLOW = 1; //文本行数不超过限定行数
    private final int STATE_COLLAPSED = 2; //文本行数超过限定行数,处于折叠状态
    private final int STATE_EXPANDED = 3; //文本行数超过限定行数,被点击全文展开

    String id="";




    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void setData() {

    }
    @Override
    protected void initView() {
        mTextview_more=findViewById(R.id.text_more);
        mTextview_skillintroduction=findViewById(R.id.text_servicedetail);
        mTextview_title=findViewById(R.id.text_shopskilltitel);
        mTextview_issuetime=findViewById(R.id.text_skillIssueTime);
        mTextview_peoplelook=findViewById(R.id.text_peoplelook);
        mTextview_skilltype=findViewById(R.id.text_skilltype);
        mTextview_name=findViewById(R.id.text_shoppeoplename);
        mTextview_shopaddress=findViewById(R.id.text_shopaddress);
        mTextview_content=findViewById(R.id.text_servicedetail);
        mTextview_shopname=findViewById(R.id.text_shopname);
        mLinearlayout_phonenumber=findViewById(R.id.linearlayout_phonenumber);

    }
    @Override
    protected void initData() {
        mPermission= new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        Intent intent=getIntent();
        id= intent.getStringExtra("id");
        request(id);
    }

    @Override
    protected void initListener() {
        mTextview_skillintroduction.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mTextview_skillintroduction.getViewTreeObserver().removeOnPreDrawListener(this);
                if(mTextview_skillintroduction.getLineCount()>MAX_LINE_COUNT){
                    mTextview_skillintroduction.setMaxLines(MAX_LINE_COUNT);
                    mTextview_more.setVisibility(View.VISIBLE);
                    mTextview_more.setText("展开全文");
                    LogUtils.LOG("ceshi",mTextview_skillintroduction.getLineCount()+"行","服务详情");
                }else {
                    mTextview_more.setVisibility(View.GONE);
                    LogUtils.LOG("ceshi",mTextview_skillintroduction.getLineCount()+"行","服务详情");
                }

                return true;
            }
        });
        mTextview_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTextview_more.getText().equals("展开全文")){
                    mTextview_skillintroduction.setMaxLines(Integer.MAX_VALUE);
                    mTextview_more.setText("收起");
                }else {
                    mTextview_skillintroduction.setMaxLines(MAX_LINE_COUNT);
                    mTextview_more.setText("展开全文");
                }
            }
        });
        mLinearlayout_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + mSkilldetailsbean.getData().getDetail().getMobile_no());
                intent.setData(data);

                if (ActivityCompat.checkSelfPermission(SkillDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

//                    ToastUtils.showToast(mContext,"拨打电话权限被你拒绝，请在手机设置中开启");
                    mPermission.request(new PermissionHelper.PermissionCallback() {
                        @Override
                        public void onPermissionGranted() {

                        }

                        @Override
                        public void onIndividualPermissionGranted(String[] grantedPermission) {

                        }

                        @Override
                        public void onPermissionDenied() {

                        }

                        @Override
                        public void onPermissionDeniedBySystem() {

                        }
                    });
                    return;
                }
                startActivity(intent);//调用具体方法
            }
        });

    }


    void request(String id){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"服务详情");
                mSkilldetailsbean=new Gson().fromJson(respose,SkillsdetailsBean.class);
                mTextview_title.setText(mSkilldetailsbean.getData().getDetail().getTitle());
                mTextview_issuetime.setText(Utils.getStrTime(mSkilldetailsbean.getData().getDetail().getRelease_date()+""));
                mTextview_skilltype.setText(mSkilldetailsbean.getData().getDetail().getSpecialty_name());
                mTextview_name.setText(mSkilldetailsbean.getData().getDetail().getContacts());
                mTextview_shopaddress.setText(mSkilldetailsbean.getData().getDetail().getRelease_address());
                mTextview_content.setText(mSkilldetailsbean.getData().getDetail().getDescription());
                mTextview_shopname.setText(mSkilldetailsbean.getData().getDetail().getBusiness_name());

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.Skilldetail+"?id="+id,SkillDetailActivity.this,0);



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
