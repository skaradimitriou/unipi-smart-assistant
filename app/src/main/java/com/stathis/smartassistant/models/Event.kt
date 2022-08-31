package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class Event(
    val title: String,
    val date : String,
    val time : String,
    val transportationOption: TransportationOption
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Event -> title == obj.title && transportationOption == obj.transportationOption
        else -> false
    }
}
