package uz.yasindev.weatherapp.core.models


import com.google.gson.annotations.SerializedName

data class WeatherResponce(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)