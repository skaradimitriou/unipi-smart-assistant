package com.stathis.smartassistant.callbacks.wardrobe

import com.stathis.smartassistant.models.wardrobe.ShoesToBuy

fun interface ShoesToBuyCallback {
    fun onShoesTap(shoes : ShoesToBuy)
}