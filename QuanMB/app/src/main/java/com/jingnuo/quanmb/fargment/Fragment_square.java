package com.jingnuo.quanmb.fargment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jingnuo.quanmb.Adapter.Adapter_SquareList;
import com.jingnuo.quanmb.Interface.InterfaceBaiduAddress;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_square_sort;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.LocationaddressActivity;
import com.jingnuo.quanmb.activity.MytaskDetailActivity;
import com.jingnuo.quanmb.activity.TaskDetailsActivity;
import com.jingnuo.quanmb.broadcastrReceiver.BaiduAddressBroadcastReciver;
import com.jingnuo.quanmb.class_.GlideLoader;
import com.jingnuo.quanmb.class_.GlideLoader22;
import com.jingnuo.quanmb.popwinow.Popwindow_SquareSort;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Square_defaultBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

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

    View listheadView;
    //控件
    EditText mEdit_serchSquare;
    ImageView mImageview_jiantou;
    PullToRefreshListView mListview_square;
    TextView mTextview_sort, mTextview_filter;
    TextView mTextview_address;
    RelativeLayout mRelativelayout_sort;
    RelativeLayout mRelayout_address;
    RelativeLayout relative_shaixuan;

    Popwindow_SquareSort mPopwindow_square_sort;



    //对象
    Adapter_SquareList mAdapter_SquareList;

    private IntentFilter intentFilter_bauduaddress;//定义广播过滤器；
    private BaiduAddressBroadcastReciver baiduAddressBroadcastReciver;

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
        setdata();
        setview();
        initlistenner();
        request_square(map_filter_sort, page);//首页默认请求 page==1

        return rootview;
    }
    void  setdata(){
        intentFilter_bauduaddress = new IntentFilter();
        intentFilter_bauduaddress.addAction("com.jingnuo.quanmb.ADDRESS");
        baiduAddressBroadcastReciver=new BaiduAddressBroadcastReciver(new InterfaceBaiduAddress() {
            @Override
            public void onResult(final String address) {
               getActivity(). runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {
                        if(address.equals("筛选")){
                            mListview_square.getRefreshableView().setSelectionFromTop(2,SizeUtils.dip2px(getActivity(),69));
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

                        }else if(address.equals("排序")){

                            mListview_square.getRefreshableView().setSelectionFromTop(2,SizeUtils.dip2px(getActivity(),69));

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
                        } else {
                            mTextview_address.setText(address);
                            map_filter_sort.put("city_code",address);
                            map_filter_sort.put("x_value", Staticdata.xValue);
                            map_filter_sort.put("y_value", Staticdata.yValue);
                            page = 1;
                            request_square(map_filter_sort, 1);
                        }

                    }
                });
            }
        });
        getActivity(). registerReceiver(baiduAddressBroadcastReciver, intentFilter_bauduaddress); //将广播监听器和过滤器注册在一起；
    }

    private void request_square(final Map map_filterOrsort, final int page) {
        map_filterOrsort.put("pageNum", page + "");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (mListview_square.isRefreshing()) {
                    mListview_square.onRefreshComplete();
                }
                LogUtils.LOG("ceshi", "" + map_filterOrsort.toString(), "fragmentsquare");

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

        mRelayout_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_address=new Intent(getActivity(), LocationaddressActivity.class);
                startActivity(intent_address);
            }
        });
        //listview的条目点击事件
        mListview_square.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                LogUtils.LOG("ceshi", "" , "fragmentsquare");
                if(Staticdata.isLogin&&mListDate_square.get(i-2).getClient_no().equals(Staticdata.static_userBean.getData().getAppuser().getClient_no())){
                    intend_taskdrtails = new Intent(getActivity(), MytaskDetailActivity.class);
                    intend_taskdrtails.putExtra("id", mListDate_square.get(i-2).getTask_ID()+"");
                    getActivity().startActivity(intend_taskdrtails);
                }else {
                    intend_taskdrtails = new Intent(getActivity(), TaskDetailsActivity.class);
                    intend_taskdrtails.putExtra("id", mListDate_square.get(i-2).getTask_ID()+"");
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
//        //监听键盘确定按钮，以便直接搜索
//        mEdit_serchSquare.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                //当actionId == XX_SEND 或者 XX_DONE时都触发
//                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
//                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
//                if (actionId == EditorInfo.IME_ACTION_SEND
//                        || actionId == EditorInfo.IME_ACTION_DONE
//                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
//                    //处理事件
//                    LogUtils.LOG("ceshi", "点击了确定按钮", "fragmentsquare");
//                    String search = "";
//                    search = mEdit_serchSquare.getText() + "";
//                    if(search.length()>5){
//                        ToastUtils.showToast(getContext(),"搜索关键字太长");
//                        return false;
//                    }
//                    String searchhou = Utils.ZhuanMa(search);
//                    initMap(MinCommission+"",MaxCommission+"",page+"",searchhou,"","");
//                    request_square(map_filter_sort, page);
//
//                }
//                return false;
//            }
//        });
        mEdit_serchSquare.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.LOG("ceshi",s+"","spisup");
                String search = "";
                search = mEdit_serchSquare.getText() + "";
                if(search.length()>5){
                    ToastUtils.showToast(getContext(),"搜索关键字太长");
                    return ;
                }
//                String searchhou = Utils.ZhuanMa(search);
                initMap(MinCommission+"",MaxCommission+"",page+"",search,"","");
                request_square(map_filter_sort, page);

            }
        });
        //listview的滑动距离监听
        mListview_square.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if(firstVisibleItem==1){
//                    mRelativelayout_sort.setBackgroundColor(Color.argb(255, 255, 255, 255));
//
//                }
                if(isScroll())
                {
                    float scrollY = getScrollY();
                    if(scrollY <= 1)
                    {
                        int alpha = (int) (255 * scrollY);

                        LogUtils.LOG("ceshi",alpha+"alpha"+firstVisibleItem,"透明度");
//                        mRelativelayout_sort.setAlpha(alpha);
                        mRelativelayout_sort.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                        relative_shaixuan.setVisibility(View.INVISIBLE);
                        mImageview_jiantou.setImageResource(R.mipmap.jiantou_xiabai);
                        mTextview_address.setTextColor(getActivity().getResources().getColor(R.color.white));
                    }else {
                        relative_shaixuan.setVisibility(View.VISIBLE);
                        mImageview_jiantou.setImageResource(R.mipmap.jiantou_xia);
                        mTextview_address.setTextColor(getActivity().getResources().getColor(R.color.black));
                        mRelativelayout_sort.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    }
                }

            }
        });
    }
    /**
     * 判断是否是第一行
     * @return
     */
    private boolean isScroll()
    {
        if(mListview_square.getRefreshableView().getFirstVisiblePosition() == 1 || mListview_square.getRefreshableView().getFirstVisiblePosition() == 0)
        {
            return true;
        }
        return false;
    }
    /**
     * 得到高度比例
     * @return
     */
    private float getScrollY()
    {
        View c = mListview_square.getRefreshableView().getChildAt(0);
        if (c == null)
        {
            return 0;
        }
        int firstVisiblePosition = mListview_square.getRefreshableView().getFirstVisiblePosition();
        if(firstVisiblePosition == 1 || firstVisiblePosition == 0)
        {
            //如果可见的是第一行或第二行，那么开始计算距离比例
            float top = c.getTop();
            //当第一行已经开始消失的时候，top是为负数的，所以取正
            top = Math.abs(top);
            //48为菜单栏的高度，单位为dp
            //得到的高度为ViewPager的高度减去菜单栏高度，即为最大可滑动距离
            float height = c.getHeight() - SizeUtils.dip2px(getContext(),70);

            float y = top / height;

            return y;
        }else
        {
            return 0;
        }
    }
    private void setview() {

    }

    private void initdata() {
        map_filter_sort = new HashMap();
        initMap(MinCommission+"",MaxCommission+"",page+"","","","");//默认展示
        mListDate_square = new ArrayList<>();
        mAdapter_SquareList = new Adapter_SquareList(mListDate_square, getActivity());
        mListview_square.setAdapter(mAdapter_SquareList);
    }
    void initMap(String  minCommission,String  maxCommission,String  pageNum,String  name,String  task_type,String  code){
        map_filter_sort.put("minCommission", minCommission);
        map_filter_sort.put("maxCommission", maxCommission);
        map_filter_sort.put("pageNum", pageNum);
        map_filter_sort.put("name", name);//关键字搜索
        map_filter_sort.put("task_type", task_type);//条件筛选
        map_filter_sort.put("code", code);//排序方式筛选
        map_filter_sort.put("x_value", Staticdata.xValue);
        map_filter_sort.put("y_value", Staticdata.yValue);
    }

    private void initview() {
        mEdit_serchSquare = rootview.findViewById(R.id.edit_searchSquare);
        mListview_square = rootview.findViewById(R.id.list_square);
        mImageview_jiantou = rootview.findViewById(R.id.iamge_jiantou);
        mTextview_sort = rootview.findViewById(R.id.text_sort);
        mTextview_filter = rootview.findViewById(R.id.text_filter);
        mRelativelayout_sort = rootview.findViewById(R.id.relative_sort);
        mTextview_address=rootview.findViewById(R.id.textview_login);
        mRelayout_address=rootview.findViewById(R.id.relayout_address);
        relative_shaixuan=rootview.findViewById(R.id.relative_shaixuan);
        listheadView=LayoutInflater.from(getContext()).inflate(R.layout.list_headview_square,null,false);
        mListview_square.getRefreshableView().addHeaderView(listheadView);
        /**
         * headview  控件
         */
        RelativeLayout relativeLayout_headbackground=listheadView.findViewById(R.id.relativeLayout_headbackground);
        AbsListView.LayoutParams mLayoutparams = new AbsListView.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth * 0.6));

        relativeLayout_headbackground.setLayoutParams(mLayoutparams);
        Banner banner = listheadView. findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideLoader22());
        List<String> images=new ArrayList<>();
        images.add("http://a1.att.hudong.com/02/52/300001202343130274523014865_950.jpg");
        images.add("http://pic19.nipic.com/20120210/9335935_195635332170_2.jpg");
        images.add("http://a2.att.hudong.com/53/51/16300000178518124461515846560_950.jpg");
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onResume() {
        super.onResume();
//        page = 1;
//        request_square(map_filter_sort, 1);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       getActivity(). unregisterReceiver(baiduAddressBroadcastReciver);
    }
}
