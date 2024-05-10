package com.example.weatherforecastapp.POJO

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: Int,
    @SerializedName("icon") val icon: Int,
)
