package com.xiao.coordinatorlayout;


import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationSet;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by ${qsh} on 2017/4/28.
 */

public class TextActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    private List<String> data;
    private ListView listView;
    private MyAdapter myadapter;
    private RelativeLayout textView;
    private AnimationSet animationSet;
    private boolean isFloag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        textView = (RelativeLayout) findViewById(R.id.tv);
        listView = (ListView) findViewById(R.id.listview);
        myadapter = new MyAdapter(this);
        listView.setAdapter(myadapter);
        listView.setOnScrollListener(this);

//        animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.dialog_enter);
//        textView.startAnimation(animationSet);
//        textView.setVisibility(View.VISIBLE);


    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (listView.getFirstVisiblePosition() == 0) {


            //TODO
        }
    }

    int j;

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {


        if (i < j) {
            isFloag = true;
            get();
        } else if (i > j) {
            isFloag = false;
            get();
        } else {
            return;
        }
        j = i;//更新位置
    }

    public void get() {
        int height = textView.getHeight();
        if (!isFloag) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(-height, 0);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int margin = (int) animation.getAnimatedValue();
                    //设置控件的marginbottom
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
                    layoutParams.topMargin = margin;
                    textView.setLayoutParams(layoutParams);
                }
            });
            valueAnimator.setDuration(300);
            valueAnimator.start();

            isFloag = true;
        } else {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, -height);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int marginButtom = (int) valueAnimator.getAnimatedValue();
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
                    layoutParams.topMargin = marginButtom;
                    textView.setLayoutParams(layoutParams);
                }
            });
            valueAnimator.setDuration(300);
            valueAnimator.start();
            isFloag = false;
        }

    }
}

