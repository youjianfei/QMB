package com.jingnuo.quanmb.class_;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.quanmb.R;

import java.util.ArrayList;
import java.util.List;

public class Popwindow_lookpic {
    View view;
    PopupWindow mPopupWindow;
    private Activity activity;
    String Url = "";

    //控件
//    ImageView imageView_lookpic;


    public Popwindow_lookpic(Activity activity) {
        this.activity = activity;
    }

    public void showPopwindow(int position,List<String> imageList) {
        view = LayoutInflater.from(activity).inflate(R.layout.popwindow_lookpic, null, false);

        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.pop_lookpic_background));//// 设置背景图片，不能在布局中设置，要通过代码来设置
        mPopupWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style); // 设置动画
        mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);//定位pop位置
        mViewpager= (ViewPager) view.findViewById(R.id.viewpager);
        mLinerlayout_dot_group= (LinearLayout) view.findViewById(R.id.dot_group);
        initview(position,imageList);
    }

    private void setview(String picUrl) {
//        Glide.with(activity).load(picUrl).into(imageView_lookpic);
    }

    private void initview(int position,List<String> imageList) {
//        imageView_lookpic = view.findViewById(R.id.image_pic);
        View dotview;
        listdate_viewpager=new ArrayList<>();
        listdate_viewpager.clear();
        for(int i=0;i<imageList.size();i++){
            iv=new ImageView(activity);
            Glide.with(activity).load(imageList.get(i)).into(iv);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
            listdate_viewpager.add(iv);
            //准备小圆点的数据
            dotview=new View(activity);
            dotview.setBackgroundResource(R.drawable.dot_selector);
            //设置小圆点的宽和高
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(10,10);
            if(i!=0){
                params.leftMargin=10;//设置小白点间距
            }
            dotview.setLayoutParams(params);
            //设置小圆点的状态
            if(i==position){
                dotview.setEnabled(true);//第一个小白点初始化
            }else{
                dotview.setEnabled(false);
            }
            //把小圆点添加到线性布局中
            mLinerlayout_dot_group.addView(dotview);

        }
        mViewpagerAdapter=new viewpagerAdapter();
        mViewpager.setAdapter( mViewpagerAdapter);
        mViewpager.setCurrentItem(position);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //当页面选中的时候调用
            @Override
            public void onPageSelected(int position) {
                mLinerlayout_dot_group.getChildAt(position).setEnabled(true);//设置小点为白色
                mLinerlayout_dot_group.getChildAt(lastposition).setEnabled(false);//把上一个小圆点设为false
                lastposition=position;//记录上一个白点的位置以便滑动后设为黑色
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private ViewPager mViewpager;
    private List<View> listdate_viewpager;
    private ImageView iv;
    private LinearLayout mLinerlayout_dot_group;
    private viewpagerAdapter mViewpagerAdapter;
    private int  lastposition=0;
    /**
     * 定义ViewpagerAdapter
     */
    class viewpagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listdate_viewpager.size();
        }
        //是否复用当前view对象
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        //重写每一个条目要显示的内容
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View iv=listdate_viewpager.get(position);
            container.addView(iv);
            return iv;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //移除条目
            container.removeView((View) object);
        }
    }

}
