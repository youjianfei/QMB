package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_FulisheList;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.List;

public class FulisheActivity extends BaseActivityother {
    //控件
    ListView mlistview_fulishe;


    //对象
    Adapter_FulisheList adapter_fulisheList;


    //数据
    List<GuanggaoBean.DataBean> mdata;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_fulishe;
    }

    @Override
    protected void setData() {
        request_GGLB();
    }

    @Override
    protected void initData() {
        mdata=new ArrayList<>();
        adapter_fulisheList=new Adapter_FulisheList(mdata,this);
        mlistview_fulishe.setAdapter(adapter_fulisheList);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mlistview_fulishe=findViewById(R.id.listview_fulishe);
    }

    private void request_GGLB(){//请求网络轮播图
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                mdata.clear();
                mdata.addAll(new Gson().fromJson(respose,GuanggaoBean.class).getData());
                adapter_fulisheList.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.shouyePic+"4",FulisheActivity.this,0);
        LogUtils.LOG("ceshiddd", "轮播图片：" + Urls.Baseurl_cui+Urls.shouyePic, "fragment_square");
    }
}
