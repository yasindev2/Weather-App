package uz.yasindev.weatherapp.core.models


import com.google.gson.annotations.SerializedName

data class Forecastday(
    val astro: Astro,
    val day: Day,
    val hour: List<Hour>
)