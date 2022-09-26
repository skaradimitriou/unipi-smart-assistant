package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class Event(
    val title: String,
    val date: String,
    val time: String,
    val transportationOption: TransportationOption? = null,
    val shop: CoffeeShop? = null,
    val coffee: Coffee? = null,
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Event -> title == obj.title && transportationOption == obj.transportationOption
        else -> false
    }

    fun hasAdditionals(): Boolean {
        return shop != null && coffee != null
    }
}