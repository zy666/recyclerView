package com.example.doubandemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends FragmentActivity {
    private PagerSlidingTabStrip pageTab;
    private ViewPager mPager;
    private final int  PAGE_NUM = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        pageTab = (PagerSlidingTabStrip) findViewById(R.id.pager_tabs);
        mPager = (ViewPager) findViewById(R.id.lv_content);
        mPager.setOffscreenPageLimit(3);
        mPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pageTab.setViewPager(mPager);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() ==0) {
            super.onBackPressed();
        }else {
            mPager.setCurrentItem(0);
        }
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private final String[] TITLES = getResources().getStringArray(R.array.page_title);
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment pagefragment;
            if (position == 0) {
                pagefragment =new ListFreshFragment();
            }else {
                pagefragment = new PageFragment();
            }
            bundle.putInt("page_num",position);
            pagefragment.setArguments(bundle);
            return pagefragment;
        }

        @Override
        public int getCount() {
            return PAGE_NUM;
        }
    }
}
