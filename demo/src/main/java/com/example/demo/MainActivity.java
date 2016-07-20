package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    private GalleryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<Integer> mList;
    private LayoutInflater mInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInflater =  LayoutInflater.from(this);
        initDatas();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new GalleryAdapter(mInflater,mList);
        mAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT)
                        .show();
                Log.d("=====","11111");
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initDatas(){
        mList = new ArrayList<Integer>(Arrays.asList(R.drawable.a,R.drawable.b,R.drawable.c,
                R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g));
    }

    public static class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{

        private LayoutInflater mInflater;
        private List<Integer> mDatas;
        private OnItemClickListener mOnItemClickListener;

        public GalleryAdapter(LayoutInflater mInflater, List<Integer> mDatas) {
            this.mInflater = mInflater;
            this.mDatas = mDatas;
        }

        public interface OnItemClickListener{
            void onItemClick(View view, int position);
        }

        public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
            this.mOnItemClickListener = mOnItemClickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_demo,parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.mImg = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mImg.setImageResource(mDatas.get(position));
            if (mOnItemClickListener !=null){
                holder.mImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick(holder.mImg,position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }
                ImageView mImg;
                TextView mText;
        }
    }
}
