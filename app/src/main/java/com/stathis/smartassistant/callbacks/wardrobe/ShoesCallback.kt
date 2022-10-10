package com.stathis.smartassistant.callbacks.wardrobe

import com.stathis.smartassistant.models.wardrobe.AddShoePromo
import com.stathis.smartassistant.models.wardrobe.Shoes

interface ShoesCallback {
    fun onShoesTap(shoes: Shoes)
    fun onAddItemTap(promo: AddShoePromo)
}