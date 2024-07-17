package uz.yasindev.weatherapp.core.models


import com.google.gson.annotations.SerializedName

data class Condition(
    val code: Int,
    val text: String
)