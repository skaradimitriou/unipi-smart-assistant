package com.stathis.smartassistant.models.wardrobe

import com.stathis.smartassistant.abstraction.LocalModel

data class Eshop(
    val title: String,
    val imageUrl: String,
    val rating: Double
) : LocalModel {
    constructor() : this("", "", 0.0)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Eshop -> false
        else -> false
    }
}
