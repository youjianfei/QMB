package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_ShequList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.SheQuListBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShezhishequActivity extends BaseActivityother {

    //控件
    EditText mEdit_search;
    TextView mTextview_cancle;
    ListView mList_shequ;


    //对象
    Adapter_ShequList adapter_shequList;
    SheQuListBean sheQuListBean;

    //数据
    String  search="";
    Map map_shequ;
    Map map_shequ_bind;
    List<SheQuListBean.DataBean> mDate;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_shezhishequ;
    }

    @Override
    protected void setData() {
        request();
    }

    @Override
    protected void initData() {
        map_shequ=new HashMap();
        mDate=new ArrayList<>();
        map_shequ_bind=new HashMap();
        adapter_shequList=new Adapter_ShequList(mDate,this);
        mList_shequ.setAdapter(adapter_shequList);


        map_shequ.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_shequ.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_shequ.put("community_name", search);
        map_shequ.put("area", Staticdata.city_location);

        map_shequ_bind.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_shequ_bind.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());

    }

    @Override
    protected void initListener() {
        mList_shequ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.LOG("ceshi", position + "", "");
                map_shequ_bind.put("community_code",mDate.get(position).getCommunity_code()+"");
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
                        ToastUtils.showToast(ShezhishequActivity.this,msg);
                        if(status==1){
                            finish();
                        }


                    }

                    @Override
                    public void onError(int error) {

                    }
                }).postHttp(Urls.Baseurl_hu+Urls.bindCommunity,ShezhishequActivity.this,1,map_shequ_bind);


            }
        });

    }

    @Override
    protected void initView() {
        mEdit_search=findViewById(R.id.edit_searchshequ);
        mTextview_cancle=findViewById(R.id.iamge_cancle);
        mList_shequ=findViewById(R.id.listView_shequ);
    }

    void  request(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose + "", "");
                sheQuListBean=new Gson().fromJson(respose,SheQuListBean.class);
                if(sheQuListBean.getData()!=null){
                    mDate.addAll(sheQuListBean.getData());
                    adapter_shequList.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.getCommunityList,ShezhishequActivity.this,1,map_shequ);
        LogUtils.LOG("ceshi", Urls.Baseurl_hu+Urls.getCommunityList + "", "");



    }

}
