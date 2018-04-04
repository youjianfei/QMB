package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaygoo.widget.RangeSeekBar;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_square_sort;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;

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
    private int type=0;
    PopupWindow mPopupWindow;
    View mInclude_sort;
    View mInclude_filter;
    TextView mText_sort_title,mText_filter_title;


    //sort_pop用到的布局 数据
    private ImageView mImage_black;//下面的遮罩
    List<String> mData_sort;//排序方式的list数据

    SortAdapter mAdapterSort;
    MyListView mListview_pop_sort;


    //filter_pop用到的布局和数据
    private DecimalFormat df = new DecimalFormat("0");
    FilterAdapter mAdapter_filter_task;
    FilterAdapter mAdapter_filter_level;
    MyGridView mGridview_filter_task,mGridview_filter_level;
    List<String> mData_filter_task;
    List<String> mData_filter_level;
    TextView mText_filter_left, mText_filter_right;
    RangeSeekBar mSeekBar;




    public Popwindow_SquareSort(Activity activity, InterfacePopwindow_square_sort mInterface, RelativeLayout mLinearLayout,int type) {
        this.activity = activity;
        this.mInterface = mInterface;
        this.mLinearLayout = mLinearLayout;
        this.type=type;
    }

    public void initPopwindow() {

        //初始化popwindow；
        conView = LayoutInflater.from(activity).inflate(R.layout.popwindow_square_sort_layout, null, false);
        mPopupWindow = new PopupWindow(conView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setAnimationStyle(R.style.popmenu_animation);



        initview();
        initdata();
        initlistenner();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showPopwindow(){
        if(mPopupWindow==null){
            initPopwindow();
            LogUtils.LOG("ceshi", "mPopupWindow   isS" , "pop");
        }


        if(mPopupWindow.isShowing()){
            LogUtils.LOG("ceshi", "mPopupWindow   isShowing" , "pop");
        }else {
            mPopupWindow.showAsDropDown(mLinearLayout, 0, 0, Gravity.BOTTOM);
        }
    }


    private void initlistenner() {
        mText_filter_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.LOG("ceshi","type="+type,"pop");
                if(type==0){
                    mPopupWindow.dismiss();
                }else {
                    type=0;
                    mInclude_filter.setVisibility(View.VISIBLE);
                    mInclude_sort.setVisibility(View.GONE);
                }
            }
        });
        mText_sort_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.LOG("ceshi","type="+type,"pop");

                if(type==1){
                    mPopupWindow.dismiss();
                }else {
                    type=1;
                    mInclude_filter.setVisibility(View.GONE);
                    mInclude_sort.setVisibility(View.VISIBLE);
                }
            }
        });
        /**
         * sort_pop用到的
         */

        mListview_pop_sort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.LOG("ceshi", "dianji 半透明" + i, "pop");
            }
        });
        mImage_black.setOnClickListener(new View.OnClickListener() {//为了解决下面半透明问题暂时用的方法
            @Override
            public void onClick(View view) {
                LogUtils.LOG("ceshi", "dianji 半透明", "pop");
                mPopupWindow.dismiss();
            }
        });

        /**
         *  filter_pop用到的
         */
        mSeekBar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                LogUtils.LOG("ceshi","min:"+min+"max:"+max+"isFromuser"+isFromUser,"popwinsow_filter");
                if (isFromUser) {
                    mText_filter_left.setText("￥  "+df.format(min));
                    mText_filter_right.setText("￥  "+df.format(max));
                    mSeekBar.setLeftProgressDescription(df.format(min));
                    mSeekBar.setRightProgressDescription(df.format(max));
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });


    }

    public void initview() {
        mInclude_sort=conView.findViewById(R.id.include_sort);
        mInclude_filter=conView.findViewById(R.id.include_filter);
        if(type==0){
            mInclude_filter.setVisibility(View.VISIBLE);
            mInclude_sort.setVisibility(View.GONE);
        }else {
            mInclude_filter.setVisibility(View.GONE);
            mInclude_sort.setVisibility(View.VISIBLE);
        }
        mText_sort_title=conView.findViewById(R.id.text_sort_title);
        mText_filter_title=conView.findViewById(R.id.text_filter_title);
        //sort_pop用到的布局 数据
        mImage_black = conView.findViewById(R.id.iamgeview_a);
        mListview_pop_sort = conView.findViewById(R.id.list_sort);
        mImage_black.setBackgroundResource(R.color.black);
        mImage_black.setAlpha((float) 0.7);
        //filter_pop用到的布局和数据
        mGridview_filter_task = conView.findViewById(R.id.mygridview_filter);
        mGridview_filter_level = conView.findViewById(R.id.mygridview_filter_levle);
        mText_filter_left = conView.findViewById(R.id.textview_filter_left);
        mText_filter_right = conView.findViewById(R.id.textview_filter_right);
        mSeekBar = conView.findViewById(R.id.seekbar);
    }

    public void initdata() {
        //sort_pop用到的布局 数据
        mData_sort = new ArrayList<>();
        mData_sort.add("综合排序");
        mData_sort.add("速度最快 ");
        mData_sort.add("评分最高 ");
        mData_sort.add("服务最好 ");
        mAdapterSort = new SortAdapter(mData_sort, activity);
        mListview_pop_sort.setAdapter(mAdapterSort);

        //filter_pop用到的布局和数据
        mData_filter_task = new ArrayList<>();
        mData_filter_task.add("同城帮");
        mData_filter_task.add("维修");
        mData_filter_task.add("家政");
        mData_filter_task.add("互联网");
        mData_filter_task.add("设计");
        mData_filter_task.add("运输");
        mData_filter_task.add("代购");
        mData_filter_task.add("商务");
        mData_filter_task.add("其他");

        mSeekBar.setValue(0,100);//设置初始值
        mText_filter_right.setText("￥  100");
        mAdapter_filter_task = new FilterAdapter(mData_filter_task, activity);
        mGridview_filter_task.setAdapter(mAdapter_filter_task);


        mData_filter_level=new ArrayList<>();
        mData_filter_level.add("一级");
        mData_filter_level.add("二级");
        mData_filter_level.add("三级");
        mData_filter_level.add("不限制");
        mAdapter_filter_level=new FilterAdapter(mData_filter_level,activity);
        mGridview_filter_level.setAdapter(mAdapter_filter_level);

    }


    class SortAdapter extends BaseAdapter {
        List<String> mData;
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
                holder.mTextview = (TextView) convertView.findViewById(R.id.text_sort);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();

            }
            holder.mTextview.setText(mData.get(position));

            return convertView;
        }

        class ViewHolder {
            TextView mTextview;
        }
    }

    class FilterAdapter extends BaseAdapter {
        List<String> mData_grid;
        Context mContext;
        LayoutInflater mInfter;

        public FilterAdapter(List<String> mDatas, Context mContext) {
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
            holder.mTextview.setText(mData_grid.get(position));


            return convertView;
        }
    }

    class ViewHolder {
        TextView mTextview;
    }
}
