package com.example.eazilydone;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class QuizActivity2 extends AppCompatActivity {
    private TextView timerTextView;
    private TextView questionTextView;
    private Button option1, option2, option3, option4, next;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 15000; // 15 seconds
    private int currentQuestionIndex = 0;
    // Define correct answers
    private int[] correctAnswers = {1, 0, 0}; // Index of correct option for each question (0-indexed)

    private boolean optionSelected = false;

    // Define your questions and options here
    private String[] questions = {
            "What is the main purpose of a budget?",
            "What is the difference between stocks and bonds?",
            "What does ROI stand for?"
    };

    private String[][] options = {
            {"To plan and manage spending", "To track income", "To calculate taxes", "To invest in stocks"},
            {"Stocks represent ownership, while bonds are debt securities", "Stocks have higher risk than bonds", "Stocks are only traded on weekdays", "Bonds are more liquid than stocks"},
            {"Return On Investment", "Revenue On Income", "Ratio Of Investment", "Risk Of Investment"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        next = findViewById(R.id.next);

        // Start the timer
        startTimer();

        // Display the first question
        displayQuestion();

        // Set onClickListener for options
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle option selection
                handleOptionSelection(option1);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionSelection(option2);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionSelection(option3);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionSelection(option4);
            }
        });

        // Set onClickListener for next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to the next question or activity
                moveToNextQuestion();
            }
        });
    }

    private void startTimer() {
        timeLeftInMillis = 15000; // Reset time left to initial value

        if (countDownTimer != null) {
            countDownTimer.cancel(); // Stop the existing timer
        }

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                // Timer finished, handle accordingly
                timeLeftInMillis = 0;
                updateCountdownText();
                // Implement your logic when the timer finishes
            }
        }.start();
    }


    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format("%02d:%02d", seconds / 60, seconds % 60);
        timerTextView.setText(timeLeftFormatted);
    }


    private void displayQuestion() {
        // Reset background color, selected state, font style, and size of all options
        resetOptions(option1);
        resetOptions(option2);
        resetOptions(option3);
        resetOptions(option4);

        // Check if there are more questions
        if (currentQuestionIndex < questions.length) {
            // Display the current question and options
            questionTextView.setText(questions[currentQuestionIndex]);
            option1.setText(options[currentQuestionIndex][0]);
            option2.setText(options[currentQuestionIndex][1]);
            option3.setText(options[currentQuestionIndex][2]);
            option4.setText(options[currentQuestionIndex][3]);

            // Enable all options
            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            option4.setEnabled(true);

            // Start or reset the timer
            startTimer();
        } else {
            // No more questions, navigate to QuizActivity3
            Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
            startActivity(intent);
            finish(); // Optional, to close QuizActivity2
        }
    }



    private void resetOptions(Button option) {
        // Reset background color
        option.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        // Reset selected state
        option.setSelected(false);

        // Reset font style and size
        option.setTypeface(null, Typeface.NORMAL);
        option.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); // Initial text size
    }


