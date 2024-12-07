package com.example.bangkapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InformationService {
    @GET("information")
    fun getInformation(): Call<List<Information>>

    @POST("information")
    fun addInformation(@Body information: Information): Call<Information>

}