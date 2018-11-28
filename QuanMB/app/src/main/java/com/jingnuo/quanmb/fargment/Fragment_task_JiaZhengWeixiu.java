package com.jingnuo.quanmb.fargment;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
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

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_UPLoad;
import com.jingnuo.quanmb.Adapter.Adapter_WeixiuJiazheng;
import com.jingnuo.quanmb.Interface.Interence_complteTask_time;
import com.jingnuo.quanmb.Interface.InterfaceBaiduAddress;
import com.jingnuo.quanmb.Interface.InterfaceDate_select;
import com.jingnuo.quanmb.Interface.InterfacePermission;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_descriptionType;
import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.IssueTaskNextActivity;
import com.jingnuo.quanmb.activity.LocationMapActivity;
import com.jingnuo.quanmb.activity.LoginActivity;
import com.jingnuo.quanmb.activity.LoginActivityPhone;
import com.jingnuo.quanmb.activity.MatchShopActivity;
import com.jingnuo.quanmb.class_.DataTime_select;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.Permissionmanage;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.WeixiuJiazhengBean;
import com.jingnuo.quanmb.popwinow.Popwindow_ChooseTime;
import com.jingnuo.quanmb.popwinow.Popwindow_descriptionType;
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
import java.util.Timer;
import java.util.TimerTask;

/**
 * //维修Fragment
 */
public class Fragment_task_JiaZhengWeixiu extends Fragment implements View.OnClickListener {
    View rootview;
    //控件
    MyGridView gridview_type;
    TextView mTextview_time;//预约时间
    TextView textview_shouqi;//收起
    RelativeLayout mRelativelayout_chosetime;//选择时间
    //    EditText mEditview_addressDetail;//详细地址
    TextView mEditview_taskdetails;
    MyGridView imageGridview;
    ImageView image_chosePIC;
    Button mButton_sub;
    ImageView imageview_jiazheng;


    TextView textview_suiji;//随机数


    //对象
    PermissionHelper permissionHelper;
    Popwindow_ChooseTime popwindow_chooseTime;
    //    Popwindow_CompleteTime popwindow_completeTime;
    DataTime_select dataTimeSelect;
    Adapter_Gridviewpic_UPLoad adapter_gridviewpic_upLoad;
    KProgressHUD mKProgressHUD;

    String xValue = "";//纬度
    String yValue = "";//经度
    String citycode = "";//城市名字

