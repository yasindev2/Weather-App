package uz.yasindev.weatherapp.core.models


import com.google.gson.annotations.SerializedName

data class Hour(
    val condition: Condition,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("temp_c")
    val tempC: Double,
    val time: String
)