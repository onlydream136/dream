package com.xiao.coordinatorlayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiao.adapter.Myadapter;
import com.xiao.coordinatorlayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${qsh} on 2017/3/23.
 */

public class Fragment1 extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private Myadapter adapter;

    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rlv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new Myadapter(getContext(),loadDatas());
        recyclerView.setAdapter(adapter);
    }


    private List<String> loadDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("模拟数据" + i);
        }
//        adapter.setDatas(datas);
        return datas;
    }

}
