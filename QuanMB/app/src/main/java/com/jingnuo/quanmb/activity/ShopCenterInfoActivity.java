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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.class_.Popwindow_lookpic;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.ShopcenterBean;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopCenterInfoActivity extends BaseActivityother {
    //控件
    TextView mTextview_edit;
    TextView mtextview_name;
    TextView mtextview_phone;
    TextView mtextview_hangye;
    TextView mtextview_address;
    CircleImageView mImageview_head;
    TextView mEdit_view_jianjie;
    MyGridView myGridView;


    //数据
    ShopcenterBean shopcenterBean;
    PermissionHelper permissionHelper;
    UpLoadImage upLoadImage;
    Map map_setheadpic;

    List<String> imageview_urllist;
    Adapter_Gridviewpic adapter_gridviewpic;
    Popwindow_lookpic popwindow_lookpic;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_center_info;
    }

    @Override
    protected void setData() {
        popwindow_lookpic=new Popwindow_lookpic(this);
        upLoadImage = new UpLoadImage(this, new Interface_loadImage_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "发布技能上传图片返回respose");
                int status = 0;
                String msg = "";
                String imageID = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//

                    if (status == 1) {
                        imageID = (String) object.get("imgID");
                        LogUtils.LOG("ceshi", "单张图片ID" + imageID, "发布技能上传图片返回imageID");
                        map_setheadpic.put("img_id", imageID + "");
                        map_setheadpic.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                        map_setheadpic.put("business_id", shopcenterBean.getData().getList().getBusiness_id() + "");
                        setheadRequest(map_setheadpic);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //设置头像上传头像ID
    void setheadRequest(Map map) {

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                String imageUrl = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("msg");//登录返回信息
                    imageUrl = (String) object.get("BusinessAvatarUrl");

                    if (status == 1) {
                        ToastUtils.showToast(ShopCenterInfoActivity.this, msg);
                        shopcenterBean.getData().getList().setAvatar_url(imageUrl);
                        Glide.with(ShopCenterInfoActivity.this).load(shopcenterBean.getData().getList().getAvatar_url()).into(mImageview_head);
                    } else {
                        ToastUtils.showToast(ShopCenterInfoActivity.this, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl + Urls.setshophead, ShopCenterInfoActivity.this, 1, map);

    }

    @Override
    protected void initData() {
        imageview_urllist = new ArrayList<>();
        adapter_gridviewpic = new Adapter_Gridviewpic(imageview_urllist, this);
        myGridView.setAdapter(adapter_gridviewpic);
        permissionHelper = new PermissionHelper(ShopCenterInfoActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        map_setheadpic = new HashMap();
        request();
    }

    @Override
    protected void initListener() {
        mImageview_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseHeadPic();
            }
        });
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popwindow_lookpic.showPopwindow(position,imageview_urllist);
            }
        });
    }

    @Override
    protected void initView() {
        mTextview_edit=findViewById(R.id.textview_edit);
        mtextview_name = findViewById(R.id.text_shopname);
        mtextview_phone = findViewById(R.id.text_phonrnumber);
        mtextview_hangye = findViewById(R.id.text_hangye);
        mtextview_address = findViewById(R.id.text_shopaddress);
        mImageview_head = findViewById(R.id.image_userpic);
        mEdit_view_jianjie = findViewById(R.id.edit_jianjie);
        myGridView = findViewById(R.id.GridView_PIC);
    }

    void setImage(String image) {
        if (image == null || image.equals("")) {

        } else {
            String[] images = image.split(",");
            int len = images.length;
            LogUtils.LOG("ceshi", "图片的个数" + images.length, "SkillDetailActivity分隔图片");
            imageview_urllist.clear();
            for (int i = 0; i < len; i++) {
                imageview_urllist.add(images[i]);
            }
            adapter_gridviewpic.notifyDataSetChanged();

        }

    }

    void request() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                shopcenterBean = new Gson().fromJson(respose, ShopcenterBean.class);
                Glide.with(ShopCenterInfoActivity.this)
                        .load(shopcenterBean.getData().getList().getAvatar_url()).into(mImageview_head);
                mtextview_name.setText(shopcenterBean.getData().getList().getBusiness_name());
                mtextview_phone.setText(shopcenterBean.getData().getList().getBusiness_mobile_no());
                mtextview_hangye.setText(shopcenterBean.getData().getList().getBusiness_type_id());
                mtextview_address.setText(shopcenterBean.getData().getList().getBusiness_address());
                if (shopcenterBean.getData().getList().getIntroduction()!=null) {
                    mEdit_view_jianjie.setText(shopcenterBean.getData().getList().getIntroduction());
                }
                setImage(shopcenterBean.getData().getList().getBusiness_url());
            }

            @Override
            public void onError(int error) {


            }
        }).Http(Urls.Baseurl + Urls.shopcenter + Staticdata.static_userBean.getData()
                .getUser_token() + "&client_no=" + Staticdata.static_userBean.getData().getAppuser()
                .getClient_no(), this, 0);
    }

    void chooseHeadPic() {
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
                    ImageSelector.open(ShopCenterInfoActivity.this, imageConfig);   // 开启图片选择器
                } else {
                    ToastUtils.showToast(ShopCenterInfoActivity.this, "请允许开启照相功能，并读取本地文件");
                }
            }
        });
        permissionmanage.requestpermission();
    }

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
            mImageview_head.setImageBitmap(bitmap);
            upLoadImage.uploadImg(pathList, 3);
        }
    }


}
