package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DiscussionRoom extends AppCompatActivity {

    private TextView bankEmployeeDialogue;
    private TextView customerDialogue;
    private Button nextButton;
    private ImageView manager;
    private int dialogueStep = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussionroom);

        bankEmployeeDialogue = findViewById(R.id.bankEmployeeDialogue);
        customerDialogue = findViewById(R.id.customerDialogue);
        nextButton = findViewById(R.id.nextButton);
        manager = findViewById(R.id.manager);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dialogueStep) {
                    case 0:
                        bankEmployeeDialogue.setVisibility(View.GONE);
                        customerDialogue.setText("I'm having this problem.");
                        customerDialogue.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        customerDialogue.setVisibility(View.GONE);
                        bankEmployeeDialogue.setText("Regarding what?");
                        bankEmployeeDialogue.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        bankEmployeeDialogue.setVisibility(View.GONE);
                        customerDialogue.setText("I want to increase the money.");
                        customerDialogue.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        customerDialogue.setVisibility(View.GONE);
                        bankEmployeeDialogue.setText("Let's discuss this in another room.");
                        bankEmployeeDialogue.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        Intent intent = new Intent(DiscussionRoom.this, ThirdActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                dialogueStep++;
            }
        });
    }
}
