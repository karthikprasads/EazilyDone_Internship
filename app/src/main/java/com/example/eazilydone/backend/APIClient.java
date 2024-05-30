package com.example.eazilydone.backend;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;
public class APIClient {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        String st= "https://intuitive-peace-production.up.railway.app/";
        Retrofit r = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(st)
                .build();
        return r;
    }
    public static APIService getBotService(){
        return getRetrofit().create(APIService.class);
    }
}
