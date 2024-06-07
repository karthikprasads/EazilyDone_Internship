package com.example.eazilydone.chatbot;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eazilydone.R;
import com.example.eazilydone.backend.APIClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chatBotActivity extends AppCompatActivity {
    private RecyclerView rv;
    private EditText edit_txt;
    private ChatAdapter chatAdapter;
    private Button btn;
    private ImageButton voice_btn;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private ActivityResultLauncher<Intent> speechRecognizerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_bot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rv = findViewById(R.id.recyclerView);
        edit_txt = findViewById(R.id.editTextMessage);
        btn = findViewById(R.id.btn);
        chatAdapter = new ChatAdapter(new ArrayList<>());
        rv.setAdapter(chatAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        btn.setOnClickListener(v->{
            String msg = edit_txt.getText().toString().trim();
            if(!msg.isEmpty()){
                chatAdapter.addMessage(new Message(msg, Message.TYPE_USER));
                getBotResponse(msg);
                edit_txt.setText("");
                rv.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                btn.setEnabled(false);
            }
        });
        speechRecognizerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        ArrayList<String> matches = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        if (matches != null && !matches.isEmpty()) {
                            String msg = matches.get(0);
                            chatAdapter.addMessage(new Message(msg, Message.TYPE_USER));
                            getBotResponse(msg);
                            rv.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                            btn.setEnabled(false);
                        }
                    }
                });

        voice_btn = findViewById(R.id.voice_btn);
        voice_btn.setOnClickListener(v->{
            startVoiceInput();
        });
    }
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "si-LK");  // Sinhala language code
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something");

        try {
            speechRecognizerLauncher.launch(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getBotResponse(String msg) {
        Map<String, String> mp = new HashMap<>();
        mp.put("msg", msg);
        Call<Map<String, String>> call = APIClient.Service().getBotResponse(mp);
        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    Map<String, String> map = response.body();
                    chatAdapter.addMessage(new Message(map.get("msg"), Message.TYPE_BOT));
                    rv.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                    Log.d("TAG", "onResponse: " + map.get("msg"));
                    btn.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(chatBotActivity.this, "Error connecting to Bot Server", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
                btn.setEnabled(true);
            }
        });
    }
}