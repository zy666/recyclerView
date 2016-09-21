package com.example.doubandemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danny on 16/8/12.
 */
public class ListFreshFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ListView mlistView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_refresh, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mlistView = (ListView) view.findViewById(R.id.lvl);
        mlistView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getData()));
        return view;
    }


    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    public List<String> getData() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            data.add(i+"");
        }
        return data;
    }
}
