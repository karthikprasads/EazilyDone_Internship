package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private TextView bankEmployeeDialogue;
    private Button next;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        bankEmployeeDialogue = findViewById(R.id.bankEmployeeDialogue);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                displayNextMessage(clickCount);
            }
        });
    }

    private void displayNextMessage(int count) {
        switch (count) {
            case 1:
                bankEmployeeDialogue.setText("You will be given 4 options and you have to pick one option.");
                break;
            case 2:
                bankEmployeeDialogue.setText("So let's start the game.");
                break;
            case 3:
                // Call QuizActivity2
                Intent intent = new Intent(QuizActivity.this, QuizActivity2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
