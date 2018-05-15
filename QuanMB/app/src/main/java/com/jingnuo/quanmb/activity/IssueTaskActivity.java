package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_UPLoad;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interence_complteTask_time;
import com.jingnuo.quanmb.Interface.InterfaceDate_select;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.class_.DataTime_select;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.class_.Popwindow_CompleteTime;
import com.jingnuo.quanmb.class_.Popwindow_SkillType;
import com.jingnuo.quanmb.class_.Popwindow_complatetask;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.customview.MyGridView;
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
    MyGridView imageGridview;
    EditText mEditview_taskmoney;
    ImageView mImage_choosejieshou;
    ImageView mImage_choosejujue;
    ImageView mImage_chooseme;
    ImageView mImage_choosehelper;
    RelativeLayout relativelayout_chujia;


    Button mButton_sub;


    //对象
    Popwindow_SkillType mPopwindow_skilltype;
    PermissionHelper permissionHelper;
    UpLoadImage upLoadImage;
    Popwindow_CompleteTime popwindow_completeTime;
    Adapter_Gridviewpic_UPLoad adapter_gridviewpic_upLoad;

    //数据
    String task_name = "";
    String task_description = "";
    String task_typeID = "";
    String task_StartDate = "";
    String task_time = "";
    String client_no = "";
    String release_address = "";
    String commission = "";
    String img_id = "";//图片
    Bitmap mBitmap=null;
    List<Bitmap> mlistdata_pic;
    String detailed_address = "";
    String task_Status_code = "";
    int is_counteroffer = 1;//是否接受议价 1 接受  0 拒绝
    int isMEchujia = 1;//1  由我出价   2  由帮手出价
    boolean ceshi = true;

    List<String> mList_picID;
    Map map_issueTask;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task;
    }

    @Override
    protected void setData() {
        mlistdata_pic=new ArrayList<>();
        Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(), R.mipmap.addpic);
        mlistdata_pic.add(bitmap);
        adapter_gridviewpic_upLoad=new Adapter_Gridviewpic_UPLoad(mlistdata_pic,this);
        imageGridview.setAdapter(adapter_gridviewpic_upLoad);
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
                        mList_picID.add(imageID);
                        LogUtils.LOG("ceshi", mList_picID.size() + "tupiangeshu", "发布技能上传图片返回imageID333");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void initData() {
        permissionHelper = new PermissionHelper(IssueTaskActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        map_issueTask = new HashMap();
        mList_picID = new ArrayList<>();
        //完成任务期限  弹窗
        popwindow_completeTime = new Popwindow_CompleteTime(IssueTaskActivity.this, new Interence_complteTask_time() {
            @Override
            public void onResult(String result, int tag) {
                LogUtils.LOG("ceshi", result, "IssueTaskActivity");
                mTextview_time.setText(result);
                mTextview_time.setTag(tag);
            }
        });
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
                        task_typeID = id + "";
                    }
                });
                mPopwindow_skilltype.showPopwindow();
            }
        });
        mTextview_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popwindow_completeTime.showPopwindow();

            }
        });

        mButton_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (initmap()) {
                    Intent intent = new Intent(IssueTaskActivity.this, IssueTaskNextActivity.class);
                    startActivity(intent);
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
                    adapter_gridviewpic_upLoad.notifyDataSetChanged();
                }

            }
        });
        mImage_choosejieshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mImage_choosejieshou.isSelected()) {
                    mImage_choosejieshou.setSelected(true);
                    mImage_choosejujue.setSelected(false);
                    is_counteroffer = 1;
                }
            }
        });
        mImage_choosejujue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mImage_choosejujue.isSelected()) {
                    mImage_choosejieshou.setSelected(false);
                    mImage_choosejujue.setSelected(true);
                    is_counteroffer = 0;
                }
            }
        });

        mImage_chooseme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mImage_chooseme.isSelected()) {
                    mImage_chooseme.setSelected(true);
                    mImage_choosehelper.setSelected(false);
                    isMEchujia = 1;
                    relativelayout_chujia.setVisibility(View.VISIBLE);
                }
            }
        });
        mImage_choosehelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mImage_choosehelper.isSelected()) {
                    mImage_choosehelper.setSelected(true);
                    mImage_chooseme.setSelected(false);
                    isMEchujia = 2;
                    map_issueTask.put("is_helper_bid",   "Y");//由帮手出价
                    relativelayout_chujia.setVisibility(View.GONE);
                    mEditview_taskmoney.setText("");
                    is_counteroffer = 1;
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
        imageGridview=findViewById(R.id.GridView_PIC);
        mEditview_taskmoney = findViewById(R.id.edit_charges);
        mButton_sub = findViewById(R.id.button_submitsave);
        mImage_choosejieshou = findViewById(R.id.image_choosejieshou);
        mImage_choosejieshou.setSelected(true);
        mImage_choosejujue = findViewById(R.id.image_choosejujue);
        mImage_chooseme = findViewById(R.id.image_chooseme);
        mImage_chooseme.setSelected(true);
        mImage_choosehelper = findViewById(R.id.image_choosehelper);
        relativelayout_chujia = findViewById(R.id.relativelayout_chujia);
    }

    boolean initmap() {
        task_name = mEditview_title.getText() + "";
        if (task_name.equals("")) {
            ToastUtils.showToast(this, "请填写任务标题");
            return false;
        }
        task_description = mEditview_taskdetails.getText() + "";
        if (task_description.equals("")) {
            ToastUtils.showToast(this, "请填写任务说明");
            return false;
        }
        String task_type = mTextview_choose.getText() + "";
        if (task_type.equals("请选择")) {
            ToastUtils.showToast(this, "请选择任务类型");
            return false;
        }

        if (task_StartDate.equals("请选择任务完成期限")) {
            ToastUtils.showToast(this, "请选择任务完成期限");
            return false;
        }
        task_time = mTextview_time.getTag() + "";

        release_address = "郑州";//TODO

        detailed_address = mEditview_addressDetail.getText() + "";
        if (mEditview_addressDetail.equals("")) {
            ToastUtils.showToast(this, "请填写详细地址");
            return false;
        }
        LogUtils.LOG("ceshi", mList_picID.size() + ".............", "tupianOD");
        for (String imageID : mList_picID) {
            img_id = img_id + imageID + ",";
        }
        commission = mEditview_taskmoney.getText() + "";
        if (!commission.equals("")) {
            float min = Float.parseFloat(commission);
            map_issueTask.put("is_helper_bid",   "N");//由我出价
            LogUtils.LOG("ceshi", min + "", "sfdsfsaf");
            if (min < 5) {
                ToastUtils.showToast(IssueTaskActivity.this, "佣金最低为5元");
                return false;
            }
        } else {
            if (isMEchujia == 1) {
                map_issueTask.put("is_helper_bid",   "N");
                ToastUtils.showToast(IssueTaskActivity.this, "请填写佣金");
                return false;
            }
        }
        map_issueTask.put("task_name", task_name + "");
        map_issueTask.put("task_description", task_description + "");
        map_issueTask.put("task_type", task_typeID + "");
        map_issueTask.put("task_time", task_time);
        map_issueTask.put("user_token", Staticdata.static_userBean.getData().getUser_token() + "");
        map_issueTask.put("release_address", "郑州");//TODO 地区
        map_issueTask.put("commission", commission + "");
        map_issueTask.put("task_Img_id", img_id + "");
        map_issueTask.put("detailed_address", detailed_address + "");
        map_issueTask.put("is_counteroffer", is_counteroffer + "");
        Staticdata.map_task = map_issueTask;//借助全局变量来传递数据
        LogUtils.LOG("ceshi", map_issueTask.toString(), "发布任务map集合中的内容");


        return true;
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
                            // 开启单选   （默认为多选）
                            .singleSelect()
                            .showCamera()
                            // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                            .filePath("/ImageSelector/Pictures")
                            .build();
                    ImageSelector.open(IssueTaskActivity.this, imageConfig);   // 开启图片选择器
                } else {
                    ToastUtils.showToast(IssueTaskActivity.this, "请允许开启照相功能，并读取本地文件");
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
//            ArrayList<Bitmap> dataPictrue = dataPictrue = new ArrayList<>();
            for (String path : pathList) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                 mBitmap = Bitmap.createScaledBitmap(bitmap, 350, 350, true);
//                dataPictrue.add(mBitmap);
                mlistdata_pic.add(0,mBitmap);
                adapter_gridviewpic_upLoad.notifyDataSetChanged();
                upLoadImage.uploadImg(pathList, 2);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        upLoadImage = null;
    }

}
