package com.example.healthydiet;

import com.example.healthydiet.Model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiInterface {

    @GET("top-headlines")
    Call<News> getNews(
            @Query("country") String country,
            @Query("category")String category,
            @Query("apiKey") String apiKey
    );


}
