package com.jingnuo.quanmb.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
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
import com.jingnuo.quanmb.broadcastrReceiver.BaiduAddressBroadcastReciver;
import com.jingnuo.quanmb.class_.Chengweibangshou;
import com.jingnuo.quanmb.class_.GlideLoader22;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.GuanggaoBean;
import com.jingnuo.quanmb.entityclass.Square_defaultBean;
import com.jingnuo.quanmb.popwinow.Popwindow_SquareSort;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.youth.banner.Banner;
import com.jingnuo.quanmb.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jingnuo.quanmb.data.Staticdata.ScreenWidth;

public class SquareActuvity extends BaseActivityother {


    View listheadView;
    //控件
    EditText mEdit_serchSquare;
    ImageView mImageview_jiantou;
    PullToRefreshListView mListview_square;
    LinearLayout mTextview_sort, mTextview_filter;
    TextView mTextview_address;
    RelativeLayout re_title;
    RelativeLayout mRelayout_address;
    RelativeLayout relative_sort;
    RelativeLayout relative_shaixuan;
    ImageView imageView_image_empty;

    Popwindow_SquareSort mPopwindow_square_sort;

    //头视图
    Banner banner;
    LinearLayout shaixuan;
    LinearLayout paixu;
    EditText mEdit_serchSquare_head;

    Chengweibangshou chengweibangshou;

    KProgressHUD mKProgressHUD;


    //对象
    Adapter_SquareList mAdapter_SquareList;

    private IntentFilter intentFilter_bauduaddress;//定义广播过滤器；
    private BaiduAddressBroadcastReciver baiduAddressBroadcastReciver;

    //数据
    Map map_filter_sort;
    Square_defaultBean.DataBean mSquare_default_DataBean;
    List<Square_defaultBean.DataBean.ListBean> mListDate_square;
    int page = 1;//分页加载；
    int MinCommission = 0, MaxCommission = 1000;
    int refresh=0;//是否请求刷新数据   1  是

