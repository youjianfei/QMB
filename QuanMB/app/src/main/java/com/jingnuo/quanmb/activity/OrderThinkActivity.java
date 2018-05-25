package com.jingnuo.quanmb.activity;




import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class OrderThinkActivity extends BaseActivityother {

    //控件
    RatingBar mRatinBar;
    String  Taskid="";
    EditText mEdit_think;
    Button mButton_submit;

    //数据
    String contenttext="";
    String xingxingcount="5";


    Map map_think;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_order_think;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        Taskid= intent.getStringExtra("task_id");
        map_think=new HashMap();
        initmap();

    }

    @Override
    protected void initListener() {
        mRatinBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                LogUtils.LOG("ceshi",ratingBar.getNumStars()+"geshu"+(int)rating+"rating"+fromUser+"fromUser","评价");
                map_think.put("star_level", (int)rating+"");

            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });

    }

    @Override
    protected void initView() {
        mRatinBar=findViewById(R.id.ratingbar);
        mEdit_think=findViewById(R.id.edit_think);
        mButton_submit=findViewById(R.id.button_submit);
    }
    void initmap(){
        map_think.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_think.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_think.put("task_id", Taskid);
        contenttext=mEdit_think.getText()+"";
        map_think.put("evaluation_content", contenttext);
        map_think.put("star_level", "5");
    }
    void request(){
        LogUtils.LOG("ceshi",map_think.toString(),"评价");
        LogUtils.LOG("ceshi",Urls.Baseurl_cui+Urls.orderthink,"评价");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"评价");
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//登录状态
                    msg = (String) object.get("message");//登录返回信息

                    if (status == 1) {
                        ToastUtils.showToast(OrderThinkActivity.this, msg);
                        finish();
                    } else {
                        ToastUtils.showToast(OrderThinkActivity.this, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui+Urls.orderthink,this,1,map_think);
    }
}
