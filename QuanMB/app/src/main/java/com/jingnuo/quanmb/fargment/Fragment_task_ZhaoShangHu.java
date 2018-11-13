package com.jingnuo.quanmb.fargment;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_UPLoad;
import com.jingnuo.quanmb.Interface.Interence_complteTask_time;
import com.jingnuo.quanmb.Interface.InterfaceDate_select;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.IssueTaskNextActivity;
import com.jingnuo.quanmb.activity.LocationMapActivity;
import com.jingnuo.quanmb.activity.LoginActivity;
import com.jingnuo.quanmb.activity.MatchShopActivity;
import com.jingnuo.quanmb.class_.DataTime_select;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.popwinow.Popwindow_ChooseTime;
import com.jingnuo.quanmb.popwinow.Popwindow_CompleteTime;
import com.jingnuo.quanmb.popwinow.Popwindow_SkillType;
import com.jingnuo.quanmb.popwinow.ProgressDlog;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ReducePIC;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.master.permissionhelper.PermissionHelper;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class Fragment_task_ZhaoShangHu extends Fragment implements View.OnClickListener {
    View rootview;
    //控件
    LinearLayout mLinearlayout_zhaoshanghu;//找商户模块
//    TextView mTextview_taskAddress;//地图返回地点
    TextView mTextview_choose;
    RelativeLayout mRelativelayout_chose;//选择类型
    TextView mTextview_time;
    RelativeLayout mRelativelayout_chosetime;//选择时间
//    EditText mEditview_addressDetail;//详细地址
    EditText mEditview_taskdetails;
    MyGridView imageGridview;
    ImageView image_chosePIC;
    Button mButton_sub;

    //对象
    Popwindow_SkillType mPopwindow_skilltype;
    Popwindow_ChooseTime popwindow_chooseTime;
    PermissionHelper permissionHelper;
//    Popwindow_CompleteTime popwindow_completeTime;
//    DataTime_select dataTimeSelect;
    Adapter_Gridviewpic_UPLoad adapter_gridviewpic_upLoad;
    KProgressHUD mKProgressHUD;

    String xValue = "";//纬度
    String yValue = "";//经度
    String citycode = "";//城市名字

    String task_description = "";
    String task_typeID = "";
    String task_time = "";
    String release_address = "";
    Bitmap mBitmap = null;

//    String detailed_address = "";

    int PIC_mix = 3;//选择图片得张数
    List<String> mList_picID;// 上传图片返回ID;
    int count = 0;//图片的张数。判断调用几次上传图片接口
    String img_id = "";//图片

    List<List<String>> mList_PicPath_down;//；压缩后本地图片path集合;
    Map map_issueTask;

    UpLoadImage upLoadImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_task_zhaoshanghu, container, false);
        initview();
        initdata();
        setdata();
        initlistenner();


        return rootview;
    }

    private void initview() {
        //找商户模块
        mLinearlayout_zhaoshanghu = rootview.findViewById(R.id.linearlayout_zhaoshanghu);
//        mTextview_taskAddress = rootview.findViewById(R.id.text_chooseaddress);
        mTextview_choose = rootview.findViewById(R.id.text_chooce);
        mTextview_time = rootview.findViewById(R.id.edit_tasktime);
        mRelativelayout_chose = rootview.findViewById(R.id.relative_chose);
        mRelativelayout_chosetime = rootview.findViewById(R.id.relative_chosetime);
//        mEditview_addressDetail = rootview.findViewById(R.id.edit_detailaddress);
        mEditview_taskdetails = rootview.findViewById(R.id.edit_detailtask);
        imageGridview = rootview.findViewById(R.id.GridView_PIC);
        image_chosePIC = rootview.findViewById(R.id.image_chosePIC);
        mButton_sub = rootview.findViewById(R.id.button_submitsave);
    }

    private void initdata() {
        mKProgressHUD = new KProgressHUD(getActivity());
        permissionHelper = new PermissionHelper(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        map_issueTask = new HashMap();
//        if(!Staticdata.aoi.equals("")){
//            mTextview_taskAddress.setText(Staticdata.aoi);
//        }



        upLoadImage = new UpLoadImage(getActivity(), new Interface_loadImage_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "发布技能上传图片返回respose");
                if (respose.equals("erro")) {
//                    progressDlog.cancelPD();
                    mKProgressHUD.dismiss();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast(getActivity(), "网络开小差儿，请重新提交");
                        }
                    });
                    mList_picID.clear();
                    return;
                }
                int status = 0;
                String msg = "";
                String imageID = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("msg");//登录返回信息

                    if (status == 1) {
                        count++;
                        imageID = (String) object.get("imgID");
                        LogUtils.LOG("ceshi", "单张图片ID" + imageID, "发布技能上传图片返回imageID");
                        mList_picID.add(0, imageID);
                        LogUtils.LOG("ceshi", mList_picID.size() + "tupiangeshu", "发布技能上传图片返回imageID333");
                        if (count != Staticdata.imagePathlist.size()) {
                            uploadimgagain(count);
                        } else {
                            for (String image : mList_picID) {
                                img_id = img_id + image + ",";
                            }
                            Staticdata.map_task.put("task_Img_id", img_id);
                            LogUtils.LOG("ceshi", "上传图片完成", "发布技能上传图片");
                            requestTaskid();
//                            requast(Staticdata.map_task);//正式发布任务
                        }
                    } else {
//                        progressDlog.cancelPD();
                        mKProgressHUD.dismiss();
                        mList_picID.clear();
                        final String finalMsg = msg;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(getActivity(), finalMsg);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    void uploadimg() {
        if (Staticdata.imagePathlist.size() >= 1) {
            upLoadImage.uploadImg(Staticdata.imagePathlist.get(0), 2,"Y");
        } else {
            requestTaskid();
        }

    }

    void uploadimgagain(int count) {
        upLoadImage.uploadImg(Staticdata.imagePathlist.get(count), 2,"Y");
    }
    void requestTaskid() {//请求任务号,
        LogUtils.LOG("ceshi", Urls.Baseurl_cui + Urls.gettaskid
                + Staticdata.static_userBean.getData().getUser_token(), "获取任务ID");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "获取任务ID");
//                {"code":1,"date":151,"message":"获取成功"}
                int status = 0;
                String msg = "";
                int data = 0;
                try {
                    JSONObject object = new JSONObject(respose);
                    data = (Integer) object.get("data");//
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("message");//
                    if (status == 1) {
                        Staticdata.map_task.put("task_id", data + "");

                            requast_zhaoshanghu(Staticdata.map_task);

                    } else {
                        ToastUtils.showToast(getActivity(), msg);
//                        progressDlog.cancelPD();
                        mKProgressHUD.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui + Urls.gettaskid
                + Staticdata.static_userBean.getData().getUser_token(), getActivity(), 0);
    }
    void requast_zhaoshanghu(Map map) {//正式发布匹配任务
        LogUtils.LOG("ceshi", Staticdata.map_task.toString(), "发布任务的map参数");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
//                progressDlog.cancelPD();
                mKProgressHUD.dismiss();

                LogUtils.LOG("ceshi", "发布任务返回json"+respose, "发布任务");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("message");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {
                    Intent intent = new Intent(getActivity(), MatchShopActivity.class);
                    intent.putExtra("respose",respose);
                    intent.putExtra("id",Staticdata.map_task.get("task_id")+"");
                    startActivity(intent);

                    Staticdata.imagePathlist.clear();
                    Staticdata.map_task.clear();

                } else {
                    ToastUtils.showToast(getActivity(),"附近没有此类型商户");
                    count = 0;
                    mList_picID.clear();
                    mKProgressHUD.dismiss();
                    ToastUtils.showToast(getActivity(), msg);
                }

            }

            @Override
            public void onError(int error) {
//                progressDlog.cancelPD();
                mKProgressHUD.dismiss();
                count = 0;
                mList_picID.clear();
            }
        }).postHttp(Urls.Baseurl_cui + Urls.issuetask_zhaoshanghu, getActivity(), 1, map);
    }
    private void setdata() {
        Staticdata.mlistdata_pic.clear();//展示得 选择得图片得bitmap
        mList_picID = new ArrayList<>();

        mList_PicPath_down = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.addpic);
        Staticdata.mlistdata_pic.add(bitmap);
        adapter_gridviewpic_upLoad = new Adapter_Gridviewpic_UPLoad(Staticdata.mlistdata_pic, getActivity());
        imageGridview.setAdapter(adapter_gridviewpic_upLoad);

    }

    public void setview(Intent data) {
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
            String targetPath = Environment.getExternalStorageDirectory() + "/picyasuo/" + System.currentTimeMillis() + ".png";//压缩后图片的路径
            final String compressImage = ReducePIC.compressImage(src_path, targetPath, 30);//进行图片压缩，返回压缩后图片的路径
            List<String> mList_picpath = new ArrayList<>();
            mList_picpath.add(compressImage);
            mList_PicPath_down.add(0, mList_picpath);
        }
        PIC_mix = 3 - mList_PicPath_down.size();
        adapter_gridviewpic_upLoad.notifyDataSetChanged();
        if (Staticdata.mlistdata_pic.size() > 1) {
            imageGridview.setVisibility(View.VISIBLE);
        }
    }
    String address_left = "";
    String address_right = "";
    public void setAddress(Intent data){
        address_left = data.getStringExtra("address");
        address_right = data.getStringExtra("address2");
        xValue = data.getStringExtra("xValue");
        yValue = data.getStringExtra("yValue");
        citycode = data.getStringExtra("citycode");
        Staticdata.aoi=address_left;
    }

    private void initlistenner() {
//        mTextview_taskAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent_map = new Intent(getActivity(), LocationMapActivity.class);
//                startActivityForResult(mIntent_map, 2018418);
//            }
//        });
        mRelativelayout_chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopwindow_skilltype = new Popwindow_SkillType(getActivity(), new InterfacePopwindow_SkillType() {
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
//                dataTimeSelect.timeSelect(getActivity());
                popwindow_chooseTime=new Popwindow_ChooseTime(getActivity(), new InterfaceDate_select() {
                    @Override
                    public void onResult(String time) {
                        mTextview_time.setText(time);
                    }
                });
                popwindow_chooseTime.showPopwindow();
            }
        });

        mButton_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Staticdata.isLogin){
                    xValue=Staticdata.xValue;
                    yValue=Staticdata.yValue;
                    citycode=Staticdata.city_location;
                    map_issueTask.put("city_code", citycode + "");
                    map_issueTask.put("x_value", xValue + "");
                    map_issueTask.put("y_value", yValue + "");
                    map_issueTask.put("user_token", Staticdata.static_userBean.getData().getUser_token() + "");
                    if (initmap_zhaoshanghu()) {
                        ProgressDlog.showProgress(mKProgressHUD);
                        Staticdata.map_task = map_issueTask;//借助全局变量来传递数据

                        Staticdata.imagePathlist = mList_PicPath_down;
                        Map map_check = new HashMap();
                        map_check.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                        map_check.put("task_description", map_issueTask.get("task_description"));
//                    map_check.put("houseNumber", map_issueTask.get("houseNumber"));
                        new Volley_Utils(new Interface_volley_respose() {
                            @Override
                            public void onSuccesses(String respose) {
                                int status = 0;
                                String msg = "";
//                            mKProgressHUD.dismiss();
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
//                                Intent intent = new Intent(getActivity(), IssueTaskNextActivity.class);
//                                intent.putExtra("issuetask","zhaoshanghu");
//                                startActivity(intent);
                                    mList_picID.clear();
                                    count = 0;
                                    uploadimg();

                                } else {
                                    ToastUtils.showToast(getActivity(), msg);
                                    mKProgressHUD.dismiss();
                                }
                            }

                            @Override
                            public void onError(int error) {
                                mKProgressHUD.dismiss();
                            }
                        }).postHttp(Urls.Baseurl_cui + Urls.checkissuetask, getActivity(), 1, map_check);

                    }
                }else {
                    Intent   intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
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
                    if (Staticdata.mlistdata_pic.size() <= 1) {
                        imageGridview.setVisibility(View.GONE);
                    }
                }
            }
        });
        image_chosePIC.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_chosePIC:
                choosePIC();

                break;
        }
    }

    boolean initmap_zhaoshanghu() {
        String task_type = mTextview_choose.getText() + "";
        if (task_type.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择任务类型");
            return false;
        }
        task_description = mEditview_taskdetails.getText() + "";
        if (task_description.equals("")) {
            ToastUtils.showToast(getActivity(), "请填写任务说明");
            return false;
        }
        if (task_description.length() < 5) {
            ToastUtils.showToast(getActivity(), "任务说明太短了");
            return false;
        }

        task_time = mTextview_time.getText() + "";


        release_address = address_left + "";

        if (release_address.equals("")) {
            release_address=Staticdata.aoi;
        }
        if (release_address.equals("选择地址")||release_address.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择任务地址");
            return false;
        }

//        detailed_address = mEditview_addressDetail.getText() + "";
//        if (detailed_address.equals("")) {
//            ToastUtils.showToast(getActivity(), "请填写详细地址");
//            return false;
//        }
        if(address_right.equals("")){
            map_issueTask.put("detailed_address", release_address + "");
        }else {
            map_issueTask.put("detailed_address", address_right + "");
        }
        map_issueTask.put("task_description", task_description + "");
        map_issueTask.put("task_type", task_typeID + "");
        map_issueTask.put("task_time", task_time);
        map_issueTask.put("release_address", release_address);
//        map_issueTask.put("houseNumber", detailed_address + "");


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
                    ImageSelector.open(getActivity(), imageConfig);   // 开启图片选择器
                } else {
                    ToastUtils.showToast(getActivity(), "请允许开启照相功能，并读取本地文件");
                }
            }
        });
        permissionmanage.requestpermission();
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
