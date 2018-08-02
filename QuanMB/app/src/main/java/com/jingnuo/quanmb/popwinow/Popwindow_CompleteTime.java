package com.jingnuo.quanmb.popwinow;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.Interence_complteTask_time;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.List;

public class Popwindow_CompleteTime {

    View view;
    Activity activity;
    PopupWindow mPopupWindow;

    //控件
    ImageView mImageview;
    ListView mListview;

    //数据

    List<String> mdata;

    //对象
    Adapter_compltetime adapter_compltetime;
    Interence_complteTask_time interence_complteTask_time;



    public Popwindow_CompleteTime(Activity activity,Interence_complteTask_time interence_complteTask_time) {
        this.activity = activity;
        this.interence_complteTask_time=interence_complteTask_time;
    }
    public void  showPopwindow(){
        view= LayoutInflater.from(activity).inflate(R.layout.popwindow_compltetime,null,false);
        mPopupWindow=new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setFocusable(true);//设置焦点在window上
        mPopupWindow.setAnimationStyle(R.style.popskilltype_animation);
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        initview();
        initdata();
        initlistenner();
        Utils.setAlpha((float) 0.3,activity);
    }

    private void initlistenner() {
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.setAlpha((float) 1,activity);

            }
        });
        mImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                interence_complteTask_time.onResult(mdata.get(i),i+1);
                mPopupWindow.dismiss();

            }
        });
    }

    private void initdata() {
        mdata=new ArrayList<>();
        mdata.add("3小时");
        mdata.add("一天");
        mdata.add("三天");
        mdata.add("七天");
        mdata.add("十五天");
        mdata.add("三十天");
        adapter_compltetime=new Adapter_compltetime(mdata,activity);
        mListview.setAdapter(adapter_compltetime);
    }

    private void initview() {
        mImageview=view.findViewById(R.id.ImageView_close);
        mListview=view.findViewById(R.id.Listview_addresspopwindow);
    }

    class Adapter_compltetime extends BaseAdapter{

        List<String> mdata;
        Context mContext;
        LayoutInflater inflater;

        public Adapter_compltetime(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mContext=mContext;
            this.mdata=mDatas;
            inflater=LayoutInflater.from(mContext);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=inflater.inflate(R.layout.item_text,null,false);
                holder.mTextview=convertView.findViewById(R.id.text_text);
                convertView.setTag(holder);
            }else {
                holder= (ViewHolder) convertView.getTag();
            }
            holder.mTextview.setText(mdata.get(position));

            return convertView;
        }
        class  ViewHolder {
            TextView mTextview;
        }
    }
}
