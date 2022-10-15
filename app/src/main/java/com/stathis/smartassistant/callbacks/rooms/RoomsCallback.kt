package com.stathis.smartassistant.callbacks.rooms

import com.stathis.smartassistant.models.Room

fun interface RoomsCallback {
    fun onRoomTap(room: Room)
}