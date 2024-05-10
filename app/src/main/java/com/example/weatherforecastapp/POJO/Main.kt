package com.example.weatherforecastapp.POJO

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp") val temp : Double,
    @SerializedName("feels_alike") val feels_alike : Double,
    @SerializedName("temp_min") val temp_min : Double,
    @SerializedName("temp_max") val temp_max : Double,
    @SerializedName("pressure") val pressure : Int,
    @SerializedName("humidity") val humidity : Int,
)
