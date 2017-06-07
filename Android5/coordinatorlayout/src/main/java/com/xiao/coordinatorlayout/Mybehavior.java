package com.xiao.coordinatorlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/13.
 */
public class Mybehavior extends CoordinatorLayout.Behavior {

    private final int id;

    public Mybehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.behavior);
        id = typedArray.getResourceId(R.styleable.behavior_viewid,-1);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY()+dependency.getHeight());
        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == id;
    }
}
