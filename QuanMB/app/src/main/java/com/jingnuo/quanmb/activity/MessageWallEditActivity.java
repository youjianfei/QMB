package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_UPLoad;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ReducePIC;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.kaopiz.kprogresshud.KProgressHUD;
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

public class MessageWallEditActivity extends BaseActivityother {
//控件
    MyGridView myGridView;
    TextView mTextview_submit;
    EditText mEdit_message;


    String message="";
    Map map_addliuyan;


    Bitmap mBitmap = null;
    PermissionHelper permissionHelper;
    List<Bitmap> mListImage_bitmap;
    List<List<String>> mList_PicPath_down;//；压缩后本地图片path集合;
    List<String> mList_picID;// 上传图片返回ID;
    Adapter_Gridviewpic_UPLoad adapter_gridviewpic;
    String img_id="";//图片String img_id="";//图片
    int  count=0;//图片的张数。判断调用几次上传图片接口
    int PIC_mix = 3;//选择图片得张数

    UpLoadImage upLoadImage;
    KProgressHUD mKProgressHUD;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_message_wall_edit;
    }

    @Override
    protected void setData() {
        upLoadImage=new UpLoadImage(this, new Interface_loadImage_respose() {
            @Override
            public void onSuccesses(String respose) {
                if(respose.equals("erro")){
//                    progressDlog.cancelPD();
                    mKProgressHUD.dismiss();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast(MessageWallEditActivity.this,"网络开小差儿，请重新提交");
                        }
                    });
                    mList_picID.clear();
                    return;
                }
                LogUtils.LOG("ceshi",respose,"发布技能上传图片返回respose");
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
                            for (String image : mList_picID) {
                                img_id=img_id+image+",";
                            }
                            map_addliuyan.put("images",img_id);
                            request(map_addliuyan);
                            LogUtils.LOG("ceshi","上传图片完成","发布技能上传图片");
                        }

                    }else {
//                        progressDlog.cancelPD();
                        mKProgressHUD.dismiss();
                        mList_picID.clear();
                        final String finalMsg = msg;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(MessageWallEditActivity.this, finalMsg);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    void uploadimgagain(int  count){
        upLoadImage.uploadImg(mList_PicPath_down.get(count),6);
    }
    @Override
    protected void initData() {
        mKProgressHUD = new KProgressHUD(MessageWallEditActivity.this);
        map_addliuyan=new HashMap();
        permissionHelper = new PermissionHelper(MessageWallEditActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        mListImage_bitmap=new ArrayList<>();
        mList_picID=new ArrayList<>();
        mList_PicPath_down=new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.addpic2);
        mListImage_bitmap.add(bitmap);
        adapter_gridviewpic=new Adapter_Gridviewpic_UPLoad(mListImage_bitmap,this);
        myGridView.setAdapter(adapter_gridviewpic);
    }

    @Override
    protected void initListener() {
        mTextview_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean Nonull= checknull();//判空方法
                if(Nonull){
//                    progressDlog.showPD("正在发布，请稍等");
                    mKProgressHUD.show();
                    uploadimg();//上传图片
                }
            }
        });
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi", "点击+" + position, "选择图片");
                if (mListImage_bitmap.size() - 1 == position) {
                    choosePIC();
                } else {
                    mListImage_bitmap.remove(position);
                    mList_PicPath_down.remove(position);//删除图片地址以便上传；
                    PIC_mix = 3 - mList_PicPath_down.size();
                    adapter_gridviewpic.notifyDataSetChanged();

                }
            }
        });
    }
    boolean checknull(){
        message=mEdit_message.getText()+"";
        if(message.equals("")){
            ToastUtils.showToast(this,"请输入留言内容");
            return false;
        }
        if(message.length()>300){
            ToastUtils.showToast(this,"最多支持300个字");
            return false;
        }

        map_addliuyan.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        map_addliuyan.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_addliuyan.put("content",message);
        map_addliuyan.put("community_code",Staticdata.static_userBean.getData().getAppuser().getCommunity_code());
        return true;
    }
    void uploadimg(){
        if( mList_PicPath_down.size()>=1){
            upLoadImage.uploadImg(mList_PicPath_down.get(0),6);
        }else {
            request(map_addliuyan);
        }
    }
    void request (Map map){
        String URL="";
        URL=  Urls.Baseurl_cui+Urls.addLiuyan;
        LogUtils.LOG("ceshi",map.toString(),"发布留言墙的map参数");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
//                    progressDlog.cancelPD();
                    mKProgressHUD.dismiss();
                    ToastUtils.showToast(MessageWallEditActivity.this,msg);
                    finish();
                }else {
                    mList_PicPath_down.clear();
                    mList_picID.clear();
//                    progressDlog.cancelPD();
                    mKProgressHUD.dismiss();
                    ToastUtils.showToast(MessageWallEditActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {
                mList_PicPath_down.clear();
                mList_picID.clear();
//                progressDlog.cancelPD();
                mKProgressHUD.dismiss();
            }
        }).postHttp(URL,this,1,map);
    }
    void choosePIC() {
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
//                            // 开启单选   （默认为多选）
//                            .singleSelect()
                            // 开启多选   （默认为多选）
                            .mutiSelect()
                            // 多选时的最大数量   （默认 9 张）
                            //这里只允许上传3张
                            .mutiSelectMaxSize(PIC_mix)
                            .showCamera()
                            // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                            .filePath("/ImageSelector/Pictures")
                            .build();
                    ImageSelector.open(MessageWallEditActivity.this, imageConfig);   // 开启图片选择器
                } else {
                    ToastUtils.showToast(MessageWallEditActivity.this, "请允许开启照相功能，并读取本地文件");
                }
            }
        });
        permissionmanage.requestpermission();
    }

    @Override
    protected void initView() {
        myGridView=findViewById(R.id.GridView_PIC);
        mTextview_submit=findViewById(R.id.textview_tijiao);
        mEdit_message=findViewById(R.id.edit_suggest);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

//            ArrayList<Bitmap> dataPictrue = dataPictrue = new ArrayList<>();
            for (String path : pathList) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                mBitmap = Bitmap.createScaledBitmap(bitmap, 350, 350, true);
//                dataPictrue.add(mBitmap);
                mListImage_bitmap.add(0, mBitmap);
//                upLoadImage.uploadImg(pathList, 2);

                //调用压缩图片的方法，返回压缩后的图片path
                String src_path = path;//原图片的路径
                String targetPath = Environment.getExternalStorageDirectory() + "/picyasuo/"+System.currentTimeMillis()+".png";//压缩后图片的路径
                final String compressImage = ReducePIC.compressImage(src_path, targetPath, 30);//进行图片压缩，返回压缩后图片的路径
                List<String> mList_picpath = new ArrayList<>();
                mList_picpath.add(compressImage);
                mList_PicPath_down.add(0, mList_picpath);
            }
            PIC_mix = 3 - mList_PicPath_down.size();
            adapter_gridviewpic.notifyDataSetChanged();

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
