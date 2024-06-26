package com.example.delta_task2

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit=Retrofit.Builder().baseUrl("https://chasedeux.vercel.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val obstacleRetrofit: ApiInterface = retrofit.create(ApiInterface::class.java)

interface ApiInterface {
    @GET("obstacleLimit")
    suspend fun getObstacleLimit():Obstacle

    @GET("image")
    suspend fun getTom(@Query("character") tom: String):ResponseBody

}