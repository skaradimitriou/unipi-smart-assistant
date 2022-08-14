package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.Room

interface HomeRoomCallback {
    fun onRoomClick(room : Room)
}