package com.example.viewpagerdemo1;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by danny on 16/8/2.
 */
public class MyAdapter extends PagerAdapter {

    private List<View> pagelist;


    public MyAdapter(List<View> pagelist) {
        this.pagelist = pagelist;
    }

    @Override
    public int getCount() {
        return pagelist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagelist.get(position));
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        //每次滑动的时候把视图添加到
        container.addView(pagelist.get(position));
        return pagelist.get(position); }
}
