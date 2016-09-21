package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mFresh;
    private RecyclerView mRecycler;
    private List<String> mList;
    private RecyclerView.LayoutManager manager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_list);
        mFresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fresh);
        mFresh.setOnRefreshListener(this);
        mFresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(""+i);
        }
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        mRecycler.setAdapter(new MyAdapter(mList));


    }

    @Override
    public void onRefresh() {
        mFresh.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFresh.setRefreshing(false);
            }
        }, 3000);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        private List<String> mData;

        public MyAdapter(List<String> mData) {
            this.mData = mData;
        }
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
            MyHolder holder = new MyHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tvShow.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }


    private class MyHolder extends RecyclerView.ViewHolder {
        TextView tvShow;

        public MyHolder(View itemView) {
            super(itemView);
            tvShow = (TextView) itemView.findViewById(R.id.tv_show);
        }
    }
}
