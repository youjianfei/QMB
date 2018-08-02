package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import com.jingnuo.quanmb.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.popwinow.ProgressDlog;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.ShopcenterBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ReducePIC;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopCenterInfoActivity extends BaseActivityother {
    //控件
    TextView mTextview_edit;
    TextView mtextview_name;
    TextView mtextview_phone;
    TextView mtextview_hangye;
    TextView mtextview_address;
    CircleImageView mImageview_head;
    EditText mEdit_view_jianjie;
    MyGridView myGridView;
    ProgressDlog progressDlog;


    //数据
    ShopcenterBean shopcenterBean;
    PermissionHelper permissionHelper;
    UpLoadImage upLoadImage;
    Map map_setheadpic;

    List<String> imageview_urllist;//用于界面展示的图片String集合
    List<List<String>> mList_PicPath_down;//；压缩后上传本地图片path集合;
    List<String> mList_picID;// 上传图片返回ID;
    Adapter_Gridviewpic adapter_gridviewpic;
//    Popwindow_lookpic popwindow_lookpic;

    List<String> imageview_deleteUrlList;//删除的网络存储的图片
    List<String> imageIDList;//线上图片ID;  删除图片之后需要重新上传

    Map map_edit;//编辑资料的map
    String jianjie = "";//简介
    String business_id = "";//店铺id
    String img_id="";//上传的图片id
    int  count=0;//图片的张数。判断调用几次上传图片接口
    int weizhi = 0;    //1  头像选择图片    2   商家图片选择
    int PIC_mix = 0;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_center_info;
    }

    @Override
    protected void setData() {
//        popwindow_lookpic=new Popwindow_lookpic(this);
        upLoadImage = new UpLoadImage(this, new Interface_loadImage_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "发布技能上传图片返回respose");
                if(weizhi == 1){//上传头像图片返回
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
                }else {//上传商铺图片返回
                    if(respose.equals("erro")){
                        progressDlog.cancelPD();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(ShopCenterInfoActivity.this,"网络开小差儿，请重新提交");
                            }
                        });
                        mList_picID.clear();
                        return;
                    }
                    int status = 0;
                    String msg = "";
                    String imageID="";
                    try {
                        JSONObject object = new JSONObject(respose);
                        status = (Integer) object.get("code");//
                        msg = (String) object.get("msg");//

                        if(status==1){
                            count++;
                            imageID=(String) object.get("imgID");
                            LogUtils.LOG("ceshi","单张图片ID"+imageID,"发布技能上传图片返回imageID");
                            mList_picID.add(0,imageID);
                            if(count!=mList_PicPath_down.size()){
                                uploadimgagain(count);
                            }else {
                                for (String image : mList_picID) {//b遍历上传图片返回的ID
                                    img_id=img_id+image+",";
                                }
                                for (String image : imageIDList) {//遍  线上图片删除之后剩下了 ID
                                    img_id=img_id+image+",";
                                }
                                map_edit.put("img_id",img_id);
                                request_edit(map_edit);
                                LogUtils.LOG("ceshi","上传图片完成","发布技能上传图片");
                            }

                        }else {
                            progressDlog.cancelPD();
                            mList_picID.clear();
                            final String finalMsg = msg;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToast(ShopCenterInfoActivity.this, finalMsg);
                                }
                            });

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
        map_edit=new HashMap();
        progressDlog=new ProgressDlog(this);
        imageview_urllist = new ArrayList<>();
        mList_picID=new ArrayList<>();
        mList_PicPath_down = new ArrayList<>();
        imageview_deleteUrlList = new ArrayList<>();
        imageIDList = new ArrayList<>();
        adapter_gridviewpic = new Adapter_Gridviewpic(imageview_urllist, this);
        myGridView.setAdapter(adapter_gridviewpic);
        permissionHelper = new PermissionHelper(ShopCenterInfoActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        map_setheadpic = new HashMap();
        request();
    }

    @Override
    protected void initListener() {
        mTextview_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(init()){
                    progressDlog.showPD("正在提交，请稍等");
                    uploadimg();//上传图片
                }

            }
        });
        mImageview_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weizhi = 1;
                chooseHeadPic();
            }
        });
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                popwindow_lookpic.showPopwindow(position,imageview_urllist);
                LogUtils.LOG("ceshi", position + "!" + imageview_urllist.size(), "shipeiqi");
                if (position == imageview_urllist.size() - 1) {//如果点击添加图片的  图片   就是选择图片
                    PIC_mix = 4 - imageview_urllist.size();
                    weizhi = 2;
                    chooseShopPic();
                } else {//点击图片  就删除图片
                    if (imageview_urllist.get(position).contains("http")) {//如果是删除线上图片   就删除idList中的  ID；
                        if(mList_PicPath_down.size()==0){
                            imageIDList.remove(position);
                        }else {
                            imageIDList.remove(position-mList_PicPath_down.size());
                        }

                    }
                    imageview_urllist.remove(position);
                    adapter_gridviewpic.notifyDataSetChanged();
                }


            }
        });
