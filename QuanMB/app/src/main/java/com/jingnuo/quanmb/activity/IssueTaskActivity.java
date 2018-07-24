package com.jingnuo.quanmb.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_UPLoad;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interence_complteTask_time;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.popwinow.Popwindow_CompleteTime;
import com.jingnuo.quanmb.popwinow.Popwindow_SkillType;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.MoneyTextWatcher;
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

public class IssueTaskActivity extends BaseActivityother {

    //控件
    TextView mTextview_taskAddress;//地图返回地点
    TextView mTextview_choose;
    RelativeLayout mRelativelayout_chose;//xuan 泽 类型
    TextView mTextview_time;
    RelativeLayout mRelativelayout_chosetime;//选择时间
    EditText mEditview_addressDetail;//详细地址
    EditText mEditview_title;
    EditText mEditview_taskdetails;
    MyGridView imageGridview;
    EditText mEditview_taskmoney;
    ImageView mImage_choosejieshou;
    TextView mText_choosejieshou;
    ImageView mImage_chooseme;
    ImageView mImage_choosehelper;
    TextView  mtextview_choseme;
    TextView  mtextview_chosehelper;
    RelativeLayout relativelayout_chujia;
    ImageView image_chosePIC;


    Button mButton_sub;


    //对象
    Popwindow_SkillType mPopwindow_skilltype;
    PermissionHelper permissionHelper;
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
    Bitmap mBitmap = null;

    String xValue="";//纬度
    String yValue="";//经度
    String citycode="";//城市名字

    String detailed_address = "";
    String task_Status_code = "";
    int is_counteroffer = 1;//是否接受议价 1 接受  0 拒绝
    int isMEchujia = 1;//1  由我出价   2  由帮手出价
    boolean ceshi = true;
    int PIC_mix = 3;//选择图片得张数

    List<List<String>> mList_PicPath_down;//；压缩后本地图片path集合;
    Map map_issueTask;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task;
    }

    @Override
    protected void setData() {

        Staticdata.mlistdata_pic.clear();//展示得 选择得图片得bitmap

        mList_PicPath_down = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.addpic);
        Staticdata.mlistdata_pic.add(bitmap);
        adapter_gridviewpic_upLoad = new Adapter_Gridviewpic_UPLoad(Staticdata.mlistdata_pic, this);
        imageGridview.setAdapter(adapter_gridviewpic_upLoad);
        setmapdata();
    }
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
    //可在其中解析amapLocation获取相应内容。
                    LogUtils.LOG("ceshi","定位成功"+aMapLocation.getAddress(),"发布任务");
                    xValue=aMapLocation.getLatitude()+"";//获取纬度
                    yValue=aMapLocation.getLongitude()+"";//获取经度
                    citycode=aMapLocation.getCity();//城市信息
                    String Aoi=aMapLocation.getAoiName()+"";
