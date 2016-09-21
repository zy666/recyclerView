package com.example.viewpagerdemo3;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private List<ImageView> mImageViewContainer = null;
    private TextView mDesc ;
    private LinearLayout llDot;
    private ViewPager mViewpager;
    private int preDot = 0;
    private boolean isStop = false;
    private long scrollTimeOffset = 5000;

    private String[] bannerTextDescArray = {
            "巩俐不低俗，我就不能低俗",
            "朴树又回来了，再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送", "热血屌丝的反杀"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        newTask();
    }

    private void newTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (!isStop) {
                    SystemClock.sleep(scrollTimeOffset);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int newindex = mViewpager.getCurrentItem() +1;
                            mViewpager.setCurrentItem(newindex);
                        }
                    });
                }
            }
        }).start();

    }

    private void initView() {
        mDesc = (TextView) findViewById(R.id.tv_desc);
        llDot = (LinearLayout) findViewById(R.id.ll_dot);
        mViewpager= (ViewPager) findViewById(R.id.viewpager3);
        mImageViewContainer  =new ArrayList<ImageView>();
        int[] imageResId = new int[]{R.drawable.main_img1,R.drawable.main_img2,R.drawable.main_img3,R.drawable.main_img4};
        ImageView imageView = null;
        View dot = null;
        LinearLayout.LayoutParams params= null;
        for (int id:imageResId) {
            imageView  = new ImageView(this);
            imageView.setBackgroundResource(id);
            mImageViewContainer.add(imageView);

            dot = new View(this);
            dot.setBackgroundResource(R.drawable.dot_bg_selector);
            params = new LinearLayout.LayoutParams(5,5);
            params.leftMargin = 10;
            dot.setEnabled(false);
            dot.setLayoutParams(params);
            llDot.addView(dot);
        }
        mViewpager.setAdapter(new BannerAdapter(mImageViewContainer));
        mViewpager.addOnPageChangeListener(new BannerPageChangeListener());
        mDesc.setText(bannerTextDescArray[0]);
        llDot.getChildAt(0).setEnabled(true);
        mViewpager.setCurrentItem(0);
    }

    private class BannerAdapter extends PagerAdapter{
        private List<ImageView> mImageViewList;

        public BannerAdapter(List<ImageView> mImageViewList) {
            this.mImageViewList = mImageViewList;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mImageViewList.get(position % mImageViewList.size());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Page 被点击了", Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViewList.get(position % mImageViewList.size()));
        }
    }

    private class BannerPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position %  mImageViewContainer.size();
            mDesc.setText(bannerTextDescArray[newPosition]);
            llDot.getChildAt(0).setEnabled(false);
            llDot.getChildAt(newPosition).setEnabled(true);
            preDot = newPosition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
