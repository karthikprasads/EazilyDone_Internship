package com.example.eazilydone;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eazilydone.DTO.LeaderBoardItem;
import com.example.eazilydone.DTO.LeaderBoardResponse;
import com.example.eazilydone.backend.APIClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO :: Yet to implement game data and fetching email from game data instead of harcoring it
//
public class LeaderBoard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private List<Result> resultList;
    private TextView yourRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leader_board);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        resultList = new ArrayList<>();
        Map<String,String> mp=new HashMap<>();
        String myMail="hi@hi.com";
        mp.put("mail",myMail);
        Call<LeaderBoardResponse> call = APIClient.Service().getLeaderBoard(mp);
        final int[] currRank = {new Random().nextInt(10)};
        call.enqueue(new Callback<LeaderBoardResponse>() {
            @Override
            public void onResponse(Call<LeaderBoardResponse> call, Response<LeaderBoardResponse> response) {
                if(response.isSuccessful()){
                    LeaderBoardResponse leaderBoardResponse = response.body();
                    String msg=leaderBoardResponse.getMsg();
                    List<LeaderBoardItem> lb=leaderBoardResponse.getLb();
                    for(int i=0;i<lb.size();i++){
                        if(lb.get(i).getName().equals("You")){
                            currRank[0] =i+1;
                        }
                        else if(lb.get(i).getName().equals("You-1")){
                            currRank[0]=lb.get(i).getScore();
                        }
                        resultList.add(new Result(String.valueOf(i+1),lb.get(i).getName(),lb.get(i).getScore()));
                    }
                    List<Result> rearrangedList = new ArrayList<>();
                    if(resultList.size()==1){
                        rearrangedList.add(resultList.get(0));
                        adapter = new ResultAdapter(rearrangedList);
                        recyclerView.setAdapter(adapter);
                        yourRank = findViewById(R.id.yourRank);
                        String temp="Your Rank: " + currRank[0];
                        yourRank.setText(temp);
                        return;
                    }
                    for (int i = 0; i < resultList.size() / 2; i++) {
                        rearrangedList.add(resultList.get(i));
                        rearrangedList.add(resultList.get(i + resultList.size() / 2));
                    }
                    Log.d("TAG", "onResponse: "+rearrangedList.size());
                    adapter = new ResultAdapter(rearrangedList);
                    recyclerView.setAdapter(adapter);
                    yourRank = findViewById(R.id.yourRank);
                    String temp="Your Rank: " + currRank[0];
                    yourRank.setText(temp);
                }
            }
            @Override
            public void onFailure(Call<LeaderBoardResponse> call, Throwable t) {
                Toast.makeText(LeaderBoard.this, "Failed to fetch LeaderBoard", Toast.LENGTH_SHORT).show();
            }
        });
    }
}