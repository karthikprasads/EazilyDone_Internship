package com.example.eazilydone.backend;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.example.eazilydone.DTO.LeaderBoardResponse;

public interface APIService {
    @POST("bot/response")
    Call<Map<String, String>> getBotResponse(@Body Map<String, String> req);
    @POST("/login")
    Call<Map<String, String>> login(@Body Map<String,String> req);
    @POST("/addUser")
    Call<Map<String, String>> addUser(@Body Map<String, String> req);
    @POST("/lb/addScore")
    Call<Map<String,String>> addScore(@Body Map<String,String> req);
    @POST("/lb/getLeaderBoard")
    Call<LeaderBoardResponse> getLeaderBoard(@Body Map<String,String> req);
}
