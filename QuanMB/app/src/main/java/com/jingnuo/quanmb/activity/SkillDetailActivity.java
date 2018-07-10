package com.jingnuo.quanmb.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_skillsdetails;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.popwinow.Popwindow_lookpic;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.SkillsdetailsBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SkillDetailActivity extends BaseActivityother {
    //控件
    TextView mTextview_more;
    TextView mTextview_text_peoplelook;//浏览量
    TextView mTextview_title;
    TextView mTextview_issuetime;
    TextView mTextview_peoplelook;
    TextView mTextview_skilltype;
    TextView mTextview_name;
    TextView mTextview_shopaddress;
    TextView mTextview_content;
    TextView mTextview_text_idcard;//认证身份
    TextView mTextview_shopname;
    TextView mTextview_shopIN_time;//加入时长
    TextView mTextview_relseaCount;//发布帖子的数量
    LinearLayout mLinearlayout_phonenumber;
    LinearLayout mLinearlayout_collection;
    LinearLayout linearlayout_bottom;
    MyGridView imageGridview;
    ImageView mImageView_Suggest;
    ImageView mImageview_collect;
    ImageView mImageview_shopPic;


    //对象
    SkillsdetailsBean mSkilldetailsbean;
    PermissionHelper mPermission;//动态申请权限
    Popwindow_lookpic popwindow_lookpic;
    Adapter_Gridviewpic_skillsdetails adapter_gridviewpic;


    //数据
    private final int MAX_LINE_COUNT = 3;
    private final int STATE_UNKNOW = -1;
    private final int STATE_NOT_OVERFLOW = 1; //文本行数不超过限定行数
    private final int STATE_COLLAPSED = 2; //文本行数超过限定行数,处于折叠状态
    private final int STATE_EXPANDED = 3; //文本行数超过限定行数,被点击全文展开

    String image_url = "";
    List<String> imageview_urllist;

    String id = "";
    String role = "";//身份

    int collrctID = 1;  //1 添加收藏 2取消收藏


    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void setData() {
        imageview_urllist = new ArrayList<>();
        adapter_gridviewpic = new Adapter_Gridviewpic_skillsdetails(imageview_urllist, this);
        imageGridview.setAdapter(adapter_gridviewpic);
        popwindow_lookpic = new Popwindow_lookpic(this);
    }

    @Override
    protected void initView() {
        mTextview_text_peoplelook = findViewById(R.id.text_peoplelook);
        mTextview_text_idcard = findViewById(R.id.text_idcard);
        mTextview_shopIN_time = findViewById(R.id.text_shopin_time);
        mTextview_relseaCount = findViewById(R.id.text_releaseCounts);
        mTextview_more = findViewById(R.id.text_more);
        mTextview_title = findViewById(R.id.text_shopskilltitel);
        mTextview_issuetime = findViewById(R.id.text_skillIssueTime);
        mTextview_peoplelook = findViewById(R.id.text_peoplelook);
        mTextview_skilltype = findViewById(R.id.text_skilltype);
        mTextview_name = findViewById(R.id.text_shoppeoplename);
        mTextview_shopaddress = findViewById(R.id.text_shopaddress);
        mTextview_content = findViewById(R.id.text_servicedetail);
        mTextview_shopname = findViewById(R.id.text_shopname);
        mLinearlayout_phonenumber = findViewById(R.id.linearlayout_phonenumber);
        linearlayout_bottom = findViewById(R.id.linearlayout_bottom);
        imageGridview = findViewById(R.id.GridView_PIC);
        mImageView_Suggest = findViewById(R.id.image_complain);
        mImageview_shopPic = findViewById(R.id.image_shoppic);
        mLinearlayout_collection = findViewById(R.id.linearlayout_collection);
        mImageview_collect = findViewById(R.id.imageview_collect);
    }

    @Override
    protected void initData() {
        mPermission = new PermissionHelper(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        role = intent.getStringExtra("role");
        LogUtils.LOG("ceshi", role + "行", "role");

        request(id, role);
    }

    @Override
    protected void initListener() {
        mImageview_shopPic.setOnClickListener(this);
        mLinearlayout_collection.setOnClickListener(this);
        mImageView_Suggest.setOnClickListener(this);
        mTextview_more.setOnClickListener(this);
        mLinearlayout_phonenumber.setOnClickListener(this);
        imageGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popwindow_lookpic.showPopwindow(position, imageview_urllist);
            }
        });
        mTextview_content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mTextview_content.getViewTreeObserver().removeOnPreDrawListener(this);
                if (mTextview_content.getLineCount() > MAX_LINE_COUNT) {
                    mTextview_content.setMaxLines(MAX_LINE_COUNT);
                    mTextview_more.setVisibility(View.VISIBLE);
                    mTextview_more.setText("展开全文");
                    LogUtils.LOG("ceshi", mTextview_content.getLineCount() + "行", "服务详情");
                } else {
                    mTextview_more.setVisibility(View.GONE);
                    LogUtils.LOG("ceshi", mTextview_content.getLineCount() + "行", "服务详情");
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.image_shoppic:
                Intent intent_shopskills=new Intent(SkillDetailActivity.this,ShophallActivity.class);
                if (role.equals("1")){
                    intent_shopskills.putExtra("NO","H"+mSkilldetailsbean.getData().getDetail().getHelper_no());
                }else if (role.equals("2")){
                    intent_shopskills.putExtra("NO","B"+mSkilldetailsbean.getData().getDetail().getBusiness_no());
                }
                startActivity(intent_shopskills);

                break;
            case R.id.image_complain:
                if (Staticdata.static_userBean.getData() == null) {
                    ToastUtils.showToast(SkillDetailActivity.this, "请先登录！");
                    return;
                }
                Intent intent_suggest = new Intent(SkillDetailActivity.this, SuggestActivity.class);
                intent_suggest.putExtra("release_specialty_id", mSkilldetailsbean.getData().getDetail().getRelease_specialty_id() + "");
                startActivity(intent_suggest);
                break;
            case R.id.text_more:
                if (mTextview_more.getText().equals("展开全文")) {
                    mTextview_content.setMaxLines(Integer.MAX_VALUE);
                    mTextview_more.setText("收起");
                } else {
                    mTextview_content.setMaxLines(MAX_LINE_COUNT);
                    mTextview_more.setText("展开全文");
                }
                break;
            case R.id.linearlayout_collection:
                if (Staticdata.static_userBean.getData() == null) {
                    ToastUtils.showToast(SkillDetailActivity.this, "请先登录！");
                    return;
                }
                Map map = new HashMap();
                map.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
                map.put("release_id", mSkilldetailsbean.getData().getDetail().getRelease_specialty_id() + "");
                map.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                map.put("type", collrctID + "");
                LogUtils.LOG("ceshi", map.toString() + "收藏网址" + Urls.Baseurl + Urls.setColltctSkill, "服务详情");
                new Volley_Utils(new Interface_volley_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        int status = 0;
                        String msg = "";
                        boolean data;
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//
                            msg = (String) object.get("message");//

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        request(id, role);
                        ToastUtils.showToast(SkillDetailActivity.this, msg);

                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl + Urls.setColltctSkill, this, 1, map);

                break;
            case R.id.linearlayout_phonenumber:
                if (!Staticdata.isLogin) {
                    ToastUtils.showToast(SkillDetailActivity.this, "请先登录");
                    return;
                }
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
                break;
        }
    }

    void setImage(String image) {
        if (image == null || image.equals("")) {
        } else {
            imageview_urllist.clear();
            String[] images = image.split(",");
            int len = images.length;
            LogUtils.LOG("ceshi", "图片的个数" + images.length, "SkillDetailActivity分隔图片");
            for (int i = 0; i < len; i++) {
                imageview_urllist.add(images[i]);
            }
            adapter_gridviewpic.notifyDataSetChanged();
        }
    }

    void request(String id, String role) {
        String URL = "";
        String usertoken = "";
        if (Staticdata.static_userBean.getData() != null) {
            usertoken = Staticdata.static_userBean.getData().getUser_token();
        }

        if (role.equals("1")) {
            URL = Urls.Baseurl + Urls.helperSkilldetail + "?id=" + id + "&user_token=" + usertoken;
        } else {
            URL = Urls.Baseurl + Urls.shopkilldetail + "?id=" + id + "&user_token=" + usertoken;
        }
        LogUtils.LOG("ceshi", "专业详情网址：" + URL, "服务详情");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "服务详情");
                mSkilldetailsbean = new Gson().fromJson(respose, SkillsdetailsBean.class);
                if (mSkilldetailsbean.getData().getDetail().getBusiness_no().equals("")) {
                    mTextview_text_idcard.setText("认证帮手");
                } else {
                    mTextview_text_idcard.setText("认证商户");
                }
                mTextview_title.setText(mSkilldetailsbean.getData().getDetail().getTitle());
                mTextview_issuetime.setText(Utils.getStrTime(mSkilldetailsbean.getData().getDetail().getRelease_date() + ""));
                mTextview_skilltype.setText(mSkilldetailsbean.getData().getDetail().getSpecialty_name());
                mTextview_name.setText(mSkilldetailsbean.getData().getDetail().getContacts());
                mTextview_shopaddress.setText(mSkilldetailsbean.getData().getDetail().getRelease_address());
                mTextview_content.setText(mSkilldetailsbean.getData().getDetail().getDescription());
                mTextview_shopname.setText(mSkilldetailsbean.getData().getDetail().getAuth_name());
                Glide.with(SkillDetailActivity.this).load(mSkilldetailsbean.getData().getDetail().getAvatar_url()).into(mImageview_shopPic);
                image_url = mSkilldetailsbean.getData().getDetail().getImg_url();
                setImage(image_url);
                collrctID = mSkilldetailsbean.getData().getDetail().getCollection_status() == 0 ? 1 : 2;
                mImageview_collect.setSelected(mSkilldetailsbean.getData().getDetail().getCollection_status() == 0 ? false : true);
                mTextview_relseaCount.setText(mSkilldetailsbean.getData().getDetail().getRelease_num() + "个");
                mTextview_text_peoplelook.setText("已有" + mSkilldetailsbean.getData().getDetail().getBrowse_number() + "人浏览");
//                long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
//                long ago = Long.parseLong(mSkilldetailsbean.getData().getDetail().getCreateDate()+"");//
//                String time = Utils.getDistanceTime(ago, now);//算出的差值
                mTextview_shopIN_time.setText(mSkilldetailsbean.getData().getDetail().getAddmission_month() + "个月");
                if(Staticdata.isLogin&&mSkilldetailsbean.getData().getDetail().getClient_no().equals(Staticdata.static_userBean.getData().getAppuser().getClient_no())){
                    linearlayout_bottom.setVisibility(View.GONE);
                }else {
                    linearlayout_bottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int error) {

            }
        }).Http(URL, SkillDetailActivity.this, 0);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermission != null) {
            mPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
