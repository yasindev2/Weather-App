package uz.yasindev.weatherapp.ui.fragments.main

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.yasindev.weatherapp.core.api.ApiClient
import uz.yasindev.weatherapp.core.models.WeatherResponce

class MainPresenter(val view: MainMVP.View) : MainMVP.Presenter {


    private val service = ApiClient.getService()
    private val apiKey = "3ab8d9551f414d8f854134639241407"

    override fun getAllData(city:String) {
        service.getAllData(apiKey, city,7).
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<Response<WeatherResponce>>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view.setError("Internet is not working")
                }

                override fun onSuccess(t: Response<WeatherResponce>) {
                    if (t.isSuccessful) {
                        t.body()?.let { view.setData(it) }
                    }
                    else view.setError("Failed")
                }

            })



    }


}