package uz.yasindev.weatherapp.core.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {


    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.weatherapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }


    fun getService():WeatherService{
        return createRetrofit().create(WeatherService::class.java)
    }

}