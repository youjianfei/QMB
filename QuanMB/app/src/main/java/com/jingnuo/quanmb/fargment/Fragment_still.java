package com.jingnuo.quanmb.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_classification_left;
import com.jingnuo.quanmb.Adapter.Adapter_classification_right;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.ShophallActivity;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Skillmenu_oneBean;
import com.jingnuo.quanmb.entityclass.Skillmenu_twoBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_still  extends Fragment{
    View rootview;
    //控件
    EditText mEdit_serch;//搜索关键字
    TextView mtextview_classification_title;

    ListView mListview_left;
    MyGridView mGridview_right;

    //数据
    List<Skillmenu_oneBean.DataBean.ListBean>mListData_left;
    List<Skillmenu_twoBean.DataBean.ListBean>mListData_right;

    int  position=0;//一级菜单的位置


    //对象
    Adapter_classification_left mAdapter_classification_left;

    Adapter_classification_right mAdapter_classification_right;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_still,container,false);
        initview();
        initdata();
        initlistenner();

        return rootview;
    }

    private void initlistenner() {
        mListview_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapter_classification_left.setSelectedPosition(i);
                mAdapter_classification_left.notifyDataSetInvalidated();
                position=i;
                int ID=mListData_left.get(i).getSpecialty_id();
                LogUtils.LOG("ceshi","一级菜单对应的id:"+ID,"找专业frament");
                requestRightMenu(ID);
            }
        });
        mGridview_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent_shophalllist=new Intent(getActivity(), ShophallActivity.class);
                int id= mListData_right.get(i).getSpecialty_id();
                intent_shophalllist.putExtra("specialty_id",id);
                getActivity().startActivity(intent_shophalllist);
            }
        });
        //监听键盘确定按钮，以便直接搜索
        mEdit_serch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    String  search=mEdit_serch.getText()+"";
                    if(search.length()>5){
                        ToastUtils.showToast(getContext(),"搜索关键字太长");
                        return false;
                    }
                    String searchhou= Utils.ZhuanMa(search);
                    LogUtils.LOG("ceshi", "点击了确定按钮...."+searchhou, "fragmentstill");
                    Intent intent_search=new Intent(getActivity(),ShophallActivity.class);
                    intent_search.putExtra("search",searchhou);
                    getActivity().startActivity(intent_search);

                }
                return false;
            }
        });
    }

    private void initdata() {
        mListData_left=new ArrayList<>();//一级菜单列表
        mAdapter_classification_left=new Adapter_classification_left(mListData_left,getContext());
        mListview_left.setAdapter(mAdapter_classification_left);
        requestOnemenu();//请求一级菜单

        mListData_right=new ArrayList<>();
        mAdapter_classification_right=new Adapter_classification_right(mListData_right,getActivity());
        mGridview_right.setAdapter(mAdapter_classification_right);
    }

    private void initview() {
        mtextview_classification_title=rootview.findViewById(R.id.textviw_classification);
        mListview_left=rootview.findViewById(R.id.gridview_left);
        mGridview_right=rootview.findViewById(R.id.gridview_right);
        mEdit_serch=rootview.findViewById(R.id.edit_searchSquare);
    }


    void requestOnemenu(){
        LogUtils.LOG("ceshi",Urls.Baseurl+Urls.Skillmenu_one,"一级专业接口");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                mListData_left.clear();
                mListData_left.addAll(new Gson().fromJson(respose,Skillmenu_oneBean.class).getData().getList());
                LogUtils.LOG("ceshi",mListData_left.size()+"个数据","找专业一级菜单");
                mAdapter_classification_left.notifyDataSetChanged();
                requestRightMenu(mListData_left.get(position).getSpecialty_id());
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.Skillmenu_one,getActivity(),0);
    }
    void requestRightMenu(int id){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"二级专业id");
                mListData_right.clear();
                mListData_right.addAll(new  Gson().fromJson(respose,Skillmenu_twoBean.class).getData().getList());
                mAdapter_classification_right.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl+Urls.Skillmenu_right+"?specialty_id="+id,getContext(),0);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.LOG("ceshi", "onResume", "fragmentstill");
        requestOnemenu();

    }
    @Override
    public void onHiddenChanged(boolean hidden) {//fragment显示与隐藏状态监听
        super.onHiddenChanged(hidden);
        if(!hidden){
            requestOnemenu();
        }

    }

}
