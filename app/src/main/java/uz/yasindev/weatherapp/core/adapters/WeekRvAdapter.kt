package uz.yasindev.weatherapp.core.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.yasindev.weatherapp.R
import uz.yasindev.weatherapp.core.models.Forecastday
import uz.yasindev.weatherapp.databinding.ItemDayRvBinding
import uz.yasindev.weatherapp.databinding.ItemWeekRvBinding

class WeekRvAdapter : RecyclerView.Adapter<WeekRvAdapter.ViewHolder>() {

    private val data = ArrayList<Forecastday>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<Forecastday>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemWeekRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(forecastDay: Forecastday, position: Int) {

            if (forecastDay.day.condition.text.contains("sunny", true)) {
                binding.imageView.setImageResource(R.drawable.sun)
            } else if (forecastDay.day.condition.text.contains("cloudy", true)) {
                binding.imageView.setImageResource(R.drawable.cloudy)
            } else if (forecastDay.day.condition.text.contains("rain", true)) {
                binding.imageView.setImageResource(R.drawable.rainy)
            } else if (forecastDay.day.condition.text.contains("snow", true)) {
                binding.imageView.setImageResource(R.drawable.snowy)
            } else if (forecastDay.day.condition.text.contains("fog", true)) {
                binding.imageView.setImageResource(R.drawable.foggy)
            } else binding.imageView.setImageResource(R.drawable.cloudy)


            binding.gradusTv.text = "${forecastDay.hour[position].tempC.toInt()} °C"
            binding.timeTv.text = forecastDay.hour[position].time.subSequence(0, 10)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWeekRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position], position)
    }

}