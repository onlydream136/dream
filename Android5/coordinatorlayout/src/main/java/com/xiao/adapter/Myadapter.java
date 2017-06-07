package com.xiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiao.coordinatorlayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    private Context context;
    private List<String> datas;
    private OnItemRecycleClickListener onItemRecycleClickListener;
    private onItemRecycleLongClickListener onItemRecycleLongClickListener;


    public void setOnItemRecycleLongClickListener(Myadapter.onItemRecycleLongClickListener onItemRecycleLongClickListener) {
        this.onItemRecycleLongClickListener = onItemRecycleLongClickListener;
    }

    public void setOnItemRecycleClickListener(OnItemRecycleClickListener onItemRecycleClickListener) {
        this.onItemRecycleClickListener = onItemRecycleClickListener;
    }

    public Myadapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
    }

    public Myadapter(Context context,List<String> datas){
        this.datas = new ArrayList<>();
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
        Log.d("print",datas.size()+"123");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            textView.setOnClickListener(this);
            textView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemRecycleClickListener != null) {
                onItemRecycleClickListener.onClick(view,getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (onItemRecycleLongClickListener != null){
                onItemRecycleLongClickListener.onLongClidk(view,getAdapterPosition());
                return true;
            } else {
                return false;
            }
        }
    }

    public interface OnItemRecycleClickListener{
        void onClick(View view,int position);
    }

    public interface onItemRecycleLongClickListener{
        void onLongClidk(View view,int position);
    }
}
