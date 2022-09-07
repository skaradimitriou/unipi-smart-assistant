package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.RoomUtil

fun interface RoomUtilsCallback {
    fun onRoomUtilTap(model : RoomUtil)
}