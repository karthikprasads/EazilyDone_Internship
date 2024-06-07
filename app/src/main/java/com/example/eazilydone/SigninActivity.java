package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
public class SigninActivity extends AppCompatActivity{

    private LinearLayout signInCard, signUpCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        signInCard = findViewById(R.id.signInCard);
        signUpCard = findViewById(R.id.signUpCard);

        findViewById(R.id.createAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInCard.setVisibility(View.GONE);
                signUpCard.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.signInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
                finish();





            }
        });

        findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if we click on sign up button, we will be redirected to the main activity
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
