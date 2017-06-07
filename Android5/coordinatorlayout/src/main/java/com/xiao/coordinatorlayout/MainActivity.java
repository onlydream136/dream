package com.xiao.coordinatorlayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.xiao.adapter.Myadapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rc;
    private Myadapter adapter;
    //actionbar标题栏
    private Toolbar toolBar;
    //下拉
    private SwipeRefreshLayout srl;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = (RecyclerView) findViewById(R.id.rc);
        rc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new Myadapter(this);
        rc.setAdapter(adapter);

        //点击事件
        adapter.setOnItemRecycleClickListener(new Myadapter.OnItemRecycleClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("print",position+"abc");
            }
        });

        //长按事件
        adapter.setOnItemRecycleLongClickListener(new Myadapter.onItemRecycleLongClickListener() {
            @Override
            public void onLongClidk(View view, int position) {
                Log.d("print","长按了"+position);
            }
        });
        rc.setLayoutManager(new LinearLayoutManager(this));


        toolBar = (Toolbar) findViewById(R.id.tools);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //下拉
//        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
//        srl.setProgressBackgroundColorSchemeColor(Color.parseColor("#ff31d6c2"));//背景色
//        srl.setColorSchemeColors(Color.RED); //
//        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                srl.setRefreshing(false);
//                            }
//                        });
//                    }
//                }).start();
//            }
//        });

        loadDatas();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void btnclick(View v) {
//        Snackbar.make(v, "一个普通的通知", Snackbar.LENGTH_SHORT).show();

//        //点击view1
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) v.getLayoutParams();
//        layoutParams.topMargin += 10;
//        v.setLayoutParams(layoutParams);
        startActivity(new Intent(MainActivity.this, FragmentActivity.class));

    }

    private void loadDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("模拟数据" + i);
        }
        adapter.setDatas(datas);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.xiao.coordinatorlayout/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.xiao.coordinatorlayout/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
