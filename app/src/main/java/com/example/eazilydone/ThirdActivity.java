package com.example.eazilydone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //Button backButton = findViewById(R.id.BackButton);
        //backButton.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View v) {
//                finish(); // Finish the current activity to go back to the previous one
//            }
//        });

        Button accountDetailsButton = findViewById(R.id.accountDetailsButton);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        Button depositButton = findViewById(R.id.depositButton);
        Button otherButton = findViewById(R.id.otherButton);
        Button chatbotButton = findViewById(R.id.chatbotButton);

        accountDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "Account Details Selected", Toast.LENGTH_SHORT).show();
                // Implement action for Account Details
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "Create Account Selected", Toast.LENGTH_SHORT).show();
                // Implement action for Create an Account
            }
        });

        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "Deposit Selected", Toast.LENGTH_SHORT).show();
                // Implement action for Deposit
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "Other Selected", Toast.LENGTH_SHORT).show();
                // Implement action for Other
            }
        });

        chatbotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "Chatbot Selected", Toast.LENGTH_SHORT).show();
                // Implement action for Chatbot
            }
        });
    }
}
