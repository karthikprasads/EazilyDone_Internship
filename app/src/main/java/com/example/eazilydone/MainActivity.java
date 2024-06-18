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

import com.example.eazilydone.data.playerData;

public class MainActivity extends AppCompatActivity {

    private TextView dialogueText;
    private Button nextButton;
//    private String[] dialogues = {
//            "  Hello there! Welcome to Eazily Done! I am your guide.",
//            "  Let's get started with the basics.",
//            "  This game will teach you financial activities.",
//            "  In this game, you will be dropped into a map. From there, you need to travel and find your respective buildings.",
//            "  One of the key buildings you need to find is the Bank. Once you enter the Bank, you will learn about how banks operate and the different types of banks.",
//            "  In the Bank, you will explore various banking services such as savings accounts, checking accounts, loans, and more.",
//            "  Another important building is the Tournament building, which hosts a quiz.",
//            "  In the Tournament, you will be shown the rules and asked to complete a quiz to gain points.",
//            "  If you successfully finish the quiz, you will earn rewards based on your performance.",
//            "  The game aims to provide a fun and interactive way to learn about financial activities and management.",
//            // Add more dialogues as needed
//    };

    private String[] dialogues = {
            "api mūlika karuṇu saman̆ga ārambha karamu.",
            "mema krīḍāva obaṭa mūlya kriyākārakam uganvanu æta.",
            "mema krīḍāvēdī, obava sitiyamakaṭa damanu æta. etæn siṭa, oba gaman kara obē adāḷa goḍanægili soyā gata yutuya.",
            "oba soyā gata yutu pradhāna goḍanæn̆gilivalin ekak vannē bæṁkuvayi. oba bæṁkuvaṭa ætuḷu vū pasu, bæṁku kriyātmaka vana ākāraya saha vividha vargayē bæṁku piḷiban̆dava oba igena ganu æta.",
            "bæṁkuva tuḷa, oba iturum giṇum, giṇum parīkṣā kirīma, ṇaya, saha tavat bohō bæṁku sēvā gavēṣaṇaya karanu æta.",
            "tavat vædagat goḍanægillak vannē praśnāvaliyak pavatvana taran̆gāvali goḍanægillayi." ,
            "taran̆gāvaliyēdī, obaṭa nīti penvanu labana atara lakuṇu labā gænīma san̆dahā praśnāvaliyak sampūrṇa karana lesa illā siṭinu æta.",
            "oba praśnāvaliya sārthakava avasan kaḷahot, obē kārya sādhanaya mata padanamva obaṭa tyāga læbenu æta.",
            "mūlya kriyākārakam saha kaḷamanākaraṇaya piḷiban̆dava igena gænīmaṭa vinōdajanaka saha antarkriyākārī mārgayak sæpayīma krīḍāvē aramuṇayi.",
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
        playerData pd = playerData.getInstance(getApplicationContext());
//        if(pd.getfirstDialogue()){
//            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//            startActivity(intent);
//            finish();
//        }
        dialogueText = findViewById(R.id.dialogue_text);
        nextButton = findViewById(R.id.next_button);
        // Handle "Next" button click
        nextButton.setOnClickListener(v -> {
            if (dialogueIndex < dialogues.length - 1) {
                dialogueIndex++;
                dialogueText.setText(dialogues[dialogueIndex]);
            } else {
                // Start the next activity or perform your desired action when dialogues are done
                //pd.setfirstDialogue(true);
                //pd.saveData(this);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
