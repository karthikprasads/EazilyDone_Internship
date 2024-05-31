// IntroActivity.java
package com.example.eazilydone;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView loadingText;
    private String[] loadingMessages = {
            "Builds are being kept ready...",
            "Roads are being built...",
            "Preparing final touches..."
    };

    private TextView introText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        progressBar = findViewById(R.id.loading_bar);
        loadingText = findViewById(R.id.loading_text);
        introText = findViewById(R.id.intro_text);
        animateText("Welcome to Eazily Done!");

        // Simulate loading process
        simulateLoading();
    }

    private void simulateLoading() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int progress = 0;
            int totalSteps = 100; // total steps for ProgressBar
            long durationInMillis = 3000; // duration in milliseconds
            long delayBetweenSteps = durationInMillis / totalSteps; // calculate delay between each step

            @Override
            public void run() {
                if (progress < totalSteps) {
                    progress++;
                    progressBar.setProgress(progress);

                    // Update loading text if needed
                    if (progress % (totalSteps / loadingMessages.length) == 0) {
                        int messageIndex = progress / (totalSteps / loadingMessages.length);
                        if (messageIndex < loadingMessages.length) {
                            loadingText.setText(loadingMessages[messageIndex]);
                        }
                    }

                    handler.postDelayed(this, delayBetweenSteps);
                } else {
                    loadingText.setText("Loading complete!");
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.post(runnable);
    }


    private void animateText(final String text) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(3000); // Duration of the animation in milliseconds
        animator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            int length = (int) (text.length() * animatedValue);
            introText.setText(text.substring(0, length));
        });
        animator.start();
    }


}
