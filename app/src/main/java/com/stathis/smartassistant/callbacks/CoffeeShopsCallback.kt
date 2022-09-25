package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.CoffeeShop

fun interface CoffeeShopsCallback {
    fun onShopTap(shop : CoffeeShop)
}