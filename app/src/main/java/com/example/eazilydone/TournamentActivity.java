package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TournamentActivity extends AppCompatActivity {

    private Button nextButton;
    private TextView rulesDisplay;
    private String[] rulesArray;
    private int currentRuleIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        nextButton = findViewById(R.id.nextButton);
        rulesDisplay = findViewById(R.id.RulesDisplay);

        // Initialize rules array with tournament rules
        rulesArray = new String[]{
                "1) All players should enter your name and then you will be given equal number of points(100)",
                "2) Your main intent is to finish few tasks and try to gain more points",
                "3) For top three players, there will be awarded few prizes",
                "4) Please read the instructions carefully before starting the game"
        };

        // Start displaying rules
        displayRule(currentRuleIndex);

        nextButton.setVisibility(View.INVISIBLE); // Hide the next button initially

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed to the next activity (QuizActivity)
                Intent intent = new Intent(TournamentActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayRule(final int index) {
        if (index < rulesArray.length) {
            // Create a new TextView for the rule
            final String rule = rulesArray[index];
            TextView newRuleTextView = new TextView(this);
            newRuleTextView.setText(rule);
            newRuleTextView.setTextColor(getResources().getColor(R.color.black)); // Set text color if needed

            // Apply animation to the rule
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_left);
            newRuleTextView.startAnimation(animation);

            // Add the TextView to the rules container
            LinearLayout rulesContainer = findViewById(R.id.rulesContainer);
            rulesContainer.addView(newRuleTextView);

            // Delay before showing the next rule
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayRule(index + 1);
                }
            }, 2000); // Adjust delay as needed (e.g., 2000 milliseconds = 2 seconds)
        } else {
            // All rules displayed, show the next button
            nextButton.setVisibility(View.VISIBLE);
        }
    }


}
