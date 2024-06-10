package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        // Find the TextView
        TextView textView = findViewById(R.id.textureView);
    }

    @Override
    public void onBackPressed() {
        // Navigate back to ThirdActivity when back button is pressed
        Intent intent = new Intent(CreateAccountActivity.this, ThirdActivity.class);
        startActivity(intent);
        finish(); // Finish this activity so that when user presses back button again, it doesn't come back here
    }
}
