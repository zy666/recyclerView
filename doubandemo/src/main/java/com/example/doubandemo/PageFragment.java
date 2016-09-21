package com.example.doubandemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by danny on 16/8/13.
 */
public class PageFragment extends android.support.v4.app.Fragment {
    private TextView tvContext;
    private int pageNum;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_page, container, false);
        tvContext = (TextView) view.findViewById(R.id.tv_content);
        Bundle bundle = getArguments();
        pageNum= bundle.getInt("page_num");
        tvContext.setText(""+pageNum);
        return view;
    }
}