    List<GuanggaoBean.DataBean> mdata_image_GG;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_square_actuvity;
    }

    @Override
    protected void setData() {
        intentFilter_bauduaddress = new IntentFilter();
        intentFilter_bauduaddress.addAction("com.jingnuo.quanmb.ADDRESS");
        baiduAddressBroadcastReciver = new BaiduAddressBroadcastReciver(new InterfaceBaiduAddress() {
            @Override
            public void onResult(final String address) {
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {

                        if (address.equals("筛选")) {
                            mListview_square.getRefreshableView().setSelection(2);
                            showPopwindow(0);

                        } else if (address.equals("排序")) {
                            mListview_square.getRefreshableView().setSelection(2);
                            showPopwindow(1);
                        } else {
                            mTextview_address.setText(address);
                            map_filter_sort.put("city_code", address);
                            map_filter_sort.put("x_value", Staticdata.xValue);
                            map_filter_sort.put("y_value", Staticdata.yValue);
                            page = 1;
                            request_square(map_filter_sort, 1);
                        }
                    }
                });
            }
        });
        registerReceiver(baiduAddressBroadcastReciver, intentFilter_bauduaddress); //将广播监听器和过滤器注册在一起；
    }

    @Override
    protected void initData() {
        map_filter_sort = new HashMap();
        if (!Staticdata.city_location.equals("")) {
            mTextview_address.setText(Staticdata.city_location);
            map_filter_sort.put("city_code", Staticdata.city_location);
        }

        mKProgressHUD = new KProgressHUD(this);
        chengweibangshou = new Chengweibangshou(this);
        mdata_image_GG = new ArrayList<>();
        initMap(MinCommission + "", MaxCommission + "", page + "", "", "", "");//默认展示
        mListDate_square = new ArrayList<>();
        mAdapter_SquareList = new Adapter_SquareList(mListDate_square, this);
        mListview_square.setAdapter(mAdapter_SquareList);

        request_square(map_filter_sort, page);//首页默认请求 page==1
        request_GGLB();//请求轮播图

    }

    private void request_square(final Map map_filterOrsort, final int page) {
        map_filterOrsort.put("pageNum", page + "");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                mKProgressHUD.dismiss();
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


                    if (page == 1  && mSquare_default_DataBean.getList().size() != 0) {
                        mListDate_square.clear();
                        mListDate_square.addAll(mSquare_default_DataBean.getList());
                        imageView_image_empty.setVisibility(mListDate_square.size() == 0?View.VISIBLE:View.GONE);
                        mAdapter_SquareList.notifyDataSetChanged();
                    } else if (page != 1 ) {
                        mListDate_square.addAll(mSquare_default_DataBean.getList());
                        mAdapter_SquareList.notifyDataSetChanged();
                        imageView_image_empty.setVisibility(mListDate_square.size() == 0?View.VISIBLE:View.GONE);
                    }else {
                        mListDate_square.clear();
                        mAdapter_SquareList.notifyDataSetChanged();
                        imageView_image_empty.setVisibility(mListDate_square.size() == 0?View.VISIBLE:View.GONE);
                        LogUtils.LOG("ceshi", "mListDate_square.clear()" , "mListDate_square");
                    }

                } else {
                    ToastUtils.showToast(SquareActuvity.this,msg);

                }


            }

            @Override
            public void onError(int error) {
                mKProgressHUD.dismiss();
            }
        }).postHttp(Urls.Baseurl_cui + Urls.square_default, SquareActuvity.this, 1, map_filterOrsort);


    }

    private void request_GGLB() {//请求网络轮播图
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshiddd", "轮播图片：" + respose, "ShopCenterActivity");
                mdata_image_GG.clear();
                mdata_image_GG.addAll(new Gson().fromJson(respose, GuanggaoBean.class).getData());
                List<String> images = new ArrayList<>();
                for (int i = 0; i < mdata_image_GG.size(); i++) {
                    images.add(mdata_image_GG.get(i).getImg_url());
                }
                //设置图片集合
                banner.setImages(images);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui + Urls.shouyePic + "1", SquareActuvity.this, 0);
        LogUtils.LOG("ceshiddd", "轮播图片：" + Urls.Baseurl_cui + Urls.shouyePic, "fragment_square");
    }

    Intent intend_taskdrtails;

    @Override
    protected void initListener() {
        mRelayout_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_address = new Intent(SquareActuvity.this, LocationaddressActivity.class);
                startActivity(intent_address);
            }
        });
        //listview的条目点击事件
        mListview_square.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!mListDate_square.get(i - 2).getClient_no().equals("")) {//传过来假数据的判断

                    LogUtils.LOG("ceshi", "", "fragmentsquare");
                    if (Staticdata.isLogin && mListDate_square.get(i - 2).getClient_no().equals(Staticdata.static_userBean.getData().getAppuser().getClient_no())) {
                        intend_taskdrtails = new Intent(SquareActuvity.this, MytaskDetailActivity.class);
                        intend_taskdrtails.putExtra("id", mListDate_square.get(i - 2).getTask_ID() + "");
                        startActivity(intend_taskdrtails);
                    } else {
                        intend_taskdrtails = new Intent(SquareActuvity.this, TaskDetailsActivity.class);
                        intend_taskdrtails.putExtra("id", mListDate_square.get(i - 2).getTask_ID() + "");
                        startActivity(intend_taskdrtails);
                    }
                }
            }
        });

        //智能排序文字点击  type=1
        mTextview_sort.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                showPopwindow(1);

            }
        });
        // 点击筛选  type=0
        mTextview_filter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                showPopwindow(0);
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
        mEdit_serchSquare.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.LOG("ceshi", s + "", "spisup");
                String search = "";
                search = mEdit_serchSquare.getText() + "";
                if (search.length() > 5) {
                    ToastUtils.showToast(SquareActuvity.this, "搜索关键字太长");
                    return;
                }
//                String searchhou = Utils.ZhuanMa(search);
                initMap(MinCommission + "", MaxCommission + "", page + "", search, "", "");
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

                if(isScroll()) {
                    float scrollY = getScrollY();
                    LogUtils.LOG("ceshi","scrollY"+scrollY,"透明度");

                    if(scrollY >0.68)
                    {
//                        int alpha = (int) (255 * scrollY);
                    relative_sort.setVisibility(View.VISIBLE);

//                        LogUtils.LOG("ceshi",alpha+"scrollY"+scrollY,"透明度");
//                        mRelativelayout_sort.setAlpha(alpha);
//                        mRelativelayout_sort.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
//                        relative_shaixuan.setVisibility(View.INVISIBLE);
                    }else {
//                        relative_shaixuan.setVisibility(View.VISIBLE);
                        relative_sort.setVisibility(View.INVISIBLE);

//                        mRelativelayout_sort.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    }
                }else {
                    if (firstVisibleItem > 1) {
                        relative_sort.setVisibility(View.VISIBLE);
                    } else {
                        relative_sort.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });
    }

    @Override
    protected void initView() {
        mEdit_serchSquare = findViewById(R.id.edit_searchSquare);
        mListview_square = findViewById(R.id.list_square);
        mImageview_jiantou = findViewById(R.id.iamge_jiantou);
        mTextview_sort = findViewById(R.id.text_sort);
        mTextview_filter = findViewById(R.id.text_filter);
        re_title = findViewById(R.id.re_title);
        mTextview_address = findViewById(R.id.textview_login);
        mRelayout_address = findViewById(R.id.relayout_address);
        relative_sort = findViewById(R.id.relative_sort);
        relative_shaixuan = findViewById(R.id.relative_shaixuan);
        imageView_image_empty=findViewById(R.id.image_empty);
        listheadView = LayoutInflater.from(this).inflate(R.layout.list_headview_square, null, false);
        mListview_square.getRefreshableView().addHeaderView(listheadView);
        /**
         * headview  控件
         */

        RelativeLayout relativeLayout_headbackground = listheadView.findViewById(R.id.relativeLayout_headbackground);
        RelativeLayout.LayoutParams mLayoutparams = new RelativeLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth * 0.26));
        relativeLayout_headbackground.setLayoutParams(mLayoutparams);

        banner = listheadView.findViewById(R.id.banner);
        shaixuan = listheadView.findViewById(R.id.text_filter);
        paixu = listheadView.findViewById(R.id.text_sort);
        mEdit_serchSquare_head = listheadView.findViewById(R.id.edit_searchSquare);

        //设置图片加载器
        banner.setImageLoader(new GlideLoader22());
        shaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mListview_square.getRefreshableView().setSelectionFromTop(2, SizeUtils.dip2px(SquareActuvity.this, 80));