    String task_description = "";
    String task_time = "";
    String release_address = "";
    Bitmap mBitmap = null;
    String commission = "";
    int task_typeID=1204;
    String  JiazhengTypeJson="{\"list\":[{\"specialty_id\":1204,\"specialty_name\":\"手机\",\"image\":\"\"," +
            "\"isselect\":true},{\"specialty_id\":1201,\"specialty_name\":\"房屋\",\"image\":\"\",\"isselect\":false}," +
            "{\"specialty_id\":1209,\"specialty_name\":\"开锁\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1207," +
            "\"specialty_name\":\"管道\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1210,\"specialty_name\":\"家电\"," +
            "\"image\":\"\",\"isselect\":false},{\"specialty_id\":1206,\"specialty_name\":\"数码\",\"image\":\"\",\"isselect\":false}," +
            "{\"specialty_id\":1208,\"specialty_name\":\"电路\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1202,\"specialty_name\"" +
            ":\"家具\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1212,\"specialty_name\":\"手表\",\"image\":\"\",\"isselect\":false}" +
            ",{\"specialty_id\":1205,\"specialty_name\":\"空调\",\"image\":\"\",\"isselect\":false},\n" +
            "{\"specialty_id\":1203,\"specialty_name\":\"电脑\",\"image\":\"\",\"isselect\":false},\n" +
            "{\"specialty_id\":1211,\"specialty_name\":\"电动车\",\"image\":\"\",\"isselect\":false}]}";
    String  JiazhengTypeJson_shao="{\"list\":[{\"specialty_id\":1204,\"specialty_name\":\"手机\",\"image\":\"\"," +
            "\"isselect\":true},{\"specialty_id\":1201,\"specialty_name\":\"房屋\",\"image\":\"\",\"isselect\":false}," +
            "{\"specialty_id\":1209,\"specialty_name\":\"开锁\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1207," +
            "\"specialty_name\":\"管道\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1209,\"specialty_name\":\"全部\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1210,\"specialty_name\":\"家电\"," +
            "\"image\":\"\",\"isselect\":false},{\"specialty_id\":1206,\"specialty_name\":\"数码\",\"image\":\"\",\"isselect\":false}," +
            "{\"specialty_id\":1208,\"specialty_name\":\"电路\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1202,\"specialty_name\"" +
            ":\"家具\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1212,\"specialty_name\":\"手表\",\"image\":\"\",\"isselect\":false}" +
            ",{\"specialty_id\":1205,\"specialty_name\":\"空调\",\"image\":\"\",\"isselect\":false},\n" +
            "{\"specialty_id\":1203,\"specialty_name\":\"电脑\",\"image\":\"\",\"isselect\":false},\n" +
            "{\"specialty_id\":1211,\"specialty_name\":\"电动车\",\"image\":\"\",\"isselect\":false}]}";

//    String  JiazhengTypeJson_shao="{\"list\":[{\"specialty_id\":1204,\"specialty_name\":\"手机\",\"image\":\"\"," +
//            "\"isselect\":true},{\"specialty_id\":1201,\"specialty_name\":\"房屋\",\"image\":\"\",\"isselect\":false}," +
//            "{\"specialty_id\":1209,\"specialty_name\":\"开锁\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1207," +
//            "\"specialty_name\":\"管道\",\"image\":\"\",\"isselect\":false},{\"specialty_id\":1209,\"specialty_name\":\"全部\",\"image\":\"\",\"isselect\":false}]}";
    List<WeixiuJiazhengBean.ListBean>mdata;
    int [] images={R.mipmap.b_shouji,R.mipmap.b_fangwu,R.mipmap.b_kaisuo,R.mipmap.b_guandao,R.mipmap.b_jiadian,R.mipmap.b_shuma
            ,R.mipmap.b_dianlu,R.mipmap.b_jiaju,R.mipmap.b_shoubiao,R.mipmap.b_kongtiaoweixiu5,R.mipmap.b_diannao,R.mipmap.b_diandongche};
    int [] images_select={R.mipmap.b_shouji,R.mipmap.b_fangwu,R.mipmap.b_kaisuo,R.mipmap.b_guandao,R.mipmap.b_jiadian,R.mipmap.b_shuma
            ,R.mipmap.b_dianlu,R.mipmap.b_jiaju,R.mipmap.b_shoubiao,R.mipmap.b_kongtiaoweixiu5,R.mipmap.b_diannao,R.mipmap.b_diandongche};
    int [] images_shao={R.mipmap.b_shouji,R.mipmap.b_fangwu,R.mipmap.b_kaisuo,R.mipmap.b_guandao,R.mipmap.b_all};
    int [] images_select_shao={R.mipmap.b_shouji,R.mipmap.b_fangwu,R.mipmap.b_kaisuo,R.mipmap.b_guandao,R.mipmap.b_all};

    Adapter_WeixiuJiazheng adapter_weixiuJiazheng;

    boolean isShowAll=false;
    int  se_position;//选择的位置



    String detailed_address = "";
    int is_counteroffer = 1;//是否接受议价 1 接受  0 拒绝
    boolean ceshi = true;
    int PIC_mix = 3;//选择图片得张数
    Bitmap bitmap;
    List<String> mList_picID;// 上传图片返回ID;
    int count = 0;//图片的张数。判断调用几次上传图片接口
    String img_id = "";//图片
    List<List<String>> mList_PicPath_down;//；压缩后本地图片path集合;
    Map map_issueTask;
    UpLoadImage upLoadImage;


