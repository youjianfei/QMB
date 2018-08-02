package com.jingnuo.quanmb.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_still extends Fragment {
    View rootview;
    //控件
    TextView mEdit_serch;//搜索关键字
    TextView mtextview_classification_title;

    ListView mListview_left;
    MyGridView mGridview_right;

    //数据
    List<Skillmenu_oneBean.DataBean.ListBean> mListData_left;
    List<Skillmenu_twoBean.DataBean.ListBean> mListData_right;

    int position = 0;//一级菜单的位置


    //对象
    Adapter_classification_left mAdapter_classification_left;

    Adapter_classification_right mAdapter_classification_right;
    Skillmenu_oneBean skillmenu_oneBean;
    Skillmenu_oneBean.DataBean.ListBean listBean;
    Skillmenu_twoBean.DataBean.ListBean listBean_two;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_still, container, false);
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
                position = i;
                int ID = mListData_left.get(i).getSpecialty_id();
                LogUtils.LOG("ceshi", "一级菜单对应的id:" + ID, "找专业frament");
                mtextview_classification_title.setText(mListData_left.get(i).getSpecialty_name());
                if(ID==0){
                    getHotRightmenu();
                }else {
                    requestRightMenu(ID);
                }
            }
        });
        mGridview_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent_shophalllist = new Intent(getActivity(), ShophallActivity.class);
                int id = mListData_right.get(i).getSpecialty_id();
                intent_shophalllist.putExtra("specialty_id", id);
                getActivity().startActivity(intent_shophalllist);
            }
        });
        mEdit_serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search = new Intent(getActivity(), ShophallActivity.class);
                intent_search.putExtra("search", "");
                getActivity().startActivity(intent_search);
            }
        });
