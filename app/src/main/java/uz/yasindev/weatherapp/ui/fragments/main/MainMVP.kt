package uz.yasindev.weatherapp.ui.fragments.main

import uz.yasindev.weatherapp.core.api.WeatherService
import uz.yasindev.weatherapp.core.models.WeatherResponce

interface MainMVP {

    interface View {
        fun setData(data: WeatherResponce)
        fun setError(message:String)
    }

    interface Presenter {
        fun getAllData(city:String)
    }

}