package com.example.eazilydone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);

        Button finishButton = findViewById(R.id.finish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display "Hello" message when the button is clicked
                Toast.makeText(QuizActivity3.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
