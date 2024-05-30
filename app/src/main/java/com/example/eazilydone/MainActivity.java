// MainActivity.java
package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView dialogueText;
    private Button nextButton;
    private String[] dialogues = {
            "Guide: Hello there! How can I help you?",
            "Guide: Let's get started with the basics.",
            "Guide: This game will teach you financial activities.",
            // Add more dialogues as needed
    };
    private int dialogueIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Apply system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dialogueText = findViewById(R.id.dialogue_text);
        nextButton = findViewById(R.id.next_button);

        // Handle "Next" button click
        nextButton.setOnClickListener(v -> {
            if (dialogueIndex < dialogues.length - 1) {
                dialogueIndex++;
                dialogueText.setText(dialogues[dialogueIndex]);
            } else {
                // Start the next activity or perform your desired action when dialogues are done
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
