package com.example.viewpagerdemo2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    private LayoutInflater mInflater;
    private ViewPager mViewPager;
    private List<ImageView> dotViewlist;
    private List<ImageView> list;
    LinearLayout dotLayout;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem=0;//当前页面
    boolean isAutoPlay = true;//是否自动轮播

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                mViewPager.setCurrentItem(currentItem);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInflater = LayoutInflater.from(MainActivity.this);
        mViewPager = (ViewPager) findViewById(R.id.myviewPager);
        dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        initView();
        if (isAutoPlay) {
            startPlay();
        }
    }



    private void initView() {
        dotViewlist = new ArrayList<ImageView>();
        list = new ArrayList<ImageView>();
        for (int i = 0; i < 4; i++) {
            ImageView dotView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            android.view.ViewGroup.LayoutParams.MATCH_PARENT));
            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            params.height = 40;//设置小圆点的大小
            params.width = 40;
            if (i == 0) {
                dotView.setImageResource(R.drawable.point_pressed);
            }else{
                dotView.setImageResource(R.drawable.point_unpressed);
            }
            dotLayout.addView(dotView,params);
            dotViewlist.add(dotView);
            //上边是动态添加四个圆点
        }

        ImageView img1 = (ImageView) mInflater.inflate(R.layout.scroll_view_item,null);
        ImageView img2 = (ImageView) mInflater.inflate(R.layout.scroll_view_item,null);
        ImageView img3 = (ImageView) mInflater.inflate(R.layout.scroll_view_item,null);
        ImageView img4 = (ImageView) mInflater.inflate(R.layout.scroll_view_item, null);

        img1.setBackgroundResource(R.drawable.main_img1);
        img2.setBackgroundResource(R.drawable.main_img2);
        img3.setBackgroundResource(R.drawable.main_img3);
        img4.setBackgroundResource(R.drawable.main_img4);

        list.add(img1);
        list.add(img2);
        list.add(img3);
        list.add(img4);
        ImagePagerAdapter adapter = new ImagePagerAdapter((ArrayList)list);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new MyPagerListener());
    }
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(),1,4, TimeUnit.SECONDS);

    }

    public class SlideShowTask implements Runnable{

        @Override
        public void run() {
            synchronized(mViewPager){
                currentItem = (currentItem + 1) % list.size();
                mhandler.sendEmptyMessage(100);
            }
        }
    }
    private class MyPagerListener implements ViewPager.OnPageChangeListener{
        boolean isAutoPlay = false;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            for (int i = 0; i < dotViewlist.size(); i++) {
                if (i == position) {
                    ((View)dotViewlist.get(position)).setBackgroundResource(R.drawable.point_pressed);
                }else {
                    ((View)dotViewlist.get(position)).setBackgroundResource(R.drawable.point_unpressed);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        mViewPager.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (mViewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }

        }
    }

}
