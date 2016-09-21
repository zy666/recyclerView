package com.example.recyclerviewdemo3;

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
    private List<ImageView> imageViewContainer = null;
    private ViewPager mViewPager;
    private TextView mtext;
    private LinearLayout llDot;
    private BannerAdapter mAdapter;
    /**上一个被选中的小圆点的索引,默认值为0*/
    private int preDotPosition=0;
    /** Banner文字描述数组*/

    private String[] desc = {
            "巩俐不低俗，我就不能低俗",
            "朴树又回来了，再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送", "热血屌丝的反杀" };

    /**Banner滚动线程是否销毁的标志,默认不销毁*/
    private boolean isStop = false;
    private long scrollTimeOffSet = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        startBannerScrollThread();
    }

    @Override
    protected void onDestroy() {
        isStop = true;
        super.onDestroy();
    }

    private void startBannerScrollThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(scrollTimeOffSet);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int newindex = mViewPager.getCurrentItem()+1;
                            mViewPager.setCurrentItem(newindex);
                        }
                    });
                }
            }
        }).start();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mtext = (TextView) findViewById(R.id.tv_banner_text_desc);
        llDot = (LinearLayout) findViewById(R.id.ll_dot_group);
        imageViewContainer = new ArrayList<ImageView>();

        int[] imgIDs = new int[]{
                R.mipmap.a,
                R.mipmap.b,
                R.mipmap.c,
                R.mipmap.d,
                R.mipmap.e,
        };
        ImageView imageView = null;
        View dot = null;
        LinearLayout.LayoutParams params= null;

        for (int id:imgIDs) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(id);
            imageViewContainer.add(imageView);

            //每循环一次添加一个点到线性布局
            dot = new View(this);
            dot.setBackgroundResource(R.drawable.dot_bg_selector);
            params =  new LinearLayout.LayoutParams(5,5);
            params.leftMargin = 10;
            dot.setEnabled(false);
            dot.setLayoutParams(params);
            llDot.addView(dot);//将点添加到线性布局中
        }

        mViewPager.setAdapter(new BannerAdapter());
        mViewPager.addOnPageChangeListener(new BannerPageChangeListener());
        mtext.setText(desc[0]);
        llDot.getChildAt(0).setEnabled(true);
        mViewPager.setCurrentItem(0);
    }
    private class BannerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewContainer.get(position%imageViewContainer.size()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = imageViewContainer.get(position%imageViewContainer.size());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,"page被点击了",Toast.LENGTH_SHORT);
                }
            });
            container.addView(view);
            return view;
        }
    }

    private class BannerPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position % imageViewContainer.size();
            mtext.setText(desc[newPosition]);
            llDot.getChildAt(preDotPosition).setEnabled(false);
            llDot.getChildAt(newPosition).setEnabled(true);
            preDotPosition = newPosition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
