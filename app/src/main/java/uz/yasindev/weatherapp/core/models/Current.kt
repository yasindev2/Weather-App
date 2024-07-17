package uz.yasindev.weatherapp.core.models


import com.google.gson.annotations.SerializedName

data class Current(
    val condition: Condition,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double
)