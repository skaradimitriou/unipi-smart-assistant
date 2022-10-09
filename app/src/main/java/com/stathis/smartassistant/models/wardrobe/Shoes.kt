package com.stathis.smartassistant.models.wardrobe

import com.stathis.smartassistant.abstraction.LocalModel

data class Shoes(
    val title: String,
    val imageUrl: String,
    val size: Int,
    val category: ShoeCategory
) : LocalModel {
    constructor() : this("", "", 0, ShoeCategory.Everyday)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Shoes -> title == obj.title && category == obj.category
        else -> false
    }
}

enum class ShoeCategory {
    Everyday, Sports, Special
}
