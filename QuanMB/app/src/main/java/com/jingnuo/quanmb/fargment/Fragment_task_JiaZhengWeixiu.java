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
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.IssueTaskNextActivity;
import com.jingnuo.quanmb.activity.LocationMapActivity;
import com.jingnuo.quanmb.class_.DataTime_select;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.popwinow.Popwindow_ChooseTime;
import com.jingnuo.quanmb.popwinow.Popwindow_CompleteTime;
import com.jingnuo.quanmb.popwinow.Popwindow_JiazhengweixiuTYpe;
import com.jingnuo.quanmb.popwinow.Popwindow_SkillType;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ReducePIC;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
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

public class Fragment_task_JiaZhengWeixiu extends Fragment implements View.OnClickListener {
    View rootview;
    //控件
    LinearLayout mLinearlayout_zhaoshanghu;//找商户模块
    TextView mTextview_taskAddress;//地图返回地点
    TextView mTextview_choose;
    TextView mTextview_time;
    RelativeLayout mRelativelayout_chosetime;//选择时间
    EditText mEditview_addressDetail;//详细地址
    EditText mEditview_taskdetails;
    MyGridView imageGridview;
    ImageView image_chosePIC;
    Button mButton_sub;

    //对象
    Popwindow_ChooseTime popwindow_chooseTime;
    PermissionHelper permissionHelper;
    //    Popwindow_CompleteTime popwindow_completeTime;
    DataTime_select dataTimeSelect;
    Adapter_Gridviewpic_UPLoad adapter_gridviewpic_upLoad;


    String xValue = "";//纬度
    String yValue = "";//经度
    String citycode = "";//城市名字

    String task_description = "";
    String task_typeID = "";
    String task_time = "";
    String release_address = "";
    Bitmap mBitmap = null;

    String detailed_address = "";
    int is_counteroffer = 1;//是否接受议价 1 接受  0 拒绝
    int isMEchujia = 1;//1  由我出价   2  由帮手出价
    boolean ceshi = true;
    int PIC_mix = 3;//选择图片得张数

