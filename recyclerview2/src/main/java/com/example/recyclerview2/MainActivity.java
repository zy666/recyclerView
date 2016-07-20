package com.example.recyclerview2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<Integer> mDatas;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initData();
        mAdapter = new MyAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initData(){
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.a,R.drawable.b,R.drawable.c,
                R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
        private Context context;
        private List<Integer> mDatas;

        public MyAdapter(Context context, List<Integer> datas) {
            this.context = context;
            this.mDatas = datas;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =  LayoutInflater.from(context).inflate(R.layout.item_list,null);
            MyHolder myHolder = new MyHolder(view);
            myHolder.itemImg = (ImageView) view.findViewById(R.id.iv_logo);
            myHolder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            myHolder.tvContent = (TextView) view.findViewById(R.id.tv_content);

            return myHolder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.itemImg.setImageResource(mDatas.get(position));
        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{

            public MyHolder(View itemView) {
                super(itemView);
            }
            ImageView itemImg;
            TextView tvTitle;
            TextView tvContent;

        }
    }

}
