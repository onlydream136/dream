package com.xiao.android5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ${qsh} on 2017/7/25.
 */

public class MyTestView extends View {
    private Paint paint;
    private Canvas canvas;
    private float radius = 0;
    private float x;
    private float y;
    private int width;
    private int height;
    private boolean isflog;


    public MyTestView(Context context) {
        super(context);
        init();
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#00cc00"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas = new Canvas();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                radius = 0;
                isflog = true;
//                postInvalidate();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
//                x = event.getX();
//                y = event.getY();
//                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                isflog = false;
//                postInvalidate();
                invalidate();
                break;
        }
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if ((x-radius>0)&&(y-radius>0)&&(x+radius<width)&&(y+radius<height)&&isflog){
            radius = radius + 10;
            postInvalidate();
        }else if (!isflog){
            radius = 0;
            canvas.drawCircle(x,y,radius,paint);
        }
        canvas.drawCircle(x,y,radius,paint);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public void clear()
    {
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        invalidate();
    }
}
