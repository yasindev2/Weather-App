package uz.yasindev.weatherapp.core.api

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.yasindev.weatherapp.core.models.WeatherResponce

interface WeatherService {


    @GET("v1/forecast.json")
    fun getAllData(
        @Query("key") apiKey: String,
        @Query("q") cityName: String,
        @Query("days") days:Int
    ): Single<Response<WeatherResponce>>

}