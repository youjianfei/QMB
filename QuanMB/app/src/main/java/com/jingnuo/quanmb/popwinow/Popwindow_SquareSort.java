package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaygoo.widget.RangeSeekBar;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_square_sort;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.PopwindowGridBean;
import com.jingnuo.quanmb.entityclass.Spuare_sortBean;
import com.jingnuo.quanmb.entityclass.TaskTypeBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/4/2.
 */

public class Popwindow_SquareSort {
    View conView;
    private Activity activity;
    private InterfacePopwindow_square_sort mInterface;
    private RelativeLayout mLinearLayout;//用于定位 popwindow弹出的位置
    private int type = 0;
    PopupWindow mPopupWindow;
    View mInclude_sort;
    View mInclude_filter;
    LinearLayout mText_sort_title, mText_filter_title;


    //sort_pop用到的布局 数据
    private ImageView mImage_black;//下面的遮罩
    List<Spuare_sortBean.DataBean.ListBean> mData_sort;//排序方式的list数据

    SortAdapter mAdapterSort;
    MyListView mListview_pop_sort;


    //filter_pop用到的布局和数据
    private DecimalFormat df = new DecimalFormat("0");
    FilterAdapter mAdapter_filter_task;
    FilterAdapter mAdapter_filter_level;
    MyGridView mGridview_filter_task;
    List<TaskTypeBean.DataBean.ListBean> listdata_tasktype;//接口返回的任务类型列表
//    List<PopwindowGridBean.FilterBean> mData_filter_level;

    TextView mText_filter_left, mText_filter_right;
    RangeSeekBar mSeekBar;
    Button mButton_chongzhi, mButton_complete;

    String TYpe = "";//多个选择类型拼接
    int Min = 0;
    int Max = 1000;


    public Popwindow_SquareSort(Activity activity, InterfacePopwindow_square_sort mInterface, RelativeLayout mLinearLayout, int type) {
        this.activity = activity;
        this.mInterface = mInterface;
        this.mLinearLayout = mLinearLayout;
        this.type = type;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showPopwindow() {

        //初始化popwindow；
        conView = LayoutInflater.from(activity).inflate(R.layout.popwindow_square_sort_layout, null, false);
        mPopupWindow = new PopupWindow(conView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popmenu_animation);
        mPopupWindow.showAsDropDown(mLinearLayout, 0, 0, Gravity.BOTTOM);

        initview();
        initdata();
        initlistenner();
        request_taskType();
        request_taskSort();
    }

    //请求智能排序list
    private void request_taskSort() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                if (new Gson().fromJson(respose, Spuare_sortBean.class).getData().getList() != null) {
                    mData_sort.clear();
                    mData_sort.addAll(new Gson().fromJson(respose, Spuare_sortBean.class).getData().getList());
                    mAdapterSort.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui + Urls.tasksort, activity, 0);

    }

    //请求任务类型gridlist
    private void request_taskType() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                listdata_tasktype.clear();
                listdata_tasktype.addAll(new Gson().fromJson(respose, TaskTypeBean.class).getData().getList());
                Staticdata. mData_filter_task.clear();
                if(Staticdata. mData_filter_task.size()!=listdata_tasktype.size()){
                    for (int i = 0; i < listdata_tasktype.size(); i++) {
                        PopwindowGridBean.FilterBean bean = new PopwindowGridBean.FilterBean();
                        bean.setChoose(false);
                        bean.setId(listdata_tasktype.get(i).getSpecialty_id());
                        bean.setText(listdata_tasktype.get(i).getSpecialty_name());
                        Staticdata. mData_filter_task.add(bean);
                    }
                }

