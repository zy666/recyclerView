package com.example.viewpager_0;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends Activity {
    private ViewPager mViewPager;
    private MyAdapter mAdapter;
    private List<ImageView> mList;
    private ImageView mImageView;
    private int[] img = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(this).inflate(R.layout.image_item,null);
        mImageView = (ImageView) view.findViewById(R.id.iv_img);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_0);
        for (int id:img) {
            mImageView.setImageResource(id);
            mList.add(mImageView);
        }
        mAdapter = new MyAdapter(mList);
        mViewPager.setAdapter(mAdapter);
    }
}
