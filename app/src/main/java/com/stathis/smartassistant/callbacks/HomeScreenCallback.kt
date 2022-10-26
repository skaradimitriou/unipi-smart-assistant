package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.RoomPromo
import com.stathis.smartassistant.models.SmartLockerPromo
import com.stathis.smartassistant.models.rooms.GeneralUtil

interface HomeScreenCallback {
    fun onRoomsPromoClick(promo: RoomPromo)
    fun onSmartLockerClick(promo: SmartLockerPromo)
    fun onHomeUtilClick(util : GeneralUtil)
}