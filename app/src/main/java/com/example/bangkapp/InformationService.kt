package com.example.bangkapp

import retrofit2.Call
import retrofit2.http.GET

interface InformationService {
    @GET("information")
    fun getInformation(): Call<List<Information>>
}