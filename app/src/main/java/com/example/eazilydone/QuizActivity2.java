package com.example.eazilydone;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity2 extends AppCompatActivity {
    private TextView timerTextView;
    private TextView questionTextView;
    private Button option1, option2, option3, option4, next;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 15000; // 15 seconds
    private int currentQuestionIndex = 0;
    // Define correct answers
    //private int[] correctAnswers = {1, 0, 0 ,0 ,0 }; // Index of correct option for each question (0-indexed)

    private boolean optionSelected = false;
    private boolean isEnglish = true; // Default language is English

    // Define your questions and options here
//    private String[] questions = {
//            "What is the main purpose of a budget?",
//            "What is the difference between stocks and bonds?",
//            "What does ROI stand for?",
//            "What is the role of a central bank in an economy?",
//            "What is inflation?",
//            "What is the purpose of diversification in investment?",
//            "What is GDP?",
//            "What is a mutual fund?",
//            "What is the function of a credit score?",
//            "What is the time value of money?",
//            "What is the difference between debit and credit cards?",
//            "What is a balance sheet?",
//            "What is the meaning of IPO?",
//            "What is asset allocation?",
//            "What is the difference between a traditional and Roth IRA?",
//            "What is a bull market?",
//            "What is a bear market?",
//            "What is compound interest?",
//            "What is a 401(k) retirement plan?",
//            "What is the significance of the Federal Reserve?"
//    };
//
//    private String[][] options = {
//            {"To plan and manage spending", "To track income", "To calculate taxes", "To invest in stocks"},
//            {"Stocks represent ownership, while bonds are debt securities", "Stocks have higher risk than bonds", "Stocks are only traded on weekdays", "Bonds are more liquid than stocks"},
//            {"Return On Investment", "Revenue On Income", "Ratio Of Investment", "Risk Of Investment"},
//            {"To regulate interest rates and monetary policy", "To supervise commercial banks", "To set fiscal policy", "To manage public debt"},
//            {"A decrease in the purchasing power of money", "An increase in the value of assets", "A rise in interest rates", "A decrease in unemployment"},
//            {"To spread risk across different assets", "To focus investments in one sector", "To maximize returns", "To minimize taxes"},
//            {"Gross Domestic Product", "Gross Domestic Price", "General Development Process", "Government Development Policy"},
//            {"A pool of funds managed by professionals", "A type of stock exchange", "A government agency", "A financial regulation"},
//            {"To assess creditworthiness for borrowing", "To determine eligibility for insurance", "To evaluate investment opportunities", "To calculate taxes"},
//            {"The principle that a dollar today is worth more than a dollar in the future", "The concept of saving money over time", "The idea that money grows through investment", "The interest rate on loans"},
//            {"Debit cards deduct funds directly from a checking account, while credit cards allow borrowing up to a certain limit", "Debit cards are used for online transactions, while credit cards are used in physical stores", "Debit cards offer rewards, while credit cards do not", "Debit cards have higher interest rates than credit cards"},
//            {"A financial statement that shows a company's assets, liabilities, and equity at a specific point in time", "A list of personal expenses and income", "A record of stock market transactions", "A government budget report"},
//            {"Initial Public Offering", "Investment Portfolio Option", "Income Profit Opportunity", "Interest Payment Obligation"},
//            {"Determining the mix of asset classes in an investment portfolio", "The process of buying and selling stocks", "Calculating the value of company shares", "Managing personal savings"},
//            {"Traditional IRA contributions may be tax-deductible, while Roth IRA contributions are not", "Traditional IRA earnings are tax-deferred, while Roth IRA earnings are tax-free", "Traditional IRA withdrawals are penalty-free at any age, while Roth IRA withdrawals are subject to penalties", "Traditional IRA contributions have no income limits, while Roth IRA contributions do"},
//            {"A market characterized by rising stock prices and investor optimism", "A market characterized by falling stock prices and investor pessimism", "A market with no significant changes in stock prices", "A market with high volatility"},
//            {"A market characterized by falling stock prices and investor pessimism", "A market characterized by rising stock prices and investor optimism", "A market with no significant changes in stock prices", "A market with high volatility"},
//            {"Interest calculated on the initial principal and also on the accumulated interest from previous periods", "Interest paid only on the initial principal", "Interest compounded monthly", "Interest paid at maturity"},
//            {"A retirement savings plan sponsored by an employer that allows employees to save and invest part of their paycheck before taxes", "A government program providing financial assistance to retirees", "A type of individual retirement account for self-employed individuals", "A high-yield savings account"},
//            {"It regulates the nation's monetary policy, supervises and regulates banks, and provides financial services such as clearing checks and managing the nation's money supply."}
//    };


    private String[] questions = {
            "What is the main purpose of a budget?",
            "What is the difference between stocks and bonds?",
            "What does ROI stand for?",
            "What is the role of a central bank in an economy?",
            "What is inflation?",
            "What is the purpose of diversification in investment?",
            "What is GDP?",
            "What is a mutual fund?",
            "What is the function of a credit score?",
            "What is the time value of money?"
    };

    private String[][] options = {
            // Options for "What is the main purpose of a budget?"
            {"To plan and manage spending", "To track income", "To calculate taxes", "To invest in stocks"},

            // Options for "What is the difference between stocks and bonds?"
            {"Stocks represent ownership, while bonds are debt securities", "Stocks have higher risk than bonds", "Stocks are only traded on weekdays", "Bonds are more liquid than stocks"},

            // Options for "What does ROI stand for?"
            {"Return On Investment", "Revenue On Income", "Ratio Of Investment", "Risk Of Investment"},

            // Options for "What is the role of a central bank in an economy?"
            {"To regulate interest rates and monetary policy", "To supervise commercial banks", "To set fiscal policy", "To manage public debt"},

            // Options for "What is inflation?"
            {"A decrease in the purchasing power of money", "An increase in the value of assets", "A rise in interest rates", "A decrease in unemployment"},

            // Options for "What is the purpose of diversification in investment?"
            {"To spread risk across different assets", "To focus investments in one sector", "To maximize returns", "To minimize taxes"},

            // Options for "What is GDP?"
            {"Gross Domestic Product", "Gross Domestic Price", "General Development Process", "Government Development Policy"},

            // Options for "What is a mutual fund?"
            {"A pool of funds managed by professionals", "A type of stock exchange", "A government agency", "A financial regulation"},

            // Options for "What is the function of a credit score?"
            {"To assess creditworthiness for borrowing", "To determine eligibility for insurance", "To evaluate investment opportunities", "To calculate taxes"},

            // Options for "What is the time value of money?"
            {"The principle that a dollar today is worth more than a dollar in the future", "The concept of saving money over time", "The idea that money grows through investment", "The interest rate on loans"}
    };


    private String[] romanisedSinhalaQuestions = {
            "1. Budget ekak genada mukuth pradhana lakshya?",
            "2. Stocks saha bonds walata wenwima mokakda?",
            "3. ROI kiyannada mokadda?",
            "4. Maadhya bankuwak artha shastrayaka muladarma mokakda?",
            "5. Inflation kiyannada mokakda?",
            "6. Investment ekak karanata diversification genada mukuth prayojana?",
            "7. GDP kiyannada mokadda?",
            "8. Mutual fund kiyannada mokadda?",
            "9. Credit score ekak genada mukuth muladarma?",
            "10. Time value of money kiyannada mokadda?"
    };

    private String[][] romanisedSinhalaOptions =
            {
                    // Options for "Budget ekak genada mukuth pradhana lakshya?"
                    {"Wiyadam wedihimata sanda wiyadam karanna hekiya", "Adayum lakwima", "Badda gananaya kirima", "Stocks wala investment karanawa"},

                    // Options for "Stocks saha bonds walata wenwima mokakda?"
                    {"Stocks walin piyawara labena bawa, bonds araksha patreni", "Stocks wala risk wediyen bond wala", "Stocks kewwath rawume denne", "Bonds ithin thurunu wena bawa"},

                    // Options for "ROI kiyannada mokadda?"
                    {"Return On Investment", "Revenue On Income", "Ratio Of Investment", "Risk Of Investment"},

                    // Options for "Maadhya bankuwak artha shastrayaka muladarma mokakda?"
                    {"Padi araksha yuthu sadahasa saha maadhya artha shastraya pabandhawa", "Vyapara banku niyojana kara", "Rajya dharmaya niyojana kara", "Janatha nidhanaya paripalanaya kara"},

                    // Options for "Inflation kiyannada mokakda?"
                    {"Mudalayak diya galawimaya", "Araksha milaya wawenawa", "Ithuru lipi padi peramun wawenawa", "Wiyadam welawe adu wenawa"},

                    // Options for "Investment ekak karanata diversification genada mukuth prayojana?"
                    {"Vibhinna assa galapena wiwida kramaya", "Ekama kshetraya wiskam karanawa", "Prathyasha laba wedihimata karanawa", "Badda adu wenawa"},

                    // Options for "GDP kiyannada mokadda?"
                    {"Gross Domestic Product", "Gross Domestic Price", "General Development Process", "Government Development Policy"},

                    // Options for "Mutual fund kiyannada mokadda?"
                    {"Wisidamana pudgalayange mudalayak", "Stocks parisarayak", "Rajaya amathi sewaya", "Aarthika pabanduma"},

                    // Options for "Credit score ekak genada mukuth muladarma?"
                    {"Usas sadarana karanaya aduwana hekiyawa", "Insurance wiliyam patha karanna hoda", "Investment sawanayak balaanna", "Badda ganana kirima"},

                    // Options for "Time value of money kiyannada mokadda?"
                    {"Adha dolarinagathaya wayak kalaaya", "Kaaleen nidhanaya wiskam kalaaya", "Mudalayak araksha kalaya", "Loan padiya"}
            };



    private int[] correctAnswers = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

//    private String[] selectedQuestions = new String[5];
//    private String[][] selectedOptions = new String[5][4];
//    private int[] selectedCorrectAnswers = new int[5];

    private List<String> selectedQuestions;
    private List<String[]> selectedOptions;
    private List<Integer> selectedCorrectAnswers;

    private List<String> selectedRomanisedSinhalaQuestions;
    private List<String[]> selectedRomanisedSinhalaOptions;


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


        //randomize questions
        selectRandomQuestions();

        //print questions and options to log both in English and Sinhala

        for (int i = 0; i < 5; i++) {
            Log.d("TAG", "onCreate: "+selectedQuestions.get(i));
            Log.d("TAG", "onCreate: "+selectedRomanisedSinhalaQuestions.get(i));
            for (int j = 0; j < 4; j++) {
                Log.d("TAG", "onCreate: "+selectedOptions.get(i)[j]);
                Log.d("TAG", "onCreate: "+selectedRomanisedSinhalaOptions.get(i)[j]);
            }
        }



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


        Button swapLanguageButton = findViewById(R.id.SwapLanguage);
        swapLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnglish = !isEnglish; // Toggle the language flag
                displayQuestion(); // Refresh the question display with the new language
            }
        });


