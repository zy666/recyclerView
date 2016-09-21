package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends FragmentActivity {
    private ViewPager mViewpager;
    private PagerSlidingTabStrip mTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mViewpager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.pager_sliding);
        mTabStrip.setViewPager(mViewpager);

    }
    private class PageAdapter extends FragmentPagerAdapter{
        String[] title  =  new String[]{"发现","我的","朋友","个人"};
        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment pagerFragment;
            if (position == 0) {
                pagerFragment = new BlankFragment();
            }else {
                pagerFragment = new ItemFragment();
            }
            bundle.putInt("page",position);
            pagerFragment.setArguments(bundle);
            return pagerFragment;
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }




}
