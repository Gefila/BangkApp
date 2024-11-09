package com.example.bangkapp

import retrofit2.Call
import retrofit2.http.GET

interface FoodService {
    //read
    @GET("food")
    fun getFood(): Call<List<Food>>

}