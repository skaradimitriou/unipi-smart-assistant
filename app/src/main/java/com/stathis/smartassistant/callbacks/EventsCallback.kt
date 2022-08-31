package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.Event

fun interface EventsCallback {
    fun onEventsClick(event: Event)
}