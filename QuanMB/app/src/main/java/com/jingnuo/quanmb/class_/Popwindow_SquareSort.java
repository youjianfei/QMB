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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.InterfacePopwindow_square_sort;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.jingnuo.quanmb.data.Staticdata.ScreenWidth;

/**
 * Created by Administrator on 2018/4/2.
 */

public class Popwindow_SquareSort {
    private Activity activity;
    private InterfacePopwindow_square_sort mInterface;
    private RelativeLayout mLinearLayout;
    private ImageView mScrollview;

    List <String> mData;

    SortAdapter madapter;
    MyListView popListview;



    PopupWindow mPopupWindow;

    public Popwindow_SquareSort(Activity activity, InterfacePopwindow_square_sort mInterface, RelativeLayout mLinearLayout) {
        this.activity = activity;
        this.mInterface = mInterface;
        this.mLinearLayout = mLinearLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  void showPopwindow() {

        //初始化popwindow；
        View conView= LayoutInflater.from(activity).inflate(R.layout.popwindow_square_sort_layout,null,false);
        mPopupWindow=new PopupWindow(conView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setAnimationStyle(R.style.popmenu_animation);

//        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        mPopupWindow.showAsDropDown(mLinearLayout,0,0,Gravity.BOTTOM);

        mScrollview=conView.findViewById(R.id.iamgeview_a);
        popListview=conView.findViewById(R.id.list_sort);
        mScrollview.setBackgroundResource(R.color.black);
        mScrollview.setAlpha((float) 0.7);

        mData=new ArrayList<>();
        mData.add("综合排序");
        mData.add("速度最快 ");
        mData.add("评分最高 ");
        mData.add("服务最好 ");

        madapter=new SortAdapter(mData,activity);
        popListview.setAdapter(madapter);


//        Utils.setAlpha((float) 0.3,activity);
//        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
////                Utils.setAlpha((float) 1,activity);
//            }
//        });
        popListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.LOG("ceshi","dianji 半透明"+i,"pop");
            }
        });
        mScrollview.setOnClickListener(new View.OnClickListener() {//为了解决下面半透明问题暂时用的方法
            @Override
            public void onClick(View view) {
                LogUtils.LOG("ceshi","dianji 半透明","pop");
                mPopupWindow.dismiss();
            }
        });
    }


    class  SortAdapter extends BaseAdapter {
        List<String> mData;
        Context mContext;
        LayoutInflater mInflater;

        public SortAdapter(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mContext=mContext;
            this.mData=mDatas;
            mInflater=LayoutInflater.from(mContext);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder  holder=null;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=mInflater.inflate(R.layout.item_listview_pop_square_sort,null,false);
                holder.mTextview= (TextView) convertView.findViewById(R.id.text_sort);
                convertView.setTag(holder);
            }else {
                holder= (ViewHolder) convertView.getTag();

            }
            holder.mTextview.setText(mData.get(position));

            return convertView;
        }
        class ViewHolder {
            TextView mTextview;
        }
    }

}