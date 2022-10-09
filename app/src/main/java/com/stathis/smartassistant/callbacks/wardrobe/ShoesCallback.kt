package com.stathis.smartassistant.callbacks.wardrobe

import com.stathis.smartassistant.models.wardrobe.Shoes

fun interface ShoesCallback {
    fun onShoesTap(shoes : Shoes)
}