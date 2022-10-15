package com.stathis.smartassistant.util

import android.text.SpannableStringBuilder
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.smartassistant.R
import com.stathis.smartassistant.models.Coffee
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.models.TransportationOption
import com.stathis.smartassistant.models.wardrobe.AddressInfo


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
        Glide.with(this.context).load(url).placeholder(R.mipmap.ic_launcher).into(this)
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

@BindingAdapter("servingTime")
fun TextView.servingTime(servingTime: Int) {
    text = context.getString(R.string.shop_serving_time, servingTime)
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

@BindingAdapter("coffeeTitle")
fun TextView.coffeeTitle(coffee: Coffee?) {
    text = context.getString(
        R.string.coffee_title_with_sugar,
        coffee?.title,
        coffee?.sugar?.toUiText()
    )
}

@BindingAdapter("startTime")
fun TextView.startTime(event: Event) {
    val startTime = event.getStartTime()
    text = context.getString(R.string.moving_time_estimation, event.time, startTime)
}

@BindingAdapter("capitalize")
fun TextView.capitalize(string: String) {
    text = string.replaceFirstChar(Char::titlecase)
}

@BindingAdapter("plannerDate")
fun TextView.plannerDate(date: String) {
    val day = date.getDay()
    val month = date.getMonthName()

    val spannableText = SpannableStringBuilder()
        .bold { append(day) }
        .append("\n")
        .append(month)

    text = spannableText
}

@BindingAdapter("setFullName")
fun TextView.setFullName(data : AddressInfo) {
    text = data.firstName + " " + data.lastName
}

@BindingAdapter("rating")
fun TextView.rating(rating: Double) {
    text = context.getString(R.string.rating_text, rating)
}