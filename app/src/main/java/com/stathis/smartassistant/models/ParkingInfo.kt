package com.stathis.smartassistant.models

data class ParkingInfo(
    val slot: String = "",
    val company: String = ""
) {
    constructor() : this ("", "")
}