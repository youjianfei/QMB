package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.content.Intent;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.AuthenticationActivity;
import com.jingnuo.quanmb.activity.HelperType;
import com.jingnuo.quanmb.activity.ShopCenterActivity;
import com.jingnuo.quanmb.activity.SubmitSuccessActivity;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Chengweibangshou {
    Activity activity;

    public Chengweibangshou(Activity activity) {
        this.activity = activity;
    }

    public void  chengweibangshou(){

        if(Staticdata.static_userBean.getData().getAppuser().getRole().contains("1")){
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
                        Intent intent_shopcenter=new Intent(activity, ShopCenterActivity.class);
                        intent_shopcenter.putExtra("type",1);//1  帮手
                        activity.startActivity(intent_shopcenter);
                    }else {
                        Intent intent_chosetype=new Intent(activity, HelperType.class);
                        activity.startActivity(intent_chosetype);
                    }
                }


                @Override
                public void onError(int error) {

                }
            }).Http(Urls.Baseurl_hu+Urls.helper_isHavehelper+Staticdata.static_userBean.getData().getUser_token()+"&client_no="+
                    Staticdata.static_userBean.getData().getAppuser().getClient_no(),activity,0);

        }
//        else if(Staticdata.static_userBean.getData().getAppuser().getRole().equals("2")) {//即时帮手也是商户
//            ToastUtils.showToast(activity,"你已经是商户啦！");
//        }
        else {//申请帮手界面
//                    Intent intent_anthentication = new Intent(getActivity(), AuthenticationActivity.class);
//                    getActivity().startActivity(intent_anthentication);
            Map map=new HashMap();
            map.put("user_token",Staticdata.static_userBean.getData().getUser_token());
            map.put("client_no",Staticdata.static_userBean.getData().getAppuser().getClient_no());
            new Volley_Utils(new Interface_volley_respose() {
                @Override
                public void onSuccesses(String respose) {
                    LogUtils.LOG("ceshi",respose,"帮手审核状态");
                    int status = 0;
                    String msg = "";
                    String state = "";
                    try {
                        JSONObject object = new JSONObject(respose);
                        status = (Integer) object.get("code");//
                        msg = (String) object.get("msg");//
                        state = (String) object.get("status");//
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (state.equals("2")){//审核通过

                        Intent intent_submit=new Intent(activity, SubmitSuccessActivity.class);
                        intent_submit.putExtra("state","3");
                        activity.startActivity(intent_submit);
                    }else if(status==0){//没提交
                        Intent intent_shopin=new Intent(activity, AuthenticationActivity.class);
                        activity.startActivity(intent_shopin);

                    }else if(state.equals("1")){//正在审核

                        Intent intent_submit=new Intent(activity,SubmitSuccessActivity.class);
                        intent_submit.putExtra("state","2");
                        activity.startActivity(intent_submit);

                    }else if(state.equals("3")){//审核失败
                        Intent intent_submit=new Intent(activity,SubmitSuccessActivity.class);
                        intent_submit.putExtra("state","4");
                        activity.startActivity(intent_submit);
                    }

                }

                @Override
                public void onError(int error) {

                }
            }).postHttp(Urls.Baseurl_hu+Urls.helpIn_state,activity,1,map);
            LogUtils.LOG("ceshi",Urls.Baseurl_hu+Urls.helpIn_state,"帮手审核状态");
            LogUtils.LOG("ceshi",map.toString(),"帮手审核状态map");
        }


    }
}
