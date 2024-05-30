package com.example.eazilydone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RouteMapView extends View {
    private Paint roadPaint;
    private Paint buildingPaint;
    private Paint treePaint;
    private Paint playerPaint;
    private Path roadPath;
    private float playerX = 100;
    private float playerY = 100;

    public RouteMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        roadPaint = new Paint();
        roadPaint.setColor(Color.GRAY);
        roadPaint.setStyle(Paint.Style.STROKE);
        roadPaint.setStrokeWidth(10);

        buildingPaint = new Paint();
        buildingPaint.setColor(Color.DKGRAY);
        buildingPaint.setStyle(Paint.Style.FILL);

        treePaint = new Paint();
        treePaint.setColor(Color.GREEN);
        treePaint.setStyle(Paint.Style.FILL);

        playerPaint = new Paint();
        playerPaint.setColor(Color.BLUE);
        playerPaint.setStyle(Paint.Style.FILL);

        roadPath = new Path();
        roadPath.moveTo(100, 100);
        roadPath.lineTo(300, 100);
        roadPath.lineTo(300, 300);
        roadPath.lineTo(500, 300);
        roadPath.lineTo(500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the road
        canvas.drawPath(roadPath, roadPaint);

        // Draw buildings
        canvas.drawRect(50, 50, 150, 150, buildingPaint);
        canvas.drawRect(400, 400, 550, 550, buildingPaint);

        // Draw trees
        canvas.drawCircle(200, 200, 30, treePaint);
        canvas.drawCircle(600, 600, 30, treePaint);

        // Draw the player
        canvas.drawCircle(playerX, playerY, 20, playerPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            if (isOnRoad(touchX, touchY)) {
                playerX = touchX;
                playerY = touchY;
                invalidate(); // Redraw the view
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private boolean isOnRoad(float x, float y) {
        // Simple check for demonstration purposes
        return roadPath.contains(x, y);
    }
}
