package com.stathis.smartassistant.models

import com.google.firebase.Timestamp
import java.util.*

data class EventResponse(
    val event: Event
) {
    constructor() : this(
        Event(
            "", "", Timestamp(Date()),"", "",
            TransportationOption("", 0, 0, false),
            ParkingInfo("", ""),
            CoffeeShop("", 0, 0, 0, true, false),
            Coffee("", 0, SugarType.SWEET, 0.0)
        )
    )
}