//        mTextview_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_edit = new Intent(ShopCenterInfoActivity.this, ShopinfoEditActivity.class);
//                intent_edit.putExtra("business_id", shopcenterBean.getData().getList().getBusiness_id() + "");
//                intent_edit.putExtra("jianjie", shopcenterBean.getData().getList().getIntroduction() + "");
//                startActivity(intent_edit);
//            }
//        });
    }

    @Override
    protected void initView() {
        mTextview_edit = findViewById(R.id.textview_edit);
        mtextview_name = findViewById(R.id.text_shopname);
        mtextview_phone = findViewById(R.id.text_phonrnumber);
        mtextview_hangye = findViewById(R.id.text_hangye);
        mtextview_address = findViewById(R.id.text_shopaddress);
        mImageview_head = findViewById(R.id.image_userpic);
        mEdit_view_jianjie = findViewById(R.id.edit_jianjie);
        myGridView = findViewById(R.id.GridView_PIC);
    }
    void uploadimg(){
        if( mList_PicPath_down.size()>=1){
            upLoadImage.uploadImg(mList_PicPath_down.get(0),6);
        }else {
            for (String image : imageIDList) {//遍  线上图片删除之后剩下了 ID
                img_id=img_id+image+",";
            }
            map_edit.put("img_id",img_id);
            request_edit(map_edit);
        }

    }
    void uploadimgagain(int  count){
        upLoadImage.uploadImg(mList_PicPath_down.get(count),6);
    }
    void request_edit (Map map){//上传编辑的资料
        String URL= Urls.Baseurl+Urls.editshopinfo;
        LogUtils.LOG("ceshi",map.toString(),"发布服务的map参数");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                LogUtils.LOG("ceshi",respose,"商户修法资料完成");
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    progressDlog.cancelPD();
                    ToastUtils.showToast(ShopCenterInfoActivity.this,msg);
                    finish();
                }else {
                    mList_PicPath_down.clear();
                    mList_picID.clear();
                    progressDlog.cancelPD();
                    ToastUtils.showToast(ShopCenterInfoActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {
                mList_PicPath_down.clear();
                mList_picID.clear();
                progressDlog.cancelPD();
            }
        }).postHttp(URL,this,1,map);
    }
    void setImage(String image) {
        if (image == null || image.equals("")) {
            imageview_urllist.add("add");
        } else {
            String[] images = image.split(",");
            int len = images.length;
            LogUtils.LOG("ceshi", "图片的个数" + images.length, "SkillDetailActivity分隔图片");
            imageview_urllist.clear();
            for (int i = 0; i < len; i++) {
                imageview_urllist.add(images[i]);
            }
            imageview_urllist.add("add");
            adapter_gridviewpic.notifyDataSetChanged();
        }

    }

    void setImageID(String imageID) {
        if (imageID == null || imageID.equals("")) {

        } else {
            String[] images = imageID.split(",");
            int len = images.length;
            imageIDList.clear();
            for (int i = 0; i < len; i++) {
                imageIDList.add(images[i]);
            }
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
                if (shopcenterBean.getData().getList().getIntroduction() != null) {
                    mEdit_view_jianjie.setText(shopcenterBean.getData().getList().getIntroduction());
                }
                setImageID(shopcenterBean.getData().getList().getBusiness_img());
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

    void chooseShopPic() {
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
//                            .singleSelect()
                            // 开启多选   （默认为多选）
                            .mutiSelect()
                            // 多选时的最大数量   （默认 9 张）
                            //这里只允许上传3张
                            .mutiSelectMaxSize(PIC_mix)

                            .build();
                    ImageSelector.open(ShopCenterInfoActivity.this, imageConfig);   // 开启图片选择器
                } else {
                    ToastUtils.showToast(ShopCenterInfoActivity.this, "请允许开启照相功能，并读取本地文件");
                }
            }
        });
        permissionmanage.requestpermission();
    }

    boolean init() {
        jianjie = mEdit_view_jianjie.getText() + "";
        if (jianjie.equals("")) {
            ToastUtils.showToast(this, "请输入商铺简介");
            return false;
        }
        map_edit.put("business_id", shopcenterBean.getData().getList().getBusiness_id() + "");
        map_edit.put("introduction", jianjie);
        map_edit.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            if (weizhi == 1) {
                List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
//            ArrayList<Bitmap> dataPictrue = dataPictrue = new ArrayList<>();
                List<String> mList_picpath = new ArrayList<>();
                Bitmap bitmap = null;
                Bitmap mBitmap = null;
                for (String path : pathList) {
                    bitmap = BitmapFactory.decodeFile(path);
                    mBitmap = Bitmap.createScaledBitmap(bitmap, 525, 350, true);
//                dataPictrue.add(mBitmap);
                    //调用压缩图片的方法，返回压缩后的图片path
                    String src_path = path;//原图片的路径
                    String targetPath = Environment.getExternalStorageDirectory() + "/picyasuo/"+System.currentTimeMillis()+".png";//压缩后图片的路径
                    final String compressImage = ReducePIC.compressImage(src_path, targetPath, 30);//进行图片压缩，返回压缩后图片的路径
                    mList_picpath.add(compressImage);
                }
                mImageview_head.setImageBitmap(bitmap);
                upLoadImage.uploadImg(mList_picpath, 3);
            } else {
                List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
                ArrayList<Bitmap> dataPictrue = dataPictrue = new ArrayList<>();
                for (String path : pathList) {
                    Log.i("ImagePathList", path);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap, 350, 350, true);
                    dataPictrue.add(mBitmap);
//                    mlistdata_pic.add(0,mBitmap);
                    //调用压缩图片的方法，返回压缩后的图片path
                    String src_path = path;//原图片的路径
                    String targetPath = Environment.getExternalStorageDirectory()+ "/picyasuo/"+System.currentTimeMillis()+".png";//压缩后图片的路径
                    final String compressImage = ReducePIC.compressImage(src_path, targetPath, 30);//进行图片压缩，返回压缩后图片的路径
                    imageview_urllist.add(0, path);
                    List<String> mList_picpath = new ArrayList<>();
                    mList_picpath.add(compressImage);
                    mList_PicPath_down.add(0, mList_picpath);

                }
                PIC_mix = 4 - imageview_urllist.size();
                adapter_gridviewpic.notifyDataSetChanged();
            }

        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
//        request();
    }
}
