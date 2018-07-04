package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_HelperType;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Skillmenu_oneBean;
import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HelperType extends BaseActivityother {
    //控件
    TabLayout mTablayout;
    RecyclerView recyclerView_helpertype;

    TextView text_ischoose;

    private GridLayoutManager mRecyclerViewManager;
    private Adapter_HelperType mAdapter_helper;

    List<Skillmenu_oneBean.DataBean.ListBean> mListData_left;
    List<Skillmenu_twoBean.DataBean.ListBean> mListData_right;
    List<String> mChooce;
    Skillmenu_oneBean skillmenu_oneBean;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_helper_type;
    }

    @Override
    protected void setData() {
        mAdapter_helper=new Adapter_HelperType(mListData_right,HelperType.this);
        recyclerView_helpertype.setAdapter (mAdapter_helper);//设置适配器
    }

    @Override
    protected void initData() {
        mListData_left=new ArrayList<>();
        mListData_right=new ArrayList<>();
        mChooce=new ArrayList<>();
        mRecyclerViewManager = new GridLayoutManager(this,
                6   );
        mRecyclerViewManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                LogUtils.LOG("ceshirr",position+"getSpanSize","getSpanSize");
                switch (position%5){
                    case 0:
                        return 3;
                    case 1:
                        return 3;
                    case 2:
                        return 2;
                    case 3:
                        return 2;
                    case 4:
                        return 2;
                }
                return 2;
            }
        });
        recyclerView_helpertype.setLayoutManager (mRecyclerViewManager);//设置布局管理器

        requestOnemenu();

    }
    void requestOnemenu() {
        LogUtils.LOG("ceshi", Urls.Baseurl + Urls.Skillmenu_one, "一级专业接口");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                skillmenu_oneBean = new Gson().fromJson(respose, Skillmenu_oneBean.class);
                mListData_left.clear();
                mListData_left.addAll(skillmenu_oneBean.getData().getList());
                for (Skillmenu_oneBean.DataBean.ListBean title :mListData_left)
                mTablayout.addTab(mTablayout.newTab().setText(title.getSpecialty_name()).setTag(title.getSpecialty_id()));
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.Skillmenu_one, HelperType.this, 0);
    }
    void requestRight(String id){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                mListData_right.clear();
                mListData_right.addAll(new Gson().fromJson(respose, Skillmenu_twoBean.class).getData().getList());
                LogUtils.LOG("ceshi",mChooce.size()+"已选泽的个数","qingqiuzhihou ");

               for (String id:mChooce){
                   int y=-1;
                   for (Skillmenu_twoBean.DataBean.ListBean iid:mListData_right){
                       ++y;
                       if(iid.getSpecialty_id()==Integer.parseInt(id)){
                           mListData_right.get(y).setIs_select(true);
                           break;
                       }

                   }

               }

                mAdapter_helper.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.Skillmenu_right + "?specialty_id=" + id,HelperType.this,0);
    }
    @Override
    protected void initListener() {
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.LOG("ceshi",tab.getTag()+"","MyOrderActivity");
                requestRight(tab.getTag()+"");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mAdapter_helper.setItemClickListener(new Adapter_HelperType.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                for (String id :mChooce){
                    if(id.equals(mListData_right.get(position).getSpecialty_id()+"")){
                        mChooce.remove(id+"");
                        mListData_right.get(position).setIs_select(false);
                        mAdapter_helper.notifyDataSetChanged();
                        if(mChooce.size()>0){
                            text_ischoose.setVisibility(View.VISIBLE);
                        }else {
                            text_ischoose.setVisibility(View.GONE);
                        }
                        return;
                    }
                }
                mChooce.add(mListData_right.get(position).getSpecialty_id()+"");
                mListData_right.get(position).setIs_select(true);
                if(mChooce.size()>5){
                    mChooce.remove(5);
                    mListData_right.get(position).setIs_select(false);
                    ToastUtils.showToast(HelperType.this,"你最多可以选择5个服务类型");

                }
                mAdapter_helper.notifyDataSetChanged();
                if(mChooce.size()>0){
                    text_ischoose.setVisibility(View.VISIBLE);
                }else {
                    text_ischoose.setVisibility(View.GONE);
                }

            }
        });
        text_ischoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String helpertype="";
                for (String id: mChooce){
                    helpertype=helpertype+id+",";
                }
                LogUtils.LOG("ceshi","选择类型"+helpertype,"xuanzebanghsouleixiu");
                new  Volley_Utils(new Interface_volley_respose() {
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
                            Intent intent_shopcenter=new Intent(HelperType.this, ShopCenterActivity.class);
                            intent_shopcenter.putExtra("type",1);//1  帮手
                            startActivity(intent_shopcenter);
                            finish();
                        }else {
                            ToastUtils.showToast(HelperType.this,msg);
                        }

                    }

                    @Override
                    public void onError(int error) {

                    }
                }).Http(Urls.Baseurl+Urls.helper_type+ Staticdata.static_userBean.getData().getUser_token()+"&helper_type_id="+helpertype,
                        HelperType.this,0);
            }
        });
    }

    @Override
    protected void initView() {
        mTablayout=findViewById(R.id.tablayout);
        recyclerView_helpertype=findViewById(R.id.recyclerView_helpertype);
        text_ischoose=findViewById(R.id.text_ischoose);
    }
}
