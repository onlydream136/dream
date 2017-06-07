package com.xiao.recyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xiao.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView re;
    private MyAdapter adapter;

    //下拉刷新
    private SwipeRefreshLayout srl;

    //navigation
    private NavigationView navigation;
    private DrawerLayout draw;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        re = (RecyclerView) findViewById(R.id.re);
        adapter = new MyAdapter(this);
        re.setAdapter(adapter);
        re.setLayoutManager(new LinearLayoutManager(this));
//        re.setLayoutManager(new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false));
//        re.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        adapter.setOnItemClickListener(this);


        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        srl.setProgressBackgroundColorSchemeColor(Color.RED);
        srl.setColorSchemeColors(Color.YELLOW);
        srl.setOnRefreshListener(this);


        navigation = (NavigationView)findViewById(R.id.navigation);

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        draw = (DrawerLayout) findViewById(R.id.drawer);
//        draw.addDrawerListener(new ActionBarDrawerToggle());

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                draw,
                toolbar,
                R.string.app_name,
                R.string.app_name
        );
        draw.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();//同步状态

        loadDatas();
    }

    private void loadDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("模拟数据"+i);
        }
        adapter.setDatas(datas);
    }

    @Override
    public void onClick(View view, int position) {
        Log.d("print", "onClick: "+position);
    }


    /**
     * swiperefreshlayout监听事件
     */
    @Override
    public void onRefresh() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                    }
                });
            }

        }).start();

    }
}
