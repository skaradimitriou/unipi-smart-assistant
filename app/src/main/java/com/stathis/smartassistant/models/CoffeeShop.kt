package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel
import kotlin.random.Random

data class CoffeeShop(
    val shopTitle: String,
    val shopImg: Int,
    val distance: Int = Random.nextInt(200, 400),
    val servingTime: Int = Random.nextInt(3, 15),
    val isOpen: Boolean? = true,
    var isBestOption: Boolean? = false
) : LocalModel {
    constructor() : this("", 0, 0, 0, true, false)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is CoffeeShop -> obj.shopTitle == shopTitle && obj.distance == distance && obj.isOpen == isOpen
        else -> false
    }
}
