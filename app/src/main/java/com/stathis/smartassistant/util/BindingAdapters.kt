package com.stathis.smartassistant.util

import android.content.res.ColorStateList
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.smartassistant.R
import com.stathis.smartassistant.models.*
import com.stathis.smartassistant.models.wardrobe.AddressInfo


/**
 * This file contains the binding adapters that are used across the app
 */

@BindingAdapter("imageResource")
fun ImageView.imageResource(imageResource: Int? = null) {
    imageResource?.let {
        this.setImageResource(imageResource)
    } ?: kotlin.run {
        this.setImageResource(R.mipmap.smarty_logo)
    }
}

@BindingAdapter("loadImg")
fun ImageView.loadImg(url: String? = null) {
    url?.let {
        Glide.with(this.context).load(url).placeholder(R.mipmap.smarty_logo).into(this)
    } ?: kotlin.run {
        setImageResource(R.mipmap.smarty_logo)
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
fun TextView.setFullName(data: AddressInfo) {
    text = data.firstName + " " + data.lastName
}

@BindingAdapter("rating")
fun TextView.rating(rating: Double) {
    text = context.getString(R.string.rating_text, rating)
}

@BindingAdapter("roomUtil")
fun ImageView.roomUtil(util: RoomUtil) {
    val image = when (util.title) {
        context.getString(R.string.lights_screen_title) -> R.drawable.ic_light
        context.getString(R.string.temperature_screen_title) -> R.drawable.ic_air
        context.getString(R.string.tv_screen_title) -> R.drawable.ic_tv
        context.getString(R.string.oven) -> R.drawable.ic_oven
        context.getString(R.string.fridge) -> R.drawable.ic_fridge
        context.getString(R.string.coffee_machine) -> R.drawable.ic_coffee_machine
        context.getString(R.string.music) -> R.drawable.ic_music
        context.getString(R.string.water_plants) -> R.drawable.ic_water
        context.getString(R.string.garage_door) -> R.drawable.ic_garage
        else -> R.drawable.ic_add_item
    }

    setBackgroundResource(image)

    //sets the backgroundTint of the util according to enabled value
    backgroundTintList = if (util.enabled) {
        ColorStateList.valueOf(context.getActualColor(R.color.white))
    } else {
        ColorStateList.valueOf(context.getActualColor(R.color.navy_blue))
    }
}

@BindingAdapter("roomUtilEnabled")
fun View.roomUtilEnabled(util: RoomUtil) {
    if (util.enabled) {
        val color = context.getActualColor(R.color.sky_blue)
        setBackgroundColor(color)
    } else {
        val color = context.getActualColor(R.color.white)
        setBackgroundColor(color)
    }
}

@BindingAdapter("enabledDevices")
fun TextView.enabledDevices(room: Room) {
    val enabledDevices = room.utils.count { it.enabled }
    text = when (enabledDevices) {
        0 -> context.getString(R.string.no_active_devices)
        1 -> context.getString(R.string.one_active_device)
        else -> context.getString(R.string.many_active_device, enabledDevices)
    }
}

@BindingAdapter("energyConsumption")
fun TextView.energyConsumption(model: EnergyModel) {
    text = context.getString(R.string.energy_consumption_kwh, model.value)
}