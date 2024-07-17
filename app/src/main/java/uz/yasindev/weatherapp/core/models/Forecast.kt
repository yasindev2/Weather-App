package uz.yasindev.weatherapp.core.models


import com.google.gson.annotations.SerializedName

data class Forecast(
    val forecastday: List<Forecastday>
)