                mAdapter_filter_task.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui + Urls.tasktype, activity, 0);
    }

    private void initlistenner() {
        //点击筛选切换布局
        mText_filter_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.LOG("ceshi", "type=" + type, "pop");
                if (type == 0) {
                    mPopupWindow.dismiss();
                } else {
                    type = 0;
                    mInclude_filter.setVisibility(View.VISIBLE);
                    mInclude_sort.setVisibility(View.GONE);
                }
            }
        });
        //点击排序切换布局
        mText_sort_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.LOG("ceshi", "type=" + type, "pop");

                if (type == 1) {
                    mPopupWindow.dismiss();
                } else {
                    type = 1;
                    mInclude_filter.setVisibility(View.GONE);
                    mInclude_sort.setVisibility(View.VISIBLE);
                }
            }
        });
        //点击遮罩关闭
        mImage_black.setOnClickListener(new View.OnClickListener() {//为了解决下面半透明问题暂时用的方法
            @Override
            public void onClick(View view) {
                LogUtils.LOG("ceshi", "dianji 半透明", "pop");
                mPopupWindow.dismiss();
            }
        });
        /**
         * sort_pop用到的
         */
        mListview_pop_sort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mInterface.onSuccesses(mData_sort.get(i).getName(), mData_sort.get(i).getCode(),1);
                mPopupWindow.dismiss();

            }
        });


        /**
         *  filter_pop用到的
         */
        mGridview_filter_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ToastUtils.showToast(activity,mData_filter_task.get(i).getText());
                LogUtils.LOG("ceshi",Staticdata. mData_filter_task.get(i).getText(), "pop");
                if (Staticdata.mData_filter_task.get(i).isChoose()) {
                    Staticdata.mData_filter_task.get(i).setChoose(false);
                } else {
                    Staticdata.mData_filter_task.get(i).setChoose(true);
                }
                mAdapter_filter_task.notifyDataSetChanged();

            }
        });


        mSeekBar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                LogUtils.LOG("ceshi", "min:" + min + "max:" + max + "isFromuser" + isFromUser, "popwinsow_filter");
                if (isFromUser) {
                    mText_filter_left.setText("￥  " + df.format(min));
                    if(max==1000){
                        mText_filter_right.setText("￥  " + df.format(max)+"+");
                    }else {
                        mText_filter_right.setText("￥  " + df.format(max));
                    }

                    mSeekBar.setLeftProgressDescription(df.format(min));
                    mSeekBar.setRightProgressDescription(df.format(max));
                    Max = (int) max;
                    Min = (int) min;
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
//        mGridview_filter_level.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ToastUtils.showToast(activity,mData_filter_level.get(i).getText());
//                LogUtils.LOG("ceshi",mData_filter_level.get(i).getText(),"pop");
//                if(mData_filter_level.get(i).isChoose()){
//                    mData_filter_level.get(i).setChoose(false);
//                }else {
//                    mData_filter_level.get(i).setChoose(true);
//
//                }
//                mAdapter_filter_level.notifyDataSetChanged();
//            }
//        });

        mButton_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TYpe = "";
                for (int i = 0; i < Staticdata.mData_filter_task.size(); i++) {
                    if (Staticdata.mData_filter_task.get(i).isChoose()) {
                        TYpe = TYpe + Staticdata.mData_filter_task.get(i).getId() + ",";
                    }
                }
                LogUtils.LOG("ceshi", "条件拼接" + TYpe, "任务条件筛选");
                mInterface.onSuccesses(TYpe, Min + "%" + Max,0);
                mPopupWindow.dismiss();
            }
        });
        mButton_chongzhi.setOnClickListener(new View.OnClickListener() {//重置按钮点击事件
            @Override
            public void onClick(View v) {
                //重置任务类型
                for (int i = 0; i < Staticdata.mData_filter_task.size(); i++) {
                    Staticdata.mData_filter_task.get(i).setChoose(false);
                }
                mAdapter_filter_task.notifyDataSetChanged();


                //重置价格区间
                mSeekBar.setValue(0, 1000);//设置初始值
                Max = (int) 0;
                Min = (int) 1000;
                mText_filter_left.setText("￥  " + df.format(0));
                mText_filter_right.setText("￥  " + df.format(1000)+"+");

            }
        });

    }

    public void initview() {
        mInclude_sort = conView.findViewById(R.id.include_sort);
        mInclude_filter = conView.findViewById(R.id.include_filter);
        if (type == 0) {
            mInclude_filter.setVisibility(View.VISIBLE);
            mInclude_sort.setVisibility(View.GONE);
        } else {
            mInclude_filter.setVisibility(View.GONE);
            mInclude_sort.setVisibility(View.VISIBLE);
        }
        mText_sort_title = conView.findViewById(R.id.text_sort_title);
        mText_filter_title = conView.findViewById(R.id.text_filter_title);
        //sort_pop用到的布局 数据
        mImage_black = conView.findViewById(R.id.iamgeview_a);
        mListview_pop_sort = conView.findViewById(R.id.list_sort);
        mImage_black.setBackgroundResource(R.color.black);
        mImage_black.setAlpha((float) 0.7);
        //filter_pop用到的布局和数据
        mGridview_filter_task = conView.findViewById(R.id.mygridview_filter);
//        mGridview_filter_level = conView.findViewById(R.id.mygridview_filter_levle);
        mText_filter_left = conView.findViewById(R.id.textview_filter_left);
        mText_filter_right = conView.findViewById(R.id.textview_filter_right);
        mSeekBar = conView.findViewById(R.id.seekbar);
        mButton_chongzhi = conView.findViewById(R.id.button_chongzhi);
        mButton_complete = conView.findViewById(R.id.button_complte);
    }

    public void initdata() {
        //sort_pop用到的布局 数据
        mData_sort = new ArrayList<>();
        mAdapterSort = new SortAdapter(mData_sort, activity);
        mListview_pop_sort.setAdapter(mAdapterSort);

        //filter_pop用到的布局和数据
        listdata_tasktype = new ArrayList<>();

        mSeekBar.setRange(0, 1000);//设置范围
        mSeekBar.setValue(0, 1000);//设置初始值
        mText_filter_right.setText("￥  1000+");
        mAdapter_filter_task = new FilterAdapter(Staticdata.mData_filter_task, activity);
        mGridview_filter_task.setAdapter(mAdapter_filter_task);

//        mData_filter_level=new ArrayList<>();
//        for (int i=0;i<4;i++){
//            PopwindowGridBean.FilterBean  bean=new PopwindowGridBean.FilterBean();
//            bean.setChoose(false);
//            bean.setId(i);
//            bean.setText(i+"级");
//            mData_filter_level.add(bean);
//        }
//        mAdapter_filter_level=new FilterAdapter(mData_filter_level,activity);
//        mGridview_filter_level.setAdapter(mAdapter_filter_level);

    }


    class SortAdapter extends BaseAdapter {
        List<Spuare_sortBean.DataBean.ListBean> mData;
        Context mContext;
        LayoutInflater mInflater;

        public SortAdapter(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mContext = mContext;
            this.mData = mDatas;
            mInflater = LayoutInflater.from(mContext);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_listview_pop_square_sort, null, false);
                holder.mTextview = convertView.findViewById(R.id.text_sort);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();

            }
            holder.mTextview.setText(mData.get(position).getName());

            return convertView;
        }

        class ViewHolder {
            TextView mTextview;
        }
    }

    class FilterAdapter extends BaseAdapter {
        List<PopwindowGridBean.FilterBean> mData_grid;
        Context mContext;
        LayoutInflater mInfter;

        public FilterAdapter(List<PopwindowGridBean.FilterBean> mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mData_grid = mDatas;
            this.mContext = mContext;
            mInfter = LayoutInflater.from(mContext);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInfter.inflate(R.layout.item_gridview_pop_square_filter, null, false);
                holder.mTextview = convertView.findViewById(R.id.textview_square_filter);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTextview.setSelected(mData_grid.get(position).isChoose());
            holder.mTextview.setText(mData_grid.get(position).getText());

            return convertView;
        }
    }

    class ViewHolder {
        TextView mTextview;
    }
}
