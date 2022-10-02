package com.stathis.smartassistant.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.smartassistant.R
import com.stathis.smartassistant.models.TransportationOption

/**
 * This file contains the binding adapters that are used across the app
 */

@BindingAdapter("imageResource")
fun ImageView.imageResource(imageResource: Int? = null) {
    imageResource?.let {
        this.setImageResource(imageResource)
    } ?: kotlin.run {
        this.setImageResource(R.mipmap.ic_launcher)
    }
}

@BindingAdapter("loadImg")
fun ImageView.loadImg(url: String? = null) {
    url?.let {
        Glide.with(this.context).load(url).into(this)
    } ?: kotlin.run {
        setImageResource(R.mipmap.ic_launcher)
    }
}

@BindingAdapter("bindEstimatedTime")
fun TextView.bindEstimatedTime(minutes: Int) {
    text = context.getString(R.string.estimated_time_minutes, minutes.toString())
}

@BindingAdapter("locationRange")
fun TextView.locationRange(distance: Int) {
    text = context.getString(R.string.shop_range, distance)
}

@BindingAdapter("trafficLevel")
fun TextView.trafficLevel(transportationOption: TransportationOption) {
    text = when (transportationOption.estimatedMinutes) {
        in 10..25 -> {
            setTextColor(context.getActualColor(R.color.traffic_green))
            context.getString(R.string.traffic_low)
        }
        in 26..35 -> {
            setTextColor(context.getActualColor(R.color.traffic_yellow))
            context.getString(R.string.traffic_medium)
        }
        else -> {
            setTextColor(context.getActualColor(R.color.traffic_red))
            context.getString(R.string.traffic_high)
        }
    }
}

@BindingAdapter("productPrice")
fun TextView.productPrice(price: Double) {
    text = context.getString(R.string.coffee_price_euros, price)
}