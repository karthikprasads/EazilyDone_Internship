package com.example.eazilydone;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;
    private float scaleFactor = 3.0f; // Initial zoom level
    private float maxScaleFactor = 4.0f;
    private float minScaleFactor = 1.0f;
    private float lastFocusX;
    private float lastFocusY;
    private float offsetX = 0f;
    private float offsetY = 0f;
    private static final float SWIPE_SPEED_MULTIPLIER = 5.0f; // Adjust as needed

    private ImageView personImage;
    private ConstraintLayout secondLayout;
    private static final int MOVE_STEP = 20; // Number of pixels to move
    private List<Obstacle> obstacles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        gestureDetector = new GestureDetector(this, new SwipeListener());

        Button bankButton = findViewById(R.id.Bankbutton);
        Button tournamentButton = findViewById(R.id.TournamentButton);
        bankButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, DiscussionRoom.class);
            startActivity(intent);
        });

        tournamentButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, TournamentActivity.class);
            startActivity(intent);
        });

        personImage = findViewById(R.id.person);
        secondLayout = findViewById(R.id.secondLayout);

        // Set initial zoom
        secondLayout.setScaleX(scaleFactor);
        secondLayout.setScaleY(scaleFactor);

        findViewById(R.id.arrow_up).setOnTouchListener(new MoveTouchListener(0, -MOVE_STEP));
        findViewById(R.id.arrow_down).setOnTouchListener(new MoveTouchListener(0, MOVE_STEP));
        findViewById(R.id.arrow_left).setOnTouchListener(new MoveTouchListener(-MOVE_STEP, 0));
        findViewById(R.id.arrow_right).setOnTouchListener(new MoveTouchListener(MOVE_STEP, 0));

        obstacles = new ArrayList<>();
        for (int i = 0; i < secondLayout.getChildCount(); i++) {
            View child = secondLayout.getChildAt(i);
            if (child instanceof Obstacle) {
                obstacles.add((Obstacle) child);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            lastFocusX = detector.getFocusX();
            lastFocusY = detector.getFocusY();
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactorDelta = detector.getScaleFactor();
            scaleFactor *= scaleFactorDelta;
            scaleFactor = Math.max(minScaleFactor, Math.min(scaleFactor, maxScaleFactor));

            float focusX = detector.getFocusX();
            float focusY = detector.getFocusY();

            float dx = focusX - lastFocusX;
            float dy = focusY - lastFocusY;

            offsetX -= dx * (scaleFactorDelta - 1);
            offsetY -= dy * (scaleFactorDelta - 1);

            lastFocusX = focusX;
            lastFocusY = focusY;

            secondLayout.setScaleX(scaleFactor);
            secondLayout.setScaleY(scaleFactor);
            secondLayout.setTranslationX(offsetX);
            secondLayout.setTranslationY(offsetY);

            return true;
        }
    }

    private class SwipeListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        offsetX = clampTranslation(offsetX + SWIPE_SPEED_MULTIPLIER * 100 / scaleFactor, secondLayout.getWidth(), scaleFactor);
                    } else {
                        offsetX = clampTranslation(offsetX - SWIPE_SPEED_MULTIPLIER * 100 / scaleFactor, secondLayout.getWidth(), scaleFactor);
                    }
                    secondLayout.setTranslationX(offsetX);
                }
            } else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        offsetY = clampTranslation(offsetY + SWIPE_SPEED_MULTIPLIER * 100 / scaleFactor, secondLayout.getHeight(), scaleFactor);
                    } else {
                        offsetY = clampTranslation(offsetY - SWIPE_SPEED_MULTIPLIER * 100 / scaleFactor, secondLayout.getHeight(), scaleFactor);
                    }
                    secondLayout.setTranslationY(offsetY);
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    private float clampTranslation(float translation, int dimensionSize, float scale) {
        float maxTranslation = (dimensionSize * (scale - 1)) / 2;
        return Math.max(-maxTranslation, Math.min(translation, maxTranslation));
    }

    private class MoveTouchListener implements View.OnTouchListener {
        private final int dx;
        private final int dy;
        private final Handler handler = new Handler();
        private Runnable runnable;

        public MoveTouchListener(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!isCollisionInDirection(dx, dy)) {
                            movePlayer(dx, dy);
                        }
                        handler.postDelayed(this, 100); // adjust the delay as needed
                    }
                };
                handler.post(runnable);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
            }
            return true;
        }
    }

    private boolean isCollisionInDirection(int dx, int dy) {
        Rect personRect = new Rect();
        personImage.getHitRect(personRect);

        // Calculate the new position if the player moves in the given direction
        personRect.offset(dx, dy);

        for (Obstacle obstacle : obstacles) {
            Rect obstacleRect = new Rect();
            obstacle.getHitRect(obstacleRect);
            if (Rect.intersects(obstacleRect, personRect)) {
                return true;
            }
        }
        return false;
    }



//    private void movePlayer(int dx, int dy) {
//        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) personImage.getLayoutParams();
//        params.horizontalBias += (float) dx / secondLayout.getWidth();
//        params.verticalBias += (float) dy / secondLayout.getHeight();
//        personImage.setLayoutParams(params);
//
//        // Update the translation of the map to move in the opposite direction of the character
//        float newTranslationX = secondLayout.getTranslationX() - dx;
//        float newTranslationY = secondLayout.getTranslationY() - dy;
//
//        // Clamp the translations to ensure the map does not move out of bounds
//        newTranslationX = clampTranslation(newTranslationX, secondLayout.getWidth(), scaleFactor);
//        newTranslationY = clampTranslation(newTranslationY, secondLayout.getHeight(), scaleFactor);
//
//        secondLayout.setTranslationX(newTranslationX);
//        secondLayout.setTranslationY(newTranslationY);
//    }


    private void movePlayer(int dx, int dy) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) personImage.getLayoutParams();
        params.horizontalBias += (float) dx / secondLayout.getWidth();
        params.verticalBias += (float) dy / secondLayout.getHeight();
        personImage.setLayoutParams(params);

        // Determine the direction based on dx and dy
        if (dx > 0) {
            // Move right
            personImage.setImageResource(R.drawable.right);
        } else if (dx < 0) {
            // Move left
            personImage.setImageResource(R.drawable.left);
        } else if (dy > 0) {
            // Move down
            personImage.setImageResource(R.drawable.down);
        } else if (dy < 0) {
            // Move up
            personImage.setImageResource(R.drawable.up);
        }

        // Update the translation of the map to move in the opposite direction of the character
        float newTranslationX = secondLayout.getTranslationX() - dx;
        float newTranslationY = secondLayout.getTranslationY() - dy;

        // Clamp the translations to ensure the map does not move out of bounds
        newTranslationX = clampTranslation(newTranslationX, secondLayout.getWidth(), scaleFactor);
        newTranslationY = clampTranslation(newTranslationY, secondLayout.getHeight(), scaleFactor);

        secondLayout.setTranslationX(newTranslationX);
        secondLayout.setTranslationY(newTranslationY);
    }


}
