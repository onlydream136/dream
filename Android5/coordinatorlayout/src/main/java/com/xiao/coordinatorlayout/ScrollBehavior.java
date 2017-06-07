package com.xiao.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior {

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0) {
            float y = child.getY();
            y = y - dyConsumed;
            if (Math.abs(y) > child.getHeight()) {
                y = - child.getHeight();
            }
            child.setY(y);
        } else if (dyConsumed < 0) {
            float y = child.getY();
            y = y - dyConsumed;
            if (y > 0) {
                y = 0;
            }
            child.setY(y);
        }
    }
}
