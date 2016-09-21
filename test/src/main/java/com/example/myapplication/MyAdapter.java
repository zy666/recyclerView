package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by danny on 16/8/20.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mContext;
    private List<ItemInfo> itemInfos;
    private LayoutInflater mInflater;

    public MyAdapter(List<ItemInfo> itemInfos, Context mContext) {
        this.itemInfos = itemInfos;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_list,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(itemInfos.get(position).getTitle());
        holder.context.setText(itemInfos.get(position).getContext());
    }

    @Override
    public int getItemCount() {
        return itemInfos.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView title;
    TextView context;
    public MyViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.tv_title);
        context = (TextView) itemView.findViewById(R.id.tv_content);
    }
}