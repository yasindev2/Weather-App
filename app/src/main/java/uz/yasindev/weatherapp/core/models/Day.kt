package uz.yasindev.weatherapp.core.models


import com.google.gson.annotations.SerializedName

data class Day(
    val condition: Condition,
    @SerializedName("maxtemp_c")
    val maxtempC: Double,
    @SerializedName("mintemp_c")
    val mintempC: Double
)