//    private void handleOptionSelection(Button option) {
//        // Reset all options to enabled state
//        option1.setEnabled(true);
//        option2.setEnabled(true);
//        option3.setEnabled(true);
//        option4.setEnabled(true);
//
//        // Reset font style and size of all options
//        option1.setTypeface(null, Typeface.NORMAL);
//        option2.setTypeface(null, Typeface.NORMAL);
//        option3.setTypeface(null, Typeface.NORMAL);
//        option4.setTypeface(null, Typeface.NORMAL);
//        option1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); // Initial text size
//        option2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
//        option3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
//        option4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
//
//        // Reset background color of all options
//        option1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
//        option2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
//        option3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
//        option4.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
//
//        // Reset selected state of all options
//        option1.setSelected(false);
//        option2.setSelected(false);
//        option3.setSelected(false);
//        option4.setSelected(false);
//
//        // Set font style and size for selected option
//        option.setTypeface(null, Typeface.BOLD_ITALIC);
//        option.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12); // Increased text size by 2sp
//
//        // Highlight selected option
//        option.setSelected(true);
//
//        // Set background color for selected option
//        option.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
//
//        // Handle selected option logic
//        // You can implement your logic here
//    }

    private void handleOptionSelection(Button option) {
        // Reset all options to enabled state
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);

        // Reset font style and size of all options
        option1.setTypeface(null, Typeface.NORMAL);
        option2.setTypeface(null, Typeface.NORMAL);
        option3.setTypeface(null, Typeface.NORMAL);
        option4.setTypeface(null, Typeface.NORMAL);
        option1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); // Initial text size
        option2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        option3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        option4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        // Reset background color of all options
        option1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        option2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        option3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        option4.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        // Reset selected state of all options
        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);

        // Set font style and size for selected option
        option.setTypeface(null, Typeface.BOLD_ITALIC);
        option.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12); // Increased text size by 2sp

        // Highlight selected option
        option.setSelected(true);

        // Set background color for selected option
        option.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

        // Set a delayed task to check if an option has been selected within the time limit
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!optionSelected) {
                    // User did not select an option within the time limit
                    // Hide all views except for LinearLayout
                    questionTextView.setVisibility(View.GONE);
                    option1.setVisibility(View.GONE);
                    option2.setVisibility(View.GONE);
                    option3.setVisibility(View.GONE);
                    option4.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);

                    // Display "Timed out" message in the result TextView
                    TextView resultTextView = findViewById(R.id.resultTextView);
                    resultTextView.setText("Timed out");

                    // Display the result in LinearLayout
                    LinearLayout resultLayout = findViewById(R.id.result);
                    resultLayout.setVisibility(View.VISIBLE);

                    // Set onClickListener for the button inside LinearLayout to move to QuizActivity3
                    Button nextButton = resultLayout.findViewById(R.id.nextResult);
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Hide the result layout
                            resultLayout.setVisibility(View.GONE);
                            // Navigate to QuizActivity3
                            Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
                            startActivity(intent);
                            finish(); // Optional, to close QuizActivity2
                        }
                    });
                }
            }
        }, timeLeftInMillis);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }



    private void moveToNextQuestion() {
        // Check if there are more questions
        if (currentQuestionIndex < questions.length) {
            // Check if user has selected an option
            if (option1.isSelected() || option2.isSelected() || option3.isSelected() || option4.isSelected()) {
                // Check if user's selection matches the correct answer
                boolean isCorrect = false;
                switch (currentQuestionIndex) {
                    case 0:
                    case 1:
                    case 2:
                        isCorrect = option1.isSelected() && correctAnswers[currentQuestionIndex] == 0 ||
                                option2.isSelected() && correctAnswers[currentQuestionIndex] == 1 ||
                                option3.isSelected() && correctAnswers[currentQuestionIndex] == 2 ||
                                option4.isSelected() && correctAnswers[currentQuestionIndex] == 3;
                        break;
                }

                // Display message based on correctness
                if (isCorrect) {
                    // Set text for the result TextView
                    TextView resultTextView = findViewById(R.id.resultTextView);
                    resultTextView.setText("Correct Answer!");
                    Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
                } else {
                    // Set text for the result TextView
                    TextView resultTextView = findViewById(R.id.resultTextView);
                    resultTextView.setText("Wrong Answer!");

                    // Hide all views except for LinearLayout
                    questionTextView.setVisibility(View.GONE);
                    option1.setVisibility(View.GONE);
                    option2.setVisibility(View.GONE);
                    option3.setVisibility(View.GONE);
                    option4.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);

                    // Display the result in LinearLayout
                    LinearLayout resultLayout = findViewById(R.id.result);
                    resultLayout.setVisibility(View.VISIBLE);

                    // Set onClickListener for the button inside LinearLayout to move to QuizActivity3
                    Button nextButton = resultLayout.findViewById(R.id.nextResult);
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Hide the result layout
                            resultLayout.setVisibility(View.GONE);
                            // Navigate to QuizActivity3
                            Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
                            startActivity(intent);
                            finish(); // Optional, to close QuizActivity2
                        }
                    });

                    return; // Return to prevent further execution
                }

                // Hide all views except for LinearLayout
                questionTextView.setVisibility(View.GONE);
                option1.setVisibility(View.GONE);
                option2.setVisibility(View.GONE);
                option3.setVisibility(View.GONE);
                option4.setVisibility(View.GONE);
                next.setVisibility(View.GONE);

                // Display the result in LinearLayout
                LinearLayout resultLayout = findViewById(R.id.result);
                resultLayout.setVisibility(View.VISIBLE);

                // Set onClickListener for the button inside LinearLayout to move to the next question
                Button nextButton = resultLayout.findViewById(R.id.nextResult);
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Hide the result layout
                        resultLayout.setVisibility(View.GONE);
                        // Show all views except for LinearLayout
                        questionTextView.setVisibility(View.VISIBLE);
                        option1.setVisibility(View.VISIBLE);
                        option2.setVisibility(View.VISIBLE);
                        option3.setVisibility(View.VISIBLE);
                        option4.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);

                        // Move to the next question
                        currentQuestionIndex++;
                        // Display the next question
                        displayQuestion();
                    }
                });
            } else {
                // User has not selected any option
                Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            }
        } else {
            // No more questions, navigate to QuizActivity3
            Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
            startActivity(intent);
            finish(); // Optional, to close QuizActivity2
        }
    }






}
