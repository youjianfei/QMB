package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_UPLoad;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.popwinow.Popwindow_SkillType;
import com.jingnuo.quanmb.popwinow.ProgressDlog;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
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

public class IssueSkillActivity extends BaseActivityother {


    //控件
    TextView mTextview_chooce;
    TextView mTextview_address;
    EditText mEditview_title;
    EditText mEditview_addressdetail;
    EditText mEditview_skilldetail;
    EditText mEditview_name;
    EditText mEditview_phonenumber;
    MyGridView imageGridview;


    Button mButton_submit;


    //对象
    PermissionHelper permissionHelper;
    Popwindow_SkillType mPopwindow_skilltype;
    UpLoadImage upLoadImage;
    Adapter_Gridviewpic_UPLoad adapter_gridviewpic_upLoad;
    ProgressDlog progressDlog;
    //数据
    String  specialty_id ="";//专业类形
    String tittle="";//
    String description="";//
    String release_address="";//
    String detail_address="";//详细地点
    String img_id="";//图片
    Bitmap mBitmap=null;

    String contacts="";//联系人
    String mobile_no="";//电话
    List<Bitmap> mlistdata_pic;//展示得 选择得图片得bitmap
    List<String> mList_picID;// 上传图片返回ID;
    List<List<String>> mList_PicPath_down;//；压缩后本地图片path集合;

    int  PIC_mix=3;//选择图片得张数

    int  type=0;
    int  count=0;//图片的张数。判断调用几次上传图片接口



    Map map_issueSkill;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_skill;
    }

    @Override
    protected void setData() {
        mList_PicPath_down=new ArrayList<>();
        mlistdata_pic=new ArrayList<>();
        Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(), R.mipmap.addpic);
        mlistdata_pic.add(bitmap);
        adapter_gridviewpic_upLoad=new Adapter_Gridviewpic_UPLoad(mlistdata_pic,this);
        imageGridview.setAdapter(adapter_gridviewpic_upLoad);
        upLoadImage=new UpLoadImage(this, new Interface_loadImage_respose() {
            @Override
            public void onSuccesses(String respose) {
                if(respose.equals("erro")){
                    progressDlog.cancelPD();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast(IssueSkillActivity.this,"网络开小差儿，请重新提交");
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
                            map_issueSkill.put("img_id",img_id);
                            request(map_issueSkill);
                            LogUtils.LOG("ceshi","上传图片完成","发布技能上传图片");
                        }

                    }else {
                        progressDlog.cancelPD();
                        mList_picID.clear();
                        final String finalMsg = msg;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(IssueSkillActivity.this, finalMsg);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void initData() {
        progressDlog=new ProgressDlog(this);
        type=getIntent().getIntExtra("type",0);
        permissionHelper=new PermissionHelper(IssueSkillActivity.this,new  String []{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},100);
        map_issueSkill=new HashMap();
        mList_picID=new ArrayList<>();

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
                    progressDlog.showPD("正在发布，请稍等");
                    uploadimg();//上传图片

                }

            }
        });
        imageGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi","点击+"+position,"选择图片");
                if(mlistdata_pic.size()-1==position){
                    choosePIC();
                }else {
                    mlistdata_pic.remove(position);
                    mList_PicPath_down.remove(position);//删除图片地址以便上传；
                    adapter_gridviewpic_upLoad.notifyDataSetChanged();
                    PIC_mix=3-mList_PicPath_down.size();
                }

            }
        });
        mTextview_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent_map = new Intent(IssueSkillActivity.this, LocationMapActivity.class);
                startActivityForResult(mIntent_map, 2018418);
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
        mEditview_phonenumber=findViewById(R.id.edit_skillpeoplephone);
        mButton_submit=findViewById(R.id.button_submit);
        imageGridview=findViewById(R.id.GridView_PIC);

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
        release_address = mTextview_address.getText()+"";
        if(release_address.equals("请选择")){
            ToastUtils.showToast(this, "请选择任务地点");
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

        LogUtils.LOG("ceshi","图片ID:"+img_id,"上传图片返回拼接后");
        map_issueSkill.put("specialty_id",specialty_id);
        map_issueSkill.put("title",tittle);
        map_issueSkill.put("description",description);
        map_issueSkill.put("release_address",release_address);
        map_issueSkill.put("detail_address",detail_address);
        map_issueSkill.put("img_id",img_id);
        map_issueSkill.put("contacts",contacts);
        map_issueSkill.put("mobile_no",mobile_no);
        map_issueSkill.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_issueSkill.put("service_area","郑州");
        return true;
    }
    void uploadimg(){
        if( mList_PicPath_down.size()>=1){
            upLoadImage.uploadImg(mList_PicPath_down.get(0),6);
        }else {
            request(map_issueSkill);
        }

    }
    void uploadimgagain(int  count){
        upLoadImage.uploadImg(mList_PicPath_down.get(count),6);
    }
    void request (Map map){
        String URL="";
        if(type==1){
            URL=  Urls.Baseurl+Urls.helperIssueSkill;
        }else {
            URL=  Urls.Baseurl+Urls.shopIssueSkill;
        }
        LogUtils.LOG("ceshi",map.toString(),"发布服务的map参数");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("message");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    progressDlog.cancelPD();
                    ToastUtils.showToast(IssueSkillActivity.this,msg);
                    finish();
                }else {
                    mList_PicPath_down.clear();
                    mList_picID.clear();
                    progressDlog.cancelPD();
                    ToastUtils.showToast(IssueSkillActivity.this,msg);
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

    void choosePIC(){
        Permissionmanage permissionmanage=new Permissionmanage(permissionHelper, new InterfacePermission() {
            @Override
            public void onResult(boolean result) {
                LogUtils.LOG("ceshi",result+"","");
                if(result){
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
                            .showCamera()
                            // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                            .filePath("/ImageSelector/Pictures")
                            .build();
                    ImageSelector.open(IssueSkillActivity.this, imageConfig);   // 开启图片选择器
                }else {
                    ToastUtils.showToast(IssueSkillActivity.this,"请允许开启照相功能，并读取本地文件");
                }
            }
        });
        permissionmanage.requestpermission();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2018418 && resultCode == 2018418) {
            String address = data.getStringExtra("address");
            String address2 = data.getStringExtra("address2");
            mTextview_address.setText(address);
            mEditview_addressdetail.setText(address2);
        }


        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            ArrayList<Bitmap> dataPictrue = dataPictrue = new ArrayList<>();
            for (String path : pathList) {
                Log.i("ImagePathList", path);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap, 350, 350, true);
                dataPictrue.add(mBitmap);
                mlistdata_pic.add(0,mBitmap);
                //调用压缩图片的方法，返回压缩后的图片path
                String src_path = path;//原图片的路径
                String targetPath = Environment.getExternalStorageDirectory() + "/download/" + path + ".jpg";//压缩后图片的路径
                final String compressImage = ReducePIC.compressImage(src_path, targetPath, 30);//进行图片压缩，返回压缩后图片的路径
                List<String> mList_picpath=new ArrayList<>();
                mList_picpath.add(compressImage);
                mList_PicPath_down.add(0,mList_picpath);

            }
            PIC_mix=3-mList_PicPath_down.size();
            adapter_gridviewpic_upLoad.notifyDataSetChanged();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(progressDlog!=null){
            progressDlog.cancelPD();
        }
    }
}