//                mListview_square.getRefreshableView().setSelection(2);
                mListview_square.getRefreshableView().setSelectionFromTop(2,SizeUtils.dip2px(SquareActuvity.this,85));

                showPopwindow(0);
            }
        });
        paixu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListview_square.getRefreshableView().setSelectionFromTop(2, SizeUtils.dip2px(SquareActuvity.this, 85));
//                mListview_square.getRefreshableView().setSelection(2);
                showPopwindow(1);
            }
        });
        mEdit_serchSquare_head.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.LOG("ceshi", s + "", "spisup");
                String search = "";
                search = mEdit_serchSquare_head.getText() + "";
                if (search.length() > 5) {
                    ToastUtils.showToast(SquareActuvity.this, "搜索关键字太长");
                    return;
                }
//                String searchhou = Utils.ZhuanMa(search);
                initMap(MinCommission + "", MaxCommission + "", page + "", search, "", "");
                request_square(map_filter_sort, page);

            }
        });
    }

    void initMap(String minCommission, String maxCommission, String pageNum, String name, String task_type, String code) {
        map_filter_sort.put("minCommission", minCommission);
        map_filter_sort.put("maxCommission", maxCommission);
        map_filter_sort.put("pageNum", pageNum);
        map_filter_sort.put("name", name);//关键字搜索
        map_filter_sort.put("task_type", task_type);//条件筛选
        map_filter_sort.put("code", code);//排序方式筛选
        map_filter_sort.put("x_value", Staticdata.xValue);
        map_filter_sort.put("y_value", Staticdata.yValue);
    }

    /**
     * 判断是否是第一行
     *
     * @return
     */
    private boolean isScroll() {
        if (mListview_square.getRefreshableView().getFirstVisiblePosition() == 1 || mListview_square.getRefreshableView().getFirstVisiblePosition() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 得到高度比例
     *
     * @return
     */
    private float getScrollY() {
        View c = mListview_square.getRefreshableView().getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = mListview_square.getRefreshableView().getFirstVisiblePosition();
        if (firstVisiblePosition == 1 || firstVisiblePosition == 0) {
            //如果可见的是第一行或第二行，那么开始计算距离比例
            float top = c.getTop();
            //当第一行已经开始消失的时候，top是为负数的，所以取正
            top = Math.abs(top);
            //48为菜单栏的高度，单位为dp
            //得到的高度为ViewPager的高度减去菜单栏高度，即为最大可滑动距离
            float height = c.getHeight() - SizeUtils.dip2px(this, 40);

            float y = top / height;

            return y;
        } else {
            return 0;
        }
    }

    @SuppressLint("NewApi")
    void showPopwindow(final int leizing) {

        mPopwindow_square_sort = new Popwindow_SquareSort(SquareActuvity.this, new InterfacePopwindow_square_sort() {
            @Override
            public void onSuccesses(String address, String id, int type) {
                if (type == 0) {//筛选
                    page = 1;
                    LogUtils.LOG("ceshi", address + id, "条件筛选");
                    String[] Q = id.split("%");
                    initMap(Q[0], Q[1], page + "", "", address, "");
                    map_filter_sort.remove("hotTask");//防止热门任务下双重条件筛选
                    request_square(map_filter_sort, page);
                } else {//排序方式
                    page = 1;
                    LogUtils.LOG("ceshi", address + id, "排序方式");
                    initMap(MinCommission + "", MaxCommission + "", page + "", "", "", id + "");
                    request_square(map_filter_sort, page);
                }

            }
        }, re_title, leizing);
        mPopwindow_square_sort.showPopwindow();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        refresh=getIntent().getIntExtra("refresh",0);
        if(refresh==1){
            request_square(map_filter_sort,1);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(baiduAddressBroadcastReciver);
    }

}
