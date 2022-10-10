package com.stathis.smartassistant.callbacks.wardrobe

import com.stathis.smartassistant.models.wardrobe.Eshop

fun interface EshopsCallback {
    fun onEshopTap(eshop: Eshop)
}