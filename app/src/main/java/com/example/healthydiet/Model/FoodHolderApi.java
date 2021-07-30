package com.example.healthydiet.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FoodHolderApi {
    @Headers({
            "x-rapidapi-key: f699e34388msh3c35db9605255ecp19cc42jsneb6d38239d8c",
            "x-rapidapi-host: edamam-food-and-grocery-database.p.rapidapi.com"
    })

    @GET("parser")
    Call<FoodPost> getParsed(@Query("ingr") String food);
}
