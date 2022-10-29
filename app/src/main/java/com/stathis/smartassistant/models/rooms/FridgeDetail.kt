package com.stathis.smartassistant.models.rooms

import com.stathis.smartassistant.abstraction.LocalModel

data class FridgeDetail(
    val title: String,
    val quantity: Int,
    val imageUrl: String
) : LocalModel {
    constructor() : this("", 0, "")

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is FridgeDetail -> title == obj.title && quantity == obj.quantity && imageUrl == obj.imageUrl
        else -> false
    }
}
