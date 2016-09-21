package com.example.viewpagerdemo1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<View> mList;
    private MyAdapter mAdapter;
    private View page1,page2,page3;
    private ViewPager mViewPager;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.myViewPager);
        LayoutInflater inflater = getLayoutInflater();
        page1 =inflater.inflate(R.layout.page1,null);
        page2 =inflater.inflate(R.layout.page2,null);
        page3 =inflater.inflate(R.layout.page3,null);
        mList = new ArrayList<View>();
        mList.add(page1);
        mList.add(page2);
        mList.add(page3);

        mAdapter = new MyAdapter(mList);
        mViewPager.setAdapter(mAdapter);
    }
}
