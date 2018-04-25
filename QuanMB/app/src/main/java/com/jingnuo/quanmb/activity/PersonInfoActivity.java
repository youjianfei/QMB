package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonInfoActivity extends BaseActivityother {
    //控件
    TextView mtextview_phonenumber,mTextview_changepassword,mTextview_setsafepassword;
    TextView mtextview_nickname;
    ImageView mImageview_headPIC;

    //对象
    PermissionHelper permissionHelper;
    UpLoadImage upLoadImage;

    //数据
    Map  map_setheadpic;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void setData() {
        upLoadImage = new UpLoadImage(this, new Interface_loadImage_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "发布技能上传图片返回respose");
                int status = 0;
                String msg = "";
                String imageID = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("msg");//登录返回信息

                    if (status == 1) {
                        imageID = (String) object.get("imgID");
                        LogUtils.LOG("ceshi", "单张图片ID" + imageID, "发布技能上传图片返回imageID");
                        map_setheadpic.put("img_id",imageID);
                        map_setheadpic.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                        setheadRequest(map_setheadpic);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void initData() {
        permissionHelper = new PermissionHelper(PersonInfoActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        map_setheadpic=new HashMap();
        mtextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
        mtextview_phonenumber.setText(Staticdata.static_userBean.getData().getAppuser().getMobile_no());
    }

    @Override
    protected void initListener() {
        mTextview_changepassword.setOnClickListener(this);
        mtextview_nickname.setOnClickListener(this);
        mtextview_phonenumber.setOnClickListener(this);
        mImageview_headPIC.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mtextview_phonenumber=findViewById(R.id.text_phonrnumber);
        mTextview_changepassword=findViewById(R.id.text_changephonenumber);
        mtextview_nickname=findViewById(R.id.text_name);
        mImageview_headPIC=findViewById(R.id.image_userpic);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    switch (v.getId()){
        case R.id.text_changephonenumber:
            Intent intent_change=new Intent(this,ChangepasswordActivity.class);
            startActivity(intent_change);
            break;
        case R.id.text_name:
            Intent intent_nickname =new Intent(this,SetNicknameActivity.class);
            startActivity(intent_nickname);
            break;
        case R.id.text_phonrnumber:
            Intent intent_change_phone =new Intent(this,ChangephoneNumberActivity.class);
            startActivity(intent_change_phone);
            break;
        case R.id.image_userpic:
            chooseHeadPic();


            break;

    }
    }
    //设置头像上传头像ID
    void setheadRequest(Map map){

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息

                    if (status == 1) {
                        ToastUtils.showToast(PersonInfoActivity.this,"设置头像成功");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.setheadPic,PersonInfoActivity.this,1,map);



    }

   void  chooseHeadPic(){
       Permissionmanage permissionmanage = new Permissionmanage(permissionHelper, new InterfacePermission() {
           @Override
           public void onResult(boolean result) {
               LogUtils.LOG("ceshi", result + "", "");
               if (result) {
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//安卓7.0权限 代替了FileProvider方式   https://blog.csdn.net/xiaoyu940601/article/details/54406725
                       StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                       StrictMode.setVmPolicy(builder.build());
                   }
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
                           // 开启单选   （默认为多选）
                           .singleSelect()
                           .showCamera()
                           // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                           .filePath("/ImageSelector/Pictures")
                           .build();
                   ImageSelector.open(PersonInfoActivity.this, imageConfig);   // 开启图片选择器
               } else {
                   ToastUtils.showToast(PersonInfoActivity.this, "请允许开启照相功能，并读取本地文件");
               }
           }
       });
       permissionmanage.requestpermission();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
//            ArrayList<Bitmap> dataPictrue = dataPictrue = new ArrayList<>();
            Bitmap bitmap = null;
            Bitmap mBitmap = null;
            for (String path : pathList) {
                bitmap = BitmapFactory.decodeFile(path);
                mBitmap = Bitmap.createScaledBitmap(bitmap, 525, 350, true);
//                dataPictrue.add(mBitmap);
            }
            mImageview_headPIC.setImageBitmap(bitmap);
            upLoadImage.uploadImg(pathList,3);


        }
    }
}