//        Handler h=new Handler();
//        Runnable r=new Runnable() {
//            @Override
//            public void run() {
//                Log.d("TAG", "run: ");
//            }
//        };
//        h.post(r);


        Handler h=new Handler();
        Runnable r= new Runnable() {
            @Override
            public void run() {
                //print timeLeftInMillis
                Log.d("TAG", "run: "+timeLeftInMillis);


                if ( timeLeftInMillis==0){

                    Log.d("TAG", "run: came to option not Selected");
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

                else{
                    h.postDelayed(this, 16);
                }

            }
        };
        h.post(r);





//        new Handler(new Runnable() {
//            @Override
//
//        });
    }

    private void startTimer() {
        timeLeftInMillis = 15000; // Reset time left to initial value

        if (countDownTimer != null) {
            Log.d("TAG", "startTimer: hiii"+timeLeftInMillis);
            countDownTimer.cancel(); // Stop the existing timer
        }

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
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


    private void resetOptions(Button option) {
        // Reset background color
        option.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        // Reset selected state
        option.setSelected(false);

        // Reset font style and size
        option.setTypeface(null, Typeface.NORMAL);
        option.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10); // Initial text size
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


//    private void displayQuestion() {
//        // Reset background color, selected state, font style, and size of all options
//        resetOptions(option1);
//        resetOptions(option2);
//        resetOptions(option3);
//        resetOptions(option4);
//
//        // Check if there are more questions
//        if (currentQuestionIndex < questions.length) {
//            // Display the current question and options
//            questionTextView.setText(questions[currentQuestionIndex]);
//            option1.setText(options[currentQuestionIndex][0]);
//            option2.setText(options[currentQuestionIndex][1]);
//            option3.setText(options[currentQuestionIndex][2]);
//            option4.setText(options[currentQuestionIndex][3]);
//
//            // Enable all options
//            option1.setEnabled(true);
//            option2.setEnabled(true);
//            option3.setEnabled(true);
//            option4.setEnabled(true);
//
//            // Start or reset the timer
//            startTimer();
//        } else {
//            // No more questions, navigate to QuizActivity3
//            Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
//            startActivity(intent);
//            finish(); // Optional, to close QuizActivity2
//        }
//    }



    private void displayQuestion() {
        // Reset background color, selected state, font style, and size of all options
        resetOptions(option1);
        resetOptions(option2);
        resetOptions(option3);
        resetOptions(option4);

        if (currentQuestionIndex < selectedQuestions.size()) {



            updateQuestionText();

//            if (isEnglish) {
//                questionTextView.setText(selectedQuestions.get(currentQuestionIndex));
//                option1.setText(selectedOptions.get(currentQuestionIndex)[0]);
//                option2.setText(selectedOptions.get(currentQuestionIndex)[1]);
//                option3.setText(selectedOptions.get(currentQuestionIndex)[2]);
//                option4.setText(selectedOptions.get(currentQuestionIndex)[3]);
//            } else {
//                questionTextView.setText(selectedRomanisedSinhalaQuestions.get(currentQuestionIndex));
//                option1.setText(selectedRomanisedSinhalaOptions.get(currentQuestionIndex)[0]);
//                option2.setText(selectedRomanisedSinhalaOptions.get(currentQuestionIndex)[1]);
//                option3.setText(selectedRomanisedSinhalaOptions.get(currentQuestionIndex)[2]);
//                option4.setText(selectedRomanisedSinhalaOptions.get(currentQuestionIndex)[3]);
//            }


//            // Display the current question and options
//            questionTextView.setText(selectedQuestions.get(currentQuestionIndex));
//            option1.setText(selectedOptions.get(currentQuestionIndex)[0]);
//            option2.setText(selectedOptions.get(currentQuestionIndex)[1]);
//            option3.setText(selectedOptions.get(currentQuestionIndex)[2]);
//            option4.setText(selectedOptions.get(currentQuestionIndex)[3]);




            // Reset optionSelected flag
            optionSelected = false;

            // Start or reset the timer
            startTimer();
        } else {
            // No more questions, navigate to QuizActivity3
            Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
            startActivity(intent);
            finish(); // Optional, to close QuizActivity2
        }
    }


    private void updateQuestionText() {
        if (currentQuestionIndex < selectedQuestions.size()) {
            // Display the current question and options based on the current language
            if (isEnglish) {
                questionTextView.setText(selectedQuestions.get(currentQuestionIndex));
                option1.setText(selectedOptions.get(currentQuestionIndex)[0]);
                option2.setText(selectedOptions.get(currentQuestionIndex)[1]);
                option3.setText(selectedOptions.get(currentQuestionIndex)[2]);
                option4.setText(selectedOptions.get(currentQuestionIndex)[3]);
            } else {
                questionTextView.setText(selectedRomanisedSinhalaQuestions.get(currentQuestionIndex));
                option1.setText(selectedRomanisedSinhalaOptions.get(currentQuestionIndex)[0]);
                option2.setText(selectedRomanisedSinhalaOptions.get(currentQuestionIndex)[1]);
                option3.setText(selectedRomanisedSinhalaOptions.get(currentQuestionIndex)[2]);
                option4.setText(selectedRomanisedSinhalaOptions.get(currentQuestionIndex)[3]);
            }
        }
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

        optionSelected = true;

        Log.d("TAG", "handleOptionSelection: came to handleOptionSelection");
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


    }







    private void moveToNextQuestion() {
        optionSelected = false;
        //startTimer();
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
                    case 3:
                    case 4:
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
                }
                else {
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
                        //reset time
                        startTimer();
                        // Display the next question
                        displayQuestion();
                    }
                });
            }
            else {
                // User has not selected any option
                Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            // No more questions, navigate to QuizActivity3
            Intent intent = new Intent(QuizActivity2.this, QuizActivity3.class);
            startActivity(intent);
            finish(); // Optional, to close QuizActivity2
        }
    }




//    private void selectRandomQuestions() {
//        List<Integer> indices = new ArrayList<>();
//        for (int i = 0; i < questions.length; i++) {
//            indices.add(i);
//        }
//        Collections.shuffle(indices);
//
//        selectedQuestions = new ArrayList<>();
//        selectedOptions = new ArrayList<>();
//        selectedCorrectAnswers = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            selectedQuestions.add(questions[indices.get(i)]);
//            selectedOptions.add(options[indices.get(i)]);
//            selectedCorrectAnswers.add(correctAnswers[indices.get(i)]);
//        }
//    }


    private void selectRandomQuestions() {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        selectedQuestions = new ArrayList<>();
        selectedRomanisedSinhalaQuestions = new ArrayList<>();
        selectedOptions = new ArrayList<>();
        selectedRomanisedSinhalaOptions = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int index = indices.get(i);
            selectedQuestions.add(questions[index]);
            selectedRomanisedSinhalaQuestions.add(romanisedSinhalaQuestions[index]);
            selectedOptions.add(options[index]);
            selectedRomanisedSinhalaOptions.add(romanisedSinhalaOptions[index]);
        }
    }






}
