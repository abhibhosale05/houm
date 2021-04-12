package com.houm.android.houmweather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.houm.android.houmweather.R
import com.houm.android.houmweather.api.Endpoints
import com.houm.android.houmweather.base.AbstractBaseRecyclerListAdapter
import com.houm.android.houmweather.common.DateUtils
import com.houm.android.houmweather.dto.Weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hourly_weather_list_row_item.view.*
import java.text.DecimalFormat

class HourlyWeatherListAdapter(private var weathers: List<Weather>?, context: Context?) :
    AbstractBaseRecyclerListAdapter<HourlyWeatherViewHolder>(context) {


    override fun getViewHolder(parent: ViewGroup, viewType: Int) = HourlyWeatherViewHolder(
        LayoutInflater.from(context).inflate(R.layout.hourly_weather_list_row_item, null)
    )

    override fun updateList(list: List<Any?>?) {
        this.weathers = list as? List<Weather>
        notifyDataSetChanged()
    }

    override fun getItem(position: Int) = weathers?.get(position)

    override fun getItemCount() = weathers?.size ?: 0

    override fun getItemViewType(position: Int) = ITEM

    override fun onBindViewHolder(viewHolder: HourlyWeatherViewHolder, position: Int) {
        weathers?.get(position)?.let { weather ->
            viewHolder.itemView.hour_textview.text = DateUtils.getHour(weather.dt)
            Picasso.get().load(String.format(Endpoints.ICON_URL, weather.weather[0].icon))
                .into(viewHolder.itemView.temp_imageview)
            viewHolder.itemView.temp_textview.text = context?.getString(R.string.str_temp)?.let {
                String.format(
                    it,
                    DecimalFormat("#.#").format(weather.temp)
                )
            }

        }

    }


}