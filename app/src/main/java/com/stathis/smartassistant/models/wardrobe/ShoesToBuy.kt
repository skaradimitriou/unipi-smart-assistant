package com.stathis.smartassistant.models.wardrobe

import com.stathis.smartassistant.abstraction.LocalModel

data class ShoesToBuy(
    val title: String,
    val imageUrl: String,
    val price: Double,
    val category: ShoeCategory
) : LocalModel {
    constructor() : this("", "", 0.0, ShoeCategory.EVERYDAY)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Shoes -> title == obj.title && category == obj.category
        else -> false
    }
}