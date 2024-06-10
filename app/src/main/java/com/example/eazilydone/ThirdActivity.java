package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private View buttonsContainer;
    private View createAccountLayout;
    private Button closeCreateAccountLayoutButton;
    private Button clickToCreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        buttonsContainer = findViewById(R.id.buttonsContainer);
        createAccountLayout = findViewById(R.id.createAccountLayout);
        closeCreateAccountLayoutButton = findViewById(R.id.closeCreateAccountLayoutButton);
        clickToCreateAccountButton = findViewById(R.id.clickToCreateAccountButton);

        Button accountDetailsButton = findViewById(R.id.accountDetailsButton);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        Button depositButton = findViewById(R.id.depositButton);
        Button otherButton = findViewById(R.id.otherButton);
        Button chatbotButton = findViewById(R.id.chatbotButton);
        ScrollView scrollView = findViewById(R.id.scrollView2);





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

        accountDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "Account Details Selected", Toast.LENGTH_SHORT).show();
                // Implement action for Account Details
            }
        });
        chatbotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "Chatbot Selected", Toast.LENGTH_SHORT).show();
                // Implement action for Chatbot
            }
        });


        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (scrollView.getChildAt(0).getBottom() <= (scrollView.getHeight() + scrollView.getScrollY())) {
                    // Scroll is at bottom, show clickToCreateAccountButton
                    clickToCreateAccountButton.setVisibility(View.VISIBLE);
                } else {
                    // Scroll is not at bottom, hide clickToCreateAccountButton
                    clickToCreateAccountButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        clickToCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ThirdActivity.this, "Account creation process started", Toast.LENGTH_SHORT).show();
                //call CreateAccountActivity
                Intent intent = new Intent(ThirdActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
                // Perform actions to start the account creation process

            }
        });


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsContainer.setVisibility(View.INVISIBLE);
                createAccountLayout.setVisibility(View.VISIBLE);
            }
        });



        closeCreateAccountLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccountLayout.setVisibility(View.INVISIBLE);
                buttonsContainer.setVisibility(View.VISIBLE);
            }
        });

        TextView textView = findViewById(R.id.accountTypesText);
        String text = "<font color='#008000'><b>Personal Account :-</b></font>" +
                "Personal accounts offer versatile management of day-to-day finances. They provide features like online banking and convenient access to funds.(Use ChatBot to know more)<br><br>" +
                "<font color='#008000'><b>Savings Account :-</b></font><br>" +
                "Savings accounts are ideal for setting aside money for future needs or emergencies. They often come with interest, helping your savings grow over time.(Use ChatBot to know more)<br><br>" +
                "<font color='#008000'><b>Checking Account :-</b></font><br>" +
                "Checking accounts are perfect for everyday transactions such as bill payments and purchases. They typically include a debit card and various banking conveniences.(Use ChatBot to know more)<br><br>" +
                "<font color='#008000'><b>Investment Account :-</b></font><br>" +
                "Investment accounts are tailored for growing your wealth through investments in stocks, bonds, and other assets. They offer the potential for higher returns over time.(Use ChatBot to know more)<br><br>" +
                "<font color='#008000'><b>Retirement Account :-</b></font><br>" +
                "Retirement accounts are essential for long-term financial planning. They provide tax advantages and often include employer contributions, helping you build a nest egg for retirement.(Use ChatBot to know more)<br><br>";


        textView.setText(Html.fromHtml(text));

    }
}
