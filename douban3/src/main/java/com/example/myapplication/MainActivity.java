package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends FragmentActivity{
    private PagerSlidingTabStrip mPagerSliding;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPagerSliding = (PagerSlidingTabStrip) findViewById(R.id.pagersliding3);
        mViewPager = (ViewPager) findViewById(R.id.viewpager3);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mPagerSliding.setViewPager(mViewPager);
    }

    public class MyAdapter extends FragmentPagerAdapter{
        private String[] titles = new String[]{"个性推荐","歌单","主播","排行榜"};
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if (position == 0) {
                fragment =new MainFragment();
            }else {
                fragment = new ContentFragment();
            }
            Bundle bundle = new Bundle();
            bundle.putInt("page",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
