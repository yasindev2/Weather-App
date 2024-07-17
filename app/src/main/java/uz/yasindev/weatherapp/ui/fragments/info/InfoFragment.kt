package uz.yasindev.weatherapp.ui.fragments.info

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import uz.yasindev.weatherapp.core.adapters.DayRvAdapter
import uz.yasindev.weatherapp.core.adapters.WeekRvAdapter
import uz.yasindev.weatherapp.core.models.WeatherResponce
import uz.yasindev.weatherapp.databinding.FragmentInfoBinding
import uz.yasindev.weatherapp.ui.fragments.main.MainMVP
import uz.yasindev.weatherapp.ui.fragments.main.MainPresenter

class InfoFragment : Fragment(),MainMVP.View {

    private val binding by lazy { FragmentInfoBinding.inflate(layoutInflater) }
    private lateinit var presenter: MainMVP.Presenter
    private val dayRvAdapter = DayRvAdapter()
    private val weekRvAdapter = WeekRvAdapter()
    private val args :InfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MainPresenter(this)
        presenter.getAllData(args.cityName)


    }


    override fun setData(data: WeatherResponce) {
        binding.progressBar.visibility = View.INVISIBLE

        dayRvAdapter.setData(data.forecast.forecastday as ArrayList)
        binding.dayForecastRv.adapter = dayRvAdapter

        weekRvAdapter.setData(data.forecast.forecastday)
        binding.weekForecastRv.adapter = weekRvAdapter
    }

    override fun setError(message: String) {
        Snackbar.make(binding.root,message,2000)
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.WHITE)
            .show()
    }

}