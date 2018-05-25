package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.Interface.Interface_paySuccessOrerro;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.broadcastrReceiver.PaySuccessOrErroBroadcastReciver;
import com.jingnuo.quanmb.class_.ProgressDlog;
import com.jingnuo.quanmb.class_.UpLoadImage;
import com.jingnuo.quanmb.class_.WechatPay;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueTaskNextActivity extends BaseActivityother {

    //控件
    Button mButton_submit;
    EditText mEdit_name;
    EditText mEdit_phonenumber;
    ImageView mImage_nan;
    ImageView mImage_nv;


    int  sex=0;   //0男1女
    String lianxiren="";
    String phonenumber="";
    List<String> mList_picID;// 上传图片返回ID;
    int  count=0;//图片的张数。判断调用几次上传图片接口
    String img_id="";//图片



    UpLoadImage upLoadImage;
    ProgressDlog progressDlog;
    private IntentFilter intentFilter_paysuccess;//定义广播过滤器；
    private PaySuccessOrErroBroadcastReciver paysuccess_BroadcastReciver;//定义广播监听器

    private IWXAPI api;





    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task_next;
    }

    @Override
    protected void setData() {
        progressDlog=new ProgressDlog(this);
        mList_picID=new ArrayList<>();
        intentFilter_paysuccess = new IntentFilter();
        intentFilter_paysuccess.addAction("com.jingnuo.quanmb.PAYSUCCESS_ERRO");
        paysuccess_BroadcastReciver=new PaySuccessOrErroBroadcastReciver(new Interface_paySuccessOrerro() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "payResult");
                if(respose.equals("success")){//支付成功
                    Staticdata.map_task.put("payResult","1");
                    requast(Staticdata.map_task);//正式发布任务
                }
            }

            @Override
            public void onError(String error) {
                progressDlog.cancelPD();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(IssueTaskNextActivity.this, "支付失败");
                    }
                });
            }
        });
        registerReceiver(paysuccess_BroadcastReciver, intentFilter_paysuccess); //将广播监听器和过滤器注册在一起；
    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(IssueTaskNextActivity.this, "wx1589c6a947d1f803");//微信支付用到
        LogUtils.LOG("ceshi", Staticdata.map_task.toString(),"发布任务map集合中的内容");
        upLoadImage = new UpLoadImage(this, new Interface_loadImage_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "发布技能上传图片返回respose");
                if(respose.equals("erro")){
                    progressDlog.cancelPD();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast(IssueTaskNextActivity.this,"网络开小差儿，请重新提交");
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
                        imageID=(String) object.get("imgID");
                        LogUtils.LOG("ceshi", "单张图片ID" + imageID, "发布技能上传图片返回imageID");
                        mList_picID.add(0,imageID);
                        LogUtils.LOG("ceshi", mList_picID.size() + "tupiangeshu", "发布技能上传图片返回imageID333");
                        if(count!=Staticdata.imagePathlist.size()){
                            uploadimgagain(count);
                        }else {
                            for (String image : mList_picID) {
                                img_id=img_id+image+",";
                            }
                            Staticdata.map_task.put("task_Img_id",img_id);
                            LogUtils.LOG("ceshi","上传图片完成","发布技能上传图片");
                            requestTaskid();
//                            requast(Staticdata.map_task);//正式发布任务
                        }
                    }
                    else {
                        progressDlog.cancelPD();
                        mList_picID.clear();
                        final String finalMsg = msg;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(IssueTaskNextActivity.this, finalMsg);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    void uploadimg(){
        if( Staticdata.imagePathlist.size()>=1){
            upLoadImage.uploadImg(Staticdata.imagePathlist.get(0),2);
        }else {
//            requast(Staticdata.map_task);
            requestTaskid();
        }

    }
    void uploadimgagain(int  count){
        upLoadImage.uploadImg(Staticdata.imagePathlist.get(count),2);
    }
    @Override
    protected void initListener() {
        mImage_nan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mImage_nan.isSelected()){
                    mImage_nan.setSelected(true);
                    mImage_nv.setSelected(false);
                    sex=0;
                }
            }
        });
        mImage_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mImage_nv.isSelected()){
                    mImage_nan.setSelected(false);
                    mImage_nv.setSelected(true);
                    sex=0;
                }
            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(initmap()){
                    progressDlog.showPD("正在发布，请稍等");
                    uploadimg();
                }
            }
        });

    }

    @Override
    protected void initView() {
        mButton_submit=findViewById(R.id.button_submit);
        mEdit_name=findViewById(R.id.edit_lianxiren);
        mEdit_phonenumber=findViewById(R.id.edit_phonenumber);
        mImage_nan=findViewById(R.id.image_choosenan);
        mImage_nv=findViewById(R.id.image_choosenv);
        mImage_nan.setSelected(true);

    }
    boolean  initmap(){
        lianxiren=mEdit_name.getText()+"";
        if(lianxiren.equals("")){
            ToastUtils.showToast(this,"请输入联系人");
            return false;
        }
        phonenumber=mEdit_phonenumber.getText()+"";
        if(phonenumber.equals("")){
            ToastUtils.showToast(this,"请输入联系电话");
            return  false;
        }

        Staticdata.map_task.put("mobile_no",phonenumber);
        Staticdata.map_task.put("client_name",lianxiren);
        Staticdata.map_task.put("client_sex",sex+"");
        Staticdata.map_task.put("task_Img_id","");
        return true;
    }
    void requestTaskid(){//请求任务号,成功后调取支付
        LogUtils.LOG("ceshi",Urls.Baseurl_cui+Urls.gettaskid
                +Staticdata.static_userBean.getData().getUser_token(),"获取任务ID");
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"获取任务ID");
//                {"code":1,"date":151,"message":"获取成功"}
                int status = 0;
                String msg = "";
                int data = 0;
                try {
                    JSONObject object = new JSONObject(respose);
                    data = (Integer) object.get("date");//登录状态
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                    if(status==1){
                        Staticdata.map_task.put("task_id",data+"");

                        Map map_pay=new HashMap();
                        map_pay.put("body","全民帮—任务付款");
                        map_pay.put("total_fee","0.01");
                        map_pay.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
                        map_pay.put("user_token",Staticdata.static_userBean.getData().getUser_token());
                        map_pay.put("task_id",data+"");
                        LogUtils.LOG("ceshi",map_pay.toString(),"充值");
                        new WechatPay(IssueTaskNextActivity.this,api,map_pay).wepay();

                    }else {
                        ToastUtils.showToast(IssueTaskNextActivity.this,msg);
                        progressDlog.cancelPD();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.gettaskid
                +Staticdata.static_userBean.getData().getUser_token(),IssueTaskNextActivity.this,0);
    }

    void requast( Map map){//正式发布任务
        LogUtils.LOG("ceshi",Staticdata.map_task.toString(),"发布任务的map参数");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                progressDlog.cancelPD();
                LogUtils.LOG("ceshi","发布任务返回json","发布任务");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("status");//
                    msg = (String) object.get("msg");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(IssueTaskNextActivity.this,"任务发布成功");
                    Intent intent=new Intent(IssueTaskNextActivity.this,MainActivity.class);
                    startActivity(intent);
                    Staticdata.imagePathlist.clear();
                    Staticdata.map_task.clear();
                }else {
                    count=0;
                    mList_picID.clear();
                    progressDlog.cancelPD();
                    ToastUtils.showToast(IssueTaskNextActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {
                progressDlog.cancelPD();
                count=0;
                mList_picID.clear();
            }
        }).postHttp(Urls.Baseurl_cui+Urls.issuetask,this,1,map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paysuccess_BroadcastReciver);
        if (progressDlog!=null){
            progressDlog.cancelPD();
            mList_picID.clear();
        }
    }
}
