package com.xiao.coordinatorlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/14.
 */
public class FabBehavior extends CoordinatorLayout.Behavior {
    private boolean isRun;

    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, final View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (!isRun) {
            if (dyConsumed > 0)
            child.animate()
                    .scaleX(0)
                    .scaleY(0)
                    .alpha(0.2f)
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            child.setVisibility(View.GONE);
                            isRun = false;
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            isRun = true;
                        }
                    })
                    .start();
            else if (dyConsumed < 0) {
                child.animate()
                        .scaleXBy(0)
                        .scaleX(1)
                        .scaleYBy(0)
                        .scaleY(1)
                        .alphaBy(0.2f)
                        .alpha(1f)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isRun = false;
                            }

                            @Override
                            public void onAnimationStart(Animator animation) {
                                child.setVisibility(View.VISIBLE);
                                isRun = true;
                            }
                        })
                        .start();
            }
        }
    }
}
