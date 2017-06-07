package com.xiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiao.swiperecyclerview.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
public class MyAdapter extends SwipeMenuAdapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<String> datas;
    private OnItemClickListener onItemClickListener;



    //设置监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MyAdapter(Context context){
        this.context = context;
        this.datas = new ArrayList<>();
    }

    public void setDatas(List<String> datas){
        this.datas = datas;
        Log.d("print",datas.size()+"");
        this.notifyDataSetChanged();
    }

    public void moveItem(int formPosition,int toPosition){
        String temp = this.datas.remove(formPosition);
        this.datas.add(toPosition,temp);
        this.notifyItemMoved(formPosition,toPosition);
    }

    public void deleteItem(int position){
        this.datas.remove(position);
        this.notifyItemRemoved(position);
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return view;
    }

    @Override
    public MyViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new MyViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView = null;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onItemClickListener != null) {
                onItemClickListener.onClick(view,getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }
}
