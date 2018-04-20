package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.class_.Popwindow_SkillType;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueSkillActivity extends BaseActivityother {


    //控件
    TextView mTextview_chooce;
    TextView mTextview_address;
    EditText mEditview_title;
    EditText mEditview_addressdetail;
    EditText mEditview_skilldetail;
    EditText mEditview_name;
    EditText mEditview_phonenumber;


    ImageView choosePIC;

    Button mButton_submit;


    //对象
    PermissionHelper permissionHelper;
    Popwindow_SkillType mPopwindow_skilltype;

    //数据
    String  specialty_id ="";//专业类形
    String tittle="";//
    String description="";//
    String release_address="郑州";//发布地点// TODO: 地点实现
    String detail_address="";//详细地点
    String img_id="daiwancheng";//图片                   // TODO: 图片
    String contacts="";//联系人
    String mobile_no="";//电话


    Map map_issueSkill;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_skill;
    }

    @Override
    protected void setData() {


    }

    @Override
    protected void initData() {
        map_issueSkill=new HashMap();

    }

    @Override
    protected void initListener() {
        mTextview_chooce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopwindow_skilltype=new Popwindow_SkillType(IssueSkillActivity.this, new InterfacePopwindow_SkillType() {
                    @Override
                    public void onSuccesses(String type, int id) {
                        mTextview_chooce.setText(type);
                        specialty_id=id+"";
                    }
                });
                mPopwindow_skilltype.showPopwindow();
            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean Nonull= checknull();//判空方法
                if(Nonull){
                    request(map_issueSkill);
                }

            }
        });
        choosePIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 permissionHelper=new PermissionHelper(IssueSkillActivity.this,new  String []{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},100);
                Permissionmanage permissionmanage=new Permissionmanage(permissionHelper, new InterfacePermission() {
                    @Override
                    public void onResult(boolean result) {
                        LogUtils.LOG("ceshi",result+"","");
                        if(result){
                            ImageConfig imageConfig
                                    = new ImageConfig.Builder(new GlideLoader())
                                    // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                                    .steepToolBarColor(getResources().getColor(R.color.yellow_jianbian_end))
                                    // 标题的背景颜色 （默认黑色）
                                    .titleBgColor(getResources().getColor(R.color.yellow_jianbian_end))
                                    // 提交按钮字体的颜色  （默认白色）
                                    .titleSubmitTextColor(getResources().getColor(R.color.white))
                                    // 标题颜色 （默认白色）
                                    .titleTextColor(getResources().getColor(R.color.white))
                                    .build();
                            ImageSelector.open(IssueSkillActivity.this, imageConfig);   // 开启图片选择器
                        }else {
                            ToastUtils.showToast(IssueSkillActivity.this,"请允许开启照相功能，并读取本地文件");
                        }
                    }
                });
                permissionmanage.requestpermission();



            }
        });
    }

    @Override
    protected void initView() {
        mTextview_chooce=findViewById(R.id.text_chooce);
        mTextview_address=findViewById(R.id.textview_skilladdress);
        mEditview_title=findViewById(R.id.edit_skilltitle);
        mEditview_addressdetail=findViewById(R.id.edit_skilldetailaddress);
        mEditview_skilldetail=findViewById(R.id.edit_detailskill);
        mEditview_name=findViewById(R.id.edit_skillpeoplename);
        mEditview_phonenumber=findViewById(R.id.edit_skillpeoplename);
        mButton_submit=findViewById(R.id.button_submit);

        choosePIC=findViewById(R.id.iamge_choosePIC);
    }
    boolean checknull(){
        if(mTextview_chooce.getText().equals("请选择")){
            ToastUtils.showToast(this,"请选择技能类型");
            return false;
        }
        tittle=mEditview_title.getText()+"";
        if(tittle.equals("")){
            ToastUtils.showToast(this,"请填写标题");
            return false;
        }
        description=mEditview_skilldetail.getText()+"";
        if(description.equals("")){
            ToastUtils.showToast(this,"请填写详细描述");
            return false;
        }
        detail_address=mEditview_addressdetail.getText()+"";
        if(detail_address.equals("")){
            ToastUtils.showToast(this,"请填写详细地址");
            return false;
        }
        contacts=mEditview_name.getText()+"";
        if(detail_address.equals("")){
            ToastUtils.showToast(this,"请填写联系人");
            return false;
        }
        mobile_no=mEditview_phonenumber.getText()+"";
        if (mobile_no.equals("")){
            ToastUtils.showToast(this,"请填写手机号码");
            return false;
        }
        map_issueSkill.put("specialty_id",specialty_id);
        map_issueSkill.put("title",tittle);
        map_issueSkill.put("description",description);
        map_issueSkill.put("release_address",release_address);
        map_issueSkill.put("detail_address",detail_address);
        map_issueSkill.put("img_id","1");
        map_issueSkill.put("contacts",contacts);
        map_issueSkill.put("mobile_no",mobile_no);
        map_issueSkill.put("client_no","90000000007");//客户号
        map_issueSkill.put("business_no","1");//商户号
        map_issueSkill.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_issueSkill.put("service_area","郑州");
        //todo 从登录信息中获得商户号  客户号  post
        return true;
    }
    void request (Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("status");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(IssueSkillActivity.this,msg);
                    finish();
                }else {
                    ToastUtils.showToast(IssueSkillActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.IssueSkill,this,1,map);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            ArrayList<Bitmap> dataPictrue = dataPictrue = new ArrayList<>();
            for (String path : pathList) {
                Log.i("ImagePathList", path);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap, 350, 350, true);
                dataPictrue.add(mBitmap);
                choosePIC.setImageBitmap(mBitmap);
                UpLoadImage.getIntence(IssueSkillActivity.this, new Interface_loadImage_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi",respose,"发布技能上传图片返回");

                    }
                }).uploadImg(pathList,1);

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
