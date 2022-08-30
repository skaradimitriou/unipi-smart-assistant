package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.TransportationOption

fun interface TransportOptionCallback {
    fun onOptionTap(option : TransportationOption)
}