//package com.example.eazilydone;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.bumptech.glide.Glide;
//
//public class SecondActivity extends AppCompatActivity {
//
//    //private static final int INTRO_DURATION = 000; // 2 seconds
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Set the introductory layout
//       // setContentView(R.layout.activity_cloud);
//
//        // Load the GIF into the ImageView using Glide
//      //  ImageView introImageView = findViewById(R.id.introImageView);
//     //   Glide.with(this).asGif().load(R.drawable.cloud).into(introImageView);
//
//        // Use a Handler to switch to the main layout after the specified duration
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Set the main layout after the delay
//                setContentView(R.layout.activity_second);
//
//                // Set up the button click listener after setting the main layout
//                Button bankButton = findViewById(R.id.Bankbutton);
//                bankButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
//                        startActivity(intent);
//                    }
//                });
//            }
//        },0);
//    }
//}



//package com.example.eazilydone;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.ScaleGestureDetector;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.constraintlayout.widget.ConstraintSet;
//
//public class SecondActivity extends AppCompatActivity {
//
//    private ScaleGestureDetector scaleGestureDetector;
//    private GestureDetector gestureDetector;
//    private float scaleFactor = 1.0f;
//    private float maxScaleFactor = 3.0f;
//    private float minScaleFactor = 1.0f;
//    private float lastFocusX;
//    private float lastFocusY;
//    private float offsetX = 0f;
//    private float offsetY = 0f;
//    private static final float SWIPE_SPEED_MULTIPLIER = 2.0f; // Adjust as needed
//
//    private ImageView personImage;
//    private ConstraintLayout secondLayout;
//    private static final int MOVE_STEP = 20; // Number of pixels to move
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);
//
//        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
//        gestureDetector = new GestureDetector(this, new SwipeListener());
//
//        Button bankButton = findViewById(R.id.Bankbutton);
//        bankButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        personImage = findViewById(R.id.person);
//        secondLayout = findViewById(R.id.secondLayout);
//
//        // Set initial zoom
//        scaleFactor = 2.1f; // Set your initial zoom level here
//        secondLayout.setScaleX(scaleFactor);
//        secondLayout.setScaleY(scaleFactor);
//
//        findViewById(R.id.arrow_up).setOnClickListener(new MoveClickListener(0, -MOVE_STEP));
//        findViewById(R.id.arrow_down).setOnClickListener(new MoveClickListener(0, MOVE_STEP));
//        findViewById(R.id.arrow_left).setOnClickListener(new MoveClickListener(-MOVE_STEP, 0));
//        findViewById(R.id.arrow_right).setOnClickListener(new MoveClickListener(MOVE_STEP, 0));
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        scaleGestureDetector.onTouchEvent(event);
//        gestureDetector.onTouchEvent(event);
//        return true;
//    }
//
//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScaleBegin(ScaleGestureDetector detector) {
//            lastFocusX = detector.getFocusX();
//            lastFocusY = detector.getFocusY();
//            return true;
//        }
//
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            float scaleFactorDelta = detector.getScaleFactor();
//            scaleFactor *= scaleFactorDelta;
//            scaleFactor = Math.max(minScaleFactor, Math.min(scaleFactor, maxScaleFactor));
//
//            float focusX = detector.getFocusX();
//            float focusY = detector.getFocusY();
//
//            float dx = focusX - lastFocusX;
//            float dy = focusY - lastFocusY;
//
//            offsetX -= dx * (scaleFactorDelta - 1);
//            offsetY -= dy * (scaleFactorDelta - 1);
//
//            lastFocusX = focusX;
//            lastFocusY = focusY;
//
//            View secondLayout = findViewById(R.id.secondLayout);
//            secondLayout.setScaleX(scaleFactor);
//            secondLayout.setScaleY(scaleFactor);
//            secondLayout.setTranslationX(offsetX);
//            secondLayout.setTranslationY(offsetY);
//
//            return true;
//        }
//    }
//
//    private class SwipeListener extends GestureDetector.SimpleOnGestureListener {
//        private static final int SWIPE_THRESHOLD = 100;
//        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            float diffX = e2.getX() - e1.getX();
//            float diffY = e2.getY() - e1.getY();
//            View secondLayout = findViewById(R.id.secondLayout);
//
//            if (Math.abs(diffX) > Math.abs(diffY)) {
//                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
//                    if (diffX > 0) {
//                        offsetX = clampTranslation(offsetX + SWIPE_SPEED_MULTIPLIER * 100 / scaleFactor, secondLayout.getWidth(), scaleFactor);
//                    } else {
//                        offsetX = clampTranslation(offsetX - SWIPE_SPEED_MULTIPLIER * 100 / scaleFactor, secondLayout.getWidth(), scaleFactor);
//                    }
//                    secondLayout.setTranslationX(offsetX);
//                }
//            } else {
//                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                    if (diffY > 0) {
//                        offsetY = clampTranslation(offsetY + SWIPE_SPEED_MULTIPLIER * 100 / scaleFactor, secondLayout.getHeight(), scaleFactor);
//                    } else {
//                        offsetY = clampTranslation(offsetY - SWIPE_SPEED_MULTIPLIER * 100 / scaleFactor, secondLayout.getHeight(), scaleFactor);
//                    }
//                    secondLayout.setTranslationY(offsetY);
//                }
//            }
//            return super.onFling(e1, e2, velocityX, velocityY);
//        }
//    }
//
//    private float clampTranslation(float translation, int dimensionSize, float scale) {
//        float maxTranslation = (dimensionSize * (scale - 1)) / 2;
//        return Math.max(-maxTranslation, Math.min(translation, maxTranslation));
//    }
//
//    private class MoveClickListener implements View.OnClickListener {
//        private final int dx;
//        private final int dy;
//
//        public MoveClickListener(int dx, int dy) {
//            this.dx = dx;
//            this.dy = dy;
//        }
//
//        @Override
//        public void onClick(View v) {
//            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) personImage.getLayoutParams();
//            params.horizontalBias += (float) dx / secondLayout.getWidth();
//            params.verticalBias += (float) dy / secondLayout.getHeight();
//            personImage.setLayoutParams(params);
//
//            // Also move arrow buttons
//            View upArrow = findViewById(R.id.arrow_up);
//            View downArrow = findViewById(R.id.arrow_down);
//            View leftArrow = findViewById(R.id.arrow_left);
//            View rightArrow = findViewById(R.id.arrow_right);
//
//            ConstraintSet constraintSet = new ConstraintSet();
//            constraintSet.clone(secondLayout);
//
//            constraintSet.connect(upArrow.getId(), ConstraintSet.BOTTOM, personImage.getId(), ConstraintSet.TOP);
//            constraintSet.connect(downArrow.getId(), ConstraintSet.TOP, personImage.getId(), ConstraintSet.BOTTOM);
//            constraintSet.connect(leftArrow.getId(), ConstraintSet.END, personImage.getId(), ConstraintSet.START);
//            constraintSet.connect(rightArrow.getId(), ConstraintSet.START, personImage.getId(), ConstraintSet.END);
//
//            constraintSet.applyTo(secondLayout);
//        }
//    }
//}






