package uz.yasindev.weatherapp.ui.fragments.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.yasindev.weatherapp.R
import uz.yasindev.weatherapp.core.models.WeatherResponce
import uz.yasindev.weatherapp.databinding.FragmentMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


class MainFragment : Fragment(), MainMVP.View {

    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private lateinit var presenter: MainMVP.Presenter
    private lateinit var list:ArrayList<String>
    lateinit var spinnerSelectedItem:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataToSpinner()
        listenSpinner()

        presenter = MainPresenter(this)
        presenter.getAllData(spinnerSelectedItem)


    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun setData(data: WeatherResponce) {
        binding.progressBar.visibility = View.INVISIBLE

        when (data.current.isDay) {
            0 -> binding.dayTv.text = "Good Morning"
            1 -> binding.dayTv.text = "Good Evening"
            else -> binding.dayTv.text = "Good afternoon"
        }

        if (data.current.condition.text.contains("sunny", true)) {
            binding.imageView.setImageResource(R.drawable.sun)
        } else if (data.current.condition.text.contains("cloudy", true)) {
            binding.imageView.setImageResource(R.drawable.cloudy)
        } else if (data.current.condition.text.contains("rain", true)) {
            binding.imageView.setImageResource(R.drawable.rainy)
        } else if (data.current.condition.text.contains("snow", true)) {
            binding.imageView.setImageResource(R.drawable.snowy)
        }
        else if (data.current.condition.text.contains("fog", true)) {
            binding.imageView.setImageResource(R.drawable.foggy)
        }
        else binding.imageView.setImageResource(R.drawable.cloudy)


        binding.conditionTv.text = data.current.condition.text
        binding.gradusTv.text = "${data.current.tempC.toInt()} °C"

        binding.dayInfoTv.text = getCurrentTimeWithWeekday()

        binding.maxTempTv.text = "${data.forecast.forecastday[0].day.maxtempC} °C"
        binding.minTempTv.text = "${data.forecast.forecastday[0].day.mintempC} °C"

        binding.sunriseTv.text = data.forecast.forecastday[0].astro.sunrise
        binding.sunset.text = data.forecast.forecastday[0].astro.sunset


        binding.forecastBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToInfoFragment(spinnerSelectedItem))
        }

    }

    override fun setError(message: String) {
        Snackbar.make(binding.root, message, 2000)
            .setBackgroundTint(Color.WHITE)
            .setTextColor(Color.BLACK)
            .show()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTimeWithWeekday(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val time = current.format(formatter)
        val dayOfWeek = current.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        return "$dayOfWeek, $time"
    }

    private fun setDataToSpinner() {
        list = ArrayList()

        list.add("Tashkent")
        list.add("Russia")
        list.add("USA")
        list.add("Germany")
        list.add("Brazil")
        list.add("Dubai")
        list.add("Canada")

        val arrayAdapter = ArrayAdapter(
            findNavController().context,
            android.R.layout.simple_list_item_1,
            list
        )
        binding.spinner.adapter = arrayAdapter
    }

    private fun listenSpinner(){

        spinnerSelectedItem = "Tashkent"

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerSelectedItem = parent.getItemAtPosition(position).toString()
                presenter.getAllData(spinnerSelectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }


}