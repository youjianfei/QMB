package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.InterfaceDate_select;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.class_.DataTime_select;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.class_.Popwindow_SkillType;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
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

import static com.jingnuo.quanmb.data.Staticdata.map_task;

public class IssueTaskActivity extends BaseActivityother {

    //控件
    TextView mTextview_taskAddress;//地图返回地点
    TextView mTextview_choose;
    TextView mTextview_time;
    EditText mEditview_addressDetail;//详细地址
    EditText mEditview_title;
    EditText mEditview_taskdetails;
    EditText mEditview_taskmoney;
    ImageView mImage_choosejieshou;
    ImageView mImage_choosejujue;


    Button mButton_sub;

    ImageView choosePIC1;
    ImageView choosePIC2;
    ImageView choosePIC3;

    //对象
    Popwindow_SkillType mPopwindow_skilltype;
    PermissionHelper permissionHelper;

    //数据
    String task_name = "";
    String task_description = "";
    String task_typeID = "";
    String task_StartDate = "";
    String task_EndDate = "";
    String client_no = "";
    String release_address = "";
    String commission = "";
    String img_id="";//图片
    String detailed_address = "";
    String task_Status_code = "";
    int  is_counteroffer=1;//是否接受议价 1 接受  2 拒绝
    boolean ceshi=true;