package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        gestureDetector = new GestureDetector(this, new SwipeListener());

        Button bankButton = findViewById(R.id.Bankbutton);
        Button tournamentButton = findViewById(R.id.TournamentButton);


        bankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, DiscussionRoom.class);
                startActivity(intent);
            }
        });


        tournamentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, TournamentActivity.class);
                startActivity(intent);
            }
        });

        personImage = findViewById(R.id.person);
        secondLayout = findViewById(R.id.secondLayout);

        // Set initial zoom
        secondLayout.setScaleX(scaleFactor);
        secondLayout.setScaleY(scaleFactor);

        findViewById(R.id.arrow_up).setOnClickListener(new MoveClickListener(0, -MOVE_STEP));
        findViewById(R.id.arrow_down).setOnClickListener(new MoveClickListener(0, MOVE_STEP));
        findViewById(R.id.arrow_left).setOnClickListener(new MoveClickListener(-MOVE_STEP, 0));
        findViewById(R.id.arrow_right).setOnClickListener(new MoveClickListener(MOVE_STEP, 0));
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

    private class MoveClickListener implements View.OnClickListener {
        private final int dx;
        private final int dy;

        public MoveClickListener(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public void onClick(View v) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) personImage.getLayoutParams();
            params.horizontalBias += (float) dx / secondLayout.getWidth();
            params.verticalBias += (float) dy / secondLayout.getHeight();
            personImage.setLayoutParams(params);

            // Adjust map movement in the opposite direction
            secondLayout.setTranslationX(secondLayout.getTranslationX() - dx);
            secondLayout.setTranslationY(secondLayout.getTranslationY() - dy);
        }
    }

}
