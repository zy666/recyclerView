package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends FragmentActivity {
    private ViewPager mPager;
    private PagerSlidingTabStrip mSlidingTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mSlidingTab = (PagerSlidingTabStrip) findViewById(R.id.sliding_tab);
        mPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mSlidingTab.setViewPager(mPager);
    }

    private class MyAdapter extends FragmentPagerAdapter{
        private String[] title = new String[]{"刷新页","滑动页","滑动页","滑动页"};
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment fragment;
            if (position == 0) {
                fragment = new SwipeFragment();
            }else {
                fragment = new BlankFragment();
            }
            bundle.putInt("page_num",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }


}