    int PICposition=0;
    List<String> mList_picID;
    Map map_issueTask;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        permissionHelper=new PermissionHelper(IssueTaskActivity.this,new  String []{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},100);
        map_issueTask=new HashMap();
        mList_picID=new ArrayList<>();
    }

    @Override
    protected void initListener() {
        mTextview_taskAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent_map = new Intent(IssueTaskActivity.this, LocationMapActivity.class);
                startActivityForResult(mIntent_map, 2018418);
            }
        });
        mTextview_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopwindow_skilltype = new Popwindow_SkillType(IssueTaskActivity.this, new InterfacePopwindow_SkillType() {
                    @Override
                    public void onSuccesses(String type, int id) {
                        mTextview_choose.setText(type);
                        task_typeID=id+"";
                    }
                });
                mPopwindow_skilltype.showPopwindow();
            }
        });
        mTextview_time.setOnClickListener(new View.OnClickListener() {//时间选择器  选择预约时间
            @Override
            public void onClick(View view) {
                DataTime_select.getIntence(IssueTaskActivity.this, new InterfaceDate_select() {
                    @Override
                    public void onResult(final String time) {
                        LogUtils.LOG("ceshi","时间选择器+"+mTextview_time.toString(),"时间团择期");

                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           mTextview_time.setText(time);//todo 界面销毁后复制无效
                       }
                   });

                    }
                }).timeSelect(IssueTaskActivity.this);
            }
        });

        mButton_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(initmap()){
                    Intent intent=new Intent(IssueTaskActivity.this,IssueTaskNextActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        choosePIC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PICposition=1;
                choosePIC();//选择图片
            }
        });
        choosePIC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PICposition=2;

                choosePIC();//选择图片
            }
        });
        choosePIC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePIC();//选择图片
                PICposition=3;
            }
        });
        mImage_choosejieshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mImage_choosejieshou.isSelected()){
                    mImage_choosejieshou.setSelected(true);
                    mImage_choosejujue.setSelected(false);
                    is_counteroffer=1;
                }
            }
        });
        mImage_choosejujue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mImage_choosejujue.isSelected()){
                    mImage_choosejieshou.setSelected(false);
                    mImage_choosejujue.setSelected(true);
                    is_counteroffer=0;
                }
            }
        });

    }

    @Override
    protected void initView() {
        mTextview_taskAddress = findViewById(R.id.text_chooseaddress);
        mTextview_choose = findViewById(R.id.text_chooce);
        mEditview_title = findViewById(R.id.edit_tasktitle);
        mTextview_time = findViewById(R.id.edit_tasktime);
        mEditview_addressDetail = findViewById(R.id.edit_detailaddress);
        mEditview_taskdetails = findViewById(R.id.edit_detailtask);
        mEditview_taskmoney = findViewById(R.id.edit_charges);
        mButton_sub = findViewById(R.id.button_submitsave);
        choosePIC1=findViewById(R.id.iamge_choosePIC1);
        choosePIC2=findViewById(R.id.iamge_choosePIC2);
        choosePIC3=findViewById(R.id.iamge_choosePIC3);
        mImage_choosejieshou=findViewById(R.id.image_choosejieshou);
        mImage_choosejieshou.setSelected(true);
        mImage_choosejujue=findViewById(R.id.image_choosejujue);
    }
    boolean initmap(){
        task_name=mEditview_title.getText()+"";
        if(task_name.equals("")){
            ToastUtils.showToast(this,"请填写任务标题");
            return false;
        }
        task_description=mEditview_taskdetails.getText()+"";
        if(task_description.equals("")){
            ToastUtils.showToast(this,"请填写任务说明");
            return false;
            

        }
        String  task_type=mTextview_choose.getText()+"";
        if(task_type.equals("请选择")){
            ToastUtils.showToast(this,"请选择任务类型");
            return  false;
        }
        task_EndDate=mTextview_time.getText()+"";
        if(task_StartDate.equals("请选择服务预约时间")){
            ToastUtils.showToast(this,"请选择预约时间");
            return false;
        }
        release_address="郑州";//TODO
        commission=mEditview_taskmoney.getText()+"";
        if(commission.equals("")){
            ToastUtils.showToast(this,"请填写任务佣金");
            return  false;
        }
        detailed_address=mEditview_addressDetail.getText()+"";
        if (mEditview_addressDetail.equals("")){
            ToastUtils.showToast(this,"请填写详细地址");
            return false;
        }

        for (String imageID : mList_picID) {
            img_id=img_id+imageID+"%";
        }
        map_issueTask.put("task_name",task_name+"");
        map_issueTask.put("task_description",task_description+"");
        map_issueTask.put("task_type",task_typeID+"");
        map_issueTask.put("task_EndDate",task_EndDate);
        map_issueTask.put("user_token", Staticdata.static_userBean.getData().getUser_token()+"");
        map_issueTask.put("release_address","郑州");//TODO 地区
        map_issueTask.put("commission",commission+"");
        map_issueTask.put("task_Img_id",img_id+"");
        map_issueTask.put("detailed_address",detailed_address+"");
        map_issueTask.put("is_counteroffer",is_counteroffer+"");
        Staticdata. map_task=map_issueTask;//借助全局变量来传递数据   //TODO 后期更改
        LogUtils.LOG("ceshi",map_issueTask.toString(),"发布任务map集合中的内容");


        return true;
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
                            .singleSelect()
                            .showCamera()
                            // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                            .filePath("/ImageSelector/Pictures")
                            .build();
                    ImageSelector.open(IssueTaskActivity.this, imageConfig);   // 开启图片选择器
                }else {
                    ToastUtils.showToast(IssueTaskActivity.this,"请允许开启照相功能，并读取本地文件");
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
            mTextview_taskAddress.setText(address);
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
                switch (PICposition){
                    case 1:
                        choosePIC1.setImageBitmap(mBitmap);
                        break;
                    case 2:
                        choosePIC2.setImageBitmap(mBitmap);
                        break;
                    case 3:
                        choosePIC3.setImageBitmap(mBitmap);
                        break;
                }
                UpLoadImage.getIntence(IssueTaskActivity.this, new Interface_loadImage_respose() {
                    @Override
                    public void onSuccesses(String respose) {
                        LogUtils.LOG("ceshi",respose,"发布技能上传图片返回respose");
                        int status = 0;
                        String msg = "";
                        String imageID="";
                        try {
                            JSONObject object = new JSONObject(respose);
                            status = (Integer) object.get("code");//登录状态
                            msg = (String) object.get("msg");//登录返回信息

                            if(status==1){
                                imageID=(String) object.get("imgID");
                                LogUtils.LOG("ceshi","单张图片ID"+imageID,"发布技能上传图片返回imageID");
                                mList_picID.add(imageID);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).uploadImg(pathList,1);

            }
        }

    }
}
