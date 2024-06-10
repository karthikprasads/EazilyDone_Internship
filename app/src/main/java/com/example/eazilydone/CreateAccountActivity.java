package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    private View savingsAccountForm;
    private View checkingAccountForm;
    private View businessAccountForm;
    private View studentAccountForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        savingsAccountForm = findViewById(R.id.savingsAccountForm);
        checkingAccountForm = findViewById(R.id.checkingAccountForm);
        businessAccountForm = findViewById(R.id.businessAccountForm);
        studentAccountForm = findViewById(R.id.studentAccountForm);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForm(savingsAccountForm);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForm(checkingAccountForm);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForm(businessAccountForm);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForm(studentAccountForm);
            }
        });
    }

    private void showForm(View form) {
        savingsAccountForm.setVisibility(View.GONE);
        checkingAccountForm.setVisibility(View.GONE);
        businessAccountForm.setVisibility(View.GONE);
        studentAccountForm.setVisibility(View.GONE);
        form.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        // Navigate back to ThirdActivity when back button is pressed
        Intent intent = new Intent(CreateAccountActivity.this, ThirdActivity.class);
        startActivity(intent);
        finish(); // Finish this activity so that when user presses back button again, it doesn't come back here
    }
}
