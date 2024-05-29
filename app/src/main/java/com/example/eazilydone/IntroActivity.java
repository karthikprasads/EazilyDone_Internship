// IntroActivity.java
package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView loadingText;
    private Button nextButton;
    private String[] loadingMessages = {
            "Builds are being kept ready...",
            "Roads are being built...",
            "Preparing final touches..."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        progressBar = findViewById(R.id.loading_bar);
        loadingText = findViewById(R.id.loading_text);
        nextButton = findViewById(R.id.next_button);

        // Simulate loading process
        simulateLoading();
    }

    private void simulateLoading() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int progress = 0;
            int messageIndex = 0;

            @Override
            public void run() {
                if (progress < 100) {
                    progress++;
                    progressBar.setProgress(progress);

                    if (progress % 33 == 0 && messageIndex < loadingMessages.length) {
                        loadingText.setText(loadingMessages[messageIndex]);
                        messageIndex++;
                    }

                    handler.postDelayed(this, 30); // Adjust speed as necessary for a total duration of 2-3 seconds
                } else {
                    loadingText.setText("Loading complete!");
                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setOnClickListener(v -> {
                        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    });
                }
            }
        };
        handler.post(runnable);
    }
}
