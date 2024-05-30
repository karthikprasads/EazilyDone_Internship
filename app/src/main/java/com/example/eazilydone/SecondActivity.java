package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {

    private static final int INTRO_DURATION = 000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the introductory layout
       // setContentView(R.layout.activity_cloud);

        // Load the GIF into the ImageView using Glide
      //  ImageView introImageView = findViewById(R.id.introImageView);
     //   Glide.with(this).asGif().load(R.drawable.cloud).into(introImageView);

        // Use a Handler to switch to the main layout after the specified duration
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Set the main layout after the delay
                setContentView(R.layout.activity_second);

                // Set up the button click listener after setting the main layout
                Button bankButton = findViewById(R.id.Bankbutton);
                bankButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }, INTRO_DURATION);
    }
}
