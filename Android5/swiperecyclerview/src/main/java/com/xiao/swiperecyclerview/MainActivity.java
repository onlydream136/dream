package com.xiao.swiperecyclerview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ViewGroup;

import com.xiao.adapter.MyAdapter;
import com.xiao.view.MyItemDecotration;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private SwipeMenuRecyclerView smrv;
    private MyAdapter adapter;
    private SwipeRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        smrv = (SwipeMenuRecyclerView) findViewById(R.id.recycle);
        smrv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this);
        smrv.setAdapter(adapter);

        smrv.setLongPressDragEnabled(true);// 开启长按拖拽
        smrv.setItemViewSwipeEnabled(false);// 开启滑动删除。
//        smrv.addItemDecoration(new ListViewDecoration(this));
        smrv.addItemDecoration(new MyItemDecotration(this));//设置分割线
        smrv.setOnItemMoveListener(new OnItemMoveListener() {
            @Override
            public boolean onItemMove(int fromPosition, int toPosition) {
                adapter.moveItem(fromPosition,toPosition);
                return false;
            }

            @Override
            public void onItemDismiss(int position) {
                adapter.deleteItem(position);
            }
        });// 监听拖拽和侧滑删除，更新UI和数据。


        //下拉
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        srl.setProgressBackgroundColorSchemeColor(Color.parseColor("#ff31d6c2"));//背景色
        srl.setColorSchemeColors(Color.RED); //
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        });


        //开启右滑菜单
        smrv.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        smrv.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                Log.d("print",adapterPosition+";"+menuPosition);
                switch (menuPosition) {
                    case 0:
                        adapter.deleteItem(adapterPosition);
                        smrv.scrollToPosition(adapterPosition);
                        break;
                    case 1:
                        adapter.moveItem(adapterPosition,0);
                }
                closeable.smoothCloseRightMenu();
            }
        });

        //加载数据
        loadDatas();
    }

    private void loadDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("构造数据"+i);
        }
        adapter.setDatas(datas);
    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

            SwipeMenuItem deleteItem = new SwipeMenuItem(MainActivity.this)
                    .setBackgroundDrawable(new ColorDrawable(Color.RED)) //点击背景
                    .setImage(android.R.drawable.ic_delete) // 图标。
                    .setWidth(180)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.


            SwipeMenuItem deleteItem1 = new SwipeMenuItem(MainActivity.this)
                    .setBackgroundDrawable(new ColorDrawable(Color.YELLOW)) //点击背景
                    .setText(R.string.app_zhiding)
                    .setWidth(180)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            swipeRightMenu.addMenuItem(deleteItem1);// 添加一个按钮到右侧侧菜单。.

        }
    };
}