//        //监听键盘确定按钮，以便直接搜索
//        mEdit_serch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                //当actionId == XX_SEND 或者 XX_DONE时都触发
//                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
//                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
//                if (actionId == EditorInfo.IME_ACTION_SEND
//                        || actionId == EditorInfo.IME_ACTION_DONE
//                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
//                    //处理事件
//                    String  search=mEdit_serch.getText()+"";
//                    if(search.length()>10){
//                        ToastUtils.showToast(getContext(),"搜索关键字太长");
//                        return false;
//                    }
//                    String searchhou= Utils.ZhuanMa(search);
//                    LogUtils.LOG("ceshi", "点击了确定按钮...."+searchhou, "fragmentstill");
//                    Intent intent_search=new Intent(getActivity(),ShophallActivity.class);
//                    intent_search.putExtra("search",searchhou);
//                    getActivity().startActivity(intent_search);
//
//                }
//                return false;
//            }
//        });

    }

    private void initdata() {
        mListData_left = new ArrayList<>();//一级菜单列表
        mAdapter_classification_left = new Adapter_classification_left(mListData_left, getContext());
        mListview_left.setAdapter(mAdapter_classification_left);
        requestOnemenu();//请求一级菜单

        mListData_right = new ArrayList<>();
        mAdapter_classification_right = new Adapter_classification_right(mListData_right, getActivity());
        mGridview_right.setAdapter(mAdapter_classification_right);
    }

    private void initview() {
        mtextview_classification_title = rootview.findViewById(R.id.textviw_classification);
        mListview_left = rootview.findViewById(R.id.gridview_left);
        mGridview_right = rootview.findViewById(R.id.gridview_right);
        mEdit_serch = rootview.findViewById(R.id.edit_searchSquare);
    }


    void requestOnemenu() {
        LogUtils.LOG("ceshi", Urls.Baseurl + Urls.Skillmenu_one, "一级专业接口");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                skillmenu_oneBean = new Gson().fromJson(respose, Skillmenu_oneBean.class);
                mListData_left.clear();
                listBean = new Skillmenu_oneBean.DataBean.ListBean();//热门 类目  本地手动添加的
                listBean.setSpecialty_id(0);
                listBean.setSpecialty_name("热门任务");
                mListData_left.add(0, listBean);

                mListData_left.addAll(skillmenu_oneBean.getData().getList());

                LogUtils.LOG("ceshi", mListData_left.size() + "个数据", "找专业一级菜单");
                mAdapter_classification_left.notifyDataSetChanged();
                if(mListData_left.get(position).getSpecialty_id()==0){
                    getHotRightmenu();
                }else {
                    requestRightMenu(mListData_left.get(position).getSpecialty_id());
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.Skillmenu_one, getActivity(), 0);
    }

    void getHotRightmenu() {
        mListData_right.clear();
        for (int i = 0; i < 13; i++) {
            switch (i) {
                case 0:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E6%90%AC%E5%AE%B6hot.png");//搬家
                    listBean_two.setSpecialty_name("搬家");
                    listBean_two.setSpecialty_id(1305);
                    mListData_right.add(listBean_two);
                    break;
                case 1:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E4%BF%9D%E6%B4%81hot.png");//家庭保洁
                    listBean_two.setSpecialty_name("家庭保洁");
                    listBean_two.setSpecialty_id(1300);
                    mListData_right.add(listBean_two);
                    break;
                case 2:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E8%BD%A6%E8%BE%86%E4%BB%A3%E5%8A%9Ehot.png");//车务代办
                    listBean_two.setSpecialty_name("车务代办");
                    listBean_two.setSpecialty_id(1702);
                    mListData_right.add(listBean_two);
                    break;
                case 3:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E4%BB%A3%E9%A9%BEhot.png");//代驾
                    listBean_two.setSpecialty_name("代驾");
                    listBean_two.setSpecialty_id(1104);
                    mListData_right.add(listBean_two);
                    break;
                case 4:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E8%BE%85%E5%AF%BChot.png");//中小学辅导
                    listBean_two.setSpecialty_name("中小学辅导");
                    listBean_two.setSpecialty_id(1901);
                    mListData_right.add(listBean_two);
                    break;
                case 5:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E5%AE%B6%E7%94%B5hot.png");//家具维修
                    listBean_two.setSpecialty_name("家具维修");
                    listBean_two.setSpecialty_id(1202);
                    mListData_right.add(listBean_two);
                    break;
                case 6:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E5%AE%B6%E6%95%99hot.png");//家教
                    listBean_two.setSpecialty_name("家教");
                    listBean_two.setSpecialty_id(1906);
                    mListData_right.add(listBean_two);
                    break;
                case 7:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E6%8E%92%E9%98%9Fhot.png");//帮排队
                    listBean_two.setSpecialty_name("帮排队");
                    listBean_two.setSpecialty_id(1102);
                    mListData_right.add(listBean_two);
                    break;
                case 8:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E6%89%8B%E6%9C%BA%E7%BB%B4%E4%BF%AEhot.png");//手机维修
                    listBean_two.setSpecialty_name("手机维修");
                    listBean_two.setSpecialty_id(1204);
                    mListData_right.add(listBean_two);
                    break;
                case 9:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E6%B0%B4hot.png");//送水
                    listBean_two.setSpecialty_name("送水");
                    listBean_two.setSpecialty_id(1306);
                    mListData_right.add(listBean_two);
                    break;
                case 10:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E9%94%81hot.png");//开锁修锁
                    listBean_two.setSpecialty_name("开锁修锁");
                    listBean_two.setSpecialty_id(1209);
                    mListData_right.add(listBean_two);
                    break;
                case 11:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E6%8E%A8%E5%B9%BFhot.png");//推广运营
                    listBean_two.setSpecialty_name("推广运营");
                    listBean_two.setSpecialty_id(1402);
                    mListData_right.add(listBean_two);
                    break;
                case 12:
                    listBean_two = new Skillmenu_twoBean.DataBean.ListBean();
                    listBean_two.setImg_url(Urls.hotbackImage+"%E7%A7%9F%E8%BD%A6hot.png");//租车
                    listBean_two.setSpecialty_name("租车");
                    listBean_two.setSpecialty_id(1700);
                    mListData_right.add(listBean_two);
                    break;
            }

            mAdapter_classification_right.notifyDataSetChanged();
        }


    }

    void requestRightMenu(int id) {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "二级专业id");
                mListData_right.clear();
                mListData_right.addAll(new Gson().fromJson(respose, Skillmenu_twoBean.class).getData().getList());
                mAdapter_classification_right.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.Skillmenu_right + "?specialty_id=" + id, getContext(), 0);
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
        if (!hidden) {
            requestOnemenu();
        }

    }

}
