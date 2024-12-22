package com.example.bangkapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface InformationService {
    @GET("information")
    fun getInformation(): Call<List<Information>>

    @GET("information/replies/{id}")
    fun getInformationReplies(@Path("id") id: Int): Call<List<InformationReplies>>

    @POST("information")
    fun addInformation(@Body information: Information): Call<Information>

    @POST("information/replies/")
    fun addInformationReplies(@Body informationReplies: InformationReplies): Call<InformationReplies>


}