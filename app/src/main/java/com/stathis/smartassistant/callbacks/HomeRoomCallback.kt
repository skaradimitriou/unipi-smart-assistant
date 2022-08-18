package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.Room

fun interface HomeRoomCallback {
    fun onRoomClick(room : Room)
}