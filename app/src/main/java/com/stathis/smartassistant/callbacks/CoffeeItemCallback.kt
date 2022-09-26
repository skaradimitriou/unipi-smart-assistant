package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.Coffee

fun interface CoffeeItemCallback {
    fun onCoffeeTap(coffee : Coffee)
}