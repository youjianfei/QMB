package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.LoveTaskListBean;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ShuidianfeiActivity extends BaseActivityother {
    //控件
    EditText mEdit_edit_name;
    EditText mEdit_edit_buding;
    EditText mEdit_edit_door;
    Button mbutton;

    //数据
    String name = "";
    String building = "";
    String doorno = "";

    Map map_shuidian = new HashMap();

    @Override
    public int setLayoutResID() {
        return R.layout.activity_shuidianfei;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initmap()) {
                    request();
                }
            }
        });
    }

    @Override
    protected void initView() {
        mEdit_edit_name = findViewById(R.id.edit_name);
        mEdit_edit_buding = findViewById(R.id.edit_buding);
        mEdit_edit_door = findViewById(R.id.edit_door);
        mbutton = findViewById(R.id.button_submit);
    }

    boolean initmap() {
        name = mEdit_edit_name.getText() + "";
        if (name.equals("")) {
            ToastUtils.showToast(this, "请输入你的姓名");
            return false;
        }
        building = mEdit_edit_buding.getText() + "";
        if (building.equals("")) {
            ToastUtils.showToast(this, "请输入你的楼号");
            return false;
        }
        doorno = mEdit_edit_door.getText() + "";
        if (doorno.equals("")) {
            ToastUtils.showToast(this, "请输入你的门牌号");
            return false;
        }
        map_shuidian.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_shuidian.put("community_code", Staticdata.static_userBean.getData().getAppuser().getCommunity_code());
        map_shuidian.put("name", name);
        map_shuidian.put("budding_no", building);
        map_shuidian.put("house_no", doorno);
        return true;
    }

    void request() {
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
                ToastUtils.showToast(ShuidianfeiActivity.this, msg);
                if (status == 1) {
                    finish();
                }
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu + Urls.shuidianfeidaijiao, ShuidianfeiActivity.this, 1, map_shuidian);


    }
}
