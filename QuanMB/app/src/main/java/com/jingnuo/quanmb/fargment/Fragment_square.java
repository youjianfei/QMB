package com.jingnuo.quanmb.fargment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_SquareList;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_square_sort;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.MytaskDetailActivity;
import com.jingnuo.quanmb.activity.TaskDetailsActivity;
import com.jingnuo.quanmb.class_.Popwindow_SquareSort;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Square_defaultBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_square extends Fragment {

    View rootview;
    //控件
    EditText mEdit_serchSquare;
    PullToRefreshListView mListview_square;
    TextView mTextview_sort, mTextview_filter;
    RelativeLayout mRelativelayout_sort;

    Popwindow_SquareSort mPopwindow_square_sort;


    //对象
    Adapter_SquareList mAdapter_SquareList;


    //数据
    Map map_filter_sort;
    Square_defaultBean.DataBean mSquare_default_DataBean;
    List<Square_defaultBean.DataBean.ListBean> mListDate_square;
    int page = 1;//分页加载；
    int MinCommission=0,MaxCommission=1000;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_square, container, false);
        initview();
        initdata();
        setview();
        initlistenner();
        request_square(map_filter_sort, page);//首页默认请求 page==1

        return rootview;
    }

    private void request_square(Map map_filterOrsort, final int page) {
        map_filterOrsort.put("pageNum", page + "");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (mListview_square.isRefreshing()) {
                    mListview_square.onRefreshComplete();
                }
                LogUtils.LOG("ceshi", "" + respose, "fragmentsquare");

                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("status");//
                    msg = (String) object.get("message");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {
                    mSquare_default_DataBean = new Gson().fromJson(respose, Square_defaultBean.class).getData();

//                    if (mSquare_default_DataBean.getList() == null || mSquare_default_DataBean.getList().size() == 0) {
//                        ToastUtils.showToast(getActivity(), "没有符合条件的任务");
//
//                        return;
//                    }
                    if (page == 1 && mSquare_default_DataBean.getList() != null) {
                        mListDate_square.clear();
                        mListDate_square.addAll(mSquare_default_DataBean.getList());

                        mAdapter_SquareList.notifyDataSetChanged();
                    } else if (page != 1 && mSquare_default_DataBean.getList() != null) {
                        mListDate_square.addAll(mSquare_default_DataBean.getList());
                        mAdapter_SquareList.notifyDataSetChanged();
                    }

                } else {
                    ToastUtils.showToast(getActivity(), msg);
                }


            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_cui + Urls.square_default, getActivity(), 1, map_filterOrsort);


    }

    Intent intend_taskdrtails;

    private void initlistenner() {
        //listview的条目点击事件
        mListview_square.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.LOG("ceshi", "" + i, "fragmentsquare");
                if(Staticdata.isLogin&&mListDate_square.get(i-1).getClient_no().equals(Staticdata.static_userBean.getData().getAppuser().getClient_no())){
                    intend_taskdrtails = new Intent(getActivity(), MytaskDetailActivity.class);
                    intend_taskdrtails.putExtra("id", mListDate_square.get(i-1).getTask_ID());
                    getActivity().startActivity(intend_taskdrtails);
                }else {
                    intend_taskdrtails = new Intent(getActivity(), TaskDetailsActivity.class);
                    intend_taskdrtails.putExtra("id", mListDate_square.get(i-1).getTask_ID());
                    getActivity().startActivity(intend_taskdrtails);
                }


            }
        });
        //智能排序文字点击  type=1
        mTextview_sort.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                mPopwindow_square_sort = new Popwindow_SquareSort(getActivity(), new InterfacePopwindow_square_sort() {
                    @Override
                    public void onSuccesses(String address, String id) {
                        page=1;
                        LogUtils.LOG("ceshi",address+id,"排序方式");
                        initMap(MinCommission+"",MaxCommission+"",page+"","","",id+"");
                        request_square(map_filter_sort,page);

                    }
                }, mRelativelayout_sort, 1);
                mPopwindow_square_sort.showPopwindow();

            }
        });
        // 点击筛选  type=0
        mTextview_filter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                mPopwindow_square_sort = new Popwindow_SquareSort(getActivity(), new InterfacePopwindow_square_sort() {
                    @Override
                    public void onSuccesses(String address, String id) {
                        page=1;
                        LogUtils.LOG("ceshi",address+id,"条件筛选");
                        String [] Q = id.split("%");
                        initMap(Q[0],Q[1],page+"","",address,"");
                        request_square(map_filter_sort,page);

                    }
                }, mRelativelayout_sort, 0);
                mPopwindow_square_sort.showPopwindow();
            }
        });

        //下拉  上拉 加载刷新
        mListview_square.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                request_square(map_filter_sort, 1);
                LogUtils.LOG("ceshi", "下拉刷新生效", "fragmentsquare");

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                request_square(map_filter_sort, ++page);
            }
        });
        //监听键盘确定按钮，以便直接搜索
        mEdit_serchSquare.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    LogUtils.LOG("ceshi", "点击了确定按钮", "fragmentsquare");
                    String search = "";
                    search = mEdit_serchSquare.getText() + "";
                    String searchhou = Utils.ZhuanMa(search);
                    initMap(MinCommission+"",MaxCommission+"",page+"",search,"","");
                    request_square(map_filter_sort, page);


                }
                return false;
            }
        });
    }

    private void setview() {

    }

    private void initdata() {
        map_filter_sort = new HashMap();
        initMap(MinCommission+"",MaxCommission+"",page+"","","","");//默认展示
        mListDate_square = new ArrayList<>();
        mAdapter_SquareList = new Adapter_SquareList(mListDate_square, getContext());
        mListview_square.setAdapter(mAdapter_SquareList);
    }
    void initMap(String  minCommission,String  maxCommission,String  pageNum,String  name,String  task_type,String  code){
        map_filter_sort.put("minCommission", minCommission);
        map_filter_sort.put("maxCommission", maxCommission);
        map_filter_sort.put("pageNum", pageNum);
        map_filter_sort.put("name", name);//关键字搜索
        map_filter_sort.put("task_type", task_type);//条件筛选
        map_filter_sort.put("code", code);//排序方式筛选
    }

    private void initview() {
        mEdit_serchSquare = rootview.findViewById(R.id.edit_searchSquare);
        mListview_square = rootview.findViewById(R.id.list_square);
        mTextview_sort = rootview.findViewById(R.id.text_sort);
        mTextview_filter = rootview.findViewById(R.id.text_filter);
        mRelativelayout_sort = rootview.findViewById(R.id.relative_sort);
    }


}