//                    address_right=aMapLocation.get;
                    if (Aoi.equals("")){
                        mTextview_taskAddress.setText(aMapLocation.getDescription().replace(aMapLocation.getProvince()+aMapLocation.getCity(),""));
                    }else {
                        mTextview_taskAddress.setText(Aoi);
                    }

                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                }
            }
        }
    };
    private void setmapdata() {
        //初始化定位
        mLocationClient = new AMapLocationClient(IssueTaskActivity.this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    @Override
    protected void initData() {

        permissionHelper = new PermissionHelper(IssueTaskActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        map_issueTask = new HashMap();
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
        mEditview_taskmoney.addTextChangedListener(new MoneyTextWatcher(mEditview_taskmoney).setDigits(2));
        mTextview_taskAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent_map = new Intent(IssueTaskActivity.this, LocationMapActivity.class);
                startActivityForResult(mIntent_map, 2018418);
            }
        });
        mRelativelayout_chose.setOnClickListener(new View.OnClickListener() {
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
        mRelativelayout_chosetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popwindow_completeTime.showPopwindow();

            }
        });

        mButton_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (initmap()) {

                    Staticdata.imagePathlist = mList_PicPath_down;


                    Map map_check = new HashMap();
                    map_check.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                    map_check.put("task_name", map_issueTask.get("task_name"));
                    map_check.put("task_description", map_issueTask.get("task_description"));
                    map_check.put("detailed_address", map_issueTask.get("detailed_address"));
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
                            if (status == 1) {
                                Staticdata.map_task.put("check", 1 + "");
                                LogUtils.LOG("ceshi", "图片地址的个数" + Staticdata.imagePathlist.size(), "发布任务图片");
                                Intent intent = new Intent(IssueTaskActivity.this, IssueTaskNextActivity.class);
                                startActivity(intent);
                            } else {
                                ToastUtils.showToast(IssueTaskActivity.this, msg);
                            }
                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl_cui + Urls.checkissuetask, IssueTaskActivity.this, 1, map_check);

                }
            }
        });
        imageGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi", "点击+" + position, "选择图片");
                if (Staticdata.mlistdata_pic.size() - 1 == position) {
                    choosePIC();
                } else {
                    Staticdata.mlistdata_pic.remove(position);
                    mList_PicPath_down.remove(position);//删除图片地址以便上传；
                    PIC_mix = 3 - mList_PicPath_down.size();
                    adapter_gridviewpic_upLoad.notifyDataSetChanged();
                    if(Staticdata.mlistdata_pic.size()<=1){
                        imageGridview.setVisibility(View.GONE);
                    }
                }
            }
        });
        mImage_choosejieshou.setOnClickListener(this);
        mText_choosejieshou.setOnClickListener(this);
        mImage_chooseme.setOnClickListener(this);
        mImage_choosehelper.setOnClickListener(this);
        mtextview_choseme.setOnClickListener(this);
        mtextview_chosehelper.setOnClickListener(this);
        image_chosePIC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.text_choosehelper:
                if (!mImage_choosehelper.isSelected()) {

//                    ToastUtils.showToast(IssueTaskActivity.this,"帮手出价需缴纳5元押金");
                    new Popwindow_Tip("需缴纳5元押金", IssueTaskActivity.this, new Interence_complteTask() {
                        @Override
                        public void onResult(boolean result) {
                            if(result){
                                mImage_choosehelper.setSelected(true);
                                mImage_chooseme.setSelected(false);
                                isMEchujia = 2;
                                relativelayout_chujia.setVisibility(View.GONE);
                                mEditview_taskmoney.setText("");
                                is_counteroffer = 1;
                            }

                        }
                    }).showPopwindow();

                }
            break;
            case R.id.textview_chooseme:
                if (!mImage_chooseme.isSelected()) {
                    mImage_chooseme.setSelected(true);
                    mImage_choosehelper.setSelected(false);
                    isMEchujia = 1;
                    relativelayout_chujia.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.image_choosehelper:
                if (!mImage_choosehelper.isSelected()) {
                    new Popwindow_Tip("需缴纳5元押金", IssueTaskActivity.this, new Interence_complteTask() {
                        @Override
                        public void onResult(boolean result) {
                            if(result){
                                mImage_choosehelper.setSelected(true);
                                mImage_chooseme.setSelected(false);
                                isMEchujia = 2;
                                relativelayout_chujia.setVisibility(View.GONE);
                                mEditview_taskmoney.setText("");
                                is_counteroffer = 1;
                            }

                        }
                    }).showPopwindow();


                }
                break;
            case R.id.image_chooseme:
                if (!mImage_chooseme.isSelected()) {
                    mImage_chooseme.setSelected(true);
                    mImage_choosehelper.setSelected(false);
                    isMEchujia = 1;
                    relativelayout_chujia.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.image_choosejieshou:
                if (!mImage_choosejieshou.isSelected()) {
                    mImage_choosejieshou.setSelected(true);
                    is_counteroffer = 1;
                }else {
                    mImage_choosejieshou.setSelected(false);
                    is_counteroffer = 0;
                }
                break;

            case R.id.text_choosejieshou:
                if (!mImage_choosejieshou.isSelected()) {
                    mImage_choosejieshou.setSelected(true);
                    is_counteroffer = 1;
                }else {
                    mImage_choosejieshou.setSelected(false);
                    is_counteroffer = 0;
                }
                break;
            case R.id.image_chosePIC:
                choosePIC();

                break;
        }
    }

    @Override
    protected void initView() {
        mTextview_taskAddress = findViewById(R.id.text_chooseaddress);
        mTextview_choose = findViewById(R.id.text_chooce);
        mEditview_title = findViewById(R.id.edit_tasktitle);
        mTextview_time = findViewById(R.id.edit_tasktime);
        mRelativelayout_chose=findViewById(R.id.relative_chose);
        mRelativelayout_chosetime=findViewById(R.id.relative_chosetime);
        mEditview_addressDetail = findViewById(R.id.edit_detailaddress);
        mEditview_taskdetails = findViewById(R.id.edit_detailtask);
        imageGridview = findViewById(R.id.GridView_PIC);
        mEditview_taskmoney = findViewById(R.id.edit_charges);
        mButton_sub = findViewById(R.id.button_submitsave);
        mImage_choosejieshou = findViewById(R.id.image_choosejieshou);
        mImage_choosejieshou.setSelected(true);
        mText_choosejieshou = findViewById(R.id.text_choosejieshou);
        mImage_chooseme = findViewById(R.id.image_chooseme);
        mImage_chooseme.setSelected(true);
        mtextview_choseme = findViewById(R.id.textview_chooseme);
        mtextview_chosehelper = findViewById(R.id.text_choosehelper);
        mImage_choosehelper = findViewById(R.id.image_choosehelper);
        relativelayout_chujia = findViewById(R.id.relativelayout_chujia);
        image_chosePIC=findViewById(R.id.image_chosePIC);
    }

    boolean initmap() {
        String task_type = mTextview_choose.getText() + "";
        if (task_type.equals("请选择类型")) {
            ToastUtils.showToast(this, "请选择任务类型");
            return false;
        }
        task_name = mEditview_title.getText() + "";
        if (task_name.equals("")) {
            ToastUtils.showToast(this, "请填写任务标题");
            return false;
        }
        if(task_name.length()>15){
            ToastUtils.showToast(this, "任务标题有点长");
            return false;
        }
        task_description = mEditview_taskdetails.getText() + "";
        if (task_description.equals("")) {
            ToastUtils.showToast(this, "请填写任务说明");
            return false;
        }
        if(task_description.length()<5){
            ToastUtils.showToast(this, "任务说明太短了");
            return false;
        }

        task_time = mTextview_time.getText() + "";
        if (task_time.equals("请选择希望完成时间")) {
            ToastUtils.showToast(this, "请选择希望完成时间");
            return false;
        }
        task_time = mTextview_time.getTag() + "";

        release_address = mTextview_taskAddress.getText()+"";
        if(release_address.equals("选择地址")){
            ToastUtils.showToast(this, "请选择任务地点");
            return false;
        }

        detailed_address = mEditview_addressDetail.getText() + "";
        if (detailed_address.equals("")) {
            ToastUtils.showToast(this, "请填写详细地址");
            return false;
        }
        detailed_address=address_right+detailed_address;


        commission = mEditview_taskmoney.getText() + "";
        if (!commission.equals("")) {
            float min = Float.parseFloat(commission);
            map_issueTask.put("is_helper_bid", "N");//由我出价
            LogUtils.LOG("ceshi", min + "", "最低佣金");
            if (min < 5) {
                ToastUtils.showToast(IssueTaskActivity.this, "佣金不能低于5元");
                return false;
            }
        } else {
            if (isMEchujia == 1) {
                map_issueTask.put("is_helper_bid", "N");
                ToastUtils.showToast(IssueTaskActivity.this, "请填写佣金");
                return false;
            }else{
                commission="5";
                map_issueTask.put("is_helper_bid", "Y");//由帮手出价

            }
        }
        map_issueTask.put("task_name", task_name + "");
        map_issueTask.put("task_description", task_description + "");
        map_issueTask.put("task_type", task_typeID + "");
        map_issueTask.put("task_time", task_time);
        map_issueTask.put("commission", commission+"");//由帮手出价
        map_issueTask.put("task_time_no", mTextview_time.getText() + "");//发布任务不用  确认界面使用该参数
        map_issueTask.put("user_token", Staticdata.static_userBean.getData().getUser_token() + "");
        map_issueTask.put("release_address", release_address);
        map_issueTask.put("detailed_address", detailed_address + "");
        map_issueTask.put("is_counteroffer", is_counteroffer + "");
        map_issueTask.put("city_code", citycode + "");
        map_issueTask.put("x_value", xValue + "");
        map_issueTask.put("y_value", yValue + "");
        Staticdata.map_task = map_issueTask;//借助全局变量来传递数据
        Staticdata.map_task.put("tasktypename", task_type);

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
                    ImageSelector.open(IssueTaskActivity.this, imageConfig);   // 开启图片选择器
                } else {
                    ToastUtils.showToast(IssueTaskActivity.this, "请允许开启照相功能，并读取本地文件");
                }
            }
        });
        permissionmanage.requestpermission();
    }
    String address_left="";
    String address_right="";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2018418 && resultCode == 2018418) {
            address_left= data.getStringExtra("address");
            address_right = data.getStringExtra("address2");
            xValue = data.getStringExtra("xValue");
            yValue = data.getStringExtra("yValue");
            citycode = data.getStringExtra("citycode");
            mTextview_taskAddress.setText(address_left);
        }

        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            // Get Image Path List
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

//            ArrayList<Bitmap> dataPictrue = dataPictrue = new ArrayList<>();
            for (String path : pathList) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                mBitmap = Bitmap.createScaledBitmap(bitmap, 350, 350, true);
//                dataPictrue.add(mBitmap);
                Staticdata.mlistdata_pic.add(0, mBitmap);
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
            adapter_gridviewpic_upLoad.notifyDataSetChanged();
            if(Staticdata.mlistdata_pic.size()>1){
                imageGridview.setVisibility(View.VISIBLE);
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
         LogUtils.LOG("ceshi","onDestroy","faburenwu");
        mLocationClient.onDestroy();//调用定位结束方法
    }
}