    List<List<String>> mList_PicPath_down;//；压缩后本地图片path集合;
    Map map_issueTask;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_task_jiazhengweixiu, container, false);
        initview();
        initdata();
        setdata();
        initlistenner();


        return rootview;
    }

    private void initview() {
        //找商户模块
        mLinearlayout_zhaoshanghu = rootview.findViewById(R.id.linearlayout_zhaoshanghu);
        mTextview_taskAddress = rootview.findViewById(R.id.text_chooseaddress);
        mTextview_choose = rootview.findViewById(R.id.text_chooce);
        mTextview_time = rootview.findViewById(R.id.edit_tasktime);
        mRelativelayout_chosetime = rootview.findViewById(R.id.relative_chosetime);
        mEditview_addressDetail = rootview.findViewById(R.id.edit_detailaddress);
        mEditview_taskdetails = rootview.findViewById(R.id.edit_detailtask);
        imageGridview = rootview.findViewById(R.id.GridView_PIC);
        image_chosePIC = rootview.findViewById(R.id.image_chosePIC);
        mButton_sub = rootview.findViewById(R.id.button_submitsave);

    }

    private void initdata() {
        permissionHelper = new PermissionHelper(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        map_issueTask = new HashMap();
        mTextview_taskAddress.setText(Staticdata.aoi);
        xValue=Staticdata.xValue;
        yValue=Staticdata.yValue;
        citycode=Staticdata.city_location;
    }

    private void setdata() {
        Staticdata.mlistdata_pic.clear();//展示得 选择得图片得bitmap

        mList_PicPath_down = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.addpic);
        Staticdata.mlistdata_pic.add(bitmap);
        adapter_gridviewpic_upLoad = new Adapter_Gridviewpic_UPLoad(Staticdata.mlistdata_pic, getActivity());
        imageGridview.setAdapter(adapter_gridviewpic_upLoad);

        dataTimeSelect=new DataTime_select(getActivity(), new InterfaceDate_select() {
            @Override
            public void onResult(String time) {
                LogUtils.LOG("ceshi","时间选择器返回的结果"+time,"zhaorenshou");
                mTextview_time.setText(time);
            }
        });
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
    public void setAddress(String address){
        mTextview_taskAddress.setText(address);
    }
    private void initlistenner() {
        mTextview_choose.setOnClickListener(this);
        mTextview_taskAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent_map = new Intent(getActivity(), LocationMapActivity.class);
                startActivityForResult(mIntent_map, 2018418);
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
                map_issueTask.put("city_code", citycode + "");
                map_issueTask.put("x_value", xValue + "");
                map_issueTask.put("y_value", yValue + "");
                map_issueTask.put("user_token", Staticdata.static_userBean.getData().getUser_token() + "");
                if (initmap_zhaoshanghu()) {
                    Staticdata.map_task = map_issueTask;//借助全局变量来传递数据

                    Staticdata.imagePathlist = mList_PicPath_down;
                    Map map_check = new HashMap();
                    map_check.put("user_token", Staticdata.static_userBean.getData().getUser_token());
                    map_check.put("task_name", "假装有标题");//todo
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
                                Intent intent = new Intent(getActivity(), IssueTaskNextActivity.class);
                                intent.putExtra("issuetask","zhaoshanghu");
                                startActivity(intent);
                            } else {
                                ToastUtils.showToast(getActivity(), msg);
                            }
                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl_cui + Urls.checkissuetask, getActivity(), 1, map_check);

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
        switch (v.getId()) {
            case R.id.image_chosePIC:
                choosePIC();

                break;
            case R.id.text_chooce://选择家政维修类型
                new Popwindow_JiazhengweixiuTYpe(getActivity(), new InterfacePopwindow_SkillType() {
                    @Override
                    public void onSuccesses(String type, int id) {
                        mTextview_choose.setText(type);
                        task_typeID = id + "";
                    }
                }).showPopwindow();
//                mPopwindow_skilltype = new Popwindow_SkillType(getActivity(), new InterfacePopwindow_SkillType() {
//                    @Override
//                    public void onSuccesses(String type, int id) {
//                        mTextview_choose.setText(type);
//                        task_typeID = id + "";
//                    }
//                });
//                mPopwindow_skilltype.showPopwindow();
                break;
        }
    }

    boolean initmap_zhaoshanghu() {
        String task_type = mTextview_choose.getText() + "";
        if (task_type.equals("请选择类型")) {
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
        if (task_time.equals("请选择希望完成时间")) {
            ToastUtils.showToast(getActivity(), "请选择希望完成时间");
            return false;
        }

        release_address = mTextview_taskAddress.getText() + "";
        if (release_address.equals("选择地址")) {
            ToastUtils.showToast(getActivity(), "请选择任务地点");
            return false;
        }

        detailed_address = mEditview_addressDetail.getText() + "";
        if (detailed_address.equals("")) {
            ToastUtils.showToast(getActivity(), "请填写详细地址");
            return false;
        }
        detailed_address = address_right + detailed_address;

        if(address_right.equals("")){
            map_issueTask.put("detailed_address", release_address + "");
        }else {
            map_issueTask.put("detailed_address", address_right + "");
        }
        map_issueTask.put("task_description", task_description + "");
        map_issueTask.put("task_type", task_typeID + "");
        map_issueTask.put("task_time", task_time);
        map_issueTask.put("release_address", release_address);
        map_issueTask.put("houseNumber", detailed_address + "");

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
                    ImageSelector.open(getActivity(), imageConfig);   // 开启图片选择器
                } else {
                    ToastUtils.showToast(getActivity(), "请允许开启照相功能，并读取本地文件");
                }
            }
        });
        permissionmanage.requestpermission();
    }

    String address_left = "";
    String address_right = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2018418 && resultCode == 2018418) {
            address_left = data.getStringExtra("address");
            address_right = data.getStringExtra("address2");
            xValue = data.getStringExtra("xValue");
            yValue = data.getStringExtra("yValue");
            citycode = data.getStringExtra("citycode");
            mTextview_taskAddress.setText(address_left);
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
