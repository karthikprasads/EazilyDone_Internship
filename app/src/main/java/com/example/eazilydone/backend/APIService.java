package com.example.eazilydone.backend;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("bot/response")
    Call<Map<String, String>> getBotResponse(@Body Map<String, String> req);
    @POST("/login")
    Call<Map<String, String>> login(@Body Map<String,String> req);
    @POST("/addUser")
    Call<Map<String, String>> addUser(@Body Map<String, String> req);
}
