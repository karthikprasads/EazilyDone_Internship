//package com.example.obstacledetection;
//
//import android.graphics.Color;
//import android.graphics.Rect;
//import android.util.AttributeSet;
//import android.view.View;
//import android.content.Context;
//
//public class Obstacle extends View {
//    Rect t;
//
//    public Obstacle(Context context) {
//        super(context);
//        t = new Rect();
//        setRect();
//        setBackgroundColor(Color.RED);
//    }
//
//    public Obstacle(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        t = new Rect();
//        setRect();
//        setBackgroundColor(Color.RED);
//    }
//
//    public Obstacle(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        t = new Rect();
//        setRect();
//        setBackgroundColor(Color.RED);
//    }
//
//    public void setRect() {
//        int[] l = new int[2];
//        getLocationOnScreen(l);
//        t.set(l[0], l[1], l[0] + getWidth(), l[1] + getHeight());
//    }
//
//    public Rect getRect() {
//        return t;
//    }
//
//    public boolean intersects(Rect otherRect) {
//        return Rect.intersects(t, otherRect);
//    }
//}

package com.example.eazilydone;

import static android.content.ContentValues.TAG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.content.Context;

public class Obstacle extends View {
    Rect t;
    Paint paint;
    Paint borderPaint; // New Paint object for the border

    public Obstacle(Context context) {
        super(context);
        init();
    }

    public Obstacle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Obstacle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        t = new Rect();
        setRect();
        setBackgroundColor(Color.GRAY);

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);

        borderPaint = new Paint(); // Initialize the border Paint object
        borderPaint.setColor(Color.RED); // Set its color to black
        borderPaint.setStyle(Paint.Style.STROKE); // Set its style to STROKE
        borderPaint.setStrokeWidth(5); // Set its stroke width
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setRect();
    }

    public void setRect() {
        int[] l = new int[2];
        getLocationOnScreen(l);
        t.set(l[0], l[1], l[0] + getWidth(), l[1] + getHeight());
        Log.d(TAG, "setRect: " + t.left + " " + t.top + " " + t.right + " " + t.bottom);
    }

    public Rect getRect() {
        return t;
    }

    public boolean intersects(Rect otherRect) {
        return Rect.intersects(t, otherRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Create a new Rect that goes from (0,0) to (width, height) of the view
        Rect localRect = new Rect(0, 0, getWidth(), getHeight());
        // Draw the border using the border Paint object
        // Offset the border by half the stroke width to make it visible
        canvas.drawRect(localRect.left + borderPaint.getStrokeWidth() / 2, localRect.top + borderPaint.getStrokeWidth() / 2, localRect.right - borderPaint.getStrokeWidth() / 2, localRect.bottom - borderPaint.getStrokeWidth() / 2, borderPaint);
    }
}