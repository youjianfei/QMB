package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.R;
import java.util.ArrayList;
import java.util.List;

public class FirstLoginActivity extends BaseActivityother {

    private TextView mTextview;

    private ViewPager mViewpager;
    private List<View> listdate_viewpager;
    private List<Integer> image;
    private viewpagerAdapter mViewpagerAdapter;
    //    private LinearLayout mLinerlayout_dot_group;
    private int lastposition = 0;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_first_login;
    }

    @Override
    protected void setData() {
        mViewpagerAdapter = new viewpagerAdapter();
        mViewpager.setAdapter(mViewpagerAdapter);
    }

    @Override
    protected void initData() {
        SharedPreferencesUtils.putBoolean(this, "QMB", "isfirstlogin", false);
        image = new ArrayList<>();
        image.add(R.mipmap.a1);
        image.add(R.mipmap.a2);
        image.add(R.mipmap.a3);
        listdate_viewpager = new ArrayList<>();
        View dotview;
        for (int i = 0; i < 3; i++) {//动态添加布局图片界面
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setBackgroundResource(R.mipmap.a4);//viewpager背景图
            RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);//设置背景图片长宽
            linearLayout.setGravity(Gravity.CENTER);//设置对齐方式，Gravity.CENTER仅限于LinearLayout
            linearLayout.setLayoutParams(mLayoutParams);
            ImageView imageview = new ImageView(this);//imageview
            imageview.setBackgroundResource(image.get(i));
            imageview.setScaleType(ImageView.ScaleType.CENTER);
            if (i == 2) {
                imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FirstLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }
            RelativeLayout.LayoutParams mImageParams = new RelativeLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth*1.75));//设置图片比例
            imageview.setLayoutParams(mImageParams);

            linearLayout.addView(imageview);
            listdate_viewpager.add(linearLayout);//添加到list
            //准备小圆点的数据
            dotview = new View(this);
            dotview.setBackgroundResource(R.drawable.dot_selector);
            //设置小圆点的宽和高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            if (i != 0) {
                params.leftMargin = 15;//设置小白点间距
            }
            dotview.setLayoutParams(params);
            //设置小圆点的状态
            if (i == 0) {
                dotview.setEnabled(true);//第一个小白点初始化
            } else {
                dotview.setEnabled(false);
            }
            //把小圆点添加到线性布局中
//            mLinerlayout_dot_group.addView(dotview);
        }
    }

    @Override
    protected void initListener() {
        mTextview.setOnClickListener(this);

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //当页面选中的时候调用
            @Override
            public void onPageSelected(int position) {
//                mLinerlayout_dot_group.getChildAt(position).setEnabled(true);//设置小点为白色
//                mLinerlayout_dot_group.getChildAt(lastposition).setEnabled(false);//把上一个小圆点设为false
                lastposition = position;//记录上一个白点的位置以便滑动后设为黑色
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mTextview = (TextView) findViewById(R.id.textview_skip);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textview_skip:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

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
            return view == object;
        }

        //重写每一个条目要显示的内容
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View iv = listdate_viewpager.get(position);
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
