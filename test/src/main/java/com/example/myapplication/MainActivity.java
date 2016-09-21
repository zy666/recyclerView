package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRecycler;
    private List<ItemInfo> mInfo;
    private MyAdapter myAdapter;
    private TextView mTilte,mContext;
    private  ItemInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTilte = (TextView) findViewById(R.id.tv_title);
        mContext = (TextView) findViewById(R.id.tv_content);
        initData();
        mRecycler = (RecyclerView) findViewById(R.id.test_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(new MyAdapter(mInfo,this));
    }



    private void initData() {

            ItemInfo it = new ItemInfo();
        for (int i = 0; i < 10; i++) {
            it.setTitle("我是标题"+i);
            it.setContext("我是内容"+i);
            mInfo.add(it);
        }
    }
}
