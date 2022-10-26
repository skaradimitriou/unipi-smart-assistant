package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.RoomPromo
import com.stathis.smartassistant.models.SmartLockerPromo

interface HomeScreenCallback {
    fun onRoomsPromoClick(promo: RoomPromo)
    fun onSmartLockerClick(promo: SmartLockerPromo)
}