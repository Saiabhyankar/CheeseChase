package com.example.delta_task2

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private val retrofit=Retrofit.Builder().baseUrl("https://chasedeux.vercel.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val obstacleRetrofit: ApiInterface = retrofit.create(ApiInterface::class.java)

interface ApiInterface {
    //TO FETCH THE OBSTACLE LIMIT FROM THE REST API
    @GET("obstacleLimit")
    suspend fun getObstacleLimit():Obstacle
    // TO FETCH THE IMAGES OF TOM,JERRY AND OBSTACLE
    @GET("image")
    suspend fun getTom(@Query("character") Object: String):ResponseBody

    @POST("obstacleCourse")
    suspend fun getObstacleCourse(@Body obstacleCourseRequest: ObstacleCourseRequest): Call<ObstacleCourseResponse>


}