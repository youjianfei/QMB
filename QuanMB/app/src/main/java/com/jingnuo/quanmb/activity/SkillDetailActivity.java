package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.class_.Popwindow_lookpic;
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
    TextView mTextview_title;
    TextView mTextview_issuetime;
    TextView mTextview_peoplelook;
    TextView mTextview_skilltype;
    TextView mTextview_name;
    TextView mTextview_shopaddress;
    TextView mTextview_content;
    TextView mTextview_shopname;
    LinearLayout mLinearlayout_phonenumber;

    ImageView mImageview_skill1,mImageview_skill2,mImageview_skill3;

    //对象
    SkillsdetailsBean   mSkilldetailsbean;
    PermissionHelper mPermission;//动态申请权限
    Popwindow_lookpic popwindow_lookpic;






    //数据
    private final int MAX_LINE_COUNT = 3;
    private final int STATE_UNKNOW = -1;
    private final int STATE_NOT_OVERFLOW = 1; //文本行数不超过限定行数
    private final int STATE_COLLAPSED = 2; //文本行数超过限定行数,处于折叠状态
    private final int STATE_EXPANDED = 3; //文本行数超过限定行数,被点击全文展开

    String image_url="";
    String image_url1="";
    String image_url2="";
    String image_url3="";

    String id="";
    String role="";//身份




    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void setData() {
        popwindow_lookpic=new Popwindow_lookpic(this);
    }
    @Override
    protected void initView() {
        mTextview_more=findViewById(R.id.text_more);
        mTextview_title=findViewById(R.id.text_shopskilltitel);
        mTextview_issuetime=findViewById(R.id.text_skillIssueTime);
        mTextview_peoplelook=findViewById(R.id.text_peoplelook);
        mTextview_skilltype=findViewById(R.id.text_skilltype);
        mTextview_name=findViewById(R.id.text_shoppeoplename);
        mTextview_shopaddress=findViewById(R.id.text_shopaddress);
        mTextview_content=findViewById(R.id.text_servicedetail);
        mTextview_shopname=findViewById(R.id.text_shopname);
        mLinearlayout_phonenumber=findViewById(R.id.linearlayout_phonenumber);
        mImageview_skill1=findViewById(R.id.iamge_skillPIC1);
        mImageview_skill2=findViewById(R.id.iamge_skillPIC2);
        mImageview_skill3=findViewById(R.id.iamge_skillPIC3);


    }
    @Override
    protected void initData() {
        mPermission= new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        Intent intent=getIntent();
        id= intent.getStringExtra("id");
        role= intent.getStringExtra("role");
        LogUtils.LOG("ceshi",role+"行","role");

        request(id,role);
    }

    @Override
    protected void initListener() {
//        mImageview_skill1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popwindow_lookpic.showPopwindow(image_url1);
//            }
//        });
//        mImageview_skill2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popwindow_lookpic.showPopwindow(image_url2);
//            }
//        });
//        mImageview_skill3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popwindow_lookpic.showPopwindow(image_url3);
//            }
//        });
        mTextview_content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mTextview_content.getViewTreeObserver().removeOnPreDrawListener(this);
                if(mTextview_content.getLineCount()>MAX_LINE_COUNT){
                    mTextview_content.setMaxLines(MAX_LINE_COUNT);
                    mTextview_more.setVisibility(View.VISIBLE);
                    mTextview_more.setText("展开全文");
                    LogUtils.LOG("ceshi",mTextview_content.getLineCount()+"行","服务详情");
                }else {
                    mTextview_more.setVisibility(View.GONE);
                    LogUtils.LOG("ceshi",mTextview_content.getLineCount()+"行","服务详情");
                }

                return true;
            }
        });
        mTextview_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTextview_more.getText().equals("展开全文")){
                    mTextview_content.setMaxLines(Integer.MAX_VALUE);
                    mTextview_more.setText("收起");
                }else {
                    mTextview_content.setMaxLines(MAX_LINE_COUNT);
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
    void setImage(String  image){
        if(image==null||image.equals("")){

        }else {
            String []images=image.split(",");
            int len=images.length;
            LogUtils.LOG("ceshi","图片的个数"+images.length,"SkillDetailActivity分隔图片");
            switch (len){
                case 1:
                    LogUtils.LOG("ceshi","图片的地址"+images[0],"SkillDetailActivity分隔图片");
                    image_url1=images[0];
                    mImageview_skill1.setVisibility(View.VISIBLE);
                    Glide.with(SkillDetailActivity.this).load(image_url1).into(mImageview_skill1);
                    break;
                case 2:
                    image_url1=images[0];
                    image_url2=images[1];

                    mImageview_skill1.setVisibility(View.VISIBLE);
                    mImageview_skill2.setVisibility(View.VISIBLE);
                    Glide.with(SkillDetailActivity.this).load(images[0]).into(mImageview_skill1);
                    Glide.with(SkillDetailActivity.this).load(images[1]).into(mImageview_skill2);
                    break;
                case 3:
                    image_url1=images[0];
                    image_url2=images[1];
                    image_url3=images[2];
                    mImageview_skill1.setVisibility(View.VISIBLE);
                    mImageview_skill2.setVisibility(View.VISIBLE);
                    mImageview_skill3.setVisibility(View.VISIBLE);
                    Glide.with(SkillDetailActivity.this).load(images[0]).into(mImageview_skill1);
                    Glide.with(SkillDetailActivity.this).load(images[1]).into(mImageview_skill2);
                    Glide.with(SkillDetailActivity.this).load(images[2]).into(mImageview_skill3);
                    break;
            }


        }

    }

    void request(String id ,String role){
        String URL="";
        if(role.equals("1")){
            URL=  Urls.Baseurl+Urls.helperSkilldetail+"?id="+id;
        }else {
            URL=  Urls.Baseurl+Urls.shopkilldetail+"?id="+id;
        }
        LogUtils.LOG("ceshi","专业详情网址："+URL,"服务详情");
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
                image_url=mSkilldetailsbean.getData().getDetail().getImg_url();
                setImage(image_url);
            }

            @Override
            public void onError(int error) {

            }
        }).Http(URL,SkillDetailActivity.this,0);



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
