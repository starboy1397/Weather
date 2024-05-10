package com.example.weatherforecastapp.Utilities

import com.example.weatherforecastapp.POJO.ModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun getCurrentWeatherData(
        @Query("lat") latitude : String,
        @Query("lon") longitude : String,
        @Query("APPID") api_key : String
    ):Call<ModelClass>

    @GET("weather?APPID=771cea381ccf6083cf4e6a1c7f820902&units=metric")
    fun getCityWeatherData(
        @Query("q") cityName : String,
        @Query("APPID") api_key : String
    ):Call<ModelClass>
}