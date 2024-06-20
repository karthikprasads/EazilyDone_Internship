package com.example.eazilydone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eazilydone.backend.APIClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
                //get the username and password from signInCard
                TextView userName= findViewById(R.id.username);
                TextView password= findViewById(R.id.password);
                String email = userName.getText().toString();
                String pwd = password.getText().toString();
                Map<String,String> mp = new HashMap<>();
                mp.put("email", email);
                mp.put("password",pwd);
                Call<Map<String, String>> call = APIClient.Service().login(mp);
                call.enqueue(new Callback<Map<String, String>>() {
                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(SigninActivity.this, "signIn Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(SigninActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        Toast.makeText(SigninActivity.this, "Error connecting to Server", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
                    }
                });
            }
        });

        findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if we click on sign up button, we will be redirected to the main activity
                TextView tv1= findViewById(R.id.fullName);
                TextView tv2= findViewById(R.id.signUpGmail);
                TextView tv3= findViewById(R.id.signUpPassword);
                String name=tv1.getText().toString();
                String email=tv2.getText().toString();
                String password=tv3.getText().toString();
                Map<String,String> mp = new HashMap<>();
                mp.put("name", name);
                mp.put("email", email);
                mp.put("password", password);
                Call<Map<String, String>> call = APIClient.Service().addUser(mp);
                call.enqueue(new Callback<Map<String, String>>() {
                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(SigninActivity.this, "SignUp successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(SigninActivity.this, "SignUp failed", Toast.LENGTH_SHORT).show();
                            return;

                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        Toast.makeText(SigninActivity.this, "Failed to connect to server", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
                    }
                });

            }
        });
    }
}
