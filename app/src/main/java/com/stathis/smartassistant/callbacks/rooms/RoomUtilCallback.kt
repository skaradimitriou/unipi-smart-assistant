package com.stathis.smartassistant.callbacks.rooms

import com.stathis.smartassistant.models.RoomUtil

fun interface RoomUtilCallback {
    fun onRoomUtilTap(util : RoomUtil)
}