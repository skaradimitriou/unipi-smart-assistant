package com.stathis.smartassistant.models

import android.annotation.SuppressLint
import com.stathis.smartassistant.abstraction.LocalModel
import java.time.Duration
import java.time.LocalTime

data class Event(
    val title: String,
    val date: String,
    val time: String,
    val transportationOption: TransportationOption? = null,
    val parkingInfo: ParkingInfo? = null,
    val shop: CoffeeShop? = null,
    val coffee: Coffee? = null,
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Event -> title == obj.title && transportationOption == obj.transportationOption
        else -> false
    }

    fun hasParkingInfo(): Boolean = parkingInfo != null

    fun hasAdditionals(): Boolean {
        return shop != null && coffee != null
    }

    @SuppressLint("NewApi")
    fun getTotalTime(): String {
        val transportMinutes = transportationOption?.estimatedMinutes ?: 0
        val servingTime = shop?.servingTime ?: 0
        val totalTime = transportMinutes + servingTime

        val time = LocalTime.MIN.plus(
            Duration.ofMinutes(totalTime.toLong())
        )

        return when {
            time.hour.toString() == "0" -> "${time.minute} λεπτά"
            time.hour.toString() == "1" -> "${time.hour} ώρα και ${time.minute} λεπτά"
            else -> "${time.hour} ώρες και ${time.minute} λεπτά"
        }
    }

    @SuppressLint("NewApi")
    fun getStartTime(): String {
        val transportMinutes = transportationOption?.estimatedMinutes ?: 0
        val servingTime = shop?.servingTime ?: 0
        val totalTime = transportMinutes + servingTime

        val finalTime = LocalTime.parse(time).minusMinutes(totalTime.toLong())
        return finalTime.toString()
    }
}