    //数字动态添加变化
    int time = 0;
    Timer timer;
    TimerTask timerTask;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    time=time+12;
                    textview_suiji.setText( " "+time + " 位 ");
                    if (time > Staticdata.suijiAcount) {
                        textview_suiji.setText( " "+Staticdata.suijiAcount + " 位 ");
                        time=0;
                        if(timer!=null){
                            timer.cancel();
                            timerTask.cancel();
                        }
                        timer=null;
                    }
                    break;
            }
        }


    };






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_task_jiazhengweixiu, container, false);

        initview();
        initdata();
        setdata();
        initlistenner();


        return rootview;
    }

    private void initview() {
        gridview_type = rootview.findViewById(R.id.gridview_type);
        imageview_jiazheng = rootview.findViewById(R.id.imageview_jiazheng);
        mTextview_time = rootview.findViewById(R.id.edit_tasktime);
        textview_shouqi = rootview.findViewById(R.id.textview_shouqi);
        mRelativelayout_chosetime = rootview.findViewById(R.id.relative_chosetime);
        mEditview_taskdetails = rootview.findViewById(R.id.edit_detailtask);
        imageGridview = rootview.findViewById(R.id.GridView_PIC);
        image_chosePIC = rootview.findViewById(R.id.image_chosePIC);
        mButton_sub = rootview.findViewById(R.id.button_submitsave);
        textview_suiji=rootview.findViewById(R.id.textview_suiji);

    }

    private void initdata() {
        imageview_jiazheng.setBackgroundResource(R.mipmap.zhaoweixiu);
        int  hight=(int) (Staticdata.ScreenWidth * 0.45);
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(Staticdata.ScreenWidth,hight );
        imageview_jiazheng.setLayoutParams(mLayoutparams);

        mdata=new ArrayList<>();
        mdata.clear();
        mdata.addAll(new Gson().fromJson(JiazhengTypeJson_shao,WeixiuJiazhengBean.class).getList());
        adapter_weixiuJiazheng=new Adapter_WeixiuJiazheng(mdata,getActivity(),images_shao,images_select_shao);
        adapter_weixiuJiazheng.isShowAll(isShowAll);
        gridview_type.setAdapter(adapter_weixiuJiazheng);

        mList_picID=new ArrayList<>();
        mKProgressHUD = new KProgressHUD(getActivity());
        permissionHelper = new PermissionHelper(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        map_issueTask = new HashMap();
//        mTextview_taskAddress.setText(Staticdata.aoi);


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

                        requast(Staticdata.map_task);

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
    void requast(Map map) {//正式发布个性任务
        LogUtils.LOG("ceshimap", Staticdata.map_task.toString(), "发布任务的map参数");
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

        mList_PicPath_down = new ArrayList<>();
         bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.addpic);
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

        //随机数动态变化
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mhandler.sendEmptyMessage(0);
            }
        };
        if(Staticdata.isyanshi){
            timer.schedule(timerTask, 1000, 50);
            Staticdata.isyanshi=false;
            LogUtils.LOG("ceshi","延时显示@@@@@@@@@","首页fragment");
        }else {
            timer.schedule(timerTask, 0, 50);
        }
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

    private void initlistenner() {
        mEditview_taskdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Popwindow_descriptionType(task_typeID, getActivity(), new Interface_descriptionType() {
                    @Override
                    public void onDestext(String text) {
                        LogUtils.LOG("ceshi",text,"descriptionTYPE");
                        mEditview_taskdetails.setText(text);
                    }

                    @Override
                    public void onDesId(String id) {

                    }
                }).showPopwindow();
            }
        });
        textview_shouqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowAll=false;
                mdata.clear();
                adapter_weixiuJiazheng = null;
                mdata.addAll(new Gson().fromJson(JiazhengTypeJson_shao, WeixiuJiazhengBean.class).getList());
                adapter_weixiuJiazheng = new Adapter_WeixiuJiazheng(mdata, getActivity(), images_shao, images_select_shao);
                adapter_weixiuJiazheng.isShowAll(isShowAll);
                adapter_weixiuJiazheng.setSelectedPosition(se_position);
                gridview_type.setAdapter(adapter_weixiuJiazheng);
                textview_shouqi.setVisibility(View.GONE);
            }
        });
        gridview_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(isShowAll){
//                    if(position==mdata.size()-1){
//                        mdata.clear();
//                        adapter_weixiuJiazheng=null;
//                        mdata.addAll(new Gson().fromJson(JiazhengTypeJson_shao,WeixiuJiazhengBean.class).getList());
//                        adapter_weixiuJiazheng=new Adapter_WeixiuJiazheng(mdata,getActivity(),images_shao,images_select_shao);
//                        gridview_type.setAdapter(adapter_weixiuJiazheng);
//                    }else {
                    se_position=position;

                    adapter_weixiuJiazheng.setSelectedPosition(se_position);
                        adapter_weixiuJiazheng.notifyDataSetInvalidated();
                        task_typeID=mdata.get(se_position).getSpecialty_id();
//                    }
                    new Popwindow_descriptionType(task_typeID, getActivity(), new Interface_descriptionType() {
                        @Override
                        public void onDestext(String text) {
                            LogUtils.LOG("ceshi",text,"descriptionTYPE");
                            mEditview_taskdetails.setText(text);
                        }

                        @Override
                        public void onDesId(String id) {

                        }
                    }).showPopwindow();

                }else {
                    if(position==4){
                        isShowAll=true;
                        mdata.clear();
                        adapter_weixiuJiazheng=null;
                        mdata.addAll(new Gson().fromJson(JiazhengTypeJson,WeixiuJiazhengBean.class).getList());
                        adapter_weixiuJiazheng=new Adapter_WeixiuJiazheng(mdata,getActivity(),images,images_select);
                        adapter_weixiuJiazheng.setSelectedPosition(se_position);
                        adapter_weixiuJiazheng.isShowAll(isShowAll);
                        gridview_type.setAdapter(adapter_weixiuJiazheng);
                        textview_shouqi.setVisibility(View.VISIBLE);

                    }else {
                        se_position=position;
                        adapter_weixiuJiazheng.setSelectedPosition(se_position);
                        adapter_weixiuJiazheng.notifyDataSetInvalidated();
                        task_typeID=mdata.get(se_position).getSpecialty_id();
                        new Popwindow_descriptionType(task_typeID, getActivity(), new Interface_descriptionType() {
                            @Override
                            public void onDestext(String text) {
                                LogUtils.LOG("ceshi",text,"descriptionTYPE");
                                mEditview_taskdetails.setText(text);
                            }

                            @Override
                            public void onDesId(String id) {

                            }
                        }).showPopwindow();
                    }
                }



            }
        });
        mRelativelayout_chosetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                popwindow_completeTime.showPopwindow();
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
//                                intent.putExtra("issuetask","zhaorenshou");
//                                startActivity(intent);
                                    mList_picID.clear();
                                    count = 0;
                                    uploadimg();
                                } else {
                                    mKProgressHUD.dismiss();
                                    ToastUtils.showToast(getActivity(), msg);
                                }
                            }

                            @Override
                            public void onError(int error) {
                                mKProgressHUD.dismiss();
                            }
                        }).postHttp(Urls.Baseurl_cui + Urls.checkissuetask, getActivity(), 1, map_check);

                    }
                }else {
                 Intent   intent = new Intent(getActivity(), LoginActivityPhone.class);
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
        switch (v.getId()) {
            case R.id.image_chosePIC:
                choosePIC();

                break;
        }

    }

    boolean initmap_zhaoshanghu() {

        task_description = mEditview_taskdetails.getText() + "";
        if (task_description.trim().equals("")) {
            ToastUtils.showToast(getActivity(), "请填写任务说明");
            return false;
        }
//        if (task_description.length() < 5) {
//            ToastUtils.showToast(getActivity(), "任务说明太短了");
//            return false;
//        }

        task_time = mTextview_time.getText() + "";
        if (task_time.equals("请选择预约时间")) {
            ToastUtils.showToast(getActivity(), "请选择预约时间");
            return false;
        }
        release_address = address_left + "";

        if (release_address.equals("")) {
            release_address=Staticdata.aoi;
        }
        if (release_address.equals("选择地址")||release_address.equals("")) {
            ToastUtils.showToast(getActivity(), "请选择任务地址");
            return false;
        }

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

//        Staticdata.map_task.put("tasktypename", task_type);

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
    public void setAddress(Intent data){
        address_left = data.getStringExtra("address");
        address_right = data.getStringExtra("address2");
        xValue = data.getStringExtra("xValue");
        yValue = data.getStringExtra("yValue");
        citycode = data.getStringExtra("citycode");
        Staticdata.aoi=address_left;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.LOG("ceshi","维修不见","维修fragment");
        if(timer!=null){
            timer.cancel();
            timerTask.cancel();
        }
        isShowAll=false;//恢复初始
        task_typeID=1204;//恢复初始
        se_position=0;//恢复初始
        timer=null;
        Staticdata.mlistdata_pic.clear();
        Staticdata.mlistdata_pic.add(bitmap);
        mList_PicPath_down.clear();//删除图片地址以便上传；
        PIC_mix = 3 - mList_PicPath_down.size();
        adapter_gridviewpic_upLoad.notifyDataSetChanged();
        if (Staticdata.mlistdata_pic.size() <= 1) {
            imageGridview.setVisibility(View.GONE);
        }
    }
}
