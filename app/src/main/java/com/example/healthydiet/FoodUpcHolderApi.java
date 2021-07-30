package com.example.healthydiet;

import com.example.healthydiet.Model.UpcFood;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FoodUpcHolderApi {
    @Headers({
            "x-rapidapi-key: f699e34388msh3c35db9605255ecp19cc42jsneb6d38239d8c",
            "x-rapidapi-host: edamam-food-and-grocery-database.p.rapidapi.com"
    })

    @GET("parser")
    Call<UpcFood> getParsedUpc(@Query("upc") String foodUpc);
}
