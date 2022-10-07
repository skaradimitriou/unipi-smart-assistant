package com.stathis.smartassistant.models

data class EventResponse(
    val event: Event
) {
    constructor() : this(
        Event(
            "", "", "",
            TransportationOption("", 0, 0, false),
            ParkingInfo("", ""),
            CoffeeShop("", 0, 0, 0, true, false),
            Coffee("", 0, SugarType.SWEET, 0.0)
        )
    )